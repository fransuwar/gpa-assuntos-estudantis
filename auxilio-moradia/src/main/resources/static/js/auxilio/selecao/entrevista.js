$(document).ready(function(){
});


$(".check").click(function(){
	let filtros = $.map($("input:checked"), function(c){return c.id; });	
	var url = "/selecao/filtrar/curso";
	var token = $("meta[name='_csrf']").attr("content");
	var id = 34;
	var param = {inscricao : id, cursos: filtros.toString() };
	$.ajax({
		url,
		type: "post",
		dataType: "json",
		data: param,
		headers: {"X-CSRF-TOKEN":token},
		
		success: function(response) {
			if(response.status === "DONE"){
				let string = response.object;
				let obj = [];
				for(let i = 0; i<string.length; i++){
					 
					obj.push(JSON.parse(string[i]))
					
				}
				console.log(obj);
			}
		}
	});
});

