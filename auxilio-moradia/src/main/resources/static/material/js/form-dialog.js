
var formDialog = function() {

	/*
		PRIVATE
	*/

	var DIALOG_COUNT = 0;
	var __getDialogID = function() {
		return "form-dialog-" + DIALOG_COUNT++;
	};

	var inputTypes = {
		TEXT_LINE: "text", 
		TEXT_AREA: "text-area", 
		DATE: "date", 
		CPF: "cpf", 
		INTEGER: "integer", 
		DECIMAL: "decimal", 
		FILE: "file", 
		CHECK_BOX: "check-box", 
		SWITCH: "switch", 
		RADIO_BUTTON: "radio-button", 
		SELECT: "select", 
		RANGE: "range"
	};

	var dialogTemplate = 
		'<div id=":MODAL_ID" class="modal">' + 
    		'<div class="modal-content">' + 
    			'<div class="row">' + 
    				'<span class="text-20 bold col s12">:TITLE</span><br/>' + 
    				'<p class="text-14 light col s12 justify">:TEXT</p>' + 
    			'</div>' + 
    			'<div class="row">' + 
      				':CONTENT' + 
      			'</div>' + 
    		'</div>' + 
    		'<div class="modal-footer">' + 
      			':ACTIONS'
    		'</div>' + 
  		'</div>';

  	var actionTemplate = 
  		'<button class="modal-action :MODAL_CLOSE waves-effect waves-green btn-flat">:ACTION_TEXT</a>';

	var textTemplate = 
		'<div class="input-field :COL">' + 
        	'<label for=":ID">:LABEL</label>' + 
        	'<input id=":ID" name=":NAME" :DISABLED required=":REQUIRED" type=":TYPE">' + 
        '</div>';

	return {

		/*
			PUBLIC
		*/

		create : function(options) {
			options = options || {};
			options = $.extend({
                trigger: null, 
                title: "Title", 
                text: "", 
                form: [
                	{ "type": inputTypes.TEXT_LINE, "label": "Type anything", "maxLength": 2, id: "input-test", disabled: true }
                ], 
                buttons: [
                	{ text: "OK", onClick: function(formData) {}, closeDialog: true }
                ]
            }, options);

			var id = __getDialogID();
			var form = "";

			options.form.map(function(i) {
				console.log(i.disabled);
				if(i.type === inputTypes.TEXT_LINE) {
					var template = textTemplate
						.replace(/:ID/g, i.id || "")
						.replace(/:NAME/g, i.name || "")
						.replace(/:DISABLED/g, !!i.disabled? "disabled='true'" : "")
						.replace(/:REQUIRED/g, !!i.required)
						.replace(/:TYPE/g, i.type || "")
						.replace(/:LABEL/g, i.label || "")
						.replace(/:COL/g, i.colSize || "col s12");

					form += template;
				}
			});

			var dialog = dialogTemplate
				.replace(/:TITLE/g, options.title)
				.replace(/:TEXT/g, options.text)
				.replace(/:CONTENT/g, form)
				.replace(/:MODAL_ID/g, id);

			$("body").append(dialog);
			Materialize.updateTextFields();

			$(options.trigger).click(function() {
				$("#" + id).openModal();
			});

			console.log(dialog);

		}

	};

} ();
