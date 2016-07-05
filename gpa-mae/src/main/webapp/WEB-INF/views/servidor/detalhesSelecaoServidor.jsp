<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:choose>
	<c:when test="${todosConsolidados}">	
		<c:url var="consolidar" value="${false}" />
		<c:set var="botao" value="Desconsolidar Todos" />
	</c:when>
	<c:otherwise>
		<c:url var="consolidar" value="${true}" />
		<c:set var="botao" value="Consolidar Todos" />
	</c:otherwise>

</c:choose>



<html>
<head>
<jsp:include page="../fragments/bodyHeader.jsp" />
<title>Detalhes da seleção</title>
</head>
<body>
	<jsp:include page="../fragments/headTag.jsp" />
	<div class="container" align="left">
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
		
		<jsp:include page="../fragments/cards.jsp" />

		<sec:authorize
			access="hasAnyRole('COORDENADOR_ASSUNTOS_ESTUDANTIS', 'STA', 'DOCENTE')">
			<div class="panel panel-primary" align="left">
				<div class="panel-heading">
					<h3 class="panel-title">Inscrições</h3>
				</div>
				<div class="panel-body">
							<c:if test="${selecao.inscritos.size()>0 }">
								<div align="right">
									<input id="idSelecao" type="hidden" value="${selecao.id}">
									<button id="consolidacaoTodos" class="btn btn-primary" >Consolidar Todos </button>
									<button id="desconsolidacaoTodos" class="btn btn-primary" > Desconsolidar Todos </button>
								</div>
							</c:if>

							<table class="table" id="tabela-detalhes-selecao-servidores">
						<thead>
							<tr class="active">
								<td>Aluno</td>
								<td>Matricula</td>
								<td>Curso</td>
								<td>Data</td>
								<td>Documentação</td>
								<td>Entrevista</td>
								<td>Visita</td>
								<td>Resultado</td>
								<td>Consolidação</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="inscricao" items="${inscricoes}">
								<tr>
									<td><a id="detalhes"
										href="<c:url value="/inscricao/detalhes/inscricao/${inscricao.id}">  
										</c:url>">
											${inscricao.aluno.pessoa.nome} </a></td>
									<td>${inscricao.aluno.matricula}</td>
									<td>${inscricao.aluno.curso.nome}</td>
									<td><fmt:formatDate value="${inscricao.data}"
											pattern="dd/MM/yyyy HH:mm" /></td>
									<td><c:choose>
											<c:when
												test="${inscricao.documentacao.deferimento eq 'NAO_AVALIADO'}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when test="${empty inscricao.documentacao.deferimento}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when
												test="${inscricao.documentacao.deferimento eq 'DEFERIDO'}">
												<div class='def-sit deferido' title="Deferido">D</div>
											</c:when>

											<c:when
												test="${inscricao.documentacao.deferimento eq 'INDEFERIDO'}">
												<div class='def-sit indeferido' title="Indeferido">I</div>
											</c:when>
										</c:choose></td>

									<td><c:choose>
											<c:when
												test="${inscricao.entrevista.deferimento eq 'NAO_AVALIADO'}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when test="${empty inscricao.entrevista.deferimento}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when
												test="${inscricao.entrevista.deferimento eq 'DEFERIDO'}">
												<div class='def-sit deferido' title="Deferido">D</div>
											</c:when>

											<c:when
												test="${inscricao.entrevista.deferimento eq 'INDEFERIDO'}">
												<div class='def-sit indeferido' title="Indeferido">I</div>
											</c:when>
										</c:choose></td>

									<td><c:choose>
											<c:when
												test="${inscricao.visitaDomiciliar.deferimento eq 'NAO_AVALIADO'}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when
												test="${empty inscricao.visitaDomiciliar.deferimento}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when
												test="${inscricao.visitaDomiciliar.deferimento eq 'DEFERIDO'}">
												<div class='def-sit deferido' title="Deferido">D</div>
											</c:when>

											<c:when
												test="${inscricao.visitaDomiciliar.deferimento eq 'INDEFERIDO'}">
												<div class='def-sit indeferido' title="Indeferido">I</div>
											</c:when>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${inscricao.resultado eq 'NAO_AVALIADO'}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when test="${inscricao.resultado eq 'DEFERIDO'}">
												<div class='def-sit deferido' title="Deferido">D</div>
											</c:when>

											<c:when test="${inscricao.resultado eq 'INDEFERIDO'}">
												<div class='def-sit indeferido' title="Indeferido">I</div>
											</c:when>
										</c:choose></td>
										
										<td>
											<input id="${inscricao.id}" class="toggle-event" type="checkbox" data-toggle="toggle" data-style="ios" data-size="small"
											<c:if test="${inscricao.consolidacao}">checked </c:if> >
									</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
					
					<div>
						<div class='legenda'>
							<div><div class='def-sit deferido' title="Deferido">D</div> Deferido</div>
							<div><div class='def-sit indeferido' title="Indeferido">I</div> Indeferido</div>
							<div><div class='def-sit nao-avaliado' title="Não avaliado">N</div> Não avaliado</div>
						</div>
					</div>
				</div>
			</div>
		</sec:authorize>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>