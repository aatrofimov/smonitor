function AgentViewModel() {
	this.agent = new Agent();
}

AgentViewModel.prototype.open = function(data) {
	this.agent.set(data);
	$('#addAgentPopup').modal({show: true});
};

AgentViewModel.prototype.close = function() {
	$('#addAgentPopup').modal('hide');
};

AgentViewModel.prototype.addAgent = function() {
	agentsViewModel.add(this.agent);
};
