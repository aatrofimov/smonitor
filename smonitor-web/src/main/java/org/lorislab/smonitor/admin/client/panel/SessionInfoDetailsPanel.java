package org.lorislab.smonitor.admin.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import org.lorislab.smonitor.rs.model.SessionInfoDetails;

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
/**
 *
 * @author Andrej Petras
 */
public final class SessionInfoDetailsPanel extends PopupPanel {

    @UiField
    Button btnClose;
    @UiField
    AttributeGridPanel attributePanel;  
    @UiField
    SessionInfoDetailsModelForm sessionForm;
    
    public SessionInfoDetailsPanel() {
        setWidget(binder.createAndBindUi(this));
        setStyleName("entity-panel-main");
        setGlassEnabled(true);

        sessionForm.init();
        
        btnClose.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                close();
            }
        });       
    }

    public void close() {
        hide();
        attributePanel.reset();
    }

    public void open(SessionInfoDetails data) {
        attributePanel.reset();
        attributePanel.setSessionInfoDetails(data);
        attributePanel.addAllItems(data.getAttributes());
        sessionForm.open(data);
        this.center();
        this.show();
    }
    
    private static final SessionInfoDetailsPanel.Binder binder = GWT.create(SessionInfoDetailsPanel.Binder.class);

    interface Binder extends UiBinder<Widget, SessionInfoDetailsPanel> {
    }
}
