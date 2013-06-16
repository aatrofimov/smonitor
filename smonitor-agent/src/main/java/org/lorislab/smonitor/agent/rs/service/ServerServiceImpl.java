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
package org.lorislab.smonitor.agent.rs.service;

import org.lorislab.smonitor.connector.service.ConnectorService;
import org.lorislab.smonitor.connector.factory.ConnectorServiceFactory;
import org.lorislab.smonitor.agent.rs.exception.AgentException;
import org.lorislab.smonitor.connector.model.HostDetails;
import org.lorislab.smonitor.connector.model.Server;

/**
 * The server rest-service implementation.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ServerServiceImpl implements ServerService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Server getServer() throws AgentException {
        Server result;
        ConnectorService service = ConnectorServiceFactory.getService();
        result = service.getServer();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HostDetails getHost(String host) throws AgentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
