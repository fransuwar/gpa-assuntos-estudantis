<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="bs-component">		
	<table class="table table-striped table-hover ">

		<thead>
			<tr>
				<th class="form-group"><span class="red"></span>Nome</th>
				<th class="form-group"><span class="red"></span>Parentesco</th>
				<th class="form-group"><span class="red"></span>Escolaridade</th>
				<th class="form-group"><span class="red"></span>Idade</th>
				<th class="form-group"><span class="red"></span>Profissao</th>
				<th class="form-group"><span class="red"></span>Renda R$</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="pessoaFamiliaContainer">
			<c:if test="${not empty pessoasDaFamilia }">
				<c:forEach items="${pessoasDaFamilia }" var="pf">
					<tr class="pessoaFamilia defaultRow">
						<td><input class="form-control" type="text" name="pf.nome"
							value="${pf.nome }" /></td>
						<td><form:select path="grauParentesco" name="pessoas[].grauParentesco"
								class="form-control" cssStyle="font-size:13px;">
								<form:option value="0">Selecione um grau de parentesco</form:option>
								<c:forEach items="${grauParentesco }" var="parentesco">
									<form:option value="${grauParentesco }" item="${grauParentesco }"
										selected="${parentesco == pf.parentesco ? 'selected' : '' }">${parentesco.nome}</form:option>
								</c:forEach>
							</form:select></td>
						<td><input class="form-control" type="text"
							name="pf.escolaridade" value="${pf.escolaridade }" /></td>
						<td><input class="form-control" type="number" name="pf.idade"
							value="${pf.idade }" /></td>
						<td><input class="form-control" type="text"
							name="pf.profissao" value="${pf.profissao }" /></td>
						<td><input class="form-control" type="number" id="rendaMensal"
							name="pf.rendaMensal" value="${pf.rendaMensal }" /></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty pessoasDaFamilia }">
				<c:forEach begin="1" end="1" var="i">
					<tr class="pessoaFamilia">
						<td><input class="form-control" type="text" name="pessoas[${i }].nome" value="" required ="required"/></td>
						<td>
							<form:select path="" name="pessoas[${i }].parentesco" class="form-control" required ="required">
								<form:option value="">Selecione um grau de parentesco</form:option>
								<form:options items="${grauParentesco}" itemLabel="nome"/>
							</form:select>
						</td>
							
						<td><input class="form-control" type="text" name="pessoas[${i }].escolaridade" value="" required ="required"/></td>
							
						<td><input class="form-control" type="number" name="pessoas[${i }].idade" value="" required ="required"/></td>
							
						<td><input class='form-control' type="text" name="pessoas[${i }].profissao" value="" required ="required"/></td>
							
						<td><input class="form-control" id="rendaMensal" type="text" name="pessoas[${i }].rendaMensal" value="" required ="required"/></td>
							
						<td><a href="#" class="removePessoa">Remover Pessoa</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<a href="#" id="addPessoa" class="btn btn-primary">Adicionar Pessoa</a>