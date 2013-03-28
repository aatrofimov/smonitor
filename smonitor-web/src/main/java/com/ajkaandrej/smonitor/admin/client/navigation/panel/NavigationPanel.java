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

import com.ajkaandrej.gwt.uc.ConstantValues;
import com.ajkaandrej.smonitor.agent.rs.model.Server;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class NavigationPanel extends Composite {

    private TabLayoutPanel tabPanel;
    private ServerNagivationPanel serverPanel;
    private ApplicationNagivationPanel applicationPanel;
    private PushButton refreshButton;
    private PushButton configButton;
    private PushButton reloadApplicationButton;
    
    public static interface Images extends ClientBundle {
        
        ImageResource refresh();
        
        ImageResource config();
        
        ImageResource reloadApp();
    }
    
    public static final Images IMAGES = GWT.create(Images.class);
    
    public NavigationPanel() {
        tabPanel = createTabPanel();
       
        refreshButton = new PushButton(new Image(IMAGES.refresh()));
        refreshButton.setTitle("Reload the servers");
        configButton = new PushButton(new Image(IMAGES.config()));
        configButton.setTitle("Reload configuration");
        reloadApplicationButton = new PushButton(new Image(IMAGES.reloadApp()));
        reloadApplicationButton.setTitle("Reload selected application");
        
        HorizontalPanel sp = new HorizontalPanel();
        sp.setStyleName("navigationButtonBar");
        sp.add(refreshButton);
        sp.add(reloadApplicationButton);
        sp.add(configButton);
        HTML spacer = new HTML(ConstantValues.HTML_TAG_DIV);        
        sp.add(spacer);
        sp.setCellWidth(spacer, ConstantValues.PCT_100);
        ConstantValues.setWidth100(sp);
        
        VerticalPanel dock = new VerticalPanel();
        ConstantValues.set100(dock);
        dock.add(tabPanel);
        dock.setCellHeight(tabPanel, ConstantValues.PCT_100);
        dock.add(sp);
        
        reset();
        
        initWidget(dock);
    }

    public PushButton getReloadApplicationButton() {
        return reloadApplicationButton;
    }
    
    public PushButton getRefreshButton() {
        return refreshButton;
    }

    public PushButton getConfigButton() {
        return configButton;
    }
        
    private TabLayoutPanel createTabPanel() {
        serverPanel = new ServerNagivationPanel();
        applicationPanel = new ApplicationNagivationPanel();

        TabLayoutPanel result = new TabLayoutPanel(2.5, Style.Unit.EM);
        result.setAnimationDuration(1000);
        result.add(serverPanel, "Servers");
        result.add(applicationPanel, "Applications");
        ConstantValues.set100(result);
        result.selectTab(0);
        return result;
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
        // disable the reload appliction button
        reloadApplicationButton.setEnabled(false);
        // reset tab server
        serverPanel.reset();
        // reset tab application
        applicationPanel.reset();
    }

    public void addServer(Server server) {
        serverPanel.addServer(server);
        applicationPanel.addServer(server);
    }
}
