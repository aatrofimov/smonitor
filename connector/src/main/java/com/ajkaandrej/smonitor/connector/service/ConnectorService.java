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
package com.ajkaandrej.smonitor.connector.service;

import com.ajkaandrej.smonitor.connector.model.Application;
import com.ajkaandrej.smonitor.connector.model.ApplicationDetails;
import com.ajkaandrej.smonitor.connector.model.AttributeDetails;
import com.ajkaandrej.smonitor.connector.model.Host;
import com.ajkaandrej.smonitor.connector.model.Server;
import com.ajkaandrej.smonitor.connector.model.Session;
import com.ajkaandrej.smonitor.connector.model.SessionDetails;
import java.util.List;

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
     * Gest the session details.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @return the session details.
     */
    SessionDetails getSessionDetails(String host, String application, String session);

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
}
