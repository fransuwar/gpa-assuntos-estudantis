<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<ol class="breadcrumb">
      <li><a href="/MAE/servidor/listar">Listar Servidor</a></li>
    </ol>

	<div class="container">
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<c:out value="${erro}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<c:out value="${info}"></c:out>
			</div>
		</c:if>
		<div align="right" style="margin-bottom: 20px;">
			<a href="<c:url value="/servidor/cadastrar" ></c:url>">
				<button class="btn btn-primary">Novo Servidor <span class="glyphicon glyphicon-plus"></span></button>
			</a>
		</div>
		
		
		<div align="right" style="margin-bottom: 20px;">
				<form:form id="buscarServidorForm" role="form"
								servletReltiveAction="/servidor/listar" method="POST"
								cssClass="form-horizontal" class="inline">
								<input id="siape" name="siape" cssClass="form-control"
									placeholder="Digite sua busca aqui..." size="20"
									required="required" autofocus="true" />
								<button class="btn btn-primary" name="submit" type="submit"
									class="btn btn-primary" value="Buscar">
									Buscar <span class="glyphicon glyphicon-search" />
								</button>

				</form:form>
			
		</div>
			
		
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#meus-servidores" role="tab" data-toggle="tab">Servidores Cadastrados</a></li>
		</ul>

		<div class="tab-content">

			<!-- Meus Servidores -->
			<div class="tab-pane active" id="servidores-cadastrados">
				<c:if test="${empty servidores}">
					<div class="alert alert-warning" role="alert">Não há servidores cadastrados.</div>
				</c:if>
				<c:if test="${not empty servidores}">
					<div class="panel panel-default">
						<div class="panel-heading" align="center">
							<h4>Todos os Servidores</h4>
						</div>
			
						<!-- Table -->
						<table class="table">

							<tr>
								<th id="teste">Id</th>
								<th>SIAPE</th>
								<th>Cargo</th>
								<th id="acoes">Ações</th>
							</tr>
							<tbody>
								<c:forEach var="servidor" items="${servidores}">
									<tr class="linha">
										<td>${servidor.id}</td>
										<td>${servidor.siape}</td>
										<td>${servidor.cargo}</td>
										<td><a id="editar"
											href="<c:url value="/servidor/${servidor.id}/editar" ></c:url>">
												<button class="btn btn-info">
													Editar <span class="glyphicon glyphicon-pencil"></span>
												</button>
										</a> <a id="excluir" data-toggle="modal"
											data-target="#confirm-delete" href="#"
											data-href="<c:url value="/servidor/${servidor.id}/excluir" ></c:url>">
												<button class="btn btn-danger">
													Excluir <span class="glyphicon glyphicon-trash"></span>
												</button>
										</a></td>
								</c:forEach>
								
							</tbody>
						</table>

					</div>
					</c:if>
			</div>
			</div>
			</div>
		
		
			<jsp:include page="../fragments/footer.jsp" />

		<div class="modal fade" id="confirm-delete" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">Excluir</div>
					<div class="modal-body">Tem certeza de que deseja excluir
						este aluno?</div>
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