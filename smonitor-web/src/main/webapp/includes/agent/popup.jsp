 

<div class="modal fade" id="addAgentPopup">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Create agent</h4>
			</div>
			<div class="modal-body">

				<div class="control-group">
					<div class="controls">
						<input data-bind="value: agent.name" type="text" id="inputAgent" placeholder="Agent name" style="width: 150px;">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<input data-bind="value: agent.server" type="text" id="inputServer" placeholder="Description" style="width: 300px;">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputEnabled">Enabled</label>
					<div class="controls">
						<input type="checkbox" data-bind="checked: agent.enabled" id="inputEnabled" placeholder="Enabled" style="width: 300px;" />
					</div>
				</div>							
			</div>
			<div class="modal-footer">
				<a href="#" data-dismiss="modal" class="btn">Cancel</a>
				<a href="#" class="btn btn-primary" data-bind="click: addAgent" >Create</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var agentViewModel = new AgentViewModel();
	ko.applyBindings(agentViewModel, $('#addAgentPopup')[0]);
</script>