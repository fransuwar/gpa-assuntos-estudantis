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
	
	<div class="container">
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert"
				id="alert-erro">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<c:out value="${erro}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-success alert-dismissible" role="alert"
				id="alert-info">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<c:out value="${info}"></c:out>
			</div>
		</c:if>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${action == 'cadastrar'}">
						<h2>Nova Seleção</h2>
					</c:when>
					<c:otherwise>
						<h2>Editar Seleção</h2>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel-body">
				<form:form id="adicionarSelecaoForm" role="form"
					commandName="selecao" servletRelativeAction="/selecao/salvar"
					method="POST" cssClass="form-horizontal"
					enctype="multipart/form-data">
					<input type="hidden" name="id" value="${selecao.id}" />
					<div class="form-group">
						<label for="tipoBolsa" class="col-sm-2 control-label"><span class="red">*</span>Tipo
							de Bolsa:</label>
						<div class="col-sm-5 control-label" id="div-select">
							<form:select path="tipoBolsa" id="tipoBolsa"
								cssClass="form-control">
								<form:option value="" label="Selecione o tipo de bolsa" />
								<form:options items="${tipoBolsa}" itemLabel="nome" />
							</form:select>
							<div class="error-validation label-erro-select"
								id="erro-tipoBolsa">
								<form:errors path="tipoBolsa"></form:errors>
							</div>
						</div>
						
						<div class="form-group">
						<label for="quantidadeVagas" class="col-sm-2 control-label"><span class="red">*</span>Vagas:</label>
						<div class="col-sm-2">
							<form:input id="quantidadeVagas" path="quantidadeVagas"
								cssClass="form-control" placeholder="0" min="1" data-mask="999" />
							<div class="error-validation" id="erro-qtdVagas">
								<form:errors path="quantidadeVagas"></form:errors>
							</div>
						</div>
					</div>
						
						<label for="sequencial" class="col-sm-2 control-label"><span class="red">*</span>Sequencial:</label>
						<div class="col-sm-2">
							<form:input id="sequencial" path="sequencial"
								cssClass="form-control" placeholder="000" data-mask="999" />
							<div class="error-validation" id="erro-sequencial">
								<form:errors path="sequencial"></form:errors>
							</div>
						</div>
						<label for="ano" class="col-sm-1 control-label"><span class="red">*</span>Ano:</label>
						<div class="col-sm-2">
							<form:input id="ano" type="text" path="ano"
								cssClass="form-control" placeholder="0"
								onkeypress="mascara(this,soNumeros)" />
							<div class="error-validation" id="erro-ano">
							<label class="col-sm-10 control-label" id="label-erro">
									${dataError} </label>
								<form:errors path="ano"></form:errors>
							</div>
						</div>


					</div>

					<div class="form-group">
						<label for="dataInicio" class="col-sm-2 control-label"><span class="red">*</span>Data
							de Início:</label>
						<div class="col-sm-2">
							<form:input id="dataInicio" type="text" path="dataInicio"
								cssClass="form-control data" placeholder="Data de Início" />
							<div class="error-validation" id="erro-dataInicio">
								<label class="col-sm-10 control-label" id="label-erro">
									${dataInicioError} </label>
								<form:errors path="dataInicio" />
							</div>
						</div>

						<label for="dataTermino" class="col-sm-2 control-label"><span class="red">*</span>Data
							de Término:</label>
						<div class="col-sm-2">
							<form:input id="dataTermino" type="text" path="dataTermino"
								cssClass="form-control data" placeholder="Data de Término" />
							<div class="error-validation" id="erro-dataTermino">
								<form:errors path="dataTermino"></form:errors>
							</div>
						</div>

						

					</div>
					
					<div class="form-group">
						<label for="arquivo" class="col-sm-2 control-label"><span class="red">*</span>Arquivos:</label>
						<div class="col-sm-5 files">
							<input type="file" id="files" name="files" class="file"
								multiple="multiple"></input>
							<div class="error-validation" id="erro-Anexo">
								<label class="col-sm-10 control-label" id="label-erro">
									${anexoError} </label>
							</div>
							<table id="file-upload" role="presentation"
								class="table table-striped">
								<tbody class="files">
									<c:forEach items="${selecao.documentos}" var="documento">
										<tr class="template-upload fade in" id="row-${documento.id}">
											<td>
												<a href="<c:url value="/selecao/downloadDocumento/${documento.id}"></c:url>">${documento.nome}</a>
												<strong class="error text-danger"></strong>
											</td>
											<td><a onclick="removerDocumento(${documento.id});"
												   class="delete-document">
													<button type="button" class="btn btn-danger">
														Excluir <span class="glyphicon glyphicon-trash"></span>
													</button>
											</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

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