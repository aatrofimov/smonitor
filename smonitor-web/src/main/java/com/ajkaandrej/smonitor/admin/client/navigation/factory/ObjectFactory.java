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
package com.ajkaandrej.smonitor.admin.client.navigation.factory;

import com.ajkaandrej.smonitor.admin.client.app.model.ApplicationDetailsModel;
import com.ajkaandrej.smonitor.admin.client.app.model.SessionTableModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.AppInstanceTreeModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.ApplicationTreeModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.HostTreeModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.ServerTreeModel;
import com.ajkaandrej.smonitor.agent.rs.model.Application;
import com.ajkaandrej.smonitor.agent.rs.model.ApplicationDetails;
import com.ajkaandrej.smonitor.agent.rs.model.Host;
import com.ajkaandrej.smonitor.agent.rs.model.Server;
import com.ajkaandrej.smonitor.agent.rs.model.ServerContext;
import com.ajkaandrej.smonitor.agent.rs.model.Session;
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
    
    public static AppInstanceTreeModel create(Application application, ServerContext context) {
        AppInstanceTreeModel result = new AppInstanceTreeModel();
        result.host = application.getHost();
        result.hostName = context.getHostName();
        result.remote = context.getRemote();
        result.hostPort = context.getPort();
        return result;
    }
    
    public static ApplicationTreeModel create(Application application) {
        ApplicationTreeModel result = new ApplicationTreeModel();
        result.id = application.getId();
        result.name = application.getName();
        return result;
    }
    
    public static List<SessionTableModel> create(ServerContext context, List<Session> sessions) {
        List<SessionTableModel> result = new ArrayList<SessionTableModel>();
        for (Session session : sessions) {
            result.add(create(context, session));
        }
        return result;
    }
    
    public static SessionTableModel create(ServerContext context, Session session) {
        SessionTableModel result = new SessionTableModel();
        result.creationTime = session.getCreationTime();
        result.id = session.getId();
        result.lastAccessedTime = session.getLastAccessedTime();
        result.lastAccessedTimeInternal = session.getLastAccessedTimeInternal();
        result.maxInactiveInterval = session.getMaxInactiveInterval();
        
        result.remote = context.getRemote();
        result.hostName = context.getHostName();
        result.hostPort = context.getPort();
        
        return result;
    }
    
    public static ApplicationDetailsModel create(ApplicationDetails appDetails) {
        ApplicationDetailsModel result = new ApplicationDetailsModel();
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
                result.hosts.add(create(host));                
            }
        }
        return result;
    }

    private static HostTreeModel create(Host host) {
        HostTreeModel result = new HostTreeModel();
        result.id = host.getId();
        result.name = host.getName();
        return result;
    }
}
