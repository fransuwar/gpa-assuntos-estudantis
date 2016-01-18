<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>


<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Lista de Seleções</title>
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

				<table class="table table-display table-striped"
					id="tabela-selecoes">
					<thead>
						<tr>
							<th>Tipo de Seleção</th>
							<th>Ano</th>
							<th>Edital</th>
							<th>Vagas</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="selecao" items="${selecoes}">
							<tr class="linha">
								<td><a id="detalhes"
									href="<c:url value="/servidor/detalhes/${selecao.id}">  </c:url>">
										${selecao.tipoSelecao.nome} </a></td>
								<td>${selecao.ano}</td>
								<td>${selecao.sequencial}</td>
								<td>${selecao.quantidadeVagas}</td>
								<td><a id="visualizarInscritos"
									href="<c:url value="/servidor/inscritos/${selecao.id}" ></c:url>">
										<button class="btn btn-primary btn-sm"
											title="Visualizar Inscritos">
											<i class="fa fa-users fa-lg"></i>
										</button>
								</a> <c:if test="${avaliar}">
										<a id="avaliarSelecao" title="Avaliar Inscritos"
											href="<c:url value="/selecao/inscritos/${selecao.id}" ></c:url>">
											<button class="btn btn-primary btn-xs">
												<span class="glyphicon glyphicon-user"></span>
											</button>
										</a>
									</c:if> <sec:authorize access="isAnonymous()">

										<a id="informacoes" title="Informações"
											href="<c:url value="/selecao/informacoes/${selecao.id}"></c:url>">
											<button class="btn btn-primary btn-xs" title="Informações">
												<span class="glyphicon glyphicon-zoom-in"></span>
											</button>
										</a>
									</sec:authorize>
							</tr>
						</c:forEach>
					</tbody>
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
					<a href="#" class="btn btn-danger btn-xs">Excluir</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>