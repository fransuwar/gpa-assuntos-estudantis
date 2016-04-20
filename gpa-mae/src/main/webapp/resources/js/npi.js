var linha;
var accordionFechado;

$(document).ready(function(){
	var form = $("#questionarioAuxilio");
	form.steps({
		headerTag: "h3",
		bodyTag: "section",
		transitionEffect: "fade",
		labels:{
			cancel: "Cancelar",
			current: "Passo atual:",
			pagination: "Paginação",
			finish: "Finalizar",
			next: "Próximo",
			previous: "Anterior",
			loading: "Carregando ..."
		},
		autoFocus: true,
		onStepChanging: function (event, currentIndex, newIndex)
		{
			if(currentIndex < newIndex){
				form.validate().settings.ignore = ":disabled,:hidden";
				return form.valid();
			}
			return true;
		},
		onFinishing: function (event, currentIndex)
		{
			form.validate().settings.ignore = ":disabled,:hidden";
			return form.valid();
		},
		onFinished: function (event, currentIndex)
		{
			form.submit();
		}
	});

	$('.fechado').slideUp();

	$('.panel-heading span.clicavel').on("click", function (e) {
		if ($(this).hasClass('panel-collapsed')) {
			//Expande o painel
			$(this).parents('.panel').find('.panel-body').slideDown();
			$(this).removeClass('panel-collapsed');
			$(this).find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
		}else{
			//Contrai o Painel
			$(this).parents('.panel').find('.panel-body').slideUp();
			$(this).addClass('panel-collapsed');
			$(this).find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
		}

	});

	$("#nome_cursinho").hide();
	$("#cursinho").click(function() {
		if($(this).is(':checked'))
			$("#nome_cursinho").show();
		else       
			$("#nome_cursinho").hide();
	});

	$('#valorMensalFinanciamento').mask("###0000000.00", {reverse: true});
	$('#areaPropriedadeRural').mask("#####0.00", {reverse: true});
	$('#rendaMensal').maskMoney({showSymbol:true, symbol:"R$", decimal:",", thousands:"."});

	$('#tabela-alunos').DataTable({
		"language": {
			"url":"/MAE/resources/js/Portuguese-Brasil.json"
		}
	});
	$('#tabela-selecoes').DataTable({
		"language": {
			"url":"/MAE/resources/js/Portuguese-Brasil.json"
		},
		"columnDefs": 
			[
			 { className: "dt-head-center", "targets": [ 0, 1, 2, 3, 4 ]}
			 ],
			 "paging": false,
			 "searching": false,
			 "ordering": false

	});
	$('#tabela-servidores').DataTable({
		"language": {
			"url":"/MAE/resources/js/Portuguese-Brasil.json"
		}
	});
	$('#tabela-inscritos').DataTable({
		"language": {
			"url":"/MAE/resources/js/Portuguese-Brasil.json"
		}
	});

	$('#tabela-detalhes-selecao-servidores').DataTable({
		"language": {
			"url":"/MAE/resources/js/Portuguese-Brasil.json"
		}
	});


	$.extend(jQuery.validator.messages, {
		required: "Campo obrigatório",
	});


	$.validator.setDefaults({
		highlight : function(element) {
			$(element).closest('.form-item').addClass('has-error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-group').removeClass('has-error');
		},
		errorElement : 'span',
		errorClass : 'text-danger',
		errorPlacement : function(error,
				element) {
			error.insertAfter(element.parent()
					.children().last());
			var itemForm = element.parent();
			var id = element.attr("name");
			$(itemForm).find("span").attr("id",id);
		}
	});

	$('#questionarioIniciacao').validate();

	onSelectSituacaoImovelSelected();

	jQuery.validator.addMethod("periodo", function(value, element) {
		return !moment($('#dataTermino').val()).isBefore($('#dataInicio').val());
	}, "A data de término deve ser posterior à data de início.");


	$('#adicionarSelecaoForm').validate({


		rules: {
			tipoSelecao:{
				required:true
			},
			dataInicio:{
				required:true,
				periodo : true
			},
			dataTermino:{
				required:true
			},
			ano: {
				required: true
			},
			quantidadeVagas: {
				required: true
			},

			sequencial:{
				required:true
			},
			duracao:{
				required:true
			},
			agree: "required"
		},

		submitHandler: function(form) {
			form.submit();
		}

	});

	$('#questionarioAuxilio').validate();

	$('#mora-com-outros').hide();
	$("#comQuemMora7").change(function () {
		if ($("#comQuemMora7").prop('checked')) {
			$('#mora-com-outros').show();
		}
		else {
			$('#mora-com-outros').hide();                                                                    
		}

	});

	//Mostrar o percentual de bolsa quando clicar na opção : "Particular com Bolsa"

	$('#percentualParticularMedio').hide();

	$('#ensinoMedio').change(function() {
		$( "#ensinoMedio option:selected" ).each(function(){
			if($(this).text() == "Particular com Bolsa"){
				$('#percentualParticularMedio').show();
			} else {
				$('#percentualParticularMedio').hide();
			}

		});
	});

	$('#percentualParticularFundamental').hide();

	$('#ensinoFundamental').change(function() {
		$( "#ensinoFundamental option:selected" ).each(function(){    		
			if($(this).text() == "Particular com Bolsa"){

				$('#percentualParticularFundamental').show();
			} else {
				$('#percentualParticularFundamental').hide();
			}

		});
	});



});



