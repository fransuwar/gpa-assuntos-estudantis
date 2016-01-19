<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Atribuir Parecerista</title>
</head>

<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<ol class="breadcrumb">
		<li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
		<li class="active">Atribuir Comissão</li>
	</ol>
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
				<h3 class="panel-title">Gerenciar Comissão</h3>
			</div>
			<div class="panel-body">

				<form:form id="adicionarComissaoForm" role="form"
					servletRelativeAction="/coordenador/comissao/atribuir"
					method="POST" class="form-horizontal">
					<input type="hidden" name="idSelecao" value="${idSelecao}">
					<div class="form-group">
						<label class="col-sm-1 control-label">Membro:</label>
						<div class="col-sm-4">
							<select id="" class="form-control" name="idServidor">
								<c:forEach items="${servidores}" var="servidor">
									<option value="${servidor.id}">${servidor.pessoa.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-sm-2" id="div-form-btn" align="left">
							<input type="submit" class="btn btn-primary"
								value="Adicionar Membro" id="form-btn" />
						</div>
					</div>
					<div class="col-sm-5">
						<table class="table table-striped table-hover ">
							<c:forEach var="servidor" items="${comissao.membrosComissao}">
								<tr class="linha">
									<td class="linha">${servidor.pessoa.nome}</td>
									<td><a id="excluir" data-toggle="modal"
										data-target="#confirm-delete"
										data-href="<c:url value="/coordenador/comissao/excluir/${idSelecao}/${servidor.id}"></c:url>">
											<button class="btn btn-danger btn-xs">
												<span class="glyphicon glyphicon-trash"></span>
											</button>
									</a></td>
								</tr>
							</c:forEach>
						</table>

					</div>
					<div class="form-group col-sm-12">

						<div class="col-sm-2" id="div-form-btn">
							<a href="<c:url value="/selecao/listar"></c:url>"
								class="btn btn-default" id="form-btn">Cancelar</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp"></jsp:include>

	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Excluir</div>
				<div class="modal-body">Tem certeza de que deseja excluir esse
					membro da Comissão?</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-danger">Excluir</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
