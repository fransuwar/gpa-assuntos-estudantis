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

				<form:form id="questionarioForm" role="form"
					commandName="questionarioIniciacaoAcademica"
					servletRelativeAction="/iniciacaoAcademica/inscricao/"
					method="POST" cssClass="form-horizontal">

					<div class="tab-content">

						<div class="tab-pane active" id="moradia-tab">

							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3>Endereço de Residência de Origem</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="enderecoAtual" class="col-sm-1 control-label">Rua/Av
											:</label>
										<div class="col-sm-5">
											<form:input id="enderecoAtual" type="text"
												path="enderecoAtual" cssClass="form-control"
												placeholder="Rua /Av" />
											<div class="error-validation">
												<form:errors path="enderecoAtual"></form:errors>
											</div>
										</div>
										<label for="bairroFamilia" class="col-sm-2 control-label">Bairro:</label>
										<div class="col-sm-4">
											<form:input id="bairroFamilia" path="bairroFamilia"
												cssClass="form-control" placeholder="Bairro" />
											<div class="error-validation">
												<form:errors path="bairroFamilia"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="numeroFamilia" class="col-sm-1 control-label">Número:</label>
										<div class="col-sm-1">
											<form:input id="numeroFamilia" path="numeroFamilia"
												cssClass="form-control" placeholder="Número" />
											<div class="error-validation">
												<form:errors path="numeroFamilia"></form:errors>
											</div>
										</div>
										<label for="cidade" class="col-sm-1 control-label">Cidade:</label>
										<div class="col-sm-3">
											<form:input id="cidade" type="text" path="cidade"
												cssClass="form-control" placeholder="Cidade" />
											<div class="error-validation">
												<form:errors path="cidade"></form:errors>
											</div>
										</div>
										<label for="complementoFamilia" class="col-sm-2 control-label">Complemento:</label>
										<div class="col-sm-4">
											<form:input id="complementoFamilia" type="text"
												path="complementoFamilia" cssClass="form-control"
												placeholder="Complemento" />
											<div class="error-validation">
												<form:errors path="complementoFamilia"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="cepFamilia" class="col-sm-1 control-label">CEP:</label>
										<div class="col-sm-2">
											<form:input id="cep" path="cepFamilia"
												cssClass="form-control" placeholder="Ex:63900000 " />
											<div class="error-validation">
												<form:errors path="cepFamilia"></form:errors>
											</div>
										</div>
										<label for="uf" class="col-sm-1 control-label">UF:</label>
										<div class="col-sm-2">
											<form:select path="estadoFamilia" id="estadoFamilia"
												cssClass="form-control">
												<form:option value="" label="Selecione Estado" />
												<form:options items="${TotalEstado}" />
											</form:select>
										</div>
										<label for="pontoReferenciaFamilia"
											class="col-sm-2 control-label">Ponto de referencia:</label>
										<div class="col-sm-4">
											<form:input id="pontoReferenciaFamilia" type="text"
												path="pontoReferenciaFamilia" cssClass="form-control"
												placeholder="Ponto de referencia" />
											<div class="error-validation">
												<form:errors path="pontoReferenciaFamilia"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">

										<label for="telefoneFixo" class="col-sm-1 control-label">Fone:</label>
										<div class="col-sm-2">
											<form:input id="telefone" path="telefoneFixo"
												cssClass="form-control" placeholder="(00) 0000-0000" />
											<div class="error-validation">
												<form:errors path="telefoneFixo"></form:errors>
											</div>
										</div>
										<label for="telefoneCelular" class="col-sm-1 control-label">Celular:</label>
										<div class="col-sm-2">
											<form:input id="telefoneCelular" path="telefoneCelular"
												cssClass="form-control" placeholder="(00) 0000-0000" />
											<div class="error-validation">
												<form:errors path="telefoneCelular"></form:errors>
											</div>
										</div>
									</div>


								</div>
								<div class="panel-heading">
									<h3>Endereço Atual</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="enderecoFamilia" class="col-sm-1 control-label">Rua/Av
											:</label>
										<div class="col-sm-5">
											<form:input id="enderecoFamilia" path="enderecoFamilia"
												cssClass="form-control" placeholder="Rua /Av" />
											<div class="error-validation">
												<form:errors path="enderecoFamilia"></form:errors>
											</div>
										</div>
										<label for="bairro" class="col-sm-2 control-label">Bairro:</label>
										<div class="col-sm-4">
											<form:input id="bairro" path="bairro" cssClass="form-control"
												placeholder="Bairro" />
											<div class="error-validation">
												<form:errors path="bairro"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="numero" class="col-sm-1 control-label">Número:</label>
										<div class="col-sm-1">
											<form:input id="numero" path="numero" cssClass="form-control"
												placeholder="Número" />
											<div class="error-validation">
												<form:errors path="numero"></form:errors>
											</div>
										</div>
										<label for="cidade" class="col-sm-1 control-label">Cidade:</label>
										<div class="col-sm-3">
											<form:input id="cidade" type="text" path="cidadeFamilia"
												cssClass="form-control" placeholder="Cidade" />
											<div class="error-validation">
												<form:errors path="cidade"></form:errors>
											</div>
										</div>
										<label for="complemento" class="col-sm-2 control-label">Complemento:</label>
										<div class="col-sm-4">
											<form:input id="complemento" type="text" path="complemento"
												cssClass="form-control" placeholder="Complemento" />
											<div class="error-validation">
												<form:errors path="complemento"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="cep_familia" class="col-sm-1 control-label">CEP:</label>
										<div class="col-sm-2">
											<form:input id="cepFamilia" path="cep"
												cssClass="form-control" placeholder="CEP" />
											<div class="error-validation">
												<form:errors path="cep"></form:errors>
											</div>
										</div>
										<label for="uf" class="col-sm-1 control-label">UF:</label>
										<div class="col-sm-2">
											<form:select path="estado" id="estado"
												cssClass="form-control">
												<form:option value="" label="Selecione Estado" />
												<form:options items="${TotalEstado}" />
											</form:select>
										</div>
										<label for="pontoReferencia" class="col-sm-2 control-label">Ponto
											de referencia:</label>
										<div class="col-sm-4">
											<form:input id="pontoReferencia" type="text"
												path="pontoReferencia" cssClass="form-control"
												placeholder="Cidade" />
											<div class="error-validation">
												<form:errors path="cidade"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="telefoneFixo" class="col-sm-1 control-label">Fone:</label>
										<div class="col-sm-2">
											<form:input id="telefoneFix" path="telefoneFixoFamilia"
												cssClass="form-control" placeholder="(00) 0000-0000" />
											<div class="error-validation">
												<form:errors path="telefoneFixo"></form:errors>
											</div>
										</div>
										<label for="telefone_celular" class="col-sm-1 control-label">Celular:</label>
										<div class="col-sm-2">
											<form:input id="telefoneCel" path="telefoneCelularFamilia"
												cssClass="form-control" placeholder="(00) 0000-0000" />
											<div class="error-validation">
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
										<label for="reside_atualmente" class="col-sm-3 control-label">Com
											quem você reside Atualmente?</label>
										<div class="col-sm-3">
											<form:input id="resideAtualmente" path="resideAtualmente"
												cssClass="form-control" placeholder="Ex: Familia, amigos" />
											<div class="error-validation">
												<form:errors path="enderecoAtual"></form:errors>
											</div>
										</div>
										<label for="cidade" class="col-sm-3 control-label">Qual
											a situação da sua residencia:</label>
										<div class="col-sm-3">
											<form:select path="situacaoResidencia"
												id="situacaoResidencia" cssClass="form-control">
												<form:option value="" label="Situação da Residência" />
												<form:options items="${SituacaoResidencia}" />
											</form:select>
										</div>
									</div>

									<div class="form-group">
										<label for="reside_atualmente" class="col-sm-3 control-label">Como
											define a localidade na qual vive atualmente?</label>
										<div class="col-sm-3">
											<form:input id="definicaoLocalAtual"
												path="definicaoLocalAtual" cssClass="form-control"
												placeholder="Ex: Casa/Apartamento" />
											<div class="error-validation">
												<form:errors path="definicaoLocalAtual"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="qtd_aparelho_som" class="col-sm-2 control-label">Aparelho
											de Som:</label>
										<div class="col-sm-1">
											<form:input id="qtdAparelhoSom" type="number" min="0"
												path="qtdAparelhoSom" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdAparelhoSom"></form:errors>
											</div>
										</div>

										<label for="qtd_televisao" class="col-sm-3 control-label">Televisão:</label>
										<div class="col-sm-1">
											<form:input id="qtdTelevisao" type="number" min="0"
												path="qtdTelevisao" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdTelevisao"></form:errors>
											</div>
										</div>

										<label for="qtd_radio" class="col-sm-4 control-label">Radio:</label>
										<div class="col-sm-1">
											<form:input id="qtdRadio" type="number" min="0"
												path="qtdRadio" cssClass="form-control" placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdRadio"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="qtd_automovel" class="col-sm-2 control-label">Automovel:</label>
										<div class="col-sm-1">
											<form:input id="qtdAutomovel" type="number" min="0"
												path="qtdAutomovel" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdAutomovel"></form:errors>
											</div>
										</div>

										<label for="qtd_motocicleta" class="col-sm-3 control-label">Motocicleta:</label>
										<div class="col-sm-1">
											<form:input id="qtdMotocicleta" type="number" min="0"
												path="qtdMotocicleta" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdMotocicleta"></form:errors>
											</div>
										</div>

										<label for="qtd_dvd_videocassete"
											class="col-sm-4 control-label">Dvd/VideoCassete:</label>
										<div class="col-sm-1">
											<form:input id="qtdDvdVideocassete" type="number" min="0"
												path="qtdDvdVideocassete" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdDvdVideocassete"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="qtd_maquina_lavar" class="col-sm-2 control-label">Maquina
											de Lavar:</label>
										<div class="col-sm-1">
											<form:input id="qtdMaquinaLavar" type="number" min="0"
												path="qtdMaquinaLavar" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdTelevisao"></form:errors>
											</div>
										</div>

										<label for="qtd_geladeira" class="col-sm-3 control-label">Geladeira:</label>
										<div class="col-sm-1">
											<form:input id="qtdGeladeira" type="number" min="0"
												path="qtdGeladeira" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdGeladeira"></form:errors>
											</div>
										</div>

										<label for="qtd_freezer" class="col-sm-4 control-label">Freezer:</label>
										<div class="col-sm-1">
											<form:input id="qtdFreezer" type="number" min="0"
												path="qtdFreezer" cssClass="form-control" placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdFreezer"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="qtd_telefonefixo" class="col-sm-2 control-label">Telefone
											Fixo:</label>
										<div class="col-sm-1">
											<form:input id="qtdTefoneFixo" type="number" min="0"
												path="qtdTelefoneFixo" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdTelefoneFixo"></form:errors>
											</div>
										</div>

										<label for="qtd_celular_residentes"
											class="col-sm-3 control-label">Celular Residentes:</label>
										<div class="col-sm-1">
											<form:input id="qtdCelularResidentes" type="number" min="0"
												path="qtdCelularResidentes" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdCelularResidentes"></form:errors>
											</div>
										</div>

										<label for="qtd_computador" class="col-sm-4 control-label">Computador:</label>
										<div class="col-sm-1">
											<form:input id="qtdComputador" type="number" min="0"
												path="qtdComputador" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdComputador"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="qtd_fogao_gas" class="col-sm-2 control-label">Fogao
											A Gás:</label>
										<div class="col-sm-1">
											<form:input id="qtdFogaoGas" type="number" min="0"
												path="qtdFogaoGas" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdFogaoGas"></form:errors>
											</div>
										</div>

										<label for="qtd_maquina_costura"
											class="col-sm-3 control-label">Máquina de Costura:</label>
										<div class="col-sm-1">
											<form:input id="qtdMaquinaCostura" type="number" min="0"
												path="qtdMaquinaCostura" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdMaquinaCostura"></form:errors>
											</div>
										</div>

										<label for="qtd_comodos_sem_banheiro"
											class="col-sm-4 control-label">Comodo sem Banheiro:</label>
										<div class="col-sm-1">
											<form:input id="qtdComodosSemBanheiro" type="number" min="0"
												path="qtdComodosSemBanheiro" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdComodosSemBanheiro"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="qtd_banheiros" class="col-sm-2 control-label">Banheiros:</label>
										<div class="col-sm-1">
											<form:input id="qtdBanheiros" type="number" min="0"
												path="qtdBanheiros" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdBanheiros"></form:errors>
											</div>
										</div>

										<label for="qtd_empregados_domesticos"
											class="col-sm-3 control-label">Empregados Domesticos:</label>
										<div class="col-sm-1">
											<form:input id="qtdEmpregadosDomesticos" type="number"
												min="0" path="qtdEmpregadosDomesticos"
												cssClass="form-control" placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="qtdEmpregadosDomesticos"></form:errors>
											</div>
										</div>

										<label for="totalMembrosFamilia"
											class="col-sm-4 control-label">Quantidade de Membros
											da Familia:</label>
										<div class="col-sm-1">
											<form:input id="totalMembrosFamilia" type="number" min="0"
												path="totalMembrosFamilia" cssClass="form-control"
												placeholder="Fone" />
											<div class="error-validation">
												<form:errors path="totalMembrosFamilia"></form:errors>
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

									<div class="form-group">
										<label for="horariodisponivelBolsa"
											class="col-sm-2 control-label">Segunda:</label>
										<div class="col-sm-3">
											<form:select path="horariodisponivelBolsa"
												id="horariodisponivelBolsa" cssClass="form-control">
												<form:option value="" label="Selecione Horário" />
												<form:options items="${HorarioDisponivel}" />
											</form:select>
										</div>
										<label for="horariodisponivelBolsa"
											class="col-sm-2 control-label">Terça:</label>
										<div class="col-sm-3">
											<form:select path="horariodisponivelBolsa"
												id="horariodisponivelBolsa" cssClass="form-control">
												<form:option value="" label="Selecione Horário" />
												<form:options items="${HorarioDisponivel}" />
											</form:select>
										</div>
									</div>

									<div class="form-group">
										<label for="horariodisponivelBolsa"
											class="col-sm-2 control-label">Quarta:</label>
										<div class="col-sm-3">
											<form:select path="horariodisponivelBolsa"
												id="horariodisponivelBolsa" cssClass="form-control">
												<form:option value="" label="Selecione Horário" />
												<form:options items="${HorarioDisponivel}" />
											</form:select>
										</div>
										<label for="horariodisponivelBolsa"
											class="col-sm-2 control-label">Quinta:</label>
										<div class="col-sm-3">
											<form:select path="horariodisponivelBolsa"
												id="horariodisponivelBolsa" cssClass="form-control">
												<form:option value="" label="Selecione Horário" />
												<form:options items="${HorarioDisponivel}" />
											</form:select>
										</div>
									</div>

									<div class="form-group">
										<label for="horariodisponivelBolsa"
											class="col-sm-2 control-label">Sexta:</label>
										<div class="col-sm-3">
											<form:select path="horariodisponivelBolsa"
												id="horariodisponivelBolsa" cssClass="form-control">
												<form:option value="" label="Selecione Horário" />
												<form:options items="${HorarioDisponivel}" />
											</form:select>
										</div>
									</div>

									<div class="form-group">
										<label for="justificativaPedido"
											class="col-sm-2 control-label">Justificativa do
											pedido:</label>
										<div class="col-sm-9">
											<form:textarea id="justificativaPedido" type="text"
												path="justificativaPedido" cssClass="form-control"
												placeholder="Justificativa do Pedido" rows="10" />
											<div class="error-validation">
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
								value="Cadastrar" id="form-btn" />
						</div>
						<div class="col-sm-2" id="div-form-btn">
							<a href="<c:url value="/selecao/listar"></c:url>"
								class="btn btn-default" id="form-btn">Cancelar</a>
						</div>
					</div>
				</form:form>
			</div>

		</div>
	</div>


	<jsp:include page="../fragments/footer.jsp" />

	<script type="text/javascript">
		function rowAdded(rowElement) {
			$(rowElement).find("input").val('');
		}
		function rowRemoved(rowElement) {
		}

		$(document)
				.ready(
						function() {

							var config = {
								rowClass : 'pessoaFamilia',
								addRowId : 'addPessoa',
								removeRowClass : 'removePessoa',
								formId : 'questionarioForm',
								rowContainerId : 'pessoaFamiliaContainer',
								indexedPropertyName : 'pessoas',
								indexedPropertyMemberNames : 'nome, grauParentesco, escolaridade, atividade, renda',
								rowAddedListener : rowAdded,
								rowRemovedListener : rowRemoved,
							};
							new DynamicListHelper(config);
						});
	</script>



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
																"<tr>  <td><input type='text'></td> <td><input type='text'></td> <td><input type='text'></td> <td><input type='text'></td> <td><input type='text'></td> </tr>");
												$(
														".form-group input#rendaFamilia")
														.attr("value",
																"Adicionando");

											});
						});
	</script>

</body>

</html>