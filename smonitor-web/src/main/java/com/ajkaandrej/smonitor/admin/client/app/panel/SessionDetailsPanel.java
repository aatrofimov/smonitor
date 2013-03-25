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

import com.ajkaandrej.smonitor.admin.client.app.model.SessionDetailsModel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionDetailsPanel extends Composite {
    
    private SessionDetailsModel data;

    public SessionDetailsPanel(SessionDetailsModel session) {
        this.data = session;
        
        FlexTable layout = new FlexTable();
        
//        layout.setCellSpacing(6);
//        FlexTable.FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
//
//        layout.setHTML(0, 0, data.id);
//        cellFormatter.setColSpan(0, 0, 6);
//        cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
  
        initWidget(layout);
    }
    
    public SessionDetailsModel getData() {
        return data;
    }
    
    
}
