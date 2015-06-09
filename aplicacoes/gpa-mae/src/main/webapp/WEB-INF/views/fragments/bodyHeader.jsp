<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="header-page">
	<div class="row">
		<div class="col-md-6">
			<img src="<c:url value="/resources/images/logo-GPA.png" />"
				alt="Brasão UFC Quixadá">
		</div>
		<div class="col-md-3 col-md-offset-3">
			<sec:authorize access="isAuthenticated()">
				<ul class="logado">
					<li><h4>Bem vindo, ${sessionScope.usuario}!</h4></li>
					<li><a href="<c:url value="/j_spring_security_logout" />">Sair
							<span class="glyphicon glyphicon-off"></span>
					</a></li>
				</ul>
			</sec:authorize>
		</div>
	</div>
</div>
<div>


	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">

			<sec:authorize access="isAuthenticated()">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<c:url value="#" />"><font>GPA-MAE</font></a>
				</div>
			</sec:authorize>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize ifAnyGranted="ROLE_ADMIN">

						<li role="presentation"><a
							href="<c:url value="/servidor/listar" />">Listar Servidor <span
								class="glyphicon glyphicon-list"></span>
						</a></li>
						<li role="presentation"><a
							href="<c:url value="/selecao/listarPorServidor/${sessionScope.id}" />">Listar
								Seleções <span class="glyphicon glyphicon-list"></span>
						</a></li>
						<li role="presentation"><a
							href="<c:url value="/aluno/listar" />">Listar Alunos <span
								class="glyphicon glyphicon-list"></span>
						</a></li>
					</sec:authorize>

					<sec:authorize ifAllGranted="ROLE_ALUNO">
						<input type="hidden" name="id" value="${sessionScope.id}" />
						<li role="presentation"><a
							href="<c:url value="/selecao/listar" />">Listar
								Seleções <span class="glyphicon glyphicon-list"></span>
						</a></li>
					</sec:authorize>
					<sec:authorize ifAllGranted="ROLE_COORDENADOR">
						<li role="presentation"><a
							href="<c:url value="/aluno/listar" />">Listar Alunos <span
								class="glyphicon glyphicon-list"></span>
						</a></li>
						<li role="presentation"><a
							href="<c:url value="/selecao/listar" />">Listar
								Seleções <span class="glyphicon glyphicon-list"></span>
						</a></li>
					</sec:authorize>

					<sec:authorize access="isAnonymous()">
						<li><a href="<c:url value="/login" />">Login </a></li>
					</sec:authorize>
				</ul>
			</div>

		</div>
	</nav>

</div>
