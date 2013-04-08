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
package com.ajkaandrej.smonitor.admin.client.panel;

import com.ajkaandrej.smonitor.admin.client.navigation.model.ApplicationTreeModel;
import com.ajkaandrej.smonitor.admin.client.app.panel.ApplicationPanel;
import com.ajkaandrej.smonitor.admin.client.navigation.panel.NavigationPanel;
import com.ajkaandrej.gwt.uc.handler.SelectionHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class MainPanel extends Composite {

    interface MyUiBinder extends UiBinder<Widget, MainPanel> {
    }
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    NavigationPanel navigationPanel;
    @UiField
    ApplicationPanel appPanel;

    public MainPanel() {
        initWidget(uiBinder.createAndBindUi(this));
        navigationPanel.getApplicationPanel().setAppHandler(new SelectionHandler<ApplicationTreeModel>() {
            @Override
            public void selectionChanged(ApplicationTreeModel model) {
                appPanel.loadApplication(model);
            }
        });
    }

}
