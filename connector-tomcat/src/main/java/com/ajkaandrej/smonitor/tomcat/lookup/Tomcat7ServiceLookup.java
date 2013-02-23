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
package com.ajkaandrej.smonitor.tomcat.lookup;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardService;

/**
 * The tomcat 7 lookup service class.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class Tomcat7ServiceLookup implements TomcatServiceLookup {
 
    private static final String JMX_DOMAIN = "jboss.web";
    
    @Override
    public Server getServer() {
        Server result = null;
        try {
            ObjectName name = new ObjectName("Catalina", "type", "Server");
            MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
            result = (Server) mBeanServer.getAttribute(name, "managedResource");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * @return 
     * 
     * TODO: tests
     */    
    @Override
    public Service getService() {
        Server server = getServer();
        Service[] services = server.findServices();
        StandardService service = null;
        for (int i = 0; i < services.length; i++) {            
            service = (StandardService) services[i];           
            if (JMX_DOMAIN.equals(service.getObjectName().getDomain())) {
                break;
            }
        }     
        return service; 
    }
}
