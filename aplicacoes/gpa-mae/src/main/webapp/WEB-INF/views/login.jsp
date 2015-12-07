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
</head>
<body>

	<jsp:include page="fragments/bodyHeader.jsp" />
<body onload='document.f.j_username.focus();'>
	<div class="center col-md-4">
		<div style="text-align: center" class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Login</h3>
			</div>
			<div class="panel-body">

				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>

				<form id="loginForm" class="form-horizontal" name='f'
					action="<c:url value='j_spring_security_check' />" method='POST'>
					<div class="form-group">
						<label class="col-md-3 control-label">Usuário:</label>
						<div class="col-md-9">
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-user"></span></span><input
									class="form-control" type='text' name='j_username' value=''>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Senha:</label>
						<div class="col-md-9">
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span> <input
									class="form-control" type='password' name='j_password' />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-5 col-lg-offset-2">
							<button type="submit" class="btn btn-primary">Login</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="fragments/footer.jsp" />
	<script src="<c:url value="/resources/js/gpa-login.js" />"></script>
</body>
</html>