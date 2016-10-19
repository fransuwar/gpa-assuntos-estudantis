<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>

<jsp:include page="../fragments/headTag.jsp" />
<title>Aluno</title>

</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<div style="text-align:center">
					<c:out value="${erro}"></c:out>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<div style="text-align:center">
					<c:out value="${info}"></c:out>
				</div>
			</div>
		</c:if>
		</div>

		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#meus-alunos" role="tab"
				data-toggle="tab">Alunos Cadastrados</a></li>
		</ul>


		<div class="tab-content">

			<!-- Meus Alunos -->
			<div class="tab-pane active" id="alunos-cadastrados">
				<c:if test="${empty alunos}">
					<div class="alert alert-warning" role="alert">Não há alunos
						cadastrados.</div>
				</c:if>
				<c:if test="${not empty alunos}">
					<div class="panel panel-default">
						<div class="panel-heading" align="center">
							<h4>Todos os Alunos</h4>
						</div>


						<!-- Table -->
						<table class="table">

							<tr>
								<th id="teste">Id</th>
								<th>Matricula</th>
								<th>Ira</th>
								<th>Curso</th>
								<th id="acoes">Ações</th>
							</tr>
							<tbody>
								<c:forEach var="aluno" items="${buscar}">
									<tr class="linha">
										<td>${aluno.id}</td>
										<td>${aluno.matricula}</td>
								</c:forEach>		
							</tbody>


						</table>
					</div>
				</c:if>
				
				<div align="right" style="margin-bottom: 20px;">
				<form:form id="buscarAlunoForm" role="form"	servletReltiveAction="/aluno/listarAluno" method="POST"
								cssClass="form-horizontal" class="inline">
								<input id="matricula" name="matricula" cssClass="form-control"
									placeholder="Digite sua busca aqui..." size="20"
									required="required" autofocus="true" />
								<button class="btn btn-primary" name="submit" type="submit"
									class="btn btn-primary" value="Buscar">
									Buscar <span class="glyphicon glyphicon-search" />
								</button>

				</form:form>
			
		</div>
			</div>
		</div>
</body>

</html>