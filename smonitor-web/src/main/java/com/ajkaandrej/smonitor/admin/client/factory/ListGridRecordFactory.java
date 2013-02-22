/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.admin.client.factory;

import com.ajkaandrej.smonitor.admin.client.model.AttributeInfoListGridRecord;
import com.ajkaandrej.smonitor.admin.client.model.EngineTreeNode;
import com.ajkaandrej.smonitor.admin.client.model.SessionInfoListGridRecord;
import com.ajkaandrej.smonitor.admin.shared.model.AttributeInfo;
import com.ajkaandrej.smonitor.admin.shared.model.EngineInfo;
import com.ajkaandrej.smonitor.admin.shared.model.HostInfo;
import com.ajkaandrej.smonitor.admin.shared.model.SessionDetails;
import com.ajkaandrej.smonitor.admin.shared.model.SessionInfo;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationDetails;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationInfo;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.Tree;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ListGridRecordFactory {
      
    public static SessionInfoListGridRecord[] createSessionInfoListGridRecord(WebApplicationDetails webApp) {
        SessionInfoListGridRecord[] result = new SessionInfoListGridRecord[0];
        if (webApp != null) {
            SessionInfo[] sessions = webApp.getSessions();
            if (sessions != null) {
                result = new SessionInfoListGridRecord[sessions.length];
                for (int i = 0; i < sessions.length; i++) {
                    result[i] = new SessionInfoListGridRecord(sessions[i]);
                }
            }
        }
        return result;
    }    
    
    public static AttributeInfoListGridRecord[] createAttributeInfoListGridRecord(SessionDetails session) {
        AttributeInfoListGridRecord[] result = new AttributeInfoListGridRecord[0];
        if (session != null) {
            AttributeInfo[] attrs = session.getAttributes();
            if (attrs != null) {
                result = new AttributeInfoListGridRecord[attrs.length];
                for (int i = 0; i < attrs.length; i++) {
                    result[i] = new AttributeInfoListGridRecord(attrs[i]);
                }
            }
        }
        return result;
    }
    
    
}
