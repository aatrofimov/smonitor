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
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface ConnectorService {
    
    String getVersion();
    
    String getName();
    
    Server getServer();
    
    Host getHost(String name);
    
    List<Host> getHosts();
    
    List<Application> getApplications(String host);
    
    ApplicationDetails getApplicationDetails(String host, String application);
    
    List<Session> getSessions(String host, String application);
    
    SessionDetails getSessionDetails(String host, String application, String session);
    
    AttributeDetails getAttributeDetails(String host, String application, String session, String attribute);
}
