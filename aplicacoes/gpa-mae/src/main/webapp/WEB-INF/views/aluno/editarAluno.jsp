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
		<div class="novo-aluno" align="left">
			<div class="form" align="center"></div>
			<c:if test="${action == 'editar'}">
				<h2>Editar Aluno</h2>
			</c:if>

			<form:form id="editar" commandName="aluno"
				enctype="multipart/form-data" cssClass="form-horizontal"
				method="POST">
				<input type="hidden" name="id" value="${aluno.id}" />
				<div class="form-group">
					<label for="matricula" class="col-sm-2 control-label">Matricula:</label>
					<div class="col-sm-10">
						<form:input id="matricula" path="matricula"
							cssClass="form-control" placeholder="Matricula do aluno" />
						<div class="error-validation">
							<form:errors path="matricula"></form:errors>
						</div>
					</div>
				</div>
				
		</form:form>

		</div>
	</div>
	<jsp:include page="../modulos/footer.jsp" />

</body>
</html>