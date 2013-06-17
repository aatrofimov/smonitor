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

import org.lorislab.smonitor.admin.client.handler.TableRowHoverHandler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.RowHoverEvent;
import com.google.gwt.user.cellview.client.RowHoverEvent.HoveringScope;
import com.google.gwt.user.client.ui.Composite;
import java.util.Date;
import java.util.List;
import org.lorislab.smonitor.admin.client.model.AgentWrapper;
import org.lorislab.smonitor.admin.client.model.SessionWrapper;
import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.column.EntityDateColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityImageColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityTextColumn;
import org.lorislab.smonitor.rs.model.SessionInfo;

/**
 *
 * @author Andrej Petras
 */
public class SessionGridPanel extends Composite {

    /**
     * The data grid.
     */
    private EntityDataGrid<SessionInfo> dataGrid;
    private TableRowHoverHandler tableRowHoverHandler;

    /**
     * The default constructor.
     */
    public SessionGridPanel() {
        dataGrid = new EntityDataGrid<SessionInfo>();

        dataGrid.addRowHoverHandler(new RowHoverEvent.Handler() {
            @Override
            public void onRowHover(RowHoverEvent event) {
                if (tableRowHoverHandler != null) {
                    if (ConstantValues.EVENT_MOUSEOUT.equals((event.getBrowserEvent().getType()))) {
                        if (HoveringScope.CELL_HOVER.equals(event.getHoveringScope())) {
                            tableRowHoverHandler.onRowOut();
                        }
                    } else {
                        if (HoveringScope.ROW_HOVER.equals(event.getHoveringScope())) {
                            tableRowHoverHandler.onRowOver(event.getHoveringRow());
                        }
                    }
                }
            }
        });

        dataGrid.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED);
        initWidget(dataGrid);
        createColumns();
    }

    public void setTableRowHoverHandler(TableRowHoverHandler tableRowHoverHandler) {
        this.tableRowHoverHandler = tableRowHoverHandler;
    }

    /**
     * Resets the panel.
     */
    public void reset() {
        dataGrid.reset();
    }

    public void set(List<SessionInfo> sessions) {
        if (sessions != null) {
            dataGrid.addAll(sessions);
        }
    }

    private void createColumns() {
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
