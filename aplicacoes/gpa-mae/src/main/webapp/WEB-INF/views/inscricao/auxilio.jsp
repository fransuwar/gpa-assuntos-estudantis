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

<html>

<head>

<jsp:include page="../fragments/headTag.jsp" />

<title>Cadastro Auxilio Moradia</title>

</head>

<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<ol class="breadcrumb">
		<li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
		<li class="active">Cadastrar Inscrição</li>
	</ol>

	<div class="container">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Programa de Auxílio Moradia</h2>

				<ul class="nav nav-tabs">
					<li class="active"><a href="#moradia-tab" data-toggle="tab">Moradia<i
							class="fa"></i>
					</a></li>
					<li><a href="#historico-escolar-tab" data-toggle="tab">Histórico
							Escolar<i class="fa"></i>
					</a></li>
					<li><a href="#situacao-socio-economica-tab" data-toggle="tab">Situação
							Sócio Econômica<i class="fa"></i>
					</a></li>
					<li><a href="#outras-informacoes-tab" data-toggle="tab">Outras
							Informações<i class="fa"></i>
					</a></li>
					<li><a href="#justificativa-tab" data-toggle="tab">Justificativa<i
							class="fa"></i>
					</a></li>

				</ul>

				<form:form id="questionarioForm" role="form"
					modelAttribute="questionarioAuxilioMoradia"
					commandName="questionarioAuxilioMoradia"
					servletRelativeAction="/auxilio/inscricao/" method="POST"
					cssClass="form-horizontal">

					<div class="tab-content">

						<div class="tab-pane active" id="moradia-tab">

							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3>Moradia</h3>
								</div>
								<div class="panel-body">
									<div class="form-group"></div>
								</div>
							</div>

						</div>

						<div class="tab-pane" id="historico-escolar-tab">

							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3>Histórico Escolar</h3>
								</div>
								<div class="panel-body">
									<div class="form-group"></div>
								</div>
							</div>

						</div>

						<div class="tab-pane" id="situacao-socio-economica-tab">

							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3>Situação Socioeconômica</h3>
								</div>
								<div class="panel-body">
									<div class="form-group"></div>
								</div>
							</div>

						</div>

					</div>

				</form:form>

			</div>

		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>

</html>