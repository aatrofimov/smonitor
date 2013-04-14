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

import org.lorislab.smonitor.config.model.Configuration;
import java.util.List;

/**
 * The monitor configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class MonitorConfig extends Configuration {

    /**
     * The module name.
     */
    private static final String MODULE = "smonitor";
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2314191168502475822L;
    /**
     * The list of connections.
     */
    private static final String KEY_CONNECTIONS = "connections";
    /**
     * The default value.
     */
    private static final String KEY_CONNECTIONS_DEFAULT = "";

    /**
     * The default constructor.
     */
    public MonitorConfig() {
        super(MODULE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setDefaultValues() {
        setValue(KEY_CONNECTIONS, KEY_CONNECTIONS_DEFAULT);
    }

    /**
     * Gets the connections.
     *
     * @return the connections.
     */
    public List<String> getConnections() {
        return getStringList(KEY_CONNECTIONS);
    }

    /**
     * Sets the connections.
     *
     * @param items the connections.
     */
    public void setConnections(List<String> items) {
        setStringList(KEY_CONNECTIONS, items);
    }
}
