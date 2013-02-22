/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.admin.client.model;

import com.ajkaandrej.smonitor.admin.shared.model.SessionDetails;
import com.ajkaandrej.smonitor.admin.shared.model.SessionInfo;
import com.ajkaandrej.smonitor.admin.shared.model.UserInfo;
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.viewer.DetailViewerRecord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionDetailsViewerRecord extends DetailViewerRecord {

    public static final String ATTR_ID = "id";
    public static final String ATTR_CREATION = "creation";
    public static final String ATTR_LAST_ACCESS = "lastAccess";
    public static final String ATTR_LAST_ACCESS_INTERVAL = "lastAccessInterval";
    public static final String ATTR_MAX_INC_INTERVAL = "maxIncInterval";
    public static final String ATTR_VALID = "valid";
    public static final String ATTR_INFO = "info";
    public static final String ATTR_SIZE = "size";
    public static final String ATTR_SER_SIZE = "serSize";
    public static final String ATTR_USER_NAME = "userName";
    public static final String ATTR_USER_ROLES = "userRoles";
    public static final String ATTR_OBJECT = "object";

    public SessionDetailsViewerRecord(SessionDetails session) {

        SessionInfo info = session.getSessionInfo();
        setAttribute(ATTR_ID, info.getId());
        setAttribute(ATTR_CREATION, info.getCreationTime());
        setAttribute(ATTR_LAST_ACCESS, info.getLastAccessedTime());
        setAttribute(ATTR_LAST_ACCESS_INTERVAL, info.getLastAccessedTimeInternal());
        setAttribute(ATTR_MAX_INC_INTERVAL, info.getMaxInactiveInterval());
        setAttribute(ATTR_VALID, info.isValid());

        setAttribute(ATTR_INFO, session.getInfo());
        setAttribute(ATTR_SIZE, session.getSize());
        setAttribute(ATTR_SER_SIZE, session.getSizeSerializable());

        UserInfo user = session.getUserInfo();
        setAttribute(ATTR_USER_NAME, user.getName());       
        setAttribute(ATTR_USER_ROLES, user.getRoles());
        setAttribute(ATTR_OBJECT, session);
    }

    public SessionDetails getObject() {
        return (SessionDetails) getAttributeAsObject(ATTR_OBJECT);
    }
}
