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

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras
 */
public abstract class StringListBoxProperty<T> extends ModelFormProperty<T, ListBox, List<String>> {

    public StringListBoxProperty() {
        this(false, false);
    }

    public StringListBoxProperty(boolean isMultipleSelect) {
        this(isMultipleSelect, false);
    }

    public StringListBoxProperty(boolean isMultipleSelect, boolean readonly) {
        super(new ListBox(isMultipleSelect));
        widget.setEnabled(!readonly);
    }

    @Override
    public ListBox getWidget(String styleName) {        
        if (styleName != null) {
            widget.setStyleName(styleName);
        }
        return widget;
    }
    
    @Override
    public List<String> getWidgetValue() {
        List<String> result = null;
        int index = widget.getSelectedIndex();
        if (index != -1) {
            result = new ArrayList<String>();
            if (widget.isMultipleSelect()) {
                int count = widget.getItemCount();
                for (int i=0; i<count; i++) {
                    if (widget.isItemSelected(i)) {
                        result.add(widget.getValue(i));
                    }
                }
            } else {
                result.add(widget.getValue(index));
            }
        }
        return result;
    }

    @Override
    public void setWidgetValue(List<String> value) {
        widget.clear();
        for (String item : value) {
            widget.addItem(item);
        }
    }
}
