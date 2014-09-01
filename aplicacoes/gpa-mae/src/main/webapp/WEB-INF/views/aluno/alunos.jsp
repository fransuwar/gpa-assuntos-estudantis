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
	<title>Cadastra alunos</title>
</head>

<body>
	<jsp:include page="../modulos/header.jsp" />

		 <div class="container">
		<div class="novo-aluno" align="left">
		<h2>Novo Aluno</h2>
				<form:form id="adicionarAlunoForm" role="form" commandName="aluno" servletRelativeAction="/projeto/cadastrar" method="POST" cssClass="form-horizontal">

					<div class="form-group">
						<label for="matricula" class="col-sm-2 control-label">Matricula:</label>
						<div class="col-sm-10">
							<form:input id="matricula" path="matricula" cssClass="form-control" placeholder="Matricula do aluno"/>
							<div class="error-validation">
								<form:errors path="matricula"></form:errors>
							</div>
						</div>
					</div>
					</form:form>
	
		</div>
	</div>
	
	<jsp:include page="../modulos/footer.jsp"></jsp:include>

</body>

</html>