/*	
 * 	Essa função é chamada quando o usuário seleciona 
 * 	alguma opção no select de Situação do Imóvel. 
 */
function onSelectSituacaoImovelSelected(){
	var $select = $("#situacaoImovel");

	var $divValorMensal = $("#div-valor-mensal");
	var $inputValorMensal = $("#valorMensalFinanciamento");

	$select.on('change', function(){
		var valorSelecionado = $select.find("option:selected").text();

		if(valorSelecionado == "Financiado"){
			$divValorMensal.removeClass("hidden");
		}else{
			$divValorMensal.addClass("hidden");
			$inputValorMensal.val("0");
		}
	});
}

/*
 * Essa função é responsável por validar a extenção do arquivo
 * que o usuário envia no upload.
 * As extenções são: jpeg, jpg e png.
 * 
 * Se o valor do input for igual a "" então o usuário não fez upload
 * de nenhuma imagem, logo é retornado true para não aparecer a mensagem
 * de foto com formato inválido.
 */
function isExtencaoFotoValida($input){
	var extencoes = ["jpeg", "jpg", "png"];
	var fileName = $input.val();

	if(fileName == "")
		return true;

	var extencaoFoto = fileName.split(".")[1] ? $input.val().split(".")[1] : "";
	console.log(extencaoFoto);
	var res = extencoes.some(function(extencao){
		return extencao == extencaoFoto;
	});
	return res;

}

function onButtonFinalizarInscricaoClick(){
	var $button = $('#form-btn');
	var $input = $("#input-foto3x4");
	var spanError = $("#span-error-foto");
	spanError.text("");
	$button.on("click", function(event){
		if(!isExtencaoFotoValida($input)){
			spanError.text("Foto com extensão inválida!");
		}
	});
}

function mascaraIra(obj) {
	var str = obj.value;

	if(parseInt(str.substring(0, str.length-1)) == 10){
		obj.value = 10;
		return;
	}

	var aux = "";
	for (var k = 0, p = false; k < str.length; k++) {
		if (str[k] == ',' || str[k] == '.') {
			if (!p) {
				aux += '.';
				p = true;
			}
		} else
			aux += str[k];
	}
	obj.value = str = aux;
	var tam = str.length - 1;
	var ch = str[tam];
	var sub = str.substring(0, tam);
	if (ch == '.' && (tam < 1))
		obj.value = sub;

}

function mascaraAgencia(obj){
	var str = obj.value;

	str = str.replace("x", "X");
	str = str.replace("-", "");
	var aux = "";
	for(var k=0;k<str.length;k++){
		if(aux.length==5) break;
		if(str[k]>='0' && str[k]<='9') aux += str[k];
		else {
			if(aux.length==4 && str[k]=='X' ) aux += 'X';
		}
	}
	obj.value = str = aux;

	if(str.length >= 5){
		obj.value = str.substring(0,4)+"-"+str.substring(4,5);
	}
}

