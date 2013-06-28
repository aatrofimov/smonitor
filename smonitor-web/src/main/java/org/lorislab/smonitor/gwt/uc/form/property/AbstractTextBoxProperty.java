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

import com.google.gwt.user.client.ui.TextBox;

/**
 * The abstract text box property.
 * 
 * @author Andrej Petras
 * @param <T> the object.
 * @param <E> the value.
 */
public abstract class AbstractTextBoxProperty<T, E> extends ModelFormProperty<T, TextBox, E>{

    /**
     * The default constructor.
     */
    public AbstractTextBoxProperty() {
        this(false);
    }
    
    /**
     * The default constructor.
     * @param readonly the read only flag.
     */
    public AbstractTextBoxProperty(boolean readonly) {
        super(new TextBox());
        widget.setReadOnly(readonly);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TextBox getWidget(String styleName) {        
        if (styleName != null) {
            widget.setStyleName(styleName);
        }
        return widget;
    }
    
}
