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

	<form:form id="questionarioForm" role="form"
		modelAttribute="questionarioAuxilioMoradia"
		commandName="questionarioAuxilioMoradia"
		servletRelativeAction="/auxilio/inscricao/" method="POST"
		cssClass="form-horizontal" style="margin-bottom: 80px;">

		<input type="hidden" name="id"
			value="${QuestionarioAuxilioMoradia.id }" />


		<div class="panel panel-primary">
			<div class="panel-heading">
				<h2>Novo Cadastro</h2>
			</div>
			<div class="panel-body">

				<div class="form-group">
					<label for="nomeMae" class="col-sm-2 control-label"
						id="form-label-right">Mora com:</label>
					<div class="col-sm-8">
						<form:checkboxes items="${moraCom}" path="moraCom" />
					</div>
				</div>

				<div class="form-group">
					<label for="nomeMae" class="col-sm-2 control-label"
						id="form-label-right">Nome da Mãe:</label>
					<div class="col-sm-4">
						<form:input id="nomeMae" type="text" path="nomeMae"
							cssClass="form-control" placeholder="Nome da Mãe" />
						<div class="error-validation">
							<form:errors path="nomeMae"></form:errors>
						</div>
					</div>
					<label for="nomePai" class="col-sm-2 control-label"
						id="form-label-right">Nome do Pai:</label>
					<div class="col-sm-4">
						<form:input id="nomePai" path="nomePai" cssClass="form-control"
							placeholder="Nome do Pai" />
						<div class="error-validation">
							<form:errors path="nomePai"></form:errors>
						</div>
					</div>
				</div>

				<h2 class="h2-form">Endereço na sede do Curso</h2>

				<div class="form-group">
					<label for="enderecoSedeCurso" class="col-sm-2 control-label"
						id="form-label-right">Rua/Av:</label>
					<div class="col-sm-4">
						<form:input id="enderecoSedeCurso" path="enderecoSedeCurso"
							cssClass="form-control" placeholder="Rua da sede do curso" />
						<div class="error-validation">
							<form:errors path="enderecoSedeCurso"></form:errors>
						</div>
					</div>
					<label for="bairro" class="col-sm-1 control-label">Bairro:</label>
					<div class="col-sm-4">
						<form:input id="Bairro da sede do curso" path="bairro"
							cssClass="form-control" placeholder="Bairro" />
						<div class="error-validation">
							<form:errors path="bairro"></form:errors>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="numeroCasa" class="col-sm-2 control-label"
						id="form-label-right">Número </label>
					<div class="col-sm-1">
						<form:input id="numeroCasa" path="numeroCasa"
							cssClass="form-control" placeholder="Número da Casa" />
						<div class="error-validation">
							<form:errors path="numeroCasa"></form:errors>
						</div>
					</div>
					<label for="cep" class="col-sm-1 control-label">Cep:</label>
					<div class="col-sm-2">
						<form:input id="cep" path="cep" cssClass="form-control"
							placeholder="CEP" />
						<div class="error-validation">
							<form:errors path="cep"></form:errors>
						</div>
					</div>
					<label for="complemento" class="col-sm-1 control-label">Complemento:</label>
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
					<label for="pontoReferencia" class="col-sm-2 control-label"
						id="form-label-right">Ponto de Referencia:</label>
					<div class="col-sm-4">
						<form:input id="pontoReferencia" path="pontoReferencia"
							cssClass="form-control" placeholder="Ponto de Referencia" />
						<div class="error-validation">
							<form:errors path="pontoReferencia"></form:errors>
						</div>
					</div>
					<label for="telefone" class="col-sm-1 control-label">Telefone:</label>
					<div class="col-sm-2">
						<form:input id="telefone" path="telefone" cssClass="form-control"
							placeholder="(00) 0000-0000" />
						<div class="error-validation">
							<form:errors path="telefone"></form:errors>
						</div>
					</div>
				</div>

				<h2 class="h2-form">Endereço da residência atual</h2>

				<div class="form-group">
					<label for="rua" class="col-sm-2 control-label"
						id="form-label-right">Rua/Av:</label>
					<div class="col-sm-4">
						<form:input id="rua" path="rua" cssClass="form-control"
							placeholder="Nome da Rua" />
						<div class="error-validation">
							<form:errors path="rua"></form:errors>
						</div>
					</div>
					<label for="cidade" class="col-sm-1 control-label">Cidade:</label>
					<div class="col-sm-4">
						<form:input id="cidade" path="cidade" cssClass="form-control"
							placeholder="Cidade" />
						<div class="error-validation">
							<form:errors path="cidade"></form:errors>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="telefone" class="col-sm-2 control-label"
						id="form-label-right">Telefone:</label>
					<div class="col-sm-2">
						<form:input id="telefone" path="telefone" cssClass="form-control"
							placeholder="(00) 0000-0000" />
						<div class="error-validation">
							<form:errors path="telefone"></form:errors>
						</div>
					</div>
				</div>

				<h2 class="h2-form">Selecione um estado:</h2>
				<div class="form-group">
					<label for="estado" class="col-sm-2 control-label"
						id="form-label-right-select">Estado:</label>
					<div class="col-sm-3 control-label">
						<form:select path="estado" id="estado" cssClass="form-control">
							<form:option value="">Selecione Estado</form:option>
							<form:options items="${estado}" />
						</form:select>
						<br>
					</div>
				</div>

				<h2 class="h2-form">Selecione a situação atual do ímovel</h2>
				<div class="form-group">
					<label for="situacaoImovel" class="col-sm-3 control-label"
						id="form-label-right-select-tam-padrao">Situação Imóvel:</label>
					<div class="col-sm-3 control-label">
						<form:select path="situacaoImovel" id="situacaoImovel"
							cssClass="form-control">
							<form:option value="">Situação Imóvel</form:option>
							<form:options items="${situacaoImovel}" />
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label for="valorMensalFinanciamento"
						class="col-sm-3 control-label" id="label">Valor Mensal do
						Financiamento:</label>
					<div class="col-sm-3">
						<form:input id="valorMensalFinanciamento"
							path="valorMensalFinanciamento" cssClass="form-control"
							placeholder="Valor Mensal Financiamento" />
						<div class="error-validation">
							<form:errors path="valorMensalFinanciamento"></form:errors>
						</div>
					</div>
				</div>

				<h2 class="h2-form">Selecione o grau de parentesco da
					propriedade rural:</h2>

				<div class="form-group">
					<label for="situacaoImovel" class="col-sm-2 control-label"
						id="form-label-right-select-tam-padrao">Grau de
						parentesco:</label>
					<div class="col-sm-3 control-label">
						<form:select path="grauParentescoImovelRural"
							id="grauParentescoImovelRural" cssClass="form-control">
							<form:option value="" label="Selecione o Grau" />
							<form:options items="${grauParentescoImovelRural}" />
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="propriedadeRural" class="col-sm-2 control-label">Propriedade
						Rural:</label>
					<div class="col-sm-4">
						<form:input id="propriedadeRural" path="propriedadeRural"
							cssClass="form-control" placeholder="Propriedade Rural" />
						<div class="error-validation">
							<form:errors path="propriedadeRural"></form:errors>
						</div>
					</div>
					<label for="areaPropriedade" class="col-sm-2 control-label">Área
						Propriedade:</label>
					<div class="col-sm-1">
						<form:input id="areaPropriedade" path="areaPropriedade"
							cssClass="form-control" placeholder="Área da Propriedade" />
						<div class="error-validation">
							<form:errors path="areaPropriedade"></form:errors>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="cidadeEstado" class="col-sm-2 control-label">Cidade/Estado:</label>
					<div class="col-sm-4">
						<form:input id="cidadeEstado" path="cidadeEstado"
							cssClass="form-control" placeholder="Cidade/Estado" />
						<div class="error-validation">
							<form:errors path="cidadeEstado"></form:errors>
						</div>
					</div>
				</div>

				<h2 class="h2-form">Selecione o grau de parentesco para com o
					veículo:</h2>

				<div class="form-group">
					<label for="grauParentesco" class="col-sm-2 control-label"
						id="form-label-right-select-tam-padrao">Grau de
						parentesco:</label>
					<div class="col-sm-3 control-label">
						<form:select path="grauParentescoVeiculos"
							id="grauParentescoVeiculos" cssClass="form-control">
							<form:option value="" label="Selecione o Grau" />
							<form:options items="${grauParentescoVeiculos}" />
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="veiculos" class="col-sm-2 control-label">Veículos:</label>
					<div class="col-sm-4">
						<form:input id="veiculos" path="veiculos" cssClass="form-control"
							placeholder="Veículos" />
						<div class="error-validation">
							<form:errors path="veiculos"></form:errors>
						</div>
					</div>
					<label for="tipo" class="col-sm-1 control-label">Tipo:</label>
					<div class="col-sm-4">
						<form:input id="tipo" path="tipo" cssClass="form-control"
							placeholder="Tipo" />
						<div class="error-validation">
							<form:errors path="tipo"></form:errors>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="marca" class="col-sm-2 control-label">Marca:</label>
					<div class="col-sm-3">
						<form:input id="marca" path="marca" cssClass="form-control"
							placeholder="Marca" />
						<div class="error-validation">
							<form:errors path="marca"></form:errors>
						</div>
					</div>
					<label for="modelo" class="col-sm-1 control-label">Modelo:</label>
					<div class="col-sm-3">
						<form:input id="modelo" path="modelo" cssClass="form-control"
							placeholder="Modelo" />
						<div class="error-validation">
							<form:errors path="modelo"></form:errors>
						</div>
					</div>
					<label for="ano" class="col-sm-1 control-label">Ano:</label>
					<div class="col-sm-1">
						<form:input id="ano" path="ano" cssClass="form-control"
							placeholder="Ano" />
						<div class="error-validation">
							<form:errors path="ano"></form:errors>
						</div>
					</div>
				</div>

				<h2 class="h2-form">Selecione a finalidade do veículo:</h2>

				<div class="form-group">
					<label for="finalidadeVeiculo" class="col-sm-2 control-label"
						id="form-label-right-select-tam-padrao">Finalidade do
						Veículo</label>
					<div class="col-sm-3 control-label">
						<form:select path="finalidadeVeiculo" id="finalidadeVeiculo"
							cssClass="form-control">
							<form:option value="" label="Selecione a Finalidade" />
							<form:options items="${finalidadeVeiculo}" />
						</form:select>
					</div>
				</div>

				<h2 class="h2-form">Selecione o tipo escolar que você
					frequentou:</h2>

				<div class="form-group">
					<label for="ensinoFundamental" class="col-sm-2 control-label"
						id="form-label-right-select-tam-padrao">Ensino Fundamental</label>
					<div class="col-sm-3 control-label">
						<form:select path="ensinoFundamental" id="ensinoFundamental"
							cssClass="form-control">
							<form:option value="" label="Selecione o Tipo" />
							<form:options items="${tipoEnsinoFundamental}" />
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="percentualParticularFundamental"
						class="col-sm-2 control-label">Percentual de bolsa:</label>
					<div class="col-sm-1">
						<form:input id="percentualParticularFundamental"
							path="percentualParticularFundamental" cssClass="form-control" />
						<div class="error-validation">
							<form:errors path="percentualParticularFundamental"></form:errors>
						</div>
					</div>
				</div>

				<h2 class="h2-form">Selecione o tipo escolar que você
					frequentou:</h2>

				<div class="form-group">
					<label for="ensinoMedio" class="col-sm-2 control-label"
						id="form-label-right-select-tam-padrao">Ensino Médio</label>
					<div class="col-sm-3 control-label">
						<form:select path="ensinoMedio" id="ensinoMedio"
							cssClass="form-control">
							<form:option value="" label="Selecione o Tipo" />
							<form:options items="${tipoEnsinoMedio}" />
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="percentualParticularMedio"
						class="col-sm-2 control-label">Percentual de bolsa:</label>
					<div class="col-sm-1">
						<form:input id="percentualParticularMedio"
							path="percentualParticularMedio" cssClass="form-control"
							placeholder="Percentual de bolsa Particular ensino Medio" />
						<div class="error-validation">
							<form:errors path="percentualParticularMedio"></form:errors>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-2">
						<label for="cursinho" class="col-sm-2 control-label">Fez
							cursinho? </label>
						<div class="checkbox">
							<form:checkbox id="cursinho" path="cursinho"
								cssClass="form-control" />
						</div>
					</div>
					<div class="error-validation">
						<form:errors path="cursinho"></form:errors>
					</div>
					<div class="col-sm-2">
						<label for="bolsista" class="col-sm-2 control-label"
							id="form-label-right-checkbox-tam-padrao">Bolsista:</label>
						<div class="checkbox">
							<form:checkbox id="bolsista" path="bolsista"
								cssClass="form-control" />
						</div>
					</div>
					<div class="error-validation">
						<form:errors path="bolsista"></form:errors>
					</div>
					<div class="col-sm-2">
						<label for="possuiGraduacao" class="col-sm-2 control-label">Possui
							Graduação:</label>
						<div class="checkbox">
							<form:checkbox id="possuiGraduacao" path="possuiGraduacao"
								cssClass="form-control" />
						</div>
					</div>
					<div class="error-validation">
						<form:errors path="possuiGraduacao"></form:errors>
					</div>
				</div>

				<div class="form-group">
					<label for="nomeCursinho" class="col-sm-2 control-label">Nome
						do Cursinho:</label>
					<div class="col-sm-3">
						<form:input id="nomeCursinho" path="nomeCursinho"
							cssClass="form-control" placeholder="Nome do cursinho" />
						<div class="error-validation">
							<form:errors path="nomeCursinho"></form:errors>
						</div>
					</div>
					<label for="tipoBolsa" class="col-sm-2 control-label">Tipo
						de Bolsa:</label>
					<div class="col-sm-3">
						<form:input id="tipoBolsa" path="tipoBolsa"
							cssClass="form-control" placeholder="Tipo de Bolsa" />
						<div class="error-validation">
							<form:errors path="tipoBolsa"></form:errors>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="descricaoGraduacao" class="col-sm-2 control-label">Descrição
						da Graduação:</label>
					<div class="col-sm-8">
						<form:input id="descricaoGraduacao" path="descricaoGraduacao"
							cssClass="form-control" placeholder="Descricao da Graduacao" />
						<div class="error-validation">
							<form:errors path="descricaoGraduacao"></form:errors>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="justificativa" class="col-sm-2 control-label">Justificativa
						para Bolsa:</label>
					<div class="col-sm-8">
						<form:textarea id="justificativa" path="justificativa"
							cssClass="form-control" placeholder="Justificativa" />
						<div class="error-validation">
							<form:errors path="justificativa"></form:errors>
						</div>
					</div>
				</div>

				<jsp:include page="pessoaFamilia.jsp" />

				<div class="form-group">
					<div class="col-sm-2" id="div-form-btn">
						<input name="submit" type="submit" class="btn btn-primary"
							value="Cadastrar" id="form-btn" />
					</div>
					<div class="col-sm-2" id="div-form-btn">
						<a href="<c:url value="/selecao/listar" ></c:url>"
							class="btn btn-default" id="form-btn">Cancelar</a>
					</div>
				</div>
			</div>
		</div>
	</form:form>

	<jsp:include page="../fragments/footer.jsp" />

</body>



</html>