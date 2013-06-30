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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;
import org.lorislab.smonitor.admin.client.model.SessionWrapper;
import org.lorislab.smonitor.gwt.uc.toolbar.AbstractToolbar;

/**
 * The session tool bar.
 * 
 * @author Andrej Petras
 */
public class SessionToolbarPanel extends AbstractToolbar<SessionWrapper, SessionToolbarPanel.ClickButtonHandler> {

    @UiField
    ToggleButton btnDelete;
    @UiField
    ToggleButton btnInfo;
    @UiField
    ToggleButton btnRefresh;

    public SessionToolbarPanel() {
        setWidget(uiBinder.createAndBindUi(this));
        setStyleName("arrow-popup-right");
    }

    @UiHandler("btnDelete")
    void clickDelete(ClickEvent event) {
        if (isEvent()) {
            getHandler().delete(getData());
        }
        close();
    }
    
    @UiHandler("btnRefresh")
    void clickRefresh(ClickEvent event) {
        if (isEvent()) {
            getHandler().refresh(getData());
        }
        close();
    }
    
    @UiHandler("btnInfo")
    void clickInfo(ClickEvent event) {
        if (isEvent()) {
            getHandler().info(getData());
        }
        close();
    }

    public void open(int left, int top, SessionWrapper wrapper) {
        int size = 1;
        btnInfo.setVisible(wrapper.data.isValid());
        btnDelete.setVisible(wrapper.data.isValid());
        if (wrapper.data.isValid()) {
            size = size + 2;
        }
        size = size * 33;
        super.open(left - 30, top - (size / 2) + 14, size, wrapper);
    }

    public interface ClickButtonHandler {

        public void info(SessionWrapper data);

        public void delete(SessionWrapper data);

        public void refresh(SessionWrapper data);
    }

    interface MyUiBinder extends UiBinder<Widget, SessionToolbarPanel> {
    }
    private static SessionToolbarPanel.MyUiBinder uiBinder = GWT.create(SessionToolbarPanel.MyUiBinder.class);
}
