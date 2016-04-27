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
		<div class="panel panel-primary">
			<div class="panel-heading">
			
				<h3 class="panel-title">Detalhes da Seleção</h3>
			</div>
			<div class="panel-body">
				<dl class="col-sm-12">
					<dt class="col-sm-2">Número do Edital:</dt>
					<dd class="col-sm-2">${selecao.sequencial}</dd>
					<dt class="col-sm-2">Tipo de Seleção:</dt>
					<dd class="col-sm-2">${selecao.tipoSelecao.nome}</dd>
					<dt class="col-sm-2">Ano do Edital:</dt>
					<dd class="col-sm-2">${selecao.ano}</dd>
				</dl>
				<dl class="col-sm-12">
					
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-2">Quantidade de vagas:</dt>
					<dd class="col-sm-2">${selecao.quantidadeVagas}</dd>
					<dt class="col-sm-2">Responsável:</dt>
					<dd class="col-sm-2">${selecao.responsavel.pessoa.nome}</dd>
					<dt class="col-sm-2">Período de inscrição:</dt>
					<dd class="col-sm-2">
						<fmt:formatDate value="${selecao.dataInicio}" pattern="dd/MM/yyyy" />
						até
						<fmt:formatDate value="${selecao.dataTermino}"
							pattern="dd/MM/yyyy" />
					</dd>
				</dl>
				<dl class="col-sm-12">
					
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Arquivos:</dt>
					<c:forEach var="documento" items="${selecao.documentos}">
						<dd class="col-sm-3">
							<a
								href="<c:url value="/selecao/documento/${documento.id}"></c:url>">
								${documento.nome} </a>
						</dd>
					</c:forEach>
				</dl>
				
				<sec:authorize access="hasAnyRole('DISCENTE')">
					<dl class="col-sm-12">
						
						<dt class="col-sm-3 pull-right" id="text-align-rigth">
							<c:choose>
								<c:when test="${controle}">
									<a id="editar" title="Editar"
										href="<c:url value="/aluno/inscricao/editar/auxilio-moradia/${inscricao.id }" ></c:url>">
										<button class=" btn btn-primary btn-sm"
															title="Editar Inscrição">
															Editar inscrição
															<span class="glyphicon glyphicon-pencil"></span>
														</button>
									</a>
								</c:when>

								<c:otherwise>
									<a id="inscrever" title="Inscrever-se"
										href="<c:url value="/aluno/inscricao/${selecao.id}/auxilio-moradia" ></c:url>">
										 <button class=" btn btn-primary btn-sm">
										 					Inscrever-se
															<span class="glyphicon glyphicon-user"></span>
														</button>
									</a>
								</c:otherwise>
							</c:choose>
						</dt>
					</dl>
				</sec:authorize>

				
			</div>
		</div>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>