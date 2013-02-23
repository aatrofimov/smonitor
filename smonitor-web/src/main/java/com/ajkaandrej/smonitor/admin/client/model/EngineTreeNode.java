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

import com.ajkaandrej.smonitor.admin.shared.model.ClientServerEngine;
import com.ajkaandrej.smonitor.admin.shared.model.ClientServerHost;
import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplication;
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
    
    public EngineTreeNode(ClientServerEngine engineInfo) {
            setAttribute(ATTR_NAME, engineInfo.getName());
            setAttribute(ATTR_PARENT, ATTR_ROOT);
            setAttribute(ATTR_OBJECT, engineInfo);        
    }
    
    public EngineTreeNode(ClientServerEngine engineInfo, ClientServerHost hostInfo) {
            setAttribute(ATTR_NAME, hostInfo.getName());
            setAttribute(ATTR_PARENT, engineInfo.getName());
            setAttribute(ATTR_OBJECT, hostInfo);           
    }
    
    public EngineTreeNode(ClientServerHost hostInfo, ClientWebApplication wepAppInfo) {
            setAttribute(ATTR_NAME, wepAppInfo.getName());
            setAttribute(ATTR_PARENT, hostInfo.getName());
            setAttribute(ATTR_OBJECT, wepAppInfo);           
    }
    
    public Object getUserObject() {
        return getAttributeAsObject(ATTR_OBJECT);
    }
}
