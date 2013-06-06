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
import org.lorislab.smonitor.agent.rs.model.Application;
import org.lorislab.smonitor.agent.rs.model.Host;
import org.lorislab.smonitor.agent.rs.model.Server;
import org.lorislab.smonitor.datastore.model.AgentData;
import org.lorislab.smonitor.datastore.service.AgentDataService;
import org.lorislab.smonitor.rs.exception.ServiceException;
import org.lorislab.smonitor.rs.model.ServerApplication;
import org.lorislab.smonitor.rs.model.ServerInfo;
import org.lorislab.smonitor.service.ServiceFactory;
import org.lorislabr.smonitor.agent.rs.client.service.ServerClientService;

/**
 *
 * @author Andrej Petras
 */
public class ServerServiceImpl implements ServerService {

    private static final Logger LOGGER = Logger.getLogger(ServerServiceImpl.class.getName());
    
    private AgentDataService service;

    public ServerServiceImpl() {
        service = ServiceFactory.getAgentDataService();
    }

    @Override
    public ServerInfo getServer(String guid) {
        ServerInfo result = null;
        AgentData data = service.findByBuid(guid);
        if (data != null) {
            
            ServerClientService serverService = new ServerClientService(data.getServer(), data.getKey());            
            Server server = null;
            try {
                server = serverService.getServer();
            } catch (Exception ex) {
                throw new ServiceException(guid, "Could not connect to the server " + data.getServer(), ex);
            }
            
            if (server != null) {
                result = new ServerInfo();
                result.setGuid(guid);
                result.setId(server.getId());
                result.setName(server.getName());
                
                List<ServerApplication> apps = new ArrayList<ServerApplication>();
                if (server.getHosts() != null) {
                    for (Host host : server.getHosts()) {
                        if (host.getApplications() != null) {
                            for (Application app : host.getApplications()) {
                                ServerApplication sa = new ServerApplication();
                                sa.setId(app.getId());
                                sa.setName(app.getName());
                                sa.setHost(app.getHost());
                                apps.add(sa);
                            }
                        }
                    }
                }
                result.setApplications(apps);
            } else {
                LOGGER.log(Level.WARNING, "There is no server information for the {0}", data.getName());                
            }
        }
        return result;
    }
}
