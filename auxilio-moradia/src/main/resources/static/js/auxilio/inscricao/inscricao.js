$(document).ready( function() {
	$(".form-propriedade-rural").submit(function (event){
		event.preventDefault();	
		var idQuestionario = $(".form-propriedade-rural").data("id-questionario");	
		var url = "/auxilio/inscricao/api/"+idQuestionario+"/propriedade-rural";
		$.ajax({
			url: url,
			type: 'post',
			success: function(response) {
				console.log(JSON.stringify(response));
				mf_base.doAlertSet(response.alert);
			},		
	        error: function() {
	        	console.log("erro");
	        }
		});
	});
});




