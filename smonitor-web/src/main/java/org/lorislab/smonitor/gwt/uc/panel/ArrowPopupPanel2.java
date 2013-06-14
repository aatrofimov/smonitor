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
import org.lorislab.smonitor.admin.client.model.AgentWrapper;

/**
 *
 * @author Andrej Petras
 */
public class ArrowPopupPanel2 extends PopupPanel {

    @UiField
    Button btnEditAction;

    @UiField
    Button btnEditDelete;
    
    @UiField
    Button btnEditStatus;
    
    @UiField
    Button btnEditInfo;
    
    private AgentWrapper data;
    
    private ClickButtonHandler handler;
    
    public ArrowPopupPanel2() {
        setWidget(uiBinder.createAndBindUi(this));
        setStyleName("arrow-popup-right");
        
        btnEditAction.setEnabled(false);
        btnEditAction.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (handler != null && data != null) {
                    handler.edit(data);
                }
                close();
            }
        });
    }

    public void setHandler(ClickButtonHandler handler) {
        this.handler = handler;
    }
    
    public void open(int left, int top, AgentWrapper data) {
        this.data = data;
        
        btnEditAction.setEnabled(true);
        
        this.setPopupPosition(left-55, top-60);
        this.show();
    }

    public void close() {
        data = null;
        this.hide();
    }

    public interface ClickButtonHandler {
        
        public void edit(AgentWrapper data);
        
        public void status(AgentWrapper data);
        
        public void password(AgentWrapper data);
        
        public void info(AgentWrapper data);
        
        public void delete(AgentWrapper data);
    }
    
    interface MyUiBinder extends UiBinder<Widget, ArrowPopupPanel2> {
    }
    private static ArrowPopupPanel2.MyUiBinder uiBinder = GWT.create(ArrowPopupPanel2.MyUiBinder.class);    
}
