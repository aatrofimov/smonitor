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
package org.lorislabr.smonitor.agent.rs.client.service;

import org.lorislab.smonitor.agent.rs.exception.AgentException;
import org.lorislab.smonitor.agent.rs.model.HostDetails;
import org.lorislab.smonitor.agent.rs.model.Server;
import org.lorislab.smonitor.agent.rs.service.ServerService;
import org.lorislabr.smonitor.agent.rs.client.AbstractClientService;

/**
 * The server service client.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ServerClientService extends AbstractClientService<ServerService> implements ServerService {

    /**
     * The default constructor.
     *
     * @param server the server.
     */
    public ServerClientService(String server, String key) {
        super(ServerService.class, server, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Server getServer() throws AgentException {
        return getService().getServer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HostDetails getHost(String host) throws AgentException {
        return getService().getHost(host);
    }
}
