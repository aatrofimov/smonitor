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
package org.lorislab.smonitor.admin.client.app.model;

import java.util.Date;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationDetailsModel {
    
    public String id;    
    public String name;    
    public String host;
    public String hostName;
    public String scheme;
    public int hostPort;
    public String remote;
    public String context;
    public Date startTime;
    
    public int activeSessions;
    public boolean distributable;
    public int expiredSessions;
    public int maxActive;
    public int maxInactiveInterval;
    public int rejectedSessions;
    public int sessionAverageAliveTime;
    public int sessionCounter;
    public int sessionIdLength;
    public int sessionMaxAliveTime;
    
}
