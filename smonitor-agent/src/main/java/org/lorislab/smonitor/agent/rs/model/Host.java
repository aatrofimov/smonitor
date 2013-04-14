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
package org.lorislab.smonitor.agent.rs.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class Host {
    
    /**
     * The id.
     */
    private String id;
    /**
     * The name.
     */
    private String name;
    /**
     * The list of applications.
     */
    private List<Application> applications;

    /**
     * The default constructor.
     */
    public Host() {
        applications = new ArrayList<Application>();
    }

    /**
     * Gets the id.
     *
     * @return the id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the applications.
     *
     * @return the applications.
     */
    public List<Application> getApplications() {
        return applications;
    }

    /**
     * Sets the applications.
     *
     * @param applications the applications.
     */
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }   
}
