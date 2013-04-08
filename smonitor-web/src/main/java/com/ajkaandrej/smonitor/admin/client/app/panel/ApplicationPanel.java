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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationPanel extends Composite {

    @Inject
    private Caller<ApplicationService> applicationService;
    final RemoteCallback<ApplicationDetails> applicationDetailsCallback = new RemoteCallback<ApplicationDetails>() {
        @Override
        public void callback(ApplicationDetails details) {
            addApplication(details);
        }
    };
    final RemoteCallback<SessionDetails> sessionDetailsCallback = new RemoteCallback<SessionDetails>() {
        @Override
        public void callback(SessionDetails details) {
            sessionPanel.load(details);
        }
    };
    private SessionPanel sessionPanel;
    private ApplicationDetailsPanel applicationDetails;
    private TabLayoutPanel tabPanel;

    public ApplicationPanel() {

        sessionPanel = new SessionPanel();
        applicationDetails = new ApplicationDetailsPanel();

        tabPanel = new TabLayoutPanel(2.5, Unit.EM);
        tabPanel.add(applicationDetails, "Details");


        tabPanel.add(sessionPanel, "Sessions");
        tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                if (event.getSelectedItem() == 1) {
                    sessionPanel.getSessionTable().onResize();
                }
            }
        });

        sessionPanel.getSessionTable().setSelectionHandler(new EntityTablePanelSelectionHandler<ApplicationDetailsModel, SessionTableModel>() {
            @Override
            public void selectionChanged(ApplicationDetailsModel model, SessionTableModel item) {
                sessionPanel.reset();
                if (model != null) {
                    try {
                        applicationService.call(sessionDetailsCallback).getSession(model.host, model.id, item.id, model.remote);
                    } catch (ServiceException ex) {
                        Window.alert("Error: " + ex.getMessage());
                    }
                }
            }
        });

        tabPanel.selectTab(0);
        reset();

        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                sessionPanel.getSessionTable().onResize();
            }
        });

        initWidget(tabPanel);
    }

    public final void reset() {
        applicationDetails.reset();
        sessionPanel.reset();
    }

    public ApplicationDetailsPanel getApplicationDetails() {
        return applicationDetails;
    }

    public SessionPanel getSessionPanel() {
        return sessionPanel;
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
                    applicationService.call(applicationDetailsCallback).getApplication(inst.host, model.id, inst.remote);
                } catch (ServiceException ex) {
                    Window.alert("Error: " + ex.getMessage());
                }
            }
        }
    }
}
