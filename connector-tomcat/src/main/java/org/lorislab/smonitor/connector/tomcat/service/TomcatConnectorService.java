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

import java.util.HashSet;
import org.lorislab.smonitor.connector.model.Application;
import org.lorislab.smonitor.connector.model.ApplicationDetails;
import org.lorislab.smonitor.connector.model.AttributeDetails;
import org.lorislab.smonitor.connector.model.Host;
import org.lorislab.smonitor.connector.model.Server;
import org.lorislab.smonitor.connector.model.Session;
import org.lorislab.smonitor.connector.model.SessionDetails;
import org.lorislab.smonitor.connector.service.ConnectorService;
import org.lorislab.smonitor.connector.tomcat.util.TomcatUtil;
import org.lorislab.smonitor.connector.tomcat.server.TomcatServer;
import java.util.List;
import java.util.Set;
import org.lorislab.smonitor.connector.model.SessionCriteria;

/**
 * The tomcat connector service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class TomcatConnectorService implements ConnectorService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        return TomcatServer.getInstance().getVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return TomcatServer.getInstance().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Server getServer() {
        TomcatServer server = TomcatServer.getInstance();
        Server result = TomcatUtil.createServer(server.getService());;
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
        TomcatServer server = TomcatServer.getInstance();
        List<Application> result = TomcatUtil.getApplications(server.getContexts());
        return result;
    }
     
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Application> getApplications(String host) {
        TomcatServer server = TomcatServer.getInstance();
        List<Application> result = TomcatUtil.getApplications(server.getContexts(host));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationDetails getApplicationDetails(String host, String application) {
        TomcatServer server = TomcatServer.getInstance();
        String id = TomcatUtil.createTomcatApplicationId(application);
        ApplicationDetails result = TomcatUtil.createApplicationDetails(server.getContext(host, id));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Session> getSessions(String host, String application) {
        TomcatServer server = TomcatServer.getInstance();
        String id = TomcatUtil.createTomcatApplicationId(application);
        List<Session> result = TomcatUtil.getSessions(host, application, server.getSessions(host, id));
        return result;
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public List<Session> findSessionByCriteria(SessionCriteria criteria) {
        List<Session> result = null;
        TomcatServer server = TomcatServer.getInstance();
        if (criteria != null) {
            if (criteria.getApplications() != null) {
                Set<String> apps = new HashSet<String>();
                for (String aa : criteria.getApplications()) {
                    apps.add(TomcatUtil.createTomcatApplicationId(aa));
                }                
                result = TomcatUtil.createSearchResult(server.getSessions(apps));
            }
        }                
        return result;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public SessionDetails getSessionDetails(String host, String application, String session) {
        TomcatServer server = TomcatServer.getInstance();
        String id = TomcatUtil.createTomcatApplicationId(application);
        SessionDetails result = TomcatUtil.createSessionDetails(host, application, server.getSession(host, id, session));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session getSession(String host, String application, String session) {
        TomcatServer server = TomcatServer.getInstance();
        String id = TomcatUtil.createTomcatApplicationId(application);
        Session result = TomcatUtil.createSession(host, application, server.getSession(host, id, session));
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Session deleteSession(String host, String application, String session) {
        TomcatServer server = TomcatServer.getInstance();
        String id = TomcatUtil.createTomcatApplicationId(application);
        Session result = TomcatUtil.createSession(host, application, server.deleteSession(host, id, session));
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AttributeDetails getAttributeDetails(String host, String application, String session, String attribute) {
        TomcatServer server = TomcatServer.getInstance();
        String id = TomcatUtil.createTomcatApplicationId(application);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
