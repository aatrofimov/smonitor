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
package com.ajkaandrej.gwt.uc.panel;

import com.ajkaandrej.gwt.uc.common.EntityComposite;
import com.ajkaandrej.gwt.uc.table.EntityDataGrid;
import com.ajkaandrej.gwt.uc.table.handler.EntityTablePanelSelectionHandler;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class EntityDataGridPanel<E, T> extends EntityComposite<E> {

    private EntityDataGrid<T> dataGrid;
    private EntityTablePanelSelectionHandler<E, T> selectionHandler;
    
    public EntityDataGridPanel() {
        dataGrid = new EntityDataGrid<T>();
        dataGrid.setWidth100();
        final SingleSelectionModel<T> ssm = new SingleSelectionModel<T>();
        ssm.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                if (selectionHandler != null && ssm.getSelectedObject() != null) {
                    selectionHandler.selectionChanged(data, ssm.getSelectedObject());
                }
            }
        });
        dataGrid.setSelectionModel(ssm);
        initWidget(dataGrid);
    }

    public void setSelectionHandler(EntityTablePanelSelectionHandler<E, T> selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    public EntityDataGrid<T> getDataGrid() {
        return dataGrid;
    }

    public void onResize() {
        dataGrid.onResize();
    }
    
    public void setData(E model, List<T> list) {
        reset();
        this.data = model;
        dataGrid.addAll(list);        
    }

    public void reset() {
        dataGrid.reset();
    }
}
