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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.ArrayList;
import java.util.List;
import org.lorislab.smonitor.admin.client.model.AgentWrapper;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.column.EntityBooleanColumn;
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

    /**
     * The default constructor.
     */
    public AgentGridPanel() {
        dataGrid = new EntityDataGrid<AgentWrapper>();
        dataGrid.setWidth100();
        selectionModel = new SingleSelectionModel<AgentWrapper>();
        dataGrid.setSelectionModel(selectionModel);
        initWidget(dataGrid);
        createColumns();
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
            dataGrid.update(item);
        }
    }

    public void update(ServerInfo server) {
        AgentWrapper item = find(server.getGuid());
        if (item != null) {
            item.clear();
            item.connected = true;
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

        Column colEnabled = table.addColumn("Enabled", true, new EntityBooleanColumn<AgentWrapper>() {
            @Override
            public Boolean getObject(AgentWrapper object) {
                return object.agent.isEnabled();
            }
        });
        table.setColumnWidth(colEnabled, 50, Style.Unit.PX);

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
    }
}
