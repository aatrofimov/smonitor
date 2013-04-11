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
package com.ajkaandrej.gwt.uc.form;

import com.google.gwt.user.client.ui.Widget;

/**
 * The header form.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 * @param <T> the entity.
 * @param <W> the widget.
 */
public abstract class HeaderForm<T, W extends Widget> {

    /**
     * Gets the widget for the object and current widget.
     *
     * @param object the object.
     * @param widget the current widget.
     * @return the new widget.
     */
    public abstract W getWidget(T object, W widget);
}
