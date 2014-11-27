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
	<jsp:include page="../fragments/headTag.jsp" />
	<title>Cadastra servidor</title>

</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<ol class="breadcrumb">
      <li><a href="/MAE/servidor/listar">Listar Servidor</a></li>
      <li class="active">Cadastrar Servidor</li>
    </ol>
	
	 <div class="container">
		<div class="novo-servidor" align="left">
		<h2>Novo Servidor</h2>
				<form:form id="adicionarServidorForm" role="form" commandName="servidor" servletRelativeAction="/servidor/cadastrar" method="POST" cssClass="form-horizontal">
					
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
				<label for="Cargo" class="col-sm-2 control-label" >Cargo:</label>
					<div class="col-sm-3 control-label">
						<select name="cargo" id="cargoServidor">
							<c:forEach items="${cargos}" var="opcao">
								<option value="${opcao}">
									<c:out value="${opcao.cargo}"></c:out>
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
					
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Cadastrar" />
						<a href="<c:url value="/servidor/listar"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
					
				</form:form>
	</div>
	</div>
	

			<jsp:include page="../fragments/footer.jsp" />
</body>
</html>