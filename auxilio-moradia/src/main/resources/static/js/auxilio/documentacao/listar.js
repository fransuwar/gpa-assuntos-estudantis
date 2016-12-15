$(document).ready(function() {
	formDialog.create({
		trigger: ".btn-add", 
		title: "Cadastrar tipo de documento", 
		text: "", 
		form: {
			action: $(".form-action-url").text(),
			method: "post", 
			fields: [
		       	{ type: 'text', label: "Nome", name: "nome", id: "nome", col: "col s12" }
		    ]
		}, 
		actions: [
		        { 
		        	text: "OK", 
		        	onClick: function(data) { return true; }, 
		        	modalClose: true, 
		        	submitForm: true
		        }, 
		        { text: "CANCEL", onClick: function() {}, modalClose: true } 
		]
	});
});