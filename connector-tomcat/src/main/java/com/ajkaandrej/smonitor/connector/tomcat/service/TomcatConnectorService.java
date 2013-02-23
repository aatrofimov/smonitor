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
package com.ajkaandrej.smonitor.connector.tomcat.service;

import com.ajkaandrej.smonitor.connector.model.HttpSessionHeader;
import com.ajkaandrej.smonitor.connector.model.HttpSessionWrapper;
import com.ajkaandrej.smonitor.connector.model.ServerEngine;
import com.ajkaandrej.smonitor.connector.model.WebApplication;
import com.ajkaandrej.smonitor.connector.model.WebApplicationWrapper;
import com.ajkaandrej.smonitor.connector.service.ConnectorService;
import com.ajkaandrej.smonitor.tomcat.mapper.TomcatMapper;
import com.ajkaandrej.smonitor.tomcat.server.TomcatServer;
import org.apache.catalina.Engine;
import org.apache.catalina.Manager;
import org.apache.catalina.Service;
import org.apache.catalina.Session;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class TomcatConnectorService implements ConnectorService {

    public HttpSessionWrapper getHttpSessionWrapper(WebApplication webApp, HttpSessionHeader header) {
        HttpSessionWrapper result = null;
        if (webApp != null && header != null) {
            Session session = TomcatServer.getInstance().findSession(webApp.getEngine(), webApp.getHost(), webApp.getName(), header.getId());
            if (session != null) {
                result = TomcatMapper.createHttpSessionWrapper(session);
            }
        }
        return result;
    }

    public WebApplicationWrapper getWebApplicationWrapper(WebApplication webApp) {
        WebApplicationWrapper result = null;
        if (webApp != null) {
            Manager manager = TomcatServer.getInstance().findWebAppManager(webApp.getEngine(), webApp.getHost(), webApp.getName());
            if (manager != null) {
                result = TomcatMapper.createWebApplicationWrapper(webApp, manager);
            }
        }        
        return result;
    }

    public ServerEngine getServerEngine() {
        ServerEngine result = null;
        Service service = TomcatServer.getInstance().getService();
        if (service != null) {
            Engine engine = (Engine) service.getContainer();
            if (engine != null) {
                
                result = TomcatMapper.createServerEngine(engine);
            }
        }
        return result;
    }

}
