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

import com.ajkaandrej.gwt.uc.ConstantValues;
import com.ajkaandrej.smonitor.admin.client.app.model.SessionTableModel;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.Comparator;
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

    private ListDataProvider<SessionTableModel> data = new ListDataProvider<SessionTableModel>();
    
    public SessionsTable() {
        table.setWidth(ConstantValues.PCT_100, true);
        table.setAutoHeaderRefreshDisabled(true);
        table.setAutoFooterRefreshDisabled(true);
        table.setPageSize(10);
        
        data.addDataDisplay(table);
        
        SingleSelectionModel<SessionTableModel> ssm = new SingleSelectionModel<SessionTableModel>();
        table.setSelectionModel(ssm);


        TextColumn<SessionTableModel> hostColumn = new TextColumn<SessionTableModel>() {
            @Override
            public String getValue(SessionTableModel object) {
                return object.hostName;
            }
        };
        hostColumn.setSortable(true);

        ListHandler<SessionTableModel> hostColumnSortHandler = new ListHandler<SessionTableModel>(data.getList());
        hostColumnSortHandler.setComparator(hostColumn, new Comparator<SessionTableModel>() {
            @Override
            public int compare(SessionTableModel o1, SessionTableModel o2) {
                if (o1 == o2) {
                    return 0;
                }

                // Compare the name columns.
                if (o1 != null) {
                    return (o2 != null) ? o1.hostName.compareTo(o2.hostName) : 1;
                }
                return -1;
            }
        });
        table.addColumnSortHandler(hostColumnSortHandler);

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
        createColumn.setSortable(true);
        
        ListHandler<SessionTableModel> createColumnSortHandler = new ListHandler<SessionTableModel>(data.getList());
        createColumnSortHandler.setComparator(createColumn, new Comparator<SessionTableModel>() {
            @Override
            public int compare(SessionTableModel o1, SessionTableModel o2) {
                if (o1 == o2) {
                    return 0;
                }

                // Compare the name columns.
                if (o1 != null) {
                    return (o2 != null) ? o1.creationTime.compareTo(o2.creationTime) : 1;
                }
                return -1;
            }
        });
        table.addColumnSortHandler(createColumnSortHandler);
        
        table.addColumn(createColumn, "Create");

        DateCell lastAccessedCell = new DateCell(DATE_FORMAT);
        Column<SessionTableModel, Date> lastAccessedColumn = new Column<SessionTableModel, Date>(lastAccessedCell) {
            @Override
            public Date getValue(SessionTableModel object) {
                return object.lastAccessedTime;
            }
        };
        table.addColumn(lastAccessedColumn, "Last accessed");

        table.setColumnWidth(hostColumn, 200, Unit.PX);
        table.setColumnWidth(createColumn, 180, Unit.PX);
        table.setColumnWidth(lastAccessedColumn, 180, Unit.PX);
        table.setColumnWidth(portColumn, 50, Unit.PX);

        SimplePager pager = new SimplePager();
        pager.setDisplay(table);
        
        VerticalPanel vp = new VerticalPanel();
        vp.add(table);
        vp.add(pager);

        initWidget(vp);
    }

    public void reset() {
        data.getList().clear();
    }

    public void add(List<SessionTableModel> sessions) {
        data.getList().addAll(sessions);
    }
}
