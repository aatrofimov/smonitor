function AgentsViewModel() {
	var _this = this;
	this.agents = ko.observableArray();
	this.searchText = ko.observable();
	this.table = ko.observable();
	this.tableBody = ko.observable();
	this.tableHeader = ko.observable();
	this.paginator = ko.observable();
	
	var _numPerPage = 2;
	
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

	this.searchClear = function() {
		_this.searchText(null);
		var tbody = $(_this.tableBody());
		tbody.find('tr').show();
		_updateTable();
	};
	
	this.search = function(data, event) {
		var q = _this.searchText();		
		var tbody = $(_this.tableBody());
		if (q === '') {
			_this.searchClear();
		} else {
			tbody.find('tr').hide();
			tbody.find('td').find('p').filter(':filterTableFind("' + q.replace(/(['"])/g, '\\$1') + '")').closest('tr').show(); 	
		}		
	};
	
	this.sortClear = function() {
		var thead = $(_this.tableHeader());		
		thead.find('span').each(function() {
			$( this ).removeClass('sort glyphicon-sort-by-attributes glyphicon-sort-by-attributes-alt');
			$( this ).addClass('glyphicon-sort');
		});
	};
		
	this.sort = function(name, data, event) {
		
		var span = $(event.target).find('span');		
		if (span.hasClass('sort')) {	
			span.toggleClass('glyphicon-sort-by-attributes-alt');		
			span.toggleClass('glyphicon-sort-by-attributes');
		} else {
			var tmp = $(_this.table()).find('span.sort');
			tmp.removeClass('sort glyphicon-sort-by-attributes glyphicon-sort-by-attributes-alt');	
			tmp.addClass('glyphicon-sort');
			span.removeClass('glyphicon-sort');
			span.addClass('sort glyphicon-sort-by-attributes');		
		}	
		
		var sort = span.hasClass('glyphicon-sort-by-attributes-alt');
		if (name == "name") {
			_sort(sort, _sortName);
		} else if (name == "server") {
			_sort(sort, _sortServer);
		} else if (name == "status") {
			_sort(sort, _sortStatus);
		} else if (name == "error") {
			_sort(sort, _sortError);
		}
		
		_updateTable();
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

	this.update = function(agent) {
		AgentService.update(agent, function(result) {
			var tmp =  ko.utils.arrayFirst(_this.agents(), function(item) {
					return result.guid == item.agent.guid;
			});
			if (tmp) {
				tmp.update(result);
				_this.updateServerInfo(tmp);
				agentViewModel.close();	
			}
		});
	};
	
	this.create = function() {
		AgentService.create(function(result) {
			agentViewModel.create(result);			
		});
	};

	this.edit = function(wrapper) {
		agentViewModel.edit(wrapper.agent);	
	};
	
	this.remove = function(wrapper) {
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
		_this.searchClear();
		_this.sortClear();
		AgentService.all(function(allData) {
			
			// mapping
			var mappedTasks = $.map(allData, function(item) {
				return new AgentWrapper(item);
			});
			_this.agents(mappedTasks);
			
			// update paginator
			_updatePaginator();
			
			// get the server status
			mappedTasks.forEach(function(item) {
				_this.updateServerInfo(item);
			});
		});
	};	
	
	function _updatePaginator() {		
		var size = _this.agents().length;
		var pages = size / _numPerPage;
		if (size % _numPerPage > 0) {
			pages = pages + 1;
		}
		var options = {
			currentPage: 1,
			totalPages: pages
		};
		$(_this.paginator()).bootstrapPaginator(options);		
		_updateTableToPage(1);
	};
	
	function _updateTable() {
		var pages = $(_this.paginator()).bootstrapPaginator("getPages");
		_updateTableToPage(pages.current);
	};
	
	function _updateTableToPage(currentPage) {
		var tbody = $(_this.tableBody());
		tbody.find('tr').hide().slice((currentPage-1) * _numPerPage, currentPage * _numPerPage).show();				
	};
	
	function _clickPaginator(e, originalEvent, type, page) {
		_updateTableToPage(page);
	};
	
	this.init = function() {	
		// initialize paginator
		var options = {
            currentPage: 1,
            totalPages: 1,
			onPageClicked: _clickPaginator,
			useBootstrapTooltip:true,
			bootstrapTooltipOptions: {
                placement: 'bottom'
            },
			shouldShowPage:function(type, page, current){
                return true;
            },
			itemContainerClass: function (type, page, current) {
                switch(type)
                {
                    case "first":
                    case "last":
					case "prev":
					case "next":
                        return (page === current) ? "disabled" : "";;
                    default:
                        return (page === current) ? "active" : "";;
                }		
			}					
		};
		$(_this.paginator()).bootstrapPaginator(options);			
		
		// load agents
		_this.getAll();
	};
	
	
}