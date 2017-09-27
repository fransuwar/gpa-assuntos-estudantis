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
function criarTabela(id, relatorio){
	//tabela referente ao resultado final
	var table = $("#"+id).DataTable({
        
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
        ],
        "language": {
            "sEmptyTable": "Nenhum registro encontrado",
            "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
            "sInfoFiltered": "(Filtrados de _MAX_ registros)",
            "sInfoPostFix": "",
            "sInfoThousands": ".",
            "sLengthMenu": "_MENU_ resultados por página",
            "sLoadingRecords": "Carregando...",
            "sProcessing": "Processando...",
            "sZeroRecords": "Nenhum registro encontrado",
            "sSearch": "Pesquisar",
            "oPaginate": {
                "sNext": "Próximo",
                "sPrevious": "Anterior",
                "sFirst": "Primeiro",
                "sLast": "Último"
            },
            "oAria": {
                "sSortAscending": ": Ordenar colunas de forma ascendente",
                "sSortDescending": ": Ordenar colunas de forma descendente"
            }
        },
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
        	tag.appendTo("#"+relatorio);
        })            
        $('.tooltipped').tooltip();
    }
    
    return table;
}

$(document).ready(function() {
	$("select").material_select();
	$("#inicio").mask("00/00/0000");
	$("#termino").mask("00/00/0000");
	$("#ano").mask("0000");
	$("#vagas").mask("###");
	
	criarTabela("inscricoes-deferidas", "float_relatorio_deferidos");
	criarTabela("inscricoes-indeferidas", "float_relatorio_indeferidos");
	criarTabela("inscricoes-reservas", "float_relatorio_reservas");

});


$(document).on("click", "#excluir-bt", function () {
		var url = _context+"/selecao/excluir/"+$(this).data("id");
		$("#confirm-excluir").attr("href", url);
	});
