/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.admin.client.view;

import com.ajkaandrej.smonitor.admin.client.model.SessionInfoListGridRecord;
import com.ajkaandrej.smonitor.admin.shared.model.SessionInfo;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.types.AutoFitWidthApproach;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionsTable extends ListGrid {

    private static final DateTimeFormat fmt = DateTimeFormat.getFormat("dd.MM.yyyy hh:mm");

    public SessionsTable() {
        setWidth100();
        setHeight100();
        setShowAllRecords(true);
        setCanResizeFields(false);
        setFields(createFields());
    }

    public void loadData(SessionInfo[] sessions) {
        SessionInfoListGridRecord[] result = new SessionInfoListGridRecord[0];
        if (sessions != null) {
            result = new SessionInfoListGridRecord[sessions.length];
            for (int i = 0; i < sessions.length; i++) {
                result[i] = new SessionInfoListGridRecord(sessions[i]);
            }
        }
        setData(result);
    }

    private static ListGridField[] createFields() {
        List<ListGridField> tmp = new ArrayList<ListGridField>();
        tmp.add(new ListGridField(SessionInfoListGridRecord.ATTR_USER, "User"));
        tmp.add(new ListGridField(SessionInfoListGridRecord.ATTR_ID, "Id"));


        ListGridField createField = new ListGridField(SessionInfoListGridRecord.ATTR_CREATIONTIME, "Created");
        createField.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object arg0, ListGridRecord arg1, int arg2, int arg3) {
                return fmt.format(arg1.getAttributeAsDate(SessionInfoListGridRecord.ATTR_CREATIONTIME));
            }
        });
        tmp.add(createField);

        ListGridField upadateField = new ListGridField(SessionInfoListGridRecord.ATTR_LASTACCESSEDTIME, "Update");
        upadateField.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object arg0, ListGridRecord arg1, int arg2, int arg3) {
                return fmt.format(arg1.getAttributeAsDate(SessionInfoListGridRecord.ATTR_LASTACCESSEDTIME));
            }
        });
        tmp.add(upadateField);
        tmp.add(new ListGridField(SessionInfoListGridRecord.ATTR_VALID, "Valid"));

        for (ListGridField field : tmp) {
            field.setShowDefaultContextMenu(false);
            field.setAutoFitWidthApproach(AutoFitWidthApproach.VALUE);
        }

        return tmp.toArray(new ListGridField[tmp.size()]);
    }
}
