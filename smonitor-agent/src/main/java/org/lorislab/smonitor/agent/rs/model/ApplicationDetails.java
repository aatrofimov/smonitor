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
import java.util.Date;
import java.util.List;

/**
 * The application details.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationDetails extends Application implements ServerRequest {

    /**
     * The server context.
     */
    private ServerContext serverContext;
    /**
     * The count of active sessions.
     */
    private int activeSessions;
    /**
     * The cluster flag.
     */
    private boolean distributable;
    /**
     * The count of expired sessions.
     */
    private int expiredSessions;
    /**
     * The maximal active sessions.
     */
    private int maxActive;
    /**
     * The maximal inactive interval.
     */
    private int maxInactiveInterval;
    /**
     * The count of reject sessions.
     */
    private int rejectedSessions;
    /**
     * The session average alive time.
     */
    private int sessionAverageAliveTime;
    /**
     * The session counter.
     */
    private int sessionCounter;
    /**
     * The session ID length.
     */
    private int sessionIdLength;
    /**
     * The session maximal alive time.
     */
    private int SessionMaxAliveTime;
    /**
     * The application context.
     */
    private String context;
    /**
     * The start time.
     */
    private Date startTime;
    /**
     * The list of sessions.
     */
    private List<Session> sessions;

    /**
     * The default constructor.
     */
    public ApplicationDetails() {
        sessions = new ArrayList<Session>();
        serverContext = new ServerContext();
    }

    /**
     * Gets the start time.
     *
     * @return the start time.
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time.
     *
     * @param startTime the start time.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the application context.
     *
     * @return the application context.
     */
    public String getContext() {
        return context;
    }

    /**
     * Sets the application context.
     *
     * @param context the application context.
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Gets the list of sessions.
     *
     * @return the list of sessions.
     */
    public List<Session> getSessions() {
        return sessions;
    }

    /**
     * Sets the list of sessions.
     *
     * @param sessions the list of sessions.
     */
    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    /**
     * Gets the count of active sessions.
     *
     * @return the count of active sessions.
     */
    public int getActiveSessions() {
        return activeSessions;
    }

    /**
     * Sets the number of active sessions.
     *
     * @param activeSessions the number of active sessions.
     */
    public void setActiveSessions(int activeSessions) {
        this.activeSessions = activeSessions;
    }

    /**
     * Returns
     * <code>true</code> if the application is clustered.
     *
     * @return the cluster flag.
     */
    public boolean isDistributable() {
        return distributable;
    }

    /**
     * Sets the application clustered flag.
     *
     * @param distributable the cluster flag.
     */
    public void setDistributable(boolean distributable) {
        this.distributable = distributable;
    }

    /**
     * Gets the number of expired sessions.
     *
     * @return the expiredSessions the number of expired sessions.
     */
    public int getExpiredSessions() {
        return expiredSessions;
    }

    /**
     * Sets the number of expired sessions.
     *
     * @param expiredSessions the number of expired sessions.
     */
    public void setExpiredSessions(int expiredSessions) {
        this.expiredSessions = expiredSessions;
    }

    /**
     * Gets the number of maximal active sessions.
     *
     * @return the maxActive the number of maximal active sessions.
     */
    public int getMaxActive() {
        return maxActive;
    }

    /**
     * Sets the maximal number of active sessions.
     *
     * @param maxActive the maximal number of active sessions.
     */
    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    /**
     * Gets the maximal interval of inactive session.
     *
     * @return the maxInactiveIntervalt the maximal interval of inactive
     * session.
     */
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    /**
     * Sets the maximal interval of inactive session.
     *
     * @param maxInactiveInterval the maximal interval of inactive session.
     */
    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    /**
     * Gets the number of rejected sessions.
     *
     * @return the rejectedSessions the number of rejected sessions.
     */
    public int getRejectedSessions() {
        return rejectedSessions;
    }

    /**
     * Sets the number of rejected sessions.
     *
     * @param rejectedSessions the number of rejected sessions.
     */
    public void setRejectedSessions(int rejectedSessions) {
        this.rejectedSessions = rejectedSessions;
    }

    /**
     * Gets the session average alive time.
     *
     * @return the sessionAverageAliveTime the session average alive time.
     */
    public int getSessionAverageAliveTime() {
        return sessionAverageAliveTime;
    }

    /**
     * Sets the session average alive time.
     *
     * @param sessionAverageAliveTime the session average alive time.
     */
    public void setSessionAverageAliveTime(int sessionAverageAliveTime) {
        this.sessionAverageAliveTime = sessionAverageAliveTime;
    }

    /**
     * Gets the session counter.
     *
     * @return the session counter.
     */
    public int getSessionCounter() {
        return sessionCounter;
    }

    /**
     * Sets the session counter.
     *
     * @param sessionCounter the session counter.
     */
    public void setSessionCounter(int sessionCounter) {
        this.sessionCounter = sessionCounter;
    }

    /**
     * Gets the session ID length.
     *
     * @return the session ID length.
     */
    public int getSessionIdLength() {
        return sessionIdLength;
    }

    /**
     * Sets the session ID length.
     *
     * @param sessionIdLength the session ID length.
     */
    public void setSessionIdLength(int sessionIdLength) {
        this.sessionIdLength = sessionIdLength;
    }

    /**
     * Gets the session maximal alive time.
     *
     * @return the session maximal alive time.
     */
    public int getSessionMaxAliveTime() {
        return SessionMaxAliveTime;
    }

    /**
     * Sets the session maximal alive time.
     *
     * @param SessionMaxAliveTime the session maximal alive time.
     */
    public void setSessionMaxAliveTime(int SessionMaxAliveTime) {
        this.SessionMaxAliveTime = SessionMaxAliveTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServerContext getServerContext() {
        return serverContext;
    }
}
