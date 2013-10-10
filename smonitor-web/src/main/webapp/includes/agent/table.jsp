
<div id="agentTable">
	<!-- BUTTONS -->
	<button type="button" class="btn btn-success" data-bind="click: getAll">Refresh</button>
	<button type="button" class="btn btn-success" data-bind="click: create" >Add</button>


	<!-- TABLE PANEL -->

	<div class="panel panel-default">
		<!-- SEARCH -->
		<div class="panel-heading">
			<div class="form-group">
				<input  id="agentsSearch" type="search" class="form-control" placeholder="Search">
			</div>	  
		</div>

		<!-- TABLE -->
		<table id="agentsTable" class="table table-striped sortable">
			<thead>
				<tr>
					<th data-bind="click: function(data, event) { sort('status', data, event) }"><span class="glyphicon glyphicon-sort" style="padding-right: 10px"></span>Status</th>
					<th data-bind="click: function(data, event) { sort('name', data, event) }"><span class="glyphicon glyphicon-sort" style="padding-right: 10px"></span>Name</th>
					<th data-bind="click: function(data, event) { sort('server', data, event) }"><span class="glyphicon glyphicon-sort" style="padding-right: 10px"></span>Server</th>
					<th data-bind="click: function(data, event) { sort('error', data, event) }"><span class="glyphicon glyphicon-sort" style="padding-right: 10px"></span>Error</th>
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

</div>

<script type="text/javascript">
	var agentsViewModel = new AgentsViewModel();
	ko.applyBindings(agentsViewModel, $('#agentTable')[0]);
</script>

