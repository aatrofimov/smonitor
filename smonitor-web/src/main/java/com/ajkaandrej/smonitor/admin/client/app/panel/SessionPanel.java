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

import com.ajkaandrej.smonitor.admin.client.app.model.AttributeTableModel;
import com.ajkaandrej.smonitor.admin.client.app.model.SessionDetailsModel;
import com.ajkaandrej.smonitor.admin.client.factory.ObjectFactory;
import com.ajkaandrej.smonitor.agent.rs.model.SessionDetails;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionPanel extends Composite {
 
    private TabLayoutPanel tabPanel;
    
    private SessionDetailsPanel sessionDetailsPanel;
        
    private AttributesPanel attributesPanel;
    
    public SessionPanel() {
        
        tabPanel = new TabLayoutPanel(2.5, Style.Unit.EM);
        
        sessionDetailsPanel = new SessionDetailsPanel();
        tabPanel.add(sessionDetailsPanel, "Session");  
        
        attributesPanel = new AttributesPanel();
        tabPanel.add(attributesPanel, "Attributes");
        
        initWidget(tabPanel);
    }
    
    public void reset() {
        sessionDetailsPanel.reset();
        attributesPanel.reset();
    }
    
    public void load(SessionDetails session) {
        SessionDetailsModel details = ObjectFactory.create(session);
        sessionDetailsPanel.load(details);
        
        List<AttributeTableModel> attributes = ObjectFactory.createAttributes(session);
        attributesPanel.setData(details, attributes);
    }
}
