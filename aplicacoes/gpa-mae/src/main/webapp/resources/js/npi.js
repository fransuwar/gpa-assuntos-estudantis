var linha;

function submeterForm() {

	console.log("Teste de submite OK");

	var idEdital = $('#id').val();
	
	var form = $('#add-edital-form');
	var data = ConvertFormToJSON(form);

	//Tabela
	var dt = $("#editals").dataTable();
	
	if(idEdital != "" || idEdital.val != null){
		console.log("Chamou método que envia requisição AjaxS");
		//Chamada AJAX para EDITAR edital
		
		var request = $.ajax({
			contentType : "application/json; charset=utf-8",
			type : "PUT",
			dataType : "json",
			url : "http://localhost:8080/MAE/edital",
			data : JSON.stringify(data), 	
		
		});
		
		request.done(function(data) {
			$("#myModal").modal("hide");
			$('#mensagens').removeClass('alert-danger');
			$('#mensagens').addClass('alert-success');
			$('#mensagens').show();	
			$('#mensagens').text("Edição feita com sucesso");
		    $('#mensagens').fadeOut(4000);

		    dt.fnUpdate(data.id , linha, 0);
		    dt.fnUpdate(data.DatadeInicio, linha, 1);
		    dt.fnUpdate(data.DatadeTermino, linha, 2);
		    dt.fnUpdate(data.comentarios, linha, 3);
		    console.log("SUCESSO ao editar Edital");
			
		});
		
			request.fail(function(data) {
			console.log("ERRO ao editar Edital");
			
		});
		
	}else{
		var request = $.ajax({
			contentType : "application/json; charset=utf-8",
			type : "POST",
			dataType : "json",
			url : "http://localhost:8080/MAE/edital",
			data : JSON.stringify(data),
		
		});
		
		request.done(function(data) {
			$("#myModal").modal("hide");
			$('#mensagens').removeClass('alert-danger');
			$('#mensagens').addClass('alert-success');
			$('#mensagens').show();	
			$('#mensagens').text("Adição feita com sucesso");
		    $('#mensagens').fadeOut(4000);
			console.log("SUCESSO ao adicionar edital");
			dt.fnAddData({"DatadeInicio":data.DatadeInicio, "DatadeTermino":data.DatadeTermino, "comentarios":data.comentarios,
				"editar":"<button id=\"btnEditar\" class=\"btn btn-default btn-lg editarEdital\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"povoaForm(\'\/gpa-mae\/edital\/"+ data.id +"\/\', \'#add-contato-form\', this);\"> <span class=\"glyphicon glyphicon-edit\"><\/span> <\/button>",
				"excluir":"<button id=\"btnExcluir\" class=\"btn btn-default btn-lg\" onclick=\"excluir(\'#edital\',\'\/gpa-mae\/edital\/"+ data.id +"\/\', this);\"><span class=\"glyphicon glyphicon-trash\"><\/span><\/button>"});
			
		});
		
		request.fail(function(data) {
			console.log("ERRO ao adicionar contato");			
		});
	}

};

function mascara(o,f){
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
}

function execmascara(){
    v_obj.value=v_fun(v_obj.value)
}
function soNumeros(v){
    return v.replace(/\D/g,"")
}

$(document).ready(function() {
	
	$('div.error-validation:has(span)').find('span').css('color', '#a94442');
	$('div.error-validation:has(span)').find('span').parent().parent().addClass('has-error has-feedback');
	
	$('#confirm-delete').on('show.bs.modal', function(e) { 
	    $(this).find('.btn-danger').attr('href', $(e.relatedTarget).data('href'));
	});
	$("#btnAdicionar").click(function() {
		$("#myModalLabel").text("Adicionar contato");
		$("#btnSubmitForm").text("Adicionar");
	});
	
	/*
	$("#gravar").click(function(ev) {
		
		submeterForm();
	});
*/
	$("#myModal").on("hidden.bs.modal", function(e) {
		document.getElementById("add-contato-form").reset();
		var id =$('#id');
		console.log(id.attr('value',''));
	});
	
	$("input.data").datepicker({
		format : "dd/mm/yyyy",
		todayBtn : "linked",
		autoclose : true,
		language : "pt-BR",
		todayHighlight : true
	});
});


function ConvertFormToJSON(form){ 
	var array = jQuery(form).serializeArray(); 
	var json = {}; 
	jQuery.each(array, function() { 
		json[this.name] = this.value || ''; 
	}); 
	
	return json; 
}

//É chamado quando clica no botão de editar contato, ele busca o contato completo e povoa o formulário de edição
function povoaForm(uri, form, row) {
	
	$("#myModalLabel").text("Atualizar contato");
	$("#btnSubmitForm").text("Atualizar");
	console.log("Verificação da linha" + row.value);
	$.ajax({
		type : "GET",
		dataType : "json",
		url : uri,
		success : function(data) {

			console.log(data);
			populate(form, data);
			
			//Adiciona a linha da tabela que está sendo editada, será usada na função submeterForm
			linha = $(row).parents('tr')[0];
			
		}
	});

};

function populate(frm, data) {
	$.each(data, function(key, value) {
		$('[name=' + key + ']', frm).val(value);
	});
}

function excluir(idTable ,uri, row) {
	var dt = $(idTable).dataTable();
	
	bootbox.confirm('Deseja apagar este contato?', function(result){
		if(result){
			$.ajax({
				type : "DELETE",
				url : uri,
				success : function(data) {
					if(data=="ok"){
					  $('#mensagens').removeClass('alert-danger');
					  $('#mensagens').addClass('alert-success');
					  $('#mensagens').show();	
					  $('#mensagens').text("Remoção Feita com Sucesso");
				      $('#mensagens').fadeOut(4000);
					  dt.fnDeleteRow($(row).parents('tr')[0]);
				
					}else{
						$('#mensagens').removeClass('alert-success');
						$('#mensagens').addClass('alert-danger');
						$('#mensagens').show();	
						$('#mensagens').text("Reportando Erros");
					      
					}
				}
			});
			}
	});
};

/*
 * 
 * //this is the id of the form $("#idForm").submit(function() { var url =
 * "path/to/your/script.php"; var method = $("#idform").Attr('method'); // the
 * script where you handle the form input. $.ajax({ type: method, url: url,
 * data: $("#idForm").serialize(), // serializes the form's elements. success:
 * function(data) { alert(data); // show response from the php script. } });
 * return false; // avoid to execute the actual submit of the form. });
 * 
 * 
 */
