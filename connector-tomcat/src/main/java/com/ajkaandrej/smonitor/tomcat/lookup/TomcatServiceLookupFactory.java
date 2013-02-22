package com.ajkaandrej.smonitor.tomcat.lookup;

import com.ajkaandrej.smonitor.jboss.tomcat.Tomcat6ServiceLookup;
import com.ajkaandrej.smonitor.jboss.tomcat.Tomcat7ServiceLookup;
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

    public static Class<? extends TomcatServiceLookup> getServiceLookupClass() {
        Class<? extends TomcatServiceLookup> result = null;
        String version = null;
        
        MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
        try {
            version = (String) mBeanServer.getAttribute(new ObjectName("jboss.as:management-root=server"), "releaseVersion");
            result = Tomcat7ServiceLookup.class;
        } catch (Exception ex) {
            // do nothing
        }
        if (result == null) {
            try {
                version = (String) mBeanServer.getAttribute(new ObjectName("jboss.system:type=Server"), "Version");
                result = Tomcat6ServiceLookup.class;
            } catch (Exception ex) {
                // do nothing
            }
        }    
        LOGGER.log(Level.INFO, "Found jBoss version {0}", version);
        return result;
    }
    
    public static TomcatServiceLookup createServiceLookup() {        
        return createServiceLookup(getServiceLookupClass());
    }
    
    public static TomcatServiceLookup createServiceLookup(String className) {
        TomcatServiceLookup result = null;
        try {
            Class<? extends TomcatServiceLookup> clazz = (Class<? extends TomcatServiceLookup>) Class.forName(className);
            result = createServiceLookup(clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static TomcatServiceLookup createServiceLookup(Class<? extends TomcatServiceLookup> clazz) {
        TomcatServiceLookup result = null;
        try {
            result = clazz.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
