var _context = $("meta[name='_context']").attr("content") != null? $("meta[name='_context']").attr("content"): "";
$(document).ready(function() {
    $(this).ajaxSend(function(event, jqxhr, settings) {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        jqxhr.setRequestHeader(header, token);
    });

    aux_base.initAlerts();
    aux_base.initModals();
    var str = $('#nomeUsuario').text();
    console.log(str);
	var url = _context + "/selecao/bucarLogin";
	var token = $("meta[name='_csrf']").attr("content");
	var param = {cpf : str};
	$.ajax({
		url,
		type: "post",
		dataType: "json",
		data: param,
		headers: {"X-CSRF-TOKEN":token},
		
		success: function(response) {			
			if(response.status === "DONE"){
				$("#nomeUsuario").html(response.object);
			}else{
				
			}
		}
	});
       
    
});

var aux_base = function() {

    return {
        initAlerts : function() {
            $("#toast-container").click(function () {
                $(this).remove();
            });
        },
        initModals : function () {
            $('.modal').modal({
                ready: function(modal, trigger) {
                    $('#confirm-excluir').attr('href', $(trigger).attr('data-url'));
                }
            });
        }
    };
}();