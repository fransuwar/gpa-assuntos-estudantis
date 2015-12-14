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

<c:if test="${action eq 'inscricao' }">
	<c:url var="url" value="/aluno/inscricao/auxilio-moradia"></c:url>
	<c:set var="titulo" value="Nova Inscrição"></c:set>
	<c:set var="botao" value="Finalizar Inscrição"></c:set>
</c:if>
<c:if test="${action eq 'editar' }">
	<c:url var="url" value="/aluno/inscricao/editar/auxilio-moradia"></c:url>
	<c:set var="titulo" value="Editar Inscrição"></c:set>
	<c:set var="botao" value="Atualizar Inscrição"></c:set>
</c:if>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Cadastro Auxilio Moradia</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Programa de Auxílio Moradia</h2>

				<ul class="nav nav-tabs">
					<li class="active"><a href="#moradia-tab" data-toggle="tab">Moradia<i
							class="fa"></i>
					</a></li>
					<li><a href="#historico-escolar-tab" data-toggle="tab">Histórico
							Escolar<i class="fa"></i>
					</a></li>
					<li><a href="#situacao-socio-economica-tab" data-toggle="tab">Situação
							Sócioeconômica<i class="fa"></i>
					</a></li>
					<li><a href="#outras-informacoes-tab" data-toggle="tab">Outras
							Informações<i class="fa"></i>
					</a></li>
					<li><a href="#justificativa-tab" data-toggle="tab">Justificativa<i
							class="fa"></i>
					</a></li>

				</ul>

				<input id="idAluno" name="idAluno" type="hidden"
					value="${sessionScope.id}" /> <input id="idSelecao"
					name="idSelecao" type="hidden" value="${idSelecao}" />
				<div class="tab-content">

					<div class="tab-pane active" id="moradia-tab">

						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3>Mora com</h3>
							</div>
							<div class="panel-body">
								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Mora com:</dt>
										<dd class="col-sm-3">${questAuxMor.comQuemMora}</dd>
										<dt class=" col-sm-3">Com quem mora os outros:</dt>
										<dd class="col-sm-3">${questAuxMor.comQuemMoraOutros}</dd>
									</dl>
								</div>
							</div>
							<div class="panel-heading">
								<h3>Nome dos pais</h3>
							</div>
							<div class="panel-body">

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Mãe:</dt>
										<dd class="col-sm-3">${questAuxMor.nomeMae}</dd>
										<dt class=" col-sm-3">Pai:</dt>
										<dd class="col-sm-3">${questAuxMor.nomePai}</dd>
									</dl>
								</div>
							</div>

							<div class="panel-heading">
								<h3>Endereço da residência atual</h3>
							</div>
							<div class="panel-body">

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Rua/Av:</dt>
										<dd class="col-sm-3">${questAuxMor.endereco}</dd>
										<dt class=" col-sm-3">Bairro:</dt>
										<dd class="col-sm-3">${questAuxMor.bairro}</dd>
									</dl>
								</div>

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Número:</dt>
										<dd class="col-sm-3">${questAuxMor.numero}</dd>
										<dt class=" col-sm-3">Cidade:</dt>
										<dd class="col-sm-3">${questAuxMor.cidade}</dd>
									</dl>
									<dl class="col-sm-12">
										<dt class="col-sm-3">Complemento:</dt>
										<dd class="col-sm-3">${questAuxMor.complemento}</dd>
										<dt class="col-sm-3">Cep:</dt>
										<dd class="col-sm-3">${questAuxMor.cep}</dd>
									</dl>
								</div>

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Referência:</dt>
										<dd class="col-sm-3">${questAuxMor.referencia}</dd>
										<dt class=" col-sm-3">Estado:</dt>
										<dd class="col-sm-3">${questAuxMor.estado}</dd>
									</dl>
									<dl class="col-sm-12">

									</dl>
								</div>
								<!--  -->

								<!--  -->
							</div>

							<div class="panel-heading">
								<h3>Endereço da residência de origem</h3>
							</div>
							<div class="panel-body">

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Rua/Av:</dt>
										<dd class="col-sm-3">${questAuxMor.enderecoOrigem}</dd>
										<dt class=" col-sm-3">Bairro Origem:</dt>
										<dd class="col-sm-3">${questAuxMor.bairroOrigem}</dd>
									</dl>
								</div>

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Numero:</dt>
										<dd class="col-sm-3">${questAuxMor.numeroOrigem}</dd>
										<dt class=" col-sm-3">Cidade Origem:</dt>
										<dd class="col-sm-3">${questAuxMor.cidadeOrigem}</dd>
									</dl>
									<dl class="col-sm-12">
										<dt class="col-sm-3">Complemento:</dt>
										<dd class="col-sm-3">${questAuxMor.complementoOrigem}</dd>
										<dt class="col-sm-3">Cep:</dt>
										<dd class="col-sm-3">${questAuxMor.cepOrigem}</dd>
									</dl>
								</div>

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Ponto de referência:</dt>
										<dd class="col-sm-3">${questAuxMor.referenciaOrigem}</dd>
										<dt class=" col-sm-3">Estado Origem:</dt>
										<dd class="col-sm-3">${questAuxMor.estadoOrigem}</dd>
									</dl>
								</div>
								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Situação Imovel:</dt>
										<dd class="col-sm-3">${questAuxMor.situacaoImovel}</dd>
										<dt class=" col-sm-3">Telefone Origem:</dt>
										<dd class="col-sm-3">${questAuxMor.telefoneOrigem}</dd>
									</dl>
								</div>

							</div>

							<div class="panel-heading">
								<h3>Propriedade Rural</h3>
							</div>
							<div class="panel-body">

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Grau de parentesco do proprietário:</dt>
										<dd class="col-sm-3">${questAuxMor.grauParentescoImovelRural}</dd>
										<dt class="col-sm-3">Área Propriedade:</dt>
										<dd class="col-sm-3">${questAuxMor.areaPropriedadeRural}</dd>
									</dl>
								</div>

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class=" col-sm-3">Cidade/Estado Imóvel:</dt>
										<dd class="col-sm-3">${questAuxMor.cidadeEstadoImovelRural}</dd>
									</dl>
								</div>

							</div>

							<div class="panel-heading">
								<h3>Bens Móveis (Veículos)</h3>
							</div>
							<div class="panel-body">

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Grau de parentesco do proprietário:</dt>
										<dd class="col-sm-3">${questAuxMor.grauParentescoVeiculos}</dd>
										<dt class=" col-sm-3">Finalidade do veículo:</dt>
										<dd class="col-sm-3">${questAuxMor.veiculo}</dd>
									</dl>
								</div>

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class=" col-sm-3">Finalidade do veículo:</dt>
										<dd class="col-sm-3">${questAuxMor.finalidadeVeiculo}</dd>
									</dl>
								</div>
							</div>
						</div>
					</div>

					<div class="tab-pane" id="historico-escolar-tab">

						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3>Ensino Fundamental</h3>
							</div>
							<div class="panel-body">
								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Tipo de escola:</dt>
										<dd class="col-sm-3">${questAuxMor.ensinoFundamental}</dd>
									</dl>
								</div>

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Possuia bolsa?</dt>
										<dd class="col-sm-3">
											<c:choose>
												<c:when test="${questAuxMor.bolsaEnsinoMedio == true}"> Sim</c:when>
												<c:otherwise>Não</c:otherwise>
											</c:choose>
										</dd>
										<dt class="col-sm-3">Percentual Particular Fundamental</dt>
										<dd class="col-sm-3">${questAuxMor.percentualParticularFundamental}</dd>
									</dl>
								</div>

							</div>

							<div class="panel-heading">
								<h3>Ensino Médio</h3>
							</div>
							<div class="panel-body">

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Tipo de escola</dt>
										<dd class="col-sm-3">${questAuxMor.ensinoMedio}</dd>
									</dl>
								</div>

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Possuia bolsa:</dt>
										<dd class="col-sm-3">
											<c:choose>
												<c:when test="${questAuxMor.bolsaEnsinoMedio == true}"> Sim</c:when>
												<c:otherwise>Não</c:otherwise>
											</c:choose>
										</dd>
										<dt class="col-sm-3">Percentual de bolsa:</dt>
										<dd class="col-sm-3">${questAuxMor.percentualParticularMedio}</dd>
									</dl>
								</div>

								<div class="form-group">
									<dl class="col-sm-12">
										<dt class="col-sm-3">Fez cursinho pré-vestibular:</dt>
										<dd class="col-sm-3">
											<c:choose>
												<c:when test="${questAuxMor.cursinho == true}"> Sim</c:when>
												<c:otherwise>Não</c:otherwise>
											</c:choose>
										</dd>
										<dt class="col-sm-3">Nome do Cursinho:</dt>
										<dd class="col-sm-3">${questAuxMor.nomeCursinho}</dd>
									</dl>
								</div>

							</div>
						</div>

					</div>

					<div class="tab-pane" id="situacao-socio-economica-tab">

						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3>Situação Socioeconômica (Grupo Familiar incluido o
									aluno)</h3>
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
											<c:forEach var="pessoa" items="${questAuxMor.pessoas }">
												<tr>
													<td>${pessoa.nome }</td>
													<td>${pessoa.parentesco }</td>
													<td>${pessoa.escolaridade }</td>
													<td>${pessoa.profissao}</td>
													<td>${pessoa.rendaMensal }</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>

					<div class="tab-pane" id="outras-informacoes-tab">

						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3>Outras Informações</h3>
							</div>
							<div class="panel-body">
								<div class="form-group">
									<div class="form-group">
										<dl class="col-sm-12">
											<dt class="col-sm-3">Bolsista UFC:</dt>
											<dd class="col-sm-3">
												<c:choose>
													<c:when test="${questAuxMor.bolsistaUfc == true}"> Sim</c:when>
													<c:otherwise>Não</c:otherwise>
												</c:choose>
											</dd>
											<dt class="col-sm-3">Descrição Bolsa:</dt>
											<dd class="col-sm-3">${questAuxMor.descricaoBolsa}</dd>
										</dl>
									</div>

									<div class="form-group">
										<dl class="col-sm-12">
											<dt class="col-sm-3">Possui Graduação:</dt>
											<dd class="col-sm-3">
												<c:choose>
													<c:when test="${questAuxMor.graduacao == true}"> Sim</c:when>
													<c:otherwise>Não</c:otherwise>
												</c:choose>
											</dd>
											<dt class="col-sm-3">Descrição da Graduação:</dt>
											<dd class="col-sm-3">${questAuxMor.descricaoGraduacao}</dd>
										</dl>
									</div>
								</div>
							</div>
						</div>

					</div>

					<div class="tab-pane" id="justificativa-tab">

						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3>Justificativa</h3>
							</div>
							<div class="panel-body">
								<div class="form-group">
									<div class="form-group">
										<dl class="col-sm-12">
											<dt class="col-sm-3">Justificativa:</dt>
											<dd class="col-sm-3">${questAuxMor.justificativa}</dd>
										</dl>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

			</div>

		</div>
		<a id="detalhes" href="<c:url value="/selecao/listar" ></c:url>">
			<button class=" btn btn-info btn-sm">Voltar</button>
		</a>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>

</html>