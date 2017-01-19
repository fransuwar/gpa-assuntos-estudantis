$(document).ready( function() {

	// Formulário de moradia
	$('#OUTROS-origem').click(function () {
		if($(this).prop('checked')) {
            $('#div-outroMoradorOrigem').removeClass('no-display');
            $('#outroMoradorOrigem').attr('required', 'required');
		} else {
            $('#div-outroMoradorOrigem').addClass('no-display');
            $('#outroMoradorOrigem').removeAttr('required', 'required');
		}
    });

    $('#OUTROS').click(function () {
        if($(this).prop('checked')) {
            $('#div-outroMorador').removeClass('no-display');
            $('#outroMorador').attr('required', 'required');
        } else {
            $('#div-outroMorador').addClass('no-display');
            $('#outroMorador').removeAttr('required', 'required');
        }
    });

    $('#quantidadeBemMovel').change(function () {
        if($(this).val() > 0) {
            $('#div-descricao-bem-movel').removeClass('no-display');
            $('#descricaoBemMovel').attr('required', 'required');
        } else {
            $('#div-descricao-bem-movel').addClass('no-display');
            $('#descricaoBemMovel').removeAttr('required');
        }
    });

    $('#situacaoImovel').change(function () {
        if($(this).val() === 'FINANCIADO') {
            $('#div-financiamento').removeClass('no-display');
            $('#financiamento').attr('required', 'required');
        } else {
            $('#div-financiamento').addClass('no-display');
            $('#financiamento').removeAttr('required', 'required');
        }
    });

    // Formulário de histórico escolar
    $('#ensinoMedio').change(function () {
        if($(this).val() != 'PUBLICO') {
            $('#div-particular').removeClass('no-display');
            $('#bolsistaEnsinoMedio').attr('required', 'required');
            if($('#bolsistaEnsinoMedio').val() === 'true') {
                $('#div-percentual').removeClass('no-display');
                $('#percentualEnsinoMedio').attr('required', 'required');
            }
        } else {
            $('#div-particular,#div-percentual').addClass('no-display');
            $('#bolsistaEnsinoMedio').removeAttr('required');
        }
    });

    $('#bolsistaEnsinoMedio').change(function () {
    	if($(this).val() === 'true') {
            $('#div-percentual').removeClass('no-display');
            $('#percentualEnsinoMedio').attr('required', 'required');
        } else {
            $('#div-percentual').addClass('no-display');
            $('#percentualEnsinoMedio').removeAttr('required', 'required');
        }
    });

    $('#participacaoAuxilio').change(function () {
        if($(this).val() === 'true') {
            $('#div-quantidade-participacao').removeClass('no-display');
            $('#quantidadeParticipacaoAuxilio').attr('required', 'required');
        } else {
            $('#div-quantidade-participacao').addClass('no-display');
            $('#quantidadeParticipacaoAuxilio').removeAttr('required', 'required');
            $('#quantidadeParticipacaoAuxilio').val('');
        }
    });

    $('#bolsistaAtual').change(function () {
        if($(this).val() === 'true') {
            $('#div-bolsa-atual').removeClass('no-display');
            $('#bolsaAtual').attr('required', 'required');
        } else {
            $('#div-bolsa-atual').addClass('no-display');
            $('#bolsaAtual').removeAttr('required', 'required');
            $('#bolsaAtual').val('');
        }
    });

    $('#graduacao').change(function () {
        if($(this).val() === 'true') {
            $('#div-outra-graduacao').removeClass('no-display');
            $('#outraGraduacao').attr('required', 'required');
        } else {
            $('#div-outra-graduacao').addClass('no-display');
            $('#outraGraduacao').removeAttr('required', 'required');
            $('#outraGraduacao').val('');
        }
    });

    $('#OUTROS-servico').click(function () {
        if($(this).prop('checked')) {
            $('#div-outro-servico').removeClass('no-display');
            $('#outroServico').attr('required', 'required');
        } else {
            $('#div-outro-servico').addClass('no-display');
            $('#outroServico').removeAttr('required', 'required');
        }
    });

    $('#OUTROS-trajeto').click(function () {
        if($(this).prop('checked')) {
            $('#div-outro-trajeto').removeClass('no-display');
            $('#outroTrajeto').attr('required', 'required');
        } else {
            $('#div-outro-trajeto').addClass('no-display');
            $('#outroTrajeto').removeAttr('required', 'required');
        }
    });

    // Formulário de situação socioeconômica
    $('#medicamento').change(function () {
        if($(this).val() === 'true') {
            $('#div-doenca-medicamento').removeClass('no-display');
            $('#doencaMedicamento').attr('required', 'required');
        } else {
            $('#div-doenca-medicamento').addClass('no-display');
            $('#doencaMedicamento').removeAttr('required', 'required');
        }
    });

    $('#deficiencia').change(function () {
        if($(this).val() === 'true') {
            $('#div-nome-deficiencia').removeClass('no-display');
            $('#nomeDeficiencia').attr('required', 'required');
        } else {
            $('#div-nome-deficiencia').addClass('no-display');
            $('#nomeDeficiencia').removeAttr('required', 'required');
        }
    });

    $('#doencaGrave').change(function () {
        if($(this).val() === 'true') {
            $('#div-membro-doenca-grave').removeClass('no-display');
            $('#membroDoencaGrave').attr('required', 'required');
        } else {
            $('#div-membro-doenca-grave').addClass('no-display');
            $('#membroDoencaGrave').removeAttr('required', 'required');
        }
    });

    $('#membroDeficiencia').change(function () {
        if($(this).val() === 'true') {
            $('#div-membro-deficiencia').removeClass('no-display');
            $('#membrodeficiencia').attr('required', 'required');
        } else {
            $('#div-membro-deficiencia').addClass('no-display');
            $('#membroDeficiencia').removeAttr('required', 'required');
        }
    });

    $('#assistenciaMedica').change(function () {
        if($(this).val() === 'true') {
            $('#div-valor-assistencia').removeClass('no-display');
            $('#valorAssistenciaMedica').attr('required', 'required');
        } else {
            $('#div-valor-assistencia').addClass('no-display');
            $('#valorAssistenciaMedica').removeAttr('required', 'required');
        }
    });

    $('#despesaMedicamento').change(function () {
        if($(this).val() === 'true') {
            $('#div-despesa-medicamento').removeClass('no-display');
            $('#descricaoDespesaMedicamento').attr('required', 'required');
        } else {
            $('#div-despesa-medicamento').addClass('no-display');
            $('#descricaoDespesaMedicamento').removeAttr('required', 'required');
        }
    });

    $('#beneficio').change(function () {
        if($(this).val() === 'true') {
            $('#div-beneficio').removeClass('no-display');
            $('#descricaoBeneficio').attr('required', 'required');
        } else {
            $('#div-beneficio').addClass('no-display');
            $('#descricaoBeneficio').removeAttr('required', 'required');
        }
    });


});