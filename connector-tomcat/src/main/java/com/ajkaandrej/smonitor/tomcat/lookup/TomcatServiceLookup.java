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
package com.ajkaandrej.smonitor.tomcat.lookup;

import org.apache.catalina.Server;
import org.apache.catalina.Service;

/**
 * The tomcat service look-up class.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class TomcatServiceLookup {

    /**
     * The name.
     */
    private String name;

    /**
     * The default constructor.
     *
     * @param name the name.
     */
    public TomcatServiceLookup(String name) {
        this.name = name;
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
     * Gets the tomcat server instance.
     *
     * @return the tomcat server instance.
     */
    public abstract Server getServer();

    /**
     * The tomcat server service instance.
     *
     * @return the tomcat server service instance.
     */
    public abstract Service getService();
}
