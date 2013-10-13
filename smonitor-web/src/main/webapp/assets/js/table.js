ko.bindingHandlers.element = {
    init: function(element, valueAccessor) {
      var value = valueAccessor();
      value(element);
    }
};

$(document).ready(function() {
	$.expr[':'].filterTableFind = jQuery.expr.createPseudo(function(arg) {
		return function(el) {
			return $(el).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
		};
	});	
});
		