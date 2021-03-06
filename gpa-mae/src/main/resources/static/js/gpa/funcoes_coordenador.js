/**
 * 
 */
var FormularioDetalhesInscricao = function() {
	var self = this;

	self.init = function(){
		self.mudarConsolidacao();
		self.mudaConsolidacaoDeTodos();
		self.mudaDesconsolidacaoDeTodos();
		self.mostrarOutroPessoaFamilia();
	}
	
	self.mostrarOutroPessoaFamilia = function(){
		$("#select-parentesco-inscricao").change(function() {
			var option = $(this).find("option:selected").text();
			var $divPessoaFamilia = $("#outro-pessoa-familia-inscricao");
			var $inputPessoaFamilia = $("#outroPessoaFamilia");
			
			if(option === "Outro"){
				$divPessoaFamilia.removeClass("hidden");
			} else {
				$divPessoaFamilia.addClass("hidden");
				$inputPessoaFamilia.val("");
			}   
		});
		
	}

	//Muda a consolidação de todas as inscrições
	self.mudaConsolidacaoDeTodos = function() {
		$('#consolidacaoTodos').click(function(){

			var idSelecao = $("#idSelecao").val();
			
			$.ajax({
				type : "GET",
				url : "http://localhost:8080/MAE/inscricao/consolidarTodos",
				data :{"idSelecao": idSelecao, "consolidacao": true},
				dataType : 'json',

				success : function() {
					$('.toggle-event').each(function(){
						$(this).prop('checked', true).change();

					});

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
				url : "http://localhost:8080/MAE/inscricao/consolidarTodos",
				data :{"idSelecao": idSelecao, "consolidacao": false},
				dataType : 'json',

				success : function(result) {
					$('.toggle-event').each(function(){
						$(this).prop('checked', false).change();

					});
					

				},
				error : function(e,b) {
					console.log("ERROR: ", e,b);

				}			
			});


		});
	}


	//Muda a consolidação de uma inscrição
	self.mudarConsolidacao = function() {
		$('.toggle-event').parent().click(function(){

			var botao = $(this).find('.toggle-event');
			
			var consolidacao = ! (botao.prop('checked'));
			var id = botao.attr("id");
			
		
			$.ajax({
				type : "GET",
				url : "http://localhost:8080/MAE/inscricao/consolidar",
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

var CardPanel = function(){
	var self = this;
	var cards = ["card-inscricao","card-comissao","card-arquivos", "card-relatorio", "card-rank"];
	
	self.init = function(){
		self.selecionarCard();
	}
	
	/*
	 * Essa função serve para manter o card selecionado 
	 * depois que o usuário clicar nele.
	 */
	self.selecionarCard = function(){
		var cardSelected = $("#card-selected").text();
		$(".card").removeClass("card-hover");
		cards.forEach(function(card){
			if(card === cardSelected){
				$("#"+card).addClass("card-hover");
			}
			return false;
		});
	}
	
}


var cardPanel = new CardPanel();

var formDestalhesInsc = new FormularioDetalhesInscricao();

$(document).ready(function(){
	cardPanel.init();
	formDestalhesInsc.init();
});