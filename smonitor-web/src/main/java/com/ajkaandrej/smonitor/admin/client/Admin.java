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
package com.ajkaandrej.smonitor.admin.client;

import com.ajkaandrej.smonitor.admin.client.navigation.model.AppInstanceTreeModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.ApplicationTreeModel;
import com.ajkaandrej.smonitor.admin.client.app.panel.ApplicationPanel;
import com.ajkaandrej.smonitor.admin.client.navigation.panel.NavigationPanel;
import com.ajkaandrej.smonitor.admin.client.handler.SelectionHandler;
import com.ajkaandrej.smonitor.agent.rs.exception.ServiceException;
import com.ajkaandrej.smonitor.agent.rs.model.ApplicationDetails;
import com.ajkaandrej.smonitor.agent.rs.model.Server;
import com.ajkaandrej.smonitor.agent.rs.service.ApplicationService;
import com.ajkaandrej.smonitor.agent.rs.service.ServerService;
import com.ajkaandrej.smonitor.rs.exception.MonitorServiceException;
import com.ajkaandrej.smonitor.rs.model.Connection;
import com.ajkaandrej.smonitor.rs.service.MonitorService;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.EntryPoint;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@EntryPoint
public class Admin {

    @Inject
    private Caller<ServerService> serverService;
    @Inject
    private Caller<ApplicationService> applicationService;
    @Inject
    private Caller<MonitorService> monitorService;
    private NavigationPanel navigationPanel;
    private ApplicationPanel appPanel;
    final RemoteCallback<Server> serverCallback = new RemoteCallback<Server>() {
        @Override
        public void callback(Server server) {
            navigationPanel.addServer(server);
        }
    };
    final RemoteCallback<ApplicationDetails> applicationDetailsCallback = new RemoteCallback<ApplicationDetails>() {
        @Override
        public void callback(ApplicationDetails details) {
            appPanel.addApplication(details);
        }
    };
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

    @PostConstruct
    public void init() {

        RestClient.setJacksonMarshallingActive(true);

        appPanel = new ApplicationPanel();
        appPanel.reset();

        navigationPanel = new NavigationPanel();
        navigationPanel.getApplicationPanel().setAppHandler(new SelectionHandler<ApplicationTreeModel>() {
            @Override
            public void selectionChanged(ApplicationTreeModel item) {
                loadApplicationDetails(item);
            }
        });

//        navigationPanel.getApplicationPanel().setAppInstanceHandler(new SelectionHandler<AppInstanceTreeModel>() {
//            @Override
//            public void selectionChanged(AppInstanceTreeModel item) {
//
//            }
//        });
        
        SplitLayoutPanel splitPanel = new SplitLayoutPanel(5);
        splitPanel.setWidth("100%");
        splitPanel.setHeight("100%");
        splitPanel.getElement().getStyle().setProperty("border", "0px solid #e7e7e7");
        splitPanel.addWest(navigationPanel, 200);
        splitPanel.add(appPanel);
        splitPanel.setWidgetMinSize(navigationPanel, 200);

        RootPanel.get().add(splitPanel);
    }

    private void loadApplicationDetails(ApplicationTreeModel model) {
        appPanel.reset();
        for (AppInstanceTreeModel inst : model.instances) {
            try {
                applicationService.call(applicationDetailsCallback).getApplication(inst.host, model.id, inst.remote);
            } catch (ServiceException ex) {
                Window.alert("Error: " + ex.getMessage());
            }
        }
    }

    @AfterInitialization
    private void loadServer() {
        try {
            navigationPanel.reset();
            monitorService.call(connectionCallback).getServerConnections();
        } catch (MonitorServiceException ex) {
            Window.alert("Error: " + ex.getMessage());
        }
    }

    private void loadConnection(Connection connection) {
        try {
            serverService.call(serverCallback).getServer(connection.getUrl());
        } catch (ServiceException ex) {
            Window.alert("Error: " + ex.getMessage());
        }
    }
}
