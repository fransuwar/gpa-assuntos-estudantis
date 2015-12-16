<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

		<div class="col-md-13">

			<div align="left" style="margin-bottom: 20px;">
				<a href="<c:url value="/coordenador/selecao/cadastrar" ></c:url>">
					<button class="btn btn-primary">
						Nova seleção <span class="glyphicon glyphicon-plus"></span>
					</button>
				</a>
			</div>

		</div>

		<div class="col-md-14">
			<div class="panel panel-info">

				<div class="panel-heading">
					<h3 class="panel-title">Seleções</h3>
				</div>

				<table class="table display" id="tabela-selecoes">
					<thead>
						<tr>
							<th>Tipo de Seleção</th>
							<th>Edital</th>
							<th>Período da Inscrição</th>
							<th>Vagas</th>
							<th>Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="selecao" items="${selecoes}">
							<tr class="linha">
								<td><a id="detalhes"
									href="<c:url value="/selecao/detalhes/${selecao.id}"></c:url>">
										${selecao.tipoSelecao.nome}</a></td>
								<td>${selecao.ano}.${selecao.sequencial}</td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${selecao.dataInicio}" /> à <fmt:formatDate pattern="dd/MM/yyyy" value="${selecao.dataTermino}" /></td>
								<td>${selecao.quantidadeVagas}</td>
								<td>${selecao.status.nome}</td>

								<td><a id="visualizarInscritos"
									href="<c:url value="/servidor/inscritos/${selecao.id}" ></c:url>">
										<button class="btn btn-primary btn-sm"
											title="Visualizar Inscritos">
											<i class="fa fa-users fa-lg"></i>
										</button>
								</a> <a id="editar"
									href="<c:url value="/coordenador/selecao/editar/${selecao.id}" ></c:url>">
										<button class="btn btn-info btn-sm" title="Editar Seleção">
											<span class="glyphicon glyphicon-pencil"></span>
										</button>
								</a> <a id="excluir" data-toggle="modal"
									data-target="#confirm-delete" href="#"
									data-href="<c:url value="/coordenador/selecao/excluir/${selecao.id}" ></c:url>">
										<button class="btn btn-danger btn-sm" title="Excluir Seleção">
											<span class="glyphicon glyphicon-trash"></span>

										</button>
								</a> <a id="atribuirComissao"
									href="<c:url value="/coordenador/comissao/atribuir/${selecao.id}" ></c:url>">
										<c:choose>
											<c:when test="${empty selecao.membrosComissao}">
												<button class="btn btn-primary btn-sm"
													title="Atribuir Membro à Comissão">
													<span class="glyphicon glyphicon-user"></span>
												</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-primary btn-sm"
													title="Editar Membros da Comissão">
													<span class="glyphicon glyphicon-user"></span>
												</button>
											</c:otherwise>
										</c:choose>
								</a> <c:if test="${avaliar}">
										<a id="avaliarSelecao"
											href="<c:url value="/selecao/inscritos/${selecao.id}" ></c:url>">
											<button class="btn btn-primary btn-sm">
												Avaliar Inscritos <span class="glyphicon glyphicon-user"></span>


											</button>
										</a>
									</c:if>
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
