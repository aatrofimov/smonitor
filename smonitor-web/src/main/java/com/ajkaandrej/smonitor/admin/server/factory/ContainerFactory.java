package com.ajkaandrej.smonitor.admin.server.factory;

import com.ajkaandrej.smonitor.admin.shared.model.AttributeInfo;
import com.ajkaandrej.smonitor.admin.shared.model.EngineInfo;
import com.ajkaandrej.smonitor.admin.shared.model.HostInfo;
import com.ajkaandrej.smonitor.admin.shared.model.SessionDetails;
import com.ajkaandrej.smonitor.admin.shared.model.SessionInfo;
import com.ajkaandrej.smonitor.admin.shared.model.UserInfo;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationDetails;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationInfo;
import com.ajkaandrej.smonitor.profiler.utils.ObjectProfile;
import com.ajkaandrej.smonitor.profiler.utils.ObjectProfiler;
import com.ajkaandrej.smonitor.tomcat.lookup.TomcatServiceLookup;
import com.ajkaandrej.smonitor.tomcat.lookup.TomcatServiceLookupFactory;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Container;
import org.apache.catalina.Engine;
import org.apache.catalina.Manager;
import org.apache.catalina.Service;
import org.apache.catalina.Session;
import org.apache.catalina.realm.GenericPrincipal;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ContainerFactory {

    private static final Logger LOGGER = Logger.getLogger(ContainerFactory.class.getName());
    
    public static SessionDetails create(WebApplicationInfo webApp, SessionInfo info) {
        SessionDetails result = null;
        Manager manager = findWebAppManager(webApp);
        if (manager != null) {
            try {

                Session session = manager.findSession(info.getId());
                if (session != null) {
                    result = new SessionDetails();
                    result.setSessionInfo(createSession(session));
                    result.setInfo(session.getInfo());
                    result.setAuthType(session.getAuthType());

                    // load user information
                    GenericPrincipal principal = (GenericPrincipal) session.getPrincipal();
                    if (principal != null) {
                        UserInfo ui = new UserInfo();
                        ui.setName(principal.getName());
                        ui.setPassword(principal.getPassword());
                        System.out.println(ui.getPassword());
                        ui.setRoles(principal.getRoles());
                        System.out.println(ui.getRoles());
                        result.setUserInfo(ui);
                    } else {
                        LOGGER.log(Level.FINE, "The principal for the session {0} is null.", new Object[]{info.getId()});                        
                    }

                    HttpSession httpSession = session.getSession();
                    if (httpSession != null) {
                        List<AttributeInfo> tmp = new ArrayList<AttributeInfo>();

                        double size = 0;
                        double sizeSerializable = 0;

                        Enumeration enumerator = httpSession.getAttributeNames();
                        while (enumerator.hasMoreElements()) {
                            String name = (String) enumerator.nextElement();
                            Object value = httpSession.getAttribute(name);

                            AttributeInfo attr = new AttributeInfo();
                            attr.setName(name);
                            // load object information
                            ObjectProfile objectInfo = ObjectProfiler.createObjectInfo(value);
                            attr.setClazz(objectInfo.getClazz());
                            attr.setSize(objectInfo.getSize());
                            attr.setSerializable(objectInfo.isSerializable());
                            attr.setSerializableSize(objectInfo.getSerializableSize());
                            tmp.add(attr);

                            size = size + attr.getSize();
                            sizeSerializable = sizeSerializable + attr.getSerializableSize();
                        }

                        result.setAttributes(tmp.toArray(new AttributeInfo[tmp.size()]));
                        result.setSize(size);
                        result.setSizeSerializable(sizeSerializable);

                        result.setNewSession(httpSession.isNew());

                    } else {
                        LOGGER.log(Level.WARNING, "The HTTP-Session for the session {0} is null.", new Object[]{info.getId()});                        
                    }
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error by reading the session " + info.getId() + " details ", ex);
            }
        }
        return result;
    }

    private static Manager findWebAppManager(WebApplicationInfo info) {
        Manager result = null;
        TomcatServiceLookup util = TomcatServiceLookupFactory.createServiceLookup();
        if (util != null) {
            Service service = util.getService();
            if (service != null) {
                result = findWebAppManager((Engine) service.getContainer(), info);
            }
        }
        return result;
    }

    public static WebApplicationDetails create(WebApplicationInfo info) {
        WebApplicationDetails result = null;
        Manager manager = findWebAppManager(info);
        if (manager != null) {
            result = new WebApplicationDetails();
            result.setInfo(info);
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
            SessionInfo[] sessions = createSession(manager.findSessions());
            result.setSessions(sessions);
        }
        return result;
    }

    private static SessionInfo createSession(Session session) {
        SessionInfo result = new SessionInfo();
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
        System.out.println(principal.toString());
        if (principal != null) {
            result.setUser(principal.getName());
        }
        return result;
    }

    private static SessionInfo[] createSession(Session[] sessions) {
        SessionInfo[] result = null;
        if (sessions != null) {
            result = new SessionInfo[sessions.length];
            for (int i = 0; i < sessions.length; i++) {
                result[i] = createSession(sessions[i]);
            }
        }
        return result;
    }

    private static Manager findWebAppManager(Engine engine, WebApplicationInfo info) {
        Manager result = null;
        if (engine != null) {
            if (engine.getName().equals(info.getEngine())) {
                Container host = engine.findChild(info.getHost());
                if (host != null) {
                    Container container = host.findChild(info.getName());
                    if (container != null) {
                        result = container.getManager();
                    }
                }
            }
        }
        return result;
    }

    public static EngineInfo createEngine() {
        EngineInfo result = null;

        TomcatServiceLookup util = TomcatServiceLookupFactory.createServiceLookup();
        if (util != null) {
            Service service = util.getService();
            if (service != null) {
                Engine engine = (Engine) service.getContainer();
                if (engine != null) {
                    result = new EngineInfo();
                    result.setName(engine.getName());
                    result.setDefaultHost(engine.getDefaultHost());
                    HostInfo[] hosts = createHosts(result, engine.findChildren());
                    result.setHosts(hosts);
                }
            }
        }


        return result;
    }

    public static HostInfo[] createHosts(EngineInfo engine, Container[] containers) {
        HostInfo[] result = null;
        if (containers != null) {
            result = new HostInfo[containers.length];
            HostInfo tmp;
            Container con;
            for (int i = 0; i < containers.length; i++) {
                con = containers[i];
                tmp = new HostInfo();
                tmp.setName(con.getName());
                tmp.setInfo(con.getInfo());
                tmp.setEngine(engine.getName());
                WebApplicationInfo[] applications = createWebApplications(engine, tmp, con.findChildren());
                tmp.setApplications(applications);
                result[i] = tmp;
            }
        }
        return result;
    }

    private static WebApplicationInfo createWebApplicationInfo(EngineInfo engine, HostInfo host, Container container) {
        WebApplicationInfo result = new WebApplicationInfo();
        result.setName(container.getName());
        result.setInfo(container.getInfo());
        result.setHost(host.getName());
        result.setEngine(engine.getName());
        return result;
    }

    public static WebApplicationInfo[] createWebApplications(EngineInfo engine, HostInfo host, Container[] containers) {
        WebApplicationInfo[] result = null;
        if (containers != null) {
            result = new WebApplicationInfo[containers.length];
            for (int i = 0; i < containers.length; i++) {
                result[i] = createWebApplicationInfo(engine, host, containers[i]);
            }
        }
        return result;
    }
}
