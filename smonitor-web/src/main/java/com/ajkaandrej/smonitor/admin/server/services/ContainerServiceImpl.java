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
package com.ajkaandrej.smonitor.admin.server.services;

import com.ajkaandrej.smonitor.admin.shared.exception.ServiceException;
import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionHeader;
import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionWrapper;
import com.ajkaandrej.smonitor.admin.shared.model.ClientServerEngine;
import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplication;
import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplicationWrapper;
import com.ajkaandrej.smonitor.admin.shared.services.ContainerService;
import com.ajkaandrej.smonitor.connector.model.HttpSessionHeader;
import com.ajkaandrej.smonitor.connector.model.HttpSessionWrapper;
import com.ajkaandrej.smonitor.connector.model.ServerEngine;
import com.ajkaandrej.smonitor.connector.model.WebApplication;
import com.ajkaandrej.smonitor.connector.model.WebApplicationWrapper;
import com.ajkaandrej.smonitor.connector.service.ConnectorService;
import com.ajkaandrej.smonitor.connector.tomcat.service.TomcatConnectorService;
import com.ajkaandrej.smonitor.mapper.ObjectMapper;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.logging.Logger;
import javax.servlet.ServletException;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ContainerServiceImpl extends RemoteServiceServlet implements ContainerService {

    private static final long serialVersionUID = 5084673839095891171L;

    private static final Logger LOGGER = Logger.getLogger(ContainerServiceImpl.class.getName());

    private ConnectorService connectorService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        connectorService = new TomcatConnectorService();
    }
            
    @Override
    public ClientServerEngine getServerEngine() throws ServiceException {
        ClientServerEngine result = null;        
        ServerEngine server = connectorService.getServerEngine();
        if (server != null) {
            result = new ClientServerEngine();
            ObjectMapper.getInstance().map(server, result);
        }
        return result;        
    }
    
    @Override
    public ClientWebApplicationWrapper getWebApplicationWrapper(ClientWebApplication info) throws ServiceException {
        ClientWebApplicationWrapper result = null;                
        if (info != null) {
            WebApplication webApp = ObjectMapper.getInstance().map(info, WebApplication.class);            
            WebApplicationWrapper wrapper = connectorService.getWebApplicationWrapper(webApp);
            
            if (wrapper != null) {
                result = ObjectMapper.getInstance().map(wrapper, ClientWebApplicationWrapper.class);
            }
        }
        return result;          
    }
    
    @Override
    public ClientHttpSessionWrapper getHttpSessionWrapper(ClientWebApplication webApp, ClientHttpSessionHeader info) throws ServiceException {
        ClientHttpSessionWrapper result = null;
        if (webApp != null && info != null) {            
            WebApplication wApp = ObjectMapper.getInstance().map(webApp, WebApplication.class);
            HttpSessionHeader header = ObjectMapper.getInstance().map(info, HttpSessionHeader.class);            
            HttpSessionWrapper wrapper = connectorService.getHttpSessionWrapper(wApp, header);            
            if (wrapper != null) {
                result = ObjectMapper.getInstance().map(wrapper, ClientHttpSessionWrapper.class);
            }
        }
        return result;
    }
   
}
