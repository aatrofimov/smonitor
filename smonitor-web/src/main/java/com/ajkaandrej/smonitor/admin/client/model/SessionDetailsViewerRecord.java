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
package com.ajkaandrej.smonitor.admin.client.model;

import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionHeader;
import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionUser;
import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionWrapper;
import com.smartgwt.client.widgets.viewer.DetailViewerRecord;

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

    public SessionDetailsViewerRecord(ClientHttpSessionWrapper session) {

        ClientHttpSessionHeader info = session.getSessionInfo();
        setAttribute(ATTR_ID, info.getId());
        setAttribute(ATTR_CREATION, info.getCreationTime());
        setAttribute(ATTR_LAST_ACCESS, info.getLastAccessedTime());
        setAttribute(ATTR_LAST_ACCESS_INTERVAL, info.getLastAccessedTimeInternal());
        setAttribute(ATTR_MAX_INC_INTERVAL, info.getMaxInactiveInterval());
        setAttribute(ATTR_VALID, info.isValid());

        setAttribute(ATTR_INFO, session.getInfo());
        setAttribute(ATTR_SIZE, session.getSize());
        setAttribute(ATTR_SER_SIZE, session.getSizeSerializable());

        ClientHttpSessionUser user = session.getUserInfo();
        setAttribute(ATTR_USER_NAME, user.getName());       
        setAttribute(ATTR_USER_ROLES, user.getRoles());
        setAttribute(ATTR_OBJECT, session);
    }

    public ClientHttpSessionWrapper getObject() {
        return (ClientHttpSessionWrapper) getAttributeAsObject(ATTR_OBJECT);
    }
}
