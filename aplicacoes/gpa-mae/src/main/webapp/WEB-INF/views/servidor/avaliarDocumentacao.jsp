<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Avaliar Documentação</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />
	<div class="container">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">

				<form:form id="relatorioForm" role="form"
					modelAttribute="avaliarDocumentacao" commandName="avaliarDocumentacao"
					servletRelativeAction="/servidor/avaliarDocumentacao" method="POST"
					cssClass="form-horizontal">

					<input type="hidden" id="idServidor" name="idServidor"
						value="${sessionScope.id}" />
					<input type="hidden" id="idInscricao" name="idInscricao"
						value="${idInscricao}" />

					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">Avaliar Documentação</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<label class="col-sm-2 control-label">Deferimento</label>
								<div class="col-sm-2" id="avaliacaoDocumentos">
									<select id="" class="form-control" name="avaliacaoDocumentos">
										<option value="${true}">Deferido</option>
										<option value="${false}">Indeferido</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2" id="div-form-btn">
							<input name="submit" type="submit" class="btn btn-primary"
								value="Cadastrar" id="form-btn" />
						</div>
						<div class="col-sm-2" id="div-form-btn">
							<a href="<c:url value="/selecao/listar" ></c:url>"
								class="btn btn-default" id="form-btn">Cancelar</a>
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>