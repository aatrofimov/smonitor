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
public class SessionDetails implements Serializable {
    
    private static final long serialVersionUID = -2879705004383931921L;

    
    private SessionInfo sessionInfo;

    private UserInfo userInfo;
    
    private AttributeInfo[] attributes;

    private String info;
    
    private String authType;
    
    private double size;

    private double sizeSerializable;

    private boolean newSession;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
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
        
    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }
        
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }
    
    
        
}
