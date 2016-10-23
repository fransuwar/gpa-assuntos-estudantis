<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<jsp:include page="../fragments/headTag.jsp" />
	<title>Gerenciar Tipos de Documentos</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
	
		<jsp:include page="../fragments/alert.jsp" />

		<div class="row">
			<div class="panel panel-default">
				<div class="panel-body">
					<h2>Gerenciar Tipos de Documentos</h2>
					<div class="divider"></div>
					<div class="col-md-12">
						<form class="form-horizontal" action="<c:url value="/coordenador/tipo-documento/cadastrar" />"
							method="post" enctype="multipart/form-data">
							<div class="form-group">
								<label class="col-md-1 control-label" for="nome">Nome</label>
								<div class="col-md-4">
									<input class="form-control" type="text" name="nome" required="required">
								</div>
								<div class="col-md-2">
									<input type="submit" class="btn btn-primary btn-raised" value="Adicionar"/>
								</div>
							</div>
						</form>
					</div>
	
					<div class="col-md-12">
						<table class="table table-striped table-hover">
							<tbody>
								<c:forEach items="${documentos}" var="documento">
									<tr id="row-${documento.id}">
										<td>${documento.nome}</td>
										<td>
											<a class="btn btn-danger" data-toggle="modal" data-target="#excluir-tipo-documento" title="Excluir"
												data-href="<c:url value="/coordenador/tipo-documento/excluir/${documento.id }"></c:url>">
												<i class="material-icons">delete</i>
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
	</div>

	<jsp:include page="../fragments/footer.jsp" />
	
	<script src="<c:url value="/js/coordenador/gerenciar-documento.js" />"></script>
	
	<div class="modal fade" id="excluir-tipo-documento">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Excluir</div>
				<div class="modal-body">Tem certeza de que deseja excluir esse documento?</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-danger">Excluir</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>

</body>

</html>