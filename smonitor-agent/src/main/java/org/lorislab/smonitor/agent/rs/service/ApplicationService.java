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

import org.lorislab.smonitor.agent.rs.exception.AgentException;
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
import org.lorislab.smonitor.connector.model.SessionCriteria;
import org.lorislab.smonitor.connector.model.Application;
import org.lorislab.smonitor.connector.model.ApplicationDetails;
import org.lorislab.smonitor.connector.model.AttributeDetails;
import org.lorislab.smonitor.connector.model.Session;
import org.lorislab.smonitor.connector.model.SessionDetails;

/**
 * The application rest-service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Path("service")
public interface ApplicationService {

    /**
     * Finds the sessions by criteria.
     *
     * @param criteria the session search criteria.
     * @return the list of session corresponding to the search criteria.
     * @throws AgentException if the method fails.
     */
    @POST
    @Path("session/search")
    @Produces(MediaType.APPLICATION_JSON)
    List<Session> findSessionByCriteria(SessionCriteria criteria) throws AgentException;

    /**
     * Gets the session.
     *
     * @param host the host.
     * @param application the application.
     * @param id the session id.
     * @return the session.
     * @throws AgentException if the method fails.
     */
    @POST
    @Path("session/get/{host}/{application}/{session}")
    @Produces(MediaType.APPLICATION_JSON)
    Session getSession(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String id) throws AgentException;

    /**
     * Gets the list of applications.
     *
     * @return the list of applications.
     * @throws AgentException if the method fails.
     */
    @GET
    @Path("application/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<Application> getApplications() throws AgentException;

    /**
     * Gets the applications.
     *
     * @param host the host.
     * @return the list of applications for the host.
     * @throws AgentException if the method fails.
     */
    @GET
    @Path("application/get/{host}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Application> getApplications(@PathParam("host") String host) throws AgentException;

    /**
     * Gets the application details.
     *
     * @param host the host.
     * @param name the name.
     * @return the application details.
     * @throws AgentException if the method fails.
     */
    @GET
    @Path("application/details/{host}/{application}")
    @Produces(MediaType.APPLICATION_JSON)
    ApplicationDetails getApplication(@PathParam("host") String host, @PathParam("application") String name) throws AgentException;

    /**
     * Gets the session details.
     *
     * @param host the host.
     * @param application the application.
     * @param id the id.
     * @return the session details.
     * @throws AgentException if the method fails.
     */
    @GET
    @Path("session/details/{host}/{application}/{session}")
    @Produces(MediaType.APPLICATION_JSON)
    SessionDetails getSessionDetails(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String id) throws AgentException;

    /**
     * Gets the attribute details.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @param name the name.
     * @return the attribute details.
     * @throws AgentException if the method fails.
     */
    @GET
    @Path("attribute/details/{host}/{application}/{session}/{attribute}")
    @Produces(MediaType.APPLICATION_JSON)
    AttributeDetails getAttribute(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String session, @PathParam("attribute") String name) throws AgentException;

    /**
     * Updates the attribute.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @param name the name.
     * @param attribute the attribute.
     * @return the updated attribute details.
     * @throws AgentException if the method fails.
     */
    @PUT
    @Path("attribute/details/{host}/{application}/{session}/{attribute}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    AttributeDetails updateAttribute(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String session, @PathParam("attribute") String name, AttributeDetails attribute) throws AgentException;

    /**
     * Deletes the attribute.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @param name the name.
     * @throws AgentException if the method fails.
     */
    @DELETE
    @Path("attribute/details/{host}/{application}/{session}/{attribute}")
    void deleteAttribute(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String session, @PathParam("attribute") String name) throws AgentException;
}
