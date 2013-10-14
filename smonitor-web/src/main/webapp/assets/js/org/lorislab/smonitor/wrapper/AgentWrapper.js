/**
 * The agent wrapper.
 * 
 * @constructor
 * @returns {AgentWrapper} the new created agent.
 * @param {org.lorislab.smonitor.rs.model.Agent} data the server side object server info.
 * 
 * @see org.lorislab.smonitor.rs.model.Agent
 * 
 * @author Andrej Petras
 */
function AgentWrapper(data) {
	this.agent = new Agent();
	this.agent.set(data);
	this.serverInfo = null;
	this.request = ko.observable(false);
	this.connected = ko.observable(false);
	this.message = ko.observable(null);
}

AgentWrapper.prototype.update = function(data) {
	this.agent.set(data);
};

AgentWrapper.prototype.statusOk = function(data) {
	if (this.serverInfo != null) {
		this.serverInfo.updateApp(data);
	} else {
		this.serverInfo = new ServerInfo(data);
	}
	this.request(false);
	this.message(null);
	this.connected(true);
};

AgentWrapper.prototype.statusError = function(data) {
	this.serverInfo = null;
	this.request(false);
	if (data != null) {
		this.message(data.message);
	} else {
		this.message(null);
	}
	this.connected(false);
};

AgentWrapper.prototype.statusStart = function() {
	this.serverInfo = null;
	this.request(true);
	this.message(null);
	this.connected(false);
};