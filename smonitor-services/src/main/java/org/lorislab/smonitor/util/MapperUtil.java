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
package org.lorislab.smonitor.util;

import java.util.ArrayList;
import java.util.List;
import org.lorislab.smonitor.connector.model.Attribute;
import org.lorislab.smonitor.connector.model.Session;
import org.lorislab.smonitor.connector.model.SessionDetails;
import org.lorislab.smonitor.datastore.model.AgentData;
import org.lorislab.smonitor.rs.model.AttributeInfo;
import org.lorislab.smonitor.rs.model.SessionInfo;
import org.lorislab.smonitor.rs.model.SessionInfoDetails;

/**
 *
 * @author Andrej Petras
 */
public final class MapperUtil {
        
    public static List<SessionInfo> create(AgentData agent, List<Session> sessions) {
        List<SessionInfo> result = null;
        if (sessions != null) {
            result = new ArrayList<SessionInfo>();
            for (Session session : sessions) {
                SessionInfo info = create(agent, session);
                if (info != null) {
                    result.add(info);
                }
            }
        }
        return result;
    }
    
    public static SessionInfo create(AgentData agent, Session session) {
        SessionInfo result = null;
        if (session != null) {
            result = new SessionInfo();
            result.setGuid(agent.getGuid());
            result.setAgent(agent.getName());
            result.setApplication(session.getApplication());
            result.setCreationTime(session.getCreationTime());
            result.setHost(session.getHost());
            result.setId(session.getId());
            result.setLastAccessedTime(session.getLastAccessedTime());
            result.setLastAccessedTimeInternal(session.getLastAccessedTimeInternal());
            result.setMaxInactiveInterval(session.getMaxInactiveInterval());
            result.setUser(session.getUser());
            result.setValid(session.isValid());
        }
        return result;
    }    
    
    public static SessionInfoDetails create(AgentData agent, SessionDetails session) {
        SessionInfoDetails result = null;
        if (session != null) {
            result = new SessionInfoDetails();            
            result.setSession(create(agent,session.getSession()));
            result.setInfo(session.getInfo());
            result.setNewSession(session.isNewSession());
            result.setRoles(session.getRoles());
            result.setSize(session.getSize());
            result.setSizeSerializable(session.getSizeSerializable());
            result.setAttributes(create(session.getAttributes()));            
        }
        return result;
    }
    
    public static List<AttributeInfo> create(List<Attribute> attributes) {
        List<AttributeInfo> result = null;
        if (attributes != null) {
            result = new ArrayList<AttributeInfo>();
            for (Attribute info : attributes) {
                result.add(create(info));
            }
        }
        return result;
    }
    
    public static AttributeInfo create(Attribute attribute) {
        AttributeInfo result = null;
        if (attribute != null) {
            result = new AttributeInfo();
            result.setName(attribute.getName());
            result.setSerializable(attribute.isSerializable());
            result.setSerializableSize(attribute.getSerializableSize());
            result.setSize(attribute.getSize());
            result.setType(attribute.getType());
        }
        return result;
    }
}
