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
package com.ajkaandrej.smonitor.admin.client.model;

import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplication;
import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplicationWrapper;
import com.smartgwt.client.widgets.viewer.DetailViewerRecord;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class WebAppDetailViewerRecord extends DetailViewerRecord {
    
    public static final String ATTR_NAME = "name";
    
    public static final String ATTR_HOST = "host";
    
    public static final String ATTR_INFO = "info";
    
    public static final String ATTR_ACTIVE_SESSION = "activeSession";
    
    public static final String ATTR_EXPIRED_SESSION = "expiredSession";
    
    public static final String ATTR_MAX_ACTIVE = "maxActive";
        
    public static final String ATTR_MAX_INACTIVE_INTERVAL = "maxInacInt";
    
    public static final String ATTR_REJECTED_SESSIONS = "sesRejected";
    
    public static final String ATTR_SESSION_AVERAGE_LIVE_TIME = "sesAvgLive";
    
    public static final String ATTR_SESSION_COUNTER = "sesCounter";
    
    public static final String ATTR_SESSION_ID_LENGTH = "sesIdLength";
    
    public static final String ATTR_SESSION_MAX_LIVE_TIME = "sesMaxLive";
    
    public static final String ATTR_OBJECT = "object";
    
    
    public WebAppDetailViewerRecord(ClientWebApplicationWrapper webApp) {
        
        ClientWebApplication info = webApp.getInfo();
        setAttribute(ATTR_NAME, info.getName());
        setAttribute(ATTR_HOST, info.getHost());
        setAttribute(ATTR_INFO, info.getInfo());  
        
        setAttribute(ATTR_ACTIVE_SESSION, webApp.getActiveSessions());
        setAttribute(ATTR_EXPIRED_SESSION, webApp.getExpiredSessions());
        setAttribute(ATTR_MAX_ACTIVE, webApp.getMaxActive());
        setAttribute(ATTR_MAX_INACTIVE_INTERVAL, webApp.getMaxInactiveInterval());
        setAttribute(ATTR_REJECTED_SESSIONS, webApp.getRejectedSessions());
        setAttribute(ATTR_SESSION_AVERAGE_LIVE_TIME, webApp.getSessionAverageAliveTime());
        setAttribute(ATTR_SESSION_COUNTER, webApp.getSessionCounter());        
        setAttribute(ATTR_SESSION_ID_LENGTH, webApp.getSessionIdLength());
        setAttribute(ATTR_SESSION_MAX_LIVE_TIME, webApp.getSessionMaxAliveTime());
        setAttribute(ATTR_OBJECT, webApp);
    }
    
    public ClientWebApplicationWrapper getObject() {
        return (ClientWebApplicationWrapper) getAttributeAsObject(ATTR_OBJECT);
    }
}
