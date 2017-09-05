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
	
	$(".teste tbody").sortable({
		items:".movel",
	    revert: true,
	    helper: "clone"
	});
	
	$(".teste tbody").on( "sortstart", function( event, ui ) {
       ui.item.attr('data-previndex', ui.item.index());
    });
	
	function atualizarRank(comeco, final){
		for(let i = comeco; i<=final; i++){
			($(".teste tbody").find("tr").eq(i)).find("td:first-child").text((i+1));
		}
	}
	
	function salvarRank(comeco, final){
		let inscricao = ($(".teste tbody").find("tr").eq(comeco)).find("td:nth-child(2)");
		inscricao = inscricao.find("a").attr("href");
		inscricao = inscricao.split("/")[3];
		var url = "/inscricao/rank";
		var token = $("meta[name='_csrf']").attr("content");
		var param = {idInscricao : inscricao, comeco : comeco, fim: final };
		$.ajax({
			url,
			type: "post",
			dataType: "json",
			data: param,
			headers: {"X-CSRF-TOKEN":token},
			
			success: function(response) {			
				if(response.status === "DONE"){
					atualizarRank(comeco, final);
					Materialize.toast(response.alert.message, response.alert.delay, "green rounded");
				}else{
					Materialize.toast(response.alert.message, response.alert.delay, "red rounded");
				}
			}
		});
	}
	
	$(".teste tbody").on( "sortupdate", function( event, ui ) {
		let novoRank = ui.item.index();
		let velhoRank = parseInt(ui.item.attr('data-previndex'));
		ui.item.removeAttr('data-previndex');
		if (ui.originalPosition.top > ui.position.top){
			salvarRank(novoRank, velhoRank);
		}else{
			salvarRank(velhorRank, novoRank);
			
		}		
	});

	
});


