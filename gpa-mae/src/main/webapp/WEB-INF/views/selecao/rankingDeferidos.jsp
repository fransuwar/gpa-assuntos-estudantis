
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="gpa" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../fragments/headTag.jsp" />
<title>Ranking dos Deferidos</title>
</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container" align="left" style="padding-left: 85px;">
		<div class="panel panel-primary" align="left">
			<div class="panel-heading">
				<h3 class="panel-title">Alunos Deferidos</h3>
			</div>
			<div class="panel-body">

				<form id="formClassificados" method="POST"
					enctype="multipart/form-data"
					action="<c:url value="/selecao/ranking"/>">

					<table class="table" id="tabela-ranking-deferidos">
						<thead>
							<tr class="active">
								<td>Aluno</td>
								<td>Nome</td>
							</tr>
						</thead>
						<tbody>
							<sec:authorize
								access="hasAnyRole('COORDENADOR_ASSUNTOS_ESTUDANTIS','STA')">
								<c:forEach var="deferido" items="${deferidos}" varStatus="linha">
									<tr>
										<td class="col-sm-1"><a id="detalhes"
											href="<c:url value="/servidor/detalhes/inscricao/${deferido.id}">  
										</c:url>"
											class="thumbnail"> <img
												src="<c:url value="/resources/images/gpa-icone.jpg"/>"
												alt="Foto do Aluno" style="height: 60px; width: 100%"
												class="img-rounded">
										</a></td>
										<td class="col-sm-11"><a id="detalhes"
											href="<c:url value="/servidor/detalhes/inscricao/${deferido.id}">  
										</c:url>">${deferido.aluno.pessoa.nome}</a></td>
									</tr>
								</c:forEach>
							</sec:authorize>
						</tbody>

					</table>
					<input class="btn btn-primary" type="submit" value="Submeter" />
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
