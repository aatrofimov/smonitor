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
package org.lorislab.smonitor.connector.tomcat6.service;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import org.apache.catalina.ServerFactory;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardService;
import org.lorislab.smonitor.connector.tomcat.service.TomcatConnectorService;

/**
 * The tomcat connector service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class Tomcat6ConnectorService extends TomcatConnectorService {
    
    /** The JMX domain. */
    private static final String JMX_DOMAIN = "jboss.web";
    
    public Tomcat6ConnectorService() {
        super("Tomcat 6");
        
        // get the server version
        try {
            MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
            version = (String) mBeanServer.getAttribute(new ObjectName("jboss.system:type=Server"), "Version");
        } catch (Exception ex) {
           throw new RuntimeException("Error get the version of the server", ex);
        }  
        
        // get the tomcat server
        server = ServerFactory.getServer();
        
        // get the tomcat server service
        service = server.findService("Catalina");
    }
        
    public Service getTomcat6Service() {
        Service[] services = ServerFactory.getServer().findServices();
        StandardService result = null;
        for (int i = 0; i < services.length; i++) {
            result = (StandardService) services[i];
            if (JMX_DOMAIN.equals(result.getObjectName().getDomain())) {
                break;
            }
        }
        return result;
    }
    
}
