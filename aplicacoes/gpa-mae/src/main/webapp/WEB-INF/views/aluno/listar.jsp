<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
	<title>Alunos</title>
</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />

<ol class="breadcrumb">
      <li><a href="/MAE/aluno/listar">Listar Aluno</a></li>
    </ol>
	<div class="container">
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<c:out value="${erro}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<c:out value="${info}"></c:out>
			</div>
		</c:if>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
		<div align="right" style="margin-bottom: 20px;">
			<a href="<c:url value="/aluno/cadastrar" ></c:url>">
				<button class="btn btn-primary">Novo Aluno <span class="glyphicon glyphicon-plus"></span></button>
			</a>
		</div>
		</sec:authorize>
		
		<div align="right" style="margin-bottom: 20px;">
				<form:form id="buscarAlunoForm" role="form"
								servletReltiveAction="/aluno/listar" method="POST"
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
		
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#meus-alunos" role="tab" data-toggle="tab">Alunos Cadastrados</a></li>
		</ul>

		<div class="tab-content">

			<!-- Meus Alunos -->
			<div class="tab-pane active" id="alunos-cadastrados">
				<c:if test="${empty alunos}">
					<div class="alert alert-warning" role="alert">Não há alunos cadastrados.</div>
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
								<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
									<th id="acoes">Ações</th>
								</sec:authorize>
							</tr>
							<tbody>
								<c:forEach var="aluno" items="${alunos}">
									<tr class="linha">
										<td>${aluno.id}</td>
										<td>${aluno.matricula}</td>
										<td>${aluno.ira}</td>
										<td>${aluno.curso}</td>

									<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
									<td>
					
										<a id="editar"
											href="<c:url value="/aluno/${aluno.id}/editar" ></c:url>">
												<button class="btn btn-info">
													Editar <span class="glyphicon glyphicon-pencil"></span>
												</button>
										</a> <a id="excluir" data-toggle="modal"
											data-target="#confirm-delete" href="#"
											data-href="<c:url value="/aluno/${aluno.id}/excluir" ></c:url>">
												<button class="btn btn-danger">
													Excluir <span class="glyphicon glyphicon-trash"></span>
												</button>
										
										</a>
										</td>
										</sec:authorize>
								</c:forEach>
							</tbody>
							</table>

					</div>
					</c:if>
			</div>
			</div>
			</div>
			<jsp:include page="../fragments/footer.jsp" />
	
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                Excluir
	            </div>
	            <div class="modal-body">
	                Tem certeza de que deseja excluir este aluno?
	            </div>
	            <div class="modal-footer">
	                <a href="#" class="btn btn-danger">Excluir</a>
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	            </div>
	        </div>
	    </div>
	</div>		
</body>		
</html>
