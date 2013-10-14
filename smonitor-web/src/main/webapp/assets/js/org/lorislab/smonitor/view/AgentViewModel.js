function AgentViewModel() {
	var _this = this;
	this.agent = new Agent();
	this.btnCreate = ko.observable();
	this.btnUpdate = ko.observable();
	this.panel = ko.observable();
	this.title = ko.observable();
	
	this.create = function(data) {
		_this.title('Create agent');
		_this.agent.set(data);
		$(_this.btnCreate()).show();
		$(_this.btnUpdate()).hide();
		$(_this.panel()).modal({show: true});
	};

	this.edit = function(data) {
		_this.title('Edit agent');
		_this.agent.copy(data);
		$(_this.btnCreate()).hide();
		$(_this.btnUpdate()).show();
		$(_this.panel()).modal({show: true});
	};

	this.close = function() {
		$(_this.panel()).modal('hide');
	};

	this.updateAgent = function() {
		agentsViewModel.update(_this.agent);
	};

	this.createAgent = function() {
		agentsViewModel.add(_this.agent);
	};
}
;
