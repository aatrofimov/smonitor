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
package org.lorislab.smonitor.connector.tomcat.service;

import org.lorislab.smonitor.connector.model.Application;
import org.lorislab.smonitor.connector.model.ApplicationDetails;
import org.lorislab.smonitor.connector.model.AttributeDetails;
import org.lorislab.smonitor.connector.model.Host;
import org.lorislab.smonitor.connector.model.Server;
import org.lorislab.smonitor.connector.model.Session;
import org.lorislab.smonitor.connector.model.SessionDetails;
import org.lorislab.smonitor.connector.service.ConnectorService;
import org.lorislab.smonitor.connector.tomcat.util.TomcatUtil;
import java.util.List;
import org.apache.catalina.Service;
import org.lorislab.smonitor.connector.model.SessionCriteria;
import org.lorislab.smonitor.connector.tomcat.listener.TrackingContainerListener;

/**
 * The tomcat connector service.
 *
 * @author Andrej Petras
 */
public abstract class TomcatConnectorService implements ConnectorService {

    /**
     * The tomcat server.
     */
    protected org.apache.catalina.Server server;
    /**
     * The tomcat server service.
     */
    protected Service service;
    /**
     * The version.
     */
    protected String version;
    /**
     * The name.
     */
    private String name;
    
    public TomcatConnectorService(String name) {
        this.name = name;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Server getServer() {
        Server result = TomcatUtil.createServer(service);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Host getHost(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Host> getHosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications() {
        List<Application> result = TomcatUtil.getApplications(service);
        return result;
    }
     
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications(String host) {
        List<Application> result = TomcatUtil.getApplications(service, host);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationDetails getApplicationDetails(String host, String application) {        
        ApplicationDetails result = TomcatUtil.createApplicationDetails(service, host, application);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Session> getSessions(String host, String application) {
        List<Session> result = TomcatUtil.getSessions(service, host, application);
        return result;
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public List<Session> findSessionByCriteria(SessionCriteria criteria) {
        List<Session> result = null;
        if (criteria != null) {
            if (criteria.getApplications() != null) {               
                result = TomcatUtil.findSessionByCriteria(service, criteria.getApplications());
            }
        }                
        return result;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public SessionDetails getSessionDetails(String host, String application, String session) {
        SessionDetails result = TomcatUtil.createSessionDetails(service, host, application, session);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session getSession(String host, String application, String session) {
        Session result = TomcatUtil.createSession(service, host, application, session);
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Session deleteSession(String host, String application, String session) {
        Session result = TomcatUtil.deleteSession(service, host, application, session);
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeDetails getAttributeDetails(String host, String application, String session, String attribute) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void start() {
        TomcatUtil.addContainerListener(service, TrackingContainerListener.INSTANCE);
    }

    @Override
    public void shutdown() {
        TomcatUtil.removeContainerListener(service, TrackingContainerListener.INSTANCE);
    }    
}
