$(document).ready(function() {
    $(this).ajaxSend(function(event, jqxhr, settings) {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        jqxhr.setRequestHeader(header, token);
    });

    aux_base.initAlerts();
});

var aux_base = function() {

    return {
        initAlerts : function() {
            $("#toast-container").click(function () {
                $(this).remove();
            });
        }
    };
}();