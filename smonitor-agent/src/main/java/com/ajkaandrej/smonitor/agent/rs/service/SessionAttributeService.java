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
import com.ajkaandrej.smonitor.agent.rs.model.AttributeDetails;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Path("session/attribute")
public interface SessionAttributeService {

    @GET
    @Path("{session}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    AttributeDetails getAttribute(@PathParam("session") String session, @PathParam("name") String name) throws ServiceException;
   
    @PUT
    @Path("{session}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    AttributeDetails updateAttribute(@PathParam("session") String session, AttributeDetails attribute) throws ServiceException;

    @DELETE
    @Path("{session}/{name}")
    void deleteAttribute(@PathParam("session") String session, @PathParam("name") String name) throws ServiceException;
}
