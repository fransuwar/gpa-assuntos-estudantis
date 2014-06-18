var linha;

function submeterForm() {

	console.log("Teste de submite OK");

	var idServidor = $('#id').val();
	
	var form = $('#add-servidor-form');
	var data = ConvertFormToJSON(form);

	//Tabela
	var dt = $("#servidores").dataTable();
	
	if(idServidor != "" || idServidor.val != null){
		console.log("Chamou método que envia requisição AjaxS");
		//Chamada AJAX para EDITAR contato
		
		var request = $.ajax({
			contentType : "application/json; charset=utf-8",
			type : "PUT",
			dataType : "json",
			url : "http://localhost:8080/MAE/servidores/",
			data : JSON.stringify(data),
		
		});
		
		request.done(function(data) {
			$("#myModal").modal("hide");
			$('#mensagens').removeClass('alert-danger');
			$('#mensagens').addClass('alert-success');
			$('#mensagens').show();	
			$('#mensagens').text("Edição feita com sucesso");
		    $('#mensagens').fadeOut(4000);

		    dt.fnUpdate(data.nome, linha, 0);
		    dt.fnUpdate(data.endereco, linha, 1);
		    dt.fnUpdate(data.cidade, linha, 2);
		    dt.fnUpdate(data.fone, linha, 3);
		    console.log("SUCESSO ao editar servidor");
			
		});
		
			request.fail(function(data) {
			console.log("ERRO ao editar servidor");
			
		});
		
	}else{
		var request = $.ajax({
			contentType : "application/json; charset=utf-8",
			type : "POST",
			dataType : "json",
			url : "http://localhost:8080/MAE/servidores/",
			data : JSON.stringify(data),
		
		});
		
		request.done(function(data) {
			$("#myModal").modal("hide");
			$('#mensagens').removeClass('alert-danger');
			$('#mensagens').addClass('alert-success');
			$('#mensagens').show();	
			$('#mensagens').text("Adição feita com sucesso");
		    $('#mensagens').fadeOut(4000);
			console.log("SUCESSO ao adicionar servidor");
			dt.fnAddData({"nome":data.nome + " "+data.sobreNome, 
				"endereco":data.endereco, "cidade":data.cidade,"fone":data.fone,
				"editar":"<button id=\"btnEditar\" class=\"btn btn-default btn-lg editarServidor\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"povoaForm(\'\/MAE\/servidores\/"+ data.id +"\/\', \'#add-servidor-form\', this);\"> <span class=\"glyphicon glyphicon-edit\"><\/span> <\/button>",
				"excluir":"<button id=\"btnExcluir\" class=\"btn btn-default btn-lg\" onclick=\"excluir(\'#servidores\',\'\/gpa-mae\/contatos\/"+ data.id +"\/\', this);\"><span class=\"glyphicon glyphicon-trash\"><\/span><\/button>"});
			
		});
		
		request.fail(function(data) {
			console.log("ERRO ao adicionar servidor");			
		});
	}

};


function ConvertFormToJSON(form){ 
	var array = jQuery(form).serializeArray(); 
	var json = {}; 
	jQuery.each(array, function() { 
		json[this.name] = this.value || ''; 
	}); 
	
	return json; 
}

//É chamado quando clica no botão de editar servidor, ele busca o servidor completo e povoa o formulário de edição
function povoaForm(uri, form, row) {
	
	$("#myModalLabel").text("Atualizar servidor");
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

$(document).ready(function() {
	
	
	$("#btnAdicionar").click(function() {
		$("#myModalLabel").text("Adicionar servidor");
		$("#btnSubmitForm").text("Adicionar");
	});
	
	/*
	$("#gravar").click(function(ev) {
		
		submeterForm();
	});
*/
	$("#myModal").on("hidden.bs.modal", function(e) {
		document.getElementById("add-servidor-form").reset();
		var id =$('#id');
		console.log(id.attr('value',''));
	});

});





/*pronto*/
function excluir(idTable ,uri, row) {
	var dt = $(idTable).dataTable();
	
	bootbox.confirm('Deseja apagar este servidor?', function(result){
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
