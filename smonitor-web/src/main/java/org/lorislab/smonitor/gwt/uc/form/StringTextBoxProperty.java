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

/**
 *
 * @author Andrej Petras
 */
public abstract class StringTextBoxProperty<T> extends AbstractTextBoxProperty<T,String>{
    
    public StringTextBoxProperty() {
        super();
    }
    
    public StringTextBoxProperty(boolean readonly) {
        super(readonly);
    }
    
    @Override
    public String getWidgetValue() {
        return widget.getText();
    }

    @Override
    public void setWidgetValue(String value) {
        widget.setText(value);
    }
    
    
}
