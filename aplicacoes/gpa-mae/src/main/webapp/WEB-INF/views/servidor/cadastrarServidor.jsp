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
	<title>Cadastra servidor</title>

</head>
<body>
	<jsp:include page="../modulos/header.jsp" />
	
	
	 <div class="container">
		<div class="novo-servidor" align="left">
		<h2>Novo Servidor</h2>
				<form:form id="adicionarServidorForm" role="form" commandName="servidor" servletRelativeAction="/servidor/cadastrarServidor" method="POST" cssClass="form-horizontal">
					
					<div class="form-group">
						<label for="SIAPE" class="col-sm-2 control-label" >SIAPE:</label>
						<div class="col-sm-10">
							<form:input id="siape" maxlength="7" path="siape" cssClass="form-control" placeholder="SIAPE do servidor" />
							<div class="error-validation">
								<form:errors path="siape"></form:errors>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="cargo" class="col-sm-2 control-label" >Cargo:</label>
						<div class="col-sm-10">
							<form:input id="cargo" path="cargo" cssClass="form-control" placeholder="Cargo do servidor" />
							<div class="error-validation">
								<form:errors path="cargo"></form:errors>
							</div>
						</div>
					</div>
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Cadastrar" />
						<a href="<c:url value="/servidor/listarServidor"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
					
				</form:form>
	</div>
	</div>
	

<jsp:include page="../modulos/footer.jsp"></jsp:include>
</body>
</html>