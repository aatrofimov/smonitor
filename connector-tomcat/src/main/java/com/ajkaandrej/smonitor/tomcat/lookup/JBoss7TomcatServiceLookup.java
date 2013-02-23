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

import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;
import org.jboss.as.server.CurrentServiceContainer;
import org.jboss.as.web.WebServer;
import org.jboss.msc.service.ServiceContainer;
import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceName;

/**
 * The jBoss 7 tomcat 7 lookup service class.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class JBoss7TomcatServiceLookup implements TomcatServiceLookup {

    public Server getServer() {
        StandardServer result = null;
        WebServer webServer = getWebServer();
        if (webServer == null) {
            System.out.println("webServer NULL!");
        } else {            
            result = webServer.getServer();
        }
        return result;
    }
    
    public Service getService() {
        StandardService result = null;
        WebServer webServer = getWebServer();
        if (webServer == null) {
            System.out.println("webServer NULL!");
        } else {            
            result = webServer.getService();
        }
        return result;
    }
    
    private static WebServer getWebServer() {
        WebServer result = null;
        ServiceName service = ServiceName.JBOSS.append("web");
        ServiceContainer container = CurrentServiceContainer.getServiceContainer();
        ServiceController<?> controller = container.getService(service);
        if (controller == null) {
            System.out.println(" NULL!");
        } else {
            result = (WebServer) controller.getService();
        }
        return result;
    }    
}
