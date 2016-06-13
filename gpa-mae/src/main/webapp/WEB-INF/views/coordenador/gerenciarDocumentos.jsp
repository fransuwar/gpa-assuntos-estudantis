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
<title>Gerenciar Documentos</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Gerenciar Documentos</h3>
			</div>
			<div class="panel-body">

				<div class="s4-custom center-block">
					<form id="adicionarArquivoForm" role="form" class="form-inline" 
						action="<c:url value="/coordenador/adicionar-tipo-arquivo" />"
						method="GET" enctype="multipart/form-data">
						<div class="form-group">
							<label for="arquivo">Tipo de Documento:</label>
							<input class="form-control" type="text" name="nome" required ="required">
						</div> 
						<input type="submit" class="btn btn-primary" value="Adicionar" id="form-btn" />
							
						
					</form>
				</div>
				
				<div class="margin-top-s1 center-block s5">
						<table id="tipo-arquivo-upload" role="presentation"
							class="table table-hover table-custom">
							<thead class="th-custom">
								<tr>
									<th colspan="2" class="th-center">
										Documentos Cadastrados
									</th>
								</tr>
							</thead>
							<tbody class="files">
								<c:forEach items="${documentos}" var="documento">
									<tr class="template-upload fade in" id="row-${documento.id}">
										<td>${documento.nome}</td>
										<td>
											<a class="btn btn-danger btn-small btn-xs" id="id" data-toggle="modal"
												data-target="#confirm-delete"
												data-href="<c:url value="/coordenador/excluir-tipo-documento/${documento.id }"></c:url>"
												class="delete-document">
													<span class="glyphicon glyphicon-trash"></span>
											</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
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