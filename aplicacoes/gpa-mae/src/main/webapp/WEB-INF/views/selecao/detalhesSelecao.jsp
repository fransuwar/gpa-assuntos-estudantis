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
	<div class="container" align="left" style="padding-left: 85px;">
		<div class="panel panel-primary-min">
			<div class="panel-heading">
				<h3 class="panel-title">Detalhes da Seleção</h3>
			</div>

			<div class="panel-body" >
					<dl class="col-sm-12">
						<dt class="col-sm-3" >Número do Edital:</dt>
						<dd class="col-sm-3" >${selecao.sequencial}</dd>
						<dt class=" col-sm-3">Tipo de Bolsa:</dt>
						<dd class="col-sm-3">${selecao.tipoBolsa.nome}</dd>
					</dl>
					<dl class="col-sm-12">
						<dt class="col-sm-3">Ano do Edital:</dt>
						<dd class="col-sm-3">${selecao.ano}</dd>
						<dt class="col-sm-3">Status:</dt>
						<dd class="col-sm-3">${selecao.status.nome}</dd>
						
					</dl>
					<dl class="col-sm-12">
						<dt class="col-sm-3">Quantidade de vagas:</dt>
						<dd class="col-sm-3">${selecao.quantidadeVagas}</dd>
						<dt class="col-sm-3">Responsável:</dt>
						<dd class="col-sm-3">${selecao.responsavel.pessoa.nome}</dd>
					</dl>
					<dl class="col-sm-12">
						<dt class="col-sm-3">Data de Início da Inscrição:</dt>
						<dd class="col-sm-3">
							<fmt:formatDate value="${selecao.dataInicio}"
								pattern="dd/MM/yyyy" />
						</dd>
						<dt class="col-sm-3">Data de Término da Inscrição:</dt>
						<dd class="col-sm-3">
							<fmt:formatDate value="${selecao.dataTermino}"
								pattern="dd/MM/yyyy" />
						</dd>
					</dl>
			<dl class="col-sm-12">
					<dt class="col-sm-3">Arquivos:</dt>
					<c:forEach var="documento" items="${selecao.documentos}">
							<dd class="col-sm-3">
								<a
									href="<c:url value="/selecao/documento/${documento.id}"></c:url>">
									${documento.nome} 
								</a>
							</dd>
					</c:forEach>
				</dl>
			</div>
		</div>
		<sec:authorize access="hasAnyRole('COORD_ASS_ESTUDANTIS', 'SERVIDOR')">
		<div class="panel panel-primary-min" align="left">
			<div class="panel-heading">
				<h3 class="panel-title">Resultado da seleção</h3>
			</div>
			<table class="table">
				<thead>
					<tr class="info">
						<td>Nome</td>
						<td>Matrícula</td>
					</tr>
				</thead>
				<tr>
					<td>Não existem classificados no momento</td>
				</tr>
			</table>
		</div>
		<div class="panel panel-primary-min" align="left">
			<div class="panel-heading">
				<h3 class="panel-title">Participantes da Seleção</h3>
			</div>
			<table class="table">
				<thead>
					<tr class="info">
						<td>Número</td>
						<td>Aluno</td>
						<td>Matricula</td>
						<td>Data</td>
					</tr>
				</thead>
				<tbody>
						<c:forEach var="inscrito"
							items="${selecao.inscritos }">
							<tr>
								<td>${inscrito.id }</td>
								<td>${inscrito.aluno.pessoa.nome }</td>
								<td>${inscrito.aluno.matricula }</td>
								<td><fmt:formatDate value="${inscrito.data}"
								pattern="dd/MM/yyyy" /></td>
							</tr>
						</c:forEach>
					</tbody>
			</table>	
		
		</div>

		</sec:authorize>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>