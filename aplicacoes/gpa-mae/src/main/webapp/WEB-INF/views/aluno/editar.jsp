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
<jsp:include page="../fragments/bodyHeader.jsp" />

<title>Editar Aluno</title>
</head>

<body>
	<jsp:include page="../fragments/headTag.jsp" />
	<ol class="breadcrumb">
      <li><a href="/MAE/aluno/listar">Listar Aluno</a></li>
      <li class="active">Editar Aluno</li>
    </ol>
	<div class="container">
		<div class="novo-aluno" align="left">
			<div class="form" align="center"></div>
			<c:if test="${action == 'editar'}">
				<h2>Editar Aluno</h2>
			</c:if>

			<form:form id="editar" commandName="aluno" cssClass="form-horizontal" method="POST">
				<input type="hidden" name="id" value="${aluno.id}" />
				<div class="form-group">
						<label for="matricula" class="col-sm-2 control-label">Matricula:</label>
						<div class="col-sm-10">
							<form:input id="matricula" path="matricula" cssClass="form-control" placeholder="Matricula do aluno"  />
							<div class="error-validation">
								<form:errors path="matricula"></form:errors>
							</div>
						</div>
					</div>
					
				<div class="form-group">
						<label for="anoIngresso" class="col-sm-2 control-label">Ano Ingresso:</label>
						<div class="col-sm-2">
							<form:input id="anoIngresso" type="text" path="anoIngresso" cssClass="form-control data" placeholder="Ano de Ingresso" />
							<div class="error-validation">
								<form:errors path="anoIngresso"></form:errors>
							</div>
						</div>
				</div>
					
					<div class="form-group">
						<label for="ira" class="col-sm-2 control-label">IRA:</label>
						<div class="col-sm-10">
							<form:input id="ira" path="ira" cssClass="form-control" placeholder="Ira do aluno" />
							<div class="error-validation">
								<form:errors path="ira"></form:errors>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="curso" class="col-sm-2 control-label">Curso:</label>
						<div class="col-sm-10">
							<form:input id="curso" path="curso" cssClass="form-control" placeholder="Curso" />
							<div class="error-validation">
								<form:errors path="curso"></form:errors>
							</div>
						</div>
					</div>	
					
					<div class="form-group">
						<label for="banco" class="col-sm-2 control-label">Banco:</label>
						<div class="col-sm-10">
							<form:input id="banco" path="banco" cssClass="form-control" placeholder="Banco" />
							<div class="error-validation">
								<form:errors path="banco"></form:errors>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="agencia" class="col-sm-2 control-label">Agencia:</label>
						<div class="col-sm-10">
							<form:input id="agencia" path="agencia" cssClass="form-control" placeholder="Agência" />
							<div class="error-validation">
								<form:errors path="agencia"></form:errors>
							</div>
						</div>
					</div>
					
					
					<div class="form-group">
						<label for="conta" class="col-sm-2 control-label">Conta:</label>
						<div class="col-sm-10">
							<form:input id="conta" path="conta" cssClass="form-control" placeholder="Conta" />
							<div class="error-validation">
								<form:errors path="conta"></form:errors>
							</div>
						</div>
					</div>
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Editar" />
						<a href="<c:url value="/aluno/listar"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
					
			</form:form>		
						
			</div>
					
	
		</div>
	
	<jsp:include page="../fragments/footer.jsp"></jsp:include>
</body>
</html>