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
package com.ajkaandrej.smonitor.connector.model;

import java.util.Date;

/**
 * The session.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class Session {

    /**
     * The id.
     */
    private String id;
    /**
     * The user.
     */
    private String user;
    /**
     * The creation time.
     */
    private Date creationTime;
    /**
     * The last accessed time.
     */
    private Date lastAccessedTime;
    /**
     * The valid flag.
     */
    private boolean valid;
    /**
     * The last accessed time internal.
     */
    private long lastAccessedTimeInternal;
    /**
     * The maximum inactive interval.
     */
    private int maxInactiveInterval;

    /**
     * Gets last accessed time interval.
     *
     * @return last accessed time interval.
     */
    public long getLastAccessedTimeInternal() {
        return lastAccessedTimeInternal;
    }

    /**
     * Sets last accessed time interval.
     *
     * @param lastAccessedTimeInternal last accessed time interval.
     */
    public void setLastAccessedTimeInternal(long lastAccessedTimeInternal) {
        this.lastAccessedTimeInternal = lastAccessedTimeInternal;
    }

    /**
     * Gets the maximal inactive interval.
     *
     * @return the maximal inactive interval.
     */
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    /**
     * Sets the maximal inactive interval.
     *
     * @param maxInactiveInterval the maximal inactive interval.
     */
    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    /**
     * Gets the valid flag.
     *
     * @return the valid flag.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Sets the valid flag.
     *
     * @param valid the valid flag.
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * Gets the creation time.
     *
     * @return the creation time.
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the creation time.
     *
     * @param creationTime the creation time.
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Gets the last accessed time.
     *
     * @return the last accessed time.
     */
    public Date getLastAccessedTime() {
        return lastAccessedTime;
    }

    /**
     * Sets the last accessed time.
     *
     * @param lastAccessedTime the last accessed time.
     */
    public void setLastAccessedTime(Date lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    /**
     * Gets the id.
     *
     * @return the id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the user.
     *
     * @param user the user.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the user.
     *
     * @return the user.
     */
    public String getUser() {
        return user;
    }
}
