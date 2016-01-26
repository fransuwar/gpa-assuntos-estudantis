<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="form-group">
	<label for="moradia" class="col-sm-2 control-label"
		id="form-label-right">Moradia:</label>
	<div class="col-sm-2">
		<form:input id="moradia" min="0" value="0" path="despesa.moradia"
			cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="despesa.moradia"></form:errors>
		</div>
	</div>
	
	<label for="energia" class="col-sm-2 control-label"
		id="form-label-right">Energia:</label>
	<div class="col-sm-2">
		<form:input id="energia" min="0" value="0" path="despesa.energia"
			cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="despesa.energia"></form:errors>
		</div>
	</div>
	
	<label for="telefone" class="col-sm-2 control-label"
		id="form-label-right">Telefone:</label>
	<div class="col-sm-2">
		<form:input id="telefone" min="0" value="0" path="despesa.telefone"
			cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="despesa.telefone"></form:errors>
		</div>
	</div>
</div>


<div class="form-group">
	<label for="educacao" class="col-sm-2 control-label"
		id="form-label-right">Educação:</label>
	<div class="col-sm-2">
		<form:input id="educacao" min="0" value="0" path="despesa.educacao"
			cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="despesa.educacao"></form:errors>
		</div>
	</div>
	
	<label for="saude" class="col-sm-2 control-label" id="form-label-right">Saúde:</label>
	<div class="col-sm-2">
		<form:input id="saude" min="0" value="0" path="despesa.saude"
			cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="despesa.saude"></form:errors>
		</div>
	</div>
	
	<label for="alimentacao" class="col-sm-2 control-label"
		id="form-label-right">Alimentação:</label>
	<div class="col-sm-2">
		<form:input id="alimentacao" min="0" value="0"
			path="despesa.alimentacao" cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="despesa.alimentacao"></form:errors>
		</div>
	</div>
</div>



<div class="form-group">
	<label for="outros" class="col-sm-2 control-label"
		id="form-label-right">Outros:</label>
	<div class="col-sm-2">
		<form:input id="outros" min="0" value="0" path="despesa.outro"
			cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="despesa.outro"></form:errors>
		</div>
	</div>
</div>