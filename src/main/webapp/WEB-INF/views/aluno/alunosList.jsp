<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="gpa" tagdir="/WEB-INF/tags"%>

<html>

<head>

<jsp:include page="../fragments/headTag.jsp" />
<link
	href="<c:url value="/webjars/datatables/1.9.4/media/css/jquery.dataTables.css" />"
	rel="stylesheet" />
<script src="<c:url value="/webjars/datatables/1.9.4/media/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/npi.js" />"></script>
</head>

<body>

	<div class="container">
		<jsp:include page="../fragments/bodyHeader.jsp" />

		<br> <br>
		<h2>Alunos</h2>

	<div id="mensagens" class="alert" hidden="true">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true"
		>&times;</button>
	</div>

		<!-- Button trigger modal -->
		<button id="btnAdicionar" class="btn btn-primary" data-toggle="modal"
			data-target="#myModal">Adicionar Alunos</button>

		<datatables:table id="alunos" data="${selections}" cdn="false"
			row="aluno" theme="bootstrap2" cssClass="table table-striped"
			paginate="true" info="false" export="pdf">

				
			 <datatables:column title="Matricula" cssStyle="width: 200px;">
				<c:out value="${aluno.matricula}" />
			</datatables:column>
			 
			 <datatables:column title="Nome" cssStyle="width: 200px;">
				<c:out value="${aluno.usuario.nome}" />
			</datatables:column>
			
			
			<datatables:column title="E-mail" cssStyle="width: 200px;">
				<c:out value="${aluno.usuario.email}" />
			</datatables:column>
			
			<datatables:column title="Telefone" cssStyle="width: 200px;">
				<c:out value="${aluno.usuario.telefone}" />
			</datatables:column>
			



		</datatables:table>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Adicionar contato</h4>
					</div>
					
										
					<div class="modal-body">

						<form class="form-horizontal" id="add-contato-form">

							<input type="hidden" name="id" id="id" /> 

							<div class="form-group">
								<label class="col-sm-2 control-label" for="nome">Nome</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="Nome"
										name="nome" id="nome" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label" for="sobreNome">SobreNome</label>
								<div class="col-sm-10">
									<input type="text" class="form-control"
										placeholder="Sobre Nome" name="sobreNome" id="sobreNome" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label" for="email">Email</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" name="email"
										id="email" placeholder="Email">
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-2 control-label" for="endereco">Endereço</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="Endereço"
										name="endereco" id="endereco" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label" for="cidade">Cidade</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="Cidade"
										name="cidade" id="cidade" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label" for="fone">Telefone</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="Telefone"
										name="fone" id="fone" />
								</div>
							</div>
							
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
						<button id="btnSubmitForm" type="button" class="btn btn-primary" onclick="submeterForm();">Salvar</button>
					</div>
				</div>
			</div>
		</div>


		<jsp:include page="../fragments/footer.jsp" />

	</div>
</body>

</html>
