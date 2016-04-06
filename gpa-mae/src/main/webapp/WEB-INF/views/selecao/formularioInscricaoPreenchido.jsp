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
<jsp:include page="../fragments/bodyHeader.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulário de Incrição Preenchido</title>
</head>

<body>
	<jsp:include page="../fragments/headTag.jsp" />

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
							Sócioeconômica<i class="fa"></i>
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
					servletRelativeAction="/auxilio/inscricao/${selecao}/"
					method="POST" cssClass="form-horizontal">
					<input type="hidden" name="id"
						value="${questionarioAuxilioMoradia.id}" />
					<d\iv class="tab-content">

						<div class="tab-pane active" id="moradia-tab">

							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3>Mora com</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<dl class="dl-horizontal">
											<dt>Candidato:</dt>
											<dd>${aluno.nome}</dd>
										</dl>

									</div>
								</div>
	
				</form:form>

			</div>

		</div>
	</div>

</body>
</html>