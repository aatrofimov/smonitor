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

import com.ajkaandrej.smonitor.admin.client.view.ApplicationPanel;
import com.ajkaandrej.smonitor.admin.client.view.ServerTree;
import com.ajkaandrej.smonitor.admin.client.view.ServerTree.ServerTreeImages;
import com.ajkaandrej.smonitor.agent.rs.exception.ServiceException;
import com.ajkaandrej.smonitor.agent.rs.model.Server;
import com.ajkaandrej.smonitor.agent.rs.service.ServerService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.EntryPoint;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@EntryPoint
public class Admin {

    @Inject
    private Caller<ServerService> serverService;

    private ServerTree serverTree;
    
    final RemoteCallback<Server> serverCallback = new RemoteCallback<Server>() {
        @Override
        public void callback(Server server) {
            serverTree.loadServer(server);
        }        
    };
    
    @PostConstruct
    public void init() {
       
        RestClient.setJacksonMarshallingActive(true);
        
        ServerTreeImages images = GWT.create(ServerTreeImages.class);        
        serverTree = new ServerTree(images);
        
        ScrollPanel staticTreeWrapper = new ScrollPanel(serverTree);
        staticTreeWrapper.ensureDebugId("cwTree-serverTree-Wrapper");
        
        staticTreeWrapper.setSize("100%", "100%");
    
  
        Button refreshButton = new Button("Refresh");
        refreshButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                loadServer();
            }
        });
                        
        VerticalPanel vPanel = new VerticalPanel();
        vPanel.add(refreshButton);
        vPanel.add(staticTreeWrapper);

        ApplicationPanel appPanel = new ApplicationPanel();
  
        SplitLayoutPanel splitPanel = new SplitLayoutPanel(5);
        splitPanel.setWidth("100%");
        splitPanel.setHeight("100%");
        splitPanel.getElement().getStyle().setProperty("border", "0px solid #e7e7e7");
        splitPanel.addWest(vPanel, 200);
        splitPanel.add(appPanel);
        splitPanel.setWidgetMinSize(vPanel, 200);
        
        RootPanel.get().add(splitPanel);
    }

    private void loadServer() {
        try {
            serverService.call(serverCallback).getServer();
        } catch (ServiceException ex) {
            Window.alert("Error: " + ex.getMessage());
        }
    }
}
