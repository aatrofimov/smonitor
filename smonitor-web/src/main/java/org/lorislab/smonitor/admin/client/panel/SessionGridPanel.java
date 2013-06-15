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
import org.lorislab.smonitor.admin.client.model.SessionWrapper;
import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.column.EntityTextColumn;

/**
 *
 * @author Andrej Petras
 */
public class SessionGridPanel extends Composite {

    /**
     * The data grid.
     */
    private EntityDataGrid<SessionWrapper> dataGrid;

    private TableRowHoverHandler tableRowHoverHandler;

    /**
     * The default constructor.
     */
    public SessionGridPanel() {
        dataGrid = new EntityDataGrid<SessionWrapper>();

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

    private void createColumns() {
        EntityDataGrid<SessionWrapper> table = dataGrid;


        Column colName = table.addColumn("Id", true, new EntityTextColumn<SessionWrapper>() {
            @Override
            public String getObject(SessionWrapper object) {
                return "ID";
            }
        });
        table.setColumnWidth(colName, 200, Style.Unit.PX);

       
    }
}
