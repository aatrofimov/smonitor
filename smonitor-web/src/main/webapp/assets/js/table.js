
/* Table initialisation */
$(document).on('click', 'table.sortable thead th', function (e) {
	var $this = $(this), $table = $this.parents('table.sortable');	
	
	if ($this.children().length > 0) {		
		$this.find('span.glyphicon').toggleClass('glyphicon-chevron-down');
		$this.find('span.glyphicon').toggleClass('glyphicon-chevron-up');
	} else {
		$table.find('span.glyphicon').remove();
		$this.append('<span style="float: right" class="glyphicon glyphicon-chevron-down"></span>');	
	}
				
	agentsViewModel.sort($this.attr('data-attr-sort'), $this.find('span.glyphicon').hasClass( 'glyphicon-chevron-up' ));	
});	
	
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
		