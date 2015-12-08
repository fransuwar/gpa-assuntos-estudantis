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

<!DOCTYPE html>

<html>
<head>
<jsp:include page="fragments/headTag.jsp" />
<title>Login</title>
<link href="<c:url value="/resources/css/style-login.css"/>"
	rel="stylesheet">


<style type="text/css">
#loginForm .has-error .control-label, #loginForm .has-error .help-block,
	#loginForm .has-error .form-control-feedback {
	color: red;
}
</style>
</head>
<body>
<body onload='document.f.j_username.focus();'>
	<div id="container">
		<div id="header">
			<img alt="Sistema de Afastamento de Professores"
				src="<c:url value="/resources/images/logo-GPA.jpg" />">
		</div>
		<div class="formulario">
			<div class="login-text">
				<span>Faça seu login</span>
			</div>

			<form id="loginForm" name="loginForm"
				action="<c:url value='j_spring_security_check' />" method='POST'
				class="form-horizontal">
				<c:if test="${not empty erro}">
					<div class="login-error">${erro }</div>
				</c:if>

				<div class="form-group">
					<label class="col-md-2 control-label">Usuário:</label>
					<div class="col-md-9">
						<div id="inputLogin" class="input-group">
							<span class="input-group-addon"><i class="fa fa-user"></i></span>
							<input class="form-control" type='text' name='j_username'
								placeholder="cpf" required="required" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label">Senha:</label>
					<div class="col-md-9">
						<div id="inputSenha" class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input class="form-control" type='password' name='j_password'
								placeholder="senha" required="required" />
						</div>
					</div>
				</div>

				<div class="controls">
					<input id="btn-login" class="btn btn-primary" type="submit"
						value="Login" />
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="fragments/footer.jsp" />
	<script src="<c:url value="/resources/js/gpa-login.js" />"></script>
</body>
</html>