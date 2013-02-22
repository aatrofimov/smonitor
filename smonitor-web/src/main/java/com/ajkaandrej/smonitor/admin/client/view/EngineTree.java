/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.admin.client.view;

import com.ajkaandrej.smonitor.admin.client.factory.ListGridRecordFactory;
import com.ajkaandrej.smonitor.admin.client.model.EngineTreeNode;
import com.ajkaandrej.smonitor.admin.shared.model.EngineInfo;
import com.ajkaandrej.smonitor.admin.shared.model.HostInfo;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationInfo;
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
    
    public void loadData(EngineInfo info) {
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
    
    private static EngineTreeNode[] createEngineTreeNode(EngineInfo info) {
        EngineTreeNode[] result = new EngineTreeNode[0];

        if (info != null) {
            List<EngineTreeNode> tmp = new ArrayList<EngineTreeNode>();
            tmp.add(new EngineTreeNode(info));

            HostInfo[] hosts = info.getHosts();
            if (hosts != null) {
                for (HostInfo host : hosts) {
                    tmp.add(new EngineTreeNode(info, host));
                    WebApplicationInfo[] webApps = host.getApplications();
                    if (webApps != null) {
                        for (WebApplicationInfo app : webApps) {
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
