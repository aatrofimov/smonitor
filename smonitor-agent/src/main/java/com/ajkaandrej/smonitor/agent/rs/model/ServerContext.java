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
package com.ajkaandrej.smonitor.agent.rs.model;

/**
 * The server context.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ServerContext {

    /**
     * The host name.
     */
    private String hostName;
    /**
     * The scheme.
     */
    private String scheme;
    /**
     * The remote server.
     */
    private String remote;
    /**
     * The port.
     */
    private int port;

    /**
     * Gets the remote server.
     *
     * @return the remote server.
     */
    public String getRemote() {
        return remote;
    }

    /**
     * Sets the remote server.
     *
     * @param remote the remote server.
     */
    public void setRemote(String remote) {
        this.remote = remote;
    }

    /**
     * Gets the scheme.
     *
     * @return the scheme.
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * Sets the scheme.
     *
     * @param scheme the scheme.
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    /**
     * Gets the host name.
     *
     * @return the host name.
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Sets the host name.
     *
     * @param hostName the host name.
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Gets the port.
     *
     * @return the port.
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the port.
     *
     * @param port the port.
     */
    public void setPort(int port) {
        this.port = port;
    }
}
