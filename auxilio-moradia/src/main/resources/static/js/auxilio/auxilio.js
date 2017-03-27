$(document).ready(function() {
    $(this).ajaxSend(function(event, jqxhr, settings) {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        jqxhr.setRequestHeader(header, token);
    });

    aux_base.initAlerts();
    aux_base.initModals();
});

var aux_base = function() {

    return {
        initAlerts : function() {
            $("#toast-container").click(function () {
                $(this).remove();
            });
        },
        initModals : function () {
            $('.modal').modal({
                ready: function(modal, trigger) {
                    $('#confirm-excluir').attr('href', $(trigger).attr('data-url'));
                }
            });
        }
    };
}();