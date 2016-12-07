$("#inicio").mask("00/00/0000");
$("#termino").mask("00/00/0000");

$(".tools").parent().mouseover(function() {
	$(this).children(".tools").css("opacity", "1");
});

$(".tools").parent().mouseleave(function() {
	$(this).children(".tools").css("opacity", "0");
});