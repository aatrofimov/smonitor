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
package org.lorislab.smonitor.connector.tomcat.server;

import org.lorislab.smonitor.connector.tomcat.lookup.JBoss7TomcatServiceLookup;
import org.lorislab.smonitor.connector.tomcat.lookup.JBossTomcatServiceLookup;
import org.lorislab.smonitor.connector.tomcat.lookup.TomcatServiceLookup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
 * The tomcat server.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class TomcatServer {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(TomcatServer.class.getName());
    /**
     * The tomcat server instance.
     */
    private static TomcatServer INSTANCE;
    /**
     * The tomcat server.
     */
    private Server server;
    /**
     * The tomcat server service.
     */
    private Service service;
    /**
     * The version.
     */
    private String version;
    /**
     * The name.
     */
    private String name;

    /**
     * The default constructor.
     */
    private TomcatServer() {
        TomcatServiceLookup util = null;
        // get the mBean server
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

        // set-up the tomcat server instance       
        if (util != null) {
            server = util.getServer();
            service = util.getService();
            name = util.getName();
        } else {
            throw new RuntimeException("No supported server found!");
        }
    }

    /**
     * Gets the server instance.
     *
     * @return the server instance.
     */
    public static TomcatServer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TomcatServer();
        }
        return INSTANCE;
    }

    /**
     * Gets the version.
     *
     * @return the version.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Gets the tomcat server instance.
     *
     * @return the tomcat server instance.
     */
    public Server getServer() {
        return server;
    }

    /**
     * Gets the tomcat server service instance.
     *
     * @return the tomcat server service instance.
     */
    public Service getService() {
        return service;
    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of hosts.
     *
     * @return the list of hosts.
     */
    public Container[] getHosts() {
        Container[] result = null;
        Engine engineService = (Engine) service.getContainer();
        if (engineService != null) {
            result = engineService.findChildren();
        }
        return result;
    }

    /**
     * Gets the host by name.
     *
     * @param host the host name.
     * @return the host corresponding to the name.
     */
    public StandardHost getHost(String host) {
        StandardHost result = null;
        Engine engineService = (Engine) service.getContainer();
        if (engineService != null) {
            result = (StandardHost) engineService.findChild(host);
        }
        return result;
    }

    /**
     * Gets the list of contexts.
     *
     * @return the list of contexts.
     */
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

    /**
     * Gets the list of contexts.
     *
     * @param host the host name.
     * @return the list of context corresponding to the host name.
     */
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

    /**
     * Gets the standard context for the host and application name.
     *
     * @param host the host.
     * @param webApp the application name.
     * @return the standard context.
     */
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

    /**
     * Gets the standard session for host, application and session id.
     *
     * @param host the host.
     * @param webApp the application.
     * @param sessionId the session id.
     * @return the standard context corresponding to the host, application and
     * session id.
     */
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

    /**
     * Gets the list of sessions for the host and application.
     *
     * @param host the host.
     * @param webApp the application.
     * @return the list of sessions.
     */
    public Session[] getSessions(String host, String webApp) {
        org.apache.catalina.Session[] result = null;
        StandardContext context = getContext(host, webApp);
        result = getSessions(context);
        return result;
    }

    /**
     * Gets all sessions for the web-application.
     *
     * @param webApps the web application name.
     * @return the result of host and sessions.
     */
    public Map<String, Map<String, Session[]>> getSessions(Set<String> webApps) {
        Map<String, Map<String, Session[]>> result = new HashMap<String, Map<String, Session[]>>();

        Container[] hosts = getHosts();
        if (hosts != null) {
            for (Container host : hosts) {

                // search web application and sessions in the current host
                Map<String, Session[]> tmp = new HashMap<String, Session[]>();
                if (webApps != null && !webApps.isEmpty()) {
                    for (String webApp : webApps) {
                        Container container = host.findChild(webApp);
                        if (container != null) {
                            StandardContext context = (StandardContext) container;
                            tmp.put(webApp, getSessions(context));
                        }
                    }
                } else {
                    Container[] containers = host.findChildren();
                    if (containers != null) {
                        for (Container container : containers) {
                            StandardContext context = (StandardContext) container;
                            tmp.put(container.getName(), getSessions(context));
                        }
                    }
                }

                // put to the host result
                if (!tmp.isEmpty()) {
                    result.put(host.getName(), tmp);
                }
            }
        }
        return result;
    }

    /**
     * Gets all session for the context.
     *
     * @param context the application context.
     * @return the list of sessions.
     */
    private Session[] getSessions(StandardContext context) {
        org.apache.catalina.Session[] result = null;
        if (context != null) {
            Manager manager = context.getManager();
            if (manager != null) {
                try {
                    result = manager.findSessions();
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Error by reading the list of sessions", ex);
                }
            } else {
                LOGGER.log(Level.SEVERE, "No mananager found for web application");
            }
        } else {
            LOGGER.log(Level.SEVERE, "No context found for web application");
        }
        return result;
    }
}
