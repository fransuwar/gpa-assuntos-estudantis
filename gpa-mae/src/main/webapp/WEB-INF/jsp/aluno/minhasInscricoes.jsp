<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Minhas Inscrições</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
	
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
		<div class="col-md-12">
			<div class="panel panel-primary">
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
				
				<div class="panel-heading">
					<h3 class="panel-title">Minhas Inscrições</h3>
				</div>
				
				<c:if test="${not empty info}">
					<div class="alert alert-success alert-dismissible" role="alert"
						id="alert-info">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<c:out value="${info}"></c:out>
					</div>
				</c:if>
				<table class="table-display" id="tabela-selecoes">
					<thead>
						<tr>
							<th>Tipo de Seleção</th>
							<th>Ano</th>
							<th>Edital</th>
							<th>Vagas</th>
							<th>Inscrições</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="inscricao" items="${inscricoes}">
							<tr class="linha">
								<sec:authorize access="hasAnyRole('DISCENTE')">
									<td><a id="detalhesSelecao"
										href="<c:url value="/inscricao/detalhes/${inscricao.id}"></c:url>">
											${inscricao.selecao.tipoSelecao.nome} </a></td>
								</sec:authorize>
								<td>${inscricao.selecao.ano}</td>
								<td>${inscricao.selecao.sequencial}</td>
								<td>${inscricao.selecao.quantidadeVagas}</td>
								<td class="dt-body-center">
									<fmt:formatDate pattern="dd/MM/yyyy" value="${inscricao.selecao.dataInicio}" /> à
									<fmt:formatDate pattern="dd/MM/yyyy" value="${inscricao.selecao.dataTermino }" />
								</td>
							</tr>
						</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>