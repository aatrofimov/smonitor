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
package com.ajkaandrej.smonitor.admin.client.navigation.model;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import java.util.ArrayList;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ServerTreeViewModel implements TreeViewModel {

    private ListDataProvider<ServerTreeModel> dataProvider = new ListDataProvider<ServerTreeModel>(new ArrayList<ServerTreeModel>());

    private SingleSelectionModel<ServerTreeModel> selectionModel;
    
    private SingleSelectionModel<HostTreeModel> selectionHostModel;
    
    public ServerTreeViewModel() {
        
    }
    
    public ServerTreeViewModel(SingleSelectionModel<ServerTreeModel> selectionModel, SingleSelectionModel<HostTreeModel> selectionHostModel) {
        this.selectionHostModel = selectionHostModel;
        this.selectionModel = selectionModel;
    }
    
    public void add(ServerTreeModel model) {
        dataProvider.getList().add(model);
    }
    
    public void clear() {
        dataProvider.getList().clear();
        dataProvider.flush();
    }
    
    @Override
    public <T> NodeInfo<?> getNodeInfo(T value) {        
        NodeInfo<?> result = null;
        if (value == null) {
            Cell<ServerTreeModel> cell = new AbstractCell<ServerTreeModel>() {
                
                @Override
                public void render(Context context, ServerTreeModel server, SafeHtmlBuilder sb) {
                    if (server != null) {
                        sb.appendEscaped(server.hostName + ":" + server.hostPort);
                    }
                }
            };

            result = new DefaultNodeInfo<ServerTreeModel>(dataProvider, cell, selectionModel, null);
        } else if (value instanceof ServerTreeModel) {
            ListDataProvider<HostTreeModel> appDataProvider = new ListDataProvider<HostTreeModel>(((ServerTreeModel) value).hosts);
            Cell<HostTreeModel> cell = new AbstractCell<HostTreeModel>() {
                @Override
                public void render(Context context, HostTreeModel value, SafeHtmlBuilder sb) {
                    if (value != null) {
                        sb.appendEscaped(value.name);
                    }
                }
            };
            result = new DefaultNodeInfo<HostTreeModel>(appDataProvider, cell, selectionHostModel, null);
        }
        return result;
    }

    @Override
    public boolean isLeaf(Object value) {
        return value instanceof HostTreeModel;
    }
}
