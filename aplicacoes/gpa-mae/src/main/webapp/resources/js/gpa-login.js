$(document).ready(function() {
	$("#loginForm").validate({
		rules : {
			j_username : {
				required : true,
				minlength : 11,
				maxlength : 11,
			},
			j_password : {
				required : true
			}
		},
		messages : {
			j_username : {
				required : "O campo username é obrigatório",
			},
			j_password : {
				required : "O campo senha é obrigatório"
			}
		},
		highlight : function(element) {
			$(element).closest('.form-group').addClass('has-error');

		},
		unhighlight : function(element) {
			$(element).closest('.form-group').removeClass('has-error');
		},
		errorElement : 'span',
		errorClass : 'help-block',
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
})