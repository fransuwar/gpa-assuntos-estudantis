<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Alunos</title>
</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert"
				id="alert-erro">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<c:out value="${erro}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-success alert-dismissible" role="alert"
				id="alert-info">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<c:out value="${info}"></c:out>
			</div>
		</c:if>

		<div class="col-md-12">
			<div class="col-md-4" id="div-form-buscar">
				<form:form id="buscarAlunoForm" role="form"
					servletReltiveAction="servidor/listar/alunos" method="POST"
					cssClass="form-horizontal" class="inline">
					<div class="input-group">
						<input id="matricula" name="matricula" class="form-control"
							placeholder="Digite sua busca aqui..." size="20"
							required="required" autofocus="true" /> <span
							class="input-group-btn">
							<button class="btn btn-primary" name="submit" type="submit"
								class="btn btn-primary" value="Buscar">
								Buscar <span class="glyphicon glyphicon-search" />
							</button>
						</span>
					</div>
				</form:form>
			</div>

			<sec:authorize access="hasAnyRole('SERVIDOR')">
				<div class="col-md-2 col-md-offset-6" id="div-btn-inserir">
					<a href="<c:url value="/servidor/cadastrar/aluno" ></c:url>">
						<button class="btn btn-primary" id="listar-btn-inserir">
							Novo Aluno <span class="glyphicon glyphicon-plus"></span>
						</button>
					</a>
				</div>
			</sec:authorize>
		</div>
		
		<div class="col-md-12">

			<div class="panel panel-info">
				<div class="panel-heading" align="center">
					<h3 class="panel-title">Todos os Alunos</h3>
				</div>
				<c:if test="${empty alunos}">
					<div class="panel-body">
						<div class="alert alert-warning" role="alert">Não há alunos
							cadastrados.</div>
					</div>
				</c:if>
				<c:if test="${not empty alunos}">
					<table class="table display table-striped" id="tabela-alunos">
						<thead>
							<tr class="info">
								<th>Matricula</th>
								<th>Ira</th>
								<th>Curso</th>
								<sec:authorize access="hasAnyRole('SERVIDOR')">
									<th id="acoes">Ações</th>
								</sec:authorize>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty alunoEncontrado}"></c:when>
								<c:otherwise>
									<c:forEach var="aluno" items="${alunos}">
										<tr class="linha">
											<td>${aluno.matricula}</td>
											<td>${aluno.ira}</td>
											<td>${aluno.curso.nome}</td>

											<sec:authorize access="hasAnyRole('SERVIDOR')">
												<td><a id="editar"
													href="<c:url value="/servidor/editar/aluno/${aluno.id}" ></c:url>">
														<button class="btn btn-info">
															<span class="glyphicon glyphicon-pencil"></span>
														</button>
												</a> <a id="excluir" data-toggle="modal"
													data-target="#confirm-delete" href="#"
													data-href="<c:url value="/servidor/excluir/aluno/${aluno.id}" ></c:url>">
														<button class="btn btn-danger">
															<span class="glyphicon glyphicon-trash"></span>
														</button>
												</a></td>
											</sec:authorize>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>


	<jsp:include page="../fragments/footer.jsp" />

	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Excluir</div>
				<div class="modal-body">Tem certeza de que deseja excluir este
					aluno?</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-danger">Excluir</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
