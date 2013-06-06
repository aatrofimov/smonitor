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
package org.lorislab.smonitor.agent.rs.provider;

import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.jboss.resteasy.annotations.interception.SecurityPrecedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.lorislab.smonitor.agent.security.AgentSecurity;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Provider
@SecurityPrecedence
@ServerInterceptor
public class SecurityProvider implements PreProcessInterceptor {

    @Override
    public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {        
        ServerResponse result = null;
        List<String> keys = request.getHttpHeaders().getRequestHeader(AgentSecurity.HEADER_KEY);
        if (keys == null || keys.size() != 1 || !SecurityService.checkKey(keys.get(0))) {
            result = (ServerResponse) Response.status(Response.Status.FORBIDDEN).build();  
        }        
        return result;
    }
    
}
