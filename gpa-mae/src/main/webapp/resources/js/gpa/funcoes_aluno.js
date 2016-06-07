/*
 * Esse aquivo contém todas as funções 
 * javascript das páginas acessadas por alunos.
 */ 

var FormularioAuxilio = function() {
    var self = this;
    var $formElement = $("#questionarioAuxilio");
    var $modalConsolidacao = $("#confirmar-consolidacao");
    
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
    	self.initDivDescricaoBolsa()
    	self.initDivDescricaoGraduacao()
    	self.initDivMesmoEndereco();
    	self.initDivMoraComOutros();
    	self.initSelectEnsinoMedio();
    	self.initSelectEstadoCidade();
    	self.initSelectSituacaoImovel();
    	self.initBotaoFinalizarInscricao();
    	self.initSelectEnsinoFundamental();
    	self.initSelectParentescoVeiculos();
    	self.initSelectParentescoImovelRural();
    	self.initConfirmButtons();
       	
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
            onFinishing: function(event, currentIndex) {
            	$formElement.validate().settings.ignore = ":disabled,:hidden";
                return $formElement.valid();
            },
            onFinished: function(event, currentIndex) {
            	$formElement.submit();
            }
        });
    }
    
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
    self.isExtencaoFotoValida = function($input){
    	var extencoes = ["jpeg", "jpg", "png"];
    	var fileName = $input.val();

    	if(fileName == "")
    		return true;

    	var extencaoFoto = fileName.split(".")[1] ? $input.val().split(".")[1] : "";
    	var res = extencoes.some(function(extencao){
    		return extencao == extencaoFoto;
    	});
    	return res;

    }

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
    	$button.on("click", function(event){
    		if(!isExtencaoFotoValida($input)){
    			$spanError.text("Foto com extensão inválida!");
    		}
    	});
    }
    
    /*
     * Todas as funções envolvendo
     * o select grauParentescoImovelRural
     * estão nesse método
     */
    self.initSelectParentescoImovelRural = function(){
    	var $select = $("#grauParentescoImovelRural");
    	$select.change(function(){
    		var $selectOutroGrauParentesco = $("#outroGrauParentescoImovelRural");
    		var $labelOutroGrauParentesco = $("#labelOutroGrauParentescoImovelRural");
    		if($(this).val() == "OUTROS"){
    			$selectOutroGrauParentesco.val("");
    			$selectOutroGrauParentesco.css("display", "block");
    			$labelOutroGrauParentesco.css("display", "block");
    		}else{
    			$selectOutroGrauParentesco.css("display", "none");
    			$labelOutroGrauParentesco.css("display", "none");
    		}
    	});
    }
    
    /*
     * Todas as funções envolvendo o select de
     * grau de parentesco de veículos estão nesse
     * método. 
     */
    self.initSelectParentescoVeiculos = function(){
    	var $select = $("#grauParentescoVeiculos"); 
    	var $outroGrauParentesco = $("#outroGrauParentescoVeiculos");
    	var $labelOutroParentesco = $("#labelOutroGrauParentescoVeiculos");
    	
    	var $outroGrauImovelRural = $("#outroGrauParentescoImovelRural")
    	var $labelGrauImovelRural = $("#labelOutroGrauParentescoImovelRural");
    	
	    $select.change(function(){
	    	if($(this).val() == "OUTROS"){
				$outroGrauParentesco.val("");
				$outroGrauParentesco.css("display", "block");
				$labelOutroParentesco.css("display", "block");
			}else{
				$outroGrauParentesco.css("display", "none");
				$labelOutroParentesco.css("display", "none");
			}
		});
	    
	    if($select.val() == "OUTROS"){
	    	$outroGrauParentesco.css("display", "block");
	    	$labelOutroParentesco.css("display", "block");

	    	$outroGrauImovelRural.css("display", "block");
	    	$labelGrauImovelRural.css("display", "block");
		}else{
			$outroGrauParentesco.css("display", "none");
			$labelOutroParentesco.css("display", "none");

			$outroGrauImovelRural.css("display", "none");
			$labelGrauImovelRural.css("display", "none");
		}
	    
    }
    
    self.initDivCursinho = function(){
    	var $divNomeCursinho = $("#nome_cursinho");
    	
    	$divNomeCursinho.hide();
    	
    	$("#cursinho").click(function() {
    		if($(this).is(":checked"))
    			$divNomeCursinho.show();
    		else       
    			$divNomeCursinho.hide();
    	});

    }
    //
    self.initDivDescricaoBolsa = function(){
    	var $divDescricaoBolsa = $("#descricao_bolsa");
    	$divDescricaoBolsa.hide();
    	
    	$( "#bolsistaUfc" ).change(function() {
    		  if($("#bolsistaUfc").val()==="true")
    			  $divDescricaoBolsa.show();	  
    		  else
    			  $divDescricaoBolsa.hide(); 
    	});

    }
    
    self.initDivDescricaoGraduacao = function(){
    	var $divDescricaoGraduacao = $("#descricao_graduacao");
    	$divDescricaoGraduacao.hide();
    	
    	$( "#graduacao" ).change(function() {
    		  if($("#graduacao").val()==="true")
    			  $divDescricaoGraduacao.show();	  
    		  else
    			  $divDescricaoGraduacao.hide(); 
    	});

    }
    
    //Copia os valores da residencia ataul para a residencia de origem
    self.initDivMesmoEndereco = function(){
    	$("#mesmoEndereco").click(function() {
    		if($(this).is(":checked")){
    			console.log("Verdade");
    			$("#enderecoOrigem").val($("#endereco").val());
    			$("#numeroOrigem").val($("#numero").val());
    			$("#bairroOrigem").val($("#bairro").val());
    			$("#cepOrigem").val($("#cep").val());
    			$("#complementoOrigem").val($("#complemento").val());
    			$("#referenciaOrigem").val($("#referencia").val());
    		    $("select[name=estadoOrigem]").val($("select[name=estado]").val());
    			$("select[name=estadoOrigem]").change();
    			$("#cidadeOrigem").disabled = false;
    			$("select[name=cidadeOrigem]").val($("select[name=cidade]").val());				
    		}
    		else{     
    			$("#enderecoOrigem").val("");
    			$("#numeroOrigem").val("");
    			$("#bairroOrigem").val("");
    			$("#cepOrigem").val("");
    			$("#complementoOrigem").val("");
    			$("#referenciaOrigem").val("");
    			$("select[name=estadoOrigem]").val(null);
    			$("select[name=cidadeOrigem]").val(null);
    			$("select[name=cidadeOrigem]").attr('disabled', 'disabled');
    		}
    	});

    }

    /*
     * Todas as mascaras do formulário 
     * estão nesse método.
     */
    self.initMascaras = function(){
    	$("#valorMensalFinanciamento").mask("###0000000.00", {reverse: true});
    	$("#areaPropriedadeRural").mask("#####0.00", {reverse: true});
    	$("#rendaMensal").maskMoney({showSymbol:true, symbol:"R$", decimal:".", thousands:"."});
    }

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
			if(option == "Particular com Bolsa"){
				$divBolsaParticularMedio.show();
			} else {
				$divBolsaParticularMedio.hide();
			}	
    	});

    }
    
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

    }
    
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
    }
    
    /*
     * Esse método inicia a biblioteca para
     * preencher os selects de estado e cidade
     * Estado e cidade de origem,
     * Estado e cidade atual.
     */
    self.initSelectEstadoCidade = function(){
    	new StateCityLib().init("estado-endereco", "cidade-endereco");
    	new StateCityLib().init("estado-origem", "cidade-origem");
    }
    
    self.initConfirmButtons = function(){
    	$('.confirm-button').click(function(){
    		var $this = $(this);
    		confirmar($this.attr('aria-title'), $this.attr('aria-destination'));
    	});
    }
}

var formAuxilio = new FormularioAuxilio();

$(document).ready(function(){
	formAuxilio.init();	
});