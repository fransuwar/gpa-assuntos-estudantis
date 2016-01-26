<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="gpa" tagdir="/WEB-INF/tags"%>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Detalhes Inscrição Auxilio Moradia</title>
</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container" align="center">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Detalhes da Inscrição de Auxílio
					Moradia</h3>
			</div>
			<div class="panel-body">
				<dl class="col-sm-12">
					<dt class="col-sm-3">Data da Inscrição:</dt>
					<dd class="col-sm-3">
						<fmt:formatDate value="${inscricao.data}" pattern="dd/MM/yyyy" />
					</dd>
					<dt class=" col-sm-3">Tipo da Seleção:</dt>
					<dd class="col-sm-3">${inscricao.selecao.tipoSelecao.nome}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Resultado:</dt>
					<dd class="col-sm-3">${inscricao.resultado.nome}</dd>
					<dt class="col-sm-3">Observações:</dt>
					<dd class="col-sm-3">${inscricao.observacoes}</dd>

				</dl>
			</div>



			<div class="panel-heading">
				<h3 class="panel-title">Mora com</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Mora com:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.comQuemMora}</dd>
						<dt class=" col-sm-3">Com quem mora os outros:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.comQuemMoraOutros}</dd>
					</dl>
				</div>
			</div>

			<div class="panel-heading">
				<h3 class="panel-title">Nome dos Pais</h3>
			</div>
			<div class="panel-body">

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Mãe:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.nomeMae}</dd>
						<dt class=" col-sm-3">Pai:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.nomePai}</dd>
					</dl>
				</div>
			</div>

			<div class="panel-heading">
				<h3 class="panel-title">Endereço da residência atual</h3>
			</div>
			<div class="panel-body">

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Rua/Av:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.endereco}</dd>
						<dt class=" col-sm-3">Bairro:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.bairro}</dd>
					</dl>
				</div>

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Número:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.numero}</dd>
						<dt class=" col-sm-3">Cidade:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.cidade}</dd>
					</dl>
					<dl class="col-sm-12">
						<dt class="col-sm-3">Complemento:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.complemento}</dd>
						<dt class="col-sm-3">Cep:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.cep}</dd>
					</dl>
				</div>

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Referência:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.referencia}</dd>
						<dt class=" col-sm-3">Estado:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.estado}</dd>
					</dl>
					<dl class="col-sm-12">

					</dl>
				</div>

			</div>

			<div class="panel-heading">
				<h3 class="panel-title">Endereço da residência de origem</h3>
			</div>
			<div class="panel-body">

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Rua/Av:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.enderecoOrigem}</dd>
						<dt class=" col-sm-3">Bairro Origem:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.bairroOrigem}</dd>
					</dl>
				</div>

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Numero:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.numeroOrigem}</dd>
						<dt class=" col-sm-3">Cidade Origem:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.cidadeOrigem}</dd>
					</dl>
					<dl class="col-sm-12">
						<dt class="col-sm-3">Complemento:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.complementoOrigem}</dd>
						<dt class="col-sm-3">Cep:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.cepOrigem}</dd>
					</dl>
				</div>

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Ponto de referência:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.referenciaOrigem}</dd>
						<dt class=" col-sm-3">Estado Origem:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.estadoOrigem.nome}</dd>
					</dl>
				</div>
				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Situação Imovel:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.situacaoImovel.nome}</dd>
						<dt class=" col-sm-3">Telefone Origem:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.telefoneOrigem}</dd>
					</dl>
				</div>

			</div>

			<div class="panel-heading">
				<h3 class="panel-title">Propriedade Rural</h3>
			</div>
			<div class="panel-body">

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Grau de parentesco do proprietário:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.grauParentescoImovelRural.nome}</dd>
						<dt class="col-sm-3">Área Propriedade:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.areaPropriedadeRural}</dd>
					</dl>
				</div>

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class=" col-sm-3">Cidade/Estado Imóvel:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.cidadeEstadoImovelRural}</dd>
					</dl>
				</div>

			</div>

			<div class="panel-heading">
				<h3 class="panel-title">Bens Móveis (Veículos)</h3>
			</div>
			<div class="panel-body">

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Grau de parentesco do proprietário:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.grauParentescoVeiculos.nome}</dd>
						<dt class=" col-sm-3">Finalidade do veículo:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.veiculo}</dd>
					</dl>
				</div>

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class=" col-sm-3">Finalidade do veículo:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.finalidadeVeiculo}</dd>
					</dl>
				</div>
			</div>




			<div class="panel-heading">
				<h3 class="panel-title">Situação Socioeconômica (Grupo familiar
					incluido o aluno)</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Nome:</th>
								<th>Parentesco:</th>
								<th>Escolaridade:</th>
								<th>Atividade:</th>
								<th>Renda R$:</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pessoa"
								items="${inscricao.questionarioAuxilioMoradia.pessoas }">
								<tr>
									<td>${pessoa.nome }</td>
									<td>${pessoa.parentesco.nome }</td>
									<td>${pessoa.escolaridade }</td>
									<td>${pessoa.profissao}</td>
									<td>${pessoa.rendaMensal }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>




			<div class="panel-heading">
				<h3 class="panel-title">Ensino Fundamental</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Tipo de escola:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.ensinoFundamental.nome}</dd>
					</dl>
				</div>

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Possuia bolsa?</dt>
						<dd class="col-sm-3">
							<c:choose>
								<c:when
									test="${inscricao.questionarioAuxilioMoradia.bolsaEnsinoMedio == true}"> Sim</c:when>
								<c:otherwise>Não</c:otherwise>
							</c:choose>
						</dd>
						<dt class="col-sm-3">Percentual Particular Fundamental</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.percentualParticularFundamental}</dd>
					</dl>
				</div>

			</div>


			<div class="panel-heading">
				<h3 class="panel-title">Ensino Médio</h3>
			</div>
			<div class="panel-body">

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Tipo de escola</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.ensinoMedio.nome}</dd>
					</dl>
				</div>

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Possuia bolsa:</dt>
						<dd class="col-sm-3">
							<c:choose>
								<c:when
									test="${inscricao.questionarioAuxilioMoradia.bolsaEnsinoMedio == true}"> Sim</c:when>
								<c:otherwise>Não</c:otherwise>
							</c:choose>
						</dd>
						<dt class="col-sm-3">Percentual de bolsa:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.percentualParticularMedio}</dd>
					</dl>
				</div>

				<div class="form-group">
					<dl class="col-sm-12">
						<dt class="col-sm-3">Fez cursinho pré-vestibular:</dt>
						<dd class="col-sm-3">
							<c:choose>
								<c:when
									test="${inscricao.questionarioAuxilioMoradia.cursinho == true}"> Sim</c:when>
								<c:otherwise>Não</c:otherwise>
							</c:choose>
						</dd>
						<dt class="col-sm-3">Nome do Cursinho:</dt>
						<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.nomeCursinho}</dd>
					</dl>
				</div>

			</div>


			<div class="panel-heading">
				<h3 class="panel-title">Outras Informações</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<div class="form-group">
						<dl class="col-sm-12">
							<dt class="col-sm-3">Bolsista UFC:</dt>
							<dd class="col-sm-3">
								<c:choose>
									<c:when
										test="${inscricao.questionarioAuxilioMoradia.bolsistaUfc == true}"> Sim</c:when>
									<c:otherwise>Não</c:otherwise>
								</c:choose>
							</dd>
							<dt class="col-sm-3">Descrição Bolsa:</dt>
							<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.descricaoBolsa}</dd>
						</dl>
					</div>

					<div class="form-group">
						<dl class="col-sm-12">
							<dt class="col-sm-3">Possui Graduação:</dt>
							<dd class="col-sm-3">
								<c:choose>
									<c:when
										test="${inscricao.questionarioAuxilioMoradia.graduacao == true}"> Sim</c:when>
									<c:otherwise>Não</c:otherwise>
								</c:choose>
							</dd>
							<dt class="col-sm-3">Descrição da Graduação:</dt>
							<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.descricaoGraduacao}</dd>
						</dl>
					</div>
				</div>
			</div>

			<div class="panel-heading">
				<h3 class="panel-title">Justificativa</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<div class="form-group">
						<dl class="col-sm-12">
							<dt class="col-sm-3">Justificativa:</dt>
							<dd class="col-sm-3">${inscricao.questionarioAuxilioMoradia.justificativa}</dd>
						</dl>
					</div>
				</div>
			</div>

			<div class="panel-heading">
				<h3 class="panel-title">Entrevista</h3>
			</div>
			<div class="panel-body">

				<div class="form-group">
					<div class="form-group">
						<dl class="col-sm-12">
							<dt class="col-sm-3">Observação:</dt>
							<dd class="col-sm-3">${inscricao.entrevista.observacao}</dd>
							<dt class="col-sm-3">Deferimento:</dt>
							<c:choose>
								<c:when test="${inscricao.entrevista.deferimento == true}">
									<dd class="col-sm-3">DEFERIDO</dd>
								</c:when>
								<c:otherwise>
									<dd class="col-sm-3">INDEFERIDO</dd>
								</c:otherwise>
							</c:choose>
							<dd class="col-sm-3">${inscricao.entrevista.observacao}</dd>
						</dl>
					</div>
				</div>

			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>

</html>