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
package org.lorislab.smonitor.store.factory;

import java.util.Iterator;
import java.util.ServiceLoader;
import org.lorislab.smonitor.store.service.ConfigurationStoreService;

/**
 * The configuration service factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ConfigurationStoreServiceFactory {

    /**
     * The configuration service instance.
     */
    private static ConfigurationStoreService SERVICE = null;

    /**
     * The static block.
     */
    static {
        ServiceLoader<ConfigurationStoreService> list = ServiceLoader.load(ConfigurationStoreService.class);
        if (list != null) {
            Iterator<ConfigurationStoreService> iter = list.iterator();
            if (iter.hasNext()) {
                SERVICE = iter.next();
            }
        }
    }

    /**
     * The default private constructor
     */
    private ConfigurationStoreServiceFactory() {
        // empty constructor
    }

    /**
     * Gets the configuration store service.
     *
     * @return the configuration service store instance.
     */
    public static ConfigurationStoreService getService() {
        return SERVICE;
    }
}
