<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Cadastra/Edita alunos</title>

</head>

<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />

	<ol class="breadcrumb">
		<li><a href="/MAE/aluno/listar">Listar Aluno</a></li>
		<li class="active">Cadastrar Aluno</li>
	</ol>

	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${action == 'cadastrar'}">
						<h2>Novo Aluno</h2>
					</c:when>
					<c:otherwise>
						<h2>Editar Aluno</h2>
					</c:otherwise>
				</c:choose>

			</div>
			<div class="panel-body">
				<form:form id="adicionarAlunoForm" role="form" commandName="aluno"
					servletRelativeAction="/aluno/salvar" method="POST"
					cssClass="form-horizontal">
					<input type="hidden" name="id" value="${aluno.id}" />
					<div class="form-group">
						<label for="matricula" class="col-sm-1 control-label"
							id="form-label">Matrícula:</label>
						<div class="col-sm-3">
							<form:input id="matricula" maxlength="7" path="matricula"
								cssClass="form-control" data-mask="9999999"
								placeholder="Matricula do aluno" />
							<div class="error-validation">
								<form:errors path="matricula">
									<label class="col-sm-10 control-label" id="label-erro">
										Campo Obrigatório!
									</label>
								</form:errors>
							</div>
						</div>
						<label for="curso" class="col-sm-1 control-label" id="form-label">Curso:</label>
						<div class="col-sm-3">
							<form:input id="curso" path="curso" cssClass="form-control"
								placeholder="Curso" />
							<div class="error-validation">
								<form:errors path="curso">
									<label class="col-sm-10 control-label" id="label-erro">
										Campo Obrigatório!
									</label>
								</form:errors>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="ira" class="col-sm-1 control-label" id="form-label">IRA:</label>
						<div class="col-sm-3">
							<form:input id="ira" path="ira" cssClass="form-control"
								placeholder="Ira do aluno" maxlength="5" onkeyup="mascaraIra(this);"/>
							<div class="error-validation">
								<form:errors path="ira">
									<label class="col-sm-10 control-label" id="label-erro">
										Campo Obrigatório!
									</label>
								</form:errors>
							</div>
						</div>
						<label for="anoIngresso" class="col-sm-2 control-label"
							id="form-label">Ano Ingresso:</label>
						<div class="col-sm-2">
							<form:input id="anoIngresso" type="text" maxlength="4" path="anoIngresso"
								cssClass="form-control " data-mask="9999" placeholder="Ano de Ingresso" />
							<div class="error-validation">
								<label class="col-sm-10 control-label" id="label-erro">
									${anoIngressoError} 
								</label>
								<form:errors path="anoIngresso">
									<label class="col-sm-10 control-label" id="label-erro">
										Campo Obrigatório!
									</label>
								</form:errors>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="banco" class="col-sm-1 control-label" id="form-label">Banco:</label>
						<div class="col-sm-3">
							<form:input id="banco" path="banco" cssClass="form-control"
								placeholder="Banco" />
							<div class="error-validation">
								<form:errors path="banco">
									<label class="col-sm-10 control-label" id="label-erro">
										Campo Obrigatório!
									</label>
								</form:errors>
							</div>
						</div>
						<label for="agencia" class="col-sm-1 control-label"
							id="form-label">Agência:</label>
						<div class="col-sm-2">
							<form:input id="agencia" path="agencia" cssClass="form-control"
								placeholder="Agência" />
							<div class="error-validation">
								<form:errors path="agencia">
									<label class="col-sm-10 control-label" id="label-erro">
										Campo Obrigatório!
									</label>
								</form:errors>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="conta" class="col-sm-1 control-label" id="form-label">Conta:</label>
						<div class="col-sm-2">
							<form:input id="conta" path="conta" cssClass="form-control"
								placeholder="Conta" />
							<div class="error-validation">
								<form:errors path="conta">
									<label class="col-sm-10 control-label" id="label-erro">
										Campo Obrigatório!
									</label>
								</form:errors>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2" id="div-form-btn">
							<input name="submit" type="submit" class="btn btn-primary"
								value="Cadastrar" id="form-btn" />
						</div>
						<div class="col-sm-2" id="div-form-btn">
							<a href="<c:url value="/aluno/listar"></c:url>"
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