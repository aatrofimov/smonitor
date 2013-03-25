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

import com.ajkaandrej.smonitor.agent.rs.model.Server;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class NavigationPanel extends Composite {
    
    private TabLayoutPanel tabPanel;
    
    private ServerNagivationPanel serverPanel;
    
    private ApplicationNagivationPanel applicationPanel;
    
    public NavigationPanel() {
        createTabPanel();
        tabPanel.setAnimationDuration(1000);        
        initWidget(tabPanel);
    }

    private void createTabPanel() {
        serverPanel = new ServerNagivationPanel();
        applicationPanel = new ApplicationNagivationPanel();
        
        tabPanel = new TabLayoutPanel(2.5, Style.Unit.EM);
        tabPanel.add(serverPanel, "Servers");
        tabPanel.add(applicationPanel, "Applications");
        tabPanel.selectTab(0);
    }

    public ApplicationNagivationPanel getApplicationPanel() {
        return applicationPanel;
    }

    public ServerNagivationPanel getServerPanel() {
        return serverPanel;
    }
            
    public void reset() {
        serverPanel.clear();
        applicationPanel.clear();
    }
    
    public void addServer(Server server) {
        serverPanel.addServer(server);
        applicationPanel.addServer(server);
    }
}
