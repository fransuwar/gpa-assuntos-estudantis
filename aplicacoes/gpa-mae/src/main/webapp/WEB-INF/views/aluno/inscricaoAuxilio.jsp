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

<c:if test="${action eq 'inscricao' }">
	<c:url var="url" value="/aluno/inscricao/auxilio-moradia"></c:url>
	<c:set var="titulo" value="Nova Inscrição"></c:set>
	<c:set var="botao" value="Finalizar Inscrição"></c:set>
</c:if>
<c:if test="${action eq 'editar' }">
	<c:url var="url" value="/aluno/inscricao/editar/auxilio-moradia"></c:url>
	<c:set var="titulo" value="Editar Inscrição"></c:set>
	<c:set var="botao" value="Atualizar Inscrição"></c:set>
</c:if>


<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Cadastro Auxilio Moradia</title>

</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
		<div  align="left">
			<div class="form" align="center">
				<h2>Programa de Auxílio Moradia</h2>

				<ul class="nav nav-tabs">
					<li class="active"><a href="#moradia-tab" data-toggle="tab">Moradia<i
							class="fa"></i>
					</a></li>
					<li><a href="#historico-escolar-tab" data-toggle="tab">Histórico
							Escolar<i class="fa"></i>
					</a></li>
					<li><a href="#situacao-socio-economica-tab" data-toggle="tab">Situação
							Sócioeconômica<i class="fa"></i>
					</a></li>
					<li><a href="#outras-informacoes-tab" data-toggle="tab">Outras
							Informações<i class="fa"></i>
					</a></li>
					<li><a href="#justificativa-tab" data-toggle="tab">Justificativa<i
							class="fa"></i>
					</a></li>

				</ul>

				<form:form id="questionarioAuxilio" role="form"
					modelAttribute="questionarioAuxilioMoradia"
					commandName="questionarioAuxilioMoradia"
					servletRelativeAction="${url }" method="POST"
					cssClass="form-horizontal">

					<input id="idSelecao" name="idSelecao" type="hidden" value="${idSelecao}" />
					
					<div class="tab-content">

						<div class="tab-pane active" id="moradia-tab">

							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">Mora com</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<div class="col-sm-15" id="col-sm-radio">
											<div style="margin: 20px 0 25px 0;">
												<form:checkboxes items="${moraCom}" path="comQuemMora"
													itemLabel="nome" cssClass="checkbox-inline"
													cssStyle="height:20px; width:30px;" />

											</div>
											<div class="error-validation">
												<form:errors path="comQuemMora"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group" align="left" class="col-sm-10">
										<label for=comQuemMoraOutros class="col-sm-4 control-label">
											<span class="red">*</span>Se escolheu outros:
										</label>
										<div class="col-sm-5">
											<form:input id="comQuemMoraOutros" path="comQuemMoraOutros"
												cssClass="form-control"
												placeholder="Quem são essas outras pessoas" />
											<div class="error-validation">
												<form:errors path="comQuemMoraOutros"></form:errors>
											</div>
										</div>
									</div>
								</div>
								<div class="panel-heading">
									<h3 class="panel-title">Nome dos pais</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="nomeMae" class="col-sm-2 control-label"
											id="form-label-right"><span class="red">*</span>Mãe:</label>
										<div class="col-sm-4">
											<form:input id="nomeMae" type="text" path="nomeMae"
												cssClass="form-control" placeholder="Nome da Mãe" />
											<div class="error-validation">
												<form:errors path="nomeMae"></form:errors>
											</div>
										</div>
										<label for="nomePai" class="col-sm-1 control-label"><span
											class="red">*</span>Pai:</label>
										<div class="col-sm-4">
											<form:input id="nomePai" path="nomePai"
												cssClass="form-control" placeholder="Nome do Pai" />
											<div class="error-validation">
												<form:errors path="nomePai"></form:errors>
											</div>
										</div>
									</div>
								</div>

								<div class="panel-heading">
									<h3 class="panel-title">Endereço da residência atual</h3>
								</div>
								<div class="panel-body">

									<div class="form-group">
										<label for="endereco" class="col-sm-1 control-label">
											<span class="red">*</span>Rua/Av:
										</label>
										<div class="col-sm-5">
											<form:input id="endereco" path="endereco"
												cssClass="form-control" placeholder="Rua da sede do curso" />
											<div class="error-validation">
												<form:errors path="endereco"></form:errors>
											</div>
										</div>
										<label for="bairro" class="col-sm-2 control-label"><span
											class="red">*</span>Bairro:</label>
										<div class="col-sm-4">
											<form:input id="bairro" path="bairro" cssClass="form-control"
												placeholder="Bairro" />
											<div class="error-validation">
												<form:errors path="bairro"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="numero" class="col-sm-1 control-label"><span
											class="red">*</span>Número </label>
										<div class="col-sm-1">
											<form:input id="numero" path="numero" cssClass="form-control"
												data-mask="999999" placeholder="Num" />
											<div class="error-validation">
												<form:errors path="numero"></form:errors>
											</div>
										</div>
										<label for="cidade" class="col-sm-1 control-label"><span
											class="red">*</span>Cidade:</label>
										<div class="col-sm-3">
											<form:input id="cidade" path="cidade" cssClass="form-control"
												placeholder="Cidade" />
											<div class="error-validation">
												<form:errors path="cidade"></form:errors>
											</div>
										</div>
										<label for="complemento" class="col-sm-2 control-label">Complemento:</label>
										<div class="col-sm-4">
											<form:input id="complemento" path="complemento"
												cssClass="form-control"
												placeholder="Complemento da sede do curso" />
											<div class="error-validation">
												<form:errors path="complemento"></form:errors>
											</div>
										</div>

									</div>

									<div class="form-group">
										<label for="cep" class="col-sm-1 control-label"><span
											class="red">*</span>CEP:</label>
										<div class="col-sm-2">
											<form:input id="cep" path="cep" data-mask="99999-999"
												cssClass="form-control" placeholder="CEP" />
											<div class="error-validation">
												<form:errors path="cep"></form:errors>
											</div>
										</div>
										<label for="estado" class="col-sm-1 control-label"><span
											class="red">*</span>Estado:</label>
										<div class="col-sm-2">
											<form:select path="estado" id="estado"
												cssClass="form-control" cssStyle="font-size:13px">
												<form:option value="">Selecione Estado</form:option>
												<form:options items="${estado}" />
											</form:select>
											<div class="error-validation">
												<form:errors path="estado"></form:errors>
											</div>
										</div>

										<label for="pontoReferencia" class="col-sm-2 control-label"><span
											class="red">*</span>Ponto de Referencia:</label>
										<div class="col-sm-4">
											<form:input id="referencia" path="referencia"
												cssClass="form-control" placeholder="Ponto de Referencia" />
											<div class="error-validation">
												<form:errors path="referencia"></form:errors>
											</div>
										</div>
									</div>
									<!--  -->

									<!--  -->
								</div>

								<div class="panel-heading">
									<h3 class="panel-title">Endereço da residência de origem</h3>
								</div>
								<div class="panel-body">

									<div class="form-group">
										<label for="enderecoOrigem" class="col-sm-1 control-label">
											<span class="red">*</span>Rua/Av:
										</label>
										<div class="col-sm-5">
											<form:input id="enderecoOrigem" path="enderecoOrigem"
												cssClass="form-control" placeholder="Rua da sede do curso" />
											<div class="error-validation">
												<form:errors path="enderecoOrigem"></form:errors>
											</div>
										</div>
										<label for="bairroOrigem" class="col-sm-2 control-label"><span
											class="red">*</span>Bairro:</label>
										<div class="col-sm-4">
											<form:input id="bairroOrigem" path="bairroOrigem"
												cssClass="form-control" placeholder="Bairro" />
											<div class="error-validation">
												<form:errors path="bairroOrigem"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="numeroOrigem" class="col-sm-1 control-label"><span
											class="red">*</span>Número </label>
										<div class="col-sm-1">
											<form:input id="numeroOrigem" path="numeroOrigem"
												cssClass="form-control" data-mask="999999" placeholder="Num" />
											<div class="error-validation">
												<form:errors path="numeroOrigem"></form:errors>
											</div>
										</div>
										<label for="cidadeOrigem" class="col-sm-1 control-label"><span
											class="red">*</span>Cidade:</label>
										<div class="col-sm-3">
											<form:input id="cidadeOrigem" path="cidadeOrigem"
												cssClass="form-control" placeholder="Cidade" />
											<div class="error-validation">
												<form:errors path="cidadeOrigem"></form:errors>
											</div>
										</div>


										<label for="complementoOrigem" class="col-sm-2 control-label">Complemento:</label>
										<div class="col-sm-4">
											<form:input id="complementoOrigem" path="complementoOrigem"
												cssClass="form-control"
												placeholder="Complemento da sede do curso" />
											<div class="error-validation">
												<form:errors path="complementoOrigem"></form:errors>
											</div>
										</div>

									</div>

									<div class="form-group">
										<label for="cepOrigem" class="col-sm-1 control-label"><span
											class="red">*</span>CEP:</label>
										<div class="col-sm-2">
											<form:input id="cepOrigem" path="cepOrigem"
												data-mask="99999-999" cssClass="form-control"
												placeholder="CEP" />
											<div class="error-validation">
												<form:errors path="cepOrigem"></form:errors>
											</div>
										</div>
										<label for="estadoOrigem" class="col-sm-1 control-label"><span
											class="red">*</span>Estado:</label>
										<div class="col-sm-2">
											<form:select path="estadoOrigem" id="estadoOrigem"
												cssClass="form-control" cssStyle="font-size:13px">
												<form:option value="">Selecione Estado</form:option>
												<form:options items="${estado}" />
											</form:select>
											<div class="error-validation">
												<form:errors path="estadoOrigem"></form:errors>
											</div>
										</div>

										<label for="pontoReferenciaOrigem"
											class="col-sm-2 control-label"><span class="red">*</span>Ponto
											de Referencia:</label>
										<div class="col-sm-4">
											<form:input id="referenciaOrigem" path="referenciaOrigem"
												cssClass="form-control" placeholder="Ponto de Referencia" />
											<div class="error-validation">
												<form:errors path="referenciaOrigem"></form:errors>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="telefoneOrigem" class="col-sm-1 control-label">
											<span class="red">*</span>Telefone:
										</label>
										<div class="col-sm-2">
											<form:input id="telefoneOrigem" path="telefoneOrigem"
												data-mask="(99) 9 9999-9999" cssClass="form-control"
												placeholder="(00) 0 0000-0000" />
											<div class="error-validation">
												<form:errors path="telefoneOrigem"></form:errors>
											</div>
										</div>
										<label for="situacaoImovel" class="col-sm-1  control-label">Situação
											Imóvel:</label>
										<div class="col-sm-2">
											<form:select path="situacaoImovel" id="situacaoImovel"
												cssClass="form-control" cssStyle="font-size:14px">
												<form:option value="">Situação Imóvel</form:option>
												<form:options items="${situacaoImovel}" />
											</form:select>
											<div class="error-validation">
												<form:errors path="situacaoImovel"></form:errors>
											</div>
										</div>
									</div>

								</div>

								<div class="panel-heading">
									<h3 class="panel-title">Propriedade Rural</h3>
								</div>
								<div class="panel-body">

									<div class="form-group">
										<label for="grauParentescoImovelRural"
											class="col-sm-2 control-label">Grau de parentesco do
											proprietário:</label>
										<div class="col-sm-3">
											<form:select path="grauParentescoImovelRural"
												id="grauParentescoImovelRural" cssClass="form-control">
												<form:option value="" label="Selecione o Grau" />
												<form:options items="${grauParentescoImovelRural}" />
											</form:select>
											<div class="error-validation">
												<form:errors path="grauParentescoImovelRural"></form:errors>
											</div>
										</div>
										<label for="areaPropriedadeRural"
											class="col-sm-2 control-label">Área Propriedade:</label>
										<div class="col-sm-3">
											<form:input id="areaPropriedadeRural"
												path="areaPropriedadeRural" cssClass="form-control"
												placeholder="Área da Propriedade" />
											<div class="error-validation">
												<form:errors path="areaPropriedadeRural"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="cidadeEstadoImovelRural"
											class="col-sm-2 control-label">Cidade:</label>
										<div class="col-sm-3">
											<form:input id="cidadeEstadoImovelRural"
												path="cidadeEstadoImovelRural" cssClass="form-control"
												placeholder="Cidade e Estado do Imovel Rural" />
											<div class="error-validation">
												<form:errors path="cidadeEstadoImovelRural"></form:errors>
											</div>
										</div>
									</div>

								</div>

								<div class="panel-heading">
									<h3 class="panel-title">Bens Móveis (Veículos)</h3>
								</div>
								<div class="panel-body">

									<div class="form-group">
										<label for="grauParentescoVeiculos"
											class="col-sm-2 control-label">Grau de parentesco do
											proprietário:</label>
										<div class="col-sm-3">
											<form:select path="grauParentescoVeiculos"
												id="grauParentescoVeiculos" cssClass="form-control">
												<form:option value="" label="Selecione o Grau" />
												<form:options items="${grauParentescoVeiculos}" />
											</form:select>
											<div class="error-validation">
												<form:errors path="grauParentescoVeiculos"></form:errors>
											</div>
										</div>
										<label for="tipoVeiculo" class="col-sm-2 control-label">Veículo:</label>
										<div class="col-sm-3">
											<form:input id="veiculo" path="veiculo"
												cssClass="form-control" placeholder="Tipo do veículo" />
											<div class="error-validation">
												<form:errors path="veiculo"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="finalidadeVeiculo" class="col-sm-2 control-label">Finalidade
											do veículo:</label>
										<div class="col-sm-3">
											<form:select path="finalidadeVeiculo" id="finalidadeVeiculo"
												cssClass="form-control">
												<form:option value="">Selecione a Finalidade</form:option>
												<form:options items="${finalidadeVeiculo}" />
											</form:select>
											<div class="error-validation">
												<form:errors path="finalidadeVeiculo"></form:errors>
											</div>
										</div>
									</div>

								</div>

							</div>

						</div>

						<div class="tab-pane" id="historico-escolar-tab">

							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">Ensino Fundamental</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="ensinoFundamental" class="col-sm-2 control-label"
											id="form-label-right-select-tam-padrao"><span
											class="red">*</span>Tipo de escola</label>
										<div class="col-sm-2">
											<form:select path="ensinoFundamental" id="ensinoFundamental"
												cssClass="form-control" cssStyle="font-size:13px;">
												<form:option value="" label="Selecione o Tipo" />
												<form:options items="${tipoEnsinoFundamental}" />
											</form:select>
											<div class="error-validation">
												<form:errors path="ensinoFundamental"></form:errors>
											</div>
										</div>

										<label for="bolsaEnsinoFundamental"
											class="col-sm-2 control-label">Possuia bolsa? </label>
										<div class="col-sm-1">
											<div class="checkbox" id="checkbox-div">
												<form:checkbox id="checkbox-mine"
													path="bolsaEnsinoFundamental" cssClass="form-control" />
											</div>
										</div>
										<label for="percentualParticularFundamental"
											class="col-sm-2 control-label">Percentual de bolsa:</label>
										<div class="col-sm-2">
											<div class="input-group">
												<form:input id="percentualParticularFundamental"
													path="percentualParticularFundamental"
													cssClass="form-control" data-mask="99" placeholder="0" />
												<div class="input-group-addon">%</div>
											</div>
											<div class="error-validation">
												<form:errors path="percentualParticularFundamental"></form:errors>
											</div>
										</div>
									</div>

								</div>

								<div class="panel-heading">
									<h3 class="panel-title">Ensino Médio</h3>
								</div>
								<div class="panel-body">

									<div class="form-group">
										<label for="ensinoMedio" class="col-sm-2 control-label"
											id="form-label-right-select-tam-padrao"><span
											class="red">*</span>Tipo de escola</label>
										<div class="col-sm-2">
											<form:select path="ensinoMedio" id="ensinoMedio"
												cssClass="form-control" cssStyle="font-size:13px;">
												<form:option value="" label="Selecione o Tipo" />
												<form:options items="${tipoEnsinoMedio}" />
											</form:select>
											<div class="error-validation">
												<form:errors path="ensinoMedio"></form:errors>
											</div>
										</div>


										<label for="bolsaEnsinoMedio" class="col-sm-2 control-label">Possuia
											bolsa? </label>
										<div class="col-sm-1">
											<div class="checkbox" id="checkbox-div">
												<form:checkbox id="bolsaEnsinoMedio" path="bolsaEnsinoMedio"
													cssClass="form-control" />
											</div>
										</div>

										<label for="percentualParticularMedio"
											class="col-sm-2 control-label">Percentual de bolsa:</label>
										<div class="col-sm-2">
											<div class="input-group">
												<form:input id="percentualParticularMedio"
													path="percentualParticularMedio" cssClass="form-control"
													data-mask="99" placeholder="0" />
												<div class="input-group-addon">%</div>
											</div>
											<div class="error-validation">
												<form:errors path="percentualParticularMedio"></form:errors>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="cursinho" class="col-sm-3 control-label">Fez
											cursinho pré-vestibular? </label>
										<div class="col-sm-1">
											<div class="checkbox" id="checkbox-div">
												<form:checkbox id="cursinho" path="cursinho"
													cssClass="form-control" />
											</div>
										</div>
										<div class="error-validation">
											<form:errors path="cursinho"></form:errors>
										</div>

										<label for="nomeCursinho" class="col-sm-3 control-label">Nome
											do Cursinho:</label>
										<div class="col-sm-4">
											<form:input id="nomeCursinho" path="nomeCursinho"
												cssClass="form-control" placeholder="Nome do cursinho" />
											<div class="error-validation">
												<form:errors path="nomeCursinho"></form:errors>
											</div>
										</div>
									</div>

								</div>
							</div>

						</div>

						<div class="tab-pane" id="situacao-socio-economica-tab">

							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">Situação Socioeconômica (Grupo Familiar incluido o
										aluno)</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<jsp:include page="pessoaFamilia.jsp" />
										<div class="error-validation">
											<form:errors path="pessoas"></form:errors>
										</div>
									</div>
								</div>
							</div>

						</div>

						<div class="tab-pane" id="outras-informacoes-tab">

							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">Outras Informações</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<div class="form-group">
											<label for="bolsista" class="col-sm-2 control-label">Bolsista
												UFC:</label>
											<div class="col-sm-1">
												<div class="checkbox" id="checkbox-div">
													<form:checkbox id="bolsistaUfc" path="bolsistaUfc"
														cssClass="form-control" />
												</div>
												<div class="error-validation">
													<form:errors path="bolsistaUfc"></form:errors>
												</div>
											</div>

											<label for="tipoBolsa" class="col-sm-2 control-label">Descrição
												Bolsa:</label>
											<div class="col-sm-3">
												<form:input id="descricaoBolsa" path="descricaoBolsa"
													cssClass="form-control" placeholder="Descrição Bolsa" />
												<div class="error-validation">
													<form:errors path="descricaoBolsa"></form:errors>
												</div>
											</div>
										</div>

										<div class="form-group">
											<label for="possuiGraduacao" class="col-sm-2 control-label">Possui
												Graduação:</label>
											<div class="col-sm-1">
												<div class="checkbox" id="checkbox-div">
													<form:checkbox id="graduacao" path="graduacao"
														cssClass="form-control" />
												</div>
												<div class="error-validation">
													<form:errors path="graduacao"></form:errors>
												</div>
											</div>

											<label for="descricaoGraduacao"
												class="col-sm-2 control-label">Descrição da
												Graduação:</label>
											<div class="col-sm-5">
												<form:input id="descricaoGraduacao"
													path="descricaoGraduacao" cssClass="form-control"
													placeholder="Descricao da Graduacao" />
												<div class="error-validation">
													<form:errors path="descricaoGraduacao"></form:errors>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>

						<div class="tab-pane" id="justificativa-tab">

							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">Justificativa</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<div class="form-group">
											<label for="justificativa" class="col-sm-3 control-label"><span
												class="red">*</span>Justificativa para Bolsa:</label>
											<div class="col-sm-8">
												<form:textarea id="justificativa" path="justificativa"
													cssClass="form-control" placeholder="Justificativa"
													rows="10" />
												<div class="error-validation">
													<form:errors path="justificativa"></form:errors>
												</div>
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
							<a href="<c:url value="/aluno/selecao/listar" ></c:url>"
								class="btn btn-default" id="form-btn">Cancelar</a>
						</div>
					</div>
				</form:form>

			</div>

		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>

</html>