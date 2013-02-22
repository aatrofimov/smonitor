/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.admin.client.model;

import com.ajkaandrej.smonitor.admin.shared.model.SessionInfo;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionInfoListGridRecord extends ListGridRecord {

    public static final String ATTR_ID = "id";
    
    public static final String ATTR_USER = "user";
    
    public static final String ATTR_CREATIONTIME = "creationTime";
    
    public static final String ATTR_LASTACCESSEDTIME = "lastAccessedTime";
    
    public static final String ATTR_VALID = "valid";
    
    public static final String ATTR_OBJECT = "object";
    
    public SessionInfoListGridRecord(SessionInfo session) {
        setAttribute(ATTR_ID, session.getId());
        setAttribute(ATTR_USER, session.getUser());
        setAttribute(ATTR_CREATIONTIME, session.getCreationTime());
        setAttribute(ATTR_LASTACCESSEDTIME, session.getLastAccessedTime());
        setAttribute(ATTR_VALID, session.isValid());
        setAttribute(ATTR_OBJECT, session);
    }
    
    public SessionInfo getObject() {
        return (SessionInfo) getAttributeAsObject(ATTR_OBJECT);
    }
}
