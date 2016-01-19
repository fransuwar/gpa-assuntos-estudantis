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
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Detalhes da Seleção</h3>
			</div>

			<div class="panel-body">
				<dl class="col-sm-12">
					<dt class="col-sm-3">Número do Edital:</dt>
					<dd class="col-sm-3">${selecao.sequencial}</dd>
					<dt class=" col-sm-3">Tipo de Bolsa:</dt>
					<dd class="col-sm-3">${selecao.tipoSelecao.nome}</dd>
				</dl>
				<dl class="col-sm-12">
					<dt class="col-sm-3">Ano do Edital:</dt>
					<dd class="col-sm-3">${selecao.ano}</dd>
					<dt class="col-sm-3">Número de Inscritos:</dt>
					<dd class="col-sm-3">${selecao.inscritos.size()} </dd>

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
						<fmt:formatDate value="${selecao.dataInicio}" pattern="dd/MM/yyyy" />
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
								${documento.nome} </a>
						</dd>
					</c:forEach>
				</dl>
			</div>
		</div>
		<sec:authorize
			access="hasAnyRole('COORDENADOR_ASSUNTOS_ESTUDANTIS', 'STA', 'DOCENTE')">
			<div class="panel panel-primary" align="left">
				<div class="panel-heading">
					<h3 class="panel-title">Participantes da Comissão</h3>
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
					<h3 class="panel-title">Resultado da Seleção</h3>
				</div>
				<div class="panel-body">
					<table class="table">
						<thead>
							<tr class="active">
								<td>Nome</td>
								<td>Matrícula</td>
							</tr>
						</thead>
						<tr>
							<td>Não existem classificados no momento</td>
						</tr>
					</table>
				</div>
			</div>

			<div class="panel panel-primary" align="left">
				<div class="panel-heading">
					<h3 class="panel-title">Incrições Existentes</h3>
				</div>
				<div class="panel-body">
					<table class="table">
						<thead>
							<tr class="active">
								<td>Número</td>
								<td>Aluno</td>
								<td>Matricula</td>
								<td>Data</td>
								<td>Tipo de Bolsa</td>
								<td>Ações</td>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="inscricao" items="${inscricoes }">
								<tr>
									<td>${inscricao.id }</td>
									<td><a id="detalhes"
										href="<c:url value="/servidor/detalhes/inscricao/${inscricao.aluno.id}">  
									</c:url>">
											${inscricao.aluno.pessoa.nome }</a></td>
									<td>${inscricao.aluno.matricula }</td>
									<td><fmt:formatDate value="${inscricao.data}"
											pattern="dd/MM/yyyy" /></td>

									<td>${inscricao.selecao.tipoSelecao.nome}</td>

									<td><a id="avaliarDocumentos" title="Avaliar Documentação"
										href="<c:url value="" ></c:url>">
											<button class=" btn btn-primary btn-xs">
												<span class="glyphicon glyphicon-duplicate"></span>
											</button>
									</a> <c:if test="${empty inscricao.entrevista }">
											<a id="realizarEntrevista" title="Realizar Entrevista"
												href="<c:url value="/servidor/entrevista/${inscricao.id}" ></c:url>">
												<button class=" btn btn-primary btn-xs">
													<span class="glyphicon glyphicon-copy"></span>
												</button>
											</a>
										</c:if> <c:if
											test="${inscricao.selecao.tipoSelecao =='AUX_MOR' and empty inscricao.visitaDomiciliar}">
											<a id="realizarVisita" title="Realizar Visita"
												href="<c:url value="/servidor/visita/${inscricao.id }"></c:url>">
												<button class=" btn btn-primary btn-xs">
													<span class="fa fa-bus"></span>
												</button>
											</a>
										</c:if></td>
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