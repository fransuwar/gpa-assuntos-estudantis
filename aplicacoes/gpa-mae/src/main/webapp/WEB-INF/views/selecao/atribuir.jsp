<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<jsp:include page="../fragments/headTag.jsp" />
	<title>Atribuir Parecerista</title>
</head>

<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<ol class="breadcrumb">
      <li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
      <li class="active">Atribuir Membro Banca</li>
    </ol>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h2>Atribuir Membro Banca</h2>
			</div>
			<div class="panel-body">
				<form:form id="adicionarBancaForm" role="form" commandName="selecao" servletRelativeAction="/selecao/atribuir" method="POST" cssClass="form-horizontal">
					<input type="hidden" name="id" value="${selecao}">
					
					<div class="form-group">
						<label class="col-sm-1 control-label">Membro:</label>
						<div class="col-sm-4">
							<form:select path="" cssClass="form-control" name="id1">
								<option value="">Selecione o primeiro membro</option>
								<c:forEach var="servidor" items="${servidores}">
									<option value="${servidor.id}" ${servidor.id == m1 ? 'selected="selected"' : ''}>${servidor.pessoa.nome}</option>	 
								</c:forEach>
							</form:select>	
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">Membro:</label>
						<div class="col-sm-4">
						<form:select path="" cssClass="form-control" name="id2">
							<option value="">Selecione o segundo membro</option>
								<c:forEach var="servidor" items="${servidores}">
									<option value="${servidor.id}" ${servidor.id == m2 ? 'selected="selected"' : ''}>${servidor.pessoa.nome}</option>	 
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">Membro:</label>
						<div class="col-sm-4">
							<form:select path="" cssClass="form-control" name="id3">
								<option value="">Selecione o terceiro membro</option>	
								<c:forEach var="servidor" items="${servidores}">
									<option value="${servidor.id}" ${servidor.id == m3 ? 'selected="selected"' : ''}>${servidor.pessoa.nome}</option>	 
								</c:forEach>
							</form:select>
						</div>
					</div>
					<div class="error-validation" id="erro-membros">
						<label class="col-sm-4 control-label" id="label-erro">
							${erroMembros}
						</label>
						<form:errors path=""></form:errors>
					</div>
					<div class="form-group">
						<div class="col-sm-2" id="div-form-btn">
							<input name="submit" type="submit" class="btn btn-primary"
								value="Adicionar Membros" id="form-btn" />
						</div>
						<div class="col-sm-2" id="div-form-btn">
							<a href="<c:url value="/selecao/listar"></c:url>"
								class="btn btn-default" id="form-btn">Cancelar</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>	
	</div>

	<jsp:include page="../fragments/footer.jsp"></jsp:include>

</body>
</html>
