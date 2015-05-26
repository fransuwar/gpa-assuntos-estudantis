<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/bodyHeader.jsp" />
<title>Informações</title>



</head>
<body>
	<jsp:include page="../fragments/headTag.jsp" />
	<div class="center col-md-6" align="left">

		<div class="panel panel-primary" align="left">
			<div class="panel-heading">
				<h3 class="panel-title">Detalhes da Seleção</h3>
			</div>
			<div class="panel-body">
				<dl class="dl-horizontal">
					<dt>Número do Edital:</dt>
					<dd>${selecao.sequencial}</dd>
				</dl>
				<dl class="dl-horizontal">
					<dt>Descrição:</dt>
					<dd>${selecao.comentarios}</dd>
				</dl>
				<dl class="dl-horizontal">
					<dt>Data de Início Insc.:</dt>
					<dd>
						<fmt:formatDate value="${selecao.dataInicio}" pattern="dd/MM/yyyy" />
					</dd>
				</dl>
				<dl class="dl-horizontal">
					<dt>Data de Término Insc.:</dt>
					<dd>
						<fmt:formatDate value="${selecao.dataTermino}"
							pattern="dd/MM/yyyy" />
					</dd>
				</dl>
				<dl class="dl-horizontal">
					<dt>Ano Edital:</dt>
					<dd>${selecao.ano}</dd>
				</dl>
				<dl class="dl-horizontal">
					<dt>Duração da Bolsa:</dt>
					<dd>${selecao.duracao} meses</dd>
				</dl>
				<dl class="dl-horizontal">
					<dt>Qtd. de vagas:</dt>
					<dd>${selecao.quantidadeVagas}</dd>
				</dl>
				<dl class="dl-horizontal">
					<dt>Arquivos:</dt>
					<c:forEach var="documento" items="${selecao.documentos}">
						<!-- <a href="<c:url value="/documento/${documento.id}"></c:url>">${documento.nomeOriginal}</a>-->
						<sec:authorize access="isAnonymous()">
							<dd>
								<a
									href="<c:url value="/selecao/${documento.id}/downloadDocumento"></c:url>">${documento.nome}</a>
							</dd>
						</sec:authorize>
					</c:forEach>
				</dl>
			</div>
		</div>



	</div>
	<div class="col-md-4">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Login</h3>
			</div>
			<div class="panel-body">
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
				<form name='f' action="<c:url value='/j_spring_security_check' />"
					method='POST' class="form-horizontal">
					<div class="form-group">
						<label class="col-md-2 control-label">Usuário:</label>
						<div class="col-sm-9">
							<input class="form-control" type='text' name='j_username'
								value=''>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">Senha:</label>
						<div class="col-sm-9">
							<input class="form-control" type='password' name='j_password' />
						</div>
					</div>
					<div class="form-group col-md-12">
						<div class="form-group">
							<div class="col-md-2 col-md-offset-7">
								<input class="btn btn-primary" name="submit" type="submit"
									value="Login" value="Login" />
							</div>
							<div class="col-md-2">
								<input class="btn btn-default" name="reset" type="reset"
									value="Limpar" />
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>


	</div>















	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>