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
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Detalhes Inscrição Auxilio Moradia</title>
</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<input id="ativar-aba-entrevista" name="ativar-aba-entrevista"
		type="hidden" value="${ativarAbaEntrevista }" />
	<div class="container" align="center">
		<ul class="nav nav-tabs">
			<li id="aba-inscricao"><a href="#inscricao-tab"
				data-toggle="tab">Inscrição<i class="fa"></i>
			</a></li>
			<li id="aba-documentos"><a href="#documentos-tab"
				data-toggle="tab">Documentos <i class="fa"></i>
			</a></li>
			<sec:authorize access="hasAnyRole('DOCENTE','STA')">
				<li id="aba-entrevista"><a href="#entrevista-tab"
					data-toggle="tab">Entrevista <i class="fa"></i>
				</a></li>
				<li id="aba-visita"><a href="#visita-tab" data-toggle="tab">Visita
						<i class="fa"></i>
				</a></li>
			</sec:authorize>
		</ul>
		<div class="tab-content">
			<div class="tab-pane" id="inscricao-tab">
				<div class="panel panel-default panel-primary">

					<div class="panel-heading">
						<h3 class="panel-title">
							Detalhes da Inscrição de Auxílio Moradia <span
								class="direita clicavel"> <i
								class="glyphicon glyphicon-chevron-up"></i>
							</span>

							<sec:authorize access="hasAnyRole('DISCENTE')">
								<c:if test="${!esconderBotoes}">
									<a id="editarInscricao"
										href="<c:url value="/aluno/inscricao/editar/${inscricao.id }" ></c:url>">
										<button class="btn btn-info btn-sm" title="Editar Inscrição">
											<span class="glyphicon glyphicon-pencil"></span>
										</button>
									</a>
									<a id="excluirInscricao"
										href="<c:url value="/aluno/inscricao/excluir/${aluno.id}/${inscricao.id}" ></c:url>">
										<button class="btn btn-danger btn-sm"
											title="Excluir Inscrição">
											<i class="glyphicon glyphicon-trash"></i>
										</button>
									</a>
								</c:if>
							</sec:authorize>
						</h3>
					</div>
					<div class="panel-body">
						<div class="aluno-img-container">
							<img id="aluno-img"
								src="<c:url value = "/inscricao/detalhes/fotoAluno/${inscricao.id}"></c:url>" />
						</div>
						<div class='f-container s4 left'>
							<label class='f-title'>Matrícula:</label>
							<div class='f-content'>
								<c:if test="${not empty inscricao.aluno.matricula}">
									<span> ${inscricao.aluno.matricula } </span>
								</c:if>
							</div>
						</div>

						<div class='f-container s3 left'>
							<label class='f-title'>Nome:</label>
							<div class='f-content'>
								<c:if test="${not empty usuarioAtivo.nome}">
									<span> ${usuarioAtivo.nome } </span>
								</c:if>
							</div>
						</div>

						<div class='f-container s4 left'>
							<label class='f-title'>CPF:</label>
							<div class='f-content'>
								<c:if test="${not empty usuarioAtivo.cpf}">
									<span> ${usuarioAtivo.cpf } </span>
								</c:if>
							</div>
						</div>

						<div class='f-container s3 left'>
							<label class='f-title'>Email:</label>
							<div class='f-content'>
								<c:if test="${not empty usuarioAtivo.email}">
									<span> ${usuarioAtivo.email } </span>
								</c:if>
							</div>
						</div>

						<div class='f-container s4 left'>
							<label class='f-title'>Telefone:</label>
							<div class='f-content'>
								<c:if test="${not empty usuarioAtivo.telefone}">
									<span> ${usuarioAtivo.telefone } </span>
								</c:if>

							</div>
						</div>

						<div class='f-container s3 left'>
							<label class='f-title'>Nascimento:</label>
							<div class='f-content'>
								<c:if test="${not empty usuarioAtivo.dataNascimento}">
									<span> <fmt:formatDate pattern="dd/MM/yyyy"
											value="${usuarioAtivo.dataNascimento }" /></span>
								</c:if>
							</div>
						</div>
						<div class='f-container s4 left'>
							<label class='f-title'>Data da inscrição:</label>
							<div class='f-content'>
								<fmt:formatDate value="${inscricao.data}" pattern="dd/MM/yyyy" />
							</div>
						</div>

						<div class='f-container s3 left'>
							<label class='f-title'>Tipo da seleção:</label>
							<div class='f-content'>${inscricao.selecao.tipoSelecao.nome}</div>
						</div>

						<div class='f-container s4 left'>
							<label class='f-title'>Resultado:</label>
							<div class='f-content'>${inscricao.resultado.nome}</div>
						</div>

						<div class='f-container s3 left'>
							<label class='f-title'>Observações:</label>
							<div class='f-content'>${inscricao.observacoes}</div>
						</div>

					</div>
				</div>
				<div class="panel panel-default panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							Moradia <span class="direita clicavel panel-collapsed"> <i
								class="glyphicon glyphicon-chevron-down"></i>
							</span>
						</h3>
					</div>
					<div class="panel-body fechado">

						<div class="form-group">
							<h5 class="titulo-dl divisor">Com quem Mora</h5>
							<!--  TODO -->
							<div class='f-container s5'>
								<label class='f-title'>Mora com:</label>
								<ul class='mora-com-lista'>
									<c:forEach var="pessoa"
										items="${inscricao.questionarioAuxilioMoradia.comQuemMora }">
										<c:if test="${pessoa.descricao ne 'OUTRO'}">
											<li>${pessoa.descricao.nome}</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>

							<c:choose>
								<c:when
									test="${not empty inscricao.questionarioAuxilioMoradia.comQuemMoraOutros}">
									<div class='f-container s5'>
										<label class='f-title'>Com que mora os outros:</label>
										<div class='f-content'>${inscricao.questionarioAuxilioMoradia.comQuemMoraOutros}</div>
									</div>

								</c:when>
								<c:otherwise>
									<div class='f-container s5'></div>
								</c:otherwise>
							</c:choose>



							<h5 class="titulo-dl divisor">Nome dos Pais</h5>
							<div class='f-container s5'>
								<label class='f-title'>Mãe:</label>
								<div class='f-content'>${inscricao.questionarioAuxilioMoradia.nomeMae}</div>
							</div>
							<div class='f-container s5'>
								<label class='f-title'>Pai:</label>
								<div class='f-content'>${inscricao.questionarioAuxilioMoradia.nomePai}</div>
							</div>
						</div>

						<h5 class="titulo-dl divisor">Endereço da Residência Atual</h5>

						<div class='f-container s3'>
							<label class='f-title'>Rua/Av:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.endereco}</div>
						</div>

						<div class='f-container s2'>
							<label class='f-title'>Bairro:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.bairro}</div>
						</div>

						<div class='f-container s3'>
							<label class='f-title'>Número:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.numero}</div>
						</div>

						<div class='f-container s2'>
							<label class='f-title'>CEP:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.cep}</div>
						</div>

						<div class='f-container s3'>
							<label class='f-title'>Cidade:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.cidade}</div>
						</div>

						<div class='f-container s2'>
							<label class='f-title'>Estado:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.estado}</div>
						</div>

						<div class='f-container s3'>
							<label class='f-title'>Complemento:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.complemento}</div>
						</div>


						<div class='f-container s2'>
							<label class='f-title'>Referência:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.referencia}</div>
						</div>



						<h5 class="titulo-dl divisor">Endereço da Residência de
							Origem</h5>

						<div class='f-container s3'>
							<label class='f-title'>Rua/Av:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.enderecoOrigem}</div>
						</div>

						<div class='f-container s2'>
							<label class='f-title'>Bairro:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.bairroOrigem}</div>
						</div>

						<div class='f-container s3'>
							<label class='f-title'>Número:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.numeroOrigem}</div>
						</div>

						<div class='f-container s2'>
							<label class='f-title'>CEP:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.cepOrigem}</div>
						</div>

						<div class='f-container s3'>
							<label class='f-title'>Cidade:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.cidadeOrigem}</div>
						</div>

						<div class='f-container s2'>
							<label class='f-title'>Estado:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.estadoOrigem}</div>
						</div>

						<div class='f-container s3'>
							<label class='f-title'>Complemento:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.complementoOrigem}</div>
						</div>

						<div class='f-container s2'>
							<label class='f-title'>Referência:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.referenciaOrigem}</div>
						</div>

						<div class='f-container s3'>
							<label class='f-title'>Telefone origem:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.telefoneOrigem}</div>
						</div>

						<div class='f-container s2'>
							<label class='f-title'>Situação do imóvel:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.situacaoImovel.nome}</div>
						</div>

						<div class='f-container s5'></div>

						<h5 class="titulo-dl divisor">Propriedade Rural</h5>
						<div class='f-container s5'>
							<label class='f-title'>Grau de parentesco do
								proprietário:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.grauParentescoImovelRural.nome}</div>
						</div>

						<div class='f-container s5'>
							<label class='f-title'>Área da propriedade:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.areaPropriedadeRural}</div>
						</div>

						<div class='f-container s5'>
							<label class='f-title'>Cidade/Estado Imóvel:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.cidadeEstadoImovelRural}</div>
						</div>

						<div class='f-container s5'></div>

						<h5 class="titulo-dl divisor">Bens Móveis (Veículos)</h5>
						<div class='f-container s5'>
							<label class='f-title'>Grau de parentesco do
								proprietário:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.grauParentescoVeiculos.nome}</div>
						</div>

						<div class='f-container s5'>
							<label class='f-title'>Veículo:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.veiculo}</div>
						</div>
						<div class='f-container s5'>
							<label class='f-title'>Finalidade do Veículo:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.finalidadeVeiculo}</div>
						</div>

						<div class='f-container s5'></div>

					</div>
				</div>
				<div class="panel panel-default panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							Situação Socioeconômica (Grupo familiar incluido o aluno) <span
								class="direita clicavel panel-collapsed"> <i
								class="glyphicon glyphicon-chevron-down"></i>
							</span>

						</h3>
					</div>
					<div class="panel-body fechado">
						<div class="form-group">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>Nome:</th>
										<th>Parentesco:</th>
										<th>Escolaridade:</th>
										<th>Atividade:</th>
										<th>Renda R$:</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pessoa"
										items="${inscricao.questionarioAuxilioMoradia.pessoas }">
										<tr>
											<td>${pessoa.nome }</td>
											<td>${pessoa.parentesco.nome }</td>
											<td>${pessoa.escolaridade }</td>
											<td>${pessoa.profissao}</td>
											<td>${pessoa.rendaMensal }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="panel panel-default panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							Histórico Escolar <span class="direita clicavel panel-collapsed">
								<i class="glyphicon glyphicon-chevron-down"></i>
							</span>
						</h3>
					</div>
					<div class="panel-body fechado">
						<h5 class="titulo-dl divisor">Ensino Fundamental</h5>


						<div class='f-container s4'>
							<label class='f-title'>Tipo de escola:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.ensinoFundamental.nome}</div>
						</div>

						<div class='f-container s3'>
							<label class='f-title'>Possuia bolsa:</label>
							<div class='f-content'>
								<c:choose>
									<c:when
										test="${\"Particular com Bolsa\" eq inscricao.questionarioAuxilioMoradia.ensinoFundamental.nome}"> Sim</c:when>
									<c:otherwise>Não</c:otherwise>
								</c:choose>
							</div>
						</div>
						<c:choose>
							<c:when
								test="${\"Particular com Bolsa\" eq inscricao.questionarioAuxilioMoradia.ensinoFundamental.nome}">
								<div class='f-container s3'>
									<label class='f-title'>Percentual da bolsa:</label>
									<div class='f-content'>${inscricao.questionarioAuxilioMoradia.percentualParticularFundamental}</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class='f-container s3'></div>
							</c:otherwise>
						</c:choose>
						<h5 class="titulo-dl divisor">Ensino Médio</h5>
						<div class='f-container s4'>
							<label class='f-title'>Tipo de escola:</label>
							<div class='f-content'>${inscricao.questionarioAuxilioMoradia.ensinoMedio.nome}</div>
						</div>
						<div class='f-container s3'>
							<label class='f-title'>Possuia bolsa:</label>
							<div class='f-content'>
								<c:choose>
									<c:when
										test="${\"Particular com Bolsa\" eq inscricao.questionarioAuxilioMoradia.ensinoMedio.nome}"> Sim</c:when>
									<c:otherwise>Não</c:otherwise>
								</c:choose>
							</div>
						</div>
						<c:choose>
							<c:when
								test="${\"Particular com Bolsa\" eq inscricao.questionarioAuxilioMoradia.ensinoMedio.nome}">
								<div class='f-container s3'>
									<label class='f-title'>Percentual da bolsa:</label>
									<div class='f-content'>${inscricao.questionarioAuxilioMoradia.percentualParticularMedio}</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class='f-container s3'></div>
							</c:otherwise>
						</c:choose>
						<div class='f-container s5'>
							<label class='f-title'>Fez cursinho pré-vestibular:</label>
							<div class='f-content'>
								<c:choose>
									<c:when
										test="${inscricao.questionarioAuxilioMoradia.cursinho == true}"> Sim</c:when>
									<c:otherwise>Não</c:otherwise>
								</c:choose>
							</div>
						</div>
						<c:choose>
							<c:when
								test="${inscricao.questionarioAuxilioMoradia.cursinho == true}">
								<div class='f-container s5'>
									<label class='f-title'>Nome do cursinho:</label>
									<div class='f-content'>
										${inscricao.questionarioAuxilioMoradia.nomeCursinho}&nbsp;</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class='f-container s5'></div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="panel panel-default panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							Outras Informações <span class="direita clicavel panel-collapsed">
								<i class="glyphicon glyphicon-chevron-down"></i>
							</span>
						</h3>
					</div>
					<div class="panel-body fechado">
						<div class='f-container s5'>
							<label class='f-title'>Bolsista UFC:</label>
							<div class='f-content'>
								<c:choose>
									<c:when
										test="${inscricao.questionarioAuxilioMoradia.bolsistaUfc == true}"> Sim</c:when>
									<c:otherwise>Não</c:otherwise>
								</c:choose>
							</div>
						</div>
						<c:choose>
							<c:when
								test="${inscricao.questionarioAuxilioMoradia.bolsistaUfc == true}">
								<div class='f-container s5'>
									<label class='f-title'>Descrição da bolsa:</label>
									<div class='f-content'>
										${inscricao.questionarioAuxilioMoradia.descricaoBolsa}</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class='f-container s5'></div>
							</c:otherwise>
						</c:choose>

						<div class='f-container s5'>
							<label class='f-title'>Possui graduação:</label>
							<div class='f-content'>
								<c:choose>
									<c:when
										test="${inscricao.questionarioAuxilioMoradia.graduacao == true}"> Sim</c:when>
									<c:otherwise>Não</c:otherwise>
								</c:choose>
							</div>
						</div>
						<c:choose>
							<c:when
								test="${inscricao.questionarioAuxilioMoradia.graduacao == true}">
								<div class='f-container s5'>
									<label class='f-title'>Descrição da graduação:</label>
									<div class='f-content'>
										${inscricao.questionarioAuxilioMoradia.descricaoGraduacao}</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class='f-container s5'></div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="panel panel-default panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							Justificativa <span class="direita clicavel panel-collapsed">
								<i class="glyphicon glyphicon-chevron-down"></i>
							</span>
						</h3>
					</div>
					<div class="panel-body fechado">
						<div class='f-container s10'>
							<label class='f-title'>Justificativa:</label>
							<div class='f-content'>
								${inscricao.questionarioAuxilioMoradia.justificativa}</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane" id="documentos-tab"></div>
			<sec:authorize
				access="hasAnyRole('SERVIDOR','STA','COORDENADOR_ASSUNTOS_ESTUDANTIS')">
				<div class="tab-pane" id="entrevista-tab">
					<c:choose>
						<c:when
							test="${inscricao.deferimentoDocumentacao == 'INDEFERIDO'}">
							<div class="alert alert-danger alert-dismissible" role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								Este Aluno foi Indeferido na Etapa de Documentação
							</div>
						</c:when>
						<c:otherwise>
							<div class="panel panel-default panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">Dados da Entrevista</h3>
								</div>
								<div class="panel-body">
									<dl class='col-sm-12'>
										<dt class="col-sm-2">Resultado:</dt>
										<dd class="col-sm-2">DEFERIDO</dd>
										<dt class="col-sm-2">Observação:</dt>
										<dd class="col-sm-2">${inscricao.entrevista.observacao}</dd>
										<dt class="col-sm-2">Responsável:</dt>
										<dd class="col-sm-2">${inscricao.entrevista.servidor.pessoa.nome}</dd>
									</dl>
								</div>
							</div>
							<div class="panel panel-default panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">Entrevista</h3>
								</div>
								<div class="panel-body">
									<form:form id="relatorioForm" role="form"
										modelAttribute="entrevista" commandName="entrevista"
										servletRelativeAction="${url}" method="POST"
										cssClass="form-horizontal">

										<input type="hidden" id="idServidor" name="idServidor"
											value="${sessionScope.id}" />
										<input type="hidden" id="idInscricao" name="idInscricao"
											value="${inscricao.id}" />
										<input type="hidden" id="idEntrevista" name="idEntrevista"
											value="${inscricao.entrevista.id}" />
										<fieldset class="form-group">
											<label for="observacao" class="col-sm-1 control-label">Observação</label>
											<form:textarea class="col-sm-5 form-control"
												name="observacao" rows="3" id="observacao" type="text"
												path="observacao" placeholder="Observação"></form:textarea>
											<span class="help-block"></span>
											<div class="error-validation">
												<form:errors path="observacao"></form:errors>
											</div>
										</fieldset>

										<fieldset class="form-group">
											<label for="deferimento" class="col-sm-1 control-label">Deferimento</label>
											<select name="deferimento" id="deferimento"
												class="form-control col-sm-2">
												<option value="DEFERIDO">Deferido</option>
												<option value="INDEFERIDO"
													<c:if test="${inscricao.entrevista.deferimento=='INDEFERIDO'}"> selected  </c:if>>Indeferido</option>
											</select>
										</fieldset>
										<fieldset class="form-group">
											<label for="realizarVisita" class="control-label">Realizar
												Visita</label> <input type="checkbox" id="realizarVisita"
												name="realizarVisita" value="true"
												<c:if test="${inscricao.realizarVisita}">checked </c:if> />
										</fieldset>
										<fieldset class="form-group">
											<div class="col-sm-1" id="div-form-btn">
												<input name="submit" type="submit" class="btn btn-primary"
													value="${botao}" id="form-btn" />
											</div>
											<div class="col-sm-2" id="div-form-btn">
												<a href="<c:url value="/selecao/listar" ></c:url>"
													class="btn btn-default" id="form-btn">Cancelar</a>
											</div>
										</fieldset>
									</form:form>
								</div>
							</div>
							<div class="panel panel-default panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">Membros da Família</h3>
								</div>
								<div class="panel-body">
									<table class="table table-striped table-hover">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Parentesco</th>
												<th>Escolaridade</th>
												<th>Idade</th>
												<th>Profissao</th>
												<th>Renda R$</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="pessoa" items="${questAuxMor.pessoas}">
												<tr>
													<td>${pessoa.nome}</td>
													<td>${pessoa.parentesco.nome}</td>
													<td>${pessoa.escolaridade}</td>
													<td>${pessoa.idade}</td>
													<td>${pessoa.profissao}</td>
													<td>${pessoa.rendaMensal}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<div class="panel panel-default panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">Editar Membros da Família</h3>
								</div>
								<div class="panel-body">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Parentesco</th>
												<th>Escolaridade</th>
												<th>Idade</th>
												<th>Profissao</th>
												<th>Renda R$</th>
												<th></th>
											</tr>
										</thead>
										<tbody>

											<c:forEach var="pessoa"
												items="${inscricao.questionarioAuxilioMoradia.pessoasEntrevista}">
												<tr>
													<td>${pessoa.nome}</td>
													<td>${pessoa.parentesco.nome}</td>
													<td>${pessoa.escolaridade}</td>
													<td>${pessoa.idade}</td>
													<td>${pessoa.profissao}</td>
													<td>${pessoa.rendaMensal}</td>
													<td><a id="remover-pessoa-familia" data-toggle="modal"
														data-target="#confirm-delete" href="#"
														data-href="<c:url value="/servidor/inscricao/removerPessoaFamilia/${pessoa.id}/${inscricao.id } "></c:url>">
															<button class="btn btn-danger btn-xs">
																<span class="glyphicon glyphicon-trash"></span>
															</button>
													</a></td>
												</tr>
											</c:forEach>
											<form:form id="formPessoaFamilia" method="POST" role="form"
												cssClass="form-horizontal" modelAttribute="pessoaDaFamilia"
												commandName="pessoaDaFamilia"
												servletRelativeAction="/servidor/inscricao/adicionarPessoaFamilia/${inscricao.id }">
												<tr>

													<td><form:input cssClass="form-control" path="nome"
															id="nome" /></td>
													<td><form:select cssClass="form-control"
															path="parentesco" id="parentesco">
															<option>Parentesco</option>
															<c:forEach items="${grauParentesco }" var="parentesco">
																<option value="${parentesco }">
																	${parentesco.nome }</option>
															</c:forEach>
														</form:select></td>
													<td><form:input cssClass="form-control" type="text"
															path="escolaridade" id="escolaridade" /></td>
													<td><form:input cssClass="form-control" type="number"
															path="idade" id="idade" /></td>
													<td><form:input cssClass="form-control" type="text"
															path="profissao" id="profissao" /></td>
													<td><form:input cssClass="form-control" type="number"
															id="rendaMensal" path="rendaMensal" /></td>
													<td></td>
												</tr>
											</form:form>
										</tbody>

									</table>
									<div class="form-group">
										<input id="addPessoaFamilia" type="submit"
											class="btn btn-primary" value="Adicionar Pessoa" />
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="tab-pane" id="visita-tab">
					<div class="panel panel-default panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">Entrevista</h3>
						</div>
						<div class="panel-body">
							<dl class='col-sm-12'>
								<dt class="col-sm-2">Parecer:</dt>
								<c:choose>
									<c:when
										test="${inscricao.visitaDomiciliar.deferimento == true}">
										<dd class="col-sm-2">DEFERIDO</dd>
									</c:when>
									<c:otherwise>
										<dd class="col-sm-2">INDEFERIDO</dd>
									</c:otherwise>
								</c:choose>
								<dt class="col-sm-2">Observação:</dt>
								<dd class="col-sm-2">${inscricao.visitaDomiciliar.observacaoParecer}</dd>
							</dl>
						</div>
					</div>
					<div class="panel panel-default panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title"></h3>
						</div>
						<div class="panel-body text-align-left">

							<c:choose>
								<c:when
									test="${inscricao.deferimentoDocumentacao eq 'INDEFERIDO'}">
									<div class="alert alert-danger alert-dismissible" role="alert">
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										Este Aluno foi Indeferido na Etapa de entrevista
									</div>
								</c:when>
								<c:otherwise>
									<!-- aqui deve ser mostrado os dados da visita -->
								</c:otherwise>
							</c:choose>

							<label class='f-title'> Formulário da visita: </label><br /> <label
								class="f-title"> <c:choose>

									<c:when
										test="${not empty inscricao.visitaDomiciliar.formularioVisita}">
										<a class="no-decoration"
											href="<c:url value="/selecao/documento/${inscricao.visitaDomiciliar.formularioVisita.id}"></c:url>">${inscricao.visitaDomiciliar.formularioVisita.nome}</a>
										<strong class="error text-danger"></strong>
										<a id="excluir" data-toggle="modal"
											aria-title="O formulário sejá removido. Deseja continuar?"
											aria-destination="<c:url value="/servidor/visita/removerFormulario/${inscricao.id}/${inscricao.visitaDomiciliar.formularioVisita.id}"></c:url>"
											class="confirm-button delete-document btn btn-danger btn-xs glyphicon glyphicon-trash">
										</a>
									</c:when>

									<c:otherwise>

										<form id="insercaoFormularioVisita" role="form" method="POST"
											enctype="multipart/form-data"
											action="<c:url value="/servidor/visita/enviarFormulario/${inscricao.id}"/>">
											<input type="file" name="formulario" /><br /> <input
												type="submit" class="btn btn-primary" />
										</form>

									</c:otherwise>

								</c:choose>

							</label>


							<hr />

							<label class='f-title'> Adicionar Foto: </label>

							<form id="insercaoImagemEntrevista" role="form"
								action="<c:url value="/servidor/detalhes/inscricao/inserirImagem"/>"
								method="POST" enctype="multipart/form-data">
								<input type="hidden" name="idInscricao" value="${inscricao.id}" />
								<input type="file" name="foto" /> <br /> <input type="submit"
									value="Adicionar" class='btn btn-primary' />
							</form>

							<hr />

							<ul class='photos-list'>
								<c:forEach var="imagem"
									items="${inscricao.visitaDomiciliar.imagens}">
									<li class='img-fullscreen'>
										<div class='input-photo'>
											<img class='photo-img'
												src="data:image/jpeg;base64,${imagem.img}" />
											<div class='remove-photo confirm-button'
												aria-title="Continuar irá remover a foto da visita, deseja prosseguir?"
												aria-destination="<c:url value="/servidor/detalhes/inscricao/removerImagem/${inscricao.id}/${imagem.id}"/>"></div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</sec:authorize>
		</div>
		<jsp:include page="../fragments/footer.jsp" />

		<div class="modal fade" id="confirm-delete" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">Excluir</div>
					<div class="modal-body">Tem certeza de que deseja excluir
						esta pessoa da família?</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-danger">Excluir</a>
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>