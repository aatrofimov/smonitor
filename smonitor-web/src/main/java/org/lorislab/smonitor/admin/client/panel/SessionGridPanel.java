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
package org.lorislab.smonitor.admin.client.panel;

import com.google.gwt.user.cellview.client.Column;
import java.util.Date;
import org.lorislab.smonitor.admin.client.model.SessionWrapper;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.column.EntityDateColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityTextColumn;
import com.google.gwt.dom.client.Style.Unit;
import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.gwt.uc.table.column.EntitySpanColumn;
import org.lorislab.smonitor.rs.model.SessionInfo;

/**
 *
 * @author Andrej Petras
 */
public final class SessionGridPanel extends EntityDataGrid<SessionInfo, SessionWrapper> {

    @Override
    protected SessionWrapper createWrapper() {
        return new SessionWrapper();
    }
    
    public void request(SessionWrapper item) {
        item.request = true;
        set(item);
    }
    
    public void replaceById(String id, SessionInfo data) {
        if (data != null) {
            SessionWrapper item = findById(id);
            if (item != null) {
                item.request = false;
                item.data = data;
                set(item);
            }
        }
    }

    @Override
    protected void createColumns() {

        Column colAction = addColumn(ConstantValues.space(), true, new EntitySpanColumn<SessionWrapper, Boolean>() {
            @Override
            public Boolean getObject(SessionWrapper object) {
                return object.data.isValid();
            }

            @Override
            public String getValue(SessionWrapper object) {
                String result = "icon-record status-icon-red";
                if (object.request) {
                    result = "icon-arrows-ccw animate-spin status-icon-blue";
                } else if (object.data.isValid()) {
                    result = "icon-record status-icon-green";
                }
                return result;
            }
        });
        setColumnWidth(colAction, 25, Unit.PX);

        Column colAgent = addColumn("Agent", true, new EntityTextColumn<SessionWrapper>() {
            @Override
            public String getObject(SessionWrapper object) {
                return object.data.getAgent();
            }
        });
        setColumnWidth(colAgent, 100, Unit.PX);

        Column colApp = addColumn("Application", true, new EntityTextColumn<SessionWrapper>() {
            @Override
            public String getObject(SessionWrapper object) {
                return object.data.getApplication();
            }
        });
        setColumnWidth(colApp, 100, Unit.PX);

        Column colId = addColumn("Id", true, new EntityTextColumn<SessionWrapper>() {
            @Override
            public String getObject(SessionWrapper object) {
                return object.data.getId();
            }
        });
        setColumnWidth(colId, 150, Unit.PX);

        Column colUser = addColumn("User", true, new EntityTextColumn<SessionWrapper>() {
            @Override
            public String getObject(SessionWrapper object) {
                return object.data.getUser();
            }
        });
        setColumnWidth(colUser, 100, Unit.PX);

        Column colCreate = addColumn("Create", true, new EntityDateColumn<SessionWrapper>() {
            @Override
            public Date getObject(SessionWrapper object) {
                return object.data.getCreationTime();
            }
        });
        setColumnWidth(colCreate, 90, Unit.PX);

        Column colUpdate = addColumn("Update", true, new EntityDateColumn<SessionWrapper>() {
            @Override
            public Date getObject(SessionWrapper object) {
                return object.data.getLastAccessedTime();
            }
        });
        setColumnWidth(colUpdate, 90, Unit.PX);
    }
}
