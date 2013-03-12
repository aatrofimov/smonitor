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
package com.ajkaandrej.smonitor.tomcat.mapper;

import com.ajkaandrej.smonitor.connector.model.Application;
import com.ajkaandrej.smonitor.connector.model.ApplicationDetails;
import com.ajkaandrej.smonitor.connector.model.Attribute;
import com.ajkaandrej.smonitor.connector.model.Server;
import com.ajkaandrej.smonitor.connector.model.Session;
import com.ajkaandrej.smonitor.connector.model.SessionDetails;
import com.ajkaandrej.smonitor.profiler.utils.ObjectProfile;
import com.ajkaandrej.smonitor.profiler.utils.ObjectProfiler;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Container;
import org.apache.catalina.Engine;
import org.apache.catalina.Manager;
import org.apache.catalina.Service;
import org.apache.catalina.realm.GenericPrincipal;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class TomcatMapper {

    private static Container findApplicationContainer(Service service, String application) {
        Container result = null;
        if (service != null) {
            Container root = service.getContainer();
            if (root != null) {
                result = root.findChild(application);
            }
        }
        return result;
    }

    public static SessionDetails createSessionDetails(Service service, String application, String id) {
        SessionDetails result = null;
        Container container = findApplicationContainer(service, application);
        if (container != null) {
            Manager manager = container.getManager();
            if (manager != null) {
                org.apache.catalina.Session session = null;
                try {
                    session = manager.findSession(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (session != null) {
                    result = new SessionDetails();
                    // session info
                    result.setInfo(session.getInfo());

                    // create session basic information
                    Session tmp = createSession(session);
                    result.setSession(tmp);

                    // load user roles
                    GenericPrincipal principal = (GenericPrincipal) session.getPrincipal();
                    if (principal != null) {
                        String[] roles = principal.getRoles();
                        if (roles != null) {
                            result.setRoles(Arrays.asList(roles));
                        }
                    }

                    // load HTTP session HttpSession                    
                    HttpSession httpSession = session.getSession();
                    if (httpSession != null) {
                        // is new session flag
                        result.setNewSession(httpSession.isNew());

                        double size = 0;
                        double sizeSerializable = 0;

                        List<Attribute> attributes = new ArrayList<Attribute>();

                        Enumeration enumerator = httpSession.getAttributeNames();
                        while (enumerator.hasMoreElements()) {
                            String name = (String) enumerator.nextElement();
                            Attribute attr = createAttribute(name, httpSession.getAttribute(name));
                            attributes.add(attr);
                            size = size + attr.getSize();
                            sizeSerializable = sizeSerializable + attr.getSerializableSize();
                        }

                        result.setSize(size);
                        result.setSizeSerializable(sizeSerializable);
                        result.setAttributes(attributes);

                    }

                }
            }
        }
        return result;
    }

    private static Attribute createAttribute(String name, Object value) {
        Attribute attr = new Attribute();
        attr.setName(name);
        // load object information  
        ObjectProfile objectInfo = ObjectProfiler.createObjectInfo(value);
        attr.setClazz(objectInfo.getClazz());
        attr.setSize(objectInfo.getSize());
        attr.setSerializable(objectInfo.isSerializable());
        attr.setSerializableSize(objectInfo.getSerializableSize());
        return attr;
    }

    public static ApplicationDetails createApplicationDetails(Service service, String application) {
        ApplicationDetails result = null;
        Container container = findApplicationContainer(service, application);
        if (container != null) {

            result = new ApplicationDetails();
            result.setName(container.getName());

            Manager manager = container.getManager();
            if (manager != null) {
                result.setDistributable(manager.getDistributable());
                result.setExpiredSessions(manager.getExpiredSessions());
                result.setMaxActive(manager.getMaxActive());
                result.setMaxInactiveInterval(manager.getMaxInactiveInterval());
                result.setRejectedSessions(manager.getRejectedSessions());
                result.setSessionAverageAliveTime(manager.getSessionAverageAliveTime());
                result.setSessionCounter(manager.getSessionCounter());
                result.setSessionIdLength(manager.getSessionIdLength());
                result.setSessionMaxAliveTime(manager.getSessionMaxAliveTime());
                result.setActiveSessions(manager.getActiveSessions());

                // load sessions
                List<Session> tmp = getSessions(manager);
                result.setSessions(tmp);
            }
        }
        return result;
    }

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

    public static Server createServer(Service service) {
        Server result = null;
        if (service != null) {
            Container root = service.getContainer();
            if (root instanceof Engine) {
                Engine engine = (Engine) root;
                result = new Server();
                result.setName(engine.getName());

                List<Application> applications = new ArrayList<Application>();
                for (Container container : engine.findChildren()) {
                    for (Container appContainer : container.findChildren()) {
                        Application app = new Application();
                        app.setName(appContainer.getName());
                        applications.add(app);
                    }
                }
                result.setApplications(applications);
            }
        }
        return result;
    }

    private static List<Session> getSessions(Manager manager) {
        List<Session> result = new ArrayList<Session>();
        if (manager != null) {
            org.apache.catalina.Session[] sessions = manager.findSessions();
            if (sessions != null) {
                for (org.apache.catalina.Session session : sessions) {
                    Session item = createSession(session);
                    if (item != null) {
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }

    public static List<Session> getSessions(Service service, String application) {
        List<Session> result = new ArrayList<Session>();
        Container container = findApplicationContainer(service, application);
        if (container != null) {
            Manager manager = container.getManager();
            if (manager != null) {
                result = getSessions(manager);
            }
        }
        return result;
    }

    public static List<Application> getApplications(Service service) {
        List<Application> result = new ArrayList<Application>();
        if (service != null) {
            Container root = service.getContainer();
            for (Container container : root.findChildren()) {
                for (Container appContainer : container.findChildren()) {
                    Application app = new Application();
                    app.setName(appContainer.getName());
                    result.add(app);
                }
            }
        }
        return result;
    }
}
