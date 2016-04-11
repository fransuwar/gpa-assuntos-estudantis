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
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Seleções</h3>
			</div>
			<div class="panel-body">
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
				<table class="table-display" id="tabela-selecoes">
					<thead>
						<tr>
							<th>Seleção</th>
							<th>Ano</th>
							<th>Vagas</th>
							<th>Inscrições</th>
					</thead>
					<tbody>
						<c:forEach var="selecao" items="${selecoes}">
							<tr>
								<td>
									<a id="detalhes" href="<c:url value="/selecao/detalhes/${selecao.id}"></c:url>">
										${selecao.tipoSelecao.nome}
									</a>
								</td>
								<td class="dt-body-center">${selecao.sequencial}/${selecao.ano}</td>
								<td class="dt-body-center">${selecao.quantidadeVagas}</td>
								<td class="dt-body-center">
									<fmt:formatDate pattern="dd/MM/yyyy" value="${selecao.dataInicio}" /> à
									<fmt:formatDate pattern="dd/MM/yyyy" value="${selecao.dataTermino }" />
								</td>
								<%-- <td>
									<sec:authorize access="hasAnyRole('DISCENTE')">
										<c:choose>
											<c:when
												test="${!aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == inic_acad and selecao.status == 'INSC_ABERTA'}">
												<a id="inscrever" title="Inscrever-se"
													href="<c:url value="/aluno/inscricao/${selecao.id}/iniciacao-academica" ></c:url>">
													<button class=" btn btn-primary btn-xs"
														title="Realizar Inscrição">
														<span class="glyphicon glyphicon-user"></span>
													</button>
												</a>
											</c:when>
											<c:when
												test="${aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == inic_acad and selecao.status == 'INSC_ABERTA'}">
												<a id="editar" title="Editar"
													href="<c:url value="/aluno/inscricao/editar/iniciacao-academica/${inscricao.id}" ></c:url>">
													<button class=" btn btn-primary btn-xs"
														title="Editar Inscrição">
														<span class="glyphicon glyphicon-pencil"></span>
													</button>
												</a>
											</c:when>
											<c:when
												test="${!aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == aux_mor and selecao.status == 'INSC_ABERTA'}">
												<a id="inscrever" title="Inscrever-se"
													href="<c:url value="/aluno/inscricao/${selecao.id}/auxilio-moradia" ></c:url>">
													<button class=" btn btn-primary btn-xs"
														Title="Realizar Inscrição">
														<span class="glyphicon glyphicon-user"></span>
													</button>
												</a>
											</c:when>
											<c:when
												test="${aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == aux_mor and selecao.status == 'INSC_ABERTA'}">
												<a id="editar" title="Editar"
													href="<c:url value="/aluno/inscricao/editar/auxilio-moradia/${inscricao.id}" ></c:url>">
													<button class=" btn btn-primary btn-xs"
														title="Editar Inscrição">
														<span class="glyphicon glyphicon-pencil"></span>
													</button>
												</a>
											</c:when>
										</c:choose>
									</sec:authorize>
								</td> --%>
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