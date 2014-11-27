<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags"%>

<html>
<head>
	<jsp:include page="../fragments/bodyHeader.jsp" />
	<title>seleções</title>
</head>
<body>
	
	<jsp:include page="../fragments/headTag.jsp" />
	<ol class="breadcrumb">
      <li><a href="/MAE/selecao/listar">Listar Seleções</a></li>
    </ol>
	<div class="container">
		
		<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_COORDENADOR')">
		<div align="right" style="margin-bottom: 20px;">
			<a href="<c:url value="/selecao/cadastrar" ></c:url>">
				<button class="btn btn-primary">Nova seleção <span class="glyphicon glyphicon-plus"></span></button>
			</a>
		</div>
		</sec:authorize>
				<div class="panel-heading" align="center">
					<h4>Seleções</h4>
				</div>

				<!-- Table -->
				<table class="table" id="table">
					<thead>
						<tr>
						
							<th>Tipo de </th>
							<th>Ano</th>
							<th>Número do Edital</th>
							<th>Vagas</th>
							 <th>Status</th>
							 
						
						</tr>
					</thead>
					<tbody>
						<c:forEach var="selecao" items="${selecoes}">
							<tr class="linha">
								
								<td>${selecao.tipoBolsa.tipo}</td>
								<td>${selecao.ano}</td>
								<td>${selecao.sequencial}</td>
								<td>${selecao.quantidadeVagas}</td>
								<td>${selecao.status}</td>
								
								<td>												
										<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_COORDENADOR')">
								<a id="editar" href="<c:url value="/selecao/${selecao.id}/editar" ></c:url>">
									<button class="btn btn-info"> Editar <span class="glyphicon glyphicon-pencil"></span></button>
								</a>

								<a id="excluir" data-toggle="modal" data-target="#confirm-delete" href="#" data-href="<c:url value="/selecao/${selecao.id}/excluir" ></c:url>">
											<button class="btn btn-danger">Excluir <span class="glyphicon glyphicon-trash"></span></button>
										</a>

										</sec:authorize>

											<sec:authorize access="hasAnyRole('ROLE_COORDENADOR')">
											<a id="atribuirBanca"
														href="<c:url value="/selecao/${selecao.id}/atribuir" ></c:url>">
														<button class="btn btn-primary">
															Atribuir Membro à Banca <span
																class="glyphicon glyphicon-user"></span>
														</button>
													</a>
											</sec:authorize>	


										
										<sec:authorize access="hasAnyRole('ROLE_ALUNO')">
										<c:if test="${selecao.tipoBolsa =='INICIACAO_ACADEMICA'}">
										<a id="inscrever" href="<c:url value="/iniciacaoAcademica/inscricao" ></c:url>">
											<button class=" btn btn-success">inscrever-se <span class="glyphicon glyphicon-user"></span></button>
										</a>
										</c:if>
										
										
										<c:if test="${selecao.tipoBolsa =='AUXILIO_MORADIA'}">
										<a id="inscrever" href="<c:url value="/auxilio/inscricao/" ></c:url>">
											<button class=" btn btn-success">inscrever-se <span class="glyphicon glyphicon-user"></span></button>
										</a>
										</c:if>
										</sec:authorize>
										<sec:authorize access="isAnonymous()">
										
										<a id="informacoes" href="<c:url value="/selecao/${selecao.id}/informacoes"></c:url>">
											<button class=" btn btn-success">+ Informações <span class="glyphicon glyphicon-zoom-in"></span></button>
										</a>
										</sec:authorize>
										
										
										
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
	
	
	<jsp:include page="../fragments/footer.jsp" />
	
	
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                Excluir
	            </div>
	            <div class="modal-body">
	                Tem certeza de que deseja excluir essa seleção?
	            </div>
	            <div class="modal-footer">
	                <a href="#" class="btn btn-danger">Excluir</a>
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	        
	        


</body>
</html>
