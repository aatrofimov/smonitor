package com.ajkaandrej.smonitor.admin.client.view;

import com.ajkaandrej.smonitor.admin.client.model.SessionDetailsViewerRecord;
import com.ajkaandrej.smonitor.admin.shared.model.SessionDetails;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateTimeItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionDetailsForm extends DynamicForm {

    public SessionDetailsForm() {
        setCanEdit(false);
        setNumCols(4);
        setCellPadding(5);
        setAutoFocus(false);
        
        setFields(createFields());
    }

    public void loadData(SessionDetails session) {
        editRecord(new SessionDetailsViewerRecord(session));
    }

    private static FormItem[] createFields() {
        List<FormItem> items = new ArrayList<FormItem>();
        items.add(new StaticTextItem(SessionDetailsViewerRecord.ATTR_USER_NAME, "User"));
        items.add(new StaticTextItem(SessionDetailsViewerRecord.ATTR_USER_ROLES, "User roles"));
        
        
        items.add(new StaticTextItem(SessionDetailsViewerRecord.ATTR_ID, "Id"));
        items.add(new CheckboxItem(SessionDetailsViewerRecord.ATTR_VALID, "isValid"));
//        items.add(new StaticTextItem(SessionDetailsViewerRecord.ATTR_INFO, "Info"));
        
        items.add(new DateTimeItem(SessionDetailsViewerRecord.ATTR_CREATION, "Created"));
        items.add(new DateTimeItem(SessionDetailsViewerRecord.ATTR_LAST_ACCESS, "Last access"));        

        
        items.add(new StaticTextItem(SessionDetailsViewerRecord.ATTR_LAST_ACCESS_INTERVAL, "Last access interval"));
        items.add(new StaticTextItem(SessionDetailsViewerRecord.ATTR_MAX_INC_INTERVAL, "Max. inactive interval"));
        
        items.add(new StaticTextItem(SessionDetailsViewerRecord.ATTR_SIZE, "Size"));
        items.add(new StaticTextItem(SessionDetailsViewerRecord.ATTR_SER_SIZE, "Ser. size"));              
        
        return items.toArray(new FormItem[items.size()]);
    }
    
}
