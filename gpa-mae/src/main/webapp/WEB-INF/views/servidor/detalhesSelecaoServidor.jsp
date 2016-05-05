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
		<c:if test="${not empty erro}">
			<div class="alert alert-danger alert-dismissible" role="alert"
				id="alert-erro">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">×</span><span class="sr-only">Close</span>
				</button>
				<c:out value="${erro}"></c:out>
			</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-success alert-dismissible" role="alert"
				id="alert-info">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">×</span><span class="sr-only">Close</span>
				</button>
				<c:out value="${info}"></c:out>
			</div>
		</c:if>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Detalhes da Seleção</h3>
			</div>
			<div class="panel-body">
				<dl class="col-sm-12">
					<dt class="col-sm-3"></dt>
					<dt class="col-sm-3">
					    <a class="btn btn-primary btn-xs"
							href="<c:url value="/selecao/ranking/${selecao.id}"></c:url>">
							Ranking 
							<span class="glyphicon glyphicon-stats"></span></a>
					</dt>
					<dt class="col-sm-3"></dt>
					<dt class="col-sm-3">
						<a class="btn btn-primary btn-xs"
							href="<c:url value="/selecao/selecionarClassificados/${selecao.id}"></c:url>">
							Selecionar Classificados</a>
					</dt>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3" id="text-align-rigth">Edital:</dt>
					<dd class="col-sm-3">${selecao.sequencial}/${selecao.ano}</dd>
					<dt class=" col-sm-3" id="text-align-rigth">Seleção:</dt>
					<dd class="col-sm-3">${selecao.tipoSelecao.nome}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3" id="text-align-rigth">Vagas:</dt>
					<dd class="col-sm-3">${selecao.quantidadeVagas}</dd>
					<dt class="col-sm-3" id="text-align-rigth">Inscritos:</dt>
					<dd class="col-sm-3">${selecao.inscritos.size()}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3" id="text-align-rigth">Inscrições:</dt>
					<dd class="col-sm-3">
					<dt class="col-sm-2">Edital:</dt>
					<dd class="col-sm-2">${selecao.sequencial}/${selecao.ano}</dd>
					<dt class=" col-sm-2">Seleção:</dt>
					<dd class="col-sm-2">${selecao.tipoSelecao.nome}</dd>
					<dt class="col-sm-2">Inscrições:</dt>
					<dd class="col-sm-2">
						<fmt:formatDate value="${selecao.dataInicio}" pattern="dd/MM/yyyy" />
						até
						<fmt:formatDate value="${selecao.dataTermino}"
							pattern="dd/MM/yyyy" />
					</dd>
				</dl>
				<dl class="col-sm-12">
				<dt class="col-sm-2">Vagas:</dt>
					<dd class="col-sm-2">${selecao.quantidadeVagas}</dd>
					<dt class="col-sm-2">Inscritos:</dt>
					<dd class="col-sm-2">${selecao.inscritos.size()}</dd>
					
					<dt class="col-sm-2">Responsável:</dt>
					<dd class="col-sm-2">${selecao.responsavel.pessoa.nome}</dd>
				</dl>
			</div>
		</div>
		<sec:authorize
			access="hasAnyRole('COORDENADOR_ASSUNTOS_ESTUDANTIS', 'STA', 'DOCENTE')">
			<div class="panel panel-primary" align="left">
				<div class="panel-heading">
					<h3 class="panel-title">Comissão</h3>
				</div>
				<div class="panel-body">
					<table class="table">
						<thead>
							<tr class="active">
								<td>Nome</td>
								<td>SIAPE</td>
								<td>Cargo</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="membro" items="${selecao.membrosComissao }">
								<tr>
									<td>${membro.pessoa.nome }</td>
									<td>${membro.siape }</td>
									<td>${membro.cargo.nome }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="panel panel-primary" align="left">
				<div class="panel-heading">
					<h3 class="panel-title">Inscrições</h3>
				</div>
				<div class="panel-body">
					<table class="table" id="tabela-detalhes-selecao-servidores">
						<thead>
							<tr class="active">
								<td>Aluno</td>
								<td>Matricula</td>
								<td>Data</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="inscricao" items="${inscricoes }">
								<tr>
									<td><a id="detalhes"
										href="<c:url value="/servidor/detalhes/inscricao/${inscricao.id}">  
										</c:url>">
											${inscricao.aluno.pessoa.nome} </a></td>
									<td>${inscricao.aluno.matricula}</td>
									<td><fmt:formatDate value="${inscricao.data}"
											pattern="dd/MM/yyyy" /></td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</sec:authorize>
	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>