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
package org.lorislab.smonitor.admin.client.app.panel;

import org.lorislab.smonitor.gwt.uc.table.handler.EntityTablePanelSelectionHandler;
import org.lorislab.smonitor.admin.client.app.model.ApplicationDetailsModel;
import org.lorislab.smonitor.admin.client.app.model.AttributeTableModel;
import org.lorislab.smonitor.admin.client.app.model.SessionDetailsModel;
import org.lorislab.smonitor.admin.client.app.model.SessionTableModel;
import org.lorislab.smonitor.admin.client.factory.ObjectFactory;
import org.lorislab.smonitor.agent.rs.exception.ServiceException;
import org.lorislab.smonitor.agent.rs.model.SessionDetails;
import org.lorislab.smonitor.agent.rs.service.ApplicationService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.List;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionPanel extends Composite {
  
    interface MyUiBinder extends UiBinder<Widget, SessionPanel> { }
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    
    final RemoteCallback<SessionDetails> sessionDetailsCallback = new RemoteCallback<SessionDetails>() {
        @Override
        public void callback(SessionDetails details) {
            load(details);
        }
    };
    
    @UiField
    SessionDetailsPanel sessionDetailsPanel;
    
    @UiField
    AttributesPanel attributesPanel;
    
    @UiField
    SessionsTable sessionTable;
    
    @UiField(provided = true)
    SplitLayoutPanel splitPanel;
    
    @UiField
    DockLayoutPanel detailsVPanel;
    
    public SessionPanel() {
         
        splitPanel = new SplitLayoutPanel(5) {

            @Override
            public void onResize() {
                super.onResize();
                onSelectTab();
            }
            
        };
        
        initWidget(uiBinder.createAndBindUi(this));   

        splitPanel.setWidgetMinSize(detailsVPanel, 20);
        
        sessionTable.setSelectionHandler(new EntityTablePanelSelectionHandler<ApplicationDetailsModel, SessionTableModel>() {
            @Override
            public void selectionChanged(ApplicationDetailsModel model, SessionTableModel item) {
                reset();
                if (model != null) {
                    try {
                        RestClient.create(ApplicationService.class, sessionDetailsCallback).getSession(model.host, model.id, item.id, model.remote);
                    } catch (ServiceException ex) {
                        Window.alert("Error: " + ex.getMessage());
                    }
                }
            }
        });   
        
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                onSelectTab();
            }
        });        
    }
   
    public void onSelectTab() {
        sessionTable.getDataGrid().onResize();
    }
    
    public void reset() {
        sessionDetailsPanel.reset();
        attributesPanel.reset();
    }
    
    public void load(ApplicationDetailsModel model, List<SessionTableModel> data) {
        List<SessionTableModel> tmp = new ArrayList<SessionTableModel>();
        for (int i=0; i<100; i++) {
            for (int j=0; j<data.size(); j++) {
                tmp.add(data.get(j));
            }
        }
        
        sessionTable.setData(model, tmp);
    }
    
    private void load(SessionDetails session) {
        SessionDetailsModel details = ObjectFactory.create(session);
        sessionDetailsPanel.open(details);

        List<AttributeTableModel> attributes = ObjectFactory.createAttributes(session);
        attributesPanel.setData(details, attributes);
        attributesPanel.getTable().flush();
    }
}
