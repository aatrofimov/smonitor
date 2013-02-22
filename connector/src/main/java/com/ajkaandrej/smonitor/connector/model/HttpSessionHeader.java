package com.ajkaandrej.smonitor.connector.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class HttpSessionHeader implements Serializable {
    
    private static final long serialVersionUID = -3820899850920038192L;    
    
    private String id;
    
    private String user;

    private Date creationTime;
    
    private Date lastAccessedTime;
        
    private boolean valid;
    
    private long lastAccessedTimeInternal;
        
    private int maxInactiveInterval;

    public long getLastAccessedTimeInternal() {
        return lastAccessedTimeInternal;
    }

    public void setLastAccessedTimeInternal(long lastAccessedTimeInternal) {
        this.lastAccessedTimeInternal = lastAccessedTimeInternal;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }
        
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(Date lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
        
    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
        
}
