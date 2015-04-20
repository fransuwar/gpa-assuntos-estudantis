package br.ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.gpa.enums.Status;
import br.ufc.quixada.npi.gpa.model.Aluno;
import br.ufc.quixada.npi.gpa.model.SelecaoBolsa;
import br.ufc.quixada.npi.gpa.service.SelecaoBolsaService;
import br.ufc.quixada.npi.service.impl.GenericServiceImpl;
import br.ufc.quixada.npi.util.SimpleMap;

@Named
public class SelecaoBolsaServiceImpl extends GenericServiceImpl<SelecaoBolsa> implements
		SelecaoBolsaService {

	@Override
	public List<SelecaoBolsa> getSelecaoBolsasSubmetidos() {
		return find(QueryType.JPQL, "from SelecaoBolsa as p where p.status != 'NOVO' ", null);
	}
	
	@Override
	public List<SelecaoBolsa> getSelecaoBolsasAtribuidos() {
		return find(QueryType.JPQL, "from SelecaoBolsa as p where p.status = 'AGUARDANDO_PARECER' ", null);
	}

	@Override
	public List<SelecaoBolsa> getSelecaoBolsasByUsuario(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return find(QueryType.JPQL, "from SelecaoBolsa where usuario_id = :id", params);
	}
	
	@Override
	public List<SelecaoBolsa> getSelecaoBolsasAguardandoParecer() {
		return find(QueryType.JPQL, "from SelecaoBolsa as p where p.status = 'AGUARDANDO_PARECER'", null);
	}

	@Override
	@Transactional
	public boolean existsSelecaoEquals(SelecaoBolsa selecaoBolsa) {
		List<SelecaoBolsa> selecoes = find(QueryType.JPQL, "from SelecaoBolsa as p where p.tipoBolsa = :tipo and p.ano = :ano and p.sequencial = :sequencial",
				new SimpleMap<String, Object>("tipo",selecaoBolsa.getTipoBolsa(), "ano",selecaoBolsa.getAno(),"sequencial",selecaoBolsa.getSequencial() ));
		if(selecoes == null || selecoes.isEmpty()){
			return false;
		}
		return true;
	
	}

	@Override
	@Transactional
	public SelecaoBolsa getSelecaoBolsaComDocumentos(Integer id) {
		return (SelecaoBolsa) findFirst("SelecaoBolsa.findSelecaoBolsaComDocumentos", new SimpleMap<String, Object>("selecaoBolsaId", id));
	}

	@Override
	@Transactional
	public void atualizaStatusSelecaoBolsa() {
		
		for(SelecaoBolsa selecao:this.find(SelecaoBolsa.class)){
			DateTime dataTermino = new DateTime(selecao.getDataTermino());
			DateTime dataInicio = new DateTime(selecao.getDataInicio());
			if( (dataInicio.isBeforeNow() || dataInicio.isEqualNow()) 
				&& selecao.getStatus().equals(Status.NOVA)){
				selecao.setStatus(Status.INSC_ABERTA);
				this.update(selecao);
			}else if( (dataTermino.isBeforeNow() || dataTermino.isEqualNow() )
					  && selecao.getStatus().equals(Status.INSC_ABERTA)){
				selecao.setStatus(Status.PROC_SELETIVO);
				this.update(selecao);
			}
		}
	}

}
