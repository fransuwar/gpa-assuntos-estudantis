function mostraCampo(el) {
		var inputOutros = document.getElementById("AproveitamentoSatisfatorio");
		if (el.value === "true") {
			inputOutros.style.display = "block";
		} else {
			inputOutros.style.display = "none";
		}
}

function mostraCampoBIA(el) {
	var div = document.getElementById("divHabilidadesDesenvolvidas");
	var div1 = document.getElementById("divHabilidadesDesenvolvidas1");

	if (el.value === "true") {
		div.style.display = "block";
		div1.style.display = "none";
	} else {
		div1.style.display = "block";
		div.style.display = "none";
	}
}

function mostraCampoSelect(el) {

	var v2 = document.getElementById("cQuem");

	if (el.value === "true") {
		v2.style.display = "block";
		document.getElementById("comquem").required = true;
	} else {
		v2.style.display = "none";
		document.getElementById("comquem").required = false;
	}
}

function mostraCampoDiv(el) {
	var v = document.getElementById("LinhaAdd");
	if (el.value === "true") {
		v.style.display = "block";
		document.getElementById("n_pessoasMoradia").required = true;
		document.getElementById("valorAluguel").required = true;
	} else {
		v.style.display = "none";
		document.getElementById("n_pessoasMoradia").required = false;
		document.getElementById("valorAluguel").required = false;
	}

}

function mostraCampoaQualDeficiencia(el){
	var v2 = document.getElementById("QualDeficienciaAluno");

	if (el.value === "true") {
		v2.style.display = "block";
	} else {
		v2.style.display = "none";
	}
}

function mostraCampoaQualMedicamento(el){
	var v2 = document.getElementById("QualMedicamento");

	if (el.value === "true") {
		v2.style.display = "block";
	} else {
		v2.style.display = "none";
	}
}

function mostraCampoMembroC_DoencaGrave(el){
	var v2 = document.getElementById("QueMembroPossuiDoenca");

	if (el.value === 'true') {
		v2.style.display = "block";
	} else {
		v2.style.display = "none";
	}
}

function mostraCampoMembroQualMembroC_deficiencia(el){
	var v2 = document.getElementById("QualMembroC_Deficiencia");

	if (el.value === "true") {
		v2.style.display = "block";
	} else {
		v2.style.display = "none";
	}
}




$(document).ready( function() {
	var s = document.getElementById("renovacaoAuxilio");
	mostraCampo(s);
	s = document.getElementById("moraQuixada");
	mostraCampoSelect(s);
	s = document.getElementById("aluguel-Info");
	mostraCampoDiv(s);
	s = document.getElementById("AlguemUsaMedicamento");
	mostraCampoaQualMedicamento(s);
	s = document.getElementById("apresentaDef");
	mostraCampoaQualDeficiencia(s);
	s = document.getElementById("algumComDoenca");
	mostraCampoMembroC_DoencaGrave(s);
	s = document.getElementById("AlguemComDeficien");
	mostraCampoMembroQualMembroC_deficiencia(s);
	s = document.getElementById("InteresseEmContinuarDesenv-trabalho");
	mostraCampoBIA(s);
});