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
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionsTable extends Composite {

    private static final String DATE_PATTERN = "dd.MM.yyyy HH:mm:ss";
    
    private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat(DATE_PATTERN);
    
    private CellTable<SessionTableModel> table = new CellTable<SessionTableModel>();
    
    private List<SessionTableModel> data = new ArrayList<SessionTableModel>();
    
    public SessionsTable() {
        table.setWidth("100%", true);
        table.setAutoHeaderRefreshDisabled(true);
        table.setAutoFooterRefreshDisabled(true);
        table.setPageSize(3);
        
        SingleSelectionModel<SessionTableModel> ssm = new SingleSelectionModel<SessionTableModel>();
        table.setSelectionModel(ssm);

        
        TextColumn<SessionTableModel> hostColumn = new TextColumn<SessionTableModel>() {
            @Override
            public String getValue(SessionTableModel object) {
                return object.hostName;
            }
        };
        table.addColumn(hostColumn, "Host");
        
        TextColumn<SessionTableModel> portColumn = new TextColumn<SessionTableModel>() {
            @Override
            public String getValue(SessionTableModel object) {
                return "" + object.hostPort;
            }
        };
        
        
        table.addColumn(portColumn, "Port");
        
        TextColumn<SessionTableModel> idColumn = new TextColumn<SessionTableModel>() {
            @Override
            public String getValue(SessionTableModel object) {
                return object.id;
            }
        };
        table.addColumn(idColumn, "ID");

        TextColumn<SessionTableModel> userColumn = new TextColumn<SessionTableModel>() {
            @Override
            public String getValue(SessionTableModel object) {
                return object.user;
            }
        };
        table.addColumn(userColumn, "User");
        
        DateCell dateCell = new DateCell(DATE_FORMAT);        
        Column<SessionTableModel, Date> createColumn = new Column<SessionTableModel, Date>(dateCell) {
            @Override
            public Date getValue(SessionTableModel object) {
                return object.creationTime;
            }
        };
        table.addColumn(createColumn, "Create");
        
        DateCell lastAccessedCell = new DateCell(DATE_FORMAT);        
        Column<SessionTableModel, Date> lastAccessedColumn = new Column<SessionTableModel, Date>(lastAccessedCell) {
            @Override
            public Date getValue(SessionTableModel object) {
                return object.lastAccessedTime;
            }
        };
        table.addColumn(lastAccessedColumn, "Last accessed time");
        
        table.setColumnWidth(hostColumn, 50, Unit.PX);
        table.setColumnWidth(portColumn, 200, Unit.PX);
         
        SimplePager pager = new SimplePager();
        pager.setDisplay(table);
        
        VerticalPanel vp = new VerticalPanel();
        vp.add(table);
        vp.add(pager);
        
        initWidget(vp);
    }

    public void reset() {
        data.clear();
        table.setRowData(data);        
    }
    
    public void add(List<SessionTableModel> sessions) {
        data.addAll(sessions);
        table.setRowData(data);
    }
}
