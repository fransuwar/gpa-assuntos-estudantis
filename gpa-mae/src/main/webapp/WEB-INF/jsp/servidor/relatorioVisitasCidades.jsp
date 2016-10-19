<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Relatório de Visitas</title>
</head>
<body>

	<jsp:include page="../fragments/bodyHeader.jsp" />

	<div class="container">
		<jsp:include page="../fragments/cards.jsp" />
				
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Relatório de Visitas</h3>
			</div>
			<div class="panel-body">

				<div class="menu-relatorio-visitas">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#porCidade"
							aria-controls="porCidade" role="tab" data-toggle="tab">Por
								Cidade</a></li>
						<li role="presentation"><a href="#porAluno"
							aria-controls="porAluno" role="tab" data-toggle="tab">Por
								Alunos</a></li>
					</ul>
				</div>
				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="porCidade">

						<div class="panel-group" id="accordion" role="tablist"
							aria-multiselectable="true">

							<c:forEach var="cidade" items="${cidadesVisitadas}">
								<div class="panel panel-primary">

									<div class="panel-heading panel-cidade" role="tab"
										id="heading${cidade.key}">
										<h4 class="panel-title">
											<a class="collapsed" role="button" data-toggle="collapse"
												data-parent="#accordion" href="#${cidade}"
												aria-expanded="true" aria-controls="${cidade.key}">
												${cidade.key} <span class="badge">${cidade.value}</span>
											</a></a>
										</h4>
									</div>
									<div id="${cidade.key}" class="panel-collapse collapse in"
										role="tabpanel" aria-labelledby="heading${cidade.key}">
										<div class="panel-body">
											<div class="list-group">
												<c:forEach var="inscricao" items="${inscritosComVisita}">
													<c:choose>
														<c:when
															test="${inscricao.questionarioAuxilioMoradia.cidadeOrigem == cidade.key}">
															<a
																href="<c:url value = "/servidor/detalhes/inscricao/${inscricao.id}"></c:url>"
																class="list-group-item"> <img
																class="foto-relatorio-visita"
																src="<c:url value = "/inscricao/detalhes/fotoAluno/${inscricao.id}"></c:url>" />
																${inscricao.aluno.pessoa.nome}
															</a>
														</c:when>
													</c:choose>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>

					<div role="tabpanel" class="tab-pane" id="porAluno">

						<div class="panel panel-primary panel-visitas col-md-6 pull-left">
							<div class="panel-heading" style="background-color: #009688;">
								<h3 class="panel-title">Selecionados para visita</h3>
							</div>
							<div class="panel-body panel-body-visitas">
								<div class="list-group">
									<c:choose>
										<c:when test="${empty inscritosComVisita}">
											Não existem alunos que <strong>serão</strong> visitados nessa seleção!
										</c:when>
										<c:otherwise>
											<div class="list-group">
												<c:forEach var="inscricao" items="${inscritosComVisita}">
													<a href="<c:url value = "/servidor/detalhes/inscricao/${inscricao.id}"></c:url>"
														class="list-group-item"> <img
														class="foto-relatorio-visita"
														src="<c:url value = "/inscricao/detalhes/fotoAluno/${inscricao.id}"></c:url>" />
														${inscricao.aluno.pessoa.nome}
													</a>
												</c:forEach>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

						<div class="panel panel-primary panel-visitas col-md-5 pull-right">
							<div class="panel-heading">
								<h3 class="panel-title">Não selecionados para visita</h3>
							</div>
							<div class="panel-body panel-body-visitas">
								<div class="list-group">
									<c:choose>
										<c:when test="${empty inscritosSemVisita}">
											Não existem alunos que <strong>não serão</strong> visitados nessa seleção!
										</c:when>
										<c:otherwise>
											<div class="list-group">
												<c:forEach var="inscricao" items="${inscritosSemVisita}">
													<a href="<c:url value = "/servidor/detalhes/inscricao/${inscricao.id}"></c:url>"
														class="list-group-item"> <img
														class="foto-relatorio-visita"
														src="<c:url value = "/inscricao/detalhes/fotoAluno/${inscricao.id}"></c:url>" />
														${inscricao.aluno.pessoa.nome}
													</a>
												</c:forEach>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>

	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>