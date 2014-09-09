<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<jsp:include page="../modulos/header-estrutura.jsp" />
	<title>selecaos</title>
</head>
<body>
	
	<jsp:include page="../modulos/header.jsp" />
	<div class="container">
		
		
		<div align="right" style="margin-bottom: 20px;">
			<a href="<c:url value="/selecao/cadastrar" ></c:url>">
				<button class="btn btn-primary">Novo selecao <span class="glyphicon glyphicon-plus"></span></button>
			</a>
		</div>
		
				<div class="panel-heading" align="center">
					<h4>Seleções</h4>
				</div>

				<!-- Table -->
				<table class="table" id="table">
					<thead>
						<tr>
						
							<th>Identificador</th>
							<th>Número do Edital</th>
							<th>Vagas</th>
							 <th>Status</th>
							 
						
						</tr>
					</thead>
					<tbody>
						<c:forEach var="selecao" items="${selecoes}">
							<tr class="linha">
								
								<td><a href="<c:url value="/selecao/${selecao.id}" > </c:url>">${selecao.identificador}</a></td>
								<td><a href="<c:url value="/selecao/${selecao.id}" > </c:url>">${selecao.sequencial}</a></td>
								<td><a href="<c:url value="/selecao/${selecao.id}" > </c:url>">${selecao.quantidadeVagas}</a></td>
								<td><a href="<c:url value="/selecao/${selecao.id}" > </c:url>">${selecao.status}</a></td>
								
								<td>												
									
										<a id="editar" href="<c:url value="/selecao/${selecao.id}/editar" ></c:url>">
											<button class="btn btn-info">Editar <span class="glyphicon glyphicon-pencil"></span></button>
										</a>

										<a id="excluir" data-toggle="modal" data-target="#confirm-delete" href="#" data-href="<c:url value="/selecao/${selecao.id}/excluir" ></c:url>">
											<button class="btn btn-danger">Excluir <span class="glyphicon glyphicon-trash"></span></button>
										</a>
									
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
	
	
	<jsp:include page="../modulos/footer.jsp" />
	
	
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                Excluir
	            </div>
	            <div class="modal-body">
	                Tem certeza de que deseja excluir esse selecao?
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
