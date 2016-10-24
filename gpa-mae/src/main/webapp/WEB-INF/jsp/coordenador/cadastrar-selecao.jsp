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
	<title>Cadastrar Seleção</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-body">
					<h2>${titulo}</h2>
					<div class="divider"></div>
					<form:form id="adicionarSelecaoForm" cssClass="row" commandName="selecao" servletRelativeAction="${url }" method="POST">
	
						<input type="hidden" name="id" value="${selecao.id}" />
						<div class="form-group col-md-3">
							<label for="quantidadeVagas" class="control-label">Vagas</label>
							<div>
								<form:input id="quantidadeVagas" path="quantidadeVagas" cssClass="form-control" placeholder="0" min="1" data-mask="999" />
								<div class="error-validation">
									<form:errors path="quantidadeVagas"></form:errors>
								</div>
							</div>
						</div>
						<div class="form-group col-md-3">
							<label for="ano" class="control-label">* Ano</label>
							<form:input id="ano" type="text" path="ano" cssClass="form-control" placeholder="ano"
								onkeypress="mascara(this,soNumeros)"/>
							<div class="error-validation">
								<form:errors path="ano"></form:errors>
							</div>
						</div>
						<div class="form-group col-md-3">
							<label for="dataInicio" class="control-label">* Início das Inscrições</label>
							<form:input id="dataInicio" type="text" path="dataInicio"
								cssClass="form-control" placeholder="dd/mm/aaaa" />
							<div class="error-validation">
								<form:errors path="dataInicio" />
							</div>
						</div>
						<div class="form-group col-md-3">
							<label for="dataTermino" class="control-label">* Fim das Inscrições: </label>
							<form:input id="dataTermino" type="text" path="dataTermino"
								cssClass="form-control" placeholder="dd/mm/aaaa"/>
							<div class="error-validation">
								<form:errors path="dataTermino"></form:errors>
							</div>
						</div>
						
						<div class="form-group form-group-lg col-md-12">
							<label class="control-label">Documentos Necessários:</label>
						    <div class="checkbox">
								<c:forEach var="documento" items="${tiposDeDocumento }">
								    <c:set var="contains" value="false" />
									<c:forEach var="documentoSelecionado" items="${selecao.tiposDeDocumento }">
										<c:if test="${documento.id eq documentoSelecionado.id }">
											<c:set var="contains" value="true" />
										</c:if>
									</c:forEach>
									<c:choose>
										<c:when test="${contains eq true }">
											<label>
												<input type="checkbox"
													name="checkDocumentos[]" value="${documento.id }" 
													checked/>
												${documento.nome }
											</label>
											
										</c:when>
										<c:otherwise>
											<label>
												<input type="checkbox" id="tiposDeDocumento" data-toggle="toggle"
													name="checkDocumentos[]" value="${documento.id }"/>
												${documento.nome }
											</label>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</div>
						</div>
						<div class="form-group col-md-12 btn-form">
							<input type="submit" class="btn btn-primary btn-raised right" value="${botao }"/>
							<a href="<c:url value="/selecao/listar"></c:url>" class="btn btn-default btn-raised right">Cancelar</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>

</html>