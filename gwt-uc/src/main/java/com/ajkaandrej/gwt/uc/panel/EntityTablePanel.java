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
import com.ajkaandrej.gwt.uc.table.handler.EntityTablePanelSelectionHandler;
import com.ajkaandrej.gwt.uc.table.EntityTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.List;

/**
 * The entity table panel.
 * @author Andrej Petras <andrej@ajka-andrej.com>
 * @param <E> the entity.
 * @param <T> the table item.
 */
public class EntityTablePanel<E, T> extends EntityComposite<E> {
    /** The entity table. */
    private EntityTable<T> table;
        /**
     * The entity table panel selection handler.
     */
    private EntityTablePanelSelectionHandler<E, T> selectionHandler;
        /**
     * The default constructor.
     */
    public EntityTablePanel() {
        table = new EntityTable<T>();
        table.setWidth100(true);
        table.setPageSize(10);
        
        final SingleSelectionModel<T> ssm = new SingleSelectionModel<T>();
        ssm.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                if (selectionHandler != null && ssm.getSelectedObject() != null) {
                    selectionHandler.selectionChanged(data, ssm.getSelectedObject());
                }
            }
        });        
        table.setSelectionModel(ssm);
        
        
        SimplePager pager = new SimplePager();
        pager.setDisplay(table);
        
        VerticalPanel vp = new VerticalPanel();
        vp.add(table);
        vp.add(pager);
        vp.setCellHorizontalAlignment(pager, HasHorizontalAlignment.ALIGN_CENTER);

        initWidget(vp);        
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
     * Gets the entity table.
     * @return the entity table.
     */
    public EntityTable<T> getTable() {
        return table;
    }

    /**
     * Sets the data.
     * @param model the model.
     * @param list the list of items.
     */
    public void setData(E model, List<T> list) {
        reset();
        this.data = model;
        table.addAll(list);
    }
    
    public void reset() {
        table.reset();
    }
}
