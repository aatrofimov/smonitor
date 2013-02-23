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

import com.ajkaandrej.smonitor.connector.model.HttpSessionAttribute;
import com.ajkaandrej.smonitor.connector.model.HttpSessionHeader;
import com.ajkaandrej.smonitor.connector.model.HttpSessionUser;
import com.ajkaandrej.smonitor.connector.model.HttpSessionWrapper;
import com.ajkaandrej.smonitor.connector.model.ServerEngine;
import com.ajkaandrej.smonitor.connector.model.ServerHost;
import com.ajkaandrej.smonitor.connector.model.WebApplication;
import com.ajkaandrej.smonitor.connector.model.WebApplicationWrapper;
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
import org.apache.catalina.Session;
import org.apache.catalina.realm.GenericPrincipal;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class TomcatMapper {

    public static HttpSessionWrapper createHttpSessionWrapper(Session session) {
        HttpSessionWrapper result = null;
        if (session != null) {
            result = new HttpSessionWrapper();
            result.setSessionInfo(createHttpSessionHeader(session));
            result.setInfo(session.getInfo());
            result.setAuthType(session.getAuthType());
            // load user information
            result.setUserInfo(createHttpSessionUser((GenericPrincipal) session.getPrincipal()));
            // load HTTP session
            HttpSession httpSession = session.getSession();
            if (httpSession != null) {
                createHttpSessionAttribute(result, httpSession);
                // is new session flag
                result.setNewSession(httpSession.isNew());
            }            
        }
        return result;
    }

    public static WebApplicationWrapper createWebApplicationWrapper(WebApplication webApp, Manager manager) {
        WebApplicationWrapper result = null;
        if (manager != null) {
            result = new WebApplicationWrapper();
            result.setInfo(webApp);
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
            result.setSessions(createHttpSessionHeaders(manager.findSessions()));
        }
        return result;
    }

    public static ServerEngine createServerEngine(Engine engine) {
        ServerEngine result = null;
        if (engine != null) {
            result = new ServerEngine();
            result.setDefaultHost(engine.getDefaultHost());
            result.setName(engine.getName());
            result.setJvmRoute(engine.getJvmRoute());
            result.setHosts(createServerHosts(result.getName(), engine.findChildren()));
        }
        return result;
    }

    private static List<ServerHost> createServerHosts(String engine, Container[] containers) {
        List<ServerHost> result = new ArrayList<ServerHost>();
        if (containers != null) {
            for (Container container : containers) {
                result.add(createServerHost(engine, container));
            }
        }
        return result;
    }

    private static ServerHost createServerHost(String engine, Container container) {
        ServerHost result = null;
        if (container != null) {
            result = new ServerHost();
            result.setName(container.getName());
            result.setInfo(container.getInfo());
            result.setEngine(engine);
            result.setApplications(createWebApplications(engine, result.getName(), container.findChildren()));
        }
        return result;
    }

    private static List<WebApplication> createWebApplications(String engine, String host, Container[] containers) {
        List<WebApplication> result = new ArrayList<WebApplication>();
        if (containers != null) {
            for (Container container : containers) {
                result.add(createWebApplication(engine, host, container));
            }
        }
        return result;
    }

    private static WebApplication createWebApplication(String engine, String host, Container container) {
        WebApplication result = null;
        if (container != null) {
            result = new WebApplication();
            result.setName(container.getName());
            result.setInfo(container.getInfo());
            result.setHost(host);
            result.setEngine(engine);
        }
        return result;
    }

    private static List<HttpSessionHeader> createHttpSessionHeaders(Session[] sessions) {
        List<HttpSessionHeader> result = new ArrayList<HttpSessionHeader>();
        if (sessions != null) {
            for (Session session : sessions) {
                result.add(createHttpSessionHeader(session));
            }
        }
        return result;
    }

    private static HttpSessionHeader createHttpSessionHeader(Session session) {
        HttpSessionHeader result = null;
        if (session != null) {
            result = new HttpSessionHeader();
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

    private static HttpSessionUser createHttpSessionUser(GenericPrincipal principal) {
        HttpSessionUser result = null;
        if (principal != null) {
            result = new HttpSessionUser();
            result.setName(principal.getName());
//                result.setPassword(principal.getPassword());
            result.setRoles(Arrays.asList(principal.getRoles()));
        }
        return result;
    }

    private static void createHttpSessionAttribute(HttpSessionWrapper wrapper, HttpSession session) {
        
        if (wrapper != null && session != null) {

            double size = 0;
            double sizeSerializable = 0;
            
            List<HttpSessionAttribute> result = new ArrayList<HttpSessionAttribute>();
            
            Enumeration enumerator = session.getAttributeNames();
            while (enumerator.hasMoreElements()) {
                String name = (String) enumerator.nextElement();
                Object value = session.getAttribute(name);

                HttpSessionAttribute attr = new HttpSessionAttribute();
                attr.setName(name);
                // load object information
                ObjectProfile objectInfo = ObjectProfiler.createObjectInfo(value);
                attr.setClazz(objectInfo.getClazz());
                attr.setSize(objectInfo.getSize());
                attr.setSerializable(objectInfo.isSerializable());
                attr.setSerializableSize(objectInfo.getSerializableSize());
                result.add(attr);

                size = size + attr.getSize();
                sizeSerializable = sizeSerializable + attr.getSerializableSize();
            }
            
            wrapper.setSize(size);
            wrapper.setSizeSerializable(sizeSerializable);        
            wrapper.setAttributes(result);            
        }
    }
}
