var linha;
function rowAdded(rowElement) {
	$(rowElement).find("input").val("");
}

function novaAba(url){
	window.open(url, "_blank");
}

function ConvertFormToJSON(form) {
	var array = jQuery(form).serializeArray();
	var json = {};
	jQuery.each(array, function() {
		json[this.name] = this.value || "";
	});

	return json;
}

function selecionarAba(idAba){
	$(".active").removeClass("active");
	$("#"+idAba).addClass("active");
	$("a[href=#"+idAba+"]").parent().addClass("active");
}
$(document).ready(function(){
	//Função genérica para iniciar os datatables
	function initDataTable(idTable, isPaging, isOrdering, isSearching, order, emptyTableMsg){
		
		emptyTableMsg = (emptyTableMsg === null || emptyTableMsg === "") ? "Nenhum registro encontrado" : emptyTableMsg;
		
		var dataTable = $(idTable).DataTable({
			"paging": isPaging,
			"order": order,
			"ordering": isOrdering,
			"bInfo" : false,
			"searching": isSearching,
			"language": {
				"sEmptyTable": emptyTableMsg,
				"url":"/MAE/resources/js/Portuguese-Brasil.json"
			}
		});
		return dataTable;
	}

	initDataTable(
			"#tabela-alunos, #tabela-servidores, #tabela-selecoes, " +
			"#tabela-ranking-classificados, #tabela-inscritos" +
			"#table-visualiza-info-auxilio, #tabela-detalhes-selecao-servidores", 
	false, false, false, false, "");


	var tabelaClassificaveis = initDataTable("#tabela-classificaveis", false, false, true,
			[[ 2, "cresc" ]], "Nenhum Aluno Classificável");
			

	var tabelaClassificados = initDataTable("#tabela-classificados", false, false, true,
			[[ 2, "cresc" ]], "Adicione pelo menos um aluno para a tabela dos classificados");
	
	$("#pesquisarClassificaveis").keyup(function() {
		tabelaClassificaveis.search(this.value).draw();
	});   

	$("#pesquisarClassificados").keyup(function() {
		tabelaClassificados.search(this.value).draw();
	});
	

	$.fn.dataTable.ext.errMode = "none";
	
	function getConfigsParaPDF(containerId, tableId){
		return {
			language: {
				url : "/MAE/resources/js/Portuguese-Brasil.json"
			},
			paging: false,
			order: [[ 2, "cresc" ]],
			ordering: false,
			bInfo : false,
			"columnDefs": [
			               { className: "dt-body-left", "targets": [ 0 ] }
			               ],
			               dom: "Bfrtip",
			               buttons: [
			                         {
			                        	 extend: "pdf",
			                        	 text: "Exportar como PDF",
			                        	 customize: function(doc){
			                        		 var colCount = new Array();
			                        		 $("#"+tableId).find("tbody tr:first-child td").each(function(){
			                        			 if($(this).attr("colspan")){
			                        				 for(var i=1;i<=$(this).attr("colspan");$i++){
			                        					 colCount.push("*");
			                        				 }
			                        			 }else{ colCount.push("*"); }
			                        		 });
			                        		 doc.content[1].table.widths = colCount;
			                        	 }
			                         }
			                         ],
			                         initComplete: function(settings) {

			                        	 $(".buttons-pdf").parent().css("float", "right");
			                        	 $(".buttons-pdf").parent().css("margin-top", "-30px");
			                        	 $(".buttons-pdf").parent().appendTo("#"+containerId);
			                        	 $(".buttons-pdf").attr("class", "btn btn-primary");

			                         }
		};
	}

	$("#confirm-delete").on("shown.bs.modal", function(e) {
		$(this).find(".btn-danger").attr("href", $(e.relatedTarget).data("href"));
	});
	
	$("#confirmar-consolidacao").modal("show");
	
	$("#btn-nao-consolidar").click(function(){
		$("#valor-consolidacao").val(false);
		
	});
	
    $("#btn-consolidar").click(function(){
    	$("#valor-consolidacao").val(true);
		
	});
	
	$("[data-toggle='tooltip']").tooltip();

	selecionarAba($("#aba").val());

	$(".panel-heading").click(function(){ $(this).find(".clicavel").click(); return false; });

	$("#resultadoFinalTableClassificados").DataTable( 
			getConfigsParaPDF("buttons-container1", "resultadoFinalTableClassificados")
	);

	$("#resultadoFinalTableReservas").DataTable( 
			getConfigsParaPDF("buttons-container2", "resultadoFinalTableReservas")
	);
	
	$("#resultadoFinalTableIndeferidos").DataTable( 
			getConfigsParaPDF("buttons-container3", "resultadoFinalTableIndeferidos")
	);

	//Submeter o formulário de Selecionar Classificados
	$("#erro-checkbox").hide();

	$("#botao-adicionar-classificados").on("click",function(e){
		e.preventDefault();
		//Validação para garantir que pelo menos um check esteja checado
		var peloMenosUmChecado = false;
		$("input[name='checkClassificados[]']").each(function() {
			if ($(this).is(":checked")) {
				peloMenosUmChecado = true;
			}
		});
		if(peloMenosUmChecado){
			$("#formClassificaveis").submit();
			$("#erro-checkbox").hide();
		}else{
			$("#erro-checkbox").show();
		}
	});

	$("#botao-remover-classificados").on("click",function(e){
		e.preventDefault();
		var peloMenosUmChecado = false;
		$("input[name='checkClassificaveis[]']").each(function() {
			if ($(this).is(":checked")) {
				peloMenosUmChecado = true;
			}
		});
		if(peloMenosUmChecado){
			$("#formClassificados").submit();
			$("#erro-checkbox").hide();
		}else{
			$("#erro-checkbox").show();
		}
	});


	$(".fechado").slideUp();

	$(".panel-heading span.clicavel").on("click", function () {
		if ($(this).hasClass("panel-collapsed")) {
			//Expande o painel
			$(this).parents(".panel").find(".panel-body").slideDown();
			$(this).removeClass("panel-collapsed");
			$(this).find("i").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
		}else{
			//Contrai o Painel
			$(this).parents(".panel").find(".panel-body").slideUp();
			$(this).addClass("panel-collapsed");
			$(this).find("i").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
		}

		return false;
	});
	

	$.extend(jQuery.validator.messages, {
		required: "Campo obrigatório",
	});


	$.validator.setDefaults({
		highlight : function(element) {
			$(element).closest(".form-item").addClass("has-error");
		},
		unhighlight : function(element) {
			$(element).closest(".form-group").removeClass("has-error");
		},
		errorElement : "span",
		errorClass : "text-danger",
		errorPlacement : function(error,
				element) {
			error.insertAfter(element.parent()
					.children().last());
			var itemForm = element.parent();
			var id = element.attr("name");
			$(itemForm).find("span").attr("id",id);
		}
	});

	$("#questionarioIniciacao").validate();



	jQuery.validator.addMethod("periodo", function() {
		return !moment($("#dataTermino").val()).isBefore($("#dataInicio").val());
	}, "A data de término deve ser posterior à data de início.");
	
	$("#dataInicio").blur();


//	$("#adicionarSelecaoForm").validate({
//
//		rules: {
//			tipoSelecao:{
//				required:true
//			},
//			dataInicio:{
//				required:true
//			},
//			dataTermino:{
//				required:true,
//				periodo : true
//			},
//			ano: {
//				required: true
//			},
//			sequencial:{
//				required:true
//			},
//			duracao:{
//				required:true
//			},
//			tiposDeDocumento:{
//				required:true
//			},
//			agree: "required"
//		},
//
//		submitHandler: function(form) {
//			form.submit();
//		}
//
//	});

	$(".img-fullscreen").find("img").click(function(){
		$(".img-fullscreen-background").remove();
		var $this = $(this).parent().parent();
		var $background = $("<div></div>");
		var $content = $("<div></div>");

		var $image = $("<img/>");
		var $prev = $("<div><</div>");
		var $next = $("<div>></div>");

		$prev.attr("class", "img-fullscreen-prev-button btn btn-primary");
		$next.attr("class", "img-fullscreen-next-button btn btn-primary");

		$prev.attr("aria-index", $this.index()-1);
		$next.attr("aria-index", $this.index()+1);

		$image.attr("src", $this.find("img").attr("src"));
		$content.append($image);

		if($this.index() !== 0)
			$content.append($prev);

		if($this.index() !== $this.siblings().length)
			$content.append($next);

		$content.attr("class", "img-fullscreen-content");
		$background.attr("class", "img-fullscreen-background");

		$background.append($content);

		$("body").append($background);

		var navigationFunction = function(){
			var index = $(this).attr("aria-index");
			$($(".img-fullscreen")[index]).find("img").click();
		};

		$next.click(navigationFunction);
		$prev.click(navigationFunction);

		$background.click(function(){$(this).remove();});
		$image.click(function(){return false});
	});

	$("div.error-validation:has(span)").find("span").css("color","#a94442");
	$("div.error-validation:has(span)").find("span").parent().parent().addClass("has-error has-feedback");

	
	

	$("#btnAdicionar").click(function() {
		$("#myModalLabel").text("Adicionar contato");
		$("#btnSubmitForm").text("Adicionar");
	});

	$("#myModal").on("hidden.bs.modal", function() {
		document.getElementById("add-contato-form").reset();
	});

	$("input.data").datepicker({
		format : "dd/mm/yyyy",
		todayBtn : "linked",
		autoclose : true,
		startDate: "0",
		language : "pt-BR",
		todayHighlight : true
	});

	var today = new Date();

	$("#ano").datepicker({
		format: " yyyy",
		viewMode: "years", 
		minViewMode: "years",
		startDate: new Date(today.getFullYear(), today.getMonth(), today.getDate()),
		language : "pt-BR",
		todayHighlight : true,
		autoclose : true

	});
	
	$('#formPessoaFamilia').submit(function(){
		$('#rendaMensal').val($('#rendaMensal').maskMoney('unmasked')[0]);
	});
	
	
});


