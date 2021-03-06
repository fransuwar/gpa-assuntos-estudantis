<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
				<tr class="horarioDisponivel defaultRow">
					<td><form:select path=""
							name="horariosDisponiveisSelecao[${i.index - 1 }].dia"
							class="form-control">
							<form:option value="">Selecione um dia</form:option>
							<c:forEach items="${diasUteis}" var="diaUtil">
							${diaUtil.nome }
								<form:option value="${diaUtil }"
									selected="${diaUtil == hd.dia ? 'selected' : ''}"
									itemLabel="nome">${diaUtil.nome }</form:option>
							</c:forEach>
						</form:select></td>

					<td><form:select path=""
							name="horariosDisponiveisSelecao[${i.index - 1 }].turno"
							class="form-control">
							<form:option value="">Selecione um turno</form:option>
							<c:forEach items="${turno}" var="tur">
								<form:option value="${tur }"
									selected="${tur == hd.turno ? 'selected' : ''}"
									itemLabel="nome">${tur.nome }</form:option>
							</c:forEach>
						</form:select></td>

					<td><a href="#" class="removerHorario">Remover Horário</a></td>
				</tr>
			</c:forEach>
		</c:if>
	<c:if test="${action eq 'editar' }">	
		<c:forEach begin="1" end="3" varStatus="i">
			
				<tr class="horarioDisponivel">
					<td><form:select path=""
							name="horariosDisponiveisSelecao[${i.index -1 }].dia"
							class="form-control">
							<form:option value="">Selecione um dia</form:option>
							<form:options items="${diasUteis}" />
						</form:select></td>

					<td><form:select path=""
							name="horariosDisponiveisSelecao[${i.index -1 }].turno"
							class="form-control">
							<form:option value="">Selecione um turno</form:option>
							<form:options items="${turno}" />
						</form:select></td>

					<td><a href="#" class="removerHorario">Remover Horário</a></td>
				</tr>
		
		</c:forEach>
	</c:if>
	
	<c:if test="${action eq 'inscricao' }">	
		<c:forEach begin="1" end="${horariosDisponiveis.size() }" varStatus="i">
			
				<tr class="horarioDisponivel">
					<td><form:select path=""
							name="horariosDisponiveisSelecao[${i.index -1 }].dia"
							class="form-control">
							<form:option value="">Selecione um dia</form:option>
							<form:options items="${diasUteis}" />
						</form:select></td>

					<td><form:select path=""
							name="horariosDisponiveisSelecao[${i.index -1 }].turno"
							class="form-control">
							<form:option value="">Selecione um turno</form:option>
							<form:options items="${turno}" />
						</form:select></td>

					<td><a href="#" class="removerHorario">Remover Horário</a></td>
				</tr>
		
		</c:forEach>
	</c:if>
	
	</tbody>
	
	
</table>

<a href="#" id="addHorario" class="btn btn-primary">Adicionar
	Horário</a>

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
