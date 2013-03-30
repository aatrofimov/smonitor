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

import com.ajkaandrej.smonitor.admin.client.app.model.SessionTableModel;
import com.ajkaandrej.gwt.uc.panel.EntityTablePanel;
import com.ajkaandrej.gwt.uc.table.EntityTable;
import com.ajkaandrej.gwt.uc.table.column.EntityDateColumn;
import com.ajkaandrej.gwt.uc.table.column.EntityIntegerColumn;
import com.ajkaandrej.gwt.uc.table.column.EntityTextColumn;
import com.ajkaandrej.smonitor.admin.client.app.model.ApplicationDetailsModel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import java.util.Date;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionsTable extends EntityTablePanel<ApplicationDetailsModel,SessionTableModel> {
        
    public SessionsTable() {
        super();
        
        EntityTable<SessionTableModel> table = this.getTable();
        Column hostColumn = table.addColumn("Host", true, new EntityTextColumn<SessionTableModel>(){
            @Override
            public String getObject(SessionTableModel object) {
                return object.hostName;
            }
        });        
        table.setColumnWidth(hostColumn, 200, Unit.PX);
        
        Column portColumn = table.addColumn("Port", true, new EntityIntegerColumn<SessionTableModel>(){
            @Override
            public Integer getObject(SessionTableModel object) {
                return object.hostPort;
            }
        });
        table.setColumnWidth(portColumn, 50, Unit.PX);
        
        table.addColumn("Id", true, new EntityTextColumn<SessionTableModel>(){
            @Override
            public String getObject(SessionTableModel object) {
                return object.id;
            }
        });
        table.addColumn("User", true, new EntityTextColumn<SessionTableModel>(){
            @Override
            public String getObject(SessionTableModel object) {
                return object.user;
            }
        });
        Column createColumn = table.addColumn("Create", true, new EntityDateColumn<SessionTableModel>(){
            @Override
            public Date getObject(SessionTableModel object) {
                return object.creationTime;
            }
        });
        table.setColumnWidth(createColumn, 180, Unit.PX);
        
        Column lastAccessedColumn = table.addColumn("Last accessed", true, new EntityDateColumn<SessionTableModel>(){
            @Override
            public Date getObject(SessionTableModel object) {
                return object.lastAccessedTime;
            }
        });
        table.setColumnWidth(lastAccessedColumn, 180, Unit.PX);               
      
    }

}
