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
package org.lorislab.smonitor.agent.rs.service;

import org.lorislab.smonitor.agent.rs.model.ServerContext;
import org.lorislab.smonitor.agent.rs.model.ServerRequest;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 * The abstract service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class AbstractService {

    /**
     * The HTTP SERVLET request.
     */
    @Context
    private HttpServletRequest context;

    /**
     * Creates the server context.
     *
     * @param request the server request.
     */
    protected void createServerRequest(ServerRequest request) {
        if (request != null) {
            ServerContext result = request.getServerContext();
            result.setScheme(context.getScheme());
            result.setHostName(context.getServerName());
            result.setPort(context.getServerPort());
            result.setRemote(null);
        }
    }
}
