<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<jsp:include page="../modulos/header-estrutura.jsp" />
	<title>Cadastro de Seleção</title>
</head>
<body>

	<jsp:include page="../modulos/header.jsp" />
	
	 <div class="container">
		<div class="novo-projeto" align="left">
			<div class="form" align="center">
				<h2>Novo Projeto</h2>
				<form:form id="adicionarProjetoForm" role="form" commandName="edital" servletRelativeAction="/selecao/selecaoList" method="POST" cssClass="form-horizontal">

					<div class="form-group">
						<label for="QuantidadeVagas" class="col-sm-2 control-label">Quantidade de Vagas:</label>
						<div class="col-sm-10">
							<form:input id="QuantidadeVagas" path="QuantidadeVagas" cssClass="form-control" placeholder="Nome do projeto"/>
							<div class="error-validation">
								<form:errors path="QuantidadeVagas"></form:errors>
							</div>
						</div>
					</div>
					
					
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Cadastrar" />
						<a href="<c:url value="/edital/index"></c:url>" class="btn btn-default">Cancelar</a>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../modulos/footer.jsp" />

</body>


</html>
