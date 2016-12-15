$(document).ready( function() {
	var token = $("meta[name='_csrf']").attr("content");
	var context = $("meta[name='_context']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$(".form-propriedade-rural").submit(function (event){
		event.preventDefault();	
		var idInscricao = $(".form-propriedade-rural").data("id-inscricao");	
		var url = context + "/inscricao/api/"+idInscricao+"/propriedade-rural";
		console.log("antes do ajax");
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
		    success : function(result) {
			    console.log(result);
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




