package com.ajkaandrej.smonitor.connector.model;

import java.io.Serializable;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class HttpSessionWrapper implements Serializable {
    
    private static final long serialVersionUID = -2879705004383931921L;
    
    private HttpSessionHeader sessionInfo;

    private HttpSessionUser userInfo;
    
    private HttpSessionAttribute[] attributes;

    private String info;
    
    private String authType;
    
    private double size;

    private double sizeSerializable;

    private boolean newSession;

    public HttpSessionUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(HttpSessionUser userInfo) {
        this.userInfo = userInfo;
    }
    
    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }
    
    public void setNewSession(boolean newSession) {
        this.newSession = newSession;
    }

    public boolean isNewSession() {
        return newSession;
    }
        
    public double getSizeSerializable() {
        return sizeSerializable;
    }

    public void setSizeSerializable(double sizeSerializable) {
        this.sizeSerializable = sizeSerializable;
    }
        
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
        
    public HttpSessionAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(HttpSessionAttribute[] attributes) {
        this.attributes = attributes;
    }
        
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public HttpSessionHeader getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(HttpSessionHeader sessionInfo) {
        this.sessionInfo = sessionInfo;
    }
    
    
        
}
