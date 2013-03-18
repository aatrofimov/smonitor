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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationDetails extends Application {
    
    private int activeSessions;
    private boolean distributable;
    private int expiredSessions;
    private int maxActive;
    private int maxInactiveInterval;
    private int rejectedSessions;
    private int sessionAverageAliveTime;
    private int sessionCounter;
    private int sessionIdLength;
    private int SessionMaxAliveTime;
    
    private List<Session> sessions;

    public ApplicationDetails() {
        sessions = new ArrayList<Session>();
    }
    
    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
     
   public int getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(int activeSessions) {
        this.activeSessions = activeSessions;
    }

    /**
     * @return the distributable
     */
    public boolean isDistributable() {
        return distributable;
    }

    /**
     * @param distributable the distributable to set
     */
    public void setDistributable(boolean distributable) {
        this.distributable = distributable;
    }

    /**
     * @return the expiredSessions
     */
    public int getExpiredSessions() {
        return expiredSessions;
    }

    /**
     * @param expiredSessions the expiredSessions to set
     */
    public void setExpiredSessions(int expiredSessions) {
        this.expiredSessions = expiredSessions;
    }

    /**
     * @return the maxActive
     */
    public int getMaxActive() {
        return maxActive;
    }

    /**
     * @param maxActive the maxActive to set
     */
    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    /**
     * @return the maxInactiveInterval
     */
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    /**
     * @param maxInactiveInterval the maxInactiveInterval to set
     */
    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    /**
     * @return the rejectedSessions
     */
    public int getRejectedSessions() {
        return rejectedSessions;
    }

    /**
     * @param rejectedSessions the rejectedSessions to set
     */
    public void setRejectedSessions(int rejectedSessions) {
        this.rejectedSessions = rejectedSessions;
    }

    /**
     * @return the sessionAverageAliveTime
     */
    public int getSessionAverageAliveTime() {
        return sessionAverageAliveTime;
    }

    /**
     * @param sessionAverageAliveTime the sessionAverageAliveTime to set
     */
    public void setSessionAverageAliveTime(int sessionAverageAliveTime) {
        this.sessionAverageAliveTime = sessionAverageAliveTime;
    }

    /**
     * @return the sessionCounter
     */
    public int getSessionCounter() {
        return sessionCounter;
    }

    /**
     * @param sessionCounter the sessionCounter to set
     */
    public void setSessionCounter(int sessionCounter) {
        this.sessionCounter = sessionCounter;
    }

    /**
     * @return the sessionIdLength
     */
    public int getSessionIdLength() {
        return sessionIdLength;
    }

    /**
     * @param sessionIdLength the sessionIdLength to set
     */
    public void setSessionIdLength(int sessionIdLength) {
        this.sessionIdLength = sessionIdLength;
    }

    /**
     * @return the SessionMaxAliveTime
     */
    public int getSessionMaxAliveTime() {
        return SessionMaxAliveTime;
    }

    /**
     * @param SessionMaxAliveTime the SessionMaxAliveTime to set
     */
    public void setSessionMaxAliveTime(int SessionMaxAliveTime) {
        this.SessionMaxAliveTime = SessionMaxAliveTime;
    }    
}