function confirmar(title, link){
	var modal = $("<div></div>");
	modal.attr("class", "modal fade");
	modal.attr("id", "confirm-delete");
	modal.attr("tabindex", "-1");
	modal.attr("role", "dialog");
	modal.attr("aria-labelledby", "myModalLabel");
	modal.attr("aria-hidden", "true");

	var modalDialog = $("<div></div>");
	modalDialog.attr("class", "modal-dialog");

	var modalContent = $("<div></div>");
	modalContent.attr("class", "modal-content");

	var modalHeader = $("<div></div>");
	modalHeader.attr("class", "modal-header");
	modalHeader.html("Excluir");

	var modalBody = $("<div></div>");
	modalBody.attr("class", "modal-body");
	modalBody.html(title);

	var modalFooter = $("<div></div>");
	modalFooter.attr("class", "modal-footer");

	var btnExcluir = $("<a/>");
	btnExcluir.attr("href", link);
	btnExcluir.attr("class", "btn btn-danger");
	btnExcluir.html("Excluir");

	var button = $("<button/>");
	button.attr("type", "button");
	button.attr("class", "btn btn-default");
	button.attr("data-dismiss", "modal");
	button.html("Cancelar");

	modalFooter.append(btnExcluir);
	modalFooter.append(button);

	modalContent.append(modalHeader);
	modalContent.append(modalBody);
	modalContent.append(modalFooter);

	modalDialog.append(modalContent);

	modal.append(modalDialog);

	$("#confirm-delete").remove();
	$("body").append(modal);

	modal.modal();
}

