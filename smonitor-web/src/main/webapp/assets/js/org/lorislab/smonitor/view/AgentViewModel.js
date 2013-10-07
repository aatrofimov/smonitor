function AgentViewModel() {
	this.agent = new Agent();
}

AgentViewModel.prototype.setAgent = function(data) {
	this.agent.set(data);
};

AgentViewModel.prototype.addAgent = function() {
	agentsViewModel.add(this.agent);
};
