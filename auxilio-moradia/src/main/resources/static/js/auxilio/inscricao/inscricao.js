$(document).ready( function() {

	// Formulário de moradia
	$('#OUTROS-origem').click(function () {
		if($(this).prop('checked')) {
            $('#div-outroMoradorOrigem').removeClass('no-display');
		} else {
            $('#div-outroMoradorOrigem').addClass('no-display');
		}
    });

    $('#OUTROS').click(function () {
        if($(this).prop('checked')) {
            $('#div-outroMorador').removeClass('no-display');
        } else {
            $('#div-outroMorador').addClass('no-display');
        }
    });



	/*var token = $("meta[name='_csrf']").attr("content");
	var context = !!$("meta[name='_context']").attr("content") || "";
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$(".form-propriedade-rural").submit(function (event){
		event.preventDefault();	
		var idInscricao = $(".form-propriedade-rural").data("id-inscricao");	
		var url = context + "/inscricao/api/" + idInscricao + "/propriedade-rural";
		var propriedadeRural = {
			parentescoProprietario : $("#parentesco").val(),
			area : $("#area").val(),
			cidade : $("#cidade").val(), 
			estado : $("#estado").val(),
			outroParentesco : $("#outroParentesco").val()
		};
		
		$.ajax({
			url : url,
			beforeSend: function (request) {
 				request.setRequestHeader(header, token);
 		    },
		    type : 'POST',
		    data : propriedadeRural,
		    success : function(response) {
		    	mf_base.doAlertSet(response.alert);
			    adicionarPropriedade(response.object);
		    }
		});
	});*/
		
});