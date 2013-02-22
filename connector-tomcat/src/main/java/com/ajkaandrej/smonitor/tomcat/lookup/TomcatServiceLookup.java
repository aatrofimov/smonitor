/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.tomcat.lookup;

import org.apache.catalina.Server;
import org.apache.catalina.Service;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface TomcatServiceLookup {
    
    Server getServer();
    
    Service getService();
}
