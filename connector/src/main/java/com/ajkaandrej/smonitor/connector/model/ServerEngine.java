package com.ajkaandrej.smonitor.connector.model;

import java.io.Serializable;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ServerEngine implements Serializable {
    
    private static final long serialVersionUID = -1653824216914780157L;
    
    private ServerHost[] hosts;

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
    
    
    public ServerHost[] getHosts() {
        return hosts;
    }

    public void setHosts(ServerHost[] hosts) {
        this.hosts = hosts;
    }
        
}
