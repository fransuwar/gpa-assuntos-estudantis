<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="fragments/headTag.jsp" />
	<title>Login</title>
	<link href="<c:url value="/css/style-login.css"/>" rel="stylesheet">
</head>
<body>
	<div id="container">
		<div id="header">
			<img alt="Sistema de Seleção do Auxílio Moradia" src="<c:url value="/images/logo-GPA.jpg" />">
		</div>
		<div class="formulario">
			<div class="login-text">
				<span>Faça seu login</span>
			</div>

			<form id="form-login" name="form-login" action="<c:url value='/login' />" method="POST">
				<c:if test="${not empty erro}">
					<div class="login-error">${erro }</div>
				</c:if>

				<div class="form-group label-floating">
					<label for="cpf" class="control-label">CPF:</label>
					<input id="cpf" class="form-control" type="text" name="username" required="required" />
				</div>

				<div class="form-group label-floating">
					<label for="senha" class="control-label">Senha:</label>
					<input id="senha" class="form-control" type="password" name="password" required="required" />
				</div>

				<div id="btn-login" class="form-group">
					<button type="submit" class="btn btn-primary btn-raised">Login</button>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="fragments/footer.jsp" />
</body>
</html>