<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="topo">
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid container">
			<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" 
		         data-target="#mainMenu">
		         <span class="sr-only">Toggle navigation</span>
		         <span class="icon-bar"></span>
		         <span class="icon-bar"></span>
		         <span class="icon-bar"></span>
		      </button>
				<a href="<c:url value="/selecao/listar" />"> <img
					src="<c:url value="/resources/images/logo-GPA.jpg" />"
					alt="GPA - Assuntos Estudantis" id="logo-gpa">
				</a>
			</div>
			<div class="collapse navbar-collapse" id="mainMenu">
				<ul class="nav navbar-nav">
					<sec:authorize access="hasAnyRole('ADMINISTRADOR_GPA')">
						<li>
							<a href="<c:url value="/administrador/listar" />">
								Servidores
							</a>
						</li>
						<li>
							<a href="<c:url value="/administrador/listar/alunos" />">
								Alunos
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('DOCENTE', 'STA')">
						<li>
							<a href="<c:url value="/servidor/selecao/listar" />">
								Seleções
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('DISCENTE')">
						<li>
							<a href="<c:url value="/aluno/selecao/listar" />">
								Seleções
							</a>
						</li>
						<li>
							<a href="<c:url value="/aluno/inscricao/listar" />">
							Minhas Inscrições
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('COORDENADOR_ASSUNTOS_ESTUDANTIS')">
						<li>
							<a href="<c:url value="/coordenador/selecao/cadastrar" />">Nova
									Seleção
							</a>
						</li>
					</sec:authorize>
				</ul>

				<ul class="nav navbar-nav navbar-right">			

					<sec:authorize access="isAuthenticated()">
						<li class="dropdown">						
							<a href="#"
							class="dropdown-toggle left"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">
								${sessionScope.usuario}
								<b class = "caret"></b>
							</a>			
							<ul class="dropdown-menu " role="menu">
								<li>
									<a href="<c:url value="/j_spring_security_logout" />"
									title="Sair"> <i class="glyphicon glyphicon-log-out"></i>
										Sair
									</a>
								</li>
							</ul>
						</li>
					</sec:authorize>
				</ul>
				<sec:authorize access="isAnonymous()">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="<c:url value="/login" />">Login</a></li>
					</ul>
				</sec:authorize>		
			</div>
		</div>
	</nav>
</div>