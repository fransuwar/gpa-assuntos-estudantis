<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="gpa" tagdir="/WEB-INF/tags"%>


<html>


<head>

	<jsp:include page="../fragments/headTag.jsp" />

		<title>Cadastro Auxilio Moradia</title>
</head>

<body>

		<jsp:include page="../fragments/bodyHeader.jsp" />
		
<div class="container">
<div class="novo-aluno" align="left">
<h2>Novo Cadastro</h2>

<form:form id="nada" role="form" commandName="auxilio" servletRelativeAction="/inscricao/auxilio" method="POST" cssClass="form-horizontal">
<div class="form-group">
<label for="enderecoSedeCurso" class="col-sm-2 control-label">rua da Sede do Curso:</label>
<div class="col-sm-10">
<form:input id="enderecoSedeCurso" path="enderecoSedeCurso" cssClass="form-control" placeholder="Rua da sede do curso"/>
<div class="error-validation">
<form:errors path="enderecoSedeCurso"></form:errors>
</div>
</div>
</div>


<div class="form-group">
<label for="anoIngresso" class="col-sm-2 control-label">Ano Ingresso:</label>
<div class="col-sm-2">
<form:input id="anoIngresso" type="text" path="anoIngresso" cssClass="form-control data" placeholder="Ano de Ingresso"/>
<div class="error-validation">
<form:errors path="anoIngresso"></form:errors>
</div>
</div>
</div>


<div class="form-group">
<label for="ira" class="col-sm-2 control-label">IRA:</label>
<div class="col-sm-10">
<form:input id="ira" path="ira" cssClass="form-control" placeholder="Ira do aluno"/>
<div class="error-validation">
<form:errors path="ira"></form:errors>
</div>
</div>
</div>


<div class="form-group">
<label for="curso" class="col-sm-2 control-label">Curso:</label>
<div class="col-sm-10">
<form:input id="curso" path="curso" cssClass="form-control" placeholder="Curso"/>
<div class="error-validation">
<form:errors path="curso"></form:errors>
</div>
</div>
</div>


	
<div class="form-group">
<label for="banco" class="col-sm-2 control-label">Banco:</label>
<div class="col-sm-10">
<form:input id="banco" path="banco" cssClass="form-control" placeholder="Banco"/>
<div class="error-validation">
<form:errors path="banco"></form:errors>
</div>
</div>
</div>


<div class="form-group">
<label for="agencia" class="col-sm-2 control-label">Agencia:</label>
<div class="col-sm-10">
<form:input id="agencia" path="agencia" cssClass="form-control" placeholder="Agência"/>
<div class="error-validation">
<form:errors path="agencia"></form:errors>
</div>
</div>
</div>


<div class="form-group">
<label for="conta" class="col-sm-2 control-label">Conta:</label>
<div class="col-sm-10">
<form:input id="conta" path="conta" cssClass="form-control" placeholder="Agência"/>
<div class="error-validation">
<form:errors path="conta"></form:errors>
</div>
</div>
</div>


</form:form>	
</div>
</div>

	<jsp:include page="../fragments/footer.jsp"></jsp:include>
</body>


</html>