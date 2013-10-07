/**
 * The exception model.
 * 
 * @constructor
 * @param {string} data server response data.
 * @returns {Exception} the new created exception model.
 * 
 * @author Andrej Petras
 */
function Exception(data) {	
	this.ref;
	this.message;
	this.details;
	this.params;
	
	this.fromResponse = function(data) {
		this.ref = data.ref;
		this.message = data.message;
		this.details = data.details;
		this.params = data.params;		
	}
}