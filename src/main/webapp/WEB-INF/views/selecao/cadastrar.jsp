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
	<title>Cadastro de selecaos</title>
</head>
<body>

	<jsp:include page="../modulos/header.jsp" />
	
	 <div class="container">
		<div class="novo-selecao" align="left">
			<div class="form" align="center">
				<h2>Nova Seleção</h2>
				<form:form id="adicionarSelecaoForm" role="form" commandName="selecao" servletRelativeAction="/selecao/cadastrar" method="POST" cssClass="form-horizontal">

					<div class="form-group">
						<label for="comentarios" class="col-sm-2 control-label">Comentarios:</label>
						<div class="col-sm-10">
							<form:textarea id="comentarios" path="comentarios" class="form-control" rows="5" placeholder="comentarios" ></form:textarea>
							<div class="error-validation">
								<form:errors path="comentarios"></form:errors>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="datadeInicio" class="col-sm-2 control-label">Data de Início:</label>
						<div class="col-sm-2">
							<form:input id="datadeInicio" type="text" path="datadeInicio" cssClass="form-control data" placeholder="Data de Início"/>
							<div class="error-validation">
								<form:errors path="datadeInicio"></form:errors>
							</div>
						</div>
						
						<label for="datadeTermino" class="col-sm-2 control-label">Data de Término:</label>
						<div class="col-sm-2">
							<form:input id="datadeTermino" type="text" path="datadeTermino" cssClass="form-control data" placeholder="Data de Término"/>
							<div class="error-validation">
								<form:errors path="datadeTermino"></form:errors>
							</div>
						</div>
						
						<label for="sequencial" class="col-sm-2 control-label">Número do Edital:</label>
						<div class="col-sm-2">
							<form:input id="sequencial" type="text" path="sequencial" cssClass="form-control" placeholder="000"/>
							<div class="error-validation">
								<form:errors path="sequencial"></form:errors>
							</div>
						</div>
						
						<label for="duracao" class="col-sm-2 control-label">Duração:</label>
						<div class="col-sm-2">
							<form:input id="duracao" type="text" path="duracao" cssClass="form-control" placeholder="0"/>
							<div class="error-validation">
								<form:errors path="duracao"></form:errors>
							</div>
						</div>
						<label for="ano" class="col-sm-2 control-label">Ano:</label>
						<div class="col-sm-2">
							<form:input id="ano" type="text" path="ano" cssClass="form-control	" placeholder="0"/>
							<div class="error-validation">
								<form:errors path="ano"></form:errors>
							</div>
						</div>
					
						
						<label for="quantidadeVagas" class="col-sm-2 control-label">Quantidade de Vagas:</label>
						<div class="col-sm-2">
							<form:input id="quantidadeVagas" type="number" min="0" placeholder="0" path="quantidadeVagas" cssClass="form-control"/>
						</div>
					</div>
					
				<div class="form-group"> 
					<label for="tipoDeBolsa" class="col-sm-2 control-label">Tipo de Bolsa:</label>	
					<form:select name="${status.expression}" path="tipoDeBolsa" id="tipoDeBolsa">
							<c:forEach items="${tipoDeBolsa}" var="opcao">
							<option value="${opcao}">
								<c:out value="${opcao.tipo}"></c:out>
							</option>
						</c:forEach>	
					</form:select>
					</div>
				
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Cadastrar" />
						<a href="<c:url value="/selecao/index"></c:url>" class="btn btn-default">Cancelar</a>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp" />

</body>


