/*
 * Copyright 2013 lorislab.org.
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
package org.lorislab.smonitor.admin.client.panel;

import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.RowHoverEvent;
import java.util.List;
import org.lorislab.smonitor.admin.client.handler.TableRowHoverHandler;
import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;

/**
 * The abstract grid panel.
 *
 * @author Andrej Petras
 */
public abstract class AbstractGridPanel2<T> extends EntityDataGrid<T> {

    /**
     * The table row handler table row hover handler.
     */
    private TableRowHoverHandler tableRowHoverHandler;
    /**
     * The table change size handler.
     */
    private ChangeSizeHandler changeSizeHandler;

    /**
     * The default constructor.
     */
    public AbstractGridPanel2() {

        // add row hover handler
        this.addRowHoverHandler(new RowHoverEvent.Handler() {
            @Override
            public void onRowHover(RowHoverEvent event) {
                if (tableRowHoverHandler != null) {
                    if (ConstantValues.EVENT_MOUSEOUT.equals((event.getBrowserEvent().getType()))) {
                        if (RowHoverEvent.HoveringScope.CELL_HOVER.equals(event.getHoveringScope())) {
                            tableRowHoverHandler.onRowOut();
                        }
                    } else {
                        if (RowHoverEvent.HoveringScope.ROW_HOVER.equals(event.getHoveringScope())) {
                            tableRowHoverHandler.onRowOver(event.getHoveringRow());
                        }
                    }
                }
            }
        });
        // disable selection mode
        this.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED);        
        // create columns
        createColumns();
    }

    /**
     * Sets the change size handler.
     *
     * @param changeSizeHandler the change size handler.
     */
    public void setChangeSizeHandler(ChangeSizeHandler changeSizeHandler) {
        this.changeSizeHandler = changeSizeHandler;
    }

    /**
     * Sets the table row hover handler.
     *
     * @param tableRowHoverHandler the table row hover handler.
     */
    public void setTableRowHoverHandler(TableRowHoverHandler tableRowHoverHandler) {
        this.tableRowHoverHandler = tableRowHoverHandler;
    }

 

    /**
     * Resets the panel.
     */
    @Override
    public void reset() {
        super.reset();
        changeSize();
    }

    /**
     * Adds the model to the table.
     *
     * @param data the model.
     * @return the model.
     */
    @Override
    public T add(T data) {
        if (data != null) {
            super.add(data);
            changeSize();
        }
        return data;
    }

    /**
     * Adds the list to the table.
     *
     * @param data the list of data.
     */
    @Override
    public void addAll(List<T> data) {
        if (data != null) {
            super.addAll(data);
            changeSize();                                  
        }
    }

    /**
     * Removes the model from the table.
     *
     * @param data the model to remove.
     */
    @Override
    public void remove(T data) {
        if (data != null) {
            super.remove(data);
            changeSize();
        }
    }

    /**
     * Removes the model by id from the table.
     *
     * @param data the model to remove.
     */
    public void removeById(final Object id) {
        if (id != null) {
            T item = findById(id);
            remove(item);
        }
    }
   
    /**
     * Replace the model by id in the table.
     *
     * @param id the model id.
     * @param newData the new model.
     */
    public void replaceById(Object id, T data) {
        if (data != null) {
            T item = findById(id);
            replace(item, data);
        }
    }

    /**
     * This method is call after the methods add, remove or deleted are call.
     */
    private void changeSize() {
        if (changeSizeHandler != null) {
            changeSizeHandler.changeSize(size());
        }
    }

    /**
     * Create columns.
     */
    protected abstract void createColumns();

    /**
     * Find item by id.
     *
     * @param id the id.
     * @return the item.
     */
    protected abstract T findById(Object id);

    /**
     * The change size handler.
     */
    public interface ChangeSizeHandler {

        /**
         * The table changed the size.
         *
         * @param size the new size.
         */
        public void changeSize(int size);
    }
}
