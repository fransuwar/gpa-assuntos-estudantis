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
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />
	<div class="container">
		<div align="left">
			<div class="form" align="center">
				<h2>Programa de Auxílio Moradia</h2>
				<div class="panel-body"
					style="align: center; color: #1a242f; text-align: center;">
					<table class="table table-striped table-hover"
						id="table-visualiza-info-auxilio">
						<thead>
							<tr id="tr-table-visualiza-info-auxilio">
								<th id="td-table-visualiza-info-auxilio">Tipo da Bolsa</th>
								<th id="td-table-visualiza-info-auxilio">Ano</th>
								<th id="td-table-visualiza-info-auxilio">Vagas</th>
								<th id="td-table-visualiza-info-auxilio">Período de
									Inscrição</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${selecao.tipoSelecao.nome }</td>
								<td>${selecao.ano }</td>
								<td>${selecao.quantidadeVagas }</td>
								<td><fmt:formatDate value="${selecao.dataInicio }"
										pattern="dd/MM/yyyy" /> à <fmt:formatDate
										value="${selecao.dataTermino }" pattern="dd/MM/yyyy" /></td>
							</tr>
						</tbody>
					</table>
				</div>



				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Informações Pessoais</h3>
					</div>
					<div class="panel-body text-align-left">
						<c:if test="${not empty usuarioAtivo.matricula}">
							<label class='title'>Matrícula: </label>
							<span> ${usuarioAtivo.matricula } </span>
						</c:if>

						<c:if test="${not empty usuarioAtivo.nome}">
							<label class='title'>Nome: </label>
							<span> ${usuarioAtivo.nome } </span>
						</c:if>

						<c:if test="${not empty usuarioAtivo.cpf}">
							<label class='title'>CPF: </label>
							<span> ${usuarioAtivo.cpf } </span>
						</c:if>

						<c:if test="${not empty usuarioAtivo.email}">
							<label class='title'>Email: </label>
							<span> ${usuarioAtivo.email } </span>
						</c:if>

						<c:if test="${not empty usuarioAtivo.telefone}">
							<label class='title'>Telefone: </label>
							<span> ${usuarioAtivo.telefone } </span>
						</c:if>

						<c:if test="${not empty usuarioAtivo.nascimento}">
							<label class='title'>Nascimento: </label>
							<span> <fmt:formatDate pattern="dd/MM/yyyy"
									value="${usuarioAtivo.nascimento }" /></span>
						</c:if>
						<br /> <br /> <label class='title'>*Estas são suas
							informações?</label> <input type="checkbox" name="minhas-informacoes"
							id="minhas-informacoes" required="required"
							onclick="selecionarInformacoes()" /> Sim, as informações estão
						corretas<br /> <span id='nao-minhas-informacoes'>Não,
							desejo <a href="#"
							onclick="novaAba('http://identidadepessoa.intranet/')">alterar
								informações</a>
						</span>


					</div>
				</div>

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

					<input id="idSelecao" name="idSelecao" type="hidden"
						value="${selecao.id}" />


					<div class='p-container-set'>

						<div class='p-container first'>
							<div class='p-header'>
								<div class='p-title'>MORA COM</div>
							</div>

							<div class='p-body'>

								<div class='f-container s8'>
									<label class='f-title'>*Opções:</label>
									<div class='f-content'>
										<div class="form-group">
											<div id="col-sm-radio">
												<c:forEach items="${moraCom }" var="mora" varStatus="count">
													<input id="comQuemMora${count.index + 1 }" type="checkbox"
														name="mora" value="${mora}" />
													<label for="comQuemMora${count.index + 1 }">${mora.nome }</label>
												</c:forEach>
											</div>
										</div>
										<div id="mora-com-outros" class="form-group" align="left">
											<div class='f-content'>
												<label class='f-title'>Quem são essas pessoas?</label>
												<div class='f-content'>
													<form:input id="comQuemMoraOutros" path="comQuemMoraOutros"
														cssClass="form-control"
														placeholder="Com quem mora essas pessoas ?"
														required="required" />
													<div class="error-validation">
														<form:errors path="comQuemMoraOutros"></form:errors>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>

						<div class='p-container'>
							<div class='p-header'>
								<div class='p-title'>PAIS</div>
							</div>

							<div class='p-body'>
								<div class='f-container half'>
									<label class='f-title'> *Mãe: </label>

									<div class='f-content'>
										<form:input id="nomeMae" type="text" path="nomeMae"
											cssClass="form-control" placeholder="Nome da Mãe"
											required="required" />
										<div class="error-validation">
											<form:errors path="nomeMae"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container half'>
									<label class='f-title'> *Pai: </label>

									<div class='f-content'>
										<form:input id="nomePai" path="nomePai"
											cssClass="form-control" placeholder="Nome do Pai"
											required="required" />
										<div class="error-validation">
											<form:errors path="nomePai"></form:errors>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class='p-container'>
							<div class='p-header'>
								<div class='p-title'>RESIDÊNCIA ATUAL</div>
							</div>

							<div class='p-body'>
								<div class='f-container s3'>
									<label class='f-title'> *Rua/Av: </label>

									<div class='f-content'>
										<form:input id="endereco" path="endereco"
											cssClass="form-control" placeholder="Rua da sede do curso"
											required="required" />
										<div class="error-validation">
											<form:errors path="endereco"></form:errors>
										</div>
									</div>
								</div>
								
								<div class='f-container s2'>
									<label class='f-title'> *Numero: </label>

									<div class='f-content'>
										<form:input id="numero" path="numero" cssClass="form-control"
											data-mask="999999" placeholder="Num" required="required" />
										<div class="error-validation">
											<form:errors path="numero"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s3'>
									<label class='f-title'> *Bairro: </label>

									<div class='f-content'>
										<form:input id="bairro" path="bairro" cssClass="form-control"
											placeholder="Bairro" required="required" />
										<div class="error-validation">
											<form:errors path="bairro"></form:errors>
										</div>
									</div>
								</div>

								

								<div class='f-container s2'>
									<label class='f-title'> *CEP: </label>

									<div class='f-content'>
										<form:input id="cep" path="cep" data-mask="99999-999"
											cssClass="form-control" placeholder="CEP" required="required" />
										<div class="error-validation">
											<form:errors path="cep"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<label class='f-title'> *Cidade: </label>

									<div class='f-content'>
										<form:input id="cidade" path="cidade" cssClass="form-control"
											placeholder="Cidade" required="required" />
										<div class="error-validation">
											<form:errors path="cidade"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<label class='f-title'> *Estado: </label>

									<div class='f-content'>
										<form:select path="estado" id="estado" cssClass="form-control"
											cssStyle="font-size:13px" required="required">
											<form:option value="">Selecione Estado</form:option>
											<form:options items="${estado}" itemLabel="nome" />
										</form:select>
										<div class="error-validation">
											<form:errors path="estado"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<label class='f-title'> *Complemento: </label>

									<div class='f-content'>
										<form:input id="complemento" path="complemento"
											cssClass="form-control"
											placeholder="Complemento da sede do curso"
											required="required" />
										<div class="error-validation">
											<form:errors path="complemento"></form:errors>
										</div>
									</div>
								</div>





								<div class='f-container s5'>
									<label class='f-title'> *Ponto de referência: </label>

									<div class='f-content'>
										<form:input id="referencia" path="referencia"
											cssClass="form-control" placeholder="Ponto de Referencia"
											required="required" />
										<div class="error-validation">
											<form:errors path="referencia"></form:errors>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class='p-container'>
							<div class='p-header'>
								<div class='p-title'>RESIDÊNCIA DE ORIGEM</div>
							</div>

							<div class='p-body'>
								<div class='f-container s3'>
									<label class='f-title'> *Rua/Av: </label>

									<div class='f-content'>
										<form:input id="enderecoOrigem" path="enderecoOrigem"
											cssClass="form-control" placeholder="Rua da sede do curso"
											required="required" />
										<div class="error-validation">
											<form:errors path="enderecoOrigem"></form:errors>
										</div>
									</div>
								</div>
								
								<div class='f-container s2'>
									<label class='f-title'> *Numero: </label>

									<div class='f-content'>
										<form:input id="numeroOrigem" path="numeroOrigem"
											cssClass="form-control" data-mask="999999" placeholder="Num"
											required="required" />
										<div class="error-validation">
											<form:errors path="numeroOrigem"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s3'>
									<label class='f-title'> *Bairro: </label>

									<div class='f-content'>
										<form:input id="bairroOrigem" path="bairroOrigem"
											cssClass="form-control" placeholder="Bairro"
											required="required" />
										<div class="error-validation">
											<form:errors path="bairroOrigem"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s2'>
									<label class='f-title'> *CEP: </label>

									<div class='f-content'>
										<form:input id="cepOrigem" path="cepOrigem"
											data-mask="99999-999" cssClass="form-control"
											placeholder="CEP" required="required" />
										<div class="error-validation">
											<form:errors path="cepOrigem"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<label class='f-title'> *Cidade: </label>

									<div class='f-content'>
										<form:input id="cidadeOrigem" path="cidadeOrigem"
												cssClass="form-control" placeholder="Cidade"
												required="required" />
											<div class="error-validation">
												<form:errors path="cidadeOrigem"></form:errors>
											</div>
									</div>
								</div>

								<div class='f-container s5'>
									<label class='f-title'> *Estado: </label>

									<div class='f-content'>
										<form:select path="estadoOrigem" id="estadoOrigem"
											cssClass="form-control" cssStyle="font-size:13px"
											required="required">
											<form:option value="">Selecione Estado</form:option>
											<form:options items="${estado}" itemLabel="nome" />
										</form:select>
										<div class="error-validation">
											<form:errors path="estadoOrigem"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<label class='f-title'> Complemento: </label>

									<div class='f-content'>
										<form:input id="complementoOrigem" path="complementoOrigem"
											cssClass="form-control"
											placeholder="Complemento da sede do curso" />
										<div class="error-validation">
											<form:errors path="complementoOrigem"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<label class='f-title'> *Ponto de referência: </label>

									<div class='f-content'>
										<form:input id="referenciaOrigem" path="referenciaOrigem"
											cssClass="form-control" placeholder="Ponto de Referencia"
											required="required" />
										<div class="error-validation">
											<form:errors path="referenciaOrigem"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<label class='f-title'>*Telefone:</label>

									<div class='f-content'>
										<form:input id="telefoneOrigem" path="telefoneOrigem"
											data-mask="(99) 9 9999-9999" cssClass="form-control"
											placeholder="(00) 0 0000-0000" required="required" />
										<div class="error-validation">
											<form:errors path="telefoneOrigem"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<label class='f-title'>Situação do Imóvel:</label>

									<div class='f-content'>
										<form:select path="situacaoImovel" id="situacaoImovel"
											cssClass="form-control" cssStyle="font-size:14px">
											<form:option value="">Situação Imóvel</form:option>
											<form:options items="${situacaoImovel}" itemLabel="nome" />
										</form:select>
										<div class="error-validation">
											<form:errors path="situacaoImovel"></form:errors>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class='p-container'>
							<div class='p-header'>
								<div class='p-title'>PROPRIEDADES RURAIS</div>
							</div>

							<div class='p-body'>
								<div class='f-container s4'>
									<label class='f-title'> Parentesco do proprietário: </label>

									<div class='f-content'>
										<form:select path="grauParentescoImovelRural"
											id="grauParentescoImovelRural" cssClass="form-control">
											<form:option value="" label="Selecione o Grau" />
											<form:options items="${grauParentescoImovelRural}"
												itemLabel="nome" />
										</form:select>
										<div class="error-validation">
											<form:errors path="grauParentescoImovelRural"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s3'>
									<label class='f-title'> Área da propriedade: </label>

									<div class='f-content'>
										<form:input id="areaPropriedadeRural"
											path="areaPropriedadeRural" cssClass="form-control"
											placeholder="Área da Propriedade" />
										<div class="error-validation">
											<form:errors path="areaPropriedadeRural"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s3'>
									<label class='f-title'>Cidade: </label>

									<div class='f-content'>
										<form:input id="cidadeEstadoImovelRural"
											path="cidadeEstadoImovelRural" cssClass="form-control"
											placeholder="Cidade e Estado do Imovel Rural" />
										<div class="error-validation">
											<form:errors path="cidadeEstadoImovelRural"></form:errors>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class='p-container last'>
							<div class='p-header'>
								<div class='p-title'>BENS MÓVEIS</div>
							</div>

							<div class='p-body'>
								<div class='f-container s4'>
									<label class='f-title'> Parentesco do proprietário: </label>

									<div class='f-content'>
										<form:select path="grauParentescoVeiculos"
											id="grauParentescoVeiculos" cssClass="form-control">
											<form:option value="" label="Selecione o Grau" />
											<form:options items="${grauParentescoVeiculos}"
												itemLabel="nome" />
										</form:select>
										<div class="error-validation">
											<form:errors path="grauParentescoVeiculos"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s3'>
									<label class='f-title'> Veículo: </label>

									<div class='f-content'>
										<form:input id="veiculo" path="veiculo"
											cssClass="form-control" placeholder="Tipo do veículo" />
										<div class="error-validation">
											<form:errors path="veiculo"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s3'>
									<label class='f-title'>Finalidade do veículo: </label>

									<div class='f-content'>
										<form:select path="finalidadeVeiculo" id="finalidadeVeiculo"
											cssClass="form-control">
											<form:option value="">Selecione a Finalidade</form:option>
											<form:options items="${finalidadeVeiculo}" itemLabel="nome" />
										</form:select>
										<div class="error-validation">
											<form:errors path="finalidadeVeiculo"></form:errors>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<br />

				</form:form>

			</div>

		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>

</html>