$(document).ready(function(){
	
});

$(".filtrar-cursos").on("click", ".filtro-curso",function(){
	let seletor = $(".alunos ul").children();
	let opcoes = $("select.alunos option");
	let filtros = $.map($("input:checked"), function(c){return c.id;});
	if(filtros.length == 0){
		seletor.removeAttr("hidden");
	}
	else{
		ocultar(opcoes, seletor,filtros);
	}	
});

function ocultar(opcoes, seletor,filtros){
	opcoes.removeAttr("hidden");
	let primeiraOpcao = null;
	for(let j=0; j< opcoes.length; j++){
		let opcao = opcoes[j];
		let existeAluno = false;
		for(let i=0; i<filtros.length; i++){
			if(opcao.id==filtros[i].toString()) existeAluno = true;
		}
		if(!existeAluno){
			$(seletor[j]).attr("hidden","");			
		}
		else if(primeiraOpcao == null){
			primeiraOpcao = opcao;
		}
	}
	atualizarSeletor($(seletor), primeiraOpcao);
}

function atualizarSeletor(seletor, opcao){	
	let exibivel = $(".alunos ul").parent().find("input");
	let filtrados = $.map($(seletor), function(c){ if(!$(c).prop("hidden")) return $(c).text() });
	exibivel.val(filtrados[0]);
	$('select.alunos').val($(opcao).val());
}

$("form#agendamento-alunos").on("submit",function(event){
	event.preventDefault();
	let form = $(this);
	let select = form.find("select").val();
	let agendamento = form.find("input[name='agendamento']").attr("value");
	var url = "/selecao/agendamentoEntrevista/adicionar";
	var token = $("meta[name='_csrf']").attr("content");
	var id =$(this).attr("id");
	var hab = $(this).is(":checked");
	var param = {idAgendamento : agendamento, idInscricao : select };
	$.ajax({
		url,
		type: "post",
		dataType: "json",
		data: param,
		headers: {"X-CSRF-TOKEN":token},
		
		success: function(response) {			
			if(response.status === "DONE"){
				adicionarAluno(JSON.parse(response.object), form);
				Materialize.toast(response.alert.message, response.alert.delay, "green rounded")
			}else{
				Materialize.toast(response.alert.message, response.alert.delay, "red rounded")
			}
		}
	});

});

function adicionarAluno(result, form){
	let chip = 	"<div class='chip deep-purple lighten-1'>" +
					"<div class='text-17 light white-text'>"+result.nome+"</div>" +
					"<a title='Excluir' href='/selecao/agendamentoEntrevista/"+result.agendamento+"/excluir/"+result.inscricao+"'>" +
					"<i class='close white-text material-icons'>close</i></a>" +
				"</div>"
	let select = $("select.alunos").find("option[value='"+result.inscricao+"']");
	let div = $("div.alunos")
	let parent = form.parent().find(".center");
	parent.append(chip);
	select.remove();
	$('.alunos').material_select('destroy');
	$('.alunos').material_select();
			
}

