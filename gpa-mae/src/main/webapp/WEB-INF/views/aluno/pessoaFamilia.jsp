<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- botão adicionar pessoa -->
<div>

	<div class="alert alert-info alert-rounded text-center">
		Adicione as suas
		informações socioeconômicas e a das pessoas com quem você mora.
	</div>
	
	<button id="abrirFormPessoaFamilia" type="button"
		class="btn btn-primary" data-toggle="modal"
		data-target="#formPessoaFamilia">Adicionar Pessoa</button>
</div>

<div class="panel-group" id="listaPessoas">

	<!-- Accordion Item -->
	<div id="pessoaFamilia" class="panel panel-default hidden">
		<div class="panel-heading clickable pointer" data-toggle="collapse"
			data-parent="#listaPessoas" data-target="#pessoa_0">
			<h4 class="panel-title"></h4>
		</div>
		<div id="pessoa_0" class="panel-collapse collapse out">
			<div class="panel-body">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th class="form-">Parentesco:</th>
							<th class="form-group">Escolaridade:</th>
							<th class="form-group">Profissão:</th>
							<th class="form-group">Idade:</th>
							<th class="form-group">Renda:</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary editarPessoa"
						data-toggle="modal" data-target="#editarPessoaFamilia">
						Editar</button>
					<button name="botaoRmv" type="button"
						class="btn btn-danger rmvPessoa">Remover</button>
				</div>
			</div>
		</div>
	</div>

</div>

<!-- Modal adicionar pessoa -->
<div class="modal fade" id="formPessoaFamilia" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content panel panel-primary">
			<div class="modal-header panel-heading">
				<h4 class="modal-title" id="myModalLabel">Nova Pessoa</h4>
			</div>
			<div class="modal-body">
				<div class='f-container s4'>
					<label class='f-title'>Nome:</label>
					<div class='f-content'>
						<input class="form-control" type="text" name="nome"
							value="${aluno.pessoa.nome}" required="required" />
					</div>
				</div>
				<div class='f-container s4'>
					<label class='f-title'>Parentesco:</label>
					<div class='f-content'>
						<form:select path="" name="parentesco" class="form-control"
							required="required">
							<form:option value="">Selecione um grau de parentesco</form:option>
							<form:options name="opcoes" items="${grauParentesco}"
								itemLabel="nome" />
						</form:select>
					</div>
				</div>
				<div class='f-container s2'>
					<label class='f-title'>Idade:</label>
					<div class='f-content'>
						<input class="form-control" type="number" name="idade" value=""
							required="required" />
					</div>
				</div>
				<div class='f-container s4'>
					<label class='f-title'>Escolaridade:</label>
					<div class='f-content'>
						<form:select class="form-control" path="" name="escolaridade"
							required="required">
							<c:forEach items="${escolaridade }" var="escolaridade">
								<option value="${escolaridade }">${escolaridade.nome }</option>
							</c:forEach>
						</form:select>
					</div>
				</div>

				<div class='f-container s4'>
					<label class='f-title'>Profissao:</label>
					<div class='f-content'>
						<input class="form-control" type="text" name="profissao" value=""
							required="required" />
					</div>
				</div>
				<div class='f-container s2'>
					<label class='f-title'>Renda R$:</label>
					<div class='f-content'>
						<input class="form-control rendaMensal" type="text"
							name="rendaMensal" value="" required="required" />
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar
				</button>
				<button id="addPessoa" type="submit" class="btn btn-success"
					data-dismiss="modal">Adicionar</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal editar pessoa -->
<div class="modal fade" id="editarPessoaFamilia" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content panel panel-primary">
			<div class="modal-header panel-heading">
				<h4 class="modal-title" id="myModalLabel">Editar</h4>
			</div>
			<div class="modal-body">
				<div class='f-container s4'>
					<label class='f-title'>Nome:</label>
					<div class='f-content'>
						<input class="form-control" type="text" name="nomeEditar" value=""
							required="required" />
					</div>
				</div>
				<div class="f-container s4">
					<label class="f-title">Parentesco:</label>
					<div class="f-content">
						<form:select path="" name="parentescoEditar" class="form-control"
							required="required">
							<form:option value="">Selecione um grau de parentesco</form:option>
							<form:options items="${grauParentesco}" itemLabel="nome" />
						</form:select>
					</div>
				</div>
				<div class='f-container s2'>
					<label class='f-title'>Idade:</label>
					<div class='f-content'>
						<input class="form-control" type="number" name="idadeEditar"
							value="" required="required" />
					</div>
				</div>
				<div class='f-container s4'>
					<label class='f-title'>Escolaridade:</label>
					<div class='f-content'>
						<input class="form-control" type="text" name="escolaridadeEditar"
							value="" required="required" />
					</div>
				</div>

				<div class='f-container s4'>
					<label class='f-title'>Profissao:</label>
					<div class='f-content'>
						<input class="form-control" type="text" name="profissaoEditar"
							value="" required="required" />
					</div>
				</div>
				<div class='f-container s2'>
					<label class='f-title'>Renda R$:</label>
					<div class='f-content'>
						<input class="form-control rendaMensal" type="text"
							name="rendaEditar" value="" required="required" />
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
				<button id="confirmarEdicao" type="submit" class="btn btn-success"
					data-dismiss="modal">Confirmar</button>
			</div>
		</div>
	</div>
</div>