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
package org.lorislab.smonitor.rs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lorislab.smonitor.connector.model.Application;
import org.lorislab.smonitor.connector.model.Host;
import org.lorislab.smonitor.connector.model.Server;
import org.lorislab.smonitor.datastore.model.AgentData;
import org.lorislab.smonitor.datastore.service.AgentDataService;
import org.lorislab.smonitor.rs.model.ServerApplication;
import org.lorislab.smonitor.rs.model.ServerInfo;
import org.lorislab.smonitor.service.ServiceFactory;
import org.lorislab.smonitor.rs.client.RSClientUtil;
import org.lorislab.smonitor.agent.rs.client.service.ServerClientService;
import org.lorislab.smonitor.agent.rs.exception.AgentException;

/**
 *
 * @author Andrej Petras
 */
public final class ServerServiceImpl implements ServerService {

    private static final Logger LOGGER = Logger.getLogger(ServerServiceImpl.class.getName());
    
    private final AgentDataService service;

    public ServerServiceImpl() {
        service = ServiceFactory.getAgentDataService();
    }

    @Override
    public ServerInfo getServer(String guid) throws Exception {
        ServerInfo result = null;
        AgentData data = service.findByBuid(guid);
        if (data != null) {
            
                     
            Server server = null;
            try {
                ServerClientService serverService = new ServerClientService(data.getServer(), data.getKey());   
                server = serverService.getServer();
            } catch (Exception ex) {
                RSClientUtil.handleException(guid, ex);
            }
            
            if (server != null) {
                result = new ServerInfo();
                result.guid = guid;
                result.id = server.getId();
                result.name = server.getName();
                
                result.applications = new ArrayList<ServerApplication>();
                if (server.getHosts() != null) {
                    for (Host host : server.getHosts()) {
                        if (host.getApplications() != null) {
                            for (Application app : host.getApplications()) {
                                ServerApplication sa = new ServerApplication();
                                sa.id = app.getId();
                                sa.name = app.getName();
                                sa.host = app.getHost();
                                result.applications.add(sa);
                            }
                        }
                    }
                }
            } else {
                LOGGER.log(Level.WARNING, "There is no server information for the {0}", data.getName());                
            }
        }
        return result;
    }
}
