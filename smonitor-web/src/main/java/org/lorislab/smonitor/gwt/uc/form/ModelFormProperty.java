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
package org.lorislab.smonitor.gwt.uc.form;

import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Andrej Petras
 */
public abstract class ModelFormProperty<T, W extends Widget, E> {
    
    protected W widget;
    
    public ModelFormProperty(W widget) {
        this.widget = widget;
    }
    
    public abstract E getValue(T object);
    
    public void setValue(T object, E value) {
    }
    
    public void load(T object) {
        setWidgetValue(getValue(object));
    }
    
    public void save(T object) {
        setValue(object, getWidgetValue());
    }
    
    public abstract void setWidgetValue(E value);
    
    public abstract E getWidgetValue();
    
    public abstract W getWidget(String styleName);
}
