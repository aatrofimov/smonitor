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
package com.ajkaandrej.gwt.uc.table;

import com.ajkaandrej.gwt.uc.ConstantValues;
import com.ajkaandrej.gwt.uc.table.column.AbstractEntityColumn;
import com.ajkaandrej.gwt.uc.table.handler.ColumnSortHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.ListDataProvider;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class EntityDataGrid<T> extends DataGrid<T> {
  private ListDataProvider<T> dataProvider;

    public EntityDataGrid() {
        dataProvider = new ListDataProvider<T>();
        dataProvider.addDataDisplay(this);
        setEmptyTableWidget(new Label("Empty"));
        
        setAutoHeaderRefreshDisabled(true);
        setAutoFooterRefreshDisabled(true);
    }

    public void set100() {
        setWidth100();
        setHeight100();
    }

    public void setWidth100() {
        setWidth(ConstantValues.PCT_100);
    }

    public void setHeight100() {
        setHeight(ConstantValues.PCT_100);
    }

    public void reset() {
        dataProvider.getList().clear();
    }

    public void addAll(List<T> data) {
        dataProvider.getList().addAll(data);
        dataProvider.flush();
    }

    public Column<T, ?> addColumn(String name, AbstractEntityColumn<T, ?, ?> column) {
        return addColumn(name, false, column);
    }

    public <K extends Comparable<K>> Column<T, ?> addColumn(String name, boolean sorting, final AbstractEntityColumn<T, K, ?> column) {
        addColumn(column, name);
        if (sorting) {
            addColumnSortHandler(new ColumnSortHandler<T>(dataProvider.getList(), column));
        }
        return column;
    }        
}
