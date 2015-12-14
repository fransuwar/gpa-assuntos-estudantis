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
			<div class="panel panel-info">


				<div class="panel-heading">
					<h3 class="panel-title">Seleções</h3>
				</div>
				<table class="table table-striped table-bordered"
					id="tabela-selecoes">
					<thead>
						<tr>
							<th>Tipo de Seleção</th>
							<th>Ano</th>
							<th>Edital</th>
							<th>Vagas</th>
							<th>Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="selecao" items="${selecoes}">
							<tr class="linha">
								<td><a id="detalhes"
									href="<c:url value="/selecao/detalhes/${selecao.id}">  </c:url>">
										${selecao.tipoSelecao.nome} </a></td>
								<td>${selecao.ano}</td>
								<td>${selecao.sequencial}</td>
								<td>${selecao.quantidadeVagas}</td>
								<td>${selecao.status.nome}</td>
								<td><a id="visualizarInscritos"
									href="<c:url value="/selecao/inscritos/${selecao.id}" ></c:url>">
										<button class="btn btn-primary btn-sm"
											title="Visualizar Inscritos">
											<i class="fa fa-users fa-lg"></i>
										</button>
								</a> <a id="excluir" data-toggle="modal"
									data-target="#confirm-delete" href="#"
									data-href="<c:url value="/coordenador/selecao/excluir/${selecao.id}" ></c:url>">
										<button class="btn btn-danger btn-sm" Title="Excluir Seleção">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
								</a> <sec:authorize access="hasAnyRole('DISCENTE')">
										<c:choose>
											<c:when
												test="${!aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == inic_acad and selecao.status == 'INSC_ABERTA'}">
												<a id="inscrever"
													href="<c:url value="/aluno/inscricao/${selecao.id}/iniciacao-academica" ></c:url>">
													<button class=" btn btn-success btn-sm"
														title="Realizar Inscrição">
														<span class="glyphicon glyphicon-user"></span>
													</button>
												</a>
											</c:when>
											<c:when
												test="${aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == inic_acad and selecao.status == 'INSC_ABERTA'}">
												<a id="editar"
													href="<c:url value="/aluno/inscricao/editar/iniciacao-academica/${inscricao.id}" ></c:url>">
													<button class=" btn btn-info btn-sm"
														title="Editar Inscrição">
														<span class="glyphicon glyphicon-pencil"></span>
													</button>
												</a>
											</c:when>
											<c:when
												test="${!aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == aux_mor and selecao.status == 'INSC_ABERTA'}">
												<a id="inscrever"
													href="<c:url value="/aluno/inscricao/${selecao.id}/auxilio-moradia" ></c:url>">
													<button class=" btn btn-success btn-sm"
														Title="Realizar Inscrição">
														<span class="glyphicon glyphicon-user"></span>
													</button>
												</a>
											</c:when>
											<c:when
												test="${aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == aux_mor and selecao.status == 'INSC_ABERTA'}">
												<a id="editar"
													href="<c:url value="/aluno/inscricao/editar/auxilio-moradia/${inscricao.id}" ></c:url>">
													<button class=" btn btn-info btn-sm"
														title="Editar Inscrição">
														<span class="glyphicon glyphicon-pencil"></span>
													</button>
												</a>
											</c:when>
										</c:choose>
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
					<a href="#" class="btn btn-danger">Excluir</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
