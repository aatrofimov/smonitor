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
import java.util.List;
import org.apache.catalina.Container;
import org.apache.catalina.Engine;
import org.apache.catalina.Manager;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.session.StandardSession;

/**
 * The tomcat utility.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class TomcatUtil {

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
     * @param session the session.
     * @return the session details.
     */
    public static SessionDetails createSessionDetails(String host, String application, StandardSession session) {
        SessionDetails result = null;

        if (session != null) {
            result = new SessionDetails();
            result.setHost(host);
            result.setApplication(application);

            // session info
            result.setInfo(session.getInfo());

            // create session basic information
            Session tmp = createSession(session);
            result.setCreationTime(tmp.getCreationTime());
            result.setId(tmp.getId());
            result.setLastAccessedTime(tmp.getLastAccessedTime());
            result.setLastAccessedTimeInternal(tmp.getLastAccessedTimeInternal());
            result.setMaxInactiveInterval(tmp.getMaxInactiveInterval());
            result.setUser(tmp.getUser());
            result.setValid(tmp.isValid());

            // load user roles
            GenericPrincipal principal = (GenericPrincipal) session.getPrincipal();
            if (principal != null) {
                String[] roles = principal.getRoles();
                if (roles != null) {
                    result.setRoles(Arrays.asList(roles));
                }
            }

            // is new session flag
            result.setNewSession(session.isNew());

            double size = 0;
            double sizeSerializable = 0;

            List<Attribute> attributes = new ArrayList<Attribute>();

            Enumeration enumerator = session.getAttributeNames();
            while (enumerator.hasMoreElements()) {
                String name = (String) enumerator.nextElement();
                Attribute attr = createAttribute(name, session.getAttribute(name));
                attributes.add(attr);
                size = size + attr.getSize();
                sizeSerializable = sizeSerializable + attr.getSerializableSize();
            }

            result.setSize(size);
            result.setSizeSerializable(sizeSerializable);
            result.setAttributes(attributes);

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
    public static ApplicationDetails createApplicationDetails(StandardContext context) {
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
                List<Session> tmp = getSessions(manager.findSessions());
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
    private static Session createSession(org.apache.catalina.Session session) {
        Session result = null;
        if (session != null) {
            result = new Session();
            //Return the session identifier for this session.
            result.setId(session.getId());
            //Return the creation time for this session.
            result.setCreationTime(new Date(session.getCreationTime()));
            //Return the last time the client sent a request associated with this session, as the number of milliseconds since midnight, January 1, 1970 GMT.
            result.setLastAccessedTime(new Date(session.getLastAccessedTime()));
            //Return the last client access time without invalidation check
            result.setLastAccessedTimeInternal(session.getLastAccessedTimeInternal());
            //Return the maximum time interval, in seconds, between client requests before the servlet container will invalidate the session.
            result.setMaxInactiveInterval(session.getMaxInactiveInterval());
            //Return the isValid flag for this session.
            result.setValid(session.isValid());
            // Get user principal
            Principal principal = (Principal) session.getPrincipal();
            if (principal != null) {
                result.setUser(principal.getName());
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
     * @param sessions the tomcat list of session.
     * @return the list of sessions.
     */
    public static List<Session> getSessions(org.apache.catalina.Session[] sessions) {
        List<Session> result = new ArrayList<Session>();
        if (sessions != null) {
            for (org.apache.catalina.Session session : sessions) {
                Session item = createSession(session);
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
    public static List<Application> getApplications(Container[] contexts) {
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
}
