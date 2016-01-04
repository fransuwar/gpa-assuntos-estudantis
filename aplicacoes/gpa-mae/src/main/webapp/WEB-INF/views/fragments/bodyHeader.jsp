<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid container">
		<div class="navbar-header">
			<a href="<c:url value="/selecao/listar" />">
				<img src="<c:url value="/resources/images/logo-GPA.jpg" />" alt="GPA - Assuntos Estudantis" id="logo-gpa">
			</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<sec:authorize access="hasAnyRole('ADMINISTRADOR_GPA')">
					<li role="presentation">
						<a href="<c:url value="/administrador/listar" />">Servidores</a>
					</li>
					<li role="presentation">
						<a href="<c:url value="/servidor/selecao/listar" />">Alunos</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('DOCENTE', 'STA')">
					<li role="presentation">
						<a href="<c:url value="/servidor/selecao/listar" />">Seleções</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('DISCENTE')">
					<li role="presentation">
						<a href="<c:url value="/aluno/selecao/listar" />">Seleções</a>
					</li>
					<li role="presentation">
						<a href="<c:url value="/aluno/inscricao/listar" />">Minhas Inscrições</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('COORDENADOR_ASSUNTOS_ESTUDANTIS')">
					<li role="presentation">
						<a href="<c:url value="/coordenador/selecao/listar" />">Coordenação</a>
					</li>
				</sec:authorize>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li>
					<p class="navbar-text"> ${sessionScope.usuario}</p>
				</li>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle glyphicon glyphicon-menu-down btn-lg" 
						data-toggle="dropdown" role="button" 
		              	aria-haspopup="true" aria-expanded="false"></a>
						<ul class="dropdown-menu " role="menu">
							<li>
								<a href="<c:url value="/j_spring_security_logout" />" title="Sair">
									<i class="glyphicon glyphicon-log-out"></i> Sair
								</a>
							</li>
						</ul>
					</li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<li><a href="<c:url value="/login" />">Login</a></li>
				</sec:authorize>
			</ul>
		</div>

	</div>
</nav>

