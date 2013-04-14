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
import org.lorislab.smonitor.agent.rs.client.ApplicationClientService;
import org.lorislab.smonitor.connector.factory.ConnectorServiceFactory;
import org.lorislab.smonitor.agent.rs.exception.ServiceException;
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
public class ApplicationServiceImpl extends AbstractService implements ApplicationService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications(String host, String remote) throws ServiceException {
        System.out.println("host " + host + " remote " + remote);
        List<Application> result;
        if (remote == null || remote.isEmpty()) {
            ConnectorService service = ConnectorServiceFactory.getService();
            Type listType = new TypeToken<List<Application>>() {
            }.getType();
            result = ObjectMapper.getInstance().map(service.getApplications(host), listType);
        } else {
            ApplicationClientService client = new ApplicationClientService(remote);
            result = client.getApplications();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications(String remote) throws ServiceException {
        System.out.println("Remote " + remote);
        List<Application> result;
        if (remote == null || remote.isEmpty()) {
            ConnectorService service = ConnectorServiceFactory.getService();
            Type listType = new TypeToken<List<Application>>() {
            }.getType();
            result = ObjectMapper.getInstance().map(service.getApplications(), listType);
        } else {
            ApplicationClientService client = new ApplicationClientService(remote);
            result = client.getApplications();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationDetails getApplication(String host, String name, String remote) throws ServiceException {
        ApplicationDetails result;
        System.out.println("host " + host + " application " + name + " remote " + remote);
        if (remote == null || remote.isEmpty()) {
            ConnectorService service = ConnectorServiceFactory.getService();
            result = ObjectMapper.getInstance().map(service.getApplicationDetails(host, name), ApplicationDetails.class);
            createServerRequest(result);
        } else {
            ApplicationClientService client = new ApplicationClientService(remote);
            result = client.getApplication(host, name);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionDetails getSession(String host, String application, String id, String remote) throws ServiceException {
        System.out.println("host " + host + " application " + application + " id " + id + " remote " + remote);
        SessionDetails result;
        if (remote == null || remote.isEmpty()) {
            ConnectorService service = ConnectorServiceFactory.getService();
            result = ObjectMapper.getInstance().map(service.getSessionDetails(host, application, id), SessionDetails.class);
            createServerRequest(result);
        } else {
            ApplicationClientService client = new ApplicationClientService(remote);
            result = client.getSession(host, application, id);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeDetails getAttribute(String host, String application, String session, String name, String remote) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeDetails updateAttribute(String host, String application, String session, String name, AttributeDetails attribute, String remote) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAttribute(String host, String application, String session, String name, String remote) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}