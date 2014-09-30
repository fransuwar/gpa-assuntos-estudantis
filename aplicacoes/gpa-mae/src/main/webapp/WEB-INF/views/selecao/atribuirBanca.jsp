<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<jsp:include page="../modulos/header-estrutura.jsp" />
	<title>Atribuir Parecerista</title>
</head>

<body>
	<jsp:include page="../modulos/header.jsp" />

	<div class="container">
		<div class="atribuirBanca" align="center">
			<div class="form" align="center">
				<h2>Atribuir Parecerista</h2>
				

				<form:form id="adicionarBancaForm" role="form" commandName="atribuirBanca" servletRelativeAction="/selecao/atribuirBanca" method="POST" cssClass="form-horizontal">
						
					<div class="form-group">
						<label for="parecerista" class="col-sm-2 control-label">Parecerista:</label>
						<div class="col-sm-4">
							<select name="membrosBanca" class="form-control">
								<c:forEach items="${usuarios}" var="usuario">
									<option value="${usuario.id}">${usuario.nome}</option>
								</c:forEach>
							</select>
						</div>
					</div>
			
						<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Atribuir" />
						<a href="<c:url value="/selecao/editar"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
			</form:form>
			</div>

		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp"></jsp:include>

</body>

</html>