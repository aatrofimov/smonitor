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

/**
 * The entity tab selection handler.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 * @param <E> the entity.
 * @param <T> the list item.
 */
public interface EntityTablePanelSelectionHandler<E, T> {

    /**
     * The selection change method.
     *
     * @param model the model.
     * @param item the selected item.
     */
    public void selectionChanged(E model, T item);
}
