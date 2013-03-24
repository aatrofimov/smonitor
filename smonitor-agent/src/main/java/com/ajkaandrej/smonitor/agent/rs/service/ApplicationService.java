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
import com.ajkaandrej.smonitor.agent.rs.model.Application;
import com.ajkaandrej.smonitor.agent.rs.model.ApplicationDetails;
import com.ajkaandrej.smonitor.agent.rs.model.AttributeDetails;
import com.ajkaandrej.smonitor.agent.rs.model.SessionDetails;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * The application rest-service.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Path("application")
public interface ApplicationService {

    @GET   
    @Produces(MediaType.APPLICATION_JSON)
    List<Application> getApplications(@QueryParam("remote") String remote) throws ServiceException;
        
    @GET   
    @Path("{host}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Application> getApplications(@PathParam("host") String host, @QueryParam("remote") String remote) throws ServiceException;
    
    @GET
    @Path("{host}/{application}")
    @Produces(MediaType.APPLICATION_JSON)
    ApplicationDetails getApplication(@PathParam("host") String host, @PathParam("application") String name, @QueryParam("remote") String remote) throws ServiceException;
    
    @GET
    @Path("{host}/{application}/{session}")
    @Produces(MediaType.APPLICATION_JSON)    
    SessionDetails getSession(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String id, @QueryParam("remote") String remote) throws ServiceException;

    @GET
    @Path("{host}/{application}/{session}/{attribute}")
    @Produces(MediaType.APPLICATION_JSON)
    AttributeDetails getAttribute(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String session, @PathParam("attribute") String name, @QueryParam("remote") String remote) throws ServiceException;
   
    @PUT
    @Path("{host}/{application}/{session}/{attribute}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    AttributeDetails updateAttribute(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String session, @PathParam("attribute") String name, AttributeDetails attribute, @QueryParam("remote") String remote) throws ServiceException;

    @DELETE
    @Path("{host}/{application}/{session}/{attribute}")
    void deleteAttribute(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String session, @PathParam("attribute") String name, @QueryParam("remote") String remote) throws ServiceException;    
        
}
