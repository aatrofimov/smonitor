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
package com.ajkaandrej.smonitor.agent.rs.client;

import com.ajkaandrej.smonitor.agent.rs.exception.ServiceException;
import com.ajkaandrej.smonitor.agent.rs.model.Application;
import com.ajkaandrej.smonitor.agent.rs.model.ApplicationDetails;
import com.ajkaandrej.smonitor.agent.rs.model.AttributeDetails;
import com.ajkaandrej.smonitor.agent.rs.model.SessionDetails;
import com.ajkaandrej.smonitor.agent.rs.service.ApplicationService;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationClientService extends AbstractClientService<ApplicationService> {

    public ApplicationClientService(String remote) {
        super(ApplicationService.class, remote);
    }
    
    public List<Application> getApplications(String host) throws ServiceException {
        return getService().getApplications(host, null);
    }

    public ApplicationDetails getApplication(String host, String name) throws ServiceException {
        return getService().getApplication(host, name, null);
    }

    public SessionDetails getSession(String host, String application, String id) throws ServiceException {
        return getService().getSession(host, application, id, null);
    }

    public AttributeDetails getAttribute(String host, String application, String session, String name) throws ServiceException {
        return getService().getAttribute(host, application, session, name, null);
    }

    public AttributeDetails updateAttribute(String host, String application, String session, String name, AttributeDetails attribute) throws ServiceException {
        return getService().updateAttribute(host, application, session, name, attribute, null);
    }

    public void deleteAttribute(String host, String application, String session, String name) throws ServiceException {
        getService().deleteAttribute(host, application, session, name, null);
    }
    
}