<script type="text/javascript">
	$(document)
			.ready(
					function($) {
						/* $('form').validate({
					        rules: {
					            nome: {
					                minlength: 2,
					                required: true
					            },
					            descricao: {
					                minlength: 5,
					                required: true
					            }
					        },
					        messages : {
								nome : {
									required : "Campo obrigatório",
									minlength : "O campo deve ter no mínimo 2 caracteres"
								},
								descricao : {
									required : "Campo obrigatório",
									minlength : "O campo deve ter no mínimo 5 caracteres"
								}
							},
					        highlight: function(element) {
					            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
					        },
					        unhighlight: function(element) {
					            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');;
					        },
					        errorElement: 'span',
					        errorClass: 'help-block',
					        errorPlacement: function(error, element) {
					            if(element.parent('.input-group').length) {
					                error.insertAfter(element.parent());
					            } else {
					                error.insertAfter(element);
					            }
					        }
					    }); */
						/* $('div.error-validation:has(span)').find('span').css('color', '#a94442');
						$('div.error-validation:has(span)').find('span').parent().parent().parent().addClass('has-error has-feedback'); */
						/* $.fn.datepicker.dates['pt-BR'] = {
							days : [ "Domingo", "Segunda", "Terça", "Quarta",
									"Quinta", "Sexta", "Sábado", "Domingo" ],
							daysShort : [ "Dom", "Seg", "Ter", "Qua", "Qui",
									"Sex", "Sáb", "Dom" ],
							daysMin : [ "Dom", "Seg", "Ter", "Qua", "Qui",
									"Sex", "Sab", "Dom" ],
							months : [ "Janeiro", "Fevereiro", "Março",
									"Abril", "Maio", "Junho", "Julho",
									"Agosto", "Setembro", "Outubro",
									"Novembro", "Dezembro" ],
							monthsShort : [ "Jan", "Fev", "Mar", "Abr", "Mai",
									"Jun", "Jul", "Ago", "Set", "Out", "Nov",
									"Dez" ],
							today : "Hoje"
						}; */

						/* $("input.data").datepicker({
							format : "dd/mm/yyyy",
							todayBtn : "linked",
							autoclose : true,
							language : "pt-BR",
							todayHighlight : true
						}); */
						//Método que valida o dia mês e ano dd/MM/yyyy
						/* jQuery.validator
								.addMethod(
										"dateBR",
										function(value, element) {
											if (value.length != 10)
												return (this.optional(element) || false);
											var data = value;
											var dia = data.substr(0, 2);
											var barra1 = data.substr(2, 1);
											var mes = data.substr(3, 2);
											var barra2 = data.substr(5, 1);
											var ano = data.substr(6, 4);

											if (data.length != 10
													|| barra1 != "/"
													|| barra2 != "/"
													|| isNaN(dia) || isNaN(mes)
													|| isNaN(ano) || dia > 31
													|| mes > 12)
												return (this.optional(element) || false);
											if ((mes == 4 || mes == 6
													|| mes == 9 || mes == 11)
													&& dia == 31)
												return (this.optional(element) || false);
											if (mes == 2
													&& (dia > 29 || (dia == 29 && ano % 4 != 0)))
												return (this.optional(element) || false);
											if (ano < 1900)
												return (this.optional(element) || false);
											return (this.optional(element) || true);
										}, "Informe uma data válida"); */

						//Validando numero de bolsas positivo
						/* $.validator.addMethod('positiveNumber',
								function(value) {
									return Number(value) >= 0;
								}, 'Entre com um numero positivo');

						//código para input só ser números
						var specialKeys = new Array();
						specialKeys.push(8);
						$("#quantidadeBolsa")
								.bind(
										"keypress",
										function(e) {
											var keyCode = e.which ? e.which
													: e.keyCode;
											var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys
													.indexOf(keyCode) != -1);
											return ret;
										});

						$("#quantidadeBolsa").bind("paste", function(e) {
							return false;
						});

						$("#quantidadeBolsa").bind("drop", function(e) {
							return false;
						}); */

						/* jQuery.validator.setDefaults({
							errorPlacement : function(error, element) {
								// if the input has a prepend or append element, put the validation msg after the parent div
								if (element.parent().hasClass('input-prepend')
										|| element.parent().hasClass(
												'input-append')) {
									error.insertAfter(element.parent());
									// else just place the validation message immediatly after the input
								} else {
									error.insertAfter(element);
								}
							},
							errorElement : "small", // contain the error msg in a small tag
							wrapper : "span", // wrap the error message and small tag in a div
							highlight : function(element) {
								$(element).closest('.control-group').addClass(
										'error'); // add the Bootstrap error class to the control group
							},

							success : function(element) {
								$(element).closest('.control-group')
										.removeClass('error'); // remove the Boostrap error class from the control group
							}
						}); */

						/* $(".registrationForm")
								.validate(
										{
											rules : {
												nome : {
													required : true,
													minlength : 2
												},
												descricao : {
													required : true,
													minlength : 5
												},
												inicio : {
													dateBR : {
														depends : function() {
															return $(
																	'#DatadeInicio')
																	.val().length > 0
																	&& $(
																			'#DatadeTermino')
																			.val().length > 0;
														}
													}
												},
												termino : {
													dateBR : {
														depends : function() {
															return $(
																	'#DatadeTermino')
																	.val().length > 0
																	&& $(
																			'#DatadeInicio')
																			.val().length > 0;
														}
													}
												},
												quantidadeBolsa : {
													positiveNumber : true
												}
											},

											messages : {
												nome : {
													required : "Campo obrigatório!",
													minlength : "Campo deve ter no mínimo 2 caracteres!"
												},
												descricao : {
													required : "Campo obrigatório!",
													minlength : "Campo deve ter no mínimo 5 caracteres!"
												},
												inicio : {
													dateBR : "Data Inválida"
												},
												termino : {
													dateBR : "Data Inválida"
												},
												positiveNumber : "Somente números positivos"
											},

											highlight : function(element) {
												$(element).closest(
														'.control-group')
														.addClass('has-error')
														.removeClass(
																'has-success');
											},
											unhighlight : function(element) {
												$(element)
														.closest(
																'.control-group')
														.removeClass(
																'has-error')
														.addClass('has-success');
											}
										});*/
					}(jQuery));
</script>

</html>