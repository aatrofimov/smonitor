/**
 * The server info model.
 * 
 * @constructor
 * @returns {ServerApplication} the new created server info.
 * @param {org.lorislab.smonitor.rs.model.ServerInfo} data the server side object server info.
 * 
 * @see org.lorislab.smonitor.rs.model.ServerInfo
 * 
 * @author Andrej Petras
 */
function ServerInfo(data) {
	this.guid = data.guid;
	this.id = data.id;
	this.name = data.name;
	this.applications = ko.observableArray();
	this.updateApp(data);
}

/**
 * Updates the list of applications from server.
 * @param {org.lorislab.smonitor.rs.model.ServerInfo} data
 */
ServerInfo.prototype.updateApp = function(data) {
	var tmp = $.map(data.applications, function(item) {
		return new ServerApplication(item);
	});
	this.applications(tmp);
};