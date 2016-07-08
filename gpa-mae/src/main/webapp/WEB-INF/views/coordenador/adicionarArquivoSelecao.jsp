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
<title>Adicionar Arquivo</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />
	
	<div class="container">
		
		<jsp:include page="../fragments/cards.jsp" />
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert"
				id="alert-erro">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">×</span><span class="sr-only">Close</span>
				</button>
				<div style="text-align: center">
					<c:out value="${erro}"></c:out>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-success alert-dismissible" role="alert"
				id="alert-info">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">×</span><span class="sr-only">Close</span>
				</button>
				<div style="text-align: center">
					<c:out value="${info}"></c:out>
				</div>
			</div>
		</c:if>
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Adicionar Arquivo</h3>
			</div>
			<div class="panel-body">
		
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
												data-href="<c:url value="/coordenador/selecao/excluir-documento/${selecao.id}/${documento.id }"></c:url>"
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