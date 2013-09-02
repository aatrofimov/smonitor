/*
 * Copyright 2013 lorislab.org.
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
package org.lorislab.smonitor.rs.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras
 */
public final class SessionInfoDetails {
    
/**
     * The session.
     */
    private SessionInfo session;
    /**
     * The info.
     */
    private String info;
    /**
     * The session size.
     */
    private double size;
    /**
     * The serialisable size.
     */
    private double sizeSerializable;
    /**
     * The new session flag.
     */
    private boolean newSession;
    /**
     * The list of roles.
     */
    private List<String> roles;
    /**
     * The list of attributes.
     */
    private List<AttributeInfo> attributes;

    /**
     * The default constructor.
     */
    public SessionInfoDetails() {
        attributes = new ArrayList<AttributeInfo>();
        roles = new ArrayList<String>();
    }

    /**
     * Gets the session.
     *
     * @return the session.
     */
    public SessionInfo getSession() {
        return session;
    }

    /**
     * Sets the session.
     *
     * @param session the session.
     */
    public void setSession(SessionInfo session) {
        this.session = session;
    }

    /**
     * Gets the list of roles.
     *
     * @return the list of roles.
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets the list of roles.
     *
     * @param roles the list of roles.
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * Gets the list of attributes.
     *
     * @return the list of attributes.
     */
    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    /**
     * Sets the list of attributes.
     *
     * @param attributes the list of attributes.
     */
    public void setAttributes(List<AttributeInfo> attributes) {
        this.attributes = attributes;
    }

    /**
     * Gets the info.
     *
     * @return the info.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the info.
     *
     * @param info the info.
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Gets the size.
     *
     * @return the size.
     */
    public double getSize() {
        return size;
    }

    /**
     * Gets the serialisable size.
     *
     * @return the serialisable size.
     */
    public double getSizeSerializable() {
        return sizeSerializable;
    }

    /**
     * Sets the new session flag.
     *
     * @param newSession the new session flag.
     */
    public void setNewSession(boolean newSession) {
        this.newSession = newSession;
    }

    /**
     * Sets the size.
     *
     * @param size the size.
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Sets the serialisable size.
     *
     * @param sizeSerializable the serialisable size.
     */
    public void setSizeSerializable(double sizeSerializable) {
        this.sizeSerializable = sizeSerializable;
    }

    /**
     * Gets the new session flag.
     *
     * @return the new session flag.
     */
    public boolean isNewSession() {
        return newSession;
    }    
}
