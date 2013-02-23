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
package com.ajkaandrej.smonitor.connector.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class HttpSessionWrapper implements Serializable {
    
    private static final long serialVersionUID = -2879705004383931921L;
    
    private HttpSessionHeader sessionInfo;

    private HttpSessionUser userInfo;
    
    private List<HttpSessionAttribute> attributes;

    private String info;
    
    private String authType;
    
    private double size;

    private double sizeSerializable;

    private boolean newSession;

    public HttpSessionUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(HttpSessionUser userInfo) {
        this.userInfo = userInfo;
    }
    
    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }
    
    public void setNewSession(boolean newSession) {
        this.newSession = newSession;
    }

    public boolean isNewSession() {
        return newSession;
    }
        
    public double getSizeSerializable() {
        return sizeSerializable;
    }

    public void setSizeSerializable(double sizeSerializable) {
        this.sizeSerializable = sizeSerializable;
    }
        
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
        
    public List<HttpSessionAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<HttpSessionAttribute> attributes) {
        this.attributes = attributes;
    }
        
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public HttpSessionHeader getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(HttpSessionHeader sessionInfo) {
        this.sessionInfo = sessionInfo;
    }
    
    
        
}
