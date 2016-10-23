<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
	<title>Seleções</title>
	<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<div class="container">
		
		<jsp:include page="../fragments/alert.jsp" />
		
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-body">
					<h2>Seleções</h2>
					<div class="divider"></div>
					<c:if test="${empty selecoes}">
						<div class="alert alert-warning">
							Não há nenhuma seleção
						</div>
					</c:if>
					<c:if test="${not empty selecoes}">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="dt-head-center">Ano</th>
									<th class="dt-head-center">Vagas</th>
									<th class="dt-head-center">Inscrições</th>
									<th class="dt-head-center">Período de Inscrições</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="selecao" items="${selecoes}">
									<tr>
										<td class="dt-body-center">
											<a id="detalhes" href="<c:url value="/servidor/selecao/detalhes/${selecao.id}"></c:url>">
												${selecao.sequencial}/${selecao.ano}
											</a>
										</td>
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
										<td class="dt-body-center">${selecao.inscricoes.size() }</td>
										<td class="dt-body-center"><fmt:formatDate
												pattern="dd/MM/yyyy" value="${selecao.dataInicio}" /> à <fmt:formatDate
												pattern="dd/MM/yyyy" value="${selecao.dataTermino}" /></td>
										<sec:authorize access="hasRole('COORDENADOR_ASSUNTOS_ESTUDANTIS')">
											<td class="dt-body-right"><a
												href="<c:url value="/coordenador/selecao/editar/${selecao.id}" ></c:url>">
													<button class="btn btn-info btn-small btn-xs" title="Editar Seleção">
														<i class="fa fa-pencil-square-o"></i>
													</button>
											</a> 
											 <c:if test="${selecao.inscritos.isEmpty() }">
													<a id="excluir" data-toggle="modal"
														data-target="#confirm-delete" href="#"
														data-href="<c:url value="/coordenador/selecao/excluir/${selecao.id}" ></c:url>">
														<button class="btn btn-small btn-danger btn-xs"
															title="Excluir Seleção">
															<i class="fa fa-trash"></i>
														</button>
													</a>
												</c:if></td>
										</sec:authorize>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
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
