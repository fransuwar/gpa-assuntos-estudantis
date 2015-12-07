<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Servidores</title>
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
		<div class="col-md-12">
			<div class="col-md-2 " id="div-btn-inserir">
				<a href="<c:url value="/servidor/cadastrar" ></c:url>">
					<button class="btn btn-primary" id="listar-btn-inserir">
						Novo Servidor <span class="glyphicon glyphicon-plus"></span>
					</button>
				</a>
			</div>
		</div>

		<div class="col-md-12">
			<div class="panel panel-info">
				<div class="panel-heading" align="center">
					<h3 class="panel-title">Servidores Cadastrados</h3>
				</div>
				<c:if test="${empty servidores}">
					<div class="panel-body">
						<div class="alert alert-warning" role="alert">Não há
							servidores cadastrados.</div>
					</div>
				</c:if>
				<c:if test="${not empty servidores}">
					<!-- Table -->
					<table class="table table-display table-striped" id="tabela-servidores">
						<thead>
							<tr class="info">
								<th>SIAPE</th>
								<th>Cargo</th>
								<th id="acoes">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty servidorEncontrado}">
								</c:when>
								<c:otherwise>
									<c:forEach var="servidor" items="${servidores}">
										<tr>
											<td>${servidor.siape}</td>
											<td>${servidor.cargo.nome}</td>
											<td><a id="editar"
												href="<c:url value="/servidor/${servidor.id}/editar" ></c:url>">
													<button class="btn btn-info">
														<span class="glyphicon glyphicon-pencil"></span>
													</button>
											</a> <a id="excluir" data-toggle="modal"
												data-target="#confirm-delete" href="#"
												data-href="<c:url value="/servidor/${servidor.id}/excluir" ></c:url>">
													<button class="btn btn-danger">
														<span class="glyphicon glyphicon-trash"></span>
													</button>
											</a></td>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Excluir</div>
				<div class="modal-body">Tem certeza de que deseja excluir este
					aluno?</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-danger">Excluir</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>