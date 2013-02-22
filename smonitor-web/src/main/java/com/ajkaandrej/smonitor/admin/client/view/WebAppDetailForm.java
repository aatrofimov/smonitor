/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.admin.client.view;

import com.ajkaandrej.smonitor.admin.client.model.WebAppDetailViewerRecord;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationDetails;
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
    
    private WebApplicationDetails object;
    
    public WebAppDetailForm() {
        setCanEdit(false);
        setNumCols(6);  
        setCellPadding(5);          
        setAutoFocus(false);          
        setFields(createFields());
    }

    public void editWebApplicationDetails(WebApplicationDetails webApp) {
        object = webApp;
        WebAppDetailViewerRecord record = new WebAppDetailViewerRecord(webApp);
        editRecord(record);
    }
    
    public WebApplicationDetails getObject() {
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
