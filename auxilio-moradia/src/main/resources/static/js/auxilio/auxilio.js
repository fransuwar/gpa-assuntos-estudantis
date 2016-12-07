$(function() {
	loadToast();
});

function loadToast() {
	if($("#alerta-erro").text() != ''){
		toastr.error($("#alerta-erro").text());
	}
	if($("#alerta-info").text() != ''){
		toastr.success($("#alerta-info").text());
	}
}