<!DOCTYPE html>
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
<html>
<head>
	<jsp:include page="../fragments/headTag.jsp" />
	<title>Minhas Inscrições</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
		<div class="col-md-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Minhas Inscrições</h3>
				</div>
				<table class="table-display" id="tabela-selecoes">
					<thead>
						<tr>
							<th>Tipo de Bolsa</th>
							<th>Ano</th>
							<th>Edital</th>
							<th>Vagas</th>
							<th>Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="inscricao" items="${inscricoes}">
							<tr class="linha">
								<sec:authorize access="hasAnyRole('DISCENTE')"><td>
								<a id="detalhesSelecao" href="<c:url value="/aluno/detalhes-inscricao/${inscricao.id}"></c:url>">
									${inscricao.selecao.tipoBolsa.nome} </a></td></sec:authorize>
								<td>${inscricao.selecao.ano}</td>
								<td>${inscricao.selecao.sequencial}</td>
								<td>${inscricao.selecao.quantidadeVagas}</td>
								<td>${inscricao.selecao.status.nome}</td>
								<td><sec:authorize access="hasAnyRole('DISCENTE')">
										<a id="editarInscricao"
											href="<c:url value="/selecao/inscritos/${aluno.id}" ></c:url>">
											<button class="btn btn-info btn-sm" title="Editar Inscrição">
												<span class="glyphicon glyphicon-pencil"></span>
											</button>
										</a>
										<a id="excluirInscricao"
											href="<c:url value="/inscricao/excluir/${idAluno}/${inscricao.id}" ></c:url>">
											<button class="btn btn-danger btn-sm"
												title="Excluir Inscrição">
												<i class="glyphicon glyphicon-trash"></i>
											</button>
										</a>
									</sec:authorize></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>