function confirmarLink(mensagem){
	return confirm(mensagem);
}

function submeterForm() {

	var idEdital = $("#id").val();

	var form = $("#add-edital-form");
	var data = ConvertFormToJSON(form);

	var $mensagens = $("#mensagens"); 

	// Tabela
	var dt = $("#editals").dataTable();

	if (idEdital != "" || idEdital.val != null) {

		var request = $.ajax({
			contentType : "application/json; charset=utf-8",
			type : "PUT",
			dataType : "json",
			url : "http://localhost:8080/MAE/edital",
			data : JSON.stringify(data),
			
		});

		request.done(function(data) {
			$("#myModal").modal("hide");
			$mensagens.removeClass("alert-danger");
			$mensagens.addClass("alert-success");
			$mensagens.show();
			$mensagens.text("Edição feita com sucesso");
			$mensagens.fadeOut(4000);

			dt.fnUpdate(data.id, linha, 0);
			dt.fnUpdate(data.DatadeInicio, linha, 1);
			dt.fnUpdate(data.DatadeTermino, linha, 2);
			dt.fnUpdate(data.comentarios, linha, 3);

		});

		request.fail(function(data) {
		});

	} else {
		var request = $.ajax({
			contentType : "application/json; charset=utf-8",
			type : "POST",
			dataType : "json",
			url : "http://localhost:8080/MAE/edital",
			data : JSON.stringify(data),

		});

		request
		.done(function(data) {
			$("#myModal").modal("hide");
			$mensagens.removeClass("alert-danger");
			$mensagens.addClass("alert-success");
			$mensagens.show();
			$mensagens.text("Adição feita com sucesso");
			$mensagens.fadeOut(4000);
			dt.fnAddData({
				"DatadeInicio" : data.DatadeInicio,
				"DatadeTermino" : data.DatadeTermino,
				"comentarios" : data.comentarios,
				"editar" : "<button id=\"btnEditar\" class=\"btn btn-default btn-lg editarEdital\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"povoaForm(\"\/gpa-mae\/edital\/"
					+ data.id
					+ "\/\", \"#add-contato-form\", this);\"> <span class=\"glyphicon glyphicon-edit\"><\/span> <\/button>",
					"excluir" : "<button id=\"btnExcluir\" class=\"btn btn-default btn-lg\" onclick=\"excluir(\"#edital\",\"\/gpa-mae\/edital\/"
						+ data.id
						+ "\/\", this);\"><span class=\"glyphicon glyphicon-trash\"><\/span><\/button>"
			});

		});

		request.fail(function(data) {
		});
	}

};

function mascara(o, f) {
	v_obj = o
	v_fun = f
	setTimeout("execmascara()", 1)
}

function execmascara() {
	v_obj.value = v_fun(v_obj.value)
}

function soNumeros(v) {
	return v.replace(/\D/g, "")
}



//É chamado quando clica no botão de editar contato, ele busca o contato
//completo e povoa o formulário de edição
function povoaForm(uri, form, row) {

	$("#myModalLabel").text("Atualizar contato");
	$("#btnSubmitForm").text("Atualizar");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : uri,
		success : function(data) {

			populate(form, data);

			// Adiciona a linha da tabela que está sendo editada, será usada na
			// função submeterForm
			linha = $(row).parents("tr")[0];

		}
	});

};


function populate(frm, data) {
	$.each(data, function(key, value) {
		$("[name=" + key + "]", frm).val(value);
	});
}

function excluir(idTable, uri, row) {
	var dt = $(idTable).dataTable();
	var $mensagens = $("#mensagens"); 
	bootbox.confirm("Deseja apagar este contato?", function(result) {
		if (result) {
			$.ajax({
				type : "DELETE",
				url : uri,
				success : function(data) {
					if (data == "ok") {
						$mensagens.removeClass("alert-danger");
						$mensagens.addClass("alert-success");
						$mensagens.show();
						$mensagens.text("Remoção Feita com Sucesso");
						$mensagens.fadeOut(4000);
						dt.fnDeleteRow($(row).parents("tr")[0]);

					} else {
						$mensagens.removeClass("alert-success");
						$mensagens.addClass("alert-danger");
						$mensagens.show();
						$mensagens.text("Reportando Erros");

					}
				}
			});
		}
	});
};

function validaHorariosDisponiveisSelecao(){

	var res = "";
	var table = document.getElementById("tabelaHorariosDisponiveis");

	valores = table.querySelectorAll("td select");
	if(valores.length < 6){
		alert("Selecione pelo menos três horários diferentes para a bolsa.");
		return false;
	} else {
		var horarios = new Set();
		for(var k=0;k<valores.length;k+=2){
			var dia = valores[k].value;
			var turno = valores[k+1].value;
			if(dia === "" || turno === ""){
				alert("Os campos de horários disponíveis são obrigatórios.");
				return false;
			}
			horarios.add(dia+turno);
		}
		console.log(horarios.size);
		if(horarios.size < 3){
			alert("Selecione pelo menos três horários diferentes para a bolsa.");
			return false;
		}
	}
	return true;
}

function removerDocumento(docId){
	var row = document.getElementById("row-"+docId);
	row.style.display = "none";
	var input = "<input type="+hidden+" value="+docId+" name="+doc+" />";
	row.innerHTML = input;
}

function buscarSelecao(){

	var editalPesq = document.getElementById("editalBusca").value;
	var anoPesq = document.getElementById("anoBusca").value;
	var tipoBolsaPesq = document.getElementById("tipoBolsaBusca").value;


	var table = document.getElementById("table");
	valores = table.querySelectorAll("td");

	for(var k=0;k<valores.length;k+=6){

		var tipoBolsa = valores[k].innerHTML;
		var ano = valores[k+1].innerHTML;
		var edital = valores[k+2].innerHTML;


		if(!tipoBolsa.contains(tipoBolsaPesq) || !edital.contains(editalPesq) || !ano.contains(anoPesq)){
			valores[k].parentNode.style.display = "none";
		} else {
			valores[k].parentNode.style.display = "table-row";
		}
	}
}



