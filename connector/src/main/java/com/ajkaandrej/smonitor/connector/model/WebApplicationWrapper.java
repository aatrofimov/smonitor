package com.ajkaandrej.smonitor.connector.model;

import java.io.Serializable;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class WebApplicationWrapper implements Serializable {

    private static final long serialVersionUID = -6698254608724144536L;
    private WebApplication info;
    private HttpSessionHeader[] sessions;
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

    public int getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(int activeSessions) {
        this.activeSessions = activeSessions;
    }

    public HttpSessionHeader[] getSessions() {
        return sessions;
    }

    public void setSessions(HttpSessionHeader[] sessions) {
        this.sessions = sessions;
    }

    public WebApplication getInfo() {
        return info;
    }

    public void setInfo(WebApplication info) {
        this.info = info;
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
