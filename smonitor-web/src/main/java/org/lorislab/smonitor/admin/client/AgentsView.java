/*
 * Copyright 2013 lorislab.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.smonitor.admin.client;

import org.lorislab.smonitor.admin.client.panel.AgentDialogBox;
import org.lorislab.smonitor.gwt.uc.dialogbox.EntityDialogBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import java.util.List;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.MarshallingWrapper;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseException;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.enterprise.client.jaxrs.api.RestErrorCallback;
import org.jboss.errai.marshalling.client.Marshalling;
import org.jboss.errai.marshalling.client.MarshallingSessionProviderFactory;
import org.jboss.errai.marshalling.client.api.Marshaller;
import org.jboss.errai.marshalling.client.api.MarshallerFactory;
import org.jboss.errai.marshalling.client.api.MarshallerFramework;
import org.jboss.errai.marshalling.client.api.MarshallingSession;
import org.jboss.errai.marshalling.client.api.ParserFactory;
import org.jboss.errai.marshalling.client.api.json.EJObject;
import org.jboss.errai.marshalling.client.api.json.EJValue;
import org.jboss.errai.marshalling.client.api.json.impl.gwt.GWTJSON;
import org.lorislab.smonitor.gwt.uc.dialogbox.DialogEventHandler;
import org.lorislab.smonitor.admin.client.panel.AgentGridPanel;
import org.lorislab.smonitor.admin.client.model.AgentWrapper;
import org.lorislab.smonitor.rs.admin.model.Agent;
import org.lorislab.smonitor.rs.admin.service.AgentRestService;
import org.lorislab.smonitor.rs.exception.RestServiceException;
import org.lorislab.smonitor.rs.model.ServerInfo;
import org.lorislab.smonitor.rs.service.ServerService;

/**
 *
 * @author Andrej Petras
 */
public class AgentsView extends Composite {

    @UiField
    AgentGridPanel agentPanel;
    @UiField
    Button btnAgentRefresh;
    @UiField
    Button btnAgentAdd;
    @UiField
    Button btnAgentEdit;
    @UiField
    Button btnAgentDelete;
    private AgentDialogBox dialogBox = new AgentDialogBox();

    public AgentsView() {
        initWidget(uiBinder.createAndBindUi(this));

        dialogBox.setCreateHandler(new DialogEventHandler<Agent>() {
            @Override
            public void event(Agent value) {
                update(value);
            }
        });

        dialogBox.setUpdateHandler(new DialogEventHandler<Agent>() {
            @Override
            public void event(Agent value) {
                update(value);
            }
        });

        agentPanel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                AgentWrapper w = agentPanel.getSelectedObject();
                btnAgentDelete.setEnabled(w != null);
                btnAgentEdit.setEnabled(w != null);
            }
        });

        btnAgentRefresh.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                refresh();
            }
        });

        btnAgentAdd.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                create();
            }
        });

        btnAgentEdit.setEnabled(false);
        btnAgentEdit.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                AgentWrapper w = agentPanel.getSelectedObject();
                if (w != null) {
                    openDialog(w.agent, EntityDialogBox.Mode.UPDATE);
                }
            }
        });

        btnAgentDelete.setEnabled(false);
        btnAgentDelete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                delete();
            }
        });
    }

    public void refresh() {
        try {
            RestClient.create(AgentRestService.class, agents).get();
        } catch (Exception ex) {
            Window.alert("Error: " + ex.getMessage());
        }
    }
    final RemoteCallback<List<Agent>> agents = new RemoteCallback<List<Agent>>() {
        @Override
        public void callback(List<Agent> value) {
            agentPanel.set(value);
            for (Agent agent : value) {
                RestClient.create(ServerService.class, serverInfo, serverInfoError).getServer(agent.getGuid());
            }
        }
    };

    final RestErrorCallback serverInfoError = new RestErrorCallback() {
        @Override
        public boolean error(Request message, Throwable throwable) {
            try {
                throw throwable;
            } catch (ResponseException e) {
                Response response = e.getResponse();
                RestServiceException exception = MarshallingWrapper.fromJSON(response.getText(), RestServiceException.class);  
                agentPanel.update(exception.getRef(), exception.getMessage());
            } catch (Throwable t) {
                Window.alert(t.getMessage());
            }            
            return false;
        }
    };
   
    final RemoteCallback<ServerInfo> serverInfo = new RemoteCallback<ServerInfo>() {
        @Override
        public void callback(ServerInfo value) {
            agentPanel.update(value);
        }
    };

    private void delete() {
        try {
            AgentWrapper w = agentPanel.getSelectedObject();
            if (w != null) {
                RestClient.create(AgentRestService.class, agentDelete).delete(w.agent.getGuid());
            }
        } catch (Exception ex) {
            Window.alert("Error: " + ex.getMessage());
        }
    }
    final RemoteCallback<String> agentDelete = new RemoteCallback<String>() {
        @Override
        public void callback(String value) {
            agentPanel.remove(value);
        }
    };

    private void create() {
        try {
            RestClient.create(AgentRestService.class, agentCreate).create();
        } catch (Exception ex) {
            Window.alert("Error: " + ex.getMessage());
        }
    }
    final RemoteCallback<Agent> agentCreate = new RemoteCallback<Agent>() {
        @Override
        public void callback(Agent value) {
            openDialog(value, EntityDialogBox.Mode.CREATE);
        }
    };

    private void update(Agent agent) {
        try {
            RestClient.create(AgentRestService.class, agentUpdate).update(agent.getGuid(), agent);
        } catch (Exception ex) {
            Window.alert("Error: " + ex.getMessage());
        }
    }
    final RemoteCallback<Agent> agentUpdate = new RemoteCallback<Agent>() {
        @Override
        public void callback(Agent value) {
            agentPanel.update(value);
            dialogBox.close();
        }
    };

    private void openDialog(Agent agent, EntityDialogBox.Mode mode) {
        dialogBox.center();
        dialogBox.open(agent, mode);
    }

    interface MyUiBinder extends UiBinder<Widget, AgentsView> {
    }
    private static AgentsView.MyUiBinder uiBinder = GWT.create(AgentsView.MyUiBinder.class);
}