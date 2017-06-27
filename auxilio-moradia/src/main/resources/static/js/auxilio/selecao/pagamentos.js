$(document).ready(function(){
	var table = $("#pagamento").DataTable({
"scrollX": true,
"paging": false,
"ordering": false,
dom: "Blrtp",
buttons: [
{
	text: "<i class='material-icons'>payment</i>",
	extend: "pdfHtml5",
	title: "Lista - Pagamento",
	orientation: "portrait",
	pageSize: "A4",
className: "btn-floating deep-purple lighten-1 tooltipped",
        }
    ],
});
//Alocação dos botões do dataTables dentro de um FAB do materialize
if(table.buttons){
	$(".dt-button").removeClass("dt-button");
var botoes=table.buttons().container().children();
botoes.detach();
botoes.each(function(){
	$(this).attr("data-position","left");
$(this).attr("data-delay","50");
if($(this).hasClass("buttons-pdf")){
$(this).attr("data-tooltip","Exportar para pagamento");            	
}
var tag = $("<li></li>");
$(this).appendTo(tag);
tag.appendTo("#float_relatorio");
});           
$(".tooltipped").tooltip();
    }
});