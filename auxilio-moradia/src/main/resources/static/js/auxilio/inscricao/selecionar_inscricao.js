$(document).ready(function() {
	$( ".selecionar" ).click(function(event) {
		event.preventDefault();
		var checkbox = $(this);
		var url = "/inscricao/selecionar";
		var token = $("meta[name='_csrf']").attr("content");
		var id =$(this).attr("id");
		var hab = $(this).is(":checked");
		var param = {idInscricao : id, selecionar : hab };
		$.ajax({
			url: url,
			type: "post",
			dataType: "json",
			data: param,
			headers: {"X-CSRF-TOKEN":token},
			
			success: function(response) {
				 if(response.status === "DONE"){
					 if(hab){ 
						checkbox.prop('checked', true);
					 }else{
						checkbox.prop('checked', false); 	
					 }
				 }
			 }
		});
	});
	
});


