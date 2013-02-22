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
public class UserInfo implements Serializable {
    
    private static final long serialVersionUID = -6173814656363768685L;
    
    private String name;
    
    private String password;
    
    private String[] roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
        
}
