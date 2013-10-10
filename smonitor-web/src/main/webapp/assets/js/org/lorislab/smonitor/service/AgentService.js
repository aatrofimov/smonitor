
AgentService = {

	_url: "/smonitor/rs/agent",
	
	create: function(call_ok, call_error) {
		REST.put(AgentService._url, null, call_ok, call_error);
	},

	add: function(agent, call_ok, call_error) {
		REST.post(REST.url(AgentService._url, [ agent.guid ]), agent, call_ok, call_error);
	},

	all: function(call_ok, call_error) {
		REST.get(AgentService._url, call_ok, call_error);
	},

	remove: function(agent, call_ok, call_error) {
		REST.remove(REST.url(AgentService._url, [ agent.guid ]), call_ok, call_error);
	}
};
