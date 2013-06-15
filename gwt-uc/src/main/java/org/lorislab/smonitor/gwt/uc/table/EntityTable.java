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

import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.gwt.uc.table.column.AbstractEntityColumn;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.ListDataProvider;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class EntityTable<T> extends CellTable<T> {

    private ListDataProvider<T> dataProvider;

    public EntityTable() {
        dataProvider = new ListDataProvider<T>();
        dataProvider.addDataDisplay(this);
        setEmptyTableWidget(new Label("Empty"));
        
        setAutoHeaderRefreshDisabled(true);
        setAutoFooterRefreshDisabled(true);
    }

    public void reset() {
        dataProvider.getList().clear();
    }

    public void addAll(List<T> data) {
        dataProvider.getList().addAll(data);
    }

    public Column<T, ?> addColumn(String name, AbstractEntityColumn<T, ?, ?> column) {
        return addColumn(name, false, column);
    }

    public <K extends Comparable<K>> Column<T, ?> addColumn(String name, boolean sorting, final AbstractEntityColumn<T, K, ?> column) {
        addColumn(column, name);
        if (sorting) {
            column.setSortable(true);
            ColumnSortEvent.ListHandler<T> hostColumnSortHandler = new ColumnSortEvent.ListHandler<T>(dataProvider.getList());
            hostColumnSortHandler.setComparator(column, new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    int result = -1;
                    if (o1 == o2) {
                        result = 0;
                    } else {
                        if (o1 != null) {
                            if (o2 != null) {
                                K a = column.getObject(o1);
                                K b = column.getObject(o2);
                                if (a == b) {
                                    result = 0;
                                } else {
                                    if (a != null) {
                                        if (b != null) {
                                            return a.compareTo(b);
                                        } else {
                                            result = 1;
                                        }
                                    }
                                }
                            } else {
                                result = 1;
                            }
                        }
                    }
                    return result;
                }
            });
            addColumnSortHandler(hostColumnSortHandler);
        }
        return column;
    }
}
