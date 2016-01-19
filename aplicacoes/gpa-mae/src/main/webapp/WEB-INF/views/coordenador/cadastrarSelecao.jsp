<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${action eq 'cadastrar' }">
	<c:url var="url" value="/coordenador/selecao/cadastrar" />
	<c:set var="titulo" value="Nova Seleção" />
	<c:set var="botao" value="Cadastrar" />
</c:if>

<c:if test="${action eq 'editar' }">
	<c:url var="url" value="/coordenador/selecao/editar" />
	<c:set var="titulo" value="Editar Seleção" />
	<c:set var="botao" value="Atualizar" />
</c:if>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Cadastro de Seleções</title>
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
				<h3 class="panel-title">${titulo }</h3>
			</div>
			<div class="panel-body">

				<form:form id="adicionarSelecaoForm" role="form"
					commandName="selecao" servletRelativeAction="${url }" method="POST"
					cssClass="form-horizontal">

					<input type="hidden" name="id" value="${selecao.id}" />

					<c:forEach items="selecao.membrosComissao" var="membro" varStatus="status">
					  <input type="hidden" name="selecao.membrosComissao[${status.index}]." value="${membro}"/>
					</c:forEach>
					
					

					<div class="form-group">

						<label for="tipoSelecao" class="col-sm-2 control-label"><span
							class="red">*</span>Tipo de Selecao:</label>
						<div class="col-sm-5 control-label" id="div-select">
							<form:select path="tipoSelecao" id="tipoSelecao"
								cssClass="form-control">
								<form:option value="" label="Selecione o tipo de Selecao" />
								<form:options items="${tipoSelecao}" itemLabel="nome" />
							</form:select>
							<div class="error-validation label-erro-select"
								id="erro-tipoSelecao">
								<form:errors path="tipoSelecao"></form:errors>
							</div>
						</div>

						<div class="form-group">
							<label for="quantidadeVagas" class="col-sm-2 control-label"><span
								class="red">*</span>Vagas:</label>
							<div class="col-sm-2">
								<form:input id="quantidadeVagas" path="quantidadeVagas"
									cssClass="form-control" placeholder="0" min="1" data-mask="999" />
								<div class="error-validation" id="erro-qtdVagas">
									<form:errors path="quantidadeVagas"></form:errors>
								</div>
							</div>
						</div>

						<label for="sequencial" class="col-sm-2 control-label"><span
							class="red">*</span>Sequencial:</label>
						<div class="col-sm-2">
							<form:input id="sequencial" path="sequencial"
								cssClass="form-control" placeholder="000" data-mask="999" />
							<div class="error-validation" id="erro-sequencial">
								<form:errors path="sequencial"></form:errors>
							</div>
						</div>
						<label for="ano" class="col-sm-1 control-label"><span
							class="red">*</span>Ano:</label>
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
						<label for="dataInicio" class="col-sm-2 control-label"><span
							class="red">*</span>Data de Início:</label>
						<div class="col-sm-2">
							<form:input id="dataInicio" type="text" path="dataInicio"
								cssClass="form-control data" placeholder="Data de Início" />
							<div class="error-validation" id="erro-dataInicio">
								<label class="col-sm-10 control-label" id="label-erro">
									${dataInicioError} </label>
								<form:errors path="dataInicio" />
							</div>
						</div>

						<label for="dataTermino" class="col-sm-2 control-label"><span
							class="red">*</span>Data de Término:</label>
						<div class="col-sm-2">
							<form:input id="dataTermino" type="text" path="dataTermino"
								cssClass="form-control data" placeholder="Data de Término" />
							<div class="error-validation" id="erro-dataTermino">
								<form:errors path="dataTermino"></form:errors>
							</div>
						</div>
					</div>
					<div class="form-group" align="right">
						<div class="col-sm-5" id="div-form-btn">
							<input name="submit" type="submit" class="btn btn-primary"
								value="${botao }" id="form-btn" />
						</div>

						<div class="col-sm-1" id="div-form-btn">
							<a href="<c:url value="/selecao/listar"></c:url>"
								class="btn btn-default" id="form-btn">Cancelar</a>
						</div>
					</div>
				</form:form>
				<br>
				<form id="adicionarArquivoForm" role="form"
					action="<c:url value="/coordenador/selecao/adicionar-documento" />"
					method="POST" class="form-horizontal" enctype="multipart/form-data">
					<input type="hidden" name="idSelecao" value="${selecao.id}">

					<div class="form-group">
						<label for="arquivo" class="col-sm-2 control-label">Arquivos:</label>
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
											<td><a
												href="<c:url value="/selecao/documento/${documento.id}"></c:url>">${documento.nome}</a>
												<strong class="error text-danger"></strong></td>
											<td><a id="excluir" data-toggle="modal"
												data-target="#confirm-delete"
												data-href="<c:url value="/coordenador/selecao/excluir-documento/${documento.id }"></c:url>"
												class="delete-document">
													<button type="button" class="btn btn-danger btn-xs">
														<span class="glyphicon glyphicon-trash"></span>
													</button>
											</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<input type="submit" class="btn btn-primary"
								value="Adicionar Arquivo" id="form-btn" />
						</div>

					</div>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Excluir</div>
				<div class="modal-body">Tem certeza de que deseja excluir esse
					Documento?</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-danger">Excluir</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
</body>

</html>