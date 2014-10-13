<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<jsp:include page="../modulos/header-estrutura.jsp" />

<title>Editar Aluno</title>
</head>
<body>
	<jsp:include page="../modulos/header.jsp" />
	<div class="container">
		<div class="novo-servidor" align="left">
			<div class="form" align="center"></div>
			<c:if test="${action == 'editar'}">
				<h2>Editar Servidor</h2>
			</c:if>
			
			
		<form:form id="editar" commandName="servidor" cssClass="form-horizontal" method="POST">
				<input type="hidden" name="id" value="${servidor.id}" />
				<div class="form-group">
						<label for="siape" class="col-sm-2 control-label">Siape:</label>
						<div class="col-sm-10">
							<form:input id="siape" path="siape" cssClass="form-control" placeholder="SIAPE do servidor"  />
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
						<input name="submit" type="submit" class="btn btn-primary" value="Editar" />
						<a href="<c:url value="/servidor/listarServidor"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
					
		</form:form>		
	</div>
	
						
	</div>
	
	
	</body>
</html>
