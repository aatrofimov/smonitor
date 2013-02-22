package com.ajkaandrej.smonitor.admin.shared.model;

import java.io.Serializable;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class EngineInfo implements Serializable {
    
    private static final long serialVersionUID = -1653824216914780157L;
    
    private HostInfo[] hosts;

    private String name;
        
    private String defaultHost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setDefaultHost(String defaultHost) {
        this.defaultHost = defaultHost;
    }

    public String getDefaultHost() {
        return defaultHost;
    }
    
    
    public HostInfo[] getHosts() {
        return hosts;
    }

    public void setHosts(HostInfo[] hosts) {
        this.hosts = hosts;
    }
        
}
