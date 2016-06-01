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
	<div class="container container-md" align="left">

		<div class="panel panel-primary">

			<div class="panel-heading">

				<h3 class="panel-title">DETALHES DA SELEÇÃO</h3>

			</div>
			
			<!-- Essa div (class='opcoes-detalhes') é para ficar fora da panel-heading mesmo,
			acredite em mim -->
			<div class='opcoes-detalhes'>
				<c:choose>
					<c:when test="${controle}">
						<a id="editar" title="Editar"
							href="<c:url value="/aluno/inscricao/editar/${inscricao.id }" ></c:url>">
							<button class=" btn btn-info btn-xs" title="Editar Inscrição">
								Editar inscrição <span class="glyphicon glyphicon-pencil"></span>
							</button>
						</a>

						<a id="editar" title="Editar"
							href="<c:url value="/aluno/inscricao/excluir/${aluno.id}/${inscricao.id }" ></c:url>"
							onclick='return confirmarLink("Prosseguir fará com que sua inscrição seja excluída. Deseja continuar?")'>
							<button class=" btn btn-danger btn-xs" title="Cancelar Inscrição">
								Cancelar inscrição <span class="glyphicon glyphicon-remove"></span>
							</button>
						</a>
					</c:when>

					<c:otherwise>
						<sec:authorize access="hasRole('DISCENTE')">
							<a id="inscrever" title="Inscrever-se"
								href="<c:url value="/aluno/inscricao/${selecao.id}/auxilio-moradia" />">
								<button class="btn btn-info btn-xs">
									Inscrever-se <span class="glyphicon glyphicon-user"></span>
								</button>
							</a>
						</sec:authorize>
					</c:otherwise>

				</c:choose>
			</div>

			<div class="panel-body">

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
						<c:forEach var="documento" items="${selecao.documentos}">
							<dd class="col-sm-3">
								<a
									href="<c:url value="/selecao/documento/${documento.id}"></c:url>">
									${documento.nome} </a>
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