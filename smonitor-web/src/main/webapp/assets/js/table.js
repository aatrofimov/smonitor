	
$(document).ready(function() {
	$.expr[':'].filterTableFind = jQuery.expr.createPseudo(function(arg) {
		return function(el) {
			return $(el).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
		};
	});

	$("#agentsSearch").keyup(function() {
		var q = $(this).val();
		var tbody = $("#agentsTable").find("tbody");
		if (q === '') {
			tbody.find('tr').show();
		} else {
			tbody.find('tr').hide();
			tbody.find('td').find('p').filter(':filterTableFind("' + q.replace(/(['"])/g, '\\$1') + '")').closest('tr').show(); // highlight (class=alt) only t
		}
	});
		
});
		