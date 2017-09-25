$(document).ready(function() {
	
	$( ".selecionar" ).click(function(event) {
		event.preventDefault();
		var checkbox = $(this);
		var url = _context + "/inscricao/selecionar";
		var token = $("meta[name='_csrf']").attr("content");
		var id =$(this).attr("id");
		var hab = $(this).is(":checked");
		var param = {idInscricao : id, selecionar : hab };
		$.ajax({
			url,
			type: "post",
			dataType: "json",
			data: param,
			headers: {"X-CSRF-TOKEN":token},
			
			success: function(response) {
				if(response.status === "DONE"){
					if(hab){ 
						checkbox.prop("checked", true);
					}else{
						checkbox.prop("checked", false); 	
					}
				}
			}
		});
	});
	
	$(".ordenavel tbody").sortable({
		items:".movel",
		revert: true,
		helper: "clone"
	});
	
	$(".ordenavel tbody").on( "sortstart", function( event, ui ) {
       ui.item.attr("data-previndex", ui.item.index());
    });
	
	function atualizarSelecionados(param){
		let posicoes = $.map(param.posicoes.split(","), function(c){if(c!== ""){return parseInt(c);}});
		let vagas = parseInt($("#vagas").text());
		let table = $.map(posicoes, function(c){
			return $(".ordenavel tbody").find("tr").eq((c-1)).find(".selecionar");
		});
		for( let i = 0; i < posicoes.length; i++){
			let hab = table[i].is(":checked");
			if(posicoes[i]<=vagas && !hab){
				table[i].trigger("click");
			}else if(posicoes[i]>vagas && hab){
				table[i].trigger("click");
			}
		}
	}
	
	function salvarRank(inscricao, posicoes){
		var url = _context + "/inscricao/ordernar";
		var token = $("meta[name='_csrf']").attr("content");
		var param = {inscricoes: inscricao, posicoes: posicoes};
		$.ajax({
			url,
			type: "post",
			dataType: "json",
			data: param,
			headers: {"X-CSRF-TOKEN":token},			
			success: function(response) {			
				if(response.status === "DONE"){
					Materialize.toast(response.alert.message, response.alert.delay, "green rounded");
					atualizarSelecionados(param);
				}else{
					Materialize.toast(response.alert.message, response.alert.delay, "red rounded");
				}
			}
		});
	}
	
	function atualizarRank(comeco, final){
		let inscricoes = ""; 
		let posicoes = "";
		for(let i = comeco; i<=final; i++){
			($(".ordenavel tbody").find("tr").eq(i)).find("td:first-child").text((i+1));
			inscricoes+= (($(".ordenavel tbody").find("tr").eq(i)).find("a").attr("href")).split("/")[3]+",";
			posicoes += ($(".ordenavel tbody").find("tr").eq(i)).find("td:first-child").text()+",";		
		}
		salvarRank(inscricoes, posicoes);
	}
	
	$(".ordenavel tbody").on( "sortupdate", function( event, ui ) {
		let novoRank = ui.item.index();
		let velhoRank = parseInt(ui.item.attr("data-previndex"));
		ui.item.removeAttr("data-previndex");
		if (ui.originalPosition.top > ui.position.top){
			atualizarRank(novoRank, velhoRank);
		}else{
			atualizarRank(velhoRank, novoRank);
			
		}		
	});
	
	$("#fab-items li a").hover(function(){
		let fab =  $(this).siblings();
		fab.toggleClass("hide");
	}, function(){
		let fab =  $(this).siblings();
		fab.toggleClass("hide");
	});

	
});


