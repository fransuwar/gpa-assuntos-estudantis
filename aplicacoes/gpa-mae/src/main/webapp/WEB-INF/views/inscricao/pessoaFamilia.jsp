<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<table class="table">
	
	<thead>
		<tr>
			<th class="form-group" width="10">Nome</th>
			<th class="form-group">Parentesco</th>
			<th class="form-group">Escolaridade</th>
			<th class="form-group">Atividade</th>
			<th class="form-group">Renda R$</th>
		</tr>
	</thead>
<tbody id="pessoaFamiliaContainer">
<%-- 	<c:if test="${pessoas == null}"> --%>
		<tr class="pessoaFamilia defaultRow">
			<td><input type="text" name="pessoas[].nome" value="" /></td>

			<!-- <td>
				<form:select path="pessoas" id="grauParentesco"
					cssClass="form-control">
					<form:option value="" label="Selecione o grau de parentesco" />
					<form:options items="${GrauParentesco}" />
				</form:select>
			</td>-->

			<td><select name="pessoas[].grauParentesco" class="form-control">
					<option value="" selected="selected">Grau Parentesco</option>
					<option value="Filho_a">Filho(a)</option>
					<option value="Neto">Neto(a)</option>
					<option value="Sobrinho">Sobrinho(a)</option>
					<option value="Irmao">Irmão</option>
					<option value="Conjuge_Companheiro">Cônjuge ou
						Companheiro(a)</option>
					<option value="Outros">Outros</option>
			</select></td> 
			
			<td><input type="text" name="pessoas[].escolaridade" value="" /></td>
			<td><input type="text" name="pessoas[].atividade" value="" /></td>
			<td><input type="text" name="pessoas[].renda" value="" /></td>

			<td><a href="#" class="removePessoa">Remover Pessoa</a></td>
		</tr>

<%-- 	</c:if> --%>
	</tbody>


</table>

<a href="#" id="addPessoa" class="btn btn-primary">Adicionar Pessoa</a>

<jsp:include page="../fragments/footer.jsp"></jsp:include>

<script type="text/javascript">
	function rowAdded(rowElement) {
		$(rowElement).find("input").val('');
	}
	function rowRemoved(rowElement) {
	}

	$(document)
			.ready(
					function() {

						var config = {
							rowClass : 'pessoaFamilia',
							addRowId : 'addPessoa',
							removeRowClass : 'removePessoa',
							formId : 'questionarioForm',
							rowContainerId : 'pessoaFamiliaContainer',
							indexedPropertyName : 'pessoas',
							indexedPropertyMemberNames : 'nome, grauParentesco, escolaridade, atividade, renda',
							rowAddedListener : rowAdded,
							rowRemovedListener : rowRemoved,
						};
						new DynamicListHelper(config);
					});
</script>
