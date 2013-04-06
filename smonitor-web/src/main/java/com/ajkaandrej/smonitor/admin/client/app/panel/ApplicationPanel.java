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

import com.ajkaandrej.smonitor.admin.client.app.model.ApplicationDetailsModel;
import com.ajkaandrej.smonitor.admin.client.factory.ObjectFactory;
import com.ajkaandrej.smonitor.agent.rs.model.ApplicationDetails;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationPanel extends Composite {
    
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
        
        tabPanel.selectTab(0);
        reset();
        
        initWidget(tabPanel);
    }
    
    public void onResize() {
        sessionPanel.getSessionTable().onResize();        
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
}
