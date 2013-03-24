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
package com.ajkaandrej.smonitor.agent.rs.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionDetails extends Session implements ServerRequest {

    private String info;
    
    private double size;

    private double sizeSerializable;

    private boolean newSession;
    
    private List<String> roles;
    
    private List<Attribute> attributes;
    
    private ServerContext serverContext;
    
    public SessionDetails() {
        attributes = new ArrayList<Attribute>();
        roles = new ArrayList<String>();
        serverContext = new ServerContext();
    }

    @Override
    public ServerContext getServerContext() {
        return serverContext;
    }
        
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
        
    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getSize() {
        return size;
    }

    public double getSizeSerializable() {
        return sizeSerializable;
    }

    public void setNewSession(boolean newSession) {
        this.newSession = newSession;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setSizeSerializable(double sizeSerializable) {
        this.sizeSerializable = sizeSerializable;
    }

    public boolean isNewSession() {
        return newSession;
    }           
}
