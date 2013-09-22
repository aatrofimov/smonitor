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
package org.lorislab.smonitor.connector.tomcat.util;

import org.lorislab.smonitor.connector.model.Application;
import org.lorislab.smonitor.connector.model.ApplicationDetails;
import org.lorislab.smonitor.connector.model.Attribute;
import org.lorislab.smonitor.connector.model.Host;
import org.lorislab.smonitor.connector.model.Server;
import org.lorislab.smonitor.connector.model.Session;
import org.lorislab.smonitor.connector.model.SessionDetails;
import org.lorislab.smonitor.profiler.utils.ObjectProfile;
import org.lorislab.smonitor.profiler.utils.ObjectProfiler;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Manager;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.session.StandardSession;
import org.lorislab.smonitor.connector.tomcat.listener.TrackingContainerListener;

/**
 * The tomcat utility.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class TomcatUtil {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(TomcatUtil.class.getName());
    
    /**
     * The application prefix.
     */
    private static final String APP_PREFIX = "/";
    /**
     * The root application.
     */
    private static final String ROOT_NAME = "ROOT";
    /**
     * The root id.
     */
    private static final String ROOT_ID = "";

    /**
     * The default constructor.
     */
    private TomcatUtil() {
        // empty constructor
    }

    /**
     * Creates the session details.
     *
     * @param host the host.
     * @param application the application.
     * @param sessionId the session id.
     * @return the session details.
     */
    public static SessionDetails createSessionDetails(Service service, String host, String application, String sessionId) {
        
        String id = TomcatUtil.createTomcatApplicationId(application);
        StandardSession session = getSession(service, host, id, sessionId);
        
        SessionDetails result = null;

        if (session != null) {
            result = new SessionDetails();

            // session info
            result.setInfo(session.getInfo());

            // create session basic information
            Session tmp = createSession(host, application, session);
            result.setSession(tmp);

            // load user roles
            GenericPrincipal principal = (GenericPrincipal) session.getPrincipal();
            if (principal != null) {
                String[] roles = principal.getRoles();
                if (roles != null) {
                    result.setRoles(Arrays.asList(roles));
                }
            }

            if (session instanceof StandardSession) {

                StandardSession standardSession = (StandardSession) session;

                // is new session flag
                result.setNewSession(standardSession.isNew());

                double size = 0;
                double sizeSerializable = 0;

                List<Attribute> attributes = new ArrayList<Attribute>();

                Enumeration enumerator = standardSession.getAttributeNames();
                while (enumerator.hasMoreElements()) {
                    String name = (String) enumerator.nextElement();
                    Attribute attr = createAttribute(name, standardSession.getAttribute(name));
                    attributes.add(attr);
                    size = size + attr.getSize();
                    sizeSerializable = sizeSerializable + attr.getSerializableSize();
                }

                result.setSize(size);
                result.setSizeSerializable(sizeSerializable);
                result.setAttributes(attributes);
            }

        }
        return result;
    }

    /**
     * Creates the attribute.
     *
     * @param name the name.
     * @param value the value.
     * @return the attribute.
     */
    private static Attribute createAttribute(String name, Object value) {
        Attribute attr = new Attribute();
        attr.setName(name);
        // load object information  
        ObjectProfile objectInfo = ObjectProfiler.createObjectInfo(value);
        attr.setType(objectInfo.getClazz());
        attr.setSize(objectInfo.getSize());
        attr.setSerializable(objectInfo.isSerializable());
        attr.setSerializableSize(objectInfo.getSerializableSize());
        return attr;
    }

    /**
     * Creates the application details.
     *
     * @param context the context.
     * @return the application details.
     */
    public static ApplicationDetails createApplicationDetails(Service service, String host, String application) {
        
        String id = TomcatUtil.createTomcatApplicationId(application);
        
        StandardContext context = getContext(service, host, id);
        ApplicationDetails result = null;
        if (context != null) {

            result = new ApplicationDetails();
            Application app = createApplication(context);
            result.setId(app.getId());
            result.setName(app.getName());
            result.setHost(app.getHost());

            result.setContext(context.getEncodedPath());
            result.setStartTime(new Date(context.getStartTime()));

            Manager manager = context.getManager();
            if (manager != null) {
                // The distributable flag for the sessions supported by this Manager
                result.setDistributable(manager.getDistributable());
                // Gets the number of sessions that have expired
                result.setExpiredSessions(manager.getExpiredSessions());
                // Gets the maximum number of sessions that have been active at the same time
                result.setMaxActive(manager.getMaxActive());
                // Return the default maximum inactive interval (in seconds) for Sessions created by this Manager.
                result.setMaxInactiveInterval(manager.getMaxInactiveInterval());
                // Gets the number of sessions that were not created because the maximum number of active sessions was reached.
                result.setRejectedSessions(manager.getRejectedSessions());
                // Gets the average time (in seconds) that expired sessions had been alive.
                result.setSessionAverageAliveTime(manager.getSessionAverageAliveTime());
                // Returns the total number of sessions created by this manager.
                result.setSessionCounter(manager.getSessionCounter());
                // Gets the session id length (in bytes) of Sessions created by this Manager.
                result.setSessionIdLength(manager.getSessionIdLength());
                // Gets the longest time (in seconds) that an expired session had been alive.
                result.setSessionMaxAliveTime(manager.getSessionMaxAliveTime());
                // Gets the number of currently active sessions.
                result.setActiveSessions(manager.getActiveSessions());

                // load sessions
                List<Session> tmp = createSessions(app.getHost(), app.getName(), manager.findSessions());
                result.setSessions(tmp);
            }
        }
        return result;
    }

    /**
     * Creates the session.
     *
     * @param session the session.
     * @return the session.
     */
    public static Session createSession(Service service, String host, String application, String sessionId) {
        String id = createTomcatApplicationId(application);
        StandardSession session = getSession(service, host, id, sessionId);
        return createSession(host, application, session);
    }
    
    private static Session createSession(String host, String application, org.apache.catalina.Session session) {
        Session result = null;
        if (session != null) {
            result = new Session();
            result.setHost(host);
            result.setApplication(getApplicationName(application));

            //Return the session identifier for this session.
            result.setId(session.getId());
            //Return the isValid flag for this session.
            result.setValid(session.isValid());

            if (session.isValid()) {
                //Return the creation time for this session.
                result.setCreationTime(new Date(session.getCreationTime()));
                //Return the last time the client sent a request associated with this session, as the number of milliseconds since midnight, January 1, 1970 GMT.
                result.setLastAccessedTime(new Date(session.getLastAccessedTime()));
                //Return the last client access time without invalidation check
                result.setLastAccessedTimeInternal(session.getLastAccessedTimeInternal());
                //Return the maximum time interval, in seconds, between client requests before the servlet container will invalidate the session.
                result.setMaxInactiveInterval(session.getMaxInactiveInterval());
                // Get user principal
                Principal principal = (Principal) session.getPrincipal();
                if (principal != null) {
                    result.setUser(principal.getName());
                }
            }
        }
        return result;
    }

    /**
     * Creates the server.
     *
     * @param service the tomcat server service.
     * @return the server.
     */
    public static Server createServer(Service service) {
        Server result = null;
        if (service != null) {
            Container root = service.getContainer();
            if (root != null) {
                Engine engine = (Engine) root;
                result = new Server();
                result.setId(engine.getName());
                result.setName(engine.getName());

                List<Host> hosts = new ArrayList<Host>();
                for (Container container : engine.findChildren()) {
                    Host host = new Host();
                    host.setName(container.getName());
                    host.setId(container.getName());
                    hosts.add(host);


                    List<Application> applications = new ArrayList<Application>();
                    for (Container appContainer : container.findChildren()) {
                        Application app = createApplication(appContainer);
                        if (app != null) {
                            applications.add(app);
                        }
                    }
                    host.setApplications(applications);

                }

                result.setHosts(hosts);

            }
        }
        return result;
    }

    /**
     * Creates the application.
     *
     * @param context the container.
     * @return the application.
     */
    private static Application createApplication(Container context) {
        Application result = null;
        if (context != null) {
            result = new Application();
            result.setId(getTomcatApplicationId(context));
            Container parent = context.getParent();
            if (parent != null) {
                result.setHost(parent.getName());
            }
            // The name string (suitable for use by humans)
            result.setName(getApplicationName(context.getName()));
        }
        return result;
    }

    /**
     * Gets the list of sessions.
     *
     * @param sessions the tomcat list of session.
     * @return the list of sessions.
     */
    public static List<Session> getSessions(Service service, String host, String application) {
        String id = TomcatUtil.createTomcatApplicationId(application);
        org.apache.catalina.Session[] sessions = getTomcatSessions(service, host, id);
        List<Session> result = createSessions(host, application, sessions);        
        return result;
    }

    /**
     * Gets the list of sessions.
     *
     * @param sessions the tomcat list of session.
     * @return the list of sessions.
     */
    private static List<Session> createSessions(String host, String application, org.apache.catalina.Session[] sessions) {
        List<Session> result = new ArrayList<Session>();
        if (sessions != null) {
            for (org.apache.catalina.Session session : sessions) {
                Session item = createSession(host, application, session);
                if (item != null) {
                    result.add(item);
                }
            }
        }
        return result;
    }
    
    /**
     * Gets the list of applications.
     *
     * @param contexts the list of containers.
     * @return the list of applications.
     */
    public static List<Application> getApplications(Service service) {
        Container[] contexts = getContexts(service);
        List<Application> result = new ArrayList<Application>();
        if (contexts != null) {
            for (Container tmp : contexts) {
                Application app = createApplication(tmp);
                if (app != null) {
                    result.add(app);
                }
            }
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
    private static StandardContext getContext(Service service, String host, String webApp) {
        StandardContext result = null;
        StandardHost hostContainer = getHost(service, host);
        if (hostContainer != null) {
            result = (StandardContext) hostContainer.findChild(webApp);
        } else {
            LOGGER.log(Level.SEVERE, "No host {0} found", new Object[]{host});
        }
        return result;
    }
    
    /**
     * Gets the list of contexts.
     *
     * @param host the host name.
     * @return the list of context corresponding to the host name.
     */
    private static Container[] getContexts(Service service, String host) {
        Container[] result = null;
        StandardHost hostContainer = getHost(service, host);
        if (hostContainer != null) {
            result = hostContainer.findChildren();
        } else {
            LOGGER.log(Level.SEVERE, "No host {0} found", new Object[]{host});
        }
        return result;
    }
    
    /**
     * Gets the list of applications.
     *
     * @param contexts the list of containers.
     * @return the list of applications.
     */
    public static List<Application> getApplications(Service service, String host) {
        Container[] contexts = getContexts(service, host);
        List<Application> result = new ArrayList<Application>();
        if (contexts != null) {
            for (Container tmp : contexts) {
                Application app = createApplication(tmp);
                if (app != null) {
                    result.add(app);
                }
            }
        }
        return result;
    }
    
    /**
     * Gets the host by name.
     *
     * @param host the host name.
     * @return the host corresponding to the name.
     */
    private static StandardHost getHost(Service service, String host) {
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
    private static Container[] getContexts(Service service) {
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
     * Gets the application name.
     *
     * @param name the tomcat application name.
     * @return the application name.
     */
    private static String getApplicationName(String name) {
        String result = name;
        if (name == null || name.isEmpty()) {
            result = ROOT_NAME;
        } else {
            if (name.startsWith(APP_PREFIX)) {
                result = name.substring(APP_PREFIX.length());
            }
        }
        return result;
    }

    /**
     * Gets the tomcat application id.
     *
     * @param container the container.
     * @return the tomcat application id.
     */
    private static String getTomcatApplicationId(Container container) {
        String result = null;
        if (container != null) {
            result = getApplicationName(container.getName());
        }
        return result;
    }

    /**
     * Creates the tomcat application id.
     *
     * @param name the application name.
     * @return the tomcat application id.
     */
    public static String createTomcatApplicationId(String name) {
        String result = name;
        if (ROOT_NAME.equals(name)) {
            result = ROOT_ID;
        } else {
            if (name != null && !name.isEmpty()) {
                if (!name.startsWith(APP_PREFIX)) {
                    result = APP_PREFIX + name;
                }
            }
        }
        return result;
    }

    /**
     * Creates list of session for the search result.
     *
     * @param search the search result.
     * @return the list of sessions.
     */
    private static List<Session> createSearchResult(Map<String, Map<String, org.apache.catalina.Session[]>> search) {
        List<Session> result = new ArrayList<Session>();
        if (search != null) {
            if (!search.isEmpty()) {
                Set<String> hosts = search.keySet();
                for (String host : hosts) {
                    Map<String, org.apache.catalina.Session[]> tmp = search.get(host);
                    if (!tmp.isEmpty()) {
                        Set<String> apps = tmp.keySet();
                        for (String app : apps) {
                            List<Session> sessions = createSessions(host, app, tmp.get(app));
                            if (sessions != null && !sessions.isEmpty()) {
                                result.addAll(sessions);
                            }
                        }
                    }
                }
            }
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
    private static StandardSession getSession(Service service, String host, String webApp, String sessionId) {
        StandardSession result = null;
        StandardContext context = getContext(service, host, webApp);
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
     * Deletes the session for host, application and session id.
     *
     * @param host the host.
     * @param webApp the application.
     * @param sessionId the session id.
     * @return the deleted session to the host, application and session id.
     */
    public static Session deleteSession(Service service, String host, String application, String sessionId) {
        String id = createTomcatApplicationId(application);
        Session result = null;
        StandardSession tmp = getSession(service, host, id, sessionId);
        if (tmp != null) {
            tmp.invalidate();
            
            result = createSession(host, application, tmp);
            LOGGER.log(Level.INFO, "The session id {0}, application {1} and host {2} was deleted", new Object[]{sessionId, application, host});
        } else {
            LOGGER.log(Level.FINEST, "No session found for id {0}, application {0} and host {1}", new Object[]{sessionId, application, host});
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
    private static org.apache.catalina.Session[] getTomcatSessions(Service service, String host, String webApp) {
        org.apache.catalina.Session[] result = null;
        StandardContext context = getContext(service, host, webApp);
        result = getSessions(context);
        return result;
    }  
    
    /**
     * Gets all session for the context.
     *
     * @param context the application context.
     * @return the list of sessions.
     */
    private static org.apache.catalina.Session[] getSessions(StandardContext context) {
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
    
    public static List<Session> findSessionByCriteria(Service service, Set<String> applications) {
        Map<String, Map<String, org.apache.catalina.Session[]>> tmp = findSession(service, applications);
        return createSearchResult(tmp);
    }
    
    /**
     * Gets all sessions for the web-application.
     *
     * @param webApps the web application name.
     * @return the result of host and sessions.
     */
    private static Map<String, Map<String, org.apache.catalina.Session[]>> findSession(Service service, Set<String> applications) {
        Map<String, Map<String, org.apache.catalina.Session[]>> result = new HashMap<String, Map<String, org.apache.catalina.Session[]>>();

        Container[] hosts = getHosts(service);
        if (hosts != null) {
            for (Container host : hosts) {

                // search web application and sessions in the current host
                Map<String, org.apache.catalina.Session[]> tmp = new HashMap<String, org.apache.catalina.Session[]>();
                if (applications != null && !applications.isEmpty()) {
                    for (String app : applications) {
                        String id = TomcatUtil.createTomcatApplicationId(app);
                        Container container = host.findChild(id);
                        if (container != null) {
                            StandardContext context = (StandardContext) container;
                            tmp.put(id, getSessions(context));
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
     * Gets the list of hosts.
     *
     * @return the list of hosts.
     */
    private static Container[] getHosts(Service service) {
        Container[] result = null;
        Engine engineService = (Engine) service.getContainer();
        if (engineService != null) {
            result = engineService.findChildren();
        }
        return result;
    }   
    
    public static void addContainerListener(Service service, TrackingContainerListener listener) {
        Container[] containers = getHosts(service);
        if (containers != null) {
            for (Container container : containers) {
                container.addContainerListener(listener);                
            }
        }
        
        containers = getContexts(service);
        if (containers != null) {
            for (Container container : containers) {
                listener.register((Context) container);
            }
        }        
    }

    public static void removeContainerListener(Service service, TrackingContainerListener listener) {
        Container[] containers = getHosts(service);
        if (containers != null) {
            for (Container container : containers) {
                container.removeContainerListener(listener);                
            }
        }      
        
        containers = getContexts(service);
        if (containers != null) {
            for (Container container : containers) {
                listener.unregister((Context) container);
            }
        }        
    }    
}
