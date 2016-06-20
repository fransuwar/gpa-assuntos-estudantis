/**
 * 
 */
var FormularioDetalhesInscricao = function() {
	var self = this;

	self.init = function(){
		self.mudarConsolidacao();
		self.mudarBotaoConsolicaoTodos();
		self.mudaConsolidacaoDeTodos();
	}

	//Muda a consolidação de todas as inscrições
	self.mudaConsolidacaoDeTodos = function() {
		$('#consolidacaoTodos').click(function(){

			var idSelecao = $("#idSelecao").val();
			var consolidacao = false;
			if( $("#consolidacaoTodos").text()==="Consolidar Todos"){
				consolidacao= true
			}
			$.ajax({
				type : "GET",
				url : "http://localhost:8080/MAE/servidor/consolidarTodos",
				data :{"idSelecao": idSelecao, "consolidacao": consolidacao},
				dataType : 'json',

				success : function(result) {
					$('.toggle-event').each(function(){
						$(this).prop('checked', consolidacao).change();

					});
					var idSelecao = $("#idSelecao").val()
					if(self.todosConsolidado()){
						$("#consolidacaoTodos").text("Desconsolidar Todos"); 
					}
					else{
						$("#consolidacaoTodos").text("Consolidar Todos");

					}

				},
				error : function(e,b) {
					console.log("ERROR: ", e,b);

				}			
			});


		});
	}


	//Muda a consolidação de uma inscrição
	self.mudarConsolidacao = function() {
		$('.toggle-event').parent().click(function(event){

			var botao = $(this).find('.toggle-event');
			
			var consolidacao = ! (botao.prop('checked'));
			var id = botao.attr("id");
			
		
			$.ajax({
				type : "GET",
				url : "http://localhost:8080/MAE/servidor/consolidar",
				data :{"idInscricao": id , "consolidacao": consolidacao},
				dataType : 'json',

				success : function(result) {
					botao.prop('checked', consolidacao).change();
					self.mudarBotaoConsolicaoTodos();
				},
				error : function(e,b) {
					console.log("ERROR: ", e,b);

				}

			});

			return false;
		} )
	}
	//Muda o botão "consolidar todos" para "Desconsolidar Todos", se todos estão desconsolidados
	self.mudarBotaoConsolicaoTodos = function() {
		var idSelecao = $("#idSelecao").val()
		if(self.todosConsolidado()){
			$("#consolidacaoTodos").text("Desconsolidar Todos"); 
		}
		else{
			$("#consolidacaoTodos").text("Consolidar Todos");

		}
	}

	//Retorna "true" todas as inscrições de uma seleção estão consolidadas
	self.todosConsolidado = function() {
		var retorno = true; 
		$('.toggle-event').each(
				function(){
					if(!$(this).prop('checked'))	
						retorno = false;
				}

		);
		return retorno; 

	}
}

var formDestalhesInsc = new FormularioDetalhesInscricao();

$(document).ready(function(){
	formDestalhesInsc.init();	
});