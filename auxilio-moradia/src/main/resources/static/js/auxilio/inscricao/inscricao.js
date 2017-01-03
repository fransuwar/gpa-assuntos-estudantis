
var propriedadeTemplate = $(".propriedade-reference"); propriedadeTemplate.remove();
var adicionarPropriedade = function(p) {
	
	$('#table-propriedade').dataTable().fnAddData( [
		p.parentescoProprietario,
		p.outroParentesco, 
		p.area, 
		p.cidade, 
		p.estado
	]);
}

$(document).ready( function() {
	var token = $("meta[name='_csrf']").attr("content");
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
	});
		
});
		
//		$.post(url, data, function(data) {
//			console.log(data);
//		});
//		$.ajax({
//			url: url,
//			type: 'post',
//			success: function(response) {
//				console.log(JSON.stringify(response));
//				mf_base.doAlertSet(response.alert);
//			},		
//	        error: function() {
//	        	console.log("erro");
//	        }
//		});
//	});




