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
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.RowHoverEvent;
import com.google.gwt.user.cellview.client.RowHoverEvent.HoveringScope;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.ArrayList;
import java.util.List;
import org.lorislab.smonitor.admin.client.model.AgentWrapper;
import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.column.EntityBooleanColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityImageColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityTextColumn;
import org.lorislab.smonitor.rs.admin.model.Agent;
import org.lorislab.smonitor.rs.model.ServerInfo;

/**
 *
 * @author Andrej Petras
 */
public class AgentGridPanel extends Composite {

    /**
     * The data grid.
     */
    private EntityDataGrid<AgentWrapper> dataGrid;
    private SingleSelectionModel<AgentWrapper> selectionModel;
    private TableRowHoverHandler tableRowHoverHandler;

    /**
     * The default constructor.
     */
    public AgentGridPanel() {
        dataGrid = new EntityDataGrid<AgentWrapper>();
        
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

        dataGrid.setWidth100();
//        dataGrid.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED);

        selectionModel = new SingleSelectionModel<AgentWrapper>();
        dataGrid.setSelectionModel(selectionModel);
        initWidget(dataGrid);
        createColumns();
    }

    public void setTableRowHoverHandler(TableRowHoverHandler tableRowHoverHandler) {
        this.tableRowHoverHandler = tableRowHoverHandler;
    }

    public AgentWrapper get(int index) {
        return dataGrid.get(index);
    }
    
    /**
     * Sets the data.
     *
     * @param model the model.
     * @param data the list of items.
     */
    public void set(List<Agent> value) {
        reset();
        List<AgentWrapper> list = new ArrayList<AgentWrapper>();
        if (value != null) {
            for (Agent agent : value) {
                AgentWrapper w = new AgentWrapper();
                w.agent = agent;
                w.request = true;
                list.add(w);
            }
        }
        dataGrid.addAll(list);
    }

    public void add(Agent data) {
        AgentWrapper w = new AgentWrapper();
        w.agent = data;
        dataGrid.add(w);
    }

    public void update(String guid, String error) {
        AgentWrapper item = find(guid);
        if (item != null) {
            item.clear();
            item.error = error;
            item.request = false;
            dataGrid.update(item);
        }
    }

    public void update(ServerInfo server) {
        AgentWrapper item = find(server.getGuid());
        if (item != null) {
            item.clear();
            item.connected = true;
            item.request = false;
            item.server = server;
            dataGrid.update(item);
        }
    }

    public void update(final Agent data) {
        if (data != null) {
            AgentWrapper item = find(data.getGuid());
            if (item != null) {
                item.clear();
                item.agent = data;
                dataGrid.update(item);
            } else {
                add(data);
            }
        }
    }
      
    public AgentWrapper find(final String guid) {
        AgentWrapper item = dataGrid.find(new EntityDataGrid.FilterItem<AgentWrapper>() {
            @Override
            public AgentWrapper isItem(AgentWrapper item) {
                if (item.agent.getGuid().equals(guid)) {
                    return item;
                }
                return null;
            }
        });
        return item;
    }

    public void remove(final String guid) {
        if (guid != null) {
            dataGrid.remove(new EntityDataGrid.FilterItem<AgentWrapper>() {
                @Override
                public AgentWrapper isItem(AgentWrapper item) {
                    if (item.agent.getGuid().equals(guid)) {
                        return item;
                    }
                    return null;
                }
            });
            selectionModel.clear();
        }
    }

    public void addSelectionChangeHandler(SelectionChangeEvent.Handler handler) {
        selectionModel.addSelectionChangeHandler(handler);
    }

    public AgentWrapper getSelectedObject() {
        return selectionModel.getSelectedObject();
    }

    /**
     * Resets the panel.
     */
    public void reset() {
        selectionModel.clear();
        dataGrid.reset();
    }

    private void createColumns() {
        EntityDataGrid<AgentWrapper> table = dataGrid;

        Column colAction = table.addColumn(" ", true, new EntityImageColumn<AgentWrapper, Boolean>() {
            @Override
            public Boolean getObject(AgentWrapper object) {
                return object.agent.isEnabled();
            }

            @Override
            public String getValue(AgentWrapper object) {
                if (object.request) {
                    return "images/empty.png";
                } else {
                    boolean status = getObject(object);
                    if (status) {
                        if (object.error == null) {
                            return "images/status_ok.png";
                        }
                        return "images/status_error.png";
                    }                    
                }
                return "images/status_disabled.png";
            }                        
        });
        
        table.setColumnWidth(colAction, 25, Style.Unit.PX);

        Column colName = table.addColumn("Name", true, new EntityTextColumn<AgentWrapper>() {
            @Override
            public String getObject(AgentWrapper object) {
                return object.agent.getName();
            }
        });
        table.setColumnWidth(colName, 200, Style.Unit.PX);

        Column colServer = table.addColumn("Server", true, new EntityTextColumn<AgentWrapper>() {
            @Override
            public String getObject(AgentWrapper object) {
                return object.agent.getServer();
            }
        });
        table.setColumnWidth(colServer, 200, Style.Unit.PX);
      
        Column colStatus = table.addColumn("Status", true, new EntityTextColumn<AgentWrapper>() {
            @Override
            public String getObject(AgentWrapper object) {
                if (object.connected) {
                    return "Connected";
                }
                return object.error;
            }
        });
        table.setColumnWidth(colStatus, 200, Style.Unit.PX);
        
        Column colRequest = table.addColumn("", false, new EntityImageColumn<AgentWrapper, Boolean>() {
            @Override
            public Boolean getObject(AgentWrapper object) {
                return object.request;
            }

            @Override
            public String getValue(AgentWrapper object) {
                boolean status = getObject(object);
                if (status) {
                    return "images/status.gif";
                }
                return "images/empty.png";
            }                        
        });   
        table.setColumnWidth(colRequest, 50, Style.Unit.PX);
    }
}
