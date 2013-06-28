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

import com.google.gwt.user.client.ui.ListBox;
import java.util.ArrayList;
import java.util.List;

/**
 * The string list box property.
 *
 * @author Andrej Petras
 * @param <T> the object.
 */
public abstract class StringListBoxProperty<T> extends ModelFormProperty<T, ListBox, List<String>> {

    /**
     * The default constructor.
     */
    public StringListBoxProperty() {
        this(false, false);
    }

    /**
     * The default constructor.
     *
     * @param isMultipleSelect is the list multiple select.
     */
    public StringListBoxProperty(boolean isMultipleSelect) {
        this(isMultipleSelect, false);
    }

    /**
     * The default constructor.
     *
     * @param isMultipleSelect is the list multiple select.
     * @param readOnly the read only flag.
     */
    public StringListBoxProperty(boolean isMultipleSelect, boolean readonly) {
        super(new ListBox(isMultipleSelect));
        widget.setEnabled(!readonly);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListBox getWidget(String styleName) {
        if (styleName != null) {
            widget.setStyleName(styleName);
        }
        return widget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getWidgetValue() {
        List<String> result = null;
        int index = widget.getSelectedIndex();
        if (index != -1) {
            result = new ArrayList<String>();
            if (widget.isMultipleSelect()) {
                int count = widget.getItemCount();
                for (int i = 0; i < count; i++) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidgetValue(List<String> value) {
        widget.clear();
        for (String item : value) {
            widget.addItem(item);
        }
    }
}
