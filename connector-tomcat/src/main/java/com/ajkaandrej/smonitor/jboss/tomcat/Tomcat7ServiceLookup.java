package com.ajkaandrej.smonitor.jboss.tomcat;

import com.ajkaandrej.smonitor.tomcat.lookup.TomcatServiceLookup;
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
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
         // Standalone tomcat 7            
//        ObjectName name = new ObjectName("Catalina", "type", "Server");
//        Server server = (Server) mBeanServer.getAttribute(name, "managedResource");

public class Tomcat7ServiceLookup implements TomcatServiceLookup {

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
