/*
 * Copyright 2013 lorislab.org.
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
package org.lorislab.smonitor.rs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lorislab.smonitor.connector.model.Session;
import org.lorislab.smonitor.connector.model.SessionCriteria;
import org.lorislab.smonitor.connector.model.SessionDetails;
import org.lorislab.smonitor.datastore.criteria.AgentDataSearchCriteria;
import org.lorislab.smonitor.datastore.model.AgentData;
import org.lorislab.smonitor.datastore.service.AgentDataService;
import org.lorislab.smonitor.rs.exception.ServiceException;
import org.lorislab.smonitor.rs.model.SessionInfo;
import org.lorislab.smonitor.rs.model.SessionInfoDetails;
import org.lorislab.smonitor.rs.model.SessionSearchCriteria;
import org.lorislab.smonitor.service.ServiceFactory;
import org.lorislab.smonitor.util.MapperUtil;
import org.lorislab.smonitor.util.RSClientUtil;
import org.lorislabr.smonitor.agent.rs.client.service.ApplicationClientService;

/**
 *
 * @author Andrej Petras
 */
public final class ApplicationServiceImpl implements ApplicationService {

    private static final Logger LOGGER = Logger.getLogger(ApplicationServiceImpl.class.getName());
    private AgentDataService service;

    public ApplicationServiceImpl() {
        service = ServiceFactory.getAgentDataService();
    }

    @Override
    public SessionInfo getSesssion(String guid, String host, String application, String id) {
        SessionInfo result = null;

        AgentData data = service.findByBuid(guid);
        if (data != null) {
            if (data.isEnabled()) {
                ApplicationClientService appService = new ApplicationClientService(data.getServer(), data.getKey());
                try {
                    Session session = appService.getSession(host, application, id);
                    result = MapperUtil.create(data, session);
                } catch (Exception ex) {
                    RSClientUtil.handleException(guid, ex);
                }
            } else {
                // disabled
            }
        } else {
            // not found
        }
        return result;
    }

    @Override
    public SessionInfoDetails getSesssionDetails(String guid, String host, String application, String id) {
        SessionInfoDetails result = null;

        AgentData data = service.findByBuid(guid);
        if (data != null) {
            if (data.isEnabled()) {
                ApplicationClientService appService = new ApplicationClientService(data.getServer(), data.getKey());
                try {
                    SessionDetails session = appService.getSessionDetails(host, application, id);
                    result = MapperUtil.create(data, session);
                } catch (Exception ex) {
                    RSClientUtil.handleException(guid, ex);
                }
            } else {
                // disabled
            }
        } else {
            // not found
        }
        return result;
    }
    
    @Override
    public String deleteSesssion(String guid, String host, String application, String id) {
        String result = null;

        AgentData data = service.findByBuid(guid);
        if (data != null) {
            if (data.isEnabled()) {
                ApplicationClientService appService = new ApplicationClientService(data.getServer(), data.getKey());
                try {
                    Session session = appService.deleteSession(host, application, id);
                    result = session.getId();
                } catch (Exception ex) {
                    RSClientUtil.handleException(guid, ex);
                }
            } else {
                // disabled
            }
        } else {
            // not found
        }
        return result;
    }
    

    @Override
    public List<SessionInfo> findSessions(SessionSearchCriteria criteria) {
        List<SessionInfo> result = new ArrayList<SessionInfo>();
        if (criteria != null) {

            AgentDataSearchCriteria tmp = new AgentDataSearchCriteria();
            tmp.setEnabled(true);
            tmp.setGuids(criteria.getAgents());
            List<AgentData> agents = service.findByCriteria(tmp);
            if (agents != null) {
                for (AgentData agent : agents) {

                    try {
                        ApplicationClientService appService = new ApplicationClientService(agent.getServer(), agent.getKey());
                        try {
                            SessionCriteria sessionCriteria = new SessionCriteria();
                            sessionCriteria.setApplications(criteria.getApplications());

                            List<Session> sessions = appService.findSessionByCriteria(sessionCriteria);
                            List<SessionInfo> infos = MapperUtil.create(agent, sessions);
                            if (infos != null) {
                                result.addAll(infos);
                            }
                        } catch (Exception ex) {
                            RSClientUtil.handleException(agent.getGuid(), ex);
                        }
                    } catch (ServiceException ex) {
                        LOGGER.log(Level.SEVERE, "Error search session in the agent " + agent.getName(), ex);
                    }
                }
            }
        }
        return result;
    }
}
