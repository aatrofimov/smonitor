
ServerInfoService = {

	_url: "/smonitor/rs/server",

	get: function(guid, call_ok, call_error) {
		REST.get(REST.url(ServerInfoService._url, [ guid ]), call_ok, call_error);
	}
};