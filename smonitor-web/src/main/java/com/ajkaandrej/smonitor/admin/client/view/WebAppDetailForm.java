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
package com.ajkaandrej.smonitor.admin.client.view;

import com.ajkaandrej.smonitor.admin.client.model.WebAppDetailViewerRecord;
import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplicationWrapper;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class WebAppDetailForm extends DynamicForm {
    
    private ClientWebApplicationWrapper object;
    
    public WebAppDetailForm() {
        setCanEdit(false);
        setNumCols(6);  
        setCellPadding(5);          
        setAutoFocus(false);          
        setFields(createFields());
    }

    public void loadData(ClientWebApplicationWrapper webApp) {
        object = webApp;
        if (webApp != null) {            
            WebAppDetailViewerRecord record = new WebAppDetailViewerRecord(webApp);
            editRecord(record);
        } else {
            editRecord(null);
        }
    }
    
    public ClientWebApplicationWrapper getObject() {
        return object;
    }
    
    private static FormItem[] createFields() {
        List<FormItem> items = new ArrayList<FormItem>();
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_NAME, "Name"));
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_INFO, "Info"));
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_HOST, "Host"));        
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_ACTIVE_SESSION, "Active sessions"));
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_EXPIRED_SESSION, "Expired sessions"));
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_MAX_ACTIVE, "Max. sessions"));
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_MAX_INACTIVE_INTERVAL, "Max. inactive interval"));
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_REJECTED_SESSIONS, "Reject sessions"));
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_SESSION_AVERAGE_LIVE_TIME, "Avarage live time"));        
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_SESSION_COUNTER, "Session count"));
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_SESSION_ID_LENGTH, "Id length"));
        items.add(new StaticTextItem(WebAppDetailViewerRecord.ATTR_SESSION_MAX_LIVE_TIME, "Max. live time")); 
        return items.toArray(new FormItem[items.size()]);        
    }
}
