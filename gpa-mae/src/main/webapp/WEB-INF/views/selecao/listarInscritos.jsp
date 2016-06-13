<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/bodyHeader.jsp" />
<title>Alunos inscritos</title>
</head>
<body>
	<jsp:include page="../fragments/headTag.jsp" />

	<div class="container">
		<div class="panel panel-primary" align="left">
				<div class="panel-heading">
					<h3 class="panel-title">Inscrições</h3>
				</div>
				<div class="panel-body">
					<table class="table" id="tabela-detalhes-selecao-servidores">
						<thead>
							<tr class="active">
								<td>Aluno</td>
								<td>Matricula</td>
								<td>Data</td>
								<td>Documentação</td>
								<td>Entrevista</td>
								<td>Visita</td>
								<td>Resultado</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="inscricao" items="${inscricoes }">
								<tr>
									<td><a id="detalhes"
										href="<c:url value="/servidor/detalhes/inscricao/${inscricao.id}">  
										</c:url>">
											${inscricao.aluno.pessoa.nome} </a></td>
									<td>${inscricao.aluno.matricula}</td>
									<td><fmt:formatDate value="${inscricao.data}"
											pattern="dd/MM/yyyy" /></td>
									<td><c:choose>
											<c:when
												test="${inscricao.deferimentoDocumentacao eq 'NAO_AVALIADO'}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when
												test="${inscricao.deferimentoDocumentacao eq 'DEFERIDO'}">
												<div class='def-sit deferido' title="Deferido">D</div>
											</c:when>

											<c:when
												test="${inscricao.deferimentoDocumentacao eq 'INDEFERIDO'}">
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