<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

									<div class="form-group">
										<label class="col-sm-4 control-label" for="qtdResidente">
											Nº de Pessoas Residentes no domicilio:</label>
										<div class="col-sm-2">
											<form:input id="qtdResidente" data-mask="999" min="0"
												value="0" path="qtdPessoasResidentes"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="qtdPessoasResidentes"></form:errors>
											</div>
										</div>

										<label for="rendaTrabalhoFormal"
											class="col-sm-4 control-label"> Trabalho Formal:</label>
										<%-- 										<div class="col-sm-2">
											<form:input id="rendaTrabalhoFormal" min="0" value="0"
												path="rendaTrabalhoFormal" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="rendaTrabalhoFormal"></form:errors>
											</div>
										</div> --%>

										<label for="rendaTrabalhoInformal"
											class="col-sm-4 control-label"> Trabalho Informal:</label>
										<%-- 										<div class="col-sm-2">
											<form:input id="rendaTrabalhoInformal" min="0" value="0"
												path="rendaTrabalhoInformal" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="rendaTrabalhoInformal"></form:errors>
											</div>
										</div> --%>


										<label for="rendaAposentadoria" class="col-sm-4 control-label">
											Aposentadoria:</label>
										<%-- 										<div class="col-sm-2">
											<form:input id="rendaAposentadoria" min="0" value="0"
												path="rendaAposentadoria" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="rendaAposentadoria"></form:errors>
											</div>
										</div> --%>

										<label for="rendaBeneficioSocial"
											class="col-sm-4 control-label"> Benefícios Sociais:</label>
										<%-- 										<div class="col-sm-2">
											<form:input id="rendaBeneficioSocial" min="0" value="0"
												path="rendaBeneficioSocial" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="rendaBeneficioSocial"></form:errors>
											</div>
										</div> --%>


										<label for="rendaAuxilioParente"
											class="col-sm-4 control-label">Auxílio de Parentes:</label>
										<%-- 										<div class="col-sm-2">
											<form:input id="rendaAuxilioParente" min="0" value="0"
												path="rendaAuxilioParente" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="rendaAuxilioParente"></form:errors>
											</div>
										</div> --%>

										<label for="rendaOutros" class="col-sm-4 control-label">Outros:</label>
										<%-- 										<div class="col-sm-2">
											<form:input id="rendaOutros" min="0" value="0"
												path="rendaOutros" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="rendaOutros"></form:errors>
											</div>
										</div> --%>
									</div>