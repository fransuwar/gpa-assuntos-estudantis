/*
 * Esse aquivo contém todas as funções 
 * javascript das páginas acessadas por alunos.
 */ 


var FormularioAuxilio = function() {
	var self = this;
	var $formElement = $("#questionarioAuxilio");
	var listaPessoasFamilia = [];
	var listaPropRurais = [];
	var listaBensMoveis = [];
	
	var formPessoaFamilia = {
		"nome" : "input[name=nome]",
		"parentesco" : "select[name=parentesco]",
		"idade" : "input[name=idade]",
		"escolaridade": "input[name=escolaridade]",
		"profissao": "input[name=profissao]",
		"renda": "input[name=rendaMensal]",
	};
	
	var formPessoaFamiliaEditar = {
		"nome" : "input[name=nomeEditar]",
		"parentesco" : "select[name=parentescoEditar]",
		"idade" : "input[name=idadeEditar]",
		"escolaridade": "input[name=escolaridadeEditar]",
		"profissao": "input[name=profissaoEditar]",
		"renda": "input[name=rendaMensalEditar]",
	};

	var formPropRural = {
		"parentesco" : "select[id=grauParentescoImovelRural]",
		"area" : "input[id=areaPropriedadeRural]",
		"cidade" : "input[id=cidadeEstadoImovelRural]",
		"outro" : "input[id=outroGrauParentescoImovelRural]",
	};
	
	var formPropRuralEditar = {
		"parentesco" : "select[id=grauParentescoImovelRuralEditar]",
		"area" : "input[id=areaPropriedadeRuralEditar]",
		"cidade" : "input[id=cidadeEstadoImovelRuralEditar]",
		"outro" : "input[id=outroGrauParentescoImovelRuralEditar]",
	};
	var formPropRuralValidate = {
		"parentesco" : "prParentescoValidate",
		"area" : "prAreaValidate",
		"cidade" : "prCidadeValidate",
		"outro" : "prOutroValidate",
	};
	var formPropRuralValidateEditar = {
		"parentesco" : "prParentescoValidateEditar",
		"area" : "prAreaValidateEditar",
		"cidade" : "prCidadeValidateEditar",
		"outro" : "prOutroValidateEditar",
	};

	var formBemMovel = {
		"parentesco" : "select[id=grauParentescoVeiculos]",
		"finalidade" : "select[id=finalidadeVeiculo]",
		"veiculo" : "input[id=veiculo]",
		"outro" : "input[id=outroGrauParentescoVeiculos]",
	};
	var formBemMovelEditar = {
		"parentesco" : "select[id=grauParentescoVeiculosEditar]",
		"finalidade" : "select[id=finalidadeVeiculoEditar]",
		"veiculo" : "input[id=veiculoEditar]",
		"outro" : "input[id=outroGrauParentescoVeiculosEditar]",
	};
	var formBemMovelValidate = {
		"parentesco" : "bmParentescoValidate",
		"finalidade" : "bmFinalidadeValidate",
		"veiculo" : "bmVeiculoValidate",
		"outro" : "bmOutroValidate",
		
	};
	var formBemMovelValidateEditar = {
		"parentesco" : "bmParentescoValidateEditar",
		"finalidade" : "bmFinalidadeValidateEditar",
		"veiculo" : "bmVeiculoValidateEditar",
		"outro" : "bmOutroValidateEditar",
	};
	
	/*
	 * Método que inicia todos os 
	 * códigos do formulario de auxilio.
	 */
	self.init = function(){
		$formElement.validate();
		
		//Iniciando plugin step.
		self.initStep();
		self.initMascaras();
		self.initDivCursinho();
		self.initDivDescricaoBolsa();
		self.initDivDescricaoGraduacao();
		self.initDivMesmoEndereco();
		self.initDivMoraComOutros();
		self.initSelectEnsinoMedio();
		self.initSelectEstadoCidade();
		self.initSelectSituacaoImovel();
		self.initBotaoFinalizarInscricao();
		self.initSelectEnsinoFundamental();
		self.initSelectParentescoImovelRural();
		self.initSelectParentescoImovelRuralEditar();
		self.initSelectParentescoBemMovel();
		self.initSelectParentescoBemMovelEditar();
		self.initConfirmButtons();
		self.selectParentesco();
		self.addPessoaFamilia();
		self.abrirFormPessoaFamilia();
		
		self.addPropRural();
		self.abrirFormPropRural();
		self.outroParentPropRural();
		self.initPropRurais();
		self.addBemMovel();
		self.abrirFormBemMovel();
		self.initBensMoveis();
		
		self.maskFields();
		self.formularioEnter();
		self.filtroFoto();
	};
	
	self.filtroFoto = function(){
		$( "#input-foto3x4" ).change(function ()  {
			var filename = $("#input-foto3x4").val();
			var fileExtension = filename.substring(filename.lastIndexOf(".") + 1, filename.length);
			if(fileExtension==="jpg" || fileExtension==="png"){
				return true;

			}else{
				$("#input-foto3x4").val("");
				return false;
			}

		});

	};
	
	
	self.formularioEnter = function(){
		$formElement.on("keyup keypress", function(e) {
			var keyCode = e.keyCode || e.which;
			if (keyCode === 13) { 
				e.preventDefault();
				return false;
			}
		});
	};
	
	self.maskFields = function(){
		$('.mask-field').each(function(){$(this).mask($(this).attr('mask-value'))});
	};
	
	self.criarDivPessoaFamilia = function (indice, pessoaFamilia) {
		var novaDivPessoa = $("#pessoaFamilia").clone();
		
		if (indice === 0) {
			var botaoRmv = novaDivPessoa.find(".rmvPessoa");
			botaoRmv.prop("disabled", true);
		}
		
		novaDivPessoa.attr("id", "pessoaFamilia_" + indice);
		novaDivPessoa.removeClass("hidden");
		
		var campoNome = novaDivPessoa.find("h4");
		campoNome.text(pessoaFamilia.nome);

		
		var outrosCampos = novaDivPessoa.find("tbody");
		outrosCampos.append("<tr></tr>");
		
		var parentesco = $("<td>" + pessoaFamilia.parentesco + "</td>");
		var escolaridade = $("<td>" + pessoaFamilia.escolaridade + "</td>");
		var profissao = $("<td>" + pessoaFamilia.profissao + "</td>");
		var idade = $("<td>" + pessoaFamilia.idade + "</td>");
		var renda = $("<td>" + pessoaFamilia.renda + "</td>");
		
		outrosCampos.children().append(parentesco);
		outrosCampos.children().append(escolaridade);
		outrosCampos.children().append(profissao);
		outrosCampos.children().append(idade);
		outrosCampos.children().append(renda);
		
		var input = $("<input type=\"hidden\"/>");
		
		input.attr("value", pessoaFamilia.nome);
		input.attr("name", "pessoas["+ indice +"].nome");
		novaDivPessoa.append(input.clone());
		
		input.attr("value", pessoaFamilia.parentesco);
		input.attr("name", "pessoas["+ indice +"].parentesco");
		novaDivPessoa.append(input.clone());
		
		input.attr("value", pessoaFamilia.escolaridade);
		input.attr("name", "pessoas["+ indice +"].escolaridade");
		novaDivPessoa.append(input.clone());
		
		input.attr("value", pessoaFamilia.profissao);
		input.attr("name", "pessoas["+ indice +"].profissao");
		novaDivPessoa.append(input.clone());
		
		input.attr("value", pessoaFamilia.idade);
		input.attr("name", "pessoas["+ indice +"].idade");
		novaDivPessoa.append(input.clone());
		
		input.attr("value", pessoaFamilia.renda);
		input.attr("name", "pessoas["+ indice +"].rendaMensal");
		novaDivPessoa.append(input.clone());
		
		novaDivPessoa.children().eq(0).attr("data-target", "#pessoa_" + (indice + 1));
		novaDivPessoa.children().eq(1).attr("id","pessoa_" + (indice + 1));
		
		return novaDivPessoa;
	};
	
	self.limparListaPessoasFamilia = function () {
		$("#listaPessoas").children().each(function (indice, item) {
			if ($(item).attr("id") !== "pessoaFamilia") {
				$(item).remove();
			}
		});
	};
	
	self.imprimirListaPessoasFamilia = function () {    	
		self.limparListaPessoasFamilia();

		listaPessoasFamilia.forEach(function (pessoaFamilia, indice) {
			var novaDivPessoa = self.criarDivPessoaFamilia(indice, pessoaFamilia);
			$("#listaPessoas").append(novaDivPessoa); 
		});
		
		$(".rmvPessoa").click(self.removerPessoaFamilia);
		$(".editarPessoa").click(self.editarPessoa);
	};
	
	self.removerPessoaFamilia = function() {
		var pessoa = $(this).parent().parent().parent().parent();
		var idPessoa = pessoa.attr("id").split("_")[1];
		
		if (idPessoa !== "0") {
			listaPessoasFamilia.splice(idPessoa, 1);    	
			self.imprimirListaPessoasFamilia();
		}
	};

	self.editarPessoa = function () {
		var divPessoa = $(this).parent().parent().parent().parent();
		var idPessoa = divPessoa.attr("id").split("_")[1];
		var pessoa = listaPessoasFamilia[idPessoa];
		
		if (idPessoa === "0") {
			$("input[name=nomeEditar]").attr("readonly", true);
			$("select[name=parentescoEditar]").attr("disabled", true);
		} else {
			$("input[name=nomeEditar]").removeAttr("readonly");
			$("select[name=parentescoEditar]").attr("disabled", false);
		}
			
		$("input[name=nomeEditar]").val(pessoa.nome);
		$("select[name=parentescoEditar]").val(pessoa.parentesco);
		$("input[name=idadeEditar]").val(pessoa.idade);
		$("input[name=escolaridadeEditar]").val(pessoa.escolaridade);
		$("input[name=profissaoEditar]").val(pessoa.profissao);
		$("input[name=rendaEditar]").val(pessoa.renda);
		$("input[name=outroEditar]").val(pessoa.renda);
		
		$("#confirmarEdicao").click({indice: idPessoa}, self.confirmarEdicao); 
	};
	   
	self.confirmarEdicao = function (evento) {
		var idPessoa = evento.data.indice;
		var pessoa = listaPessoasFamilia[idPessoa];
		
		pessoa.nome = $("input[name=nomeEditar]").val();
		pessoa.parentesco = $("select[name=parentescoEditar]").val();
		pessoa.escolaridade = $("input[name=escolaridadeEditar]").val();
		pessoa.profissao = $("input[name=profissaoEditar]").val();
		pessoa.idade = $("input[name=idadeEditar]").val();
		pessoa.renda = $("input[name=rendaEditar]").val();
		pessoa.outro = $("input[name=outroEditar]").val();
		
		$("input[name=nomeEditar]").val("");
		$("select[name=parentescoEditar]").val("");
		$("input[name=idadeEditar]").val("");
		$("input[name=escolaridadeEditar]").val("");
		$("input[name=profissaoEditar]").val("");
		$("input[name=rendaEditar]").val("");
		$("input[name=outroEditar]").val("");
		
		self.imprimirListaPessoasFamilia();
		$( "#confirmarEdicao").unbind( "click" );
	};
	
	self.abrirFormPessoaFamilia = function () {
		$("#abrirFormPessoaFamilia").click(function () {
			if (listaPessoasFamilia.length === 0) {
				$("input[name=nome]").attr("readonly", true);
				$("select[name=parentesco]").attr("disabled", true);
				$("option[value=EU]").attr("selected", true);
				
			} else {
				$("input[name=nome]").val("");
				$("input[name=nome]").removeAttr("readonly");
				$("select[name=parentesco]").removeAttr("disabled");
				$("option[value=EU]").remove();
			}
		});
	};
	
	self.selectParentesco = function(){
		$("#select-parentesco").change(function() {
			if($(this).val() === "OUTROS"){
				$("#outro-pessoa-familia").removeClass("hidden");
			}
			alert($(this).val());	    
		});
		
	};
	
	self.addPessoaFamilia = function () {
		$("#addPessoa").click(function() {
			var pessoaFamilia = {}
			
			//No caso de escolherem a opção outros no select e digitarem algo no campo outros 
			//e depois mudarem de opção, o campo outros é limpo.
			if($("#select-parentesco").val() !== "OUTROS"){
				$("input[name=outro]").val("");
			}

			pessoaFamilia.nome = $("input[name=nome]").val();
			pessoaFamilia.parentesco = $("select[name=parentesco]").val();
			pessoaFamilia.idade = $("input[name=idade]").val();
			pessoaFamilia.escolaridade = $("input[name=escolaridade]").val();
			pessoaFamilia.profissao = $("input[name=profissao]").val();
			pessoaFamilia.renda = $("input[name=rendaMensal]").val();
			pessoaFamilia.outro = $("input[name=outro]").val();
			
			
			if(pessoaFamilia.nome == "" || pessoaFamilia.parentesco == "" || pessoaFamilia.idade == "" || pessoaFamilia.escolaridade == "" || pessoaFamilia.profissao == "" || pessoaFamilia.renda == ""){
				$("#alert-pessoa-familia").removeClass("hidden");
			} else{
				
				$("input[name=nome]").val("");
				$("select[name=parentesco]").val("");
				$("input[name=idade]").val("");
				$("input[name=escolaridade]").val("");
				$("input[name=profissao]").val("");
				$("input[name=rendaMensal]").val("");
				$("input[name=outro]").val("");
				
				
				listaPessoasFamilia.push(pessoaFamilia);	

				self.imprimirListaPessoasFamilia();
			}
			
		});
	};
	self.initPropRurais = function () {
		try {
			listaPropRurais = listaGlobalPropRurais;
			self.imprimirListaPropRurais();
		} catch (err) {
		
		}
	};
	self.initBensMoveis = function () {
		try {
			listaBensMoveis = listaGlobalBensMoveis;
			self.imprimirListaBensMoveis();
		} catch (err) {
		
		}
	};
	self.criarDivItem = function(indice, elemento, nomeElemento, form){
		
		var novoDivItem = $("#"+nomeElemento).clone();

		novoDivItem.attr("id", nomeElemento + "_" + indice);
		novoDivItem.removeClass("hidden");

		var table = novoDivItem.find("tbody");
		var tr = $("<tr></tr>");
		
		
		
		if(nomeElemento == "bemMovel"){
			if(elemento.parentesco == "OUTRO"){
				tr.append($("<td>" + elemento.outro + "</td>"));
			}else{
				tr.append($("<td>" + elemento.parentesco + "</td>"));
			}
			tr.append($("<td>" + elemento.finalidade + "</td>"));
			tr.append($("<td>" + elemento.veiculo + "</td>"));
		}else{
			if(elemento.parentesco == "OUTRO"){
				tr.append($("<td>" + elemento.outro + "</td>"));
			}else{
				tr.append($("<td>" + elemento.parentesco + "</td>"));
			}
			tr.append($("<td>" + elemento.area + "</td>"));
			tr.append($("<td>" + elemento.cidade + "</td>"));
		}
		table.append(tr);
		
		var input = $("<input type=\"hidden\"/>");
		
		if(nomeElemento == "bemMovel"){
			input.attr("value", elemento.parentesco);
			input.attr("name", "bemMovel[" + indice + "].parentesco");
			novoDivItem.append(input.clone());
			input.attr("value", elemento.finalidade);
			input.attr("name", "bemMovel[" + indice + "].finalidade");
			novoDivItem.append(input.clone());
			input.attr("value", elemento.veiculo);
			input.attr("name", "bemMovel[" + indice + "].veiculo");
			novoDivItem.append(input.clone());
			input.attr("value", elemento.outro);
			input.attr("name", "bemMovel[" + indice + "].outro");
			novoDivItem.append(input.clone());
		}else{
			input.attr("value", elemento.parentesco);
			input.attr("name", "propRural[" + indice + "].parentesco");
			novoDivItem.append(input.clone());
			input.attr("value", elemento.cidade);
			input.attr("name", "propRural[" + indice + "].cidade");
			novoDivItem.append(input.clone());
			input.attr("value", elemento.area);
			input.attr("name", "propRural[" + indice + "].area");
			novoDivItem.append(input.clone());
			input.attr("value", elemento.outro);
			input.attr("name", "propRural[" + indice + "].outro");
			novoDivItem.append(input.clone());
		}
		
		return novoDivItem;

	}

	self.limparListaPropRurais = function () {
		$("#listaPropRurais").children().each(function (indice, item) {
			if ($(item).attr("id") !== "propRural") {
				$(item).remove();
			}
		});
	};
	
	self.limparListaBemMovel = function () {
		$("#listaBensMoveis").children().each(function (indice, item) {
			if ($(item).attr("id") !== "bemMovel") {
				$(item).remove();
			}
		});
	};

	self.imprimirListaPropRurais = function () {    	
		self.limparListaPropRurais();

		listaPropRurais.forEach(function (propRural, indice) {
			var novaDivProp = self.criarDivItem(indice, propRural, "propRural", self.formPropRural);
			$("#listaPropRurais").append(novaDivProp); 
		});
		
		$(".rmvPropRural").click(self.removerPropRural);
		$(".editarPropRural").click(self.editarPropRural);
	};
	
	self.imprimirListaBensMoveis = function () {    	
		self.limparListaBemMovel();

		listaBensMoveis.forEach(function (bemMovel, indice) {
			var novaDiv = self.criarDivItem(indice, bemMovel, "bemMovel", self.formBemMovel);
			
			$("#listaBensMoveis").append(novaDiv); 
		});
		
		$(".rmvBemMovel").click(self.removerBemMovel);
		$(".editarBemMovel").click(self.editarBemMovel);
	};
	
	self.outroParentPropRural = function(){
		$(formPropRural.parentesco).on("change", function(){
			if($(formPropRural.parentesco).value == "OUTRO"){
				
			}
		});
	}
	
	self.outroParentBemMovel = function(){
		
		
	}
	
	self.removerPropRural = function(){
		var item = $(this).parent().parent();
		var idItem = item.attr("id").split("_")[1];
		listaPropRurais.splice(idItem, 1);
		self.imprimirListaPropRurais();

	}
	
	self.removerBemMovel = function(){
		var item = $(this).parent().parent();
		var idItem = item.attr("id").split("_")[1];
		listaBensMoveis.splice(idItem, 1);
		self.imprimirListaBensMoveis();
	}
	
	self.editarPropRural = function () {
		
		var divPropRural = $(this).parent().parent()
		var idPropRural = divPropRural.attr("id").split("_")[1];
		var propRural = listaPropRurais[idPropRural];
		
		$(formPropRuralEditar.parentesco).val(propRural.parentesco);
		$(formPropRuralEditar.cidade).val(propRural.cidade);
		$(formPropRuralEditar.area).val(propRural.area);
		$(formPropRuralEditar.outro).val(propRural.outro);
		
		$("#"+formPropRuralValidateEditar.parentesco).addClass("hidden");
		$("#"+formPropRuralValidateEditar.area).addClass("hidden");
		$("#"+formPropRuralValidateEditar.cidade).addClass("hidden");
		$("#"+formPropRuralValidateEditar.outro).addClass("hidden");
		
		var $selectOutroGrauParentesco = $("#outroGrauSelectImovelRuralEditar");
		
		if($("#grauParentescoImovelRuralEditar").val() == "OUTRO"){
			$selectOutroGrauParentesco.removeClass("hidden");
			
		}else{
			$selectOutroGrauParentesco.addClass("hidden");
			$("outroGrauParentescoImovelRuralEditar").val("");
		}
		
		$("#confirmarEdicaoPropRural").click({indice: idPropRural}, self.confirmarEdicaoPropRural);
	};
	
	self.editarBemMovel = function () {
		var divBemMovel = $(this).parent().parent()
		var idBemMovel = divBemMovel.attr("id").split("_")[1];
		var bemMovel = listaBensMoveis[idBemMovel];

		$(formBemMovelEditar.parentesco).val(bemMovel.parentesco);
		$(formBemMovelEditar.veiculo).val(bemMovel.veiculo);
		$(formBemMovelEditar.finalidade).val(bemMovel.finalidade);
		$(formBemMovelEditar.outro).val(bemMovel.outro);

		
		$("#"+formBemMovelValidateEditar.parentesco).addClass("hidden");
		$("#"+formBemMovelValidateEditar.veiculo).addClass("hidden");
		$("#"+formBemMovelValidateEditar.finalidade).addClass("hidden");
		$("#"+formBemMovelValidateEditar.outro).addClass("hidden");

		var $selectOutroGrauParentesco = $("#outroGrauSelectBemMovelEditar");
		
		if($("#grauParentescoVeiculosEditar").val() == "OUTRO"){
			$selectOutroGrauParentesco.removeClass("hidden");
			
		}else{
			$selectOutroGrauParentesco.addClass("hidden");
			$("outroGrauParentescoVeiculosEditar").val("");
		}
		
		$("#confirmarEdicaoBemMovel").click({indice: idBemMovel}, self.confirmarEdicaoBemMovel);
	};
	
	self.confirmarEdicaoPropRural = function (evento) {
		var idPropRural = evento.data.indice;
		var propRural = listaPropRurais[idPropRural];
		var flag = false;
		$.each(formPropRuralEditar, function(chave, valor){
			if($(valor).val()==""){
				$("#"+formPropRuralValidateEditar[chave]).removeClass("hidden");
				flag = true;
			}else{
				$("#"+formPropRuralValidateEditar[chave]).addClass("hidden");
				propRural[chave] = $(valor).val();
			}
			
		});
		
		if(flag){
			return false;
		}
		
		self.imprimirListaPropRurais();
		$( "#confirmarEdicaoPropRural").unbind( "click" );
	};
	
	self.confirmarEdicaoBemMovel = function (evento) {
		var idBemMovel = evento.data.indice;
		var bemMovel = listaBensMoveis[idBemMovel];
		var flag = false;
		$.each(formBemMovelEditar, function(chave, valor){
			if($(valor).val()==""){
				$("#"+formBemMovelValidateEditar[chave]).removeClass("hidden");
				flag = true;
			}else{
				$("#"+formBemMovelValidateEditar[chave]).addClass("hidden");
				bemMovel[chave] = $(valor).val();
			}
			
		});
	
		if(flag){
			return false;
		}
		self.imprimirListaBensMoveis();
		$( "#confirmarEdicaoBemMovel").unbind( "click" );
	};
	
	self.criarElementoDoFormPropRural = function(){
		var elemento = {}
		flag = false
		
		if($(formPropRural.parentesco).val() == ""){
			$("#"+formPropRuralValidate.parentesco).removeClass("hidden");
			flag = true;
		}else{
			$("#"+formPropRuralValidate.parentesco).addClass("hidden");
		}	
		elemento.parentesco = $(formPropRural.parentesco).val();
		
		if($(formPropRural.cidade).val() == ""){
			$("#"+formPropRuralValidate.cidade).removeClass("hidden");
			flag = true;
		}else{
			$("#"+formPropRuralValidate.cidade).addClass("hidden");
		}	
		elemento.cidade = $(formPropRural.cidade).val();
		
		if($(formPropRural.area).val() == ""){
			$("#"+formPropRuralValidate.area).removeClass("hidden");
			flag = true;
		}else{
			$("#"+formPropRuralValidate.area).addClass("hidden");
		}	
		elemento.area = $(formPropRural.area).val();
		
		if($(formPropRural.parentesco).val() == "OUTRO"){
			if($(formPropRural.outro).val() == ""){
				$("#"+formPropRuralValidate.outro).removeClass("hidden");
				flag = true;
			}else{
				$("#"+formPropRuralValidate.outro).addClass("hidden");
			}
		}
		elemento.outro = $(formPropRural.outro).val();
		
		if(flag){
			return false;
		}

		return elemento;
	}
	
	self.criarElementoDoFormBemMovel = function(){
		var elemento = {}
		flag = false
		
		if($(formBemMovel.parentesco).val() == ""){
			$("#"+formBemMovelValidate.parentesco).removeClass("hidden");
			flag = true;
		}else{
			$("#"+formBemMovelValidate.parentesco).addClass("hidden");
		}	
		elemento.parentesco = $(formBemMovel.parentesco).val();
		
		if($(formBemMovel.finalidade).val() == ""){
			$("#"+formBemMovelValidate.finalidade).removeClass("hidden");
			flag = true;
		}else{
			$("#"+formBemMovelValidate.finalidade).addClass("hidden");
		}	
		elemento.finalidade = $(formBemMovel.finalidade).val();
		
		if($(formBemMovel.veiculo).val() == ""){
			$("#"+formBemMovelValidate.veiculo).removeClass("hidden");
			flag = true;
		}else{
			$("#"+formBemMovelValidate.veiculo).addClass("hidden");
		}	
		elemento.veiculo = $(formBemMovel.veiculo).val();
		
		if($(formBemMovel.parentesco).val() == "OUTRO"){
			if($(formBemMovel.outro).val() == ""){
				$("#"+formBemMovelValidate.outro).removeClass("hidden");
				flag = true;
			}else{
				$("#"+formBemMovelValidate.outro).addClass("hidden");
			}
		}	
		elemento.outro = $(formBemMovel.outro).val();
		
		if(flag){
			return false;
		}

		return elemento;
	}
	
	

	self.addPropRural = function () {
		$("#addPropRural").click(function() {
			var propRural = self.criarElementoDoFormPropRural();
			if(propRural){
				listaPropRurais.push(propRural);
				self.imprimirListaPropRurais();
			}else{
				return false;
			}
		});
	};
	
	self.abrirFormPropRural = function(){
		$("#abrirFormPropRural").click(function(){
			$.each(formPropRural, function (chave, valor) {
				$("#"+formPropRuralValidate[chave]).addClass("hidden");
				$(valor).val("");
			});
			var $selectOutroGrauParentesco = $("#outroGrauSelectImovelRural");
			if($("#grauParentescoImovelRural").val() == "OUTRO"){
				$("outroGrauParentescoImovelRural").val("");
				$selectOutroGrauParentesco.removeClass("hidden");
				
			}else{
				$selectOutroGrauParentesco.addClass("hidden");
			}
		});
	}
	
	
	self.addBemMovel = function () {
		$("#addBemMovel").click(function() {
			var bemMovel = self.criarElementoDoFormBemMovel();
			if(bemMovel){
				listaBensMoveis.push(bemMovel);
				self.imprimirListaBensMoveis();
			}else{
				return false;
			}
		});
	};
	
	self.abrirFormBemMovel = function(){
		$("#abrirFormBemMovel").click(function(){
			$.each(formBemMovel, function (chave, valor) {
				$("#"+formBemMovelValidate[chave]).addClass("hidden");
				$(valor).val("");
			});
			var $selectOutroGrauParentesco = $("#outroGrauSelectBemMovel");
			if($("#grauParentescoBemMovel").val() == "OUTRO"){
				$("outroGrauParentescoBemMovel").val("");
				$selectOutroGrauParentesco.removeClass("hidden");
			}else{
				$selectOutroGrauParentesco.addClass("hidden");
			}
		});
	}

	
	self.initStep = function() {
		$formElement.steps({
			headerTag: "h3",
			bodyTag: "section",
			transitionEffect: "fade",
			labels: {
				cancel: "Cancelar",
				current: "Passo atual:",
				pagination: "Paginação",
				finish: "Finalizar",
				next: "Próximo",
				previous: "Anterior",
				loading: "Carregando ..."
			},
			autoFocus: true,
			onStepChanging: function(event, currentIndex, newIndex) {
				if (currentIndex < newIndex) {
					$formElement.validate().settings.ignore = ":disabled,:hidden";
					return $formElement.valid();
				}
				return true;
			},
			onFinishing: function() {
				$formElement.validate().settings.ignore = ":disabled,:hidden";
				return $formElement.valid();
			},
			onFinished: function() {
				$formElement.submit();
			}
		});
	};
	
	/*
	 * Todas as funções do select de situação
	 * de imóvel.
	 */
	self.initSelectSituacaoImovel = function(){
		var $select = $("#situacaoImovel");

		var $divValorMensal = $("#div-valor-mensal");
		var $inputValorMensal = $("#valorMensalFinanciamento");
		/*	
		 * 	Essa função é chamada quando o usuário seleciona 
		 * 	alguma opção no select de Situação do Imóvel. 
		 */
		$select.on("change", function(){
			var valorSelecionado = $select.find("option:selected").text();

			if(valorSelecionado === "Financiado"){
				$divValorMensal.removeClass("hidden");
			}else{
				$divValorMensal.addClass("hidden");
				$inputValorMensal.val("0");
			}
		});
	};
 
	/*
	 * Essa função é responsável por validar a extenção do arquivo
	 * que o usuário envia no upload.
	 * As extenções são: jpeg, jpg e png.
	 * 
	 * Se o valor do input for igual a "" então o usuário não fez upload
	 * de nenhuma imagem, logo é retornado true para não aparecer a mensagem
	 * de foto com formato inválido.
	 */
	self.isExtencaoFotoValida = function($input){
		var extencoes = ["jpeg", "jpg", "png"];
		var fileName = $input.val();

		if(fileName === "")
			return true;

		var extencaoFoto = fileName.split(".")[1] ? $input.val().split(".")[1] : "";
		var res = extencoes.some(function(extencao){
			return extencao == extencaoFoto;
		});
		return res;

	};

	/*
	 * Todas as funções envolvendo
	 * o botão de finalizar a inscrição
	 * estão nesse método.
	 */
	self.initBotaoFinalizarInscricao = function(){
		var $button = $("#form-btn");
		var $input = $("#input-foto3x4");
		var $spanError = $("#span-error-foto");
		$spanError.text("");
		$button.on("click", function(){
			if(!isExtencaoFotoValida($input)){
				$spanError.text("Foto com extensão inválida!");
			}
		});
	};
	
	/*
	 * Todas as funções envolvendo
	 * o select grauParentescoImovelRural
	 * estão nesse método
	 */
	self.initSelectParentescoImovelRural = function(){
		var $select = $("#grauParentescoImovelRural");

		var $selectOutroGrauParentesco = $("#outroGrauSelectImovelRural");
		$select.change(function(){		
			if($(this).val() == "OUTRO"){
				$("outroGrauParentescoImovelRural").val("");
				$selectOutroGrauParentesco.removeClass("hidden");
				

			}else{
				$selectOutroGrauParentesco.addClass("hidden");

			}
		});
	};
	self.initSelectParentescoImovelRuralEditar = function(){
		var $select = $("#grauParentescoImovelRuralEditar");
		var $selectOutroGrauParentesco = $("#outroGrauSelectImovelRuralEditar");
		$select.change(function(){
			if($(this).val() == "OUTRO"){
				$("outroGrauParentescoImovelRuralEditar").val("");
				$selectOutroGrauParentesco.removeClass("hidden");
			}else{
				$selectOutroGrauParentesco.addClass("hidden");
			}
		});
		
	};
	self.initSelectParentescoBemMovel = function(){
		var $select = $("#grauParentescoVeiculos");
		var $selectOutroGrauParentesco = $("#outroGrauSelectBemMovel");
		$select.change(function(){
			if($(this).val() == "OUTRO"){
				$("outroGrauParentescoBemMovel").val("");
				$selectOutroGrauParentesco.removeClass("hidden");
				
			}else{
				$selectOutroGrauParentesco.addClass("hidden");
			}
		});
		
	};
	self.initSelectParentescoBemMovelEditar = function(){
		var $select = $("#grauParentescoVeiculosEditar");
		var $selectOutroGrauParentesco = $("#outroGrauSelectBemMovelEditar");
		$select.change(function(){
			if($(this).val() == "OUTRO"){
				$("outroGrauParentescoBemMovelEditar").val("");
				$selectOutroGrauParentesco.removeClass("hidden");
			}else{
				$selectOutroGrauParentesco.addClass("hidden");
			}
		});
		
	};

	
	self.initDivCursinho = function(){
		var $divNomeCursinho = $("#nome_cursinho");
		
		$divNomeCursinho.hide();
		
		$("#cursinho").click(function() {
			if($(this).is(":checked"))
				$divNomeCursinho.show();
			else       
				$divNomeCursinho.hide();
		});
	};
	
	self.initDivDescricaoBolsa = function(){
		var $divDescricaoBolsa = $("#descricao_bolsa");
		$divDescricaoBolsa.hide();
		
		$( "#bolsistaUfc" ).change(function() {
			  if($("#bolsistaUfc").val()==="true")
				  $divDescricaoBolsa.show();	  
			  else
				  $divDescricaoBolsa.hide(); 
		});
	};
	
	self.initDivDescricaoGraduacao = function(){
		var $divDescricaoGraduacao = $("#descricao_graduacao");
		$divDescricaoGraduacao.hide();
		
		$( "#graduacao" ).change(function() {
			  if($("#graduacao").val()==="true")
				  $divDescricaoGraduacao.show();	  
			  else
				  $divDescricaoGraduacao.hide(); 
		});
	};
	
	//Copia os valores da residencia de origem para a residencia atual
	self.initDivMesmoEndereco = function(){
		$("#mesmoEndereco").click(function() {
			if($(this).is(":checked")){
				console.log("Verdade");
				$("#endereco").val($("#enderecoOrigem").val());
				$("#numero").val($("#numeroOrigem").val());
				$("#bairro").val($("#bairroOrigem").val());
				$("#cep").val($("#cepOrigem").val());
				$("#complemento").val($("#complementoOrigem").val());
				$("#referencia").val($("#referenciaOrigem").val());
				$("select[name=estado]").val($("select[name=estadoOrigem]").val());
				$("select[name=estado]").change();
				$("#cidade").disabled = false;
				$("select[name=cidade]").val($("select[name=cidadeOrigem]").val());				
			}
			else{     
				$("#endereco").val("");
				$("#numero").val("");
				$("#bairro").val("");
				$("#cep").val("");
				$("#complemento").val("");
				$("#referencia").val("");
				$("select[name=estado]").val(null);
				$("select[name=cidade]").val(null);
				$("select[name=cidade]").attr('disabled', 'disabled');
			}
		});

	};

	/*
	 * Todas as mascaras do formulário 
	 * estão nesse método.
	 */
	self.initMascaras = function(){
		
		$('[data-mask]').each(function(){ $(this).mask( $(this).attr('data-mask')); });
		
		
		$("#rendaMensal").mask("###0000000.00", {reverse: true});
		$("#valorMensalFinanciamento").mask("###0000000.00", {reverse: true});
		$("#areaPropriedadeRural").mask("#####0.00", {reverse: true});
		$("#renda-pessoa-familia").maskMoney({showSymbol:true, symbol:"R$", decimal:".", thousands:"."});
	};

	/*
	 * Todas as funções do select bolsa
	 * particular ensino médio.
	 */
	self.initSelectEnsinoMedio = function(){
		var $divBolsaParticularMedio = $("#percentualParticularMedio"); 
		$divBolsaParticularMedio.hide();
		
		var $select = $("#ensinoMedio");
		
		//Mostrar o percentual de bolsa quando clicar na opção : "Particular com Bolsa"
		$select.change(function() {
			var option = $(this).find("option:selected").text();
			if(option === "Particular com Bolsa"){
				$divBolsaParticularMedio.show();
			} else {
				$divBolsaParticularMedio.hide();
			}	
		});
	};
	
	self.initSelectEnsinoFundamental = function(){
		var $divBolsaParticularFundamental = $("#percentualParticularFundamental"); 
		$divBolsaParticularFundamental.hide();
		
		var $select = $("#ensinoFundamental");
		
		$select.change(function() {
			var option = $(this).find("option:selected").text();
			if(option == "Particular com Bolsa"){
				$divBolsaParticularFundamental.show();
			} else {
				$divBolsaParticularFundamental.hide();
			}
		});
	};
	
	/*
	 * Todas as funções na div
	 * "mora com outros" estão 
	 * nesse método.
	 */
	self.initDivMoraComOutros = function(){
		var $divMoraComOutros = $("#mora-com-outros"); 
		var $inputComQuemMoraOutros = $("#comQuemMoraOutros");
		
		$divMoraComOutros.hide();
		$("#comQuemMora7").change(function () {
			if ($(this).prop("checked")) {
				$divMoraComOutros.show();
			}else {
				$inputComQuemMoraOutros.val("")
				$divMoraComOutros.hide();
			}
		});
	};
	
	/*
	 * Esse método inicia a biblioteca para
	 * preencher os selects de estado e cidade
	 * Estado e cidade de origem,
	 * Estado e cidade atual.
	 */
	self.initSelectEstadoCidade = function(){
		new StateCityLib().init("estado-endereco", "cidade-endereco");
		new StateCityLib().init("estado-origem", "cidade-origem");
	};
	
	self.initConfirmButtons = function(){
		$('.confirm-button').click(function(){
			var $this = $(this);
			confirmar($this.attr('aria-title'), $this.attr('aria-destination'));
		});
	};
};

var formAuxilio = new FormularioAuxilio();

$(document).ready(function(){
	formAuxilio.init();	
});