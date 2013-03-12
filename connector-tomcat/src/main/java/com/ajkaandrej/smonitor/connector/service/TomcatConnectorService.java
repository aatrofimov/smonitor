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
import com.ajkaandrej.smonitor.connector.model.Server;
import com.ajkaandrej.smonitor.connector.model.Session;
import com.ajkaandrej.smonitor.connector.model.SessionDetails;
import com.ajkaandrej.smonitor.tomcat.mapper.TomcatMapper;
import com.ajkaandrej.smonitor.tomcat.server.TomcatServer;
import java.util.List;
import org.apache.catalina.Service;


/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class TomcatConnectorService implements ConnectorService {

    @Override
    public String getName() {
        return TomcatServer.getInstance().getUtilityClass().getSimpleName();
    }

    @Override
    public Server getServer() {
        Server result = null;
        Service service = TomcatServer.getInstance().getService();
        if (service != null) {
            result = TomcatMapper.createServer(service);
        }
        return result;
    }

    @Override
    public List<Application> getApplications() {
        List<Application> result = null;
        Service service = TomcatServer.getInstance().getService();
        if (service != null) {
            result = TomcatMapper.getApplications(service);
        }        
        return result;
    }
    
    @Override
    public ApplicationDetails getApplicationDetails(String application) {
        ApplicationDetails result = null;
        Service service = TomcatServer.getInstance().getService();
        if (service != null) {
            result = TomcatMapper.createApplicationDetails(service, application);
        }
        return result;
    }

    @Override
    public List<Session> getSessions(String application) {
        List<Session> result = null;
        Service service = TomcatServer.getInstance().getService();
        if (service != null) {
            result = TomcatMapper.getSessions(service, application);
        }        
        return result;
    }
        
    @Override
    public SessionDetails getSessionDetails(String application, String session) {
        SessionDetails result = null;
        Service service = TomcatServer.getInstance().getService();
        if (service != null) {
            result = TomcatMapper.createSessionDetails(service, application, session);
        }
        return result;
    }

    @Override
    public AttributeDetails getAttributeDetails(String application, String session, String attribute) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
