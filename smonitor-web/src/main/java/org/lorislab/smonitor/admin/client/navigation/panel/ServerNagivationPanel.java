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
package org.lorislab.smonitor.admin.client.navigation.panel;

import org.lorislab.smonitor.gwt.uc.handler.SelectionHandler;
import org.lorislab.smonitor.admin.client.factory.ObjectFactory;
import org.lorislab.smonitor.admin.client.navigation.model.HostTreeModel;
import org.lorislab.smonitor.admin.client.navigation.model.ServerTreeModel;
import org.lorislab.smonitor.admin.client.navigation.model.ServerTreeViewModel;
import org.lorislab.smonitor.agent.rs.model.Server;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Composite;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ServerNagivationPanel extends Composite {
    
    private CellTree tree;
    
    private ServerTreeViewModel model;
    
    private SelectionHandler<ServerTreeModel> serverHandler;
    private SelectionHandler<HostTreeModel> hostHandler;
    
    public ServerNagivationPanel() {
        
//        final SingleSelectionModel<ServerTreeModel> selectionModel = new SingleSelectionModel<ServerTreeModel>(ServerTreeModel.KEY_PROVIDER);
//        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//            @Override
//            public void onSelectionChange(SelectionChangeEvent event) {
//                if (serverHandler != null) {
//                    serverHandler.selectionChanged(selectionModel.getSelectedObject());
//                }
//            }
//        });
//
//        final SingleSelectionModel<HostTreeModel> selectionHostModel = new SingleSelectionModel<HostTreeModel>(HostTreeModel.KEY_PROVIDER);
//        selectionHostModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//            @Override
//            public void onSelectionChange(SelectionChangeEvent event) {
//                if (hostHandler != null) {
//                    hostHandler.selectionChanged(selectionHostModel.getSelectedObject());
//                }
//            }
//        });
//        
        model = new ServerTreeViewModel(null, null);        
        tree = new CellTree(model, null);        
        initWidget(tree);
    }
    
    public void reset() {
        model.clear();
    }
    
    public void addServer(Server server) {
        ServerTreeModel tmp = ObjectFactory.create(server);
        model.add(tmp);
    }
}
