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
package com.ajkaandrej.smonitor.connector.factory;

import com.ajkaandrej.smonitor.connector.service.ConnectorService;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * The connector factory.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ConnectorServiceFactory {
   
    /** The connector service. */
    private static ConnectorService service = null;
    
    /**
     * Load the connector service implementation
     */
    static {
        ServiceLoader<ConnectorService> list = ServiceLoader.load(ConnectorService.class);
        if (list != null) {
            Iterator<ConnectorService> iter = list.iterator();
            if (iter.hasNext()) {
                service = iter.next();
            }
        }        
    }
    
    /**
     * The default constructor
     */
    private ConnectorServiceFactory() {
        // empty constructor
    }
    
    /**
     * Gets the connector service instance.
     * 
     * @return the connector service instance.
     */
    public static ConnectorService getService() {
        return service;
    }
}
