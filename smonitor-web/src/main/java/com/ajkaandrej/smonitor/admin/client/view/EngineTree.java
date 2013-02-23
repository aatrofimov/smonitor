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
package com.ajkaandrej.smonitor.admin.client.view;

import com.ajkaandrej.smonitor.admin.client.model.EngineTreeNode;
import com.ajkaandrej.smonitor.admin.shared.model.ClientServerEngine;
import com.ajkaandrej.smonitor.admin.shared.model.ClientServerHost;
import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplication;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class EngineTree extends TreeGrid {
    

    public EngineTree() {
        setWidth100();
        setHeight100();
        setShowConnectors(true);
        setShowResizeBar(false);
        setShowConnectors(true);    
    }
    
    public void loadData(ClientServerEngine info) {
        Tree tree = createTree();
        tree.setData(createEngineTreeNode(info));
        this.setData(tree);
    }

    private static Tree createTree() {
        Tree tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setIdField(EngineTreeNode.ATTR_NAME);
        tree.setParentIdField(EngineTreeNode.ATTR_PARENT);
        tree.setNameProperty(EngineTreeNode.ATTR_NAME);
        tree.setRootValue(EngineTreeNode.ATTR_ROOT);        
        return tree;
    }
    
    private static EngineTreeNode[] createEngineTreeNode(ClientServerEngine info) {
        EngineTreeNode[] result = new EngineTreeNode[0];

        if (info != null) {
            List<EngineTreeNode> tmp = new ArrayList<EngineTreeNode>();
            tmp.add(new EngineTreeNode(info));

            List<ClientServerHost> hosts = info.getHosts();
            if (hosts != null) {
                for (ClientServerHost host : hosts) {
                    tmp.add(new EngineTreeNode(info, host));
                    List<ClientWebApplication> webApps = host.getApplications();
                    if (webApps != null) {
                        for (ClientWebApplication app : webApps) {
                            tmp.add(new EngineTreeNode(host, app));
                        }
                    }
                }
            }
            result = tmp.toArray(new EngineTreeNode[tmp.size()]);
        }
        return result;
    }        
}
