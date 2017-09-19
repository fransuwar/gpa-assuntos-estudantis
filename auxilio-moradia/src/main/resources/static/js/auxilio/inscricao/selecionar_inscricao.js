$(document).ready(function() {
	$( ".selecionar" ).click(function(event) {
		event.preventDefault();
		var checkbox = $(this);
		var _context = $("meta[name='_context']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		var url = _context+"/inscricao/selecionar";
		var id =$(this).attr("id");
		var hab = $(this).is(":checked");
		var param = {idInscricao : id, selecionar : hab };
		$.ajax({
			url,
			type: "post",
			dataType: "json",
			data: param,
			headers: {"X-CSRF-TOKEN":token},
			
			success: function(response) {
				if(response.status === "DONE"){
					if(hab){ 
						checkbox.prop("checked", true); 
					}else{
						checkbox.prop("checked", false);  	
					}
				}
			}
		});
	});
	
});


$(document).on("click", ".alterarParecer", function () {
    $("#nomeAluno").text("Aluno(a): "+$(this).data("text"));
    $("#disabled").val($(this).data("id"))
});

$("#modal1 form").on("submit", function(event){
	event.preventDefault();	
	let _context = $("meta[name='_context']").attr("content");
	let token = $("meta[name='_csrf']").attr("content");
	let id = $("#disabled").val();
	let resultado = $(this).find("select").val();
	let observacao = $(this).find("#observacao").val();
	let url = _context+"/inscricao/parecerFinal";
	let selecionar = $($(".alterarParecer[data-id='"+id+"']").parent().parent());
	var param = {inscricaoId : id, resultado : resultado, observacao : observacao};
	let button = $(".alterarParecer[data-id='"+id+"']").find("#inscResult");
	$.ajax({
		url,
		type: "post",
		dataType: "json",
		data: param,
		headers: {"X-CSRF-TOKEN":token},
		
		success: function(response) {
			button.removeClass('red green grey');
			if(response.status === "DONE"){
				console.log(selecionar)
				if(param.resultado == "DEFERIDO"){
					button.text('Deferido');
					button.addClass('green');
				}else if(param.resultado == "INDEFERIDO"){
					button.text('Indeferido');
					button.addClass('red');
				}else{
					button.text('Não Avaliado');
					button.addClass('grey');
				}
			}
			$('.modal').modal('close');
		}
	});
});

