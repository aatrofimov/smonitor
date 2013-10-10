function AgentsViewModel() {
	var _this = this;
	this.agents = ko.observableArray();

	function _sortName(left, right) {
		return left.agent.name() == right.agent.name() ? 0 : (left.agent.name() < right.agent.name() ? -1 : 1);
	};

	function _sortServer(left, right) {
		return left.agent.server() == right.agent.server() ? 0 : (left.agent.server() < right.agent.server() ? -1 : 1);
	};

	function _sortStatus(left, right) {
		return left.agent.enabled() == right.agent.enabled() ? 0 : (left.agent.enabled() < right.agent.enabled() ? -1 : 1);
	}
	;
	function _sortError(left, right) {
		return left.agent.enabled() == right.agent.enabled() ? 0 : (left.agent.enabled() < right.agent.enabled() ? -1 : 1);
	}
	;

	function _sort(reverse, sortFunction) {
		if (reverse) {
			_this.agents.reverse(sortFunction);
		} else {
			_this.agents.sort(sortFunction);
		}
	};

	this.sortReset = function() {
		
	}
	
	this.sort = function(name, data, event) {
		
		var header = $(event.target);
		var table = header.parents('table.sortable');
		var span = header.find('span');
		
		if (span.hasClass('sort')) {	
			span.toggleClass('glyphicon-sort-by-alphabet-alt');		
			span.toggleClass('glyphicon-sort-by-alphabet');

		} else {
			var tmp = table.find('span.sort');
			tmp.removeClass('sort glyphicon-sort-by-alphabet glyphicon-sort-by-alphabet-alt');	
			tmp.addClass('glyphicon-sort');

			span.removeClass('glyphicon-sort');
			span.addClass('sort glyphicon-sort-by-alphabet');		
		}	
		
		var sort = span.hasClass('glyphicon-sort-by-alphabet-alt');
		if (name == "name") {
			_sort(sort, _sortName);
		} else if (name == "server") {
			_sort(sort, _sortServer);
		} else if (name == "status") {
			_sort(sort, _sortStatus);
		} else if (name == "error") {
			_sort(sort, _sortError);
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
					agentViewModel.close();					
				});
	};

	this.create = function() {
		AgentService.create(function(result) {
			agentViewModel.open(result);			
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