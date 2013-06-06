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
package org.lorislabr.smonitor.agent.rs.client.executor;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.lorislab.smonitor.agent.security.AgentSecurity;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class AgentClientExecutor extends ApacheHttpClient4Executor {
    
    /**
     * The security key.
     */
    private String key;
    
/**
     * The default constructor.
     *
     * @param key the bandicoot key.
     */
    public AgentClientExecutor(String key) {
        this.key = key;
    }   
    
 /**
     * {@inheritDoc} 
     * 
     * Adds the security key to the header request.
     */
    @Override
    public ClientResponse execute(ClientRequest request) throws Exception {
        if (key != null) {
            request.header(AgentSecurity.HEADER_KEY, key);
        }
        return super.execute(request);
    }    
}
