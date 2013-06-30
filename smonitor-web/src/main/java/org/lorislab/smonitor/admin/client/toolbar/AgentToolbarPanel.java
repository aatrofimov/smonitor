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
package org.lorislab.smonitor.admin.client.toolbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;
import org.lorislab.smonitor.admin.client.model.AgentWrapper;
import org.lorislab.smonitor.gwt.uc.toolbar.AbstractToolbar;

/**
 * The agent tool bar.
 * 
 * @author Andrej Petras
 */
public class AgentToolbarPanel extends AbstractToolbar<AgentWrapper, AgentToolbarPanel.ClickButtonHandler> {

    @UiField
    ToggleButton btnEditAction;
    @UiField
    ToggleButton btnEditDelete;
    @UiField
    ToggleButton btnEditInfo;
    @UiField
    ToggleButton btnEditRefresh;

    public AgentToolbarPanel() {
        setWidget(uiBinder.createAndBindUi(this));
        setStyleName("arrow-popup-right");
    }

    @UiHandler("btnEditAction")
    void clickEdit(ClickEvent event) {
        if (isEvent()) {
            getHandler().edit(getData());
        }
        close();
    }
    
    @UiHandler("btnEditDelete")
    void clickDelete(ClickEvent event) {
        if (isEvent()) {
            getHandler().delete(getData());
        }
        close();
    }
    
    @UiHandler("btnEditRefresh")
    void clickRefresh(ClickEvent event) {
        if (isEvent()) {
            getHandler().refresh(getData());
        }
        close();
    }

    @UiHandler("btnEditInfo")
    void clickInfo(ClickEvent event) {
        if (isEvent()) {
            getHandler().info(getData());
        }
        close();
    }

    public void open(int left, int top, AgentWrapper wrapper) {
        int size = 2;
        if (wrapper.connected) {
            size = size + 1;
        }
        if (wrapper.data.isEnabled()) {
            size = size + 1;
        }
        size = size * 32;
        btnEditInfo.setVisible(wrapper.connected);
        btnEditRefresh.setVisible(wrapper.data.isEnabled());
        super.open(left - 30, top - (size / 2) + 14, size, wrapper);
    }


    public interface ClickButtonHandler {

        public void edit(AgentWrapper data);

        public void password(AgentWrapper data);

        public void info(AgentWrapper data);

        public void delete(AgentWrapper data);

        public void refresh(AgentWrapper data);
    }

    interface MyUiBinder extends UiBinder<Widget, AgentToolbarPanel> {
    }
    private static AgentToolbarPanel.MyUiBinder uiBinder = GWT.create(AgentToolbarPanel.MyUiBinder.class);
}
