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
	<title>Atribuir Parecerista</title>
</head>

<body>
	<jsp:include page="../fragments/headTag.jsp" />
<ol class="breadcrumb">
      <li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
      <li class="active">Atribuir Membro Banca</li>
    </ol>
	<div class="container">
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<c:out value="${erro}"></c:out>
			</div>
		</c:if>
		<div class="atribuirBanca" align="center">
			<div class="form" align="center">
				<h2>Atribuir Membros da Banca</h2>
				

				<form:form id="adicionarBancaForm" role="form" commandName="atribuir" servletRelativeAction="/selecao/atribuir" method="POST" cssClass="form-horizontal">
						<input type="hidden" name="id" value="${selecao}">
					<div class="form-group">
						<label for="servidor" class="col-sm-2 control-label">Parecerista:</label>
						<div class="col-sm-4">
							<select name="id1" class="form-control">
								<c:forEach items="${servidores}" var="servidor">
									<option value="${servidor.id}">${servidor.pessoa.nome}</option>
								</c:forEach>
							</select>
							<label for="servidor" class="col-sm-2 control-label">Parecerista:</label>
							<select name="id2" class="form-control">
								<c:forEach items="${servidores}" var="servidor">
									<option value="${servidor.id}">${servidor.pessoa.nome}</option>
								</c:forEach>
							</select>
							<label for="servidor" class="col-sm-2 control-label">Parecerista:</label>
							<select name="id3" class="form-control">
								<c:forEach items="${servidores}" var="servidor">
									<option value="${servidor.id}">${servidor.pessoa.nome}</option>
								</c:forEach>
							</select>
					
						</div>
					</div>
					
					
			
						<div class="controls">
						<input name="submit" type="submit" class="btn btn-primary" value="Atribuir" />
						<a href="<c:url value="/selecao/listar"></c:url>" class="btn btn-default">Cancelar</a>
					</div>
			</form:form>
			</div>

		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp"></jsp:include>

</body>
</html>
