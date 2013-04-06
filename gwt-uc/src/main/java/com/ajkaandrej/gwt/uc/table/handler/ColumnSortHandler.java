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
package com.ajkaandrej.gwt.uc.table.handler;

import com.ajkaandrej.gwt.uc.table.column.AbstractEntityColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ColumnSortHandler<T> extends ColumnSortEvent.ListHandler<T> {

    public <K extends Comparable<K>> ColumnSortHandler(List<T> list, final AbstractEntityColumn<T, K, ?> column) {
        super(list);
        column.setSortable(true);
        setComparator(column, new Comparator<T>() {
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
    }
}
