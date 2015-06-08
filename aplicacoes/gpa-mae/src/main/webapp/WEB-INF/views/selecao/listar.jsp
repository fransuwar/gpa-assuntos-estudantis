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
<jsp:include page="../fragments/bodyHeader.jsp" />
<title>Seleções</title>
</head>
<body>

	<jsp:include page="../fragments/headTag.jsp" />
	<ol class="breadcrumb">
		<li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
	</ol>
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

		<sec:authorize access="hasAnyRole('ROLE_COORDENADOR')">
			<div class="col-md-5" id="div-form-buscar">

				<ul class="nav nav-tabs">
					<li class="active"><a href="#buscar-edital-tab"
						data-toggle="tab">Buscar por Edital<i class="fa"></i>
					</a></li>
					<li><a href="#buscar-ano-tab" data-toggle="tab">Buscar por
							Ano<i class="fa"></i>
					</a></li>
					<li><a href="#buscar-tipo-tab" data-toggle="tab">Buscar
							por Tipo<i class="fa"></i>
					</a></li>

				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="buscar-edital-tab">
						<input id="editalBusca" name="edital" class="form-control"
							placeholder="Digite o edital a ser buscado" size="20"
							required="required" data-mask="999999" onkeyup="buscarSelecao();" />
					</div>
					<div class="tab-pane" id="buscar-ano-tab">
						<input id="anoBusca" name="ano" class="form-control"
							placeholder="Digite o ano a ser buscado" size="20"
							required="required" data-mask="9999" onkeyup="buscarSelecao();" />
					</div>
					<div class="tab-pane" id="buscar-tipo-tab">
						<form:select path="tipoBolsa" id="tipoBolsaBusca"
							class="form-control" onchange="buscarSelecao();">
							<form:option value="">Selecione o tipo de bolsa</form:option>
							<form:options items="${tipoBolsa}" itemLabel="nome"
								itemValue="nome" />
						</form:select>
					</div>
				</div>
			</div>

			<div align="right" style="margin-bottom: 20px;">
				<a href="<c:url value="/selecao/cadastrar" ></c:url>">
					<button class="btn btn-primary">
						Nova seleção <span class="glyphicon glyphicon-plus"></span>
					</button>
				</a>
			</div>
		</sec:authorize>
		<div class="panel panel-default pane-edit">

			<div class="panel-heading">
				<h4>Seleções</h4>
			</div>

			<!-- Table -->
			<table class="table" id="table">
				<thead>
					<tr>
						<th>Tipo de Bolsa</th>
						<th>Ano</th>
						<th>Número do Edital</th>
						<th>Vagas</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="selecao" items="${selecoes}">
						<tr class="linha">

							<td>${selecao.tipoBolsa.nome}</td>
							<td>${selecao.ano}</td>
							<td>${selecao.sequencial}</td>
							<td>${selecao.quantidadeVagas}</td>
							<td>${selecao.status.nome}</td>

							<td><sec:authorize
									access="hasAnyRole('ROLE_COORDENADOR', 'ROLE_ADMIN')">
									<a id="visualizarInscritos"
										href="<c:url value="/selecao/inscritos/${selecao.id}" ></c:url>">
										<button class="btn btn-primary">
											Visualizar Inscritos <span class="glyphicon glyphicon-user"></span>
										</button>
									</a>
								</sec:authorize> <sec:authorize access="hasAnyRole('ROLE_COORDENADOR')">
									<a id="editar"
										href="<c:url value="/selecao/editar/${selecao.id}" ></c:url>">
										<button class="btn btn-info">
											Editar <span class="glyphicon glyphicon-pencil"></span>
										</button>
									</a>
									<a id="excluir" data-toggle="modal"
										data-target="#confirm-delete" href="#"
										data-href="<c:url value="/selecao/excluir/${selecao.id}" ></c:url>">
										<button class="btn btn-danger">
											Excluir <span class="glyphicon glyphicon-trash"></span>
										</button>
									</a>

									<a id="atribuirBanca"
										href="<c:url value="/selecao/atribuir/${selecao.id}" ></c:url>">
										<c:choose>
											<c:when test="${empty selecao.membrosBanca}">
												<button class="btn btn-primary">
													Atribuir Membro à Banca <span
														class="glyphicon glyphicon-user"></span>
												</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-primary">
													Editar Membros da Banca <span
														class="glyphicon glyphicon-user"></span>
												</button>
											</c:otherwise>
										</c:choose>
									</a>

								</sec:authorize> <sec:authorize access="hasAnyRole('ROLE_ALUNO')">
									<c:choose>
										<c:when
											test="${!aluno.editais.contains(selecao) && selecao.tipoBolsa == inic_acad && selecao.status == 'INSC_ABERTA'}">
											<a id="inscrever"
												href="<c:url value="/iniciacaoAcademica/inscricao/${selecao.id}/" ></c:url>">
												<button class=" btn btn-success">
													inscrever-se <span class="glyphicon glyphicon-user"></span>
												</button>
											</a>
										</c:when>
										<c:when
											test="${aluno.editais.contains(selecao) && selecao.tipoBolsa == inic_acad && selecao.status == 'INSC_ABERTA'}">
											<a id="editar"
												href="<c:url value="/iniciacaoAcademica/editar/${sessionScope.id}/" ></c:url>">
												<button class=" btn btn-info">
													editar <span class="glyphicon glyphicon-pencil"></span>
												</button>
											</a>
										</c:when>
										<c:when
											test="${!aluno.editais.contains(selecao) && selecao.tipoBolsa == aux_mor && selecao.status == 'INSC_ABERTA'}">
											<a id="inscrever"
												href="<c:url value="/auxilio/inscricao/${selecao.id}/" ></c:url>">
												<button class=" btn btn-success">
													inscrever-se <span class="glyphicon glyphicon-user"></span>
												</button>
											</a>
										</c:when>
										<c:when
											test="${aluno.editais.contains(selecao) && selecao.tipoBolsa == aux_mor && selecao.status == 'INSC_ABERTA'}">
											<a id="editar"
												href="<c:url value="/auxilio/editar/${sessionScope.id}/" ></c:url>">
												<button class=" btn btn-info">
													editar <span class="glyphicon glyphicon-pencil"></span>
												</button>
											</a>
										</c:when>
									</c:choose>
								</sec:authorize> <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
									<c:if test="${avaliar}">
										<a id="avaliarSelecao" href="<c:url value="" ></c:url>">
											<button class="btn btn-primary">
												Avaliar Inscritos <span class="glyphicon glyphicon-user"></span>
											</button>
										</a>
									</c:if>
								</sec:authorize> <sec:authorize access="isAnonymous()">

									<a id="informacoes"
										href="<c:url value="/selecao/informacoes/${selecao.id}"></c:url>">
										<button class=" btn btn-success">
											+ Informações <span class="glyphicon glyphicon-zoom-in"></span>
										</button>
									</a>
								</sec:authorize></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
