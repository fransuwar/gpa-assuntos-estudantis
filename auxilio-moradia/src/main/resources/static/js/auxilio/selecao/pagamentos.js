$(document).ready(function(){
	var table = $("#pagamento").DataTable({
        "scrollX": true,
        "paging": false,
        "ordering": false,
        dom: 'Blrtp',
        buttons: [
            {
            	text: '<i class="material-icons">payment</i>',
            	extend: 'pdfHtml5',
            	title: 'Lista - Pagamento',
                orientation: 'portrait',
                pageSize: 'A4',
                className: 'btn-floating deep-purple lighten-1 tooltipped',
            }
        ],
        /*
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
        }*/
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
        		$(this).attr("data-tooltip", "Exportar para pagamento");            	
        	}
        	var tag = $("<li></li>");
        	$(this).appendTo(tag);
        	tag.appendTo("#float_relatorio");
        })            
        $('.tooltipped').tooltip();
    }
});