/*
 * Copyright 2013 lorislab.org.
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
package org.lorislab.smonitor.admin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;
import org.lorislab.smonitor.admin.client.handler.MenuPanelHandler;

/**
 *
 * @author Andrej Petras
 */
public class MenuPanel extends Composite {

    @UiField
    ToggleButton btnAgent;
    
    @UiField
    ToggleButton btnSession;    
            
    @UiField
    ToggleButton btnDashboard;
    
    private MenuPanelHandler handler;
    
    public MenuPanel() {
        initWidget(uiBinder.createAndBindUi(this));
        
        btnDashboard.setDown(true);
        btnDashboard.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                btnSession.setDown(false);
                btnAgent.setDown(false);                
                if (handler != null) {                    
                    handler.switchToDashboard();
                }
            }
        });
                
        btnAgent.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                btnSession.setDown(false);
                btnDashboard.setDown(false);
                if (handler != null) {                    
                    handler.switchToAgent();
                }
            }
        });
        
        btnSession.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                btnAgent.setDown(false);
                btnDashboard.setDown(false);
                if (handler != null) {                    
                    handler.switchToSession();
                }
            }
        });
    }

    public void setHandler(MenuPanelHandler handler) {
        this.handler = handler;
    }

    
    interface MyUiBinder extends UiBinder<Widget, MenuPanel> {
    }
    private static MenuPanel.MyUiBinder uiBinder = GWT.create(MenuPanel.MyUiBinder.class);
}
