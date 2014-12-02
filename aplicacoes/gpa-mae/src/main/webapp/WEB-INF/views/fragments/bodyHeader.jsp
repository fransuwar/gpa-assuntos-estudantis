<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="header-page">
	<div class="row">
		<div class="col-md-6">
			<img width="370"
				src="<c:url value="/resources/images/brasao-qxd.png" />"
				alt="Brasão UFC Quixadá">
		</div>
		<div class="col-md-6"></div>
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
				<a class="navbar-brand" href="<c:url value="#" />">GPA - MAE</a>
			</div>
		</sec:authorize>
	
		<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<li>
						<a href="<c:url value="/servidor/listar" />">Listar Servidor <span class="glyphicon glyphicon-list"></span></a>
					</li>
					<li>
						<a href="<c:url value="/aluno/listar" />">Listar Alunos <span class="glyphicon glyphicon-plus"></span></a>
					</li>
					</sec:authorize>

					<sec:authorize ifAllGranted="ROLE_COORDENADOR, ROLE_ALUNO" >
					<li>
						<a href="<c:url value="/selecao/listar" />">Listar Seleções<span class="glyphicon glyphicon-plus"></span></a>
					</li>				
					</sec:authorize>
					<sec:authorize ifAllGranted="ROLE_COORDENADOR">
					<li>
						<a href="<c:url value="/aluno/listar" />">Listar Alunos <span class="glyphicon glyphicon-plus"></span></a>
					</li>
					<li>
						<a href="<c:url value="/selecao/listar" />">Listar Seleções <span class="glyphicon glyphicon-plus"></span></a>
					</li>
					</sec:authorize>

					<sec:authorize access="isAuthenticated()">

					<li>
						<a href="<c:url value="/j_spring_security_logout" />">Sair <span class="glyphicon glyphicon-off"></span></a>
					</li>
					</sec:authorize>
					
					<sec:authorize access="isAnonymous()">
					<li>
						<a href="<c:url value="/login" />">Login </a>
					</li>
					</sec:authorize>
					
				</ul>
				<sec:authorize access="isAuthenticated()">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a style="font-weight: bold;">Bem vindo, ${sessionScope.usuario.nome}!</a> 
					</li>
				</ul>
				</sec:authorize>
			</div>
		</div>
	</nav>


</div>
