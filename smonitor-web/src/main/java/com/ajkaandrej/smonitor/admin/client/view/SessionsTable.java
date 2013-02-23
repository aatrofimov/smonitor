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

import com.ajkaandrej.smonitor.admin.client.model.SessionInfoListGridRecord;
import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionHeader;
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

    public void loadData(List<ClientHttpSessionHeader> sessions) {
        if (sessions != null) {
            SessionInfoListGridRecord[] result = new SessionInfoListGridRecord[sessions.size()];
            for (int i = 0; i < sessions.size(); i++) {
                result[i] = new SessionInfoListGridRecord(sessions.get(i));
            }
            setData(result);
        } else {
            setData(new SessionInfoListGridRecord[0]);
        }        
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
