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

import org.lorislab.smonitor.agent.mapper.ObjectMapper;
import org.lorislab.smonitor.connector.factory.ConnectorServiceFactory;
import org.lorislab.smonitor.agent.rs.exception.AgentException;
import org.lorislab.smonitor.agent.rs.model.Application;
import org.lorislab.smonitor.agent.rs.model.ApplicationDetails;
import org.lorislab.smonitor.agent.rs.model.AttributeDetails;
import org.lorislab.smonitor.agent.rs.model.SessionDetails;
import org.lorislab.smonitor.connector.service.ConnectorService;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.TypeToken;

/**
 * The implementation of application service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationServiceImpl implements ApplicationService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications(String host) throws AgentException {
        List<Application> result;
        ConnectorService service = ConnectorServiceFactory.getService();
        Type listType = new TypeToken<List<Application>>() {
        }.getType();
        result = ObjectMapper.getInstance().map(service.getApplications(host), listType);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications() throws AgentException {
        List<Application> result;
        ConnectorService service = ConnectorServiceFactory.getService();
        Type listType = new TypeToken<List<Application>>() {
        }.getType();
        result = ObjectMapper.getInstance().map(service.getApplications(), listType);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationDetails getApplication(String host, String name) throws AgentException {
        ApplicationDetails result;
        ConnectorService service = ConnectorServiceFactory.getService();
        result = ObjectMapper.getInstance().map(service.getApplicationDetails(host, name), ApplicationDetails.class);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionDetails getSession(String host, String application, String id) throws AgentException {
        SessionDetails result;
        ConnectorService service = ConnectorServiceFactory.getService();
        result = ObjectMapper.getInstance().map(service.getSessionDetails(host, application, id), SessionDetails.class);
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
