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
<title>Seleções</title>
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

		<div class="col-md-14">

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
								<th class="dt-head-center">Edital</th>
								<th class="dt-head-center">Vagas</th>
								<th class="dt-head-center">Inscrições</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="selecao" items="${selecoes}">
								<tr>
									<td class="dt-body-center"><a id="detalhes"
										href="<c:url value="/selecao/detalhes/${selecao.id}">  </c:url>">
											${selecao.tipoSelecao.nome} </a></td>

									<td class="dt-body-center">${selecao.ano}</td>
									<td class="dt-body-center">${selecao.sequencial}</td>
									<td class="dt-body-center"><c:choose>
											<c:when test="${not empty selecao.quantidadeVagas}">
										${selecao.quantidadeVagas}
									</c:when>
											<c:otherwise>
										-
									</c:otherwise>
										</c:choose></td>
									<td class="dt-body-center"><fmt:formatDate
											pattern="dd/MM/yyyy" value="${selecao.dataInicio}" /> à <fmt:formatDate
											pattern="dd/MM/yyyy" value="${selecao.dataTermino }" /></td>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>