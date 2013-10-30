/*
 * Copyright 2013 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.smonitor.agent.rs.client;

import org.lorislab.smonitor.agent.rs.client.executor.AgentClientExecutor;
import org.jboss.resteasy.client.ProxyFactory;

/**
 * The abstract client service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class AbstractClientService<T> {

    /**
     * The agent monitor URL.
     */
    private static final String AGENT_SERVLET = "/smonitor-agent";
    /**
     * The service.
     */
    private T service;

    /**
     * The default constructor.
     *
     * @param clazz the service class.
     * @param server the server destination.
     */
    public AbstractClientService(Class<T> clazz, String server, String key) {
        AgentClientExecutor executor = new AgentClientExecutor(key);
        service = ProxyFactory.create(clazz, server + AGENT_SERVLET, executor);
    }

    /**
     * Gets the service.
     *
     * @return the service.
     */
    protected T getService() {
        return service;
    }

}
