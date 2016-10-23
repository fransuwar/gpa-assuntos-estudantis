<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a id="logo-gpa" class="navbar-brand" href="javascript:void(0)">
				<img src="<c:url value="/images/logo-GPA.jpg" />" alt="GPA - Assuntos Estudantis">
			</a>
		</div>
		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<ul class="nav navbar-nav">
				<li>
					<a href="<c:url value="/selecao/listar" />">Seleções</a>
				</li>
				<sec:authorize access="hasAuthority('ALUNO')">
					<li>
						<a href="<c:url value="/aluno/inscricao/listar" />">Minhas Inscrições</a>
					</li>
				</sec:authorize>
				<sec:authorize access="hasAuthority('COORDENADOR')">
					<li>
						<a href="<c:url value="/coordenador/tipo-documento" />">Tipos de Documentos</a>
					</li>
				</sec:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<p class="navbar-text">${sessionScope.pessoa.nome}</p>
				</li>
				<sec:authorize access="isAuthenticated()">
					<li>
						<a href="<c:url value="/logout" />" title="Sair">Sair</a>
					</li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</div>
