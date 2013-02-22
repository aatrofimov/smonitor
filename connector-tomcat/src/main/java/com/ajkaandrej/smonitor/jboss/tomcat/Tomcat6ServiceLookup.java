package com.ajkaandrej.smonitor.jboss.tomcat;

import com.ajkaandrej.smonitor.tomcat.lookup.TomcatServiceLookup;
import org.apache.catalina.Server;
import org.apache.catalina.ServerFactory;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardService;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class Tomcat6ServiceLookup implements TomcatServiceLookup {
 
    private static final String JMX_DOMAIN = "jboss.web";
    
    @Override
    public Server getServer() {
        return ServerFactory.getServer();
    }

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
