<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html >
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/bodyHeader.jsp" />

<title>Visualizar dados de Visita Domiciliar</title>
</head>


<body>
	<jsp:include page="../fragments/headTag.jsp" />

	<div class="col-md-7 col-md-offset-1 dist-footer" align="center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h2 class="panel-title">Informações de Relatório de Visita Domiciliar</h2>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<dl class="dl-horizontal">
						<dt>Candidato:</dt>
						<dd>${relatorio.aluno.nome}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Endereço:</dt>
						<dd>${relatorio.endereco}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Forma de acesso a casa:</dt>
						<dd>${relatorio.formaAcessoCasa }</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Curso:</dt>
						<dd>${relatorio.aluno.curso}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Semestre:</dt>
						 <dd>${relatorio.semestre}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Data da visita:</dt>
						<dd>${relatorio.dataVisita}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Técnicos Responsáveis:</dt>
						<dd></dd>
					</dl>

				</div>
			</div>
		</div>

		<div class="panel panel-primary" align="center">
			<div class="panel-heading">
				<h3 class="panel-title">I - Perfil Econômico: Despesas</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<dl class="dl-horizontal">
						<dt>Moradia:</dt>
						<dd>${relatorio.despesaMoradia}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Energia:</dt>
						<dd>${relatorio.despesaEnergia}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Telefone:</dt>
						<dd>${relatorio.despesaTelefone}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Educação:</dt>
						<dd>${relatorio.despesaEducacao}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Saúde:</dt>
						<dd>${relatorio.despesaSaude}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Alimentação:</dt>
						<dd>${relatorio.despesaAlimentacao}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Outros:</dt>
						<dd>${relatorio.despesaOutros}</dd>
					</dl>



				</div>
			</div>


			<div class="panel-heading">
				<h3 class="panel-title">I - Perfil Econômico: Receitas</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<dl>
						<dt>Nº de Pessoas residentes no domicílio:</dt>
						<dd>${relatorio.qtdPessoasResidentes}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Trabalho Formal:</dt>
						<dd>${relatorio.rendaTrabalhoFormal}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Trabalho Informal:</dt>
						<dd>${relatorio.rendaTrabalhoInformal}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Aposentadoria:</dt>
						<dd>${relatorio.rendaAposentadoria}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Benefícios Sociais:</dt>
						<dd>${relatorio.rendaBeneficioSocial}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Auxílio de Parentes:</dt>
						<dd>${relatorio.rendaAuxilioParente}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Outros:</dt>
						<!-- <dd>${aluno.nome}</dd> -->
					</dl>


				</div>
			</div>
		</div>

		<div class="panel panel-primary" align="center">
			<div class="panel-heading">
				<h3 class="panel-title">III - Moradia</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<table class="table">
						<thead>
							<tr class="info">
								<td>Item</td>
								<td>Quantidade</td>
								<td>Estado</td>
							</tr>
						</thead>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Varanda:</dt>
								</dl></td>
							<td>${relatorio.moradiaVarandaQtd}</td>
							<td>${relatorio.moradiaVarandaEstado}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Sala:</dt>
								</dl></td>
							<td>
								${relatorio.moradiaSalaQtd}
							</td>
							<td>${relatorio.moradiaSalaEstado}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Banheiro:</dt>
								</dl></td>
							<td>
								${relatorio.moradiaBanheiroQtd}
							</td>
							<td>${relatorio.moradiaBanheiroEstado}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Quarto:</dt>
								</dl></td>
							<td>
								${relatorio.moradiaQuartoQtd}
							</td>
							<td>${relatorio.moradiaQuartoEstado}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Cozinha:</dt>
								</dl></td>
							<td>
								${relatorio.moradiaCozinhaQtd}
							</td>
							<td>${relatorio.moradiaCozinhaEstado}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Quintal:</dt>
								</dl></td>
							<td>
								${relatorio.moradiaQuintalQtd}
							</td>
							<td>${relatorio.moradiaQuintalEstado}</td>
						</tr>

					</table>
				</div>
			</div>

			<div class="panel-heading">
				<h3 class="panel-title">IV - Utensílios Domésticos (Eletro
					Eletrônicos)</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<table class="table">
						<thead>
							<tr class="info">
								<td>Item</td>
								<td>Quantidade</td>
								<td>Observação</td>
							</tr>
						</thead>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>TV:</dt>
								</dl></td>
							<td>${relatorio.utensilioTvQtd}</td>
							<td>${relatorio.utensilioTvObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Som:</dt>
								</dl></td>
							<td>${relatorio.utensilioSomQtd}</td>
							<td>${relatorio.utensilioSomObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Computador:</dt>
								</dl></td>
							<td>${relatorio.utensilioComputadorQtd}</td>
							<td>${relatorio.utensilioComputadorObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Fogão:</dt>
								</dl></td>
							<td>${relatorio.utensilioFogaoQtd}</td>
							<td>${relatorio.utensilioFogaoObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Geladeira:</dt>
								</dl></td>
							<td>${relatorio.utensilioGeladeiraQtd}</td>
							<td>${relatorio.utensilioGeladeiraObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Freezer:</dt>
								</dl></td>
							<td>${relatorio.utensilioFreezerQtd}</td>
							<td>${relatorio.utensilioFreezerObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Lavadora:</dt>
								</dl></td>
							<td>${relatorio.utensilioLavadoraQtd}</td>
							<td>${relatorio.utensilioLavadoraObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>DVD:</dt>
								</dl></td>
							<td>${relatorio.utensilioDvdQtd}</td>
							<td>${relatorio.utensilioDvdObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Outros:</dt>
								</dl></td>
							<td>${relatorio.utensilioOutrosQtd}</td>
							<td>${relatorio.utensilioOutrosObservacao}</td>
						</tr>
					</table>
				</div>
			</div>


		</div>

		<div class="panel panel-primary" align="center">
			<div class="panel-heading">
				<h3 class="panel-title">V - Bens Móveis</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<table class="table">
						<thead>
							<tr class="info">
								<td>Item</td>
								<td>Quantidade</td>
								<td>Observação</td>
							</tr>
						</thead>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Moto:</dt>
								</dl></td>
							<td>${relatorio.bemMovelMotoQtd}</td>
							<td>${relatorio.bemMovelMotoObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Bicicleta:</dt>
								</dl></td>
							<td>${relatorio.bemMovelBicicletaQtd}</td>
							<td>${relatorio.bemMovelBicicletaObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Carro:</dt>
								</dl></td>
							<td>${relatorio.bemMovelCarroQtd}</td>
							<td>${relatorio.bemMovelCarroObservacao}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Outros:</dt>
								</dl></td>
							<td>${relatorio.bemMovelOutrosQtd}</td>
							<td>${relatorio.bemMovelOutrosObservacao}</td>
						</tr>
					</table>
				</div>
			</div>



		</div>

		<div class="panel panel-primary" align="center">
			<div class="panel-heading">
				<h3 class="panel-title">VI - Síntese Perfil Econômico</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<table class="table">
						<thead>
							<tr class="info">
								<td>Item</td>
								<td>Compatível com a receita?</td>
							</tr>
						</thead>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Utensílios Doméstico (eletro eletrônicos):</dt>
								</dl></td>
							<td>${relatorio.perfilCompativelUtensilioDomestico}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Bens Móveis:</dt>
								</dl></td>
							<td>${relatorio.perfilCompativelBensMoveis}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Maquinário:</dt>
								</dl></td>
							<td>${relatorio.perfilCompativelMaquinario}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Aspecto físico da residência:</dt>
								</dl></td>
							<td>${relatorio.perfilCompativelAspectoFisicoResidencia}</td>
						</tr>
						<tr>
							<td><dl class="dl-horizontal">
									<dt>Outros:</dt>
								</dl></td>
							<td>${relatorio.perfilCompativelOutros}</td>
						</tr>

					</table>
				</div>
			</div>



		</div>


		<div class="panel panel-primary" align="center">
			<div class="panel-heading">
				<h3 class="panel-title">Análise e Descrição da Realidade/Perfil
					Psicossosial</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<dl>
						<dd>${relatorio.analiseDescricaoRealidade}</dd>
					</dl>
				</div>
			</div>
			<div class="panel-heading">
				<h3 class="panel-title">Parecer Final</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">

					<dl class="dl-horizontal">
						<dt>Deferido:</dt>
						<dd>${relatorio.parecerFinalDeferido}</dd>
					</dl>
					<dl class="dl-horizontal">
						<dt>Data do Relatório:</dt>
						<dd>${relatorio.dataRelatorio}</dd>
					</dl>
				</div>
			</div>
		</div>


	</div>


	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>