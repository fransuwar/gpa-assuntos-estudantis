<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">

			<sec:authorize access="isAuthenticated() || isAnonymous()">
				<div class="navbar-header">
					<a class="navbar-brand" id="navbar-brand" href="#"> <img
						src="<c:url value="/resources/images/logo-GPA.png" />"
						alt="Brasão UFC Quixadá" id="logo-gpa">
					</a>
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
			</sec:authorize>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize ifAnyGranted="SERVIDOR">

						<li role="presentation"><a
							href="<c:url value="/servidor/listar" />">Listar Servidor <span
								class="glyphicon glyphicon-list"></span>
						</a></li>
						<li role="presentation"><a
							href="<c:url value="/selecao/listarPorServidor/${sessionScope.id}" />">Listar
								Seleções <span class="glyphicon glyphicon-list"></span>
						</a></li>
						<li role="presentation"><a
							href="<c:url value="/servidor/listar/alunos" />">Listar Alunos <span
								class="glyphicon glyphicon-list"></span>
						</a></li>
					</sec:authorize>

					<sec:authorize ifAllGranted="DISCENTE">
						<input type="hidden" name="id" value="${sessionScope.id}" />
						<li role="presentation"><a
							href="<c:url value="/selecao/listar" />">Listar Seleções <span
								class="glyphicon glyphicon-list"></span>
						</a></li>
					</sec:authorize>
					<sec:authorize ifAllGranted="COORD_ASS_ESTUDANTIS">
						<li role="presentation"><a
							href="<c:url value="/servidor/listar/alunos" />">Listar Alunos <span class="glyphicon glyphicon-list"></span>
						</a></li>
						<li role="presentation"><a
							href="<c:url value="/selecao/listar" />">Listar Seleções <span
								class="glyphicon glyphicon-list"></span>
						</a></li>
					</sec:authorize>

					<sec:authorize access="isAuthenticated()">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">${sessionScope.usuario}<span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/j_spring_security_logout" />">Sair</a></li>
							</ul></li>
					</sec:authorize>

					<sec:authorize access="isAnonymous()">
						<li><a href="<c:url value="/login" />">Login </a></li>
					</sec:authorize>
				</ul>
			</div>

		</div>
	</nav>

</div>
