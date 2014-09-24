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
	
	<jsp:include page="../modulos/header-estrutura.jsp" />
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload-ui.css" />">
	<title>Editar Seleções</title>
</head>

<body>
	<jsp:include page="../modulos/header.jsp" />
	
	<div class="container">
		<div class="novo-selecao" align="left">
			<div class="form" align="center">
				<c:if test="${action == 'editar'}">
					<c:set var="url" value="/selecao/${selecao.id}/editar"></c:set>
					<h2>Editar Seleção</h2>
				</c:if>
				<form:form id="editar" commandName="selecao" servletRelativeAction="${url }" cssClass="form-horizontal" method="POST">
					<input type="hidden" name="id" value="${selecao.id }"/>
					<input type="hidden" name="status" value="${selecao.status }"/>

					<div class="form-group">
						<label for="comentarios" class="col-sm-2 control-label">Comentarios:</label>
						<div class="col-sm-10">
							<form:textarea id="comentarios" path="comentarios" class="form-control" rows="5"  ></form:textarea>
							<div class="error-validation">
								<form:errors path="comentarios"></form:errors>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="dataInicio" class="col-sm-2 control-label">Data de Início:</label>
						<div class="col-sm-2">
							<form:input id="dataInicio" type="text" path="dataInicio" cssClass="form-control data" placeholder="Data de Início"/>
							<div class="error-validation">
								<form:errors path="dataInicio"></form:errors>
							</div>
						</div>
						
						<label for="dataTermino" class="col-sm-2 control-label">Data de Término:</label>
						<div class="col-sm-2">
							<form:input id="dataTermino" type="text" path="dataTermino" cssClass="form-control data" placeholder="Data de Término"/>
							<div class="error-validation">
								<form:errors path="dataTermino"></form:errors>
							</div>
						</div>
						
						<label for="sequencial" class="col-sm-2 control-label">Número do Edital:</label>
						<div class="col-sm-2">
							<form:input id="sequencial" type="number" path="sequencial" cssClass="form-control" placeholder="000"/>
							<div class="error-validation">
								<form:errors path="sequencial"></form:errors>
							</div>
						</div>
						
						<label for="duracao" class="col-sm-2 control-label">Duração:</label>
						<div class="col-sm-2">
							<form:input id="duracao" type="text" path="duracao" cssClass="form-control" placeholder="0"/>
							<div class="error-validation">
								<form:errors path="duracao"></form:errors>
							</div>
						</div>
						<label for="ano" class="col-sm-2 control-label">Ano:</label>
						<div class="col-sm-2">
							<form:input id="ano" type="text" path="ano" cssClass="form-control	" placeholder="0" onkeypress="mascara(this,soNumeros)"/>
							<div class="error-validation">
								<form:errors path="ano"></form:errors>
							</div>
						</div>
					
						
						<label for="quantidadeVagas" class="col-sm-2 control-label">Quantidade de Vagas:</label>
						<div class="col-sm-2">
							<form:input id="quantidadeVagas" type="number" min="0" placeholder="0" path="quantidadeVagas" cssClass="form-control"/>
							<div class="error-validation">
								<form:errors path="quantidadeVagas"></form:errors>
							</div>
						</div>
					</div>
					
			<div class="form-group"> 
					<label for="tipoBolsa" class="col-sm-2 control-label">Tipo de Bolsa:</label>	
					<form:select  name="${status.expression}" path="tipoBolsa" id="tipoBolsa">
						<c:forEach var="item" items="${tiposBolsa}">
							<form:option value="${item}"><spring:eval expression="item.tipo"/></form:option>
						</c:forEach>
					</form:select>
					</div>
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Salvar" />
						<a href="<c:url value="/selecao/listar"></c:url>" class="btn btn-default">Cancelar</a>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp" />

</body>

</html>
