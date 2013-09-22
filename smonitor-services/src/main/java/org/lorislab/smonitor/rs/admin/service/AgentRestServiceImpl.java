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
package org.lorislab.smonitor.rs.admin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.lorislab.smonitor.rs.admin.model.Agent;
import org.lorislab.smonitor.rs.admin.model.ChangeAgentKeyRequest;
import org.lorislab.smonitor.datastore.model.AgentData;
import org.lorislab.smonitor.datastore.service.AgentDataService;
import org.lorislab.smonitor.rs.exception.ServiceException;
import org.lorislab.smonitor.service.ServiceFactory;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class AgentRestServiceImpl implements AgentRestService {

    private AgentDataService service;

    public AgentRestServiceImpl() {
        service = ServiceFactory.getAgentDataService();
    }

    @Override
    public List<Agent> get() {
        List<Agent> result = new ArrayList<Agent>();
        Collection<AgentData> items = service.findAll();
        if (items != null) {
            for (AgentData item : items) {
                Agent tmp = create(item);
                result.add(tmp);
            }
        }
        return result;
    }

    @Override
    public Agent get(String guid) {
        Agent result = null;
        AgentData item = service.findByBuid(guid);
        if (item != null) {
            result = create(item);
        }
        return result;
    }

    @Override
    public Agent create() throws ServiceException {
        return create(new AgentData());
    }

    @Override
    public Agent update(String guid, Agent data) {
        Agent result = null;
        AgentData item = service.findByBuid(guid);
        item = update(item, data);
        item = service.save(item);
        if (item != null) {
            result = create(item);
        }
        return result;
    }

    @Override
    public String delete(String guid) {
        return service.delete(guid);
    }

    @Override
    public boolean changePassword(ChangeAgentKeyRequest data) {
        boolean result = false;
        if (data != null) {
            if (data.getKey1() != null && data.getKey2() != null) {
                AgentData tmp = service.findByBuid(data.getGuid());
                if (tmp != null) {
                    if (tmp.getKey() == null && data.getKey1() == null) {
                        tmp.setKey(data.getKey2());
                        service.save(tmp);
                    } else {
                        if (tmp.getKey() != null && tmp.getKey().equals(data.getKey1())) {
                            tmp.setKey(data.getKey1());
                            service.save(tmp);
                        } else {
                            // LOG
                        }
                    }
                } else {
                    // LOG
                }
            } else {
                // LOG
            }
        }
        return result;
    }

    private static AgentData update(AgentData item, Agent data) {
        if (item == null) {
            item = new AgentData();
        }
        item.setName(data.getName());
        item.setServer(data.getServer());
        item.setEnabled(data.isEnabled());   
        return item;
    }
    
    private static Agent create(AgentData data) {
        Agent result = new Agent();
        result.setGuid(data.getGuid());
        result.setEnabled(data.isEnabled());
        result.setName(data.getName());
        result.setServer(data.getServer());
        return result;
    }
}
