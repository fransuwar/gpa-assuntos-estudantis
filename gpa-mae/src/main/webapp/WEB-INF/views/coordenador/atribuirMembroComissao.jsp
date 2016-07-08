<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
<title>Gerenciar Comissão</title>
</head>

<body>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	
	<div class="container">
	
		<jsp:include page="../fragments/cards.jsp" />
	
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Gerenciar Comissão</h3>
			</div>
			
			<div class="panel-body">
				<c:if test="${not empty erro}">
					<div class="alert alert-danger alert-dismissible" role="alert"
						id="alert-erro">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<div style="text-align:center">
							<c:out value="${erro}"></c:out>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty info}">
					<div class="alert alert-success alert-dismissible" role="alert"
						id="alert-info">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<div style="text-align:center">
							<c:out value="${info}"></c:out>
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1">
						<form:form id="adicionarComissaoForm" role="form"
							servletRelativeAction="/coordenador/comissao/atribuir"
							method="POST" class="form-horizontal">
							<input type="hidden" name="idSelecao" value="${idSelecao}">
							
							
									<table class="table">
										<tr>
											<td><select id="" class="form-control" name="idServidor">
													<c:forEach items="${servidores}" var="servidor">
														<option value="${servidor.id}">${servidor.pessoa.nome}</option>
													</c:forEach>
											</select></td>
											<td><input type="submit" class="btn btn-primary"
												value="Adicionar" id="form-btn" /></td>
										</tr>
									</table>
								
						</form:form>
					</div>
					
					<div class="col-sm-6">
						<table class="table table-custom table-hover">
							<thead class="th-custom">
								<tr>
									<th colspan="4" class="th-center">
										Comissão
									</th>
								</tr>
								<tr>
									<th class="th-center">Nome</th>
									<th class="th-center">SIAPE</th>
									<th class="th-center">Cargo</th>
									<th class="th-center">-</th>
								</tr>
							</thead>
							<c:forEach var="servidor" items="${selecao.membrosComissao}">
								<tr>
									<td>${servidor.pessoa.nome}</td>
									<td>${servidor.siape }</td>
									<td>${servidor.cargo.nome }</td>
									<c:choose>
										<c:when test="${selecao.responsavel.siape != servidor.siape}">
											<td>
												<a id="excluir" data-toggle="modal" data-target="#confirm-delete" data-href="<c:url value="/coordenador/comissao/excluir/${idSelecao}/${servidor.id}"></c:url>">
													<button class="btn btn-danger btn-small btn-xs">
														<span class="glyphicon glyphicon-trash"></span>
													</button>
												</a>
											</td>
										</c:when>
										<c:otherwise>
											<td>
												<a disabled="disabled" class="btn btn-danger btn-small btn-xs">
														<span class="glyphicon glyphicon-trash"></span>
												</a>
											</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp"></jsp:include>

	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Excluir</div>
				<div class="modal-body">Tem certeza de que deseja excluir esse
					membro da Comissão?</div>
				<div class="modal-footer">
					<a class="btn btn-danger">Excluir</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
