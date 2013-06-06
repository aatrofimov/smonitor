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
package org.lorislab.smonitor.rs.model;

import java.util.List;

/**
 *
 * @author Andrej Petras
 */
public class ServerInfo {
    
    private String guid;
    
    /**
     * The id.
     */
    private String id;
    /**
     * The name.
     */
    private String name;
    
    /**
     * The server applications.
     */
    private List<ServerApplication> applications;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServerApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<ServerApplication> applications) {
        this.applications = applications;
    }
        
}
