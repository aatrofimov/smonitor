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
package com.ajkaandrej.smonitor.tomcat.server;

import com.ajkaandrej.smonitor.tomcat.lookup.TomcatServiceLookup;
import com.ajkaandrej.smonitor.tomcat.lookup.TomcatServiceLookupFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.Container;
import org.apache.catalina.Engine;
import org.apache.catalina.Manager;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.Session;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class TomcatServer {

    private static final Logger LOGGER = Logger.getLogger(TomcatServer.class.getName());
    
    private static TomcatServer INSTANCE;
    private Server server;
    private Service service;

    private TomcatServer() {
        TomcatServiceLookup util = TomcatServiceLookupFactory.createServiceLookup();
        server = util.getServer();
        service = util.getService();
    }

    public static TomcatServer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TomcatServer();
        }
        return INSTANCE;
    }

    public Server getServer() {
        return server;
    }

    public Service getService() {
        return service;
    }
    
    public Manager findWebAppManager(String engine, String host, String webApp) {
        Manager result = null;
        Engine engineService = (Engine) service.getContainer();
        if (engineService != null) {
            if (engineService.getName().equals(engine)) {
                Container hostContainer = engineService.findChild(host);
                if (hostContainer != null) {
                    Container container = hostContainer.findChild(webApp);
                    if (container != null) {
                        result = container.getManager();
                    } else {
                        LOGGER.log(Level.WARNING, "No web application {0} found in the host {1} and engine {2}",new Object[]{ webApp, host, engine} );
                    }
                } else {
                    LOGGER.log(Level.WARNING, "No host {0} found in the engine {1}",new Object[]{ host, engine} );
                }
            }
        }
        return result;
    }    
    
    public Session findSession(String engine, String host, String webApp, String sessionId) {
        Session result = null;
        Manager manager = findWebAppManager(engine, host, webApp);
        if (manager != null) {
            try {
                result = manager.findSession(sessionId);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error by reading the session " + sessionId, ex);
            }
        } else {
            LOGGER.log(Level.WARNING, "No mananager found for web application {0}, host {1} and engine {2}",new Object[]{ webApp, host, engine} );
        }
        return result;
    }
}
