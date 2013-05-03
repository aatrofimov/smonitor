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
package org.lorislab.smonitor.config.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.lorislab.smonitor.config.annotation.Config;

/**
 * The monitor configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Config
public class MonitorConfig implements Serializable {
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2314191168502475822L;

    private List<String> connections;
    
    /**
     * The default constructor.
     */
    public MonitorConfig() {
        connections = new ArrayList<String>();
    }

    /**
     * Gets the connections.
     *
     * @return the connections.
     */    
    public List<String> getConnections() {
        return connections;
    }

    /**
     * Sets the connections.
     *
     * @param connections the connections.
     */    
    public void setConnections(List<String> connections) {
        this.connections = connections;
    }
    
}
