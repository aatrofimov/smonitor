
<!-- BUTTONS -->
<button type="button" class="btn btn-success" data-bind="click: getAll">Refresh</button>
<button type="button" data-bind="click: create" class="btn btn-success">Add</button>


<!-- TABLE PANEL -->

<div class="panel panel-default">
	<!-- SEARCH -->
	<div class="panel-heading">
		<div class="form-group">
			<input id="agentsSearch" type="search" class="form-control" placeholder="Search">
		</div>	  
	</div>

	<!-- TABLE -->
	<table id="agentsTable" class="table table-striped sortable">
		<thead>
			<tr>
				<th data-attr-sort="status">Status</th>
				<th data-attr-sort="name">Name</th>
				<th data-attr-sort="server">Server</th>
				<th data-attr-sort="error">Error</th>
			</tr>
		</thead>	

		<tbody data-bind="foreach: agents">
			<tr>
				<td data-value="agent.enabled()">
					<div class="btn-group" data-bind="ifnot: request">
						<button type="button" class="btn dropdown-toggle" data-toggle="dropdown"  data-bind="css: { 'btn-default': !agent.enabled(), 'btn-danger': message, 'btn-success': !message() && agent.enabled() }">
							<span class="icon-record"> </span>
						</button>						
						<ul class="dropdown-menu">
							<li data-bind="if: agent.enabled"><a href="#" data-bind="click: $parent.refresh" >Refresh</a></li>
							<li><a href="#" >Edit</a></li>
							<li><a href="#" data-bind="click: $parent.delete">Delete</a></li>
						</ul>			
					</div>
					<div data-bind="if: request">
						<span  class="icon-arrows-ccw animate-spin" style='font-size: 33px'></span>
					</div>
					<span style="display: none" data-bind="text: agent.guid"></span>
				</td>
				<td data-value="agent.name"><p data-bind="text: agent.name"></p></td>			
				<td data-value="agent.server"><p data-bind="text: agent.server"></p></td>			
				<td data-value="message"><p data-bind="text: message"></p></td>
			</tr>
		</tbody>			
	</table>	

</div>



