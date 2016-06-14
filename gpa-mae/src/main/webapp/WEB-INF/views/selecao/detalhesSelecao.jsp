<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/bodyHeader.jsp" />
<title>Detalhes da seleção</title>
</head>
<body>
	<jsp:include page="../fragments/headTag.jsp" />
	
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title">DETALHES DA SELEÇÃO</div>
			</div>
			<div class="panel-body">
				<div class='f-container s3'>
					<label class='f-title'>Edital:</label>
					<div class='f-content'>${selecao.sequencial}/${selecao.ano}</div>
				</div>
				<div class='f-container s3'>
					<label class='f-title'>Tipo de Seleção:</label>
					<div class='f-content'>${selecao.tipoSelecao.nome}</div>
				</div>
				<div class='f-container s3'>
					<label class='f-title'>Vagas:</label>
					<div class='f-content'>${selecao.quantidadeVagas}</div>
				</div>
				<div class='f-container s3'>
					<label class='f-title'>Período de inscrição:</label>
					<div class='f-content'>
						<fmt:formatDate value="${selecao.dataInicio}" pattern="dd/MM/yyyy" /> até <fmt:formatDate value="${selecao.dataTermino}"
							pattern="dd/MM/yyyy" />
					</div>
				</div>
				<div class='f-container s3'>
					<label class='f-title'>Responsável:</label>
					<div class='f-content'>${selecao.responsavel.pessoa.nome}</div>
				</div>
				<div class='f-container s3'>
					<label class='f-title'>Arquivos:</label>
					<div class='f-content'>&nbsp;
						<c:forEach var="documento" items="${selecao.documentos}">
							<dd class="col-sm-3">
								<a href="<c:url value="/selecao/documento/${documento.id}"></c:url>">${documento.nome} </a>
							</dd>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>