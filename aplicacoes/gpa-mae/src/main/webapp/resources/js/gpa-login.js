$(document).ready(function() {

	$('#loginForm').bootstrapValidator({
		group: '.form-group',
		fields : {
			j_username : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório',
					}
				}
			},
			j_password : {
				validators : {
					notEmpty : {
						message : 'Campo Obrigatório',
					}
				}
			}
		}

	})
});