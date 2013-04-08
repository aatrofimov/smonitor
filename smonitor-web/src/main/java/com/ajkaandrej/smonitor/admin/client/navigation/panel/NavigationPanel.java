/*
 * Copyright 2013 Andrej Petras <andrej@ajka-andrej.com>.
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
package com.ajkaandrej.smonitor.admin.client.navigation.panel;

import com.ajkaandrej.smonitor.agent.rs.exception.ServiceException;
import com.ajkaandrej.smonitor.agent.rs.model.Server;
import com.ajkaandrej.smonitor.agent.rs.service.ServerService;
import com.ajkaandrej.smonitor.rs.model.Connection;
import com.ajkaandrej.smonitor.rs.service.MonitorService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseCallback;
import org.jboss.errai.ioc.client.api.AfterInitialization;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class NavigationPanel extends Composite {

    interface MyUiBinder extends UiBinder<Widget, NavigationPanel> { }
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    
    @UiField
    TabLayoutPanel tabPanel;
    
    @UiField
    ServerNagivationPanel serverPanel;
    @UiField
    ApplicationNagivationPanel applicationPanel;
        
    @UiField
    PushButton refreshButton;
    @UiField
    PushButton configButton;
    
    @Inject
    private Caller<MonitorService> monitorService;
    @Inject
    private Caller<ServerService> serverService;
    
    final RemoteCallback<List<Connection>> connectionCallback = new RemoteCallback<List<Connection>>() {
        @Override
        public void callback(List<Connection> connections) {
            if (connections != null) {
                for (Connection con : connections) {
                    loadConnection(con);
                }
            }
        }
    };
    
    final RemoteCallback<Server> serverCallback = new RemoteCallback<Server>() {
        @Override
        public void callback(Server server) {
            addServer(server);
        }
    };
    
    final ResponseCallback configCallback = new ResponseCallback() {
        @Override
        public void callback(Response response) {
        }
    };
    
    public NavigationPanel() {               
        initWidget(uiBinder.createAndBindUi(this));   
        
        refreshButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                loadServers();
            }
        });
        
        configButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                relaodConfiguration();
            }
        });
 
        reset();
    }
           
    public ApplicationNagivationPanel getApplicationPanel() {
        return applicationPanel;
    }

    public ServerNagivationPanel getServerPanel() {
        return serverPanel;
    }

    /**
     * Resets the navigation panel to startup status.
     */
    public final void reset() {
        // reset tab server
        serverPanel.reset();
        // reset tab application
        applicationPanel.reset();
    }

    public void addServer(Server server) {
        serverPanel.addServer(server);
        applicationPanel.addServer(server);
    }
  
    @AfterInitialization
    public void loadServers() {
        try {
            reset();
            monitorService.call(connectionCallback).getServerConnections();
        } catch (ServiceException ex) {
            Window.alert("Error: " + ex.getMessage());
        }
    }
    
    public void loadConnection(Connection connection) {
        try {
            serverService.call(serverCallback).getServer(connection.getUrl());
        } catch (ServiceException ex) {
            Window.alert("Error: " + ex.getMessage());
        }
    } 
    
    public void relaodConfiguration() {
        try {
            monitorService.call(configCallback).realoadConfiguration();
        } catch (ServiceException ex) {
            Window.alert("Error: " + ex.getMessage());
        }
    }    
}
