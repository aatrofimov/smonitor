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
package org.lorislab.smonitor.admin.client.navigation.model;

import org.lorislab.smonitor.agent.rs.model.Server;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationTreeViewModel implements TreeViewModel {
            
    private ListDataProvider<ApplicationTreeModel> dataProvider = new ListDataProvider<ApplicationTreeModel>(new ArrayList<ApplicationTreeModel>());

    private SingleSelectionModel<ApplicationTreeModel> selectionModel;
    
    private SingleSelectionModel<AppInstanceTreeModel> appInstanceSelectionModel;
    
    public ApplicationTreeViewModel() {
        
    }
    
    public ApplicationTreeViewModel(SingleSelectionModel<ApplicationTreeModel> selectionModel, SingleSelectionModel<AppInstanceTreeModel> appInstanceSelectionModel) {
        this.selectionModel = selectionModel;
        this.appInstanceSelectionModel = appInstanceSelectionModel;
        selectionModel.getSelectedObject();
    }              
    
    public ApplicationTreeModel getSelectApplication() {
        return selectionModel.getSelectedObject();
    }
    
    public void clear() {
        selectionModel.clear();
        dataProvider.getList().clear();
        dataProvider.flush();
    }
    
    public void add(ApplicationTreeModel model) {
        dataProvider.getList().add(model);
    }
    
    public Map<String, ApplicationTreeModel> getData() {
        Map<String,ApplicationTreeModel> tmp = new HashMap<String,ApplicationTreeModel>();
        for (ApplicationTreeModel model : dataProvider.getList()) {
            tmp.put(model.id, model);
        }
        return tmp;
    }
    
    @Override
    public <T> NodeInfo<?> getNodeInfo(T value) {
        NodeInfo<?> result = null;
        if (value == null) {
            Cell<ApplicationTreeModel> cell = new AbstractCell<ApplicationTreeModel>() {
                @Override
                public void render(Context context, ApplicationTreeModel app, SafeHtmlBuilder sb) {
                    if (app != null) {
                        sb.appendEscaped(app.name);
                    }
                }
            };
            result = new DefaultNodeInfo<ApplicationTreeModel>(dataProvider, cell, selectionModel, null);
        } else if (value instanceof ApplicationTreeModel) {
            ListDataProvider<AppInstanceTreeModel> appDataProvider = new ListDataProvider<AppInstanceTreeModel>(((ApplicationTreeModel) value).instances);
            Cell<AppInstanceTreeModel> cell = new AbstractCell<AppInstanceTreeModel>() {
                @Override
                public void render(Context context, AppInstanceTreeModel server, SafeHtmlBuilder sb) {
                    if (server != null) {
                        sb.appendEscaped(server.hostName + ":" + server.hostPort + " (" + server.host + ")");
                    }
                }
            };
            result = new DefaultNodeInfo<AppInstanceTreeModel>(appDataProvider, cell, appInstanceSelectionModel, null);
        }
        return result;
    }

    @Override
    public boolean isLeaf(Object value) {
        return value instanceof AppInstanceTreeModel;
    }
    
}
