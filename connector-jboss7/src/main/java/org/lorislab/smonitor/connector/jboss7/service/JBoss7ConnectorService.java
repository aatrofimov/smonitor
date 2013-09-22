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
package org.lorislab.smonitor.connector.jboss7.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;
import org.jboss.as.server.CurrentServiceContainer;
import org.jboss.as.web.WebServer;
import org.jboss.msc.service.ServiceContainer;
import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceName;
import org.lorislab.smonitor.connector.tomcat.service.TomcatConnectorService;
import org.lorislab.smonitor.connector.tomcat.util.TomcatUtil;
import org.lorislab.smonitor.connector.jboss7.listener.TrackingJBoss7ContainerListener;

/**
 * The JBOSS 7 connector service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class JBoss7ConnectorService extends TomcatConnectorService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(JBoss7ConnectorService.class.getName());
    
    public JBoss7ConnectorService() {
        super("JBoss 7");
        
        // get the server version
        try {
            MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
            version = (String) mBeanServer.getAttribute(new ObjectName("jboss.as:management-root=server"), "releaseVersion");
        } catch (Exception ex) {
           throw new RuntimeException("Error get the version of the server", ex);
        }  
        
        // get the tomcat server
        server = getJBoss7TomcatServer();
        
        // get the tomcat server service
        service = getJBoss7TomcatService();
    }
    
    public Service getJBoss7TomcatService() {
        StandardService result = null;
        WebServer webServer = getWebServer();
        if (webServer == null) {
            LOGGER.log(Level.SEVERE, "The web server is null!");
        } else {
            result = webServer.getService();
        }
        return result;
    }
    
    public Server getJBoss7TomcatServer() {
        StandardServer result = null;
        WebServer webServer = getWebServer();
        if (webServer == null) {
            LOGGER.log(Level.SEVERE, "The web server is null!");
        } else {
            result = webServer.getServer();
        }
        return result;
    }
    
    private static WebServer getWebServer() {
        WebServer result = null;
        ServiceName service = ServiceName.JBOSS.append("web");
        ServiceContainer container = CurrentServiceContainer.getServiceContainer();
        ServiceController<?> controller = container.getService(service);
        if (controller == null) {
            LOGGER.log(Level.SEVERE, "The service controller is null!");
        } else {
            result = (WebServer) controller.getService();
        }
        return result;
    }
    
    @Override
    public void start() {
        TomcatUtil.addContainerListener(service, TrackingJBoss7ContainerListener.LISTENER_INSTANCE);
    }

    @Override
    public void shutdown() {
        TomcatUtil.removeContainerListener(service, TrackingJBoss7ContainerListener.LISTENER_INSTANCE);
    }

}
