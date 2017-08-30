$(document).ready(function(){
	
});

var listaAluno = $("select.alunos");

$(".filtrar-cursos").on("click", ".filtro-curso",function(){
	let seletor = $(".alunos ul").children();
	let opcoes = $("select.alunos option");
	let filtros = $.map($("input:checked"), function(c){return c.id;});
	if(filtros.length == 0){
		mostrarTodos(seletor);
	}
	else{
		ocultar(opcoes, seletor,filtros);
	}	
});

function ocultar(opcoes, seletor,filtros){
	mostrarTodos(seletor);
	for(let j=0; j< opcoes.length; j++){
		let opcao = opcoes[j];
		let existeAluno = false;
		for(let i=0; i<filtros.length; i++){
			if(opcao.id==filtros[i].toString()) existeAluno = true;
		}
		if(!existeAluno) $(seletor[j]).hide();
	}
}

function mostrarTodos(opcoes){
	opcoes.attr("style","");
}

/*
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
});*/

