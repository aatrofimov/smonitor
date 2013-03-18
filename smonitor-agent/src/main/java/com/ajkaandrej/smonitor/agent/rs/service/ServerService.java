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

import com.ajkaandrej.smonitor.agent.rs.exception.ServiceException;
import com.ajkaandrej.smonitor.agent.rs.model.HostDetails;
import com.ajkaandrej.smonitor.agent.rs.model.Server;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * The server rest-service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Path("server")
public interface ServerService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Server getServer(@QueryParam("remote") String remote) throws ServiceException;

    @GET
    @Path("{host}")
    @Produces(MediaType.APPLICATION_JSON)
    HostDetails getHost(@PathParam("host") String host, @QueryParam("remote") String remote) throws ServiceException;
    
    
}