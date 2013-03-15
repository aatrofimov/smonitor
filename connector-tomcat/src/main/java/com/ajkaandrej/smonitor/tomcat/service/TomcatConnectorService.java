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
package com.ajkaandrej.smonitor.tomcat.service;

import com.ajkaandrej.smonitor.connector.model.Application;
import com.ajkaandrej.smonitor.connector.model.ApplicationDetails;
import com.ajkaandrej.smonitor.connector.model.AttributeDetails;
import com.ajkaandrej.smonitor.connector.model.Host;
import com.ajkaandrej.smonitor.connector.model.Server;
import com.ajkaandrej.smonitor.connector.model.Session;
import com.ajkaandrej.smonitor.connector.model.SessionDetails;
import com.ajkaandrej.smonitor.connector.service.ConnectorService;
import com.ajkaandrej.smonitor.tomcat.util.TomcatUtil;
import com.ajkaandrej.smonitor.tomcat.server.TomcatServer;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class TomcatConnectorService implements ConnectorService {

    @Override
    public String getVersion() {
        return TomcatServer.getInstance().getVersion();        
    }
    
    @Override
    public String getName() {
        return TomcatServer.getInstance().getName();
    }

    @Override
    public Server getServer() {
        TomcatServer server = TomcatServer.getInstance();
        Server result = TomcatUtil.createServer(server.getService());;
        return result;
    }

    @Override
    public Host getHost(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Host> getHosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Application> getApplications(String host) {
        TomcatServer server = TomcatServer.getInstance();
        List<Application> result = TomcatUtil.getApplications(server.getContexts(host));        
        return result;
    }
    
    @Override
    public ApplicationDetails getApplicationDetails(String host, String application) {
        TomcatServer server = TomcatServer.getInstance();
        String id = TomcatUtil.createTomcatApplicationId(application);
        ApplicationDetails result = TomcatUtil.createApplicationDetails(server.getContext(host, id));        
        return result;
    }

    @Override
    public List<Session> getSessions(String host, String application) {
        TomcatServer server = TomcatServer.getInstance();
        String id = TomcatUtil.createTomcatApplicationId(application);
        List<Session> result = TomcatUtil.getSessions(server.getSessions(host, id));
        return result;
    }
        
    @Override
    public SessionDetails getSessionDetails(String host, String application, String session) {
        TomcatServer server = TomcatServer.getInstance();
        String id = TomcatUtil.createTomcatApplicationId(application);
        SessionDetails result = TomcatUtil.createSessionDetails(server.getSession(host, id, session));
        return result;
    }

    @Override
    public AttributeDetails getAttributeDetails(String host, String application, String session, String attribute) {
        TomcatServer server = TomcatServer.getInstance();
        String id = TomcatUtil.createTomcatApplicationId(application);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
