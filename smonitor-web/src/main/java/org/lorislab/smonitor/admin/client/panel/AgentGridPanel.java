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

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import org.lorislab.smonitor.admin.client.model.AgentWrapper;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.column.EntityImageColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntitySpanColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityTextColumn;
import org.lorislab.smonitor.rs.admin.model.Agent;
import org.lorislab.smonitor.rs.model.ServerInfo;

/**
 *
 * @author Andrej Petras
 */
public class AgentGridPanel extends EntityDataGrid<Agent, AgentWrapper> {

    @Override
    protected AgentWrapper createWrapper() {
        return new AgentWrapper();
    }
      
    public void request(AgentWrapper item) {
        item.request = true;
        set(item);
    }

    public void error(String guid, String error) {
        AgentWrapper item = findById(guid);
        if (item != null) {
            item.clear();
            item.error = error;
            item.request = false;
            set(item);
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
                set(item);
            }
        }
    }

    public AgentWrapper update(final Agent data) {
        AgentWrapper result = null;
        if (data != null) {
            result = findById(data.getGuid());
            if (result != null) {
                result.clear();
                result.data = data;
                update();
            } else {
                result = addItem(data);
            }
        }
        return result;
    }

    @Override
    protected void createColumns() {

        Column colAction = addColumn(" ", true, new EntitySpanColumn<AgentWrapper, Boolean>() {
            @Override
            public Boolean getObject(AgentWrapper object) {
                return object.data.isEnabled();
            }

            @Override
            public String getValue(AgentWrapper object) {
                String result = null;
                if (!object.data.isEnabled()) {
                    result = "icon-record status-icon-gray";
                } else if (object.request) {
                    result = "icon-arrows-ccw animate-spin status-icon-blue";
                } else {
                    if (object.connected) {
                        result = "icon-record status-icon-green";
                    } else if (object.error != null) {
                        result = "icon-alert status-icon-red";
                    }
                }
                return result;
            }
        });

        setColumnWidth(colAction, 25, Unit.PX);

        Column colName = addColumn("Name", true, new EntityTextColumn<AgentWrapper>() {
            @Override
            public String getObject(AgentWrapper object) {
                return object.data.getName();
            }
        });
        setColumnWidth(colName, 200, Unit.PX);

        Column colServer = addColumn("Server", true, new EntityTextColumn<AgentWrapper>() {
            @Override
            public String getObject(AgentWrapper object) {
                return object.data.getServer();
            }
        });
        setColumnWidth(colServer, 200, Unit.PX);

        Column colStatus = addColumn("Status", true, new EntityTextColumn<AgentWrapper>() {
            @Override
            public String getObject(AgentWrapper object) {
                if (object.connected) {
                    return "Connected";
                }
                return object.error;
            }
        });
        setColumnWidth(colStatus, 200, Unit.PX);

    }
}
