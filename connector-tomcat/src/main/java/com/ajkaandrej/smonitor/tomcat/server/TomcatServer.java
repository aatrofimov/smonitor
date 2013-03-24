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

import com.ajkaandrej.smonitor.tomcat.lookup.JBoss7TomcatServiceLookup;
import com.ajkaandrej.smonitor.tomcat.lookup.JBossTomcatServiceLookup;
import com.ajkaandrej.smonitor.tomcat.lookup.TomcatServiceLookup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import org.apache.catalina.Container;
import org.apache.catalina.Engine;
import org.apache.catalina.Manager;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.Session;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.session.StandardSession;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class TomcatServer {

    private static final Logger LOGGER = Logger.getLogger(TomcatServer.class.getName());
    private static TomcatServer INSTANCE;
    private Server server;
    private Service service;
    private String version;
    private String name;

    private TomcatServer() {
        TomcatServiceLookup util = null;

        MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);

        // check jboss 7
        try {
            version = (String) mBeanServer.getAttribute(new ObjectName("jboss.as:management-root=server"), "releaseVersion");
            util = new JBoss7TomcatServiceLookup();
        } catch (Exception ex) {
            // do nothing
        }

        // check jboss 6
        if (util == null) {
            try {
                version = (String) mBeanServer.getAttribute(new ObjectName("jboss.system:type=Server"), "Version");
                util = new JBossTomcatServiceLookup();
            } catch (Exception ex) {
                // do nothing
            }
        }

        if (util != null) {
            server = util.getServer();
            service = util.getService();
            name = util.getName();
        } else {
            throw new RuntimeException("No supported server found!");
        }
    }

    public static TomcatServer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TomcatServer();
        }
        return INSTANCE;
    }

    public String getVersion() {
        return version;
    }

    public Server getServer() {
        return server;
    }

    public Service getService() {
        return service;
    }

    public String getName() {
        return name;
    }

    public Container[] getHosts() {
        Container[] result = null;
        Engine engineService = (Engine) service.getContainer();
        if (engineService != null) {
            result = engineService.findChildren();
        }
        return result;        
    }
    
    public StandardHost getHost(String host) {
        StandardHost result = null;
        Engine engineService = (Engine) service.getContainer();
        if (engineService != null) {
            result = (StandardHost) engineService.findChild(host);
        }
        return result;
    }

    public Container[] getContexts() {
        List<Container> result = new ArrayList<Container>();
        Container root = service.getContainer();
        if (root != null) {
            Container[] hosts = root.findChildren();
            if (hosts != null) {
                for (Container host : hosts) {
                    Container[] items = host.findChildren();
                    result.addAll(Arrays.asList(items));
                }
            }
        }
        return result.toArray(new Container[result.size()]);                
    }

    public Container[] getContexts(String host) {
        Container[] result = null;
        StandardHost hostContainer = getHost(host);
        if (hostContainer != null) {
            result = hostContainer.findChildren();
        } else {
            LOGGER.log(Level.SEVERE, "No host {0} found", new Object[]{host});
        }
        return result;        
    }
    
    public StandardContext getContext(String host, String webApp) {
        StandardContext result = null;
        StandardHost hostContainer = getHost(host);
        if (hostContainer != null) {
            result = (StandardContext) hostContainer.findChild(webApp);
        } else {
            LOGGER.log(Level.SEVERE, "No host {0} found", new Object[]{host});
        }
        return result;
    }

    public StandardSession getSession(String host, String webApp, String sessionId) {
        StandardSession result = null;
        StandardContext context = getContext(host, webApp);
        if (context != null) {
            Manager manager = context.getManager();
            if (manager != null) {
                try {
                    result = (StandardSession) manager.findSession(sessionId);
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Error by reading the session " + sessionId, ex);
                }
            } else {
                LOGGER.log(Level.SEVERE, "No mananager found for web application {0}, host {1}", new Object[]{webApp, host});
            }
        } else {
            LOGGER.log(Level.SEVERE, "No context found for web application {0}, host {1}", new Object[]{webApp, host});
        }
        return result;
    }
    
    public org.apache.catalina.Session[] getSessions(String host, String webApp) {
        org.apache.catalina.Session[] result = null;
        StandardContext context = getContext(host, webApp);
        if (context != null) {
            Manager manager = context.getManager();
            if (manager != null) {
                try {
                    result = manager.findSessions();
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Error by reading the list of sessions", ex);
                }
            } else {
                LOGGER.log(Level.SEVERE, "No mananager found for web application {0}, host {1}", new Object[]{webApp, host});
            }
        } else {
            LOGGER.log(Level.SEVERE, "No context found for web application {0}, host {1}", new Object[]{webApp, host});
        }
        return result;        
    }
}
