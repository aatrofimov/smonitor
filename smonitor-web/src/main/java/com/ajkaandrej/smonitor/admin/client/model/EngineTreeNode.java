/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.admin.client.model;

import com.ajkaandrej.smonitor.admin.shared.model.EngineInfo;
import com.ajkaandrej.smonitor.admin.shared.model.HostInfo;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationInfo;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class EngineTreeNode extends TreeNode {
    
    public static final String ATTR_ROOT = "root";
      
    public static final String ATTR_NAME = "name";
    
    public static final String ATTR_ID = ATTR_NAME;
    
    public static final String ATTR_PARENT = "parent";
    
    public static final String ATTR_OBJECT = "object";
    
    public EngineTreeNode(EngineInfo engineInfo) {
            setAttribute(ATTR_NAME, engineInfo.getName());
            setAttribute(ATTR_PARENT, ATTR_ROOT);
            setAttribute(ATTR_OBJECT, engineInfo);        
    }
    
    public EngineTreeNode(EngineInfo engineInfo, HostInfo hostInfo) {
            setAttribute(ATTR_NAME, hostInfo.getName());
            setAttribute(ATTR_PARENT, engineInfo.getName());
            setAttribute(ATTR_OBJECT, hostInfo);           
    }
    
    public EngineTreeNode(HostInfo hostInfo, WebApplicationInfo wepAppInfo) {
            setAttribute(ATTR_NAME, wepAppInfo.getName());
            setAttribute(ATTR_PARENT, hostInfo.getName());
            setAttribute(ATTR_OBJECT, wepAppInfo);           
    }
    
    public Object getUserObject() {
        return getAttributeAsObject(ATTR_OBJECT);
    }
}
