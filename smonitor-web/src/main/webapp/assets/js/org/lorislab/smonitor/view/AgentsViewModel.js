function AgentsViewModel() {
	var _this = this;
	this.agents = ko.observableArray();

	function _sortName(left, right) {
		return left.agent.name() == right.agent.name() ? 0 : (left.agent.name() < right.agent.name() ? -1 : 1);
	}
	;
	function _sortServer(left, right) {
		return left.agent.server() == right.agent.server() ? 0 : (left.agent.server() < right.agent.server() ? -1 : 1);
	}
	;
	function _sortStatus(left, right) {
		return left.agent.enabled() == right.agent.enabled() ? 0 : (left.agent.enabled() < right.agent.enabled() ? -1 : 1);
	}
	;
	function _sortError(left, right) {
		return left.agent.enabled() == right.agent.enabled() ? 0 : (left.agent.enabled() < right.agent.enabled() ? -1 : 1);
	}
	;

	_sort = function(reverse, sortFunction) {
		if (reverse) {
			_this.agents.reverse(sortFunction);
		} else {
			_this.agents.sort(sortFunction);
		}
	};

	this.sort = function(data, sort) {
		if (data == "name") {
			_sort(sort, sortName);
		} else if (data == "server") {
			_sort(sort, sortServer);
		} else if (data == "status") {
			_sort(sort, sortStatus);
		} else if (data == "error") {
			_sort(sort, sortError);
		}
	};

	this.refresh = function(wrapper) {
		_this.updateServerInfo(wrapper);
	};

	this.add = function(agent) {

		AgentService.add(agent,
			function(result) {
				var tmp = new AgentWrapper(result);
				_this.agents.push(tmp);
				_this.updateServerInfo(tmp);
				$('#add').modal('hide');
			});
	};

	this.create = function() {
		AgentService.create(function(result) {
				agentViewModel.setAgent(result);
				$('#add').modal({show: true});
			});
	};

	this.delete = function(wrapper) {
		bootbox.confirm("Do you really want to delete selected agent " + wrapper.agent.name() + " ?", function(result) {
			if (result) {
				AgentService.remove(wrapper.agent, function(result) {
				_this.agents.remove(wrapper);
			});
			}
		});
	};

	this.updateServerInfo = function(wrapper) {
		if (wrapper.agent.enabled()) {
			wrapper.statusStart();
			ServerInfoService.get(wrapper.agent.guid,
					function(serverInfo) {
						wrapper.statusOk(serverInfo);
					},
					function(exception) {
						wrapper.statusError(exception);
					});
		} else {
			wrapper.statusError(null);
		}
	};

	this.getAll = function() {
		AgentService.all(function(allData) {
			var mappedTasks = $.map(allData, function(item) {
				return new AgentWrapper(item);
			});
			_this.agents(mappedTasks);
			mappedTasks.forEach(function(item) {
				_this.updateServerInfo(item);
			});
		});
	};

	this.getAll();
}