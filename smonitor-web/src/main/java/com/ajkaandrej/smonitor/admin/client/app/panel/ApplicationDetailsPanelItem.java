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
package com.ajkaandrej.smonitor.admin.client.app.panel;

import com.ajkaandrej.smonitor.admin.client.app.model.ApplicationDetailsModel;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationDetailsPanelItem extends Composite {
    
    private static final String DATE_PATTERN = "dd.MM.yyyy HH:mm:ss";
    private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat(DATE_PATTERN);
    private static final String LINK_TARGET_BLANK = "_blank";
    
    private ApplicationDetailsModel data;
    
    public ApplicationDetailsPanelItem(ApplicationDetailsModel model) {
        this.data = model;
        FlexTable layout = new FlexTable();
        layout.setWidth("100%");
        layout.setStyleName("applicationDetailsPanelItem");
        
        layout.setCellSpacing(6);
        FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

        // Add a title to the form
//        layout.setHTML(0, 0, data.hostName + ":" + data.hostPort + data.context);
        String url = data.scheme + "://" + data.hostName + ":" + data.hostPort + data.context;
        layout.setWidget(0, 0, new Anchor(url, url, LINK_TARGET_BLANK));
        cellFormatter.setColSpan(0, 0, 6);
        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        // Add some standard form options
        
        layout.setHTML(1, 0, "id:");
        layout.setHTML(1, 1, data.id);
        layout.setHTML(1, 2, "name:");
        layout.setHTML(1, 3, data.name);
        layout.setHTML(1, 4, "host:");
        layout.setHTML(1, 5, data.host);

        layout.setHTML(2, 0, "activeSessions:");
        layout.setHTML(2, 1, "" + data.activeSessions);
        layout.setHTML(2, 2, "distributable:");
        layout.setHTML(2, 3, "" + data.distributable);
        layout.setHTML(2, 4, "expiredSessions:");
        layout.setHTML(2, 5, "" + data.expiredSessions);
        
        layout.setHTML(3, 0, "maxActive:");
        layout.setHTML(3, 1, "" + data.maxActive);
        layout.setHTML(3, 2, "maxInactiveInterval:");
        layout.setHTML(3, 3, "" + data.maxInactiveInterval);
        layout.setHTML(3, 4, "rejectedSessions:");
        layout.setHTML(3, 5, "" + data.rejectedSessions);
        
        layout.setHTML(4, 0, "sessionAverageAliveTime:");
        layout.setHTML(4, 1, "" + data.sessionAverageAliveTime);
        layout.setHTML(4, 2, "sessionCounter:");
        layout.setHTML(4, 3, "" + data.sessionCounter);
        layout.setHTML(4, 4, "sessionIdLength:");
        layout.setHTML(4, 5, "" + data.sessionIdLength);
        
        layout.setHTML(5, 0, "sessionMaxAliveTime:");
        layout.setHTML(5, 1, "" + data.sessionMaxAliveTime);
        layout.setHTML(5, 2, "context:");
        layout.setHTML(5, 3, "" + data.context);
        layout.setHTML(5, 4, "startTime:");
        layout.setHTML(5, 5, DATE_FORMAT.format(data.startTime));
        
        initWidget(layout);
    }

    public ApplicationDetailsModel getData() {
        return data;
    }
}
