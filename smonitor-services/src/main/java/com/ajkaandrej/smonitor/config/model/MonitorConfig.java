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
package com.ajkaandrej.smonitor.config.model;

import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class MonitorConfig extends Configuration {

    private static final String MODULE = "smonitor";
    
    private static final long serialVersionUID = -2314191168502475822L;
    
    private static final String KEY_CONNECTIONS = "connections";
    
    private static final String KEY_CONNECTIONS_DEFAULT = "";
    
    public MonitorConfig() {
        super(MODULE);
    }
    
    @Override
    protected void setDefaultValues() {
        setValue(KEY_CONNECTIONS, KEY_CONNECTIONS_DEFAULT);
    }
    
    public List<String> getConnections() {
        return getStringList(KEY_CONNECTIONS);
    }
    
    public void setConnections(List<String> items) {
        setStringList(KEY_CONNECTIONS, items);
    }
}
