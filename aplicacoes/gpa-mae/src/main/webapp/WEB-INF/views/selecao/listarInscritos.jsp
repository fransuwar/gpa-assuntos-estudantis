<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/bodyHeader.jsp" />
<title>Alunos inscritos</title>
</head>
<body>
	<jsp:include page="../fragments/headTag.jsp" />
	<ol class="breadcrumb">
		<li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
    	<li>Listar Inscritos</li>
    </ol>
    <div class="container">
    	<sec:authorize access="hasAnyRole('ROLE_COORDENADOR', 'ROLE_ADMIN')">
    		<div class="panel-heading" align="center">


				<h4>Alunos inscritos</h4>
			</div>
			<table class="table" id="table">
				<thead>
					<tr>
						<th>Matricula</th>
						<th>Nome</th>
						<th>Curso</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="aluno" items="${alunos}">
						<tr class="linha">
							<td>${aluno.matricula}</td>
							<td>${aluno.nome}</td>
							<td>${aluno.curso}</td>
							
							<td>
							<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
								<a id="preencherRelatorio"
									href="<c:url value="relatorioVisita/${aluno.id}" ></c:url>">
									<button class="btn btn-primary">
										Relatório de Visita Domiciliar <span class="glyphicon glyphicon-user"></span>
									</button>
								</a>
							</sec:authorize>
							</td>
							
							<td><button class="btn btn-primary">Visualizar Formulário</button></td>
							<td><button class="btn btn-primary">Visualizar
									Entrevista</button></td>
							<td><button class="btn btn-primary">Visualizar
									Visita</button></td>
							<td>
								<div class="checkbox">
									<label> <input type="checkbox" value="">
										Selecionar Aluno
									</label>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="col-md-2 col-md-offset-5">
				<button class="btn btn-success">Emitir Parecer</button>
			</div>
		</sec:authorize>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>