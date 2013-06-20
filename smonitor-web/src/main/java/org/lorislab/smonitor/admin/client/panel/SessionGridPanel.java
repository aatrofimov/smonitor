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

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.Column;
import java.util.Date;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.column.EntityDateColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityImageColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityTextColumn;
import org.lorislab.smonitor.rs.model.SessionInfo;

/**
 *
 * @author Andrej Petras
 */
public class SessionGridPanel extends AbstractGridPanel<SessionInfo> {
    
    @Override
    public SessionInfo findById(final Object id) {
        SessionInfo item = find(new EntityDataGrid.FilterItem<SessionInfo>() {
            @Override
            public SessionInfo isItem(SessionInfo item) {
                if (item.getId().equals(id)) {
                    return item;
                }
                return null;
            }
        });
        return item;
    }
              
    @Override
    protected void createColumns() {
        EntityDataGrid<SessionInfo> table = dataGrid;


        Column colAction = table.addColumn(" ", true, new EntityImageColumn<SessionInfo, Boolean>() {
            @Override
            public Boolean getObject(SessionInfo object) {
                return object.isValid();
            }

            @Override
            public String getValue(SessionInfo object) {
                String result = "images/status_error.png";
                if (object.isValid()) {
                    result = "images/status_ok.png";
                }
                return result;
            }
        });
        table.setColumnWidth(colAction, 25, Style.Unit.PX);
        
        Column colAgent = table.addColumn("Agent", true, new EntityTextColumn<SessionInfo>() {
            @Override
            public String getObject(SessionInfo object) {
                return object.getAgent();
            }
        });
        table.setColumnWidth(colAgent, 100, Style.Unit.PX);
        
        Column colApp = table.addColumn("Application", true, new EntityTextColumn<SessionInfo>() {
            @Override
            public String getObject(SessionInfo object) {
                return object.getApplication();
            }
        });
        table.setColumnWidth(colApp, 100, Style.Unit.PX);
        
        Column colId = table.addColumn("Id", true, new EntityTextColumn<SessionInfo>() {
            @Override
            public String getObject(SessionInfo object) {
                return object.getId();
            }
        });
        table.setColumnWidth(colId, 150, Style.Unit.PX);

        Column colUser = table.addColumn("User", true, new EntityTextColumn<SessionInfo>() {
            @Override
            public String getObject(SessionInfo object) {
                return object.getUser();
            }
        });
        table.setColumnWidth(colUser, 100, Style.Unit.PX);

        Column colCreate = table.addColumn("Create", true, new EntityDateColumn<SessionInfo>() {
            @Override
            public Date getObject(SessionInfo object) {
                return object.getCreationTime();
            }
        });
        table.setColumnWidth(colCreate, 90, Style.Unit.PX);

        Column colUpdate = table.addColumn("Update", true, new EntityDateColumn<SessionInfo>() {
            @Override
            public Date getObject(SessionInfo object) {
                return object.getLastAccessedTime();
            }
        });
        table.setColumnWidth(colUpdate, 90, Style.Unit.PX);
    }
}
