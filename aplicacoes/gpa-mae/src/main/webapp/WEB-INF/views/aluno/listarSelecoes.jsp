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

				<table class="table display" id="tabela-selecoes">
					<thead>
						<tr>
							<th>Seleção</th>
							<th>Edital</th>
							<th>Vagas</th>
							<th>Inscrições</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="selecao" items="${selecoes}">
							<tr>
								<td><a id="detalhes"
									href="<c:url value="/selecao/detalhes/${selecao.id}">  </c:url>">
										${selecao.tipoSelecao.nome} </a></td>
								<td>${selecao.ano}</td>
								<td>${selecao.sequencial}</td>
								<td>${selecao.quantidadeVagas}</td>
								<td><c:if test="${not empty selecao.inscritos }">
										<c:forEach items="${selecao.inscritos}" var="inscricao">
											<c:choose>
												<c:when
													test="${!aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == inic_acad}">
													<a id="inscrever" title="Inscrever-se"
														href="<c:url value="/aluno/inscricao/${selecao.id}/iniciacao-academica" ></c:url>">
														<button class=" btn btn-primary btn-xs">
															<span class="glyphicon glyphicon-user"></span>
														</button>
													</a>
												</c:when>
												<c:when
													test="${aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == inic_acad }">
													<a id="editar" title="Editar"
														href="<c:url value="/aluno/inscricao/editar/iniciacao-academica/${inscricao.id }" ></c:url>">
														<button class=" btn btn-primary btn-xs"
															title="Editar Inscrição">
															<span class="glyphicon glyphicon-pencil"></span>
														</button>
													</a>
												</c:when>
												<c:when
													test="${!aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == aux_mor}">
													<a id="inscrever" title="Inscrever-se"
														href="<c:url value="/aluno/inscricao/${selecao.id}/auxilio-moradia" ></c:url>">
														<button class="btn btn-primary btn-xs">
															<span class="glyphicon glyphicon-user"></span>
														</button>
													</a>
												</c:when>
												<c:when
													test="${aluno.inscricoes.contains(inscricao) and selecao.tipoSelecao == aux_mor }">
													<a id="editar" title="Editar"
														href="<c:url value="/aluno/inscricao/editar/auxilio-moradia/${inscricao.id }" ></c:url>">
														<button class=" btn btn-primary btn-xs"
															title="Editar Inscrição">
															<span class="glyphicon glyphicon-pencil"></span>
														</button>
													</a>
												</c:when>

											</c:choose>
										</c:forEach>
									</c:if> <c:if test="${empty selecao.inscritos}">
										<c:choose>
											<c:when test="selecao.tipoSelecao == inic_acad">
												<a id="inscrever" title="Inscrever-se"
													href="<c:url value="/aluno/inscricao/${selecao.id}/iniciacao-academica" ></c:url>">
													<button class=" btn btn-primary btn-xs">
														<span class="glyphicon glyphicon-user"></span>
													</button>
												</a>
											</c:when>
											<c:otherwise>
												<a id="inscrever" title="Inscrever-se"
													href="<c:url value="/aluno/inscricao/${selecao.id}/auxilio-moradia" ></c:url>">
													<button class="btn btn-primary btn-xs">

														<span class="glyphicon glyphicon-user"></span>
													</button>
												</a>
											</c:otherwise>
										</c:choose>
									</c:if></td>
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