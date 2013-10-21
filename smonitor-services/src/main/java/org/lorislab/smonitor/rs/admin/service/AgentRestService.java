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
package org.lorislab.smonitor.rs.admin.service;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lorislab.smonitor.rs.admin.model.Agent;
import org.lorislab.smonitor.rs.admin.model.ChangeAgentKeyRequest;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Path("agent")
public interface AgentRestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Agent> get() throws Exception;
       
    @GET
    @Path("{guid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Agent get(@PathParam("guid") String guid) throws Exception;
        
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Agent create() throws Exception;
    
    @POST
    @Path("{guid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Agent update(@PathParam("guid") String guid, Agent agent) throws Exception;
    
    @DELETE
    @Path("{guid}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("guid") String guid) throws Exception;
    
    @POST
    @Path("password")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public boolean changePassword(ChangeAgentKeyRequest data) throws Exception;    
}
