<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${action eq 'cadastrar' }">
	<c:url var="url" value="/coordenador/selecao/cadastrar" />
	<c:set var="titulo" value="Nova Seleção" />
	<c:set var="botao" value="Cadastrar" />
</c:if>

<c:if test="${action eq 'editar' }">
	<c:url var="url" value="/coordenador/selecao/editar" />
	<c:set var="titulo" value="Editar Seleção" />
	<c:set var="botao" value="Atualizar" />
</c:if>

<html>
<head>
	<jsp:include page="../fragments/headTag.jsp" />
	<title>Cadastro de Seleções</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert" id="alert-erro">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">×</span><span class="sr-only">Close</span>
				</button>
				<div>
					<c:out value="${erro}"></c:out>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-success alert-dismissible" role="alert"
				id="alert-info">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">×</span><span class="sr-only">Close</span>
				</button>
				<div>
					<c:out value="${info}"></c:out>
				</div>
			</div>
		</c:if>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">${titulo }</h3>
			</div>
			<div class="panel-body">
				<form:form id="adicionarSelecaoForm" role="form" commandName="selecao" servletRelativeAction="${url }" method="POST"
					cssClass="form-horizontal">

					<input type="hidden" name="id" value="${selecao.id}" />

					<div class="form-group">
						<div class="col-sm-3">
							<label for="quantidadeVagas" class="control-label" id="text-align-left">Vagas:</label>

							<form:input id="quantidadeVagas" path="quantidadeVagas" cssClass="form-control" placeholder="0" min="1" data-mask="999" />
							<div class="error-validation" id="erro-qtdVagas">
								<form:errors path="quantidadeVagas"></form:errors>
							</div>
						</div>

						<div class="col-sm-3">
							<label for="ano" class="control-label" id="text-align-left">
								<span class="red">*</span>Ano:
							</label>

							<form:input id="ano" type="text" path="ano" cssClass="form-control" placeholder="ano"
								onkeypress="mascara(this,soNumeros)" readonly="true"/>
							<div class="error-validation" id="erro-ano">
								<label class="col-sm-10 control-label" id="label-erro">${dataError}</label>
								<form:errors path="ano"></form:errors>
							</div>
						</div>
						
						<div class="col-sm-3">
							<label for="dataInicio" class="control-label" id="text-align-left">
								<span class="red">*</span>Início das Inscrições:
							</label>

							<form:input id="dataInicio" type="text" path="dataInicio"
								cssClass="form-control data" placeholder="dd/mm/aaaa" readonly="true" />
							<div class="error-validation" id="erro-dataInicio">
								<form:errors path="dataInicio" />
							</div>
						</div>

						<div class="col-sm-3">
							<label for="dataTermino" class="control-label" id="text-align-left">
								<span class="red">*</span>Fim das Inscrições:
							</label>

							<form:input id="dataTermino" type="text" path="dataTermino"
								cssClass="form-control data" placeholder="dd/mm/aaaa" readonly="true"/>
							<div class="error-validation" id="erro-dataTermino">
								<form:errors path="dataTermino"></form:errors>
							</div>
						</div>

					</div>

					<div class="form-group">
						<div class="col-sm-12">
							<div class="col-sm-12">
								<label for="dataTermino" class="control-label" id="text-align-left">Documentos Necessários:</label>
							</div>
						    <div class="col-sm-12">
								<c:forEach var="documento" items="${tiposDeDocumento }">
								    <c:set var="contains" value="false" />
									<c:forEach var="documentoSelecionado" items="${selecao.tiposDeDocumento }">
										<c:if test="${documento.id eq documentoSelecionado.id }">
											<c:set var="contains" value="true" />
										</c:if>
									</c:forEach>
									<c:choose>
										<c:when test="${contains eq true }">
											<div class="checkbox">
												<input type="checkbox" id="tiposDeDocumento" data-toggle="toggle"
													name="checkDocumentos[]" value="${documento.id }" 
													checked>${documento.nome }
											</div>
										</c:when>
										<c:otherwise>
											<div class="checkbox">
												<input type="checkbox" id="tiposDeDocumento" data-toggle="toggle"
													name="checkDocumentos[]" value="${documento.id }" >${documento.nome }
											</div>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="form-btn">
						<a href="<c:url value="/servidor/selecao/listar"></c:url>" class="btn btn-default" id="form-btn">Cancelar</a>
						<input type="submit" class="btn btn-primary" value="${botao }" id="form-btn" />
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>

</html>