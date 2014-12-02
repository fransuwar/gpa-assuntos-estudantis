<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
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

			

			<form:form id="questionarioForm" role="form" modelAttribute="questionarioAuxilioMoradia"
				commandName="questionarioAuxilioMoradia" servletRelativeAction="/inscricao/auxilio/"
				method="POST" cssClass="form-horizontal">

				<input type="hidden" name="id"
					value="${QuestionarioAuxilioMoradia.id }" />


						<form:checkboxes items="${moraCom}" path="moraCom" />					
	

				<div class="form-group">
					<label for="nomeMae" class="col-sm-2 control-label">Nome da Mãe:</label>
					<div class="col-sm-10">
						<form:input id="nomeMae" type="text" path="nomeMae" cssClass="form-control" placeholder="Nome da Mãe" />
						<div class="error-validation">
							<form:errors path="nomeMae"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="nomePai" class="col-sm-2 control-label">Nome do
						Pai:</label>
					<div class="col-sm-10">
						<form:input id="nomePai" path="nomePai" cssClass="form-control"
							placeholder="Nome do Pai" />
						<div class="error-validation">
							<form:errors path="nomePai"></form:errors>
						</div>
					</div>
				</div>

				<h4>Endereço na sede do Curso</h4>

				<div class="form-group">
					<label for="enderecoSedeCurso" class="col-sm-2 control-label">Rua/Av:</label>
					<div class="col-sm-7">
						<form:input id="enderecoSedeCurso" path="enderecoSedeCurso"
							cssClass="form-control" placeholder="Rua da sede do curso" />
						<div class="error-validation">
							<form:errors path="enderecoSedeCurso"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="numeroCasa" class="col-sm-2 control-label">Número
						da casa:</label>
					<div class="col-sm-1">
						<form:input id="numeroCasa" path="numeroCasa"
							cssClass="form-control" placeholder="Número da Casa" />
						<div class="error-validation">
							<form:errors path="numeroCasa"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="complemento" class="col-sm-2 control-label">Complemento:</label>
					<div class="col-sm-6">
						<form:input id="complemento" path="complemento"
							cssClass="form-control" placeholder="complemento" />
						<div class="error-validation">
							<form:errors path="complemento"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="bairro" class="col-sm-2 control-label">Bairro:</label>
					<div class="col-sm-5">
						<form:input id="bairro" path="bairro" cssClass="form-control"
							placeholder="bairro" />
						<div class="error-validation">
							<form:errors path="bairro"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="cep" class="col-sm-2 control-label">Cep:</label>
					<div class="col-sm-2">
						<form:input id="cep" path="cep" cssClass="form-control"
							placeholder="cep" />
						<div class="error-validation">
							<form:errors path="cep"></form:errors>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="telefone" class="col-sm-2 control-label">Telefone:</label>
					<div class="col-sm-2">
						<form:input id="telefone" path="telefone" cssClass="form-control"
							placeholder="telefone" />
						<div class="error-validation">
							<form:errors path="telefone"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="pontoReferencia" class="col-sm-2 control-label">Ponto
						de Referencia:</label>
					<div class="col-sm-10">
						<form:input id="pontoReferencia" path="pontoReferencia"
							cssClass="form-control" placeholder="Ponto de Referencia" />
						<div class="error-validation">
							<form:errors path="pontoReferencia"></form:errors>
						</div>
					</div>
				</div>

				<h4>Endereço da residencia atual</h4>

				<div class="form-group">
					<label for="rua" class="col-sm-2 control-label">Nome da
						rua:</label>
					<div class="col-sm-6">
						<form:input id="rua" path="rua" cssClass="form-control"
							placeholder="Nome da Rua" />
						<div class="error-validation">
							<form:errors path="rua"></form:errors>
						</div>
					</div>
				</div>



				<div class="form-group">
					<label for="cidade" class="col-sm-2 control-label">Cidade:</label>
					<div class="col-sm-6">
						<form:input id="cidade" path="cidade" cssClass="form-control"
							placeholder="cidade" />
						<div class="error-validation">
							<form:errors path="cidade"></form:errors>
						</div>
					</div>
				</div>



				<div class="form-group">
					<label for="telefone" class="col-sm-2 control-label">Telefone:</label>
					<div class="col-sm-2">
						<form:input id="telefone" path="telefone" cssClass="form-control"
							placeholder="telefone" />
						<div class="error-validation">
							<form:errors path="telefone"></form:errors>
						</div>
					</div>
				</div>

				<h4>Selecione um estado:</h4>
				<div class="form-group">
					<div class="col-sm-3 control-label">
						<select name="estado" id="estado">
							<c:forEach items="${ufs}" var="opcao">
								<option value="${opcao}">
									<c:out value="${opcao.estado}"></c:out>
								</option>
							</c:forEach>
						</select><br>
					</div>
				</div>

				<h4>Selecione a situação atual do ímovel</h4>
				<div class="form-group">
					<div class="col-sm-3 control-label">
						<select name="imovel" id="imovel">
							<c:forEach items="${situacaoImovel }" var="opcao">
								<option value="${opcao}">
									<c:out value="${opcao.imovel}"></c:out>
								</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<div class="form-group">
					<label for="valorMensalFinanciamento"
						class="col-sm-2 control-label">Valor Mensal do
						Financiamento:</label>
					<div class="col-sm-2">
						<form:input id="valorMensalFinanciamento"
							path="valorMensalFinanciamento" cssClass="form-control"
							placeholder="valor Mensal do Financiamento" />
						<div class="error-validation">
							<form:errors path="valorMensalFinanciamento"></form:errors>
						</div>
					</div>
				</div>

				<h4>Selecione o grau de parentesco da propriedade rural:</h4>
				
				<div class="form-group">
					<div class="col-sm-3 control-label">
						<select name="${status.expression}"
							name="grauParentescoImovelRural" id="grauParentescoImovelRural">
							<c:forEach items="${grauParentescoImovelRural }" var="opcao">
								<option value="${opcao}">
									<c:out value="${opcao.imovelRural}"></c:out>
								</option>
							</c:forEach>
						</select>
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
				</div>
				
				

				<div class="form-group">
					<label for="areaPropriedade" class="col-sm-2 control-label">Área
						Propriedade:</label>
					<div class="col-sm-4">
						<form:input id="areaPropriedade" path="areaPropriedade"
							cssClass="form-control" placeholder="Área da Propriedade" />
						<div class="error-validation">
							<form:errors path="areaPropriedade"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="cidadeEstado" class="col-sm-2 control-label">Cidade/Estado:</label>
					<div class="col-sm-5">
						<form:input id="cidadeEstado" path="cidadeEstado"
							cssClass="form-control" placeholder="cidade/Estado" />
						<div class="error-validation">
							<form:errors path="cidadeEstado"></form:errors>
						</div>
					</div>
				</div>

				
				

			<h4>Selecione o grau de parentesco para com o veículo:</h4>
			
			
				<div class="form-group">
					<div class="col-sm-3 control-label">
						<select name="${status.expression}" name="grauParentescoVeiculos"
							id="grauParentescoVeiculos">
							<c:forEach items="${grauParentescoVeiculos }" var="opcao">
								<option value="${opcao}">
									<c:out value="${opcao.parentesco}"></c:out>
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			
				<div class="form-group">
					<label for="veiculos" class="col-sm-2 control-label">Veículos:</label>
					<div class="col-sm-4">
						<form:input id="veiculos" path="veiculos" cssClass="form-control"
							placeholder="veiculos" />
						<div class="error-validation">
							<form:errors path="veiculos"></form:errors>
						</div>
					</div>
				</div>

			



				<div class="form-group">
					<label for="tipo" class="col-sm-2 control-label">Tipo:</label>
					<div class="col-sm-4">
						<form:input id="tipo" path="tipo" cssClass="form-control"
							placeholder="tipo" />
						<div class="error-validation">
							<form:errors path="tipo"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="marca" class="col-sm-2 control-label">Marca:</label>
					<div class="col-sm-4">
						<form:input id="marca" path="marca" cssClass="form-control"
							placeholder="marca" />
						<div class="error-validation">
							<form:errors path="marca"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="modelo" class="col-sm-2 control-label">Modelo:</label>
					<div class="col-sm-4">
						<form:input id="modelo" path="modelo" cssClass="form-control"
							placeholder="modelo" />
						<div class="error-validation">
							<form:errors path="modelo"></form:errors>
						</div>
					</div>
				</div>



				<div class="form-group">
					<label for="ano" class="col-sm-2 control-label">Ano:</label>
					<div class="col-sm-2">
						<form:input id="ano" path="ano" cssClass="form-control"
							placeholder="ano" />
						<div class="error-validation">
							<form:errors path="ano"></form:errors>
						</div>
					</div>
				</div>

				<h4>Selecione a finalidade do veículo:</h4>
				<div class="form-group">
					<div class="col-sm-3 control-label">
						<select name="${status.expression}" name="finalidadeVeiculo"
							id="finalidadeVeiculo">
							<c:forEach items="${finalidadeVeiculo }" var="opcao">
								<option value="${opcao}">
									<c:out value="${opcao.veiculo}"></c:out>
								</option>
							</c:forEach>
						</select>
					</div>
				</div>


			<h4>Selecione o tipo escolar que você frequentou:</h4>
				<div class="form-group">
					<div class="col-sm-3 control-label">
						<h5>Ensino Fundamental</h5>
						<select name="${status.expression}" name="ensinoFundamental"
							id="ensinoFundamental">
							<c:forEach items="${tipoEnsinoFundamental }" var="opcao">
								<option value="${opcao}">
									<c:out value="${opcao.nome}"></c:out>
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				
				<div class="form-group">
					<label for="percentualParticularFundamental" class="col-sm-2 control-label">Percentual de bolsa:</label>
					<div class="col-sm-1">
						<form:input id="percentualParticularFundamental" path="percentualParticularFundamental" cssClass="form-control"
							placeholder="Percentual de bolsa Particular ensino Fundamental" />
						<div class="error-validation">
							<form:errors path="percentualParticularFundamental"></form:errors>
						</div>
					</div>
				</div>
				

				<h4>Selecione o tipo escolar que você frequentou:</h4>
				<div class="form-group">
					<div class="col-sm-3 control-label">
						<h5>Ensino Médio</h5>
						<select name="${status.expression}" name="tipoEnsinoMedio"
							id="tipoEnsinoMedio">
							<c:forEach items="${tipoEnsinoMedio }" var="opcao">
								<option value="${opcao}">
									<c:out value="${opcao.media}"></c:out>
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="percentualParticularMedio" class="col-sm-2 control-label">Percentual de bolsa:</label>
					<div class="col-sm-1">
						<form:input id="percentualParticularMedio" path="percentualParticularMedio" cssClass="form-control"
							placeholder="Percentual de bolsa Particular ensino Medio" />
						<div class="error-validation">
							<form:errors path="percentualParticularMedio"></form:errors>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="cursinho" class="col-sm-2 control-label">Fez
						cursinho?</label>
					<div class="col-sm-5">
						<form:input id="cursinho" path="cursinho" cssClass="form-control"
							placeholder="cursinho" />
						<div class="error-validation">
							<form:errors path="cursinho"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="nomeCursinho" class="col-sm-2 control-label">Nome
						do Cursinho:</label>
					<div class="col-sm-7">
						<form:input id="nomeCursinho" path="nomeCursinho"
							cssClass="form-control" placeholder="nome do Cursinho" />
						<div class="error-validation">
							<form:errors path="nomeCursinho"></form:errors>
						</div>
					</div>
				</div>



<!-- 				<div class="form-group"> -->
<!-- 					<label for="rendaMediaFamilia" class="col-sm-2 control-label">Renda -->
<!-- 						Media da Familia:</label> -->
<!-- 					<div class="col-sm-2"> -->
<%-- 						<form:input id="rendaMediaFamilia" path="rendaMediaFamilia" --%>
<%-- 							cssClass="form-control" placeholder="rendaMediaFamilia" /> --%>
<!-- 						<div class="error-validation"> -->
<%-- 							<form:errors path="rendaMediaFamilia"></form:errors> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->


<!-- 				<div class="form-group"> -->
<!-- 					<label for="rendaMediaPessoa" class="col-sm-2 control-label">Renda -->
<!-- 						Media por Pessoa:</label> -->
<!-- 					<div class="col-sm-2"> -->
<%-- 						<form:input id="rendaMediaPessoa" path="rendaMediaPessoa" --%>
<%-- 							cssClass="form-control" placeholder="rendaMediaPessoa" /> --%>
<!-- 						<div class="error-validation"> -->
<%-- 							<form:errors path="rendaMediaPessoa"></form:errors> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->


				<div class="form-group">
					<label for="bolsista" class="col-sm-2 control-label">Bolsista:</label>
					<div class="col-sm-3">
						<form:input id="bolsista" path="bolsista" cssClass="form-control"
							placeholder="bolsista" />
						<div class="error-validation">
							<form:errors path="bolsista"></form:errors>
						</div>
					</div>
				</div>



				<div class="form-group">
					<label for="tipoBolsa" class="col-sm-2 control-label">Tipo
						de Bolsa:</label>
					<div class="col-sm-5">
						<form:input id="tipoBolsa" path="tipoBolsa"
							cssClass="form-control" placeholder="tipo de Bolsa" />
						<div class="error-validation">
							<form:errors path="tipoBolsa"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="possuiGraduacao" class="col-sm-2 control-label">Possui
						Graduação:</label>
					<div class="col-sm-1">
						<form:input id="possuiGraduacao" path="possuiGraduacao"
							cssClass="form-control" placeholder="possuiGraduacao" />
						<div class="error-validation">
							<form:errors path="possuiGraduacao"></form:errors>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="descricaoGraduacao" class="col-sm-2 control-label">Descrição
						da Graduação:</label>
					<div class="col-sm-10">
						<form:input id="descricaoGraduacao" path="descricaoGraduacao"
							cssClass="form-control" placeholder="descricao da Graduacao" />
						<div class="error-validation">
							<form:errors path="descricaoGraduacao"></form:errors>
						</div>
					</div>
				</div>



				<div class="form-group">
					<label for="justificativa" class="col-sm-2 control-label">Justificativa
						para Bolsa:</label>
					<div class="col-sm-10">
						<form:textarea id="justificativa" path="justificativa"
							cssClass="form-control" placeholder="justificativa" />
						<div class="error-validation">
							<form:errors path="justificativa"></form:errors>
						</div>
					</div>
				</div>
				<jsp:include page="pessoaFamilia.jsp" />
				
	<div class="controls">
		<input name="submit" type="submit" class="btn btn-primary" value="Cadastrar" id="btn-cadastrar"/>
		 <a href="<c:url value="/inscricao/auxilio" ></c:url>" class="btn btn-default" >Cancelar</a>
	</div>


		
</form:form>
		</div>
	</div>




	
</body>



</html>