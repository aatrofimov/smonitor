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

import com.ajkaandrej.gwt.uc.form.EntityForm;
import com.ajkaandrej.gwt.uc.form.item.TextFormItem;
import com.ajkaandrej.smonitor.admin.client.app.model.ApplicationDetailsModel;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationDetailsPanelItem extends EntityForm<ApplicationDetailsModel> {
          
    public ApplicationDetailsPanelItem() {
        super(3);
        setStyleName("applicationDetailsPanelItem");
        
      
        addCell("Id:", new TextFormItem<ApplicationDetailsModel>() {
            @Override
            public String getObject(ApplicationDetailsModel object) {
                return object.id;
            }
        });
        addCell("Name:", new TextFormItem<ApplicationDetailsModel>() {
            @Override
            public String getObject(ApplicationDetailsModel object) {
                return object.name;
            }
        });
        addCell("Host:", new TextFormItem<ApplicationDetailsModel>() {
            @Override
            public String getObject(ApplicationDetailsModel object) {
                return object.host;
            }
        });
        
//        String url = data.scheme + "://" + data.hostName + ":" + data.hostPort + data.context;
//        layout.setWidget(0, 0, new Anchor(url, url, ConstantValues.TARGET_BLANK));
        

        // Add some standard form options        
//        layout.setHTML(2, 0, "activeSessions:");
//        layout.setHTML(2, 1, "" + data.activeSessions);
//        layout.setHTML(2, 2, "distributable:");
//        layout.setHTML(2, 3, "" + data.distributable);
//        layout.setHTML(2, 4, "expiredSessions:");
//        layout.setHTML(2, 5, "" + data.expiredSessions);
//        
//        layout.setHTML(3, 0, "maxActive:");
//        layout.setHTML(3, 1, "" + data.maxActive);
//        layout.setHTML(3, 2, "maxInactiveInterval:");
//        layout.setHTML(3, 3, "" + data.maxInactiveInterval);
//        layout.setHTML(3, 4, "rejectedSessions:");
//        layout.setHTML(3, 5, "" + data.rejectedSessions);
//        
//        layout.setHTML(4, 0, "sessionAverageAliveTime:");
//        layout.setHTML(4, 1, "" + data.sessionAverageAliveTime);
//        layout.setHTML(4, 2, "sessionCounter:");
//        layout.setHTML(4, 3, "" + data.sessionCounter);
//        layout.setHTML(4, 4, "sessionIdLength:");
//        layout.setHTML(4, 5, "" + data.sessionIdLength);
//        
//        layout.setHTML(5, 0, "sessionMaxAliveTime:");
//        layout.setHTML(5, 1, "" + data.sessionMaxAliveTime);
//        layout.setHTML(5, 2, "context:");
//        layout.setHTML(5, 3, "" + data.context);
//        layout.setHTML(5, 4, "startTime:");
//        layout.setHTML(5, 5, DATE_FORMAT.format(data.startTime));
//        
//        initWidget(layout);
    }
}
