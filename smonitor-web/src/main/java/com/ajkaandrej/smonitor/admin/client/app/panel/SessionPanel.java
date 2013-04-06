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

import com.ajkaandrej.gwt.uc.ConstantValues;
import com.ajkaandrej.smonitor.admin.client.app.model.ApplicationDetailsModel;
import com.ajkaandrej.smonitor.admin.client.app.model.AttributeTableModel;
import com.ajkaandrej.smonitor.admin.client.app.model.SessionDetailsModel;
import com.ajkaandrej.smonitor.admin.client.app.model.SessionTableModel;
import com.ajkaandrej.smonitor.admin.client.factory.ObjectFactory;
import com.ajkaandrej.smonitor.agent.rs.model.SessionDetails;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionPanel extends Composite {
 
//    private TabLayoutPanel tabPanel;
    
    private SessionDetailsPanel sessionDetailsPanel;
        
    private AttributesPanel attributesPanel;
    
    private SessionsTable sessionTable;
    
    public SessionPanel() {
         
        sessionTable = new SessionsTable(); 
        sessionDetailsPanel = new SessionDetailsPanel();
        attributesPanel = new AttributesPanel();

        DockLayoutPanel detailsVPanel = new DockLayoutPanel(Style.Unit.PX);
        detailsVPanel.addNorth(sessionDetailsPanel, 150);
        detailsVPanel.add(attributesPanel);

        SplitLayoutPanel splitPanel = new SplitLayoutPanel(5) {

            @Override
            public void onResize() {
                super.onResize();
                sessionTable.onResize();
            }
            
        };
        ConstantValues.set100(splitPanel);
        splitPanel.getElement().getStyle().setProperty("border", "0px solid #e7e7e7");
        
        
        splitPanel.addSouth(detailsVPanel, 33);
        splitPanel.add(sessionTable);
        
        splitPanel.setWidgetMinSize(detailsVPanel, 33);
        initWidget(splitPanel);
    }
   
    public SessionsTable getSessionTable() {
        return sessionTable;
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
    
    public void load(SessionDetails session) {
        SessionDetailsModel details = ObjectFactory.create(session);
        sessionDetailsPanel.open(details);

        List<AttributeTableModel> attributes = ObjectFactory.createAttributes(session);
        attributesPanel.setData(details, attributes);
        attributesPanel.getTable().flush();
    }
}
