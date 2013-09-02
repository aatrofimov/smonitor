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

import org.lorislab.smonitor.connector.factory.ConnectorServiceFactory;
import org.lorislab.smonitor.agent.rs.exception.AgentException;
import org.lorislab.smonitor.connector.service.ConnectorService;
import java.util.List;
import javax.ws.rs.PathParam;
import org.lorislab.smonitor.connector.model.SessionCriteria;
import org.lorislab.smonitor.connector.model.Application;
import org.lorislab.smonitor.connector.model.ApplicationDetails;
import org.lorislab.smonitor.connector.model.AttributeDetails;
import org.lorislab.smonitor.connector.model.Session;
import org.lorislab.smonitor.connector.model.SessionDetails;

/**
 * The implementation of application service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ApplicationServiceImpl implements ApplicationService {

    /**
     * {@inheritDoc}
     */    
    @Override
    public List<Session> findSessionByCriteria(SessionCriteria criteria) throws AgentException {
        List<Session> result;
        ConnectorService service = ConnectorServiceFactory.getService();
        result = service.findSessionByCriteria(criteria);
        return result;
    }
    
    /**
     * {@inheritDoc}
     */     
    @Override
    public Session getSession(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String id) throws AgentException {
        Session result;
        ConnectorService service = ConnectorServiceFactory.getService();
        result = service.getSession(host, application, id);
        return result;
    }

    /**
     * {@inheritDoc}
     */     
    @Override
    public Session deleteSession(@PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String id) throws AgentException {
        Session result;
        ConnectorService service = ConnectorServiceFactory.getService();
        result = service.deleteSession(host, application, id);
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications(String host) throws AgentException {
        List<Application> result;
        ConnectorService service = ConnectorServiceFactory.getService();
        result = service.getApplications(host);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications() throws AgentException {
        List<Application> result;
        ConnectorService service = ConnectorServiceFactory.getService();
        result = service.getApplications();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationDetails getApplication(String host, String name) throws AgentException {
        ApplicationDetails result;
        ConnectorService service = ConnectorServiceFactory.getService();
        result = service.getApplicationDetails(host, name);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionDetails getSessionDetails(String host, String application, String id) throws AgentException {
        SessionDetails result;
        ConnectorService service = ConnectorServiceFactory.getService();
        result = service.getSessionDetails(host, application, id);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeDetails getAttribute(String host, String application, String session, String name) throws AgentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeDetails updateAttribute(String host, String application, String session, String name, AttributeDetails attribute) throws AgentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAttribute(String host, String application, String session, String name) throws AgentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
