<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/bodyHeader.jsp" />
<title>Alunos inscritos</title>
</head>
<body>
	<jsp:include page="../fragments/headTag.jsp" />

	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Inscrições</h3>
			</div>
<<<<<<< HEAD

<%-- 			<form:form id="emitirParecerForm" role="form" commandName="pareceres" --%>
<%-- 				servletRelativeAction="/selecao/parecer/${idSelecao}" method="POST" --%>
<%-- 				cssClass="form-horizontal"> --%>

				<table class="table table-striped" id="tabela-inscritos">
=======
			<div class="panel-body">
				<table class="table" id="tabela-selecao-inscritos">
>>>>>>> refs/remotes/origin/master
					<thead>
						<th>Edital</th>
						<th>Tipo de Bolsa</th>
						<th>Período de Inscrições</th>
						<th>Vagas</th>
						<th>Coordenador Responsável</th>
					</thead>
					<tbody>
					<tr>
						<td>${selecao.ano }.${selecao.sequencial }</td>
						<td>${selecao.tipoBolsa.nome }</td>
						<td><fmt:formatDate value="${selecao.dataInicio }"
								pattern="dd/MM/yyyy" /> até <fmt:formatDate
								value="${selecao.dataTermino }" pattern="dd/MM/yyyy" /></td>
						<td>${selecao.quantidadeVagas }</td>
						<td>${selecao.responsavel.pessoa.nome }</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<h3 class="panel-title">Alunos Inscritos</h3>
			<table class="table" id="tabela-inscritos">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Data</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="inscritoSelecao" items="${selecao.inscritos }">
						<tr>
							<td>${inscritoSelecao.aluno.pessoa.nome }</td>
							<td><fmt:formatDate value="${inscritoSelecao.data}"
									pattern="dd/MM/yyyy" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>

		<jsp:include page="../fragments/footer.jsp" />
</body>
</html>