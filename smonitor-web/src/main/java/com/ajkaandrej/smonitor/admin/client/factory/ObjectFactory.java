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
package com.ajkaandrej.smonitor.admin.client.factory;

import com.ajkaandrej.smonitor.admin.client.app.model.ApplicationDetailsModel;
import com.ajkaandrej.smonitor.admin.client.app.model.AttributeTableModel;
import com.ajkaandrej.smonitor.admin.client.app.model.SessionDetailsModel;
import com.ajkaandrej.smonitor.admin.client.app.model.SessionTableModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.AppInstanceTreeModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.ApplicationTreeModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.HostTreeModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.ServerTreeModel;
import com.ajkaandrej.smonitor.agent.rs.model.Application;
import com.ajkaandrej.smonitor.agent.rs.model.ApplicationDetails;
import com.ajkaandrej.smonitor.agent.rs.model.Attribute;
import com.ajkaandrej.smonitor.agent.rs.model.Host;
import com.ajkaandrej.smonitor.agent.rs.model.Server;
import com.ajkaandrej.smonitor.agent.rs.model.ServerContext;
import com.ajkaandrej.smonitor.agent.rs.model.Session;
import com.ajkaandrej.smonitor.agent.rs.model.SessionDetails;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ObjectFactory {

    private ObjectFactory() {
        // empty constructor
    }

    public static List<AttributeTableModel> createAttributes(SessionDetails session) {
        List<AttributeTableModel> result = new ArrayList<AttributeTableModel>();

        List<Attribute> attributes = session.getAttributes();
        ServerContext context = session.getServerContext();
        if (attributes != null) {
            for (Attribute attribute : attributes) {
                result.add(create(context, attribute));
            }
        }
        return result;
    }

    public static AttributeTableModel create(ServerContext context, Attribute attribute) {
        AttributeTableModel result = new AttributeTableModel();
        result.name = attribute.getName();
        result.type = attribute.getType();
        result.serializable = attribute.isSerializable();
        result.serializableSize = attribute.getSerializableSize();
        result.size = attribute.getSize();
        return result;
    }

    public static SessionDetailsModel create(SessionDetails session) {
        SessionDetailsModel result = new SessionDetailsModel();

        result.host = session.getHost();
        result.application = session.getApplication();
        result.creationTime = session.getCreationTime();
        result.id = session.getId();
        result.info = session.getInfo();
        result.lastAccessedTime = session.getLastAccessedTime();
        result.lastAccessedTimeInternal = session.getLastAccessedTimeInternal();
        result.maxInactiveInterval = session.getMaxInactiveInterval();
        result.newSession = session.isNewSession();
        result.roles = session.getRoles();
        result.size = session.getSize();
        result.sizeSerializable = session.getSizeSerializable();
        result.user = session.getUser();
        result.valid = session.isValid();

        ServerContext context = session.getServerContext();
        result.hostName = context.getHostName();
        result.hostPort = context.getPort();
        result.remote = context.getRemote();

        return result;
    }

    public static AppInstanceTreeModel create(Application application, ServerContext context) {
        AppInstanceTreeModel result = new AppInstanceTreeModel();
        result.host = application.getHost();
        result.hostName = context.getHostName();
        result.remote = context.getRemote();
        result.hostPort = context.getPort();
        result.id = application.getId();
        return result;
    }

    public static ApplicationTreeModel create(Application application) {
        ApplicationTreeModel result = new ApplicationTreeModel();
        result.id = application.getId();
        result.name = application.getName();
        return result;
    }

    public static List<SessionTableModel> createSessions(ApplicationDetails application) {
        List<SessionTableModel> result = new ArrayList<SessionTableModel>();

        if (application != null) {
            ServerContext context = application.getServerContext();
            String id = application.getId();
            String host = application.getHost();
            if (application.getSessions() != null) {
                for (Session session : application.getSessions()) {
                    result.add(create(host, id, context, session));
                }
            }
        }
        return result;
    }

    public static SessionTableModel create(String host, String application, ServerContext context, Session session) {
        SessionTableModel result = new SessionTableModel();
        result.creationTime = session.getCreationTime();
        result.id = session.getId();
        result.lastAccessedTime = session.getLastAccessedTime();
        result.lastAccessedTimeInternal = session.getLastAccessedTimeInternal();
        result.maxInactiveInterval = session.getMaxInactiveInterval();
        result.application = application;
        result.host = host;
        result.remote = context.getRemote();
        result.hostName = context.getHostName();
        result.hostPort = context.getPort();

        return result;
    }

    public static ApplicationDetailsModel create(ApplicationDetails app) {
        ApplicationDetailsModel result = new ApplicationDetailsModel();
        result.id = app.getId();
        result.name = app.getName();

        result.host = app.getHost();
        result.hostName = app.getServerContext().getHostName();
        result.hostPort = app.getServerContext().getPort();
        result.remote = app.getServerContext().getRemote();
        result.scheme = app.getServerContext().getScheme();
        result.context = app.getContext();
        result.startTime = app.getStartTime();

        result.activeSessions = app.getActiveSessions();
        result.distributable = app.isDistributable();
        result.expiredSessions = app.getExpiredSessions();
        result.maxActive = app.getMaxActive();
        result.maxInactiveInterval = app.getMaxInactiveInterval();
        result.rejectedSessions = app.getRejectedSessions();
        result.sessionAverageAliveTime = app.getSessionAverageAliveTime();
        result.sessionCounter = app.getSessionCounter();
        result.sessionIdLength = app.getSessionIdLength();

        result.sessionMaxAliveTime = app.getSessionMaxAliveTime();
        return result;
    }

    public static ServerTreeModel create(Server server) {
        ServerTreeModel result = new ServerTreeModel();
        ServerContext context = server.getServerContext();
        result.remote = context.getRemote();
        result.hostName = context.getHostName();
        result.hostPort = context.getPort();

        if (server.getHosts() != null) {
            for (Host host : server.getHosts()) {
                result.hosts.add(create(result, host));
            }
        }
        return result;
    }

    private static HostTreeModel create(ServerTreeModel server, Host host) {
        HostTreeModel result = new HostTreeModel();
        result.id = host.getId();
        result.name = host.getName();
        result.remote = server.remote;
        return result;
    }
}
