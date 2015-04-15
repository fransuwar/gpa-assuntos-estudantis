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
<title>Cadastro de seleções</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />
	<ol class="breadcrumb">
		<li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
		<c:choose>
			<c:when test="${action == 'cadastrar'}">
				<li class="active">Cadastrar Seleções</li>
			</c:when>
			<c:otherwise>
				<li class="active">Editar Seleções</li>
			</c:otherwise>
		</c:choose>

	</ol>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${action == 'cadastrar'}">
						<h2>Novo Seleção</h2>
					</c:when>
					<c:otherwise>
						<h2>Editar Seleção</h2>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel-body">
				<form:form id="adicionarSelecaoForm" role="form"
					commandName="selecao" servletRelativeAction="/selecao/salvar"
					method="POST" cssClass="form-horizontal" enctype="multipart/form-data">
					<input type="hidden" name="id" value="${selecao.id}" />
					<div class="form-group">
						<label for="sequencial" class="col-sm-2 control-label">Número
							do Edital:</label>
						<div class="col-sm-2">
							<form:input id="sequencial" type="number" path="sequencial"
								cssClass="form-control" placeholder="000" />
							<div class="error-validation">
								<form:errors path="sequencial"></form:errors>
							</div>
							<div class="error-validation">${editalError}</div>
						</div>

						<label for="tipoBolsa" class="col-sm-2 control-label">Tipo
							de Bolsa:</label>
						<div class="col-sm-5 control-labe" id="div-select">
							<form:select path="tipoBolsa" id="tipoBolsa"
								cssClass="form-control">
								<form:option value="" label="Selecione o tipo de bolsa" />
								<form:options items="${tipoBolsa}" />
							</form:select>
							<form:errors path="tipoBolsa">
								<label class="col-sm-10 control-label" id="label-erro">
									Campo Obrigatório </label>
							</form:errors>
						</div>

					</div>

					<div class="form-group">
						<label for="dataInicio" class="col-sm-2 control-label">Data
							de Início:</label>
						<div class="col-sm-2">
							<form:input id="dataInicio" type="text" path="dataInicio"
								cssClass="form-control data" placeholder="Data de Início" />
							<div class="error-validation">
								${dataInicioError }
								<form:errors path="dataInicio"></form:errors>
							</div>
						</div>

						<label for="dataTermino" class="col-sm-2 control-label">Data
							de Término:</label>
						<div class="col-sm-2">
							<form:input id="dataTermino" type="text" path="dataTermino"
								cssClass="form-control data" placeholder="Data de Término" />
							<div class="error-validation">
								<form:errors path="dataTermino"></form:errors>
							</div>
						</div>

						<label for="ano" class="col-sm-1 control-label">Ano:</label>
						<div class="col-sm-2">
							<form:input id="ano" type="text" path="ano"
								cssClass="form-control	" placeholder="0"
								onkeypress="mascara(this,soNumeros)" />
							<div class="error-validation">
								<form:errors path="ano"></form:errors>
							</div>
							<div class="error-validation">${dataError}</div>
						</div>

					</div>

					<div class="form-group">
						<label for="quantidadeVagas" class="col-sm-2 control-label">Quant.
							de Vagas:</label>
						<div class="col-sm-2">
							<form:input id="quantidadeVagas" type="number" min="0"
								placeholder="0" path="quantidadeVagas" cssClass="form-control" />
							<div class="error-validation">
								<form:errors path="quantidadeVagas"></form:errors>
							</div>
							<div class="error-validation">${quantidadeError}</div>
						</div>

						<label for="duracao" class="col-sm-2 control-label">Duração:</label>
						<div class="col-sm-2">
							<form:input id="duracao" type="text" path="duracao"
								cssClass="form-control" placeholder="0" />
							<div class="error-validation">
								<form:errors path="duracao"></form:errors>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="comentarios" class="col-sm-2 control-label">Comentarios:</label>
						<div class="col-sm-9">
							<form:textarea id="comentarios" path="comentarios"
								class="form-control" rows="3"></form:textarea>
							<div class="error-validation">
								<form:errors path="comentarios"></form:errors>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="arquivo" class="col-sm-2 control-label">Arquivos:</label>
						<div class="col-sm-5 files">
						<input type="file" id="files" name="files" class="file" multiple="multiple" ></input>
							<c:if test="${not empty selecao.documentos }">
								<table id="file-upload" role="presentation"
									class="table table-striped">
									<tbody class="files">
										<c:forEach items="${selecao.documentos}" var="documento">
											<tr class="template-upload fade in">
												<td><a
													href="<c:url value="/documento/${documento.id }" />">${documento.nomeOriginal}</a>
													<strong class="error text-danger"></strong></td>
												<td><a id="${documento.id}" href="#"
													class="delete-document">
														<button type="button" class="btn btn-danger">
															Excluir <span class="glyphicon glyphicon-trash"></span>
														</button>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
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

</body>

</html>