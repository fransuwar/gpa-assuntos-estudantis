<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${action eq 'inscricao' }">
	<c:url var="url" value="/aluno/inscricao/iniciacao-academica"></c:url>
	<c:set var="titulo" value="Nova Inscrição"></c:set>
	<c:set var="botao" value="Finalizar Inscrição"></c:set>
</c:if>

<c:if test="${action eq 'editar' }">
	<c:url var="url" value="/aluno/inscricao/editar/iniciacao-academica"></c:url>
	<c:set var="titulo" value="Editar Inscrição"></c:set>
	<c:set var="botao" value="Atualizar Inscrição"></c:set>
</c:if>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Cadastro de Seleção de Bolsas</title>
</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Programa de Iniciação Acadêmica</h2>

				<ul class="nav nav-tabs">
					<li class="active"><a href="#moradia-tab" data-toggle="tab">Moradia<i
							class="fa"></i>
					</a></li>
					<li><a href="#situacao-socio-economica-tab" data-toggle="tab">Situação
							Sócio Econômica<i class="fa"></i>
					</a></li>
					<li><a href="#informacao-grupo-familiar-tab" data-toggle="tab">Informações
							do seu Grupo Familiar<i class="fa"></i>
					</a></li>
					<li><a href="#informacao-adicional-tab" data-toggle="tab">Informações
							Adicionais<i class="fa"></i>
					</a></li>
				</ul>

				<form:form id="questionarioIniciacao" role="form"
				    onsubmit="return validaHorariosDisponiveisBolsa();"
					commandName="questionarioIniciacaoAcademica"
					modelAttribute="questionarioIniciacaoAcademica"
					servletRelativeAction="${url }"
					method="POST" cssClass="form-horizontal">
					
					<input type="hidden" id="idSelecao" name="idSelecao" value="${idSelecao }"/>
					
					<div class="tab-content">
						<div class="tab-pane active" id="moradia-tab">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3>Endereço de Residência de Origem</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="enderecoFamilia" class="col-sm-1 control-label"><span
											class="red">*</span>Rua/Av:</label>
										<div class="col-sm-5">
											<form:input id="enderecoFamilia" type="text"
												path="enderecoFamilia" cssClass="form-control"
												placeholder="Rua /Av" />
											<div class="error-validation" id="erro-enderecoFamilia">
												<form:errors path="enderecoFamilia">
												</form:errors>
											</div>
										</div>
										<label for="bairroFamilia" class="col-sm-2 control-label"><span
											class="red">*</span>Bairro:</label>
										<div class="col-sm-4">
											<form:input id="bairroFamilia" path="bairroFamilia"
												cssClass="form-control" placeholder="Bairro" />
											<div class="error-validation" id="erro-bairroFamilia">
												<form:errors path="bairroFamilia"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="numeroFamilia" class="col-sm-1 control-label"><span
											class="red">*</span>Número:</label>
										<div class="col-sm-1">
											<form:input id="numeroFamilia" path="numeroFamilia"
												cssClass="form-control" placeholder="Num" data-mask="9999" />
											<div class="error-validation" id="erro-numeroFamilia">
												<form:errors path="numeroFamilia"></form:errors>
											</div>
										</div>
										<label for="cidade" class="col-sm-1 control-label"><span
											class="red">*</span>Cidade:</label>
										<div class="col-sm-3">
											<form:input id="cidadeFamilia" type="text"
												path="cidadeFamilia" cssClass="form-control"
												placeholder="Cidade" />
											<div class="error-validation" id="erro-cidade">
												<form:errors path="cidade"></form:errors>
											</div>
										</div>
										<label for="complementoFamilia" class="col-sm-2 control-label">Complemento:</label>
										<div class="col-sm-4">
											<form:input id="complementoFamilia" type="text"
												path="complementoFamilia" cssClass="form-control"
												placeholder="Complemento" />
											<div class="error-validation" id="erro-complementoFamilia">
												<form:errors path="complementoFamilia"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="cepFamilia" class="col-sm-1 control-label"><span
											class="red">*</span>CEP:</label>
										<div class="col-sm-2">
											<form:input id="cepFamilia" path="cepFamilia" data-mask="99999999"
												cssClass="form-control" placeholder="Ex:63900000 " />
											<div class="error-validation" id="erro-cepFamilia">
												<form:errors path="cepFamilia"></form:errors>
											</div>
										</div>
										<label for="uf" class="col-sm-1 control-label"><span
											class="red">*</span>UF:</label>
										<div class="col-sm-2">
											<form:select path="estadoFamilia" id="estadoFamilia"
												cssClass="form-control">
												<form:option value="" label="Selecione Estado" />
												<form:options items="${totalEstado}" />
											</form:select>
											<div class="error-validation" id="erro-estadoFamilia">
												<form:errors path="estadoFamilia"></form:errors>
											</div>
										</div>
										<label for="referencia" class="col-sm-2 control-label">Ponto
											de referencia:</label>
										<div class="col-sm-4">
											<form:input id="referencia" type="text" path="referencia"
												cssClass="form-control" placeholder="Ponto de referencia" />
											<div class="error-validation" id="erro-referencia">
												<form:errors path="referencia"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group"></div>
								</div>
								<div class="panel-heading">
									<h3>Endereço Atual</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="endereco" class="col-sm-1 control-label"><span
											class="red">*</span>Rua/Av:</label>
										<div class="col-sm-5">
											<form:input id="endereco" path="endereco"
												cssClass="form-control" placeholder="Rua /Av" />
											<div class="error-validation" id="erro-endereco">
												<form:errors path="endereco"></form:errors>
											</div>
										</div>
										<label for="bairro" class="col-sm-2 control-label"><span
											class="red">*</span>Bairro:</label>
										<div class="col-sm-4">
											<form:input id="bairro" path="bairro" cssClass="form-control"
												placeholder="Bairro" />
											<div class="error-validation" id="erro-bairroAtual">
												<form:errors path="bairro"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="numero" class="col-sm-1 control-label"><span
											class="red">*</span>Número:</label>
										<div class="col-sm-1">
											<form:input id="numero" path="numero" cssClass="form-control"
												placeholder="Num" data-mask="9999" />
											<div class="error-validation" id="erro-numeroAtual">
												<form:errors path="numero"></form:errors>
											</div>
										</div>
										<label for="cidade" class="col-sm-1 control-label"><span
											class="red">*</span>Cidade:</label>
										<div class="col-sm-3">
											<form:input id="cidade" type="text" path="cidade"
												cssClass="form-control" placeholder="Cidade" />
											<div class="error-validation" id="erro-cidadeAtual">
												<form:errors path="cidade"></form:errors>
											</div>
										</div>
										<label for="complemento" class="col-sm-2 control-label">Complemento:</label>
										<div class="col-sm-4">
											<form:input id="complemento" type="text" path="complemento"
												cssClass="form-control" placeholder="Complemento" />
											<div class="error-validation" id="erro-complementoAtual">
												<form:errors path="complemento"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="cep_familia" class="col-sm-1 control-label"><span
											class="red">*</span>CEP:</label>
										<div class="col-sm-2">
											<form:input id="cep" path="cep" data-mask="99999999"
												cssClass="form-control" placeholder="CEP" />
											<div class="error-validation" id="erro-cepAtual">
												<form:errors path="cep"></form:errors>
											</div>
										</div>
										<label for="uf" class="col-sm-1 control-label"><span
											class="red">*</span>UF:</label>
										<div class="col-sm-2">
											<form:select path="estado" id="estado"
												cssClass="form-control">
												<form:option value="" label="Selecione Estado" />
												<form:options items="${totalEstado}" />
											</form:select>
											<div class="error-validation" id="erro-estadoAtual">
												<form:errors path="estado"></form:errors>
											</div>
										</div>
										<label for="referenciaFamilia" class="col-sm-2 control-label"><span
											class="red">*</span>Ponto de referencia:</label>
										<div class="col-sm-4">
											<form:input id="referenciaFamilia" type="text"
												path="referenciaFamilia" cssClass="form-control"
												placeholder="Ponto de Referencia" />
											<div class="error-validation" id="erro-cidadeAtual">
												<form:errors path="cidade"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="telefoneFixo" class="col-sm-1 control-label">Fone:</label>
										<div class="col-sm-2">
											<form:input id="telefoneFixo" path="telefoneFixo"
												data-mask="(00) 0000-0000" cssClass="form-control"
												placeholder="(00) 0000-0000" />
											<div class="error-validation" id="erro-telefoneFixoAtual">
												<form:errors path="telefoneFixo"></form:errors>
											</div>
										</div>
										<label for="telefone_celular" class="col-sm-1 control-label">Celular:</label>
										<div class="col-sm-2">
											<form:input id="telefoneCelelular" path="telefoneCelular"
												data-mask="(00) 0000-0000" cssClass="form-control"
												placeholder="(00) 0000-0000" />
											<div class="error-validation" id="erro-telefoneCelularatual">
												<form:errors path="telefoneCelular"></form:errors>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="situacao-socio-economica-tab">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3>Situação Sócio Econômica</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="reside_atualmente" class="col-sm-3 control-label"><span
											class="red">*</span>Com quem você reside Atualmente?</label>
										<div class="col-sm-3">
											<form:input id="comQuemReside" path="comQuemReside"
												cssClass="form-control" placeholder="Ex: Familia, amigos" />
											<div class="error-validation"
												id="erro-enderecoAtualSitSocioEcon">
												<form:errors path="comQuemReside"></form:errors>
											</div>
										</div>
										<label for="cidade" class="col-sm-3 control-label">Qual
											a situação da sua residencia:</label>
										<div class="col-sm-3">
											<form:select path="situacaoResidencia" id="situacaoResidencia" cssClass="form-control">
												<form:option value="" label="Situação da Residência" />
												<form:options items="${situacaoResidencia}" />
											</form:select>
											<div class="error-validation" id="erro-situacaoResidencia">
												<form:errors path="situacaoResidencia"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="tipoResidencia" class="col-sm-3 control-label"><span
											class="red">*</span>Como define a localidade na qual vive
											atualmente?</label>
										<div class="col-sm-3">
											<form:input id="tipoResidencia" path="tipoResidencia"
												cssClass="form-control" placeholder="Ex: Casa/Apartamento" />
											<div class="error-validation" id="erro-definicaoLocalAtual">
												<form:errors path="tipoResidencia"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="qtd_aparelho_som" class="col-sm-2 control-label">Aparelho de Som:</label>
										<div class="col-sm-1">
											<form:input id="qtdAparelhoSom" data-mask="999" min="0"
												value="0" path="qtdAparelhoSom" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdAparelhoSom">
												<form:errors path="qtdAparelhoSom"></form:errors>
											</div>
										</div>
										<label for="qtdTelevisao" class="col-sm-3 control-label">Televisão:</label>
										<div class="col-sm-1">
											<form:input id="qtdTelevisao" data-mask="999" min="0"
												value="0" path="qtdTelevisao" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdTelevisao">
												<form:errors path="qtdTelevisao"></form:errors>
											</div>
										</div>
										<label for="qtdRadio" class="col-sm-4 control-label">Radio:</label>
										<div class="col-sm-1">
											<form:input id="qtdRadio" data-mask="999" min="0" value="0"
												path="qtdRadio" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdRadio">
												<form:errors path="qtdRadio"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="qtdAutomovel" class="col-sm-2 control-label">Automóvel:</label>
										<div class="col-sm-1">
											<form:input id="qtdAutomovel" data-mask="999" min="0"
												value="0" path="qtdAutomovel" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdAutomovel">
												<form:errors path="qtdAutomovel"></form:errors>
											</div>
										</div>
										<label for="qtdMotocicleta" class="col-sm-3 control-label">Motocicleta:</label>
										<div class="col-sm-1">
											<form:input id="qtdMotocicleta" data-mask="999" min="0"
												value="0" path="qtdMotocicleta" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdMotocicleta">
												<form:errors path="qtdMotocicleta"></form:errors>
											</div>
										</div>
										<label for="qtdEmpregadosDomesticos"
											class="col-sm-4 control-label">Empregados Domésticos:</label>
										<div class="col-sm-1">
											<form:input id="qtdEmpregadosDomesticos" data-mask="999"
												min="0" value="0" path="qtdEmpregadosDomesticos"
												cssClass="form-control" />
											<div class="error-validation"
												id="erro-qtdEmpregadosDomesticos">
												<form:errors path="qtdEmpregadosDomesticos"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="qtdMaquinaLavar" class="col-sm-2 control-label">Máquina
											de Lavar:</label>
										<div class="col-sm-1">
											<form:input id="qtdMaquinaLavar" data-mask="999" min="0"
												value="0" path="qtdMaquinaLavar" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdMaquinaLavar">
												<form:errors path="qtdMaquinaLavar"></form:errors>
											</div>
										</div>
										<label for="qtdGeladeira" class="col-sm-3 control-label">Geladeira:</label>
										<div class="col-sm-1">
											<form:input id="qtdGeladeira" data-mask="999" min="0"
												value="0" path="qtdGeladeira" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdGeladeira">
												<form:errors path="qtdGeladeira"></form:errors>
											</div>
										</div>
										<label for="qtdFreezer" class="col-sm-4 control-label">Freezer:</label>
										<div class="col-sm-1">
											<form:input id="qtdFreezer" data-mask="999" min="0" value="0"
												path="qtdFreezer" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdFreezer">
												<form:errors path="qtdFreezer"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="qtdTelefoneFixo" class="col-sm-2 control-label">Telefone
											Fixo:</label>
										<div class="col-sm-1">
											<form:input id="qtdTefoneFixo" data-mask="999" min="0"
												value="0" path="qtdTelefoneFixo" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdTelefoneFixo">
												<form:errors path="qtdTelefoneFixo"></form:errors>
											</div>
										</div>
										<label for="qtdCelular" class="col-sm-3 control-label">Celular
											Residentes:</label>
										<div class="col-sm-1">
											<form:input id="qtdCelular" data-mask="999" min="0" value="0"
												path="qtdCelular" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdCelularResidentes">
												<form:errors path="qtdCelular"></form:errors>
											</div>
										</div>
										<label for="qtdComputador" class="col-sm-4 control-label">Computador:</label>
										<div class="col-sm-1">
											<form:input id="qtdComputador" data-mask="999" min="0"
												value="0" path="qtdComputador" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdComputador">
												<form:errors path="qtdComputador"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="qtdFogaoGas" class="col-sm-2 control-label">Fogão
											a Gás:</label>
										<div class="col-sm-1">
											<form:input id="qtdFogaoGas" data-mask="999" min="0"
												value="0" path="qtdFogaoGas" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdFogaoGas">
												<form:errors path="qtdFogaoGas"></form:errors>
											</div>
										</div>
										<label for="qtdMaquinaDeCostura"
											class="col-sm-3 control-label">Máquina de Costura:</label>
										<div class="col-sm-1">
											<form:input id="qtdMaquinaDeCostura" data-mask="999" min="0"
												value="0" path="qtdMaquinaDeCostura" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdMaquinaCostura">
												<form:errors path="qtdMaquinaDeCostura"></form:errors>
											</div>
										</div>
										<label for="qtdComodos" class="col-sm-4 control-label">Cômodo
											sem Banheiro:</label>
										<div class="col-sm-1">
											<form:input id="qtdComodos" data-mask="999" value="0" min="0"
												path="qtdComodos" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdComodosSemBanheiro">
												<form:errors path="qtdComodos"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="qtdBanheiros" class="col-sm-2 control-label">Banheiros:</label>
										<div class="col-sm-1">
											<form:input id="qtdBanheiros" data-mask="999" min="0"
												value="0" path="qtdBanheiros" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdBanheiros">
												<form:errors path="qtdBanheiros"></form:errors>
											</div>
										</div>
										<label for="qtdDvd" class="col-sm-3 control-label">Dvd:</label>
										<div class="col-sm-1">
											<form:input id="qtdDvd" data-mask="999" min="0" value="0"
												path="qtdDvd" cssClass="form-control" />
											<div class="error-validation" id="erro-qtdDvdVideocassete">
												<form:errors path="qtdDvd"></form:errors>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="informacao-grupo-familiar-tab">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3>Informações do seu Grupo Familiar</h3>
								</div>
								<div class="panel-body">
									<jsp:include page="pessoaFamilia.jsp" />
									<div class="error-validation">
										<form:errors path="pessoas"></form:errors>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="informacao-adicional-tab">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3>Informações Adicionais</h3>
								</div>
								<div class="panel-body">
									<h3>Horários Disponíves Para Bolsa</h3>
									<div class="panel-body">
										<jsp:include page="horarioDisponivel.jsp" />
									</div>
									<div class="form-group">
										<label for="justificativaPedido"
											class="col-sm-2 control-label"><span class="red">*</span>Justificativa
											do pedido:</label>
										<div class="col-sm-9">
											<form:textarea id="justificativaPedido" type="text"
												path="justificativaPedido" cssClass="form-control"
												placeholder="Justificativa do Pedido" rows="10" />
											<div class="error-validation" id="erro-justificativa">
												<form:errors path="justificativaPedido"></form:errors>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2" id="div-form-btn">
							<input name="submit" type="submit" class="btn btn-primary"
								value="${botao }" id="form-btn" />
						</div>
						<div class="col-sm-2" id="div-form-btn">
							<a href="<c:url value="/aluno/selecao/listar"></c:url>"
								class="btn btn-default" id="form-btn">Cancelar</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#telefone').mask("(99) 9999-9999");
							$('#telefoneCelular').mask("(99) 9999-9999");
							$('#telefoneFix').mask("(99) 9999-9999");
							$('#telefoneCel').mask("(99) 9999-9999");
							$('#cep').mask("99999999");
							$('#cepFamilia').mask("99999999");
							$("a#addInf")
									.click(
											function() {
												$("tbody#corpoInfo")
														.append(
																"<tr> <td><input type='text'></td> <td><input type='text'></td> <td><input type='text'></td> <td><input type='text'></td> <td><input type='text'></td> </tr>");
												$(
														".form-group input#rendaFamilia")
														.attr("value",
																"Adicionando");
											});
						});
	</script>
</body>
</html>