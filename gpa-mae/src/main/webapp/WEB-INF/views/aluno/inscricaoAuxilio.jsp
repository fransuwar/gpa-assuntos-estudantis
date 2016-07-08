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
	<c:url var="url" value="/aluno/inscricao/editar/${idInscricao}"></c:url>
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

				<form:form id="questionarioAuxilio" role="form"
					modelAttribute="questionarioAuxilioMoradia"
					commandName="questionarioAuxilioMoradia"
					servletRelativeAction="${url }" method="POST"
					cssClass="form-horizontal" enctype="multipart/form-data">

					<input id="idSelecao" name="idSelecao" type="hidden"
						value="${selecao.id}" />


					<h3>Informações Pessoais</h3>
					<section>
						<div class='p-container-set'>
							<div class='p-container unique'>
								<div class='p-header'>
									<div class='p-title'>INFORMAÇÕES</div>
								</div>

								<div class='p-body'>
									<div class='f-container s5'>
										<label class='f-title'>Matrícula</label>
										<div class='f-content'>
											<c:if test="${not empty usuarioAtivo.matricula}">
												<span> ${usuarioAtivo.matricula } </span>
											</c:if>
										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'>Nome:</label>
										<div class='f-content'>
											<c:if test="${not empty usuarioAtivo.nome}">
												<span> ${usuarioAtivo.nome } </span>
											</c:if>
										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'>CPF:</label>
										<div class='f-content'>
											<c:if test="${not empty usuarioAtivo.cpf}">
												<span> ${usuarioAtivo.cpf } </span>
											</c:if>
										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'>Email:</label>
										<div class='f-content'>
											<c:if test="${not empty usuarioAtivo.email}">
												<span> ${usuarioAtivo.email } </span>
											</c:if>
										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'>Telefone</label>
										<div class='f-content'>
											<c:if test="${not empty usuarioAtivo.telefone}">
												<span> ${usuarioAtivo.telefone } </span>
											</c:if>

										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'>Nascimento</label>
										<div class='f-content'>
											<c:if test="${not empty usuarioAtivo.nascimento}">
												<span> <fmt:formatDate pattern="dd/MM/yyyy"
														value="${usuarioAtivo.nascimento }" /></span>
											</c:if>
										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'>*Estas informações estão
											corretas?</label>

										<div class='f-content'>
											<input type="checkbox" name="minhas-informacoes"
												id="minhas-informacoes" required="required" /> Sim, as
											informações estão corretas<br /> <span
												id='nao-minhas-informacoes'>Não, desejo </span><a href="#"
												onclick="novaAba('http://identidadepessoa.intranet/')">alterar
												informações</a>
										</div>
									</div>

								</div>
							</div>
						</div>
					</section>

					<h3>Moradia</h3>
					<section>
						<div class='p-container-set'>
							<div class='p-container first'>
								<div class='p-header'>
									<div class='p-title'>MORA COM</div>
								</div>

								<div class='p-body'>

									<div class='f-container s8'>
										<label class='f-title'>Opções:</label>
										<div class='f-content'>
											<div class="form-group">
												<div id="col-sm-radio">
													<c:forEach items="${moraCom }" var="mora" varStatus="count">
														<c:set var="moraJaCadastrado" value="nao"></c:set>
														<c:forEach
															items="${questionarioAuxilioMoradia.comQuemMora}"
															var="moraCadastrado">
															<c:if test="${mora eq moraCadastrado.descricao}">
																<c:set var="moraJaCadastrado" value="sim"></c:set>
																<input id="comQuemMora${count.index + 1 }"
																	type="checkbox" name="mora" value="${mora}"
																	checked="checked"></input>
															</c:if>
														</c:forEach>
														<c:if test="${moraJaCadastrado eq 'nao'}">
															<input id="comQuemMora${count.index + 1 }"
																type="checkbox" name="mora" value="${mora}"></input>
														</c:if>
														<label for="comQuemMora${count.index + 1 }">${mora.nome }</label>
													</c:forEach>
												</div>
											</div>
											<div id="mora-com-outros" class="form-group" align="left">
												<div class='f-content'>
													<label class='f-title'>Quem são essas pessoas?</label>
													<div class='f-content'>
														<form:input id="comQuemMoraOutros"
															path="comQuemMoraOutros" cssClass="form-control"
															placeholder="Com quem mora essas pessoas ?" />
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
										<label class='f-title'> Mãe: </label>

										<div class='f-content'>
											<form:input id="nomeMae" type="text" path="nomeMae"
												cssClass="form-control" placeholder="Nome da Mãe" />
											<div class="error-validation">
												<form:errors path="nomeMae"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container half'>
										<label class='f-title'> Pai: </label>

										<div class='f-content'>
											<form:input id="nomePai" path="nomePai"
												cssClass="form-control" placeholder="Nome do Pai" />
											<div class="error-validation">
												<form:errors path="nomePai"></form:errors>
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
										<label class='f-title'> Rua/Av: </label>

										<div class='f-content'>
											<form:input id="enderecoOrigem" path="enderecoOrigem"
												cssClass="form-control" placeholder="Rua/Av" />
											<div class="error-validation">
												<form:errors path="enderecoOrigem"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s2'>
										<label class='f-title'> Numero: </label>

										<div class='f-content'>
											<form:input id="numeroOrigem" path="numeroOrigem"
												cssClass="form-control" placeholder="Num" />
											<div class="error-validation">
												<form:errors path="numeroOrigem"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s3'>
										<label class='f-title'> Bairro: </label>

										<div class='f-content'>
											<form:input id="bairroOrigem" path="bairroOrigem"
												cssClass="form-control" placeholder="Bairro" />
											<div class="error-validation">
												<form:errors path="bairroOrigem"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s2'>
										<label class='f-title'> CEP: </label>

										<div class='f-content'>
											<form:input id="cepOrigem" path="cepOrigem"
												data-mask="99999-999" cssClass="form-control"
												placeholder="CEP" />
											<div class="error-validation">
												<form:errors path="cepOrigem"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'> Estado: </label>

										<div class='f-content'>
											<input type="hidden" id="def-estado-origem"
												value="${inscricao.questionarioAuxilioMoradia.estadoOrigem}" />

											<form:select path="estadoOrigem" id="estado-origem"
												cssClass="form-control" cssStyle="font-size:13px">
											</form:select>
											<div class="error-validation">
												<form:errors path="estadoOrigem"></form:errors>
											</div>
										</div>
									</div>


									<div class='f-container s5'>
										<label class='f-title'> Cidade: </label>

										<div class='f-content'>
											<input type="hidden" id="def-cidade-origem"
												value="${inscricao.questionarioAuxilioMoradia.cidadeOrigem}" />

											<form:select id="cidade-origem" path="cidadeOrigem"
												cssClass="form-control" placeholder="Cidade">
											</form:select>
											<div class="error-validation">
												<form:errors path="cidadeOrigem"></form:errors>
											</div>
										</div>
									</div>


									<div class='f-container s5'>
										<label class='f-title'> Complemento: </label>

										<div class='f-content'>
											<form:input id="complementoOrigem" path="complementoOrigem"
												cssClass="form-control" placeholder="Complemento" />
											<div class="error-validation">
												<form:errors path="complementoOrigem"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'> Ponto de referência: </label>

										<div class='f-content'>
											<form:input id="referenciaOrigem" path="referenciaOrigem"
												cssClass="form-control" placeholder="Ponto de Referencia" />
											<div class="error-validation">
												<form:errors path="referenciaOrigem"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'>Telefone:</label>

										<div class='f-content'>
											<form:input id="telefoneOrigem" path="telefoneOrigem"
												data-mask="(99) 9 9999-9999" cssClass="form-control"
												placeholder="(00) 0 0000-0000" />
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

									<div class='f-container s5 hidden' id='div-valor-mensal'>
										<label class='f-title'>Valor mensal do financiamento:</label>

										<div class='f-content'>

											<form:input id="valorMensalFinanciamento"
												path="valorMensalFinanciamento" cssClass="form-control"
												placeholder="Valor mensal do financiamento"
												required="required" />
											<div class="error-validation">
												<form:errors path="valorMensalFinanciamento"></form:errors>
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
									<label class='f-title'><input type="checkbox"
										id="mesmoEndereco" /> Mesmo endereço da residência de origem
									</label><br>
									<div class='f-container s3'>
										<label class='f-title'> Rua/Av: </label>

										<div class='f-content'>
											<form:input id="endereco" path="endereco"
												cssClass="form-control" placeholder="Rua/Av" />
											<div class="error-validation">
												<form:errors path="endereco"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s2'>
										<label class='f-title'> Numero: </label>

										<div class='f-content'>
											<form:input id="numero" path="numero" cssClass="form-control"
												placeholder="Num" />
											<div class="error-validation">
												<form:errors path="numero"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s3'>
										<label class='f-title'> Bairro: </label>

										<div class='f-content'>
											<form:input id="bairro" path="bairro" cssClass="form-control"
												placeholder="Bairro" />
											<div class="error-validation">
												<form:errors path="bairro"></form:errors>
											</div>
										</div>
									</div>



									<div class='f-container s2'>
										<label class='f-title'> CEP: </label>

										<div class='f-content'>
											<form:input id="cep" path="cep" data-mask="99999-999"
												cssClass="form-control" placeholder="CEP" />
											<div class="error-validation">
												<form:errors path="cep"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'> Estado: </label>

										<div class='f-content'>
											<input type="hidden" id="def-estado"
												value="${inscricao.questionarioAuxilioMoradia.estado}" />
											<form:select path="estado" id="estado-endereco"
												cssClass="form-control" cssStyle="font-size:13px">
											</form:select>
											<div class="error-validation">
												<form:errors path="estado"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s5'>
										<label class='f-title'> Cidade: </label>

										<div class='f-content'>
											<input type="hidden" id="def-cidade"
												value="${inscricao.questionarioAuxilioMoradia.cidade}" />
											<form:select id="cidade-endereco" path="cidade"
												cssClass="form-control" placeholder="Cidade">
											</form:select>
											<div class="error-validation">
												<form:errors path="cidade"></form:errors>
											</div>
										</div>
									</div>



									<div class='f-container s5'>
										<label class='f-title'> Complemento: </label>

										<div class='f-content'>
											<form:input id="complemento" path="complemento"
												cssClass="form-control" placeholder="Complemento" />
											<div class="error-validation">
												<form:errors path="complemento"></form:errors>
											</div>
										</div>
									</div>





									<div class='f-container s5'>
										<label class='f-title'> Ponto de referência: </label>

										<div class='f-content'>
											<form:input id="referencia" path="referencia"
												cssClass="form-control" placeholder="Ponto de Referencia" />
											<div class="error-validation">
												<form:errors path="referencia"></form:errors>
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
									<button id="abrirFormPropRural" type="button"
										class="btn btn-primary" data-toggle="modal"
										data-target="#formPropRural">Adicionar</button>
									<div id="listaPropRurais">

										<div id="propRural" class="panel-body hidden">
											<table class="table table-striped table-hover">
												<thead>
													<tr>
														<th class="form-">Parentesco:</th>
														<th class="form-group">Área da propriedade:</th>
														<th class="form-group">Cidade:</th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>

											<div class="modal-footer">
												<button type="button"
													class="btn btn-primary editarPropRural" data-toggle="modal"
													data-target="#editarPropRural">Editar</button>
												<button name="botaoRmv" type="button"
													class="btn btn-danger rmvPropRural">Remover</button>
											</div>
										</div>

									</div>


									<!-- Modal ADICIONAR propriedade rural -->
									<div class="modal fade" id="formPropRural" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel">
										<div class="modal-dialog modal-lg" role="document">
											<div class="modal-content panel panel-primary">
												<div class="modal-header panel-heading">
													<h4 class="modal-title" id="myModalLabel">Nova
														Propriedade Rural</h4>
												</div>
												<div class="modal-body">
													<div class='f-container s4'>
														<label class='f-title'> Parentesco do
															proprietário: </label>

														<div class='f-content'>
															<form:select path="" id="grauParentescoImovelRural"
																cssClass="form-control">
																<form:option value="" label="Selecione o Grau" />
																<form:options items="${grauParentesco}" itemLabel="nome" />
															</form:select>
															<p id="prParentescoValidate" class="hidden text-danger">
																Campo Obrigatório</p>
														</div>
													</div>

													<div class='f-container s3'>
														<label class='f-title'> Área da propriedade: </label>

														<div class='f-content'>
															<form:input id="areaPropriedadeRural" path=""
																cssClass="form-control"
																placeholder="Área da Propriedade" />
															<p id="prAreaValidate" class="hidden text-danger">
																Campo Obrigatório</p>

														</div>
													</div>

													<div class='f-container s3'>
														<label class='f-title'>Cidade: </label>

														<div class='f-content'>
															<form:input id="cidadeEstadoImovelRural" path=""
																cssClass="form-control"
																placeholder="Cidade e Estado do Imovel Rural" />
															<p id="prCidadeValidate" class="hidden text-danger">
																Campo Obrigatório</p>
														</div>
													</div>

													<div id="outroGrauSelectImovelRural"
														class="hidden f-container s3">
														<label for="outroGrauParentescoImovelRural"
															id="labelOutroGrauParentescoImovelRural" class="f-title">
															Especifique o grau de parentesco:</label>
														<div class="f-content">
															<form:input id="outroGrauParentescoImovelRural" path=""
																cssClass="form-control"
																placeholder="Outro grau de parentesco" />
															<p id="prOutroValidate" class="hidden text-danger">
																Campo Obrigatório</p>
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button id="addPropRural" type="submit"
														class="btn btn-success" data-dismiss="modal">Adicionar
													</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">Cancelar</button>

												</div>
											</div>
										</div>
									</div>

									<!-- Modal EDITAR propriedade rural -->
									<div class="modal fade" id="editarPropRural" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel">
										<div class="modal-dialog modal-lg" role="document">
											<div class="modal-content panel panel-primary">
												<div class="modal-header panel-heading">
													<h4 class="modal-title" id="myModalLabel">Editar
														Propriedade Rural</h4>
												</div>
												<div class="modal-body">
													<div class='f-container s4'>
														<label class='f-title'> Parentesco do
															proprietário: </label>

														<div class='f-content'>
															<form:select path="" id="grauParentescoImovelRuralEditar"
																cssClass="form-control">
																<form:option value="" label="Selecione o Grau" />
																<form:options items="${grauParentesco}" itemLabel="nome" />
															</form:select>
															<p id="prParentescoValidateEditar"
																class="hidden text-danger">Campo Obrigatório</p>
														</div>
													</div>

													<div class='f-container s3'>
														<label class='f-title'> Área da propriedade: </label>

														<div class='f-content'>
															<form:input id="areaPropriedadeRuralEditar" path=""
																cssClass="form-control"
																placeholder="Área da Propriedade" />
															<p id="prAreaValidateEditar" class="hidden text-danger">
																Campo Obrigatório</p>

														</div>
													</div>

													<div class='f-container s3'>
														<label class='f-title'>Cidade: </label>

														<div class='f-content'>
															<form:input id="cidadeEstadoImovelRuralEditar" path=""
																cssClass="form-control"
																placeholder="Cidade e Estado do Imovel Rural" />
															<p id="prCidadeValidateEditar" class="hidden text-danger">
																Campo Obrigatório</p>
														</div>
													</div>

													<div id="outroGrauSelectImovelRuralEditar"
														class="f-container s3 hidden">
														<label for="outroGrauParentescoImovelRuralEditar"
															id="labelOutroGrauParentescoImovelRural" class="f-title">
															Especifique o grau de parentesco:</label>
														<div class="f-content">
															<form:input id="outroGrauParentescoImovelRuralEditar"
																path="" cssClass="form-control"
																placeholder="Outro grau de parentesco" />
															<p id="prOutroValidateEditar" class="hidden text-danger">
																Campo Obrigatório</p>
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button id="confirmarEdicaoPropRural" type="submit"
														class="btn btn-success" data-dismiss="modal">Confirmar
													</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">Cancelar</button>

												</div>
											</div>
										</div>
									</div>


								</div>
								<script type="text/javascript">
									var listaGlobalPropRurais = [];
									<c:if test="${not empty questionarioAuxilioMoradia.propRural}">
									listaGlobalPropRurais = ${questionarioAuxilioMoradia.propRural};
									</c:if>
								</script>
							</div>
							<div class='p-container'>
								<div class='p-header'>
									<div class='p-title'>BENS MÓVEIS</div>
								</div>

								<div class='p-body'>
									<button id="abrirFormBemMovel" type="button"
										class="btn btn-primary" data-toggle="modal"
										data-target="#formBemMovel">Adicionar</button>
									<div id="listaBensMoveis">

										<div id="bemMovel" class="panel-body hidden">
											<table class="table table-striped table-hover">
												<thead>
													<tr>
														<th class="form-">Parentesco:</th>
														<th class="form-group">Finalidade:</th>
														<th class="form-group">Veiculo:</th>
													</tr>
												</thead>
												<tbody>

												</tbody>
											</table>

											<div class="modal-footer">
												<button id="abrirFormPropRuralEditar" type="button"
													class="btn btn-primary editarBemMovel" data-toggle="modal"
													data-target="#editarBemMovel">Editar</button>
												<button name="botaoRmv" type="button"
													class="btn btn-danger rmvBemMovel">Remover</button>
											</div>
										</div>

									</div>


									<!-- Modal ADICIONAR bem movel -->
									<div class="modal fade" id="formBemMovel" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel">
										<div class="modal-dialog modal-lg" role="document">
											<div class="modal-content panel panel-primary">
												<div class="modal-header panel-heading">
													<h4 class="modal-title" id="myModalLabel">Novo Bem
														Movel</h4>
												</div>
												<div class="modal-body">
													<div class='f-container s4'>
														<label class='f-title'> Parentesco do
															proprietário: </label>

														<div class='f-content'>
															<form:select path="" id="grauParentescoVeiculos"
																cssClass="form-control">
																<form:option value="" label="Selecione o Grau" />
																<form:options items="${grauParentesco}" itemLabel="nome" />
															</form:select>
															<p id="bmParentescoValidate" class="hidden text-danger">
																Campo Obrigatório</p>
														</div>
													</div>

													<div class='f-container s3'>
														<label class='f-title'> Finalidade: </label>

														<div class='f-content'>
															<form:select path="" id="finalidadeVeiculo"
																cssClass="form-control">
																<form:option value="">Selecione a Finalidade</form:option>
																<form:options items="${finalidadeVeiculo}"
																	itemLabel="nome" />
															</form:select>
															<p id="bmFinalidadeValidate" class="hidden text-danger">
																Campo Obrigatório</p>

														</div>
													</div>

													<div class='f-container s3'>
														<label class='f-title'>Veiculo: </label>

														<div class='f-content'>
															<form:input id="veiculo" path="" cssClass="form-control"
																placeholder="Tipo de veículo" />
															<p id="bmVeiculoValidate" class="hidden text-danger">
																Campo Obrigatório</p>
														</div>
													</div>

													<div id="outroGrauSelectBemMovel"
														class="hidden f-container s3">
														<label for="outroGrauParentescoVeiculos"
															id="labelOutroGrauParentescoVeiculos" class="f-title">
															Especifique o grau de parentesco:</label>
														<div class="f-content">
															<form:input id="outroGrauParentescoVeiculos" path=""
																cssClass="form-control"
																placeholder="Outro grau de parentesco" />
															<p id="bmOutroValidate" class="hidden text-danger">
																Campo Obrigatório</p>
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button id="addBemMovel" type="submit"
														class="btn btn-success" data-dismiss="modal">Adicionar
													</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">Cancelar</button>

												</div>
											</div>
										</div>
									</div>

									<!-- Modal EDITAR bem movel -->
									<div class="modal fade" id="editarBemMovel" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel">
										<div class="modal-dialog modal-lg" role="document">
											<div class="modal-content panel panel-primary">
												<div class="modal-header panel-heading">
													<h4 class="modal-title" id="myModalLabel">Editar Bem
														Movel</h4>
												</div>
												<div class="modal-body">
													<div class='f-container s4'>
														<label class='f-title'> Parentesco do
															proprietário: </label>

														<div class='f-content'>
															<form:select path="" id="grauParentescoVeiculosEditar"
																cssClass="form-control">
																<form:option value="" label="Selecione o Grau" />
																<form:options items="${grauParentesco}" itemLabel="nome" />
															</form:select>
															<p id="bmParentescoValidateEditar"
																class="hidden text-danger">Campo Obrigatório</p>
														</div>
													</div>

													<div class='f-container s3'>
														<label class='f-title'> Finalidade: </label>

														<div class='f-content'>
															<form:select path="" id="finalidadeVeiculoEditar"
																cssClass="form-control">
																<form:option value="">Selecione a Finalidade</form:option>
																<form:options items="${finalidadeVeiculo}"
																	itemLabel="nome" />
															</form:select>
															<p id="bmFinalidadeValidateEditar"
																class="hidden text-danger">Campo Obrigatório</p>

														</div>
													</div>

													<div class='f-container s3'>
														<label class='f-title'>Veículo: </label>

														<div class='f-content'>
															<form:input id="veiculoEditar" path=""
																cssClass="form-control" placeholder="Tipo de Veículo" />
															<p id="bmVeiculoValidateEditar"
																class="hidden text-danger">Campo Obrigatório</p>
														</div>
													</div>

													<div id="outroGrauSelectBemMovelEditar"
														class="hidden f-container s3">
														<label for="outroGrauParentescoVeiculosEditar"
															id="labelOutroGrauParentescoVeiculos" class="f-title"
															style="display: none;">Especifique o grau de
															parentesco:</label>
														<div class="f-content">
															<form:input id="outroGrauParentescoVeiculosEditar"
																path="" cssClass="form-control"
																placeholder="Outro grau de parentesco"
																style="display:none;" />
															<p id="bmOutroValidateEditar" class="hidden text-danger">
																Campo Obrigatório</p>
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button id="confirmarEdicaoBemMovel" type="submit"
														class="btn btn-success" data-dismiss="modal">Confirmar
													</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">Cancelar</button>

												</div>
											</div>
										</div>
									</div>


								</div>
								<script type="text/javascript">
									var listaGlobalBensMoveis = [];
									<c:if test="${not empty questionarioAuxilioMoradia.bemMovel}">
									listaGlobalBensMoveis = ${questionarioAuxilioMoradia.bemMovel};
									</c:if>
								</script>
							</div>



						</div>
					</section>


					<h3>Historico Escolar</h3>
					<section>
						<div class='p-container-set'>
							<div class='p-container first'>
								<div class='p-header'>
									<div class='p-title'>ENSINO FUNDAMENTAL</div>
								</div>

								<div class='p-body'>
									<div class='f-container s5'>
										<label class='f-title'>Tipo de escola:</label>

										<div class='f-content'>
											<form:select path="ensinoFundamental" id="ensinoFundamental"
												cssClass="form-control" cssStyle="font-size:13px;">
												<form:option value="" label="Selecione o Tipo" />
												<form:options items="${tipoEnsinoFundamental}"
													itemLabel="nome" />
											</form:select>
											<div class="error-validation">
												<form:errors path="ensinoFundamental"></form:errors>
											</div>
										</div>
									</div>
									<div class='f-container s5'
										id="percentualParticularFundamental">
										<label class='f-title'>Percentual de Bolsa:</label>

										<div class='f-content'>
											<div class="input-group">
												<form:input id="percentualParticularFundamental"
													path="percentualParticularFundamental"
													cssClass="form-control" data-mask="000"
													placeholder="Digite um valor maior que 0 e menor ou igual 100"
													min="0.1" max="100" />
												<div class="input-group-addon">%</div>
											</div>

										</div>
									</div>
								</div>
							</div>
							<div class='p-container last'>
								<div class='p-header'>
									<div class='p-title'>ENSINO MÉDIO</div>
								</div>

								<div class='p-body'>

									<div class='f-container s5'>
										<label class='f-title'> Tipo de escola: </label>

										<div class='f-content'>
											<form:select path="ensinoMedio" id="ensinoMedio"
												cssClass="form-control" cssStyle="font-size:13px;">
												<form:option value="" label="Selecione o Tipo" />
												<form:options items="${tipoEnsinoMedio}" itemLabel="nome" />
											</form:select>
											<div class="error-validation">
												<form:errors path="ensinoMedio"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s5' id="percentualParticularMedio">
										<label class='f-title'> Percentual de Bolsa: </label>

										<div class='f-content'>
											<div class="input-group">
												<form:input id="percentualParticularMedio"
													path="percentualParticularMedio" cssClass="form-control"
													data-mask="000"
													placeholder="Digite um valor entre 0.1 e 100" min="0.1"
													max="100" />
												<div class="input-group-addon">%</div>
											</div>
											<div class="error-validation">
												<form:errors path="percentualParticularMedio"></form:errors>
											</div>
										</div>
									</div>

									<br />

									<div class='f-container s10'>
										<label class='f-title'><form:checkbox id="cursinho"
												path="cursinho" /> Fez cursinho pré-vestibular? </label>

										<div class='f-content'>
											<div class="error-validation">
												<form:errors path="cursinho"></form:errors>
											</div>
										</div>
									</div>

									<div class='f-container s5' id="nome_cursinho">
										<label class='f-title'> Nome do cursinho: </label>

										<div class='f-content'>
											<form:input id="nomeCursinho" path="nomeCursinho"
												cssClass="form-control" required="required"
												placeholder="Nome do cursinho" />
											<div class="error-validation">
												<form:errors path="nomeCursinho"></form:errors>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>

					</section>

					<h3>Situação Socioeconômica</h3>
					<section>
						<div class='p-container unique'>
							<div class='p-header'>
								<div class='p-title'>SITUAÇÃO SOCIOECONÔMICA</div>
							</div>

							<div class='p-body'>
								<div class="">
									<jsp:include page="pessoaFamilia.jsp" />
									<div class="error-validation">
										<form:errors path="pessoas"></form:errors>
									</div>
								</div>
							</div>
						</div>
					</section>

					<h3>Outras informações</h3>
					<section>

						<div class='p-container unique'>
							<div class='p-header'>
								<div class='p-title'>OUTRAS INFORMAÇÕES</div>
							</div>

							<div class='p-body'>
								<div class='f-container s5'>
									<label class='f-title'> Bolsista UFC: <form:select
											required="required" path="bolsistaUfc" id="bolsistaUfc"
											class="form-control">
											<option value="">Selecione...</option>

											<c:choose>
												<c:when
													test="${inscricao.questionarioAuxilioMoradia.bolsistaUfc}">
													<option selected value="${true}">Sim</option>
												</c:when>
												<c:otherwise>
													<option value="${true}">Sim</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when
													test="${not inscricao.questionarioAuxilioMoradia.bolsistaUfc}">
													<option selected value="${false}">Não</option>
												</c:when>
												<c:otherwise>
													<option value="${false}">Não</option>
												</c:otherwise>
											</c:choose>
										</form:select>

									</label>
									<div class='f-content'>
										<div class="error-validation">
											<form:errors path="bolsistaUfc"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<div id="descricao_bolsa">
										<label class='f-title'> Descrição da Bolsa: </label>

										<div class='f-content'>
											<form:input id="descricaoBolsa" required="required"
												path="descricaoBolsa" cssClass="form-control"
												placeholder="Descrição Bolsa" />
											<div class="error-validation">
												<form:errors path="descricaoBolsa"></form:errors>
											</div>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<label class='f-title'> Possui Graduação: <form:select
											required="required" path="graduacao" id="graduacao"
											class="form-control">
											<option value="">Selecione...</option>

											<c:choose>
												<c:when
													test="${inscricao.questionarioAuxilioMoradia.graduacao}">
													<option selected value="${true}">Sim</option>
												</c:when>
												<c:otherwise>
													<option value="${true}">Sim</option>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when
													test="${not inscricao.questionarioAuxilioMoradia.graduacao}">
													<option selected value="${false}">Não</option>
												</c:when>
												<c:otherwise>
													<option value="${false}">Não</option>
												</c:otherwise>
											</c:choose>
										</form:select>

									</label>

									<div class='f-content'>

										<div class="error-validation">
											<form:errors path="graduacao"></form:errors>
										</div>
									</div>
								</div>

								<div class='f-container s5'>
									<div id="descricao_graduacao">
										<label class='f-title'> Descrição da Graduação: </label>

										<div class='f-content'>
											<form:input id="descricaoGraduacao" required="required"
												path="descricaoGraduacao" cssClass="form-control"
												placeholder="Descricao da Graduacao" />
											<div class="error-validation">
												<form:errors path="descricaoGraduacao"></form:errors>
											</div>
										</div>
									</div>
								</div>

								<div class='f-container'>
									<label class='f-title'>Foto (3x4):</label>
									<div class='f-content'>
										<img class="imagem-inscricao-pequena"
											src="<c:url value = "/inscricao/detalhes/fotoAluno/${inscricao.id}"></c:url>" />
										<input id="input-foto3x4" type="file" name="fileFoto"
											accept=".jpg, .png" />
										<div class="col-sm-8 error-validation">
											<span id="span-error-foto"></span>
										</div>
									</div>
								</div>


							</div>
						</div>

					</section>

					<h3>Justificativa</h3>
					<section>

						<div class='p-container unique'>
							<div class='p-header'>
								<div class='p-title'>JUSTIFICATIVA</div>
							</div>

							<div class='p-body'>
								<div class='f-container s10'>
									<label class='f-title'>Justificativa para Bolsa</label>

									<div class='f-content'>
										<form:textarea id="justificativa" path="justificativa"
											cssClass="form-control s10" placeholder="Justificativa"
											rows="10" />
										<div class="error-validation">
											<form:errors path="justificativa"></form:errors>
										</div>
									</div>
								</div>
							</div>
						</div>
					</section>
				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
<!-- 
<script type="text/javascript">
new StateCityLib().init("estado-endereco", "cidade-endereco");
new StateCityLib().init("estado-origem", "cidade-origem");

var defEstadoOrigem = $("#def-estado-origem").val();
var defCidadeOrigem = $("#def-cidade-origem").val();
var defEstado = $("#def-estado").val();
var defCidade = $("#def-cidade").val();

var $estadoOrigem = $("#estado-origem");
var $estadoEndereco = $("#estado-endereco");
var $cidadeOrigem = $("#cidade-origem");
var $cidadeEndereco = $("#cidade-endereco");

if(defEstadoOrigem.length > 0){
	$estadoOrigem.val(defEstadoOrigem);
	$estadoOrigem.change();
}

if(defEstado.length > 0){
	$estadoEndereco.val(defEstado);
	$estadoEndereco.change();
}

if(defCidadeOrigem.length > 0){
	$cidadeOrigem.val(defCidadeOrigem);
	$cidadeOrigem.change();
}

if(defCidade.length > 0){
	$cidadeEndereco.val(defCidade);
	$cidadeEndereco.change();
}
</script> -->

</html>