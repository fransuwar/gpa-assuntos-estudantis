<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:choose>
	<c:when test="${todosConsolidados}">	
		<c:url var="consolidar" value="${false}" />
		<c:set var="botao" value="Desconsolidar Todos" />
	</c:when>
	<c:otherwise>
		<c:url var="consolidar" value="${true}" />
		<c:set var="botao" value="Consolidar Todos" />
	</c:otherwise>

</c:choose>



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

		<div class="panel-card">
			<div class="panel-card-content">
				<div class="card">
					<a href="<c:url value="/servidor/detalhes/${selecao.id}"></c:url>">
						<div class="card-content">
							<div class="card-icon">
								<i class="fa fa-folder-open"></i>
							</div>
							<div class="card-description">INSCRIÇÕES</div>
						</div>
					</a>
				</div>

				<div class="card">
					<a
						href="<c:url value="/coordenador/comissao/atribuir/${selecao.id}" ></c:url>">
						<div class="card-content">
							<div class="card-icon">
								<i class="fa fa-users"></i>
							</div>
							<div class="card-description">COMISSÃO</div>
						</div>
					</a>
				</div>
				<div class="card">
					<a
						href="<c:url value="/coordenador/selecao/adicionar-documento/${selecao.id}"></c:url>">
						<div class="card-content">
							<div class="card-icon">
								<i class="fa fa-file"></i>
							</div>
							<div class="card-description">ARQUIVOS</div>
						</div>
					</a>
				</div>
				
					<div class="card dropdown">
					<a class="dropdown-toggle" href="#" data-toggle="dropdown">
						<div class="card-content">
							<div class="card-icon">
								<i class="fa fa-file-text"></i>
							</div>
							<div class="card-description">RELATÓRIO</div>
						</div>

					</a>

					<ul class="dropdown-menu">
						<li><a href="<c:url value="/coordenador/selecao/adicionar-documento/${selecao.id}"></c:url>">Visitas</a></li>
						<li><a href="<c:url value="/coordenador/comissao/relatorioFinal/${selecao.id}"></c:url>">Final</a></li>
					</ul>
				</div>

				<div class="card">
					<a href="<c:url value="/selecao/selecionarClassificados/${selecao.id}"></c:url>">
						<div class="card-content">
							<div class="card-icon">
								<i class="fa fa-signal"></i>
							</div>
							<div class="card-description">RANKING</div>
						</div>
					</a>
				</div>


			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Detalhes da Seleção</h3>
			</div>
			<div class="panel-body">
				<div class='f-container s3'>
					<label class='f-title'>Edital:</label>
					<div class='f-content'>${selecao.sequencial}/${selecao.ano}</div>
				</div>

				<div class='f-container s3'>
					<label class='f-title'>Seleção:</label>
					<div class='f-content'>${selecao.tipoSelecao.nome}</div>
				</div>

				<div class='f-container s4'>
					<label class='f-title'>Inscrições:</label>
					<div class='f-content'>
						<fmt:formatDate value="${selecao.dataInicio}" pattern="dd/MM/yyyy" />
						até
						<fmt:formatDate value="${selecao.dataTermino}"
							pattern="dd/MM/yyyy" />
					</div>
				</div>

				<div class='f-container s3'>
					<label class='f-title'>Vagas:</label>
					<div class='f-content'>${selecao.quantidadeVagas}</div>
				</div>

				<div class='f-container s3'>
					<label class='f-title'>Inscritos:</label>
					<div class='f-content'>${selecao.inscritos.size()}</div>
				</div>

				<div class='f-container s4'>
					<label class='f-title'>Responsável:</label>
					<div class='f-content'>${selecao.responsavel.pessoa.nome}</div>
				</div>
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
							<div align="right">
								<input id="idSelecao" type="hidden" value="${selecao.id}">
								<button id="consolidacaoTodos" class="btn btn-primary" > </button>
							</div>


							<table class="table" id="tabela-detalhes-selecao-servidores">
						<thead>
							<tr class="active">
								<td>Aluno</td>
								<td>Matricula</td>
								<td>Data</td>
								<td>Consolidação</td>
								<td>Documentação</td>
								<td>Entrevista</td>
								<td>Visita</td>
								<td>Resultado</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="inscricao" items="${inscricoes}">
								<tr>
									<td><a id="detalhes"
										href="<c:url value="/servidor/detalhes/inscricao/${inscricao.id}">  
										</c:url>">
											${inscricao.aluno.pessoa.nome} </a></td>
									<td>${inscricao.aluno.matricula}</td>
									<td><fmt:formatDate value="${inscricao.data}"
											pattern="dd/MM/yyyy" /></td>
											
									<td>
											<input id="${inscricao.id}" class="toggle-event" type="checkbox" data-toggle="toggle" data-style="ios" data-size="small"
											<c:if test="${inscricao.consolidacao}">checked </c:if> >
									</td>
									<td><c:choose>
											<c:when
												test="${inscricao.documentacao.deferimento eq 'NAO_AVALIADO'}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when test="${empty inscricao.documentacao.deferimento}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when
												test="${inscricao.documentacao.deferimento eq 'DEFERIDO'}">
												<div class='def-sit deferido' title="Deferido">D</div>
											</c:when>

											<c:when
												test="${inscricao.documentacao.deferimento eq 'INDEFERIDO'}">
												<div class='def-sit indeferido' title="Indeferido">I</div>
											</c:when>
										</c:choose></td>

									<td><c:choose>
											<c:when
												test="${inscricao.entrevista.deferimento eq 'NAO_AVALIADO'}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when test="${empty inscricao.entrevista.deferimento}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when
												test="${inscricao.entrevista.deferimento eq 'DEFERIDO'}">
												<div class='def-sit deferido' title="Deferido">D</div>
											</c:when>

											<c:when
												test="${inscricao.entrevista.deferimento eq 'INDEFERIDO'}">
												<div class='def-sit indeferido' title="Indeferido">I</div>
											</c:when>
										</c:choose></td>

									<td><c:choose>
											<c:when
												test="${inscricao.visitaDomiciliar.deferimento eq 'NAO_AVALIADO'}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when
												test="${empty inscricao.visitaDomiciliar.deferimento}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when
												test="${inscricao.visitaDomiciliar.deferimento eq 'DEFERIDO'}">
												<div class='def-sit deferido' title="Deferido">D</div>
											</c:when>

											<c:when
												test="${inscricao.visitaDomiciliar.deferimento eq 'INDEFERIDO'}">
												<div class='def-sit indeferido' title="Indeferido">I</div>
											</c:when>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${inscricao.resultado eq 'NAO_AVALIADO'}">
												<div class='def-sit nao-avaliado' title="Não avaliado">N</div>
											</c:when>
											<c:when test="${inscricao.resultado eq 'DEFERIDO'}">
												<div class='def-sit deferido' title="Deferido">D</div>
											</c:when>

											<c:when test="${inscricao.resultado eq 'INDEFERIDO'}">
												<div class='def-sit indeferido' title="Indeferido">I</div>
											</c:when>
										</c:choose></td>
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