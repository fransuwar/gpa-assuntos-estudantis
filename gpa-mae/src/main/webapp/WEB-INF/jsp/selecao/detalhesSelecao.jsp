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
	<div class="container" align="left">
		<c:if test="${controle }">
			<div class="alert alert-success alert-dismissible" role="alert"
				id="alert-info">
				<div style="text-align: center">
					<c:out value="Você já está inscrito nesta seleção!"></c:out>
				</div>

			</div>
		</c:if>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title">DETALHES DA SELEÇÃO</div>
			</div>

			<div class="panel-body">
				<c:if test="${!controle }">
					<div class="f-conteiner" align="right">
						<sec:authorize access="hasRole('DISCENTE')">
							<a id="inscrever" title="Inscrever-se"
								href="<c:url value="/aluno/inscricao/auxilio-moradia/${selecao.id}/${aluno.id}" />">
								<button class="btn btn-info">
									Inscrever-se <span class="glyphicon glyphicon-user"></span>
								</button>
							</a>

						</sec:authorize>
					</div>
				</c:if>

				<div class='f-container s3'>

					<label class='f-title'>Número do Edital:</label>
					<div class='f-content'>${selecao.sequencial}/${selecao.ano}</div>

				</div>

				<div class='f-container s3'>

					<label class='f-title'>Tipo de Seleção:</label>
					<div class='f-content'>${selecao.tipoSelecao.nome}</div>

				</div>

				<div class='f-container s3'>

					<label class='f-title'>Quantidade de vagas:</label>
					<div class='f-content'>${selecao.quantidadeVagas}</div>

				</div>

				<div class='f-container s3'>

					<label class='f-title'>Período de inscrição:</label>
					<div class='f-content'>
						<fmt:formatDate value="${selecao.dataInicio}" pattern="dd/MM/yyyy" />
						até
						<fmt:formatDate value="${selecao.dataTermino}"
							pattern="dd/MM/yyyy" />
					</div>

				</div>

				<div class='f-container s3'>

					<label class='f-title'>Responsável:</label>
					<div class='f-content'>${selecao.responsavel.pessoa.nome}</div>

				</div>



				<div class='f-container s3'>

					<label class='f-title'>Arquivos:</label>
					<div class='f-content'>
						&nbsp;
						<ul class='lista-documentos-selecao'>
							<c:forEach var="documento" items="${selecao.documentos}">
								<li><a
									href="<c:url value="/selecao/documento/${documento.id}"></c:url>">
										${documento.nome} </a></li>
							</c:forEach>
						</ul>
					</div>
				</div>

			</div>

		</div>

	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>