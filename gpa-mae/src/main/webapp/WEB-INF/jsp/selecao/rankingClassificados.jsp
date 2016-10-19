
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../fragments/headTag.jsp" />
<title>Ranking dos Classificados</title>
</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container" align="left" style="padding-left: 85px;">
		<div class="panel panel-primary" align="left">
			<div class="panel-heading">
				<h3 class="panel-title">Alunos Classificados</h3>
			</div>
			<div class="panel-body">

				<form id="formClassificados" method="POST"
					action="<c:url value="/selecao/ranking"/>">

					<table class="table" id="tabela-ranking-classificados">
						<thead>
							<tr class="active">
								<td>Aluno</td>
								<td>Nome</td>
							</tr>
						</thead>
						<tbody>
							<sec:authorize
								access="hasAnyRole('COORDENADOR_ASSUNTOS_ESTUDANTIS','STA')">
								<c:forEach var="classificado" items="${classificados}">
									<tr>
										<td class="col-sm-1"><a id="detalhes"
											href="<c:url value="/servidor/detalhes/inscricao/${classificado.id}">  
										</c:url>"
											class="thumbnail"> <img
												src="<c:url value="/resources/images/gpa-icone.jpg"/>"
												alt="Foto do Aluno" style="height: 60px; width: 100%"
												class="img-rounded">
										</a></td>
										<td class="col-sm-11"><a id="detalhes"
											href="<c:url value="/servidor/detalhes/inscricao/${classificado.id}">  
										</c:url>">${classificado.aluno.pessoa.nome}</a></td>
									</tr>
								</c:forEach>
							</sec:authorize>
						</tbody>

					</table>
					<button class="btn btn-primary">Voltar</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
