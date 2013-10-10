
REST = {
	CONTENT_TYPE: 'application/json',
	RESPONS_TYPE: 'json',
	POST: 'POST',
	GET: 'GET',
	DELETE: 'DELETE',
	PUT: 'PUT',
	SEPARATOR: '/',
	url: function(url, params) {
		var tmp = url;
		for (var i = 0; i < params.length; ++i) {
			tmp = tmp + REST.SEPARATOR + params[i];
		}
		return tmp;
	},
	exception: function(xhr, ajaxOptions, thrownError) {
		result = new Exception();
		if (xhr.getResponseHeader('Content-Type') == REST.CONTENT_TYPE) {
			var tmp = jQuery.parseJSON(xhr.responseText);			
			result.fromResponse(tmp);
		} else {
			result.message = "Error";
			result.details = thrownError;
		}
		return result;
	},
	post: function(url, data, call_ok, call_error) {
		var tmp = data;
		if (data.isObservable()) {
			tmp = ko.toJSON(data);
		}
		$.ajax(url, {
			data: tmp,
			type: REST.POST,
			dataType: REST.RESPONS_TYPE,
			contentType: REST.CONTENT_TYPE,
			success: function(data, textStatus, jqXHR) {
				call_ok(data);
			},
			error: function(xhr, ajaxOptions, thrownError) {
				call_error(REST.exception(xhr, ajaxOptions, thrownError), data);
			}
		});
	},
	get: function(url, call_ok, call_error) {
		$.ajax(url, {
			type: REST.GET,
			dataType: REST.RESPONS_TYPE,
			success: function(data, textStatus, jqXHR) {
				call_ok(data);
			},
			error: function(xhr, ajaxOptions, thrownError) {
				call_error(REST.exception(xhr, ajaxOptions, thrownError));
			}
		});
	},
	remove: function(url, call_ok, call_error) {
		$.ajax(url, {
			type: REST.DELETE
		})
			.fail(function( jqXHR, textStatus, errorThrown ) {
				call_error(REST.exception(jqXHR, textStatus, errorThrown));
			})
			.complete(function(data, textStatus, jqXHR){
				call_ok(data);
			});
	},
	put: function(url, data, call_ok, call_error) {
		var tmp = data;
		if (data != null && data.isObservable()) {
			tmp = ko.toJSON(data);
		}
		$.ajax(url, {
			data: tmp,
			type: REST.PUT,
			dataType: REST.RESPONS_TYPE,
			contentType: REST.CONTENT_TYPE,
			success: function(data, textStatus, jqXHR) {
				call_ok(data);
			},
			error: function(xhr, ajaxOptions, thrownError) {
				call_error(REST.exception(xhr, ajaxOptions, thrownError), data);
			}
		});
	}
};
