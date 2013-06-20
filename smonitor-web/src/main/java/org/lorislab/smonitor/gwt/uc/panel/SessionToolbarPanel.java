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
package org.lorislab.smonitor.gwt.uc.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import org.lorislab.smonitor.rs.model.SessionInfo;

/**
 *
 * @author Andrej Petras
 */
public class SessionToolbarPanel extends PopupPanel {


    @UiField
    Button btnDelete;
    
    @UiField
    Button btnInfo;

    @UiField
    Button btnRefresh;
    
    private SessionInfo data;
    
    private ClickButtonHandler handler;
    
    public SessionToolbarPanel() {
        setWidget(uiBinder.createAndBindUi(this));
        setStyleName("arrow-popup-right");
                       
        btnDelete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (handler != null && data != null) {
                    handler.delete(data);
                }
                close();
            }
        });  
     
        btnRefresh.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (handler != null && data != null) {
                    handler.refresh(data);
                }
                close();
            }
        });
        
        btnInfo.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (handler != null && data != null) {
                    handler.info(data);
                }
                close();
            }
        });
                      
    }

    public void setHandler(ClickButtonHandler handler) {
        this.handler = handler;
    }
    
    public void open(int left, int top, SessionInfo data) {
        this.data = data;
        int size = 1;
        btnInfo.setVisible(data.isValid());
        btnDelete.setVisible(data.isValid());        
        if (data.isValid()) {
            size = size + 2;
        }
        size = size * 32;
        this.setHeight("" + size + "px");
        this.setPopupPosition(left-55, top-(size/2)+10);
        this.show();
    }

    public void close() {
        data = null;
        this.hide();
    }

    public interface ClickButtonHandler {
        
        public void info(SessionInfo data);
        
        public void delete(SessionInfo data);
        
        public void refresh(SessionInfo data);
        
    }
    
    interface MyUiBinder extends UiBinder<Widget, SessionToolbarPanel> {
    }
    private static SessionToolbarPanel.MyUiBinder uiBinder = GWT.create(SessionToolbarPanel.MyUiBinder.class);    
}
