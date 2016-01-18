<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



									<div class="form-group">
										<label for="moradia" class="col-sm-2 control-label"
											id="form-label-right">Moradia:</label>
										<div class="col-sm-4">
											<form:input id="moradia" min="0" value="0"
												path="moradia" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="moradia"></form:errors>
											</div>
										</div>
										<label for="energia" class="col-sm-2 control-label"
											id="form-label-right">Energia:</label>
<%-- 										<div class="col-sm-4">
											<form:input id="energia" min="0" value="0"
												path="despesaEnergia" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="despesaEnergia"></form:errors>
											</div>
										</div> --%>
										<label for="telefone" class="col-sm-2 control-label"
											id="form-label-right">Telefone:</label>
<%-- 										<div class="col-sm-4">
											<form:input id="telefone" min="0" value="0"
												path="despesaTelefone" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="despesaTelefone"></form:errors>
											</div>
										</div> --%>
										<label for="educacao" class="col-sm-2 control-label"
											id="form-label-right">Educação:</label>
<%-- 										<div class="col-sm-4">
											<form:input id="educacao" min="0" value="0"
												path="despesaEducacao" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="despesaEducacao"></form:errors>
											</div>
										</div> --%>
										<label for="saude" class="col-sm-2 control-label"
											id="form-label-right">Saúde:</label>
<%-- 										<div class="col-sm-4">
											<form:input id="saude" min="0" value="0" path="despesaSaude"
												cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="despesaSaude"></form:errors>
											</div>
										</div> --%>
										<label for="alimentacao" class="col-sm-2 control-label"
											id="form-label-right">Alimentação:</label>
<%-- 										<div class="col-sm-4">
											<form:input id="alimentacao" min="0" value="0"
												path="despesaAlimentacao" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="despesaAlimentacao"></form:errors>
											</div>
										</div> --%>
										<label for="outros" class="col-sm-2 control-label"
											id="form-label-right">Outros:</label>
<%-- 										<div class="col-sm-4">
											<form:input id="outros" min="0" value="0"
												path="despesaOutros" cssClass="form-control" />
											<div class="error-validation">
												<form:errors path="despesaOutros"></form:errors>
											</div>
										</div> --%>
									</div>






<%-- 
<table class="table" id="tabelaHorariosDisponiveis">

	<thead>
		<tr>
			<th class="form-group">Dia</th>
			<th class="form-group">Turno</th>
		</tr>
	</thead>
	<tbody id="horarioDisponivelContainer">
		<c:if test="${not empty horariosDisponiveis }">
			<c:forEach items="${horariosDisponiveis }" var="hd" varStatus="i">
				<h1>${count }</h1>
				<tr class="horarioDisponivel defaultRow">
					
					<td>
					<form:select path="" name="horariosDisponiveisSelecao[${i.index - 1 }].dia" class="form-control">
							<form:option value="">Selecione um dia</form:option>
							<c:forEach items="${diasUteis}" var="diaUtil" >
								<form:option value="${diaUtil }" selected="${diaUtil == hd.dia ? 'selected' : ''}" itemLabel="nome">${diaUtil.nome }</form:option>
							</c:forEach>
						</form:select>
					</td>

					<td>
					<form:select path="" name="horariosDisponiveisSelecao[${i.index - 1 }].turno" class="form-control">
							<form:option value="">Selecione um turno</form:option>
							<c:forEach items="${turno}" var="tur">
								<form:option value="${tur }" selected="${tur == hd.turno ? 'selected' : ''}" itemLabel="nome">${tur.nome }</form:option>
							</c:forEach>
						</form:select>
					</td>

					<td><a href="#" class="removerHorario">Remover Horário</a></td>
				</tr>
			</c:forEach>
		</c:if>

		<c:forEach begin="1" end="3" varStatus="i">
			<tr class="horarioDisponivel">
				<td><form:select path=""
						name="horariosDisponiveisSelecao[${i.index -1 }].dia" class="form-control">
						<form:option value="">Selecione um dia</form:option>
						<form:options items="${diasUteis}" />
					</form:select></td>

				<td><form:select path=""
						name="horariosDisponiveisSelecao[${i.index -1 }].turno" class="form-control">
						<form:option value="">Selecione um turno</form:option>
						<form:options items="${turno}" />
					</form:select></td>

				<td><a href="#" class="removerHorario">Remover Horário</a></td>
			</tr>

		</c:forEach>

	</tbody>
</table>

<a href="#" id="addHorario" class="btn btn-primary">Adicionar Horário</a>

<jsp:include page="../fragments/footer.jsp"></jsp:include>

<script type="text/javascript">

	function rowAdded(rowElement) {
		$(rowElement).find("input").val('');
	}
	function rowRemoved(rowElement) {
	}

	$(document).ready(function() {

		var config = {
			rowClass : 'horarioDisponivel',
			addRowId : 'addHorario',
			removeRowClass : 'removerHorario',
			formId : 'questionario',
			rowContainerId : 'horarioDisponivelContainer',
			indexedPropertyName : 'horariosDisponiveisSelecao',
			indexedPropertyMemberNames : 'turno, dia',
			rowAddedListener : rowAdded,
			rowRemovedListener : rowRemoved,
		};
		new DynamicListHelper(config);
	});
</script>
 --%>