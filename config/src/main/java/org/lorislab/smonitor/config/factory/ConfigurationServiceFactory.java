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
package org.lorislab.smonitor.config.factory;

import org.lorislab.smonitor.config.service.ConfigurationService;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ConfigurationServiceFactory {
    
    private static ConfigurationService service = null;
    
    static {
        ServiceLoader<ConfigurationService> list = ServiceLoader.load(ConfigurationService.class);
        if (list != null) {
            Iterator<ConfigurationService> iter = list.iterator();
            if (iter.hasNext()) {
                service = iter.next();
            }
        }        
    }
    
    private ConfigurationServiceFactory() {
        
    }
    
    public static ConfigurationService getService() {
        return service;
    }    
}