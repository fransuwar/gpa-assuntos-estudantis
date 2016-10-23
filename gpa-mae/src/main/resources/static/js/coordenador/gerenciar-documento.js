$(document).ready(function(){
	
	// Inclui link para exclusão de tipo de documento ao abrir a modal
	$("#excluir-tipo-documento").on("shown.bs.modal", function(e) {
		$(this).find(".btn-danger").attr("href", $(e.relatedTarget).data("href"));
	});
});