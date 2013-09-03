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
package org.lorislab.smonitor.gwt.uc.table;

import com.google.gwt.safehtml.shared.SafeHtml;
import org.lorislab.smonitor.gwt.uc.table.column.AbstractEntityColumn;
import org.lorislab.smonitor.gwt.uc.table.handler.ColumnSortHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.RowHoverEvent;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.ListDataProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.lorislab.smonitor.admin.client.handler.TableRowHoverHandler;
import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.gwt.uc.model.Wrapper;

/**
 * The entity data grid.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class EntityDataGrid<E, T extends Wrapper<E>> extends DataGrid<T> {

    /**
     * The table row handler table row hover handler.
     */
    private TableRowHoverHandler tableRowHoverHandler;
    /**
     * The table change size handler.
     */
    private ChangeSizeHandler changeSizeHandler;
    /**
     * The list data provider.
     */
    private ListDataProvider<T> dataProvider;

    /**
     * The default constructor.
     */
    public EntityDataGrid() {
        dataProvider = new ListDataProvider<T>();
        dataProvider.addDataDisplay(this);
        setEmptyTableWidget(new Label("Empty"));

        // disable auto refresh
        setAutoHeaderRefreshDisabled(true);
        setAutoFooterRefreshDisabled(true);

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
     * Reset the grid.
     */
    public void reset() {
        dataProvider.getList().clear();
        changeSize();
    }

    /**
     * Adds the item to the grid.
     *
     * @param data the data item.
     * @return the corresponding wrapper with the item.
     */
    public T addItem(E data) {
        if (data != null) {
            T w = createWrapper();
            w.data = data;
            return add(w);
        }
        return null;
    }

    /**
     * Adds the wrapper to the grid.
     *
     * @param data the wrapper.
     * @return the wrapper.
     */
    public T add(T data) {
        if (data != null) {
            dataProvider.getList().add(data);
            dataProvider.flush();
            changeSize();
        }
        return data;
    }

    /**
     * Adds all items to the list.
     *
     * @param data the list of items.
     */
    public void addAllItems(List<E> data) {
        if (data != null) {
            List<T> tmp = new ArrayList<T>();
            for (E item : data) {
                T w = createWrapper();
                w.data = item;
                tmp.add(w);
            }
            addAll(tmp);
        }
    }

    /**
     * Adds the list of wrappers.
     *
     * @param data the list of wrappers.
     */
    public void addAll(List<T> data) {
        if (data != null && !data.isEmpty()) {
            dataProvider.getList().addAll(data);
            dataProvider.flush();
            changeSize();
        }
    }

    /**
     * Refresh the data provider.
     */
    public void refresh() {
        dataProvider.refresh();
    }

    /**
     * Replaces the old data with new data.
     *
     * @param data the old data.
     * @param newData the new data.
     */
    public void replace(T data, T newData) {
        if (data != null && newData != null) {
            int index = dataProvider.getList().indexOf(data);
            if (index != -1) {
                dataProvider.getList().set(index, newData);
                update();
            }
        }
    }

    /**
     * Update data provider.
     *
     * @see ListDataProvider#flush()
     */
    public void update() {
        dataProvider.flush();
    }

    /**
     * Sets the data to the list.
     *
     * @param data the data to list.
     */
    public void set(T data) {
        if (data != null) {
            int index = dataProvider.getList().indexOf(data);
            if (index != -1) {
                dataProvider.getList().set(index, data);
                update();
            }
        }
    }

    /**
     * Removes the data from the list.
     *
     * @param data the data to be removed.
     */
    public void remove(T data) {
        if (data != null) {
            dataProvider.getList().remove(data);
            dataProvider.flush();
            changeSize();
        }
    }

    /**
     * Removes all data from the list by the filter.
     *
     * @param filter the filter.
     */
    public void remove(FilterItem<T> filter) {
        T item = find(filter);
        if (item != null) {
            remove(item);
        }
    }

    /**
     * Gets the item by the index.
     *
     * @param index the index.
     * @return the wrapper.
     */
    public T get(int index) {
        return dataProvider.getList().get(index);
    }

    /**
     * Gets the list of wrappers.
     *
     * @return the list of wrappers.
     */
    public List<T> get() {
        return dataProvider.getList();
    }

    /**
     * Finds the first item by the filter.
     *
     * @param filter the filter.
     * @return the first item corresponding to the filter.
     */
    public T find(FilterItem<T> filter) {
        T result = null;
        if (filter != null) {
            List<T> items = dataProvider.getList();
            if (items != null && !items.isEmpty()) {
                Iterator<T> iter = items.iterator();
                while (result == null && iter.hasNext()) {
                    result = filter.isItem(iter.next());
                }
            }
        }
        return result;
    }

    /**
     * Finds all items by the filter.
     *
     * @param filter the filter.
     * @return all items corresponding to the filter.
     */
    public List<T> findAll(FilterItem<T> filter) {
        List<T> result = new ArrayList<T>();
        if (filter != null) {
            List<T> items = dataProvider.getList();
            if (items != null && !items.isEmpty()) {
                Iterator<T> iter = items.iterator();
                T item;
                while (iter.hasNext()) {
                    item = filter.isItem(iter.next());
                    if (item != null) {
                        result.add(item);
                    }
                }
            }
        }
        return result;
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
     * Removes the model by id from the table.
     *
     * @param data the model to remove.
     */
    public T removeById(final Object id) {
        T result = null;
        if (id != null) {
            result = findById(id);
            remove(result);
        }
        return result;
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
     * Returns the size of the grid.
     *
     * @return the size of the grid.
     */
    public int size() {
        return dataProvider.getList().size();
    }

    /**
     * Adds the column to the grid.
     *
     * @param name the name.
     * @param column the column.
     * @return the corresponding column.
     */
    public Column<T, ?> addColumn(String name, AbstractEntityColumn<T, ?, ?> column) {
        return addColumn(name, false, column);
    }

    /**
     * Adds the column to the grid.
     *
     * @param <K> the entity.
     * @param name the HTML column header.
     * @param sorting the sorting flag.
     * @param column the column.
     * @return the corresponding column.
     */    
    public <K extends Comparable<K>> Column<T, ?> addColumn(SafeHtml html, boolean sorting, final AbstractEntityColumn<T, K, ?> column) {
        addColumn(column, html);
        if (sorting) {
            addColumnSortHandler(new ColumnSortHandler<T>(dataProvider.getList(), column));
        }
        return column;
    }
    
    /**
     * Adds the column to the grid.
     *
     * @param <K> the entity.
     * @param name the column name.
     * @param sorting the sorting flag.
     * @param column the column.
     * @return the corresponding column.
     */
    public <K extends Comparable<K>> Column<T, ?> addColumn(String name, boolean sorting, final AbstractEntityColumn<T, K, ?> column) {
        addColumn(column, name);
        if (sorting) {
            addColumnSortHandler(new ColumnSortHandler<T>(dataProvider.getList(), column));
        }
        return column;
    }

    /**
     * Adds the column to the grid.
     *
     * @param <K> the entity.
     * @param name the column name.
     * @param sorting the sorting flag.
     * @param column the column.
     * @param footer the footer.
     * @return the corresponding column.
     */
    public <K extends Comparable<K>> Column<T, ?> addColumn(String name, boolean sorting, final AbstractEntityColumn<T, K, ?> column, Header footer) {
        addColumn(column, new TextHeader(name), footer);
        if (sorting) {
            addColumnSortHandler(new ColumnSortHandler<T>(dataProvider.getList(), column));
        }
        return column;
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
     * Creates the wrapper.
     *
     * @return the wrapper.
     */
    protected abstract T createWrapper();

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
    public T findById(final Object id) {
        T item = find(new FilterItem<T>() {
            @Override
            public T isItem(T item) {
                if (item.getId().equals(id)) {
                    return item;
                }
                return null;
            }
        });
        return item;
    }

    /**
     * The data filter.
     *
     * @param <T> the wrapper.
     */
    public interface FilterItem<T> {

        /**
         * Returns the item if the item is accepted by the filter.
         *
         * @param item the item.
         * @return the item or null.
         */
        public T isItem(T item);
    }

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
