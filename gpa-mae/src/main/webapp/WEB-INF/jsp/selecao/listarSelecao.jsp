<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<html>
	<head>
		<title>Seleções</title>
		<jsp:include page="../fragments/headTag.jsp" />
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
					<div style="text-align: center">
						<c:out value="${erro}"></c:out>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty info}">
				<div class="alert alert-success alert-dismissible" role="alert"
					id="alert-info">
					<button type="button" class="close" data-dismiss="alert">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<div style="text-align: center">
						<c:out value="${info}"></c:out>
					</div>
				</div>
			</c:if>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Seleções</h3>
			</div>
			<div class="panel-body">
		
				<table class="display" id="tabela-selecoes">
					<thead>
						<tr>
							<th class="dt-head-center">Seleção</th>
							<th class="dt-head-center">Ano</th>
							<th class="dt-head-center">Vagas</th>
							<th class="dt-head-center">Inscrições</th>
					</thead>
					<tbody>
						<c:forEach var="selecao" items="${selecoes}">
							<tr>
								<td class="dt-body-center">
									<a id="detalhes" href="<c:url value="/selecao/detalhesPublico/${selecao.id}"></c:url>">
										${selecao.tipoSelecao.nome}
									</a>
								</td>
								<td class="dt-body-center">${selecao.sequencial}/${selecao.ano}</td>
								<td class="dt-body-center">
								<c:choose>
									<c:when test="${not empty selecao.quantidadeVagas}">
										${selecao.quantidadeVagas}
									</c:when>
									<c:otherwise>
										-
									</c:otherwise>
								</c:choose>
								</td>
								<td class="dt-body-center">
									<fmt:formatDate pattern="dd/MM/yyyy" value="${selecao.dataInicio}" /> à
									<fmt:formatDate pattern="dd/MM/yyyy" value="${selecao.dataTermino }" />
								</td>
							</tr>
						</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Excluir</div>
				<div class="modal-body">Tem certeza de que deseja excluir essa
					seleção?</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-danger">Excluir</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>