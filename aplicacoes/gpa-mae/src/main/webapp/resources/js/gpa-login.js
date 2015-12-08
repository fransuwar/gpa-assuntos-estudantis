$(document).ready(function() {

	$('#loginForm').bootstrapValidator({
		group: '.form-group',
		fields : {
			j_username : {
				validators : {
					notEmpty : {
						message : 'Por favor insira o username',
					}
				}
			},
			j_password : {
				validators : {
					notEmpty : {
						message : 'Por favor insira a senha',
					}
				}
			}
		}

	})
});