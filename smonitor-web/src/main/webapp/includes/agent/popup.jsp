 

<div class="modal fade" id="addAgentPopup">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Create agent</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label class="label label-primary" for="name">Agent name</label>
					<input class="form-control" style="margin-top: 7px;" type="text" name="name" data-bind="value: agent.name">
					<br/>
					<label class="label label-primary" for="server">Server</label>
					<input class="form-control" style="margin-top: 7px;" type="server" name="server" data-bind="value: agent.server">
					<br/>
					
					<label class="label label-primary" >Enabled</label>
					<div class="checkbox-basic">
						<input id="enabledId" class="form-control" style="margin-top: 7px;" type="checkbox" name="enabled" data-bind="checked: agent.enabled" >
						<label for="enabledId"></label>
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