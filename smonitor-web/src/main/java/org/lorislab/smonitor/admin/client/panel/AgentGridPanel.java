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
import java.util.ArrayList;
import java.util.List;
import org.lorislab.smonitor.admin.client.model.AgentWrapper;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.column.EntityImageColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityTextColumn;
import org.lorislab.smonitor.rs.admin.model.Agent;
import org.lorislab.smonitor.rs.model.ServerInfo;

/**
 *
 * @author Andrej Petras
 */
public class AgentGridPanel extends AbstractGridPanel<AgentWrapper> {

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

    public AgentWrapper add(Agent data) {
        AgentWrapper result = new AgentWrapper();
        result.agent = data;
        return add(result);
    }

    public void request(AgentWrapper item) {
        item.request = true;
        update(item);
    }

    public void error(String guid, String error) {
        AgentWrapper item = findById(guid);
        if (item != null) {
            item.clear();
            item.error = error;
            item.request = false;
            update(item);
        }
    }

    public void update(ServerInfo server) {
        if (server != null) {
            AgentWrapper item = findById(server.getGuid());
            if (item != null) {
                item.clear();
                item.connected = true;
                item.request = false;
                item.server = server;
                update(item);
            }
        }
    }

    public AgentWrapper update(final Agent data) {
        AgentWrapper result = null;
        if (data != null) {
            result = findById(data.getGuid());
            if (result != null) {
                result.clear();
                result.agent = data;
                update(result);
            } else {
                result = add(data);
            }
        }
        return result;
    }

    @Override
    public AgentWrapper findById(final Object guid) {
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

    @Override
    protected void createColumns() {
        EntityDataGrid<AgentWrapper> table = dataGrid;

        Column colAction = table.addColumn(" ", true, new EntityImageColumn<AgentWrapper, Boolean>() {
            @Override
            public Boolean getObject(AgentWrapper object) {
                return object.agent.isEnabled();
            }

            @Override
            public String getValue(AgentWrapper object) {
                String result = "images/empty.png";
                if (!object.agent.isEnabled()) {
                    result = "images/status_disabled.png";
                } else if (object.request) {
                    result = "images/status.gif";
                } else {
                    if (object.connected) {
                        result = "images/status_ok.png";
                    } else if (object.error != null) {
                        result = "images/status_error.png";
                    }
                }
                return result;
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

    }
}
