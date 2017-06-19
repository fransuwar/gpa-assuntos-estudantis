$(document).ready(function(){
    //bindEventToNavigation();
    showBreadCrumb();
});

function showBreadCrumb(){
	var tmp = $("#caminho"); 
    tmp.append(sessionStorage.breadcrumb);    
}

$(document).on("click","a",function(event){
	if(isDescendant($("#nav-header"), $(this))){
		if($(this).attr('href') == "/" || $(this).attr('href') == "/logout"){
			sessionStorage.clear();
		}
		else{
			var parent = $(this).parent();
			var children = parent.children();
			var caminhoFinal;
			for (var i = 0; i < children.length;i++){
				
			}
			//sessionStorage.clear();
		}
	}
	else{
		if($(this).attr('data-tooltip')){
			breadcrumbStateSaver($(this).attr('href'), $(this).attr('data-tooltip'));
		}
		else{
			breadcrumbStateSaver($(this).attr('href'), $(this).text());
		}
	}
});


function breadcrumbStateSaver(link, text) {
    if (typeof (Storage) != "undefined") {
        if (sessionStorage.breadcrumb) {
            var breadcrumb = sessionStorage.breadcrumb;
            sessionStorage.breadcrumb = breadcrumb + " <a class='breadcrumb white-text' href='" + link + "'>" + text + "</a>";
        } else {
            sessionStorage.breadcrumb = "<a class='breadcrumb white-text' href='" + link + "'>" + text + "</a>";
        }
    }
}

function isDescendant(parent, child) {
    var node = child[0].parentNode;
    while (node != null) {
        if (node == parent[0]) {
            return true;
        }
        node = node.parentNode;
    }
    return false;
}