function submeterForm() {

	var idEdital = $('#id').val();

	var form = $('#add-edital-form');
	var data = ConvertFormToJSON(form);

	// Tabela
	var dt = $("#editals").dataTable();

	if (idEdital != "" || idEdital.val != null) {

		var request = $.ajax({
			contentType : "application/json; charset=utf-8",
			type : "PUT",
			dataType : "json",
			url : "http://localhost:8080/MAE/edital",
			data : JSON.stringify(data),

		});

		request.done(function(data) {
			$("#myModal").modal("hide");
			$('#mensagens').removeClass('alert-danger');
			$('#mensagens').addClass('alert-success');
			$('#mensagens').show();
			$('#mensagens').text("Edição feita com sucesso");
			$('#mensagens').fadeOut(4000);

			dt.fnUpdate(data.id, linha, 0);
			dt.fnUpdate(data.DatadeInicio, linha, 1);
			dt.fnUpdate(data.DatadeTermino, linha, 2);
			dt.fnUpdate(data.comentarios, linha, 3);

		});

		request.fail(function(data) {
		});

	} else {
		var request = $.ajax({
			contentType : "application/json; charset=utf-8",
			type : "POST",
			dataType : "json",
			url : "http://localhost:8080/MAE/edital",
			data : JSON.stringify(data),

		});

		request
		.done(function(data) {
			$("#myModal").modal("hide");
			$('#mensagens').removeClass('alert-danger');
			$('#mensagens').addClass('alert-success');
			$('#mensagens').show();
			$('#mensagens').text("Adição feita com sucesso");
			$('#mensagens').fadeOut(4000);
			dt
			.fnAddData({
				"DatadeInicio" : data.DatadeInicio,
				"DatadeTermino" : data.DatadeTermino,
				"comentarios" : data.comentarios,
				"editar" : "<button id=\"btnEditar\" class=\"btn btn-default btn-lg editarEdital\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"povoaForm(\'\/gpa-mae\/edital\/"
					+ data.id
					+ "\/\', \'#add-contato-form\', this);\"> <span class=\"glyphicon glyphicon-edit\"><\/span> <\/button>",
					"excluir" : "<button id=\"btnExcluir\" class=\"btn btn-default btn-lg\" onclick=\"excluir(\'#edital\',\'\/gpa-mae\/edital\/"
						+ data.id
						+ "\/\', this);\"><span class=\"glyphicon glyphicon-trash\"><\/span><\/button>"
			});

		});

		request.fail(function(data) {
		});
	}

};

function mascara(o, f) {
	v_obj = o
	v_fun = f
	setTimeout("execmascara()", 1)
}

function execmascara() {
	v_obj.value = v_fun(v_obj.value)
}
function soNumeros(v) {
	return v.replace(/\D/g, "")
}

$(document).ready(
		function() {

			$('div.error-validation:has(span)').find('span').css('color',
			'#a94442');
			$('div.error-validation:has(span)').find('span').parent().parent()
			.addClass('has-error has-feedback');

			$('#confirm-delete').on(
					'show.bs.modal',
					function(e) {
						$(this).find('.btn-danger').attr('href',
								$(e.relatedTarget).data('href'));
					});
			$("#btnAdicionar").click(function() {
				$("#myModalLabel").text("Adicionar contato");
				$("#btnSubmitForm").text("Adicionar");
			});

			$("#myModal").on("hidden.bs.modal", function(e) {
				document.getElementById("add-contato-form").reset();
				var id = $('#id');
			});

			$("input.data").datepicker({
				format : "dd/mm/yyyy",
				todayBtn : "linked",
				autoclose : true,
				language : "pt-BR",
				todayHighlight : true
			});

			$("#ano").datepicker({
				format: " yyyy",
				viewMode: "years", 
				minViewMode: "years",
				language : "pt-BR",
				todayHighlight : true

			});
		});

function ConvertFormToJSON(form) {
	var array = jQuery(form).serializeArray();
	var json = {};
	jQuery.each(array, function() {
		json[this.name] = this.value || '';
	});

	return json;
}

//É chamado quando clica no botão de editar contato, ele busca o contato
//completo e povoa o formulário de edição
function povoaForm(uri, form, row) {

	$("#myModalLabel").text("Atualizar contato");
	$("#btnSubmitForm").text("Atualizar");
	$.ajax({
		type : "GET",
		dataType : "json",
		url : uri,
		success : function(data) {

			populate(form, data);

			// Adiciona a linha da tabela que está sendo editada, será usada na
			// função submeterForm
			linha = $(row).parents('tr')[0];

		}
	});

};

function populate(frm, data) {
	$.each(data, function(key, value) {
		$('[name=' + key + ']', frm).val(value);
	});
}

function excluir(idTable, uri, row) {
	var dt = $(idTable).dataTable();

	bootbox.confirm('Deseja apagar este contato?', function(result) {
		if (result) {
			$.ajax({
				type : "DELETE",
				url : uri,
				success : function(data) {
					if (data == "ok") {
						$('#mensagens').removeClass('alert-danger');
						$('#mensagens').addClass('alert-success');
						$('#mensagens').show();
						$('#mensagens').text("Remoção Feita com Sucesso");
						$('#mensagens').fadeOut(4000);
						dt.fnDeleteRow($(row).parents('tr')[0]);

					} else {
						$('#mensagens').removeClass('alert-success');
						$('#mensagens').addClass('alert-danger');
						$('#mensagens').show();
						$('#mensagens').text("Reportando Erros");

					}
				}
			});
		}
	});
};

function validaHorariosDisponiveisSelecao(){

	var res = "";
	var table = document.getElementById("tabelaHorariosDisponiveis");

	valores = table.querySelectorAll('td select');
	if(valores.length < 6){
		alert("Selecione pelo menos três horários diferentes para a bolsa.");
		return false;
	} else {
		var horarios = new Set();
		for(var k=0;k<valores.length;k+=2){
			var dia = valores[k].value;
			var turno = valores[k+1].value;
			if(dia == "" || turno == ""){
				alert("Os campos de horários disponíveis são obrigatórios.");
				return false;
			}
			horarios.add(dia+turno);
		}
		console.log(horarios.size);
		if(horarios.size < 3){
			alert("Selecione pelo menos três horários diferentes para a bolsa.");
			return false;
		}
	}
	return true;
}

function removerDocumento(docId){
	var row = document.getElementById("row-"+docId);
	row.style.display = "none";
	var input = '<input type="hidden" value="'+docId+'" name="doc" />';
	row.innerHTML = input;
}


function buscarSelecao(){

	var editalPesq = document.getElementById("editalBusca").value;
	var anoPesq = document.getElementById("anoBusca").value;
	var tipoBolsaPesq = document.getElementById("tipoBolsaBusca").value;


	var table = document.getElementById("table");
	valores = table.querySelectorAll('td');

	for(var k=0;k<valores.length;k+=6){

		var tipoBolsa = valores[k].innerHTML;
		var ano = valores[k+1].innerHTML;
		var edital = valores[k+2].innerHTML;


		if(!tipoBolsa.contains(tipoBolsaPesq) || !edital.contains(editalPesq) || !ano.contains(anoPesq)){
			valores[k].parentNode.style.display = "none";
		} else {
			valores[k].parentNode.style.display = "table-row";
		}
	}
}

$(document).ready(function(){
	$("#grauParentescoImovelRural").change(function(){
		if($(this).val() == "OUTROS"){
			$("#outroGrauParentescoImovelRural").val("");
			$("#outroGrauParentescoImovelRural").css("display", "block");
			$("#labelOutroGrauParentescoImovelRural").css("display", "block");
		}else{
			$("#outroGrauParentescoImovelRural").css("display", "none");
			$("#labelOutroGrauParentescoImovelRural").css("display", "none");
		}
	});
});

$(document).ready(function(){
	$("#grauParentescoVeiculos").change(function(){
		if($(this).val() == "OUTROS"){
			$("#outroGrauParentescoVeiculos").val("");
			$("#outroGrauParentescoVeiculos").css("display", "block");
			$("#labelOutroGrauParentescoVeiculos").css("display", "block");
		}else{
			$("#outroGrauParentescoVeiculos").css("display", "none");
			$("#labelOutroGrauParentescoVeiculos").css("display", "none");
		}
	});
});

$(document).ready(function(){
	if($("#grauParentescoVeiculos").val() == "OUTROS"){
		$("#outroGrauParentescoVeiculos").css("display", "block");
		$("#labelOutroGrauParentescoVeiculos").css("display", "block");

		$("#outroGrauParentescoImovelRural").css("display", "block");
		$("#labelOutroGrauParentescoImovelRural").css("display", "block");
	}else{
		$("#outroGrauParentescoVeiculos").css("display", "none");
		$("#labelOutroGrauParentescoVeiculos").css("display", "none");

		$("#outroGrauParentescoImovelRural").css("display", "none");
		$("#labelOutroGrauParentescoImovelRural").css("display", "none");
	}
});

$(document).ready(function(){
	new dgCidadesEstados({
		cidade : document.getElementById('cidade-endereco'),
		estado : document.getElementById('estado-endereco')
	});
	new dgCidadesEstados({
		cidade : document.getElementById('cidade-origem'),
		estado : document.getElementById('estado-origem')
	});
});

function rowAdded(rowElement) {
	$(rowElement).find("input").val('');
}

function novaAba(url){
	window.open(url, '_blank');
}