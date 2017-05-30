function mostraCampo(el) {
		var inputOutros = document.getElementById("AproveitamentoSatisfatorio");
		if (el.value === "Sim") {
			inputOutros.style.display = "block";
		} else {
			inputOutros.style.display = "none";
		}
	}

	function mostraCampoBIA(el) {
		var div = document.getElementById("divHabilidadesDesenvolvidas");
		var div1 = document.getElementById("divHabilidadesDesenvolvidas1");

		if (el.value === "Sim") {
			div.style.display = "block";
			div1.style.display = "none";
		} else {
			div1.style.display = "block";
			div.style.display = "none";
		}
	}

	function mostraCampoSelect(el) {

		var v2 = document.getElementById("cQuem");

		if (el.value === "Sim") {
			v2.style.display = "block";
		} else {
			v2.style.display = "none";
		}
	}

	function mostraCampoDiv(el) {
		var v = document.getElementById("LinhaAdd");
		if (el.value === "Sim") {
			v.style.display = "block";
		} else {
			v.style.display = "none";
		}

	}
	
	function mostraCampoaQualDeficiencia(el){
		var v2 = document.getElementById("QualDeficienciaAluno");

		if (el.value === "Sim") {
			v2.style.display = "block";
		} else {
			v2.style.display = "none";
		}
	}

	function mostraCampoMembroC_DoencaGrave(el){
		var v2 = document.getElementById("QueMembroPossuiDoenca");

		if (el.value === "Sim") {
			v2.style.display = "block";
		} else {
			v2.style.display = "none";
		}
	}
	
	function mostraCampoMembroQualMembroC_deficiencia(el){
		var v2 = document.getElementById("QualMembroC_Deficiencia");

		if (el.value === "Sim") {
			v2.style.display = "block";
		} else {
			v2.style.display = "none";
		}
	}
