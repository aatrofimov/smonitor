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
package org.lorislab.smonitor.connector.tomcat7.service;

import java.util.logging.Logger;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.lorislab.smonitor.connector.tomcat.service.TomcatConnectorService;

/**
 * The tomcat connector service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class Tomcat7ConnectorService extends TomcatConnectorService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(Tomcat7ConnectorService.class.getName());
    
    /**
     * The JMX domain.
     */
    private static final String JMX_DOMAIN = "web";
    
    public Tomcat7ConnectorService() {
        super("Tomcat 7");
        
        // get the server version
        try {
            MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
            version = (String) mBeanServer.getAttribute(new ObjectName("jboss.as:management-root=server"), "releaseVersion");
            
            // get the tomcat server
            server = (org.apache.catalina.Server) mBeanServer.getAttribute(new ObjectName("Catalina", "type", "Server"), "managedResource");
           
            service = server.findService("Catalina");
        } catch (Exception ex) {
           throw new RuntimeException("Error get the version of the server", ex);
        }          
    }    
}
