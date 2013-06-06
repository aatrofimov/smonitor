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
package org.lorislab.smonitor.admin.client.panel;

import org.lorislab.smonitor.gwt.uc.dialogbox.EntityDialogBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import org.lorislab.smonitor.rs.admin.model.Agent;

/**
 *
 * @author Andrej Petras
 */
public class AgentDialogBox extends EntityDialogBox<Agent> {

    @UiField
    Label title;
    
    @UiField
    Button btnCreate;
    
    @UiField
    Button btnCancel;
    
    @UiField
    Button btnUpdate;
    
    @UiField
    TextBox txtName;
    
    @UiField
    TextBox txtServer;
    
    @UiField
    TextBox txtKey;
    
    @UiField
    CheckBox chEnabled;

    public AgentDialogBox() {
        setWidget(binder.createAndBindUi(this));

        btnCreate.setVisible(true);
        btnCancel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                cancel();
            }
        });

        btnCreate.setVisible(false);
        btnCreate.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                create();
            }
        });
        
        btnUpdate.setVisible(false);
        btnUpdate.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                update();
            }
        });        
    }

    @Override
    protected void openView(Mode mode) {
        btnCreate.setVisible(Mode.CREATE.equals(mode));
        btnUpdate.setVisible(Mode.UPDATE.equals(mode));
        if (Mode.CREATE.equals(mode)) {
            title.setText("Create agent");
        } else if (Mode.UPDATE.equals(mode)) {
            title.setText("Update agent");
        } else {
            title.setText("Agent dialog");
        }        
    }
    
    @Override
    protected void getEntityData(Agent item) {
        txtName.setText(item.getName());
        txtServer.setText(item.getServer());
        chEnabled.setValue(item.isEnabled());
    }
    
    @Override
    protected void setEntityData(Agent item) {
        item.setName(txtName.getText());
        item.setServer(txtServer.getText());
        item.setEnabled(chEnabled.getValue());        
    }
    
    private static final Binder binder = GWT.create(Binder.class);

    interface Binder extends UiBinder<Widget, AgentDialogBox> {
    }
        
}
