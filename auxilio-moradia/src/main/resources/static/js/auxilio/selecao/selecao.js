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
	$('#ano').mask("0000");
	$('#vagas').mask("###");
});