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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class TomcatServiceLookupFactory {

    private static final Logger LOGGER = Logger.getLogger(TomcatServiceLookupFactory.class.getName());
    
    private TomcatServiceLookupFactory() {
    }

    private static Class<? extends TomcatServiceLookup> getServiceLookupClass() {
        Class<? extends TomcatServiceLookup> result = null;
        String version = null;
        
        MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
        
        // check jboss 7
        try {
            version = (String) mBeanServer.getAttribute(new ObjectName("jboss.as:management-root=server"), "releaseVersion");
            result = JBoss7TomcatServiceLookup.class;
        } catch (Exception ex) {
            // do nothing
        }
        
        // check jboss 6
        if (result == null) {
            try {
                version = (String) mBeanServer.getAttribute(new ObjectName("jboss.system:type=Server"), "Version");
                result = JBossTomcatServiceLookup.class;
            } catch (Exception ex) {
                // do nothing
            }
        }    
        
        // check tomcat 7
        // TODO: ??
        
        // check tomcat 6
        // TODO: ??
        
        LOGGER.log(Level.INFO, "Found jBoss version {0}", version);
        return result;
    }
    
    public static TomcatServiceLookup createServiceLookup() {        
        return createServiceLookup(getServiceLookupClass());
    }
    
    private static TomcatServiceLookup createServiceLookup(String className) {
        TomcatServiceLookup result = null;
        try {
            Class<? extends TomcatServiceLookup> clazz = (Class<? extends TomcatServiceLookup>) Class.forName(className);
            result = createServiceLookup(clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static TomcatServiceLookup createServiceLookup(Class<? extends TomcatServiceLookup> clazz) {
        TomcatServiceLookup result = null;
        try {
            result = clazz.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
