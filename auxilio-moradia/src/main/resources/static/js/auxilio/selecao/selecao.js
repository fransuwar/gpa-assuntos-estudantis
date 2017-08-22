function filtra(ele) {
	if (ele != null && ele.value === "vertodas") {
		$(".AUXILIO_MORADIA").show();
		$(".INICIACAO_ACADEMICA").show();
		$(".AUXILIO_EMERGENCIAL").show();
	} else if (ele != null && ele.value === "AUXILIO_MORADIA") {
		$(".AUXILIO_MORADIA").show();
		$(".INICIACAO_ACADEMICA").hide();
		$(".AUXILIO_EMERGENCIAL").hide();
	} else if (ele != null && ele.value === "INICIACAO_ACADEMICA") {
		$(".AUXILIO_MORADIA").hide();
		$(".INICIACAO_ACADEMICA").show();
		$(".AUXILIO_EMERGENCIAL").hide();
	} else if (ele != null && ele.value === "AUXILIO_EMERGENCIAL") {
		$(".AUXILIO_MORADIA").hide();
		$(".INICIACAO_ACADEMICA").hide();
		$(".AUXILIO_EMERGENCIAL").show();
	}
}

$(document).ready(function() {
	$("select").material_select();
	$("#inicio").mask("00/00/0000");
	$("#termino").mask("00/00/0000");
	$("#ano").mask("0000");
	$("#vagas").mask("###");
	
	criarTabela("#inscricoes-deferidas", "#float_relatorio_deferidos");
	criarTabela("#inscricoes-indeferidas", "#float_relatorio_indeferidos");
	
	
});


function criarTabela(id, relatorio){
	//tabela referente ao resultado final - indeferidas
	var table = $(id.string).DataTable({
        "paging": false,
        "ordering": false,
        dom: 'Blrtp',
        buttons: [
            {
            	text: '<i class="material-icons">picture_as_pdf</i>',
            	extend: 'pdfHtml5',
                orientation: 'portrait',
                pageSize: 'A4',
                className: 'btn-floating deep-purple lighten-1 tooltipped',
            },              
            {	
				extend:'excelHtml5',
				text:'<i class="material-icons">insert_chart</i>',
             	className: 'btn-floating deep-purple lighten-1 tooltipped'                  
            }
        ]
    });
	
    //Alocação dos botões do dataTables dentro de um FAB do materialize
    if(table.buttons){
    	$(".dt-button").removeClass("dt-button");
        var botoes = table.buttons().container().children();
        botoes.detach();
        botoes.each(function (){
        	$(this).attr("data-position", "left");
        	$(this).attr("data-delay", "50");
        	if($(this).hasClass("buttons-pdf")){
        		$(this).attr("data-tooltip", "Exportar para pdf");            	
        	}else{
        		$(this).attr("data-tooltip", "Exportar para excel"); 
        	}
        	var tag = $("<li></li>");
        	$(this).appendTo(tag);
        	tag.appendTo(relatorio);
        })            
        $('.tooltipped').tooltip();
    }
}