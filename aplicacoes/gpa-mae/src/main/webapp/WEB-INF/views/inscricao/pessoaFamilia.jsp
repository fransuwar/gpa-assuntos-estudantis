<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><table>
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
                <c:if test="${pessoas == null}">
                    <tr class="pessoaFamilia defaultRow">    
                        <td><input type="text" name="pessoas[].nome" value="" /></td>
                        <td>
                        	<select name="pessoas[].grauParentesco">
			    	<option value="" selected="selected">Grau Parentesco</option>  
			    	<option value="Filho_a">Filho(a)</option>
			    	<option value="Neto">Neto(a)</option>
			    	<option value="Sobrinho">Sobrinho(a)</option>
			    	<option value="Irmao">Irmão</option>
			    	<option value="Conjuge_Companheiro">Cônjuge ou Companheiro(a)</option>
			    	<option value="Outros">Outros</option>
		    	</select>
		    </td>
                        <td><input type="text" name="pessoas[].escolaridade" value="" /></td>
                        <td><input type="text" name="pessoas[].atividade" value="" /></td>
                        <td><input type="text" name="pessoas[].renda" value="" /></td>
                        
                        <td><a href="#" class="removePessoa"  >Remove Pessoa</a></td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <a href="#" id="addPessoa" class="btn btn-primary" >Adicionar Pessoa</a>
    	<jsp:include page="../fragments/footer.jsp"></jsp:include>
     <script type="text/javascript">
         function rowAdded(rowElement) {
             $(rowElement).find("input").val('');
         }
         function rowRemoved(rowElement) {
//              alert( "Removed Row HTML:\n" + $(rowElement).html() );
         }
         
         $(document).ready( function() {
//          	console.log("ssssssssssssssssssssssssssss");
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