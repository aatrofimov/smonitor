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
package com.ajkaandrej.gwt.uc.form.item;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * The abstract form item.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 * @param <T> the entity.
 * @param <K> the object value.
 * @param <C> the cell value.
 */
public abstract class AbstractFormItem<T, K, C> {

    /**
     * The cell.
     */
    private Cell<C> cell;

    /**
     * The default constructor.
     *
     * @param cell the cell.
     */
    public AbstractFormItem(Cell<C> cell) {
        this.cell = cell;
    }

    /**
     * Gets the cell.
     *
     * @return the cell.
     */
    public Cell<C> getCell() {
        return cell;
    }

    /**
     * Renders the form item.
     *
     * @param context the context.
     * @param object the object.
     * @param sb the safe HTML builder.
     */
    public void render(Cell.Context context, T object, SafeHtmlBuilder sb) {
        cell.render(context, getValue(object), sb);
    }

    /**
     * Gets the cell value.
     *
     * @param object the object.
     * @return the cell value.
     */
    public C getValue(T object) {
        if (object != null) {
            return (C) getObject(object);
        }
        return null;
    }

    /**
     * Gets the object value.
     *
     * @param object the entity.
     * @return the object value.
     */
    public abstract K getObject(T object);
}
