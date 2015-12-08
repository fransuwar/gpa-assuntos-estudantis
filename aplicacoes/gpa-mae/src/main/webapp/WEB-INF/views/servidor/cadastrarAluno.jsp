<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${action eq 'cadastrar' }">
	<c:url var="url" value="/servidor/cadastrar/aluno" />
	<c:set var="titulo" value="Nova Aluno"/>
	<c:set var="botao" value="Cadastrar"/>
</c:if>
<c:if test="${action eq 'editar' }">
<c:url var="url" value="/servidor/editar/aluno" />
	<c:set var="titulo" value="Atualizar Aluno"/>
	<c:set var="botao" value="Atualizar"/>
</c:if>

<html>
<head>
	<jsp:include page="../fragments/headTag.jsp" />
</head>
<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-body">
				<form:form id="adicionarAlunoForm" role="form" commandName="aluno"
					servletRelativeAction="${url }" method="POST"
					cssClass="form-horizontal">
					<input type="hidden" name="id" value="${aluno.id}" />
					<div class="form-group">
						<label for="matricula" class="col-sm-1 control-label"
							id="form-label">Matrícula:</label>
						<div class="col-sm-3">
							<form:input id="matricula" maxlength="7" path="matricula"
								cssClass="form-control" data-mask="9999999"
								placeholder="Matricula do aluno" />
							<div class="error-validation" id="erro-matricula">
								<form:errors path="matricula">
								</form:errors>
							</div>
						</div>

						<label for="curso" class="col-sm-1 control-label">Curso:</label>
						<div class="col-sm-5 control-label" id="div-select">
							<form:select path="curso" id="curso" cssClass="form-control">
								<form:option value="" label="Selecione o curso" />
								<form:options items="${cursos}" itemLabel="nome"/>
							</form:select>
							<div class="error-validation" id="erro-curso">
								<div class="error-validation label-erro-select" id="erro-curso">
									<form:errors path="curso"></form:errors>
								</div>
							</div>
						</div>
					</div>	
						<div class="form-group">
							<label for="ira" class="col-sm-1 control-label" id="form-label">IRA:</label>
							<div class="col-sm-3">
								<form:input id="ira" path="ira" cssClass="form-control"
									placeholder="Ira do aluno" maxlength="5"
									onkeyup="mascaraIra(this);" />
								<div class="error-validation" id="erro-ira">
									<form:errors path="ira">
									</form:errors>
								</div>
							</div>
							<label for="anoIngresso" class="col-sm-2 control-label"
								id="form-label">Ano Ingresso:</label>
							<div class="col-sm-2">
								<form:input id="anoIngresso" type="text" maxlength="4"
									path="anoIngresso" cssClass="form-control " data-mask="9999"
									placeholder="Ano de Ingresso" />
								<div class="error-validation" id="erro-anoIngresso">
									<form:errors path="anoIngresso">
									</form:errors>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="banco" class="col-sm-1 control-label" id="form-label">Banco:</label>
							<div class="col-sm-5 control-label" id="div-select">
								<form:select path="banco" id="banco" cssClass="form-control">
									<form:option value="" label="Selecione o banco" />
									<form:options items="${banco}" itemLabel="nome" />
								</form:select>
								<div class="error-validation" id="erro-banco">
									<div class="error-validation label-erro-select" id="erro-banco">
										<form:errors path="banco"></form:errors>
									</div>
								</div>
							</div>

							<label for="agencia" class="col-sm-2 control-label"
								id="form-label">Agência(Com DV):</label>

							<div class="col-sm-2">
								<form:input id="agencia" onkeyup="mascaraAgencia(this);"
									path="agencia" cssClass="form-control" placeholder="Agência" />
								<div class="error-validation" id="erro-agencia">
									<form:errors path="agencia">
									</form:errors>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="conta" class="col-sm-1 control-label" id="form-label">Conta:</label>
							<div class="col-sm-2">
								<form:input id="conta" data-mask="00000000000000" path="conta"
									cssClass="form-control" placeholder="Conta" />
								<div class="error-validation" id="erro-conta">
									<form:errors path="conta">
									</form:errors>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2" id="div-form-btn">
								<input name="submit" type="submit" class="btn btn-primary"
									value="${botao }" id="form-btn" />
							</div>
							<div class="col-sm-2" id="div-form-btn">
								<a href="<c:url value="/servidor/listar/alunos"></c:url>"
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