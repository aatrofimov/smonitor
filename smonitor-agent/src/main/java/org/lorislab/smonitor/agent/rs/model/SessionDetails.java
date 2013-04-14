/*
 * Copyright 2013 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.smonitor.agent.rs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The session details.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionDetails extends Session implements ServerRequest {

    /**
     * The host.
     */
    private String host;
    /**
     * The application.
     */
    private String application;
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
    private List<Attribute> attributes;
        /**
     * The server context.
     */
    private ServerContext serverContext;
        /**
     * The default constructor.
     */
    public SessionDetails() {
        attributes = new ArrayList<Attribute>();
        roles = new ArrayList<String>();
        serverContext = new ServerContext();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ServerContext getServerContext() {
        return serverContext;
    }
  /**
     * Gets the application.
     *
     * @return the application.
     */
    public String getApplication() {
        return application;
    }

    /**
     * Sets the application.
     *
     * @param application the application.
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * Gets the host.
     *
     * @return the host.
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the host.
     *
     * @param host the host.
     */
    public void setHost(String host) {
        this.host = host;
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
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Sets the list of attributes.
     *
     * @param attributes the list of attributes.
     */
    public void setAttributes(List<Attribute> attributes) {
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
