<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<%@include file="header.jsp" %>    
	<body>
		<%@include file="menu.jsp" %>


		<div style="margin-top: 80px" />

		<div id="main" class="container marketing">
			<div class="row">		
				<%@include file="agents.jsp" %>				
			</div>	
			<%@include file="footer.jsp" %>	
		</div>	  

		<div class="modal fade" id="add">
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

		<script type="text/javascript" language="javascript" src="assets/js/org/lorislab/smonitor/model/Exception.js"></script>
		<script type="text/javascript" language="javascript" src="assets/js/org/lorislab/smonitor/model/ServerApplication.js"></script>
		<script type="text/javascript" language="javascript" src="assets/js/org/lorislab/smonitor/model/ServerInfo.js"></script>
		<script type="text/javascript" language="javascript" src="assets/js/org/lorislab/smonitor/model/Agent.js"></script>
		<script type="text/javascript" language="javascript" src="assets/js/org/lorislab/smonitor/wrapper/AgentWrapper.js"></script>
		<script type="text/javascript" language="javascript" src="assets/js/org/lorislab/smonitor/service/AgentService.js"></script>
		<script type="text/javascript" language="javascript" src="assets/js/org/lorislab/smonitor/view/AgentViewModel.js"></script>
		<script type="text/javascript" language="javascript" src="assets/js/org/lorislab/smonitor/view/AgentsViewModel.js"></script>
		
		<script type="text/javascript">
			var agentsViewModel = new AgentsViewModel();
			var agentViewModel = new AgentViewModel();
			ko.applyBindings(agentsViewModel, $('#main')[0]);
			ko.applyBindings(agentViewModel, $('#add')[0]);
		</script>

		<%@include file="scripts.jsp" %>
	</body>

</html>
