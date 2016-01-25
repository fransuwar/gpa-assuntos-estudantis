<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form-group">
	<label class="col-sm-4 control-label" for="qtdResidente"> Nº de
		Pessoas Residentes no domicilio:</label>
	<div class="col-sm-2">
		<form:input id="qtdResidente" data-mask="999" min="0" value="0"
			path="qtdPessoasResidentes" cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="qtdPessoasResidentes"></form:errors>
		</div>
	</div>
	<label for="rendaTrabalhoFormal" class="col-sm-4 control-label">
		Trabalho Formal:</label>
	<div class="col-sm-2">
		<form:input id="rendaTrabalhoFormal" min="0" value="0"
			path="receita.trabalhoFormal" cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="receita.trabalhoFormal"></form:errors>
		</div>
	</div>

</div>
<div class="form-group">

	<label for="rendaTrabalhoInformal" class="col-sm-4 control-label">Trabalho
		Informal:</label>
	<div class="col-sm-2">
		<form:input id="rendaTrabalhoInformal" min="0" value="0"
			path="receita.trabalhoInformal" cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="receita.trabalhoInformal"></form:errors>
		</div>
	</div>
	<label for="rendaAposentadoria" class="col-sm-4 control-label">
		Aposentadoria:</label>
	<div class="col-sm-2">
		<form:input id="rendaAposentadoria" min="0" value="0"
			path="receita.aposentadoria" cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="receita.aposentadoria"></form:errors>
		</div>
	</div>
</div>
<div class="form-group">
	<label for="rendaBeneficioSocial" class="col-sm-4 control-label">
		Benefícios Sociais:</label>
	<div class="col-sm-2">
		<form:input id="rendaBeneficioSocial" min="0" value="0"
			path="receita.beneficioSocial" cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="receita.beneficioSocial"></form:errors>
		</div>
	</div>
	<label for="rendaAuxilioParente" class="col-sm-4 control-label">Auxílio	de Parentes:</label>
	<div class="col-sm-2">
		<form:input id="receitaAuxilioParente" min="0" value="0"
			path="receita.auxilioParentes" cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="receita.auxilioParentes"></form:errors>
		</div>
	</div>
</div>
<div class="form-group">

	<label for="rendaOutros" class="col-sm-4 control-label">Outros:</label>
	<div class="col-sm-2">
		<form:input id="receita.Outro" min="0" value="0" path="receita.outro"
			cssClass="form-control" />
		<div class="error-validation">
			<form:errors path="receita.outro"></form:errors>
		</div>
	</div>
</div>





