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
package org.lorislab.smonitor.gwt.uc.form.property;

import com.google.gwt.user.client.ui.Widget;

/**
 * The model form property.
 *
 * @author Andrej Petras
 * @param <T> the object.
 * @param <W> the widget.
 * @param <E> the value.
 */
public abstract class ModelFormProperty<T, W extends Widget, E> {

    /**
     * The widget.
     */
    protected W widget;

    /**
     * The default constructor.
     *
     * @param widget the widget.
     */
    public ModelFormProperty(W widget) {
        this.widget = widget;
    }

    /**
     * Gets the value.
     *
     * @param object the object.
     * @return the value for the property.
     */
    public abstract E getValue(T object);

    /**
     * Saves the value for the property.
     *
     * @param object the object.
     * @param value the value.
     */
    public void setValue(T object, E value) {
    }

    /**
     * Loads the object.
     *
     * @param object the object.
     */
    public void load(T object) {
        setWidgetValue(getValue(object));
    }

    /**
     * Saves the object.
     */
    public void save(T object) {
        setValue(object, getWidgetValue());
    }

    /**
     * Sets the widget value.
     *
     * @param value the value.
     */
    public abstract void setWidgetValue(E value);

    /**
     * Gets the widget value.
     *
     * @return the value.
     */
    public abstract E getWidgetValue();

    /**
     * Gets the widget with the style name.
     *
     * @param styleName the style name.
     * @return the widget.
     */
    public abstract W getWidget(String styleName);
}
