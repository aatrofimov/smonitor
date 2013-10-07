/**
 * The agent model.
 * 
 * @constructor
 * @returns {Agent} the new created agent.
 * @param {org.lorislab.smonitor.rs.model.Agent} data the server side object server info.
 * 
 * @see org.lorislab.smonitor.rs.model.Agent
 * 
 * @author Andrej Petras
 */
function Agent() {
	this.guid = null;
	this.name = ko.observable(null);
	this.server = ko.observable(null);
	this.enabled = ko.observable(null);

	this.set = function(data) {
		if (data) {
			this.guid = data.guid;
			this.name(data.name);
			this.server(data.server);
			this.enabled(data.enabled);
		}
	};
}

Agent.prototype.isObservable = function() {
	return true;
};

