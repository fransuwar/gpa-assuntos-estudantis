<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<title>Cadastro de Seleção</title>
</head>
<body>

	<jsp:include page="../fragments/headTag.jsp" />
	
	 <div class="container">
		<div class="novo-selecao" align="left">
			<div class="form" align="center">
				<h2>Nova Seleção</h2>
				<form:form id="adicionarSelecaoForm" role="form" commandName="edital" servletRelativeAction="/selecaoBolsa/selecaoList" method="POST" cssClass="form-horizontal">

					<div class="form-group">
						<label for="quantidadeVagas" class="col-sm-2 control-label">Quantidade de Vagas:</label>
						<div class="col-sm-10">
							<form:input id="quantidadeVagas" path="quantidadeVagas" cssClass="form-control" placeholder="Nome da Seleção"/>
							<div class="error-validation">
								<form:errors path="quantidadeVagas"></form:errors>
							</div>
						</div>
					</div>
					
					
					
					<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Cadastrar" />
						<a href="<c:url value="/index"></c:url>" class="btn btn-default">Cancelar</a>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>


</html>
