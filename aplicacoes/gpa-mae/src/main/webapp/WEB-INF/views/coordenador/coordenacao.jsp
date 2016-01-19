<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<c:out value="${erro}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty info}">
					<div class="alert alert-success alert-dismissible" role="alert"
						id="alert-info">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<c:out value="${info}"></c:out>
					</div>
				</c:if>
			
				<table class="display" id="tabela-selecoes">
					<thead>
						<tr>
							<th>Seleção</th>
							<th>Edital</th>
							<th>Vagas</th>
							<th>Incritos</th>
							<th>Período de Inscrições</th>
							<th></th>
						</tr>
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
								<td class="dt-body-center">${selecao.inscritos.size() }</td>
								<td class="dt-body-center"><fmt:formatDate pattern="dd/MM/yyyy" value="${selecao.dataInicio}" /> a <fmt:formatDate pattern="dd/MM/yyyy" value="${selecao.dataTermino}" /></td>
								<td class="dt-body-right">
									<a href="<c:url value="/coordenador/selecao/editar/${selecao.id}" ></c:url>">
										<button class="btn btn-primary btn-xs" title="Editar Seleção">
											<i class="fa fa-pencil-square-o"></i>
										</button>
									</a>
									<a href="<c:url value="/coordenador/comissao/atribuir/${selecao.id}" ></c:url>">
										<button class="btn btn-primary btn-xs" title="Gerenciar Comissão">
											<i class="fa fa-users"></i>
										</button>
									</a>
									<c:if 
									test="${selecao.inscritos.isEmpty() }">
									<a id="excluir" data-toggle="modal" data-target="#confirm-delete" href="#"
										data-href="<c:url value="/coordenador/selecao/excluir/${selecao.id}" ></c:url>">
										<button class="btn btn-danger btn-xs" title="Excluir Seleção">
											<i class="fa fa-trash"></i>
										</button>
									</a>
									</c:if>
								</td>

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
					<div class="modal-body">Tem certeza de que deseja excluir essa seleção?</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-danger">Excluir</a>
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
