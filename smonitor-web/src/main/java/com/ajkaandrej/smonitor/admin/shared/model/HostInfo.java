/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.admin.shared.model;

import java.io.Serializable;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class HostInfo implements Serializable {
    
    private static final long serialVersionUID = -3600295211742829057L;
    
    private String name;
        
    private String engine;
      
    private String info;
    
    private WebApplicationInfo[] applications;

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
    
    public WebApplicationInfo[] getApplications() {
        return applications;
    }

    public void setApplications(WebApplicationInfo[] applications) {
        this.applications = applications;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
