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
package org.lorislab.smonitor.agent.rs.client.service;

import org.lorislab.smonitor.agent.rs.service.ApplicationService;
import java.util.List;
import org.lorislab.smonitor.agent.rs.exception.AgentException;
import org.lorislab.smonitor.connector.model.Application;
import org.lorislab.smonitor.connector.model.ApplicationDetails;
import org.lorislab.smonitor.connector.model.AttributeDetails;
import org.lorislab.smonitor.connector.model.Session;
import org.lorislab.smonitor.connector.model.SessionDetails;
import org.lorislab.smonitor.connector.model.SessionCriteria;
import org.lorislab.smonitor.agent.rs.client.AbstractClientService;

/**
 * The application client service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ApplicationClientService extends AbstractClientService<ApplicationService> implements ApplicationService {

    /**
     * The default constructor.
     *
     * @param server the server.
     */
    public ApplicationClientService(String server, String key) {
        super(ApplicationService.class, server, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications() throws AgentException {
        return getService().getApplications(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationDetails getApplication(String host, String name) throws AgentException {
        ApplicationDetails result = getService().getApplication(host, name);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionDetails getSessionDetails(String host, String application, String id) throws AgentException {
        SessionDetails result = getService().getSessionDetails(host, application, id);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session getSession(String host, String application, String id) throws AgentException {
        Session result = getService().getSession(host, application, id);
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Session deleteSession(String host, String application, String id) throws AgentException {
        Session result = getService().deleteSession(host, application, id);
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeDetails getAttribute(String host, String application, String session, String name) throws AgentException {
        AttributeDetails result = getService().getAttribute(host, application, session, name);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeDetails updateAttribute(String host, String application, String session, String name, AttributeDetails attribute) throws AgentException {
        AttributeDetails result = getService().updateAttribute(host, application, session, name, attribute);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAttribute(String host, String application, String session, String name) throws AgentException {
        getService().deleteAttribute(host, application, session, name);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications(String host) throws AgentException {
        return getService().getApplications(host);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Session> findSessionByCriteria(SessionCriteria criteria) throws AgentException {
        return getService().findSessionByCriteria(criteria);
    }
}
