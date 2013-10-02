<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<%@include file="header.jsp" %>    
	<body>
		<%@include file="menu.jsp" %>


		<div style="margin-top: 80px" />

		<div id="main" class="container marketing">
			<div class="row">		
				<%@include file="agents.jsp" %>				
			</div>	
			<%@include file="footer.jsp" %>	
		</div>	  

		<div class="modal fade" id="add">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Create agent</h4>
					</div>
					<div class="modal-body">

							<div class="control-group">
								<div class="controls">
									<input data-bind="value: agent.name" type="text" id="inputAgent" placeholder="Agent name" style="width: 150px;">
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<input data-bind="value: agent.server" type="text" id="inputServer" placeholder="Description" style="width: 300px;">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputEnabled">Enabled</label>
								<div class="controls">
									<input type="checkbox" data-bind="checked: agent.enabled" id="inputEnabled" placeholder="Enabled" style="width: 300px;" />
								</div>
							</div>							
					</div>
					<div class="modal-footer">
						<a href="#" data-dismiss="modal" class="btn">Cancel</a>
						<a href="#" class="btn btn-primary" data-bind="click: addAgent" >Create</a>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">

			function Exception(data) {
				var tmp = jQuery.parseJSON(data);
				this.ref = tmp.ref;
				this.message = tmp.message;
				this.details = tmp.details;
				this.params = tmp.params;
			}

			/** SERVER APPLICATION */
			function ServerApplication() {
				this.id = null;
				this.name = null;
				this.host = null;
			}

			/** SERVER INFO */
			function ServerInfo(data) {
				this.guid = data.guid;
				this.id = data.id;
				this.name = data.name;
				this.applications = ko.observableArray();
				this.updateApp(data);
			}

			ServerInfo.prototype.updateApp = function(data) {
				var tmp = $.map(data.applications, function(item) {
					return new ServerApplication(item);
				});
				this.applications(tmp);
			};

			/** AGENT */
			function Agent(data) {
				if (data != null) {
					this.guid = data.guid;
					this.name = ko.observable(data.name);
					this.server = ko.observable(data.server);
					this.enabled = ko.observable(data.enabled);
				} else {
					this.guid = null;
					this.name = ko.observable(null);
					this.server = ko.observable(null);
					this.enabled = ko.observable(false);
				}
			}

			Agent.prototype.clear = function() {
				this.guid = null;
				this.name(null);
				this.server(null);
				this.enabled(false);
			};

			Agent.prototype.update = function(data) {
				this.guid = data.guid;
				this.name(data.name);
				this.server(data.server);
				this.enabled(data.enabled);
			};

			/**
			 * The agent wrapper.
			 * 
			 * @param {JSON-Agent} data the agent object from server.
			 */
			function AgentWrapper(data) {
				this.agent = new Agent(data);
				this.serverInfo = null;
				this.request = ko.observable(false);
				this.connected = ko.observable(false);
				this.message = ko.observable(null);
			}

			AgentWrapper.prototype.statusOk = function(data) {
				if (this.serverInfo != null) {
					this.serverInfo.updateApp(data);
				} else {
					this.serverInfo = new ServerInfo(data);
				}
				this.request(false);
				this.message(null);
				this.connected(true);
			};

			AgentWrapper.prototype.statusError = function(data) {
				this.serverInfo = null;
				this.request(false);
				if (data != null) {
					this.message(data.message);
				} else {
					this.message(null);
				}
				this.connected(false);
			};

			AgentWrapper.prototype.statusStart = function() {
				this.serverInfo = null;
				this.request(true);
				this.message(null);
				this.connected(false);
			};

			/** AGENT VIEW MODEL */
			function AgentViewModel() {
				this.agent = new Agent();
			}

			AgentViewModel.prototype.addAgent = function() {
				agentsViewModel.add(this.agent);
			};

			AgentViewModel.prototype.update = function(data) {
				this.agent.update(data);
			};

			/** AGENTS VIEW MODEL */
			function AgentsViewModel() {
				var inst = this;
				this.agents = ko.observableArray();
				this.url = "/smonitor/rs/agent";

				function _sortName(left, right) {
					return left.agent.name() == right.agent.name() ? 0 : (left.agent.name() < right.agent.name() ? -1 : 1);
				};
				function _sortServer(left, right) {
					return left.agent.server() == right.agent.server() ? 0 : (left.agent.server() < right.agent.server() ? -1 : 1);
				};
				function _sortStatus(left, right) {
					return left.agent.enabled() == right.agent.enabled() ? 0 : (left.agent.enabled() < right.agent.enabled() ? -1 : 1);
				};
				function _sortError(left, right) {
					return left.agent.enabled() == right.agent.enabled() ? 0 : (left.agent.enabled() < right.agent.enabled() ? -1 : 1);
				};	
				
				_sort = function(reverse, sortFunction) {
					if (reverse) {
						inst.agents.reverse(sortFunction);
					} else {
						inst.agents.sort(sortFunction);
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
					inst.updateServerInfo(wrapper);
				};

				this.add = function(agent) {
					$.ajax(inst.url + "/" + agent.guid, {
						data: ko.toJSON(agent),
						type: "post", contentType: "application/json",
						success: function(result) {
							var tmp = new AgentWrapper(result);
							inst.agents.push(tmp);
							inst.updateServerInfo(tmp);
							inst.updateTable();
							$('#add').modal('hide');
						}
					});
				};

				this.create = function() {
					$.ajax(inst.url, {
						type: "PUT",
						success: function(result) {
							agentViewModel.update(result);
							$('#add').modal({show: true});
						}
					});
				};

				this.delete = function(wrapper) {
					bootbox.confirm("Do you really want to delete selected agent " + wrapper.agent.name() + " ?", function(result) {
					  if (result) {
						  _delete(wrapper);
					  }
					}) ;				
				};
				
				_delete = function(wrapper) {				
					$.ajax(inst.url + "/" + wrapper.agent.guid, {
						type: "DELETE",
						dataType: 'json',
						complete: function(result) {
//							inst.agents.remove(function(item) { return item.agent.guid == result }); 
							inst.agents.remove(wrapper);
							inst.updateTable();
						}
					});
				};

				this.updateServerInfo = function(wrapper) {
					if (wrapper.agent.enabled()) {
						wrapper.statusStart();
						$.ajax("/smonitor/rs/server/" + wrapper.agent.guid, {
							type: "GET",
							dataType: 'json',
							success: function(serverInfo) {
								wrapper.statusOk(serverInfo);
								inst.updateTable();
							},
							error: function(xhr, ajaxOptions, thrownError) {
								wrapper.statusError(new Exception(xhr.responseText));
								inst.updateTable();
							}
						});
					} else {
						wrapper.statusError(null);
						inst.updateTable();
					}
				};

				this.getAll = function() {
					$.getJSON(inst.url, function(allData) {
						var mappedTasks = $.map(allData, function(item) {
							return new AgentWrapper(item);
						});
						inst.agents(mappedTasks);

						inst.updateTable();
						mappedTasks.forEach(function(item) {
							inst.updateServerInfo(item);
						});
					});
				};

				this.updateTable = function() {

//					var oTable = $('#agentsTable').dataTable();
//					oTable.fnDraw();
//					$('#agentsTable').dataTable().fnAddData(null);
//					var resort = true;
//					$("#agentsTable").trigger("update", [resort]);
				};

				this.getAll();
			}

			var agentsViewModel = new AgentsViewModel();
			var agentViewModel = new AgentViewModel();
			ko.applyBindings(agentsViewModel, $('#main')[0]);
			ko.applyBindings(agentViewModel, $('#add')[0]);
		</script>

		<%@include file="scripts.jsp" %>
	</body>

</html>
