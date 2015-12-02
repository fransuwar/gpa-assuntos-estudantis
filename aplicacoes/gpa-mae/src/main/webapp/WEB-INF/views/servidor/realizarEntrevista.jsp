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
<title>Realizar Entrevista</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />
	<div class="container">
		<sec:authorize access="hasAnyRole('SERVIDOR')">

			<div class="novo-projeto" align="left">
				<div class="form" align="center">

					<form:form id="relatorioForm" role="form"
						modelAttribute="entrevista" commandName="entrevista"
						servletRelativeAction="/entrevista" method="POST"
						cssClass="form-horizontal">
						
						<input type="hidden" name="idEntrevista" value="${entrevista.id}" />

						<div class="tab-content">
							<div class="tab-pane active" id="dados-gerais-tab">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h3>Entrevista</h3>
									</div>
									<div class="panel-body">
										<div class="form-group">
											<label for="observacao" class="col-sm-2 control-label">Observação</label>
											<div class="col-sm-10">
												<form:textarea class="form-control" rows="3" id="observacao"
													type="text" path="observacao" placeholder="Observação"></form:textarea>
												<span class="help-block"></span>
												<div class="error-validation">
													<form:errors path="observacao"></form:errors>
												</div>
											</div>
										</div>

										<div class="form-group">
											<label for="deferimento" class="col-sm-2 control-label">Deferimento
												Aprovado? </label>

											<div class="col-sm-1" id="deferimento">
												<form:checkbox id="deferimento" path="deferimento"
													cssClass="form-control" />
											</div>
										</div>
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
		</sec:authorize>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>