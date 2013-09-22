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
package org.lorislab.smonitor.datastore.model;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Andrej Petras
 */
public class AgentData implements Serializable {

    private static final long serialVersionUID = 4319212340865836308L;
    private String guid = UUID.randomUUID().toString();
    private String oldGuid = guid;
    private String name;
    private String server;
    private String key;
    private boolean enabled;

    public boolean isNew() {
        return guid.equals(oldGuid);
    }

    public String getGuid() {
        return guid;
    }

    protected void setGuid(String guid) {
        this.guid = guid;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AgentData data = (AgentData) o;
        return guid.equals(data.guid);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.guid != null ? this.guid.hashCode() : 0);
        return hash;
    }
}
