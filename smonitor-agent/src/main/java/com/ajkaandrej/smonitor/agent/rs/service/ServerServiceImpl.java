/*
 * Copyright 2013 Andrej_Petras.
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
package com.ajkaandrej.smonitor.agent.rs.service;

import com.ajkaandrej.smonitor.connector.service.ConnectorService;
import com.ajkaandrej.smonitor.connector.factory.ConnectorServiceFactory;
import com.ajkaandrej.smonitor.agent.mapper.ObjectMapper;
import com.ajkaandrej.smonitor.agent.rs.client.ServerClientService;
import com.ajkaandrej.smonitor.agent.rs.exception.ServiceException;
import com.ajkaandrej.smonitor.agent.rs.model.HostDetails;
import com.ajkaandrej.smonitor.agent.rs.model.Server;

/**
 * The server rest-service implementation.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ServerServiceImpl extends AbstractService implements ServerService {

    @Override
    public Server getServer(String remote) throws ServiceException {
        Server result;
        if (remote == null || remote.isEmpty()) {
            ConnectorService service = ConnectorServiceFactory.getService();
            result = ObjectMapper.getInstance().map(service.getServer(), Server.class);
            createServerRequest(result);
        } else {
            ServerClientService client = new ServerClientService(remote);
            result = client.getServer();
        }
        return result;
    }

    @Override
    public HostDetails getHost(String host, String remote) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
