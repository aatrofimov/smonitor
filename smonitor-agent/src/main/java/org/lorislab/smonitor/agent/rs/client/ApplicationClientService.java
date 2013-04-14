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
package org.lorislab.smonitor.agent.rs.client;

import org.lorislab.smonitor.agent.rs.exception.ServiceException;
import org.lorislab.smonitor.agent.rs.model.Application;
import org.lorislab.smonitor.agent.rs.model.ApplicationDetails;
import org.lorislab.smonitor.agent.rs.model.AttributeDetails;
import org.lorislab.smonitor.agent.rs.model.SessionDetails;
import org.lorislab.smonitor.agent.rs.service.ApplicationService;
import java.util.List;

/**
 * The application client service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationClientService extends AbstractClientService<ApplicationService> {

    /**
     * The default constructor.
     *
     * @param remote the remote server.
     */
    public ApplicationClientService(String remote) {
        super(ApplicationService.class, remote);
    }

    /**
     * Gets the list of applications.
     *
     * @return the list of applications.
     * @throws ServiceException if the method fails.
     */
    public List<Application> getApplications() throws ServiceException {
        return getService().getApplications(null);
    }

    /**
     * Gets the application details.
     *
     * @param host the host.
     * @param name the name.
     * @return the application details.
     * @throws ServiceException if the method fails.
     */
    public ApplicationDetails getApplication(String host, String name) throws ServiceException {
        ApplicationDetails result = getService().getApplication(host, name, null);
        updateServerRequest(result);
        return result;
    }

    /**
     * Gets the session details.
     *
     * @param host the host.
     * @param application the application.
     * @param id the session id.
     * @return the session.
     * @throws ServiceException if the method fails.
     */
    public SessionDetails getSession(String host, String application, String id) throws ServiceException {
        SessionDetails result = getService().getSession(host, application, id, null);
        updateServerRequest(result);
        return result;
    }

    /**
     * Gets the attribute.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @param name the name.
     * @return the attribute.
     * @throws ServiceException if the method fails.
     */
    public AttributeDetails getAttribute(String host, String application, String session, String name) throws ServiceException {
        AttributeDetails result = getService().getAttribute(host, application, session, name, null);
        updateServerRequest(result);
        return result;
    }

    /**
     * Updates the attribute.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @param name the name.
     * @param attribute the attribute.
     * @return the updated attribute,
     * @throws ServiceException if the method fails.
     */
    public AttributeDetails updateAttribute(String host, String application, String session, String name, AttributeDetails attribute) throws ServiceException {
        AttributeDetails result = getService().updateAttribute(host, application, session, name, attribute, null);
        updateServerRequest(result);
        return result;
    }

    /**
     * Deletes attribute.
     *
     * @param host the host.
     * @param application the application.
     * @param session the session.
     * @param name the name.
     * @throws ServiceException if the method fails.
     */
    public void deleteAttribute(String host, String application, String session, String name) throws ServiceException {
        getService().deleteAttribute(host, application, session, name, null);
    }
}
