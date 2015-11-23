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
			<th class="form-group" width="10"><span class="red">*</span>Nome</th>
			<th class="form-group"><span class="red">*</span>Parentesco</th>
			<th class="form-group"><span class="red">*</span>Escolaridade</th>
			<th class="form-group"><span class="red">*</span>Atividade</th>
			<th class="form-group"><span class="red">*</span>Renda R$</th>
		</tr>
	</thead>
	<tbody id="pessoaFamiliaContainer">
		<%-- 	<c:if test="${pessoas == null}"> --%>
		<tr class="pessoaFamilia defaultRow">
			<td><input type="text" name="pessoas[].nome" value="" /></td>

			<td>
				<form:select path="" name="pessoas[].grauParentesco" class="form-control" >
					<form:option value="">Selecione um grau de parentesco</form:option>
					<form:options items="${parentesco}" />
				</form:select>
				
			</td>

			<td><input type="text" name="pessoas[].escolaridade" value="" /></td>
			<td><input type="text" name="pessoas[].profissao" value="" /></td>
			<td><input type="text" name="pessoas[].rendaMensal" value="" /></td>

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
							indexedPropertyMemberNames : 'nome, grauParentesco, escolaridade, atividadeProfissao, rendaMensal',
							rowAddedListener : rowAdded,
							rowRemovedListener : rowRemoved,
						};
						new DynamicListHelper(config);
					});
</script>
