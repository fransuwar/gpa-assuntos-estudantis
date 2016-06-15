/**
 * 
 */
var FormularioDetalhesInscricao = function() {
	var self = this;

	self.init = function(){
		self.mudarConsolidacao();
		self.mudaConsolidacaoDeTodos();
		self.mudaDesconsolidacaoDeTodos();
	}

	//Muda a consolidação de todas as inscrições
	self.mudaConsolidacaoDeTodos = function() {
		$('#consolidacaoTodos').click(function(){

			var idSelecao = $("#idSelecao").val();
			
			$.ajax({
				type : "GET",
				url : "http://localhost:8080/MAE/servidor/consolidarTodos",
				data :{"idSelecao": idSelecao, "consolidacao": true},
				dataType : 'json',

				success : function(result) {
					$('.toggle-event').each(function(){
						$(this).prop('checked', true).change();

					});
					var idSelecao = $("#idSelecao").val()
					

				},
				error : function(e,b) {
					console.log("ERROR: ", e,b);

				}			
			});


		});
	}
	
	self.mudaDesconsolidacaoDeTodos = function() {
		$('#desconsolidacaoTodos').click(function(){

			var idSelecao = $("#idSelecao").val();
			
			$.ajax({
				type : "GET",
				url : "http://localhost:8080/MAE/servidor/consolidarTodos",
				data :{"idSelecao": idSelecao, "consolidacao": false},
				dataType : 'json',

				success : function(result) {
					$('.toggle-event').each(function(){
						$(this).prop('checked', false).change();

					});
					var idSelecao = $("#idSelecao").val()
					

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
					
				},
				error : function(e,b) {
					console.log("ERROR: ", e,b);

				}

			});

			return false;
		} )
	}
	
}

var formDestalhesInsc = new FormularioDetalhesInscricao();

$(document).ready(function(){
	formDestalhesInsc.init();	
});