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
package org.lorislab.smonitor.gwt.uc.panel;

import org.lorislab.smonitor.gwt.uc.common.EntityComposite;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.handler.EntityTablePanelSelectionHandler;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.List;

/**
 * The entity data grid panel.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 * @param <E> the entity.
 * @param <T> the list item.
 */
public class EntityDataGridPanel<E, T> extends EntityComposite<E> {

    /**
     * The data grid.
     */
    private EntityDataGrid<T> dataGrid;
    /**
     * The entity table panel selection handler.
     */
    private EntityTablePanelSelectionHandler<E, T> selectionHandler;

    private SingleSelectionModel<T> selectionModel;
    
    /**
     * The default constructor.
     */
    public EntityDataGridPanel() {
        dataGrid = new EntityDataGrid<T>();
        selectionModel = new SingleSelectionModel<T>();
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                if (selectionHandler != null && selectionModel.getSelectedObject() != null) {
                    selectionHandler.selectionChanged(data, selectionModel.getSelectedObject());
                }
            }
        });
        dataGrid.setSelectionModel(selectionModel);
        initWidget(dataGrid);
    }

    /**
     * Sets the selection handler.
     *
     * @param selectionHandler the selection handler.
     */
    public void setSelectionHandler(EntityTablePanelSelectionHandler<E, T> selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    /**
     * Gets the data grid.
     *
     * @return the data grid.
     */
    public EntityDataGrid<T> getDataGrid() {
        return dataGrid;
    }

    /**
     * Sets the data.
     * @param model the model.
     * @param list the list of items.
     */
    public void setData(E model, List<T> list) {
        this.data = model;
        setData(list);
    }

    public void setData(List<T> list) {
        reset();
        dataGrid.addAll(list);
    }
       
    public SingleSelectionModel<T> getSelectionModel() {
        return selectionModel;
    }
    
    /**
     * Resets the panel.
     */
    public void reset() {
        dataGrid.reset();
    }
}
