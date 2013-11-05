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
package org.lorislab.smonitor.datastore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import org.lorislab.smonitor.datastore.db.DataStore;
import org.lorislab.smonitor.datastore.model.AgentData;
import org.lorislab.smonitor.datastore.model.AgentDataSerializer;
import org.lorislab.smonitor.datastore.resources.ErrorKeys;
import org.lorislab.smonitor.base.exception.ServiceException;

/**
 * The agent data service.
 * 
 * @author Andrej Petras
 */
public class AgentDataService {

    private ConcurrentNavigableMap<String, AgentData> map;
    
    public AgentDataService() {
        map = DataStore.getSmallMap(AgentDataService.class, new AgentDataSerializer());        
    }
    
    public Collection<AgentData> findAll() {
        return map.values();
    }
    
    public List<AgentData> findEnabledAgents(Set<String> guids) {
        List<AgentData> result = new ArrayList<AgentData>();
        if (guids != null && !guids.isEmpty()) {
            for (String guid : guids) {
                AgentData tmp = map.get(guid);
                if (tmp != null && tmp.isEnabled()) {
                    result.add(tmp);
                }
            }
        } else {
            Collection<AgentData> agents = findAll();
            if (agents != null) {
                for (AgentData agent: agents) {
                    if (agent.isEnabled()) {
                        result.add(agent);
                    }
                }
            }
        }
        return result;
    }
    
    public AgentData findByBuid(String guid) {
        return map.get(guid);
    }
    
    public AgentData save(AgentData data) {
        map.put(data.getGuid(), data);
        DataStore.commit();
        return data;
    }
    
    public String delete(String guid) throws ServiceException {
        String result = null;
        AgentData tmp = map.remove(guid);
        if (tmp == null) {
            throw new ServiceException(ErrorKeys.AGENT_DELETE_FAILED, guid);
        } else {
            DataStore.commit();
            result = tmp.getGuid();
        }
        return result;
    }
    
}
