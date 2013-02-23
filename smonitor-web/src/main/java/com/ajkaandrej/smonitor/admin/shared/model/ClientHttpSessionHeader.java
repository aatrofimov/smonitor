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
package com.ajkaandrej.smonitor.admin.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ClientHttpSessionHeader implements Serializable {
    
    private static final long serialVersionUID = -3820899850920038192L;    
    
    private String id;
    
    private String user;

    private Date creationTime;
    
    private Date lastAccessedTime;
        
    private boolean valid;
    
    private long lastAccessedTimeInternal;
        
    private int maxInactiveInterval;

    public long getLastAccessedTimeInternal() {
        return lastAccessedTimeInternal;
    }

    public void setLastAccessedTimeInternal(long lastAccessedTimeInternal) {
        this.lastAccessedTimeInternal = lastAccessedTimeInternal;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }
        
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(Date lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
        
    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
        
}
