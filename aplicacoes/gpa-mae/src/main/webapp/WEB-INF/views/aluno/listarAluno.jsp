<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <jsp:include page="../modulos/header-estrutura.jsp" />
<title>Projetos</title>
</head>
<body>
</body>
	<jsp:include page="../modulos/header.jsp" />
	
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
		<div align="right" style="margin-bottom: 20px;">
			<a href="<c:url value="/aluno/alunos" ></c:url>">
				<button class="btn btn-primary">Novo Aluno <span class="glyphicon glyphicon-plus"></span></button>
			</a>
		</div>
		
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#meus-alunos" role="tab" data-toggle="tab">Alunos</a></li>
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
						<table class="table" id="table">
							<thead>
								<tr>
									<th id="teste">Matricula</th>
									<th>Ira</th>
									<th>Curso</th>
									<th id="acoes">Ações</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="aluno" items="${alunos}">
									<tr class="linha">
										<td>${aluno.matricula}</td>
										<td><a href="<c:url value="/aluno/${aluno.matricula}" ></c:url>">${aluno.matricula}</a></td>
										
										</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
			</div>
			</div>
		
		
</html>