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

import com.ajkaandrej.smonitor.agent.rs.model.Session;
import com.google.gwt.cell.client.DateCell;
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
    
    private CellTable<Session> table = new CellTable<Session>();
    
    
    public SessionsTable() {
        table.setWidth("100%", true);
        table.setAutoHeaderRefreshDisabled(true);
        table.setAutoFooterRefreshDisabled(true);
        table.setPageSize(3);
        
        SingleSelectionModel<Session> ssm = new SingleSelectionModel<Session>();
        table.setSelectionModel(ssm);

        

        TextColumn<Session> idColumn = new TextColumn<Session>() {
            @Override
            public String getValue(Session object) {
                return object.getId();
            }
        };
        table.addColumn(idColumn, "ID");

        TextColumn<Session> userColumn = new TextColumn<Session>() {
            @Override
            public String getValue(Session object) {
                return object.getUser();
            }
        };
        table.addColumn(userColumn, "User");
        
        DateCell dateCell = new DateCell(DATE_FORMAT);        
        Column<Session, Date> createColumn = new Column<Session, Date>(dateCell) {
            @Override
            public Date getValue(Session object) {
                return object.getCreationTime();
            }
        };
        table.addColumn(createColumn, "Create");
        
        DateCell lastAccessedCell = new DateCell(DATE_FORMAT);        
        Column<Session, Date> lastAccessedColumn = new Column<Session, Date>(lastAccessedCell) {
            @Override
            public Date getValue(Session object) {
                return object.getLastAccessedTime();
            }
        };
        table.addColumn(lastAccessedColumn, "Last accessed time");
        
        SimplePager pager = new SimplePager();
        pager.setDisplay(table);
        
        VerticalPanel vp = new VerticalPanel();
        vp.add(table);
        vp.add(pager);
        
        initWidget(vp);
    }

    public void reset() {
        table.setRowData(new ArrayList<Session>());        
    }
    
    public void load(List<Session> sessions) {
        table.setRowData(sessions);
    }
}
