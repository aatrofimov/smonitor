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

import com.ajkaandrej.smonitor.connector.model.HttpSessionWrapper;
import com.ajkaandrej.smonitor.connector.model.ServerEngine;
import com.ajkaandrej.smonitor.connector.model.WebApplicationWrapper;
import com.ajkaandrej.smonitor.connector.service.ConnectorService;
import com.ajkaandrej.smonitor.tomcat.mapper.TomcatMapper;
import com.ajkaandrej.smonitor.tomcat.server.TomcatServer;
import org.apache.catalina.Container;
import org.apache.catalina.Engine;
import org.apache.catalina.Service;
import org.apache.catalina.Session;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class TomcatConnectorService implements ConnectorService {

    public HttpSessionWrapper getHttpSessionWrapper(String engine, String host, String name, String session) {
        HttpSessionWrapper result = null;
        Session tmp = TomcatServer.getInstance().findSession(engine, host, name, session);
        if (tmp != null) {
            result = TomcatMapper.createHttpSessionWrapper(tmp);
        }
        return result;
    }

    public WebApplicationWrapper getWebApplicationWrapper(String engine, String host, String name) {
        WebApplicationWrapper result = null;
        Container container = TomcatServer.getInstance().findWebAppContainer(engine, host, name);
        if (container != null) {
            result = TomcatMapper.createWebApplicationWrapper(engine, host, container);
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
