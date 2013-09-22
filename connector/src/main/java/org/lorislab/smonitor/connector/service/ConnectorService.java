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
package org.lorislab.smonitor.connector.service;

import org.lorislab.smonitor.connector.model.Application;
import org.lorislab.smonitor.connector.model.ApplicationDetails;
import org.lorislab.smonitor.connector.model.AttributeDetails;
import org.lorislab.smonitor.connector.model.Host;
import org.lorislab.smonitor.connector.model.Server;
import org.lorislab.smonitor.connector.model.Session;
import org.lorislab.smonitor.connector.model.SessionDetails;
import java.util.List;
import org.lorislab.smonitor.connector.model.SessionCriteria;

/**
 * The connector service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface ConnectorService {

    /**
     * Gets the version.
     *
     * @return the version.
     */
    String getVersion();

    /**
     * Gets the name.
     *
     * @return the name.
     */
    String getName();

    /**
     * Gets the server.
     *
     * @return the server.
     */
    Server getServer();

    /**
     * Gets the host by name.
     *
     * @param name the host name.
     * @return the host.
     */
    Host getHost(String name);

    /**
     * Gets the list of hosts.
     *
     * @return the list of hosts.
     */
    List<Host> getHosts();

    /**
     * Gets the list of applications.
     *
     * @return the list of applications.
     */
    List<Application> getApplications();

    /**
     * Gets the list of applications for the host.
     *
     * @param host the host.
     * @return the list of applications corresponding to the host.
     */
    List<Application> getApplications(String host);

    /**
     * Gets the application details.
     *
     * @param host the host.
     * @param application the application name.
     * @return the application details.
     */
    ApplicationDetails getApplicationDetails(String host, String application);

    /**
     * Gets the list of session for the application.
     *
     * @param host the host.
     * @param application the application name.
     * @return the list of session.
     */
    List<Session> getSessions(String host, String application);

    /**
     * Finds the session by search criteria.
     *
     * @param criteria the search criteria.
     * @return the list of session corresponding to the criteria.
     */
    List<Session> findSessionByCriteria(SessionCriteria criteria);

    /**
     * Gest the session details.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @return the session details.
     */
    SessionDetails getSessionDetails(String host, String application, String session);

    /**
     * Gets the session.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @return the session details.
     */
    Session getSession(String host, String application, String session);

    /**
     * Deletes the session.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @return the session deleted session.
     */
    Session deleteSession(String host, String webApp, String sessionId);

    /**
     * Gets the attribute details.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @param attribute the attribute.
     * @return the attribute details.
     */
    AttributeDetails getAttributeDetails(String host, String application, String session, String attribute);
    
    void start();
    
    void shutdown();
}
