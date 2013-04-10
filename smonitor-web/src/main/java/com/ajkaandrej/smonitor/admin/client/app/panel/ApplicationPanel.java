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
package com.ajkaandrej.smonitor.admin.client.app.panel;

import com.ajkaandrej.gwt.uc.table.handler.EntityTablePanelSelectionHandler;
import com.ajkaandrej.smonitor.admin.client.app.model.ApplicationDetailsModel;
import com.ajkaandrej.smonitor.admin.client.app.model.SessionTableModel;
import com.ajkaandrej.smonitor.admin.client.factory.ObjectFactory;
import com.ajkaandrej.smonitor.admin.client.navigation.model.AppInstanceTreeModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.ApplicationTreeModel;
import com.ajkaandrej.smonitor.agent.rs.exception.ServiceException;
import com.ajkaandrej.smonitor.agent.rs.model.ApplicationDetails;
import com.ajkaandrej.smonitor.agent.rs.model.SessionDetails;
import com.ajkaandrej.smonitor.agent.rs.service.ApplicationService;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationPanel extends Composite {

    interface MyUiBinder extends UiBinder<Widget, ApplicationPanel> { }
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    
    final RemoteCallback<ApplicationDetails> applicationDetailsCallback = new RemoteCallback<ApplicationDetails>() {
        @Override
        public void callback(ApplicationDetails details) {
            addApplication(details);
        }
    };

    
    @UiField
    SessionPanel sessionPanel;
    @UiField
    ApplicationDetailsPanel applicationDetails;
    @UiField
    TabLayoutPanel tabPanel;

    public ApplicationPanel() {
        initWidget(uiBinder.createAndBindUi(this)); 

        tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                if (event.getSelectedItem() == 1) {
                    sessionPanel.onSelectTab();
                }
            }
        });

        reset();
    }

    public final void reset() {
        applicationDetails.reset();
        sessionPanel.reset();
    }

    public void addApplication(ApplicationDetails application) {
        ApplicationDetailsModel app = ObjectFactory.create(application);
        applicationDetails.add(app);
        sessionPanel.load(app, ObjectFactory.createSessions(application));
    }

    public void loadApplication(ApplicationTreeModel model) {
       reset();
       sessionPanel.reset();
        if (model != null) {
            for (AppInstanceTreeModel inst : model.instances) {
                try {
                    RestClient.create(ApplicationService.class, applicationDetailsCallback).getApplication(inst.host, model.id, inst.remote);
                } catch (ServiceException ex) {
                    Window.alert("Error: " + ex.getMessage());
                }
            }
        }
    }
}
