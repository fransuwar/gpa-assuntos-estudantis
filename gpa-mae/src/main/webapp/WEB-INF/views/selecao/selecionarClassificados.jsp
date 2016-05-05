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
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../fragments/headTag.jsp" />
<title>Selecionar Classificados</title>
</head>
<body id="pagina-selecionar-classificados">
	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container" align="left">
		<div class="panel panel-primary" align="left">
			<sec:authorize
				access="hasAnyRole('COORDENADOR_ASSUNTOS_ESTUDANTIS','STA')">
				<div class="panel-heading">
					<h3 class="panel-title">Selecionar Classificados</h3>
				</div>
				<div class="panel-body">
					<input type="hidden" value="${selecao.id}"
						id="id-selecao-selecionar-classificados">
					<div class="row">
						<c:if test="${mostrarErro}">
							<div
								class="alert alert-warning alert-dismissible error-validation"
								role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								${erro}
							</div>
						</c:if>
						<div
							class="alert alert-warning alert-dismissible error-validation"
							role="alert" id="erro-checkbox">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							Você deve escolher pelo menos um aluno
						</div>
						<div class="col-sm-5">
							<form id="formClassificaveis" method="POST"
								action="<c:url value="/selecao/selecionarClassificados/${selecao.id}"/>">
								<h4 class="text-warning">Classificáveis</h4>
								<input type="text" class="form-control" placeholder="Pesquisar"
									id="pesquisarClassificaveis">
								<table class="table table-hover borda-laranja"
									id="tabela-classificaveis">
									<thead>
										<tr class="warning row">
											<td class="col-sm-2">Aluno</td>
											<td class="col-sm-4">Nome</td>
											<td class="col-sm-2">Renda</td>
											<td class="col-sm-4"></td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="deferido" items="${deferidos}">
											<c:if test="${!deferido.classificado}">
												<tr class="row">
													<td class="col-sm-2"><a id="detalhes"
														href="<c:url value="/servidor/detalhes/inscricao/${deferido.id}">  
										</c:url>"
														class="thumbnail"> <img
															src="<c:url value="/resources/images/gpa-icone.jpg"/>"
															alt="Foto do Aluno" style="height: 60px; width: 100%"
															class="img-rounded">
													</a></td>
													<td class="col-sm-4"><a id="detalhes"
														href="<c:url value="/servidor/detalhes/inscricao/${deferido.id}">  
										</c:url>">${deferido.aluno.pessoa.nome}</a></td>
													<td class="col-sm-2">R$ 900</td>
													<td class="col-sm-4">
														<div class="checkbox">
															<label> <input type="checkbox"
																name="checkClassificados[]" value="${deferido.id}"
																required>
															</label>
														</div>
													</td>
												</tr>
											</c:if>

										</c:forEach>
									</tbody>
								</table>
							</form>
						</div>

						<div class="col-sm-2" id="div-botoes-classificados">
						    <h4 class="col-sm-12">${selecao.quantidadeVagas} vagas</h4>
							<button class=" btn btn-primary btn-xs col-sm-12"
								id="botao-adicionar-classificados">
								Adicionar <span class="glyphicon glyphicon-chevron-right"></span>
							</button>
							<button class=" btn btn-primary btn-xs col-sm-12"
								id="botao-remover-classificados">
								<span class="glyphicon glyphicon-chevron-left"></span> Remover
							</button>
							<button class=" btn btn-primary btn-xs col-sm-12"
								id="botao-finalizar-ranking">
								Finalizar
							</button>
						</div>

						<div class="col-sm-5">
							<form id="formClassificados" method="POST"
								action="<c:url value="/selecao/removerClassificados/${selecao.id}"/>">
								<h4 class="text-success">
									Classificados <span class="badge">${qtdClassificados}</span>
								</h4>
								<input type="text" class="form-control" placeholder="Pesquisar"
									id="pesquisarClassificados">
								<table class="table table-hover borda-verde"
									id="tabela-classificados">
									<thead>
										<tr class="success row">
											<td class="col-sm-2">Aluno</td>
											<td class="col-sm-4">Nome</td>
											<td class="col-sm-2">Renda</td>
											<td class="col-sm-4"></td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="deferido" items="${deferidos}">
											<c:if test="${deferido.classificado}">
												<tr class="row">
													<td class="col-sm-2"><a id="detalhes"
														href="<c:url value="/servidor/detalhes/inscricao/${deferido.id}">  
										</c:url>"
														class="thumbnail"> <img
															src="<c:url value="/resources/images/gpa-icone.jpg"/>"
															alt="Foto do Aluno" style="height: 60px; width: 100%"
															class="img-rounded">
													</a></td>
													<td class="col-sm-4"><a id="detalhes"
														href="<c:url value="/servidor/detalhes/inscricao/${deferido.id}">  
										</c:url>">${deferido.aluno.pessoa.nome}</a></td>
													<td class="col-sm-2">R$ 900</td>
													<td class="col-sm-4">
														<div class="checkbox">
															<label> <input type="checkbox"
																value="${deferido.id}" name="checkClassificaveis[]">
															</label>
														</div>
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
			</sec:authorize>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>