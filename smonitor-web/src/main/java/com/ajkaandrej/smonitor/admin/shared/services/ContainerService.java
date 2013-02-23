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
package com.ajkaandrej.smonitor.admin.shared.services;

import com.ajkaandrej.smonitor.admin.shared.exception.ServiceException;
import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionHeader;
import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionWrapper;
import com.ajkaandrej.smonitor.admin.shared.model.ClientServerEngine;
import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplication;
import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplicationWrapper;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@RemoteServiceRelativePath("ContainerService")
public interface ContainerService extends RemoteService {
    
    ClientServerEngine getServerEngine() throws ServiceException;
    
    ClientWebApplicationWrapper getWebApplicationWrapper(ClientWebApplication info) throws ServiceException;
    
    ClientHttpSessionWrapper getHttpSessionWrapper(ClientWebApplication webApp, ClientHttpSessionHeader info) throws ServiceException;
}
