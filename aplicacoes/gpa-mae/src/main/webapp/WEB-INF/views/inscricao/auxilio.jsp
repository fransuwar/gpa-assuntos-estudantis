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

<form:form id="questionarioAuxilioMoradia" role="form" commandName="auxilio" servletRelativeAction="/inscricao/auxilio" method="POST" cssClass="form-horizontal">
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
<label for="nomeMae" class="col-sm-2 control-label">Nome da Mãe:</label>
<div class="col-sm-2">
<form:input id="nomeMae" type="text" path="nomeMae" cssClass="form-control data" placeholder="Nome da Mãe"/>
<div class="error-validation">
<form:errors path="nomeMae"></form:errors>
</div>
</div>
</div>


<div class="form-group">
<label for="nomePai" class="col-sm-2 control-label">Nome do Pai:</label>
<div class="col-sm-10">
<form:input id="nomePai" path="nomePai" cssClass="form-control" placeholder="Nome do Pai"/>
<div class="error-validation">
<form:errors path="nomePai"></form:errors>
</div>
</div>
</div>


<div class="form-group">
<label for="rua" class="col-sm-2 control-label">Nome da rua:</label>
<div class="col-sm-10">
<form:input id="rua" path="rua" cssClass="form-control" placeholder="Nome da Rua"/>
<div class="error-validation">
<form:errors path="rua"></form:errors>
</div>
</div>
</div>


	
<div class="form-group">
<label for="numeroCasa" class="col-sm-2 control-label">Número da casa:</label>
<div class="col-sm-10">
<form:input id="numeroCasa" path="numeroCasa" cssClass="form-control" placeholder="Número da Casa"/>
<div class="error-validation">
<form:errors path="numeroCasa"></form:errors>
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