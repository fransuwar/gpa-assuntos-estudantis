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
	<ol class="breadcrumb">
		<li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
		<li>Listar Inscritos</li>
	</ol>
	<div class="container">
		<sec:authorize access="hasAnyRole('ROLE_COORDENADOR', 'ROLE_ADMIN')">
			<div class="panel-heading" align="center">
				<h4>Alunos inscritos</h4>
			</div>
			<form:form id="emitirParecerForm" role="form" commandName="pareceres"
				servletRelativeAction="/selecao/parecer/${idSelecao}" method="POST"
				cssClass="form-horizontal">
				<table class="table" id="table">
					<thead>
						<tr>
							<th>Matricula</th>
							<th>Nome</th>
							<th>Curso</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="parecer" items="${pareceres.pareceres}"
							varStatus="i">
							<tr class="linha">
								<td>${parecer.alunoApto.matricula}</td>
								<td>${parecer.alunoApto.nome}</td>
								<td>${parecer.alunoApto.curso.nome}</td>

								<td><a class="btn btn-primary">Visualizar Formulário</a></td>
								<td><a class="btn btn-primary">Visualizar Entrevista</a></td>
								<td><a class="btn btn-primary">Visualizar Visita</a></td>
								<td><form:input id="peso" maxlength="3"
										path="pareceres[${i.index}].peso" data-mask="999"
										placeholder="Parecer" cssClass="form-control" /></td>
								<td>
									<div class="checkbox">
										<label> <form:checkbox name="alunoApto"
												path="pareceres[${i.index}].selecionado"
												value="Selecionar Aluno" /> Selecionar Aluno
										</label>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="col-md-2 col-md-offset-5">
					<input name="submit" type="submit" class="btn btn-success"
						value="Emitir Parecer" id="form-btn" />
				</div>
			</form:form>
		</sec:authorize>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>