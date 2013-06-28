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

import org.lorislab.smonitor.gwt.uc.form.property.ModelFormProperty;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The abstract model form.
 *
 * @author Andrej Petras
 * @param <T> the object.
 */
public abstract class ModelForm<T> extends FlexTable {

    /**
     * The number of column.
     */
    private int column;
    /**
     * The label style name.
     */
    private String labelStyleName;
    /**
     * The value style name.
     */
    private String valueStyleName;
    /**
     * The data.
     */
    private T data;
    /**
     * The list of properties.
     */
    private List<ModelFormProperty> properties;
    /**
     * The temporary map of span rows.
     */
    private Map<Integer, Set<Integer>> spanRows;

    /**
     * The default constructor.
     */
    public ModelForm() {
        properties = new ArrayList<ModelFormProperty>();
    }

    /**
     * initialise method.
     */
    public void init() {
        spanRows = new HashMap<Integer, Set<Integer>>();
        createProperties();
        spanRows.clear();
        spanRows = null;
    }

    /**
     * Create the properties for the form.
     */
    protected abstract void createProperties();

    /**
     * Gets the data.
     *
     * @return the data.
     */
    public T getData() {
        return data;
    }

    /**
     * Opens the data.
     *
     * @param data the data.
     */
    public void open(T data) {
        this.data = data;
        for (ModelFormProperty property : properties) {
            property.load(data);
        }
    }

    /**
     * Gets the value style name.
     *
     * @return the value style name.
     */
    public String getValueStyleName() {
        return valueStyleName;
    }

    /**
     * Sets the value style name.
     *
     * @param valueStyleName the value style name.
     */
    public void setValueStyleName(String valueStyleName) {
        this.valueStyleName = valueStyleName;
    }

    /**
     * Gets the label style name.
     *
     * @return the label style name.
     */
    public String getLabelStyleName() {
        return labelStyleName;
    }

    /**
     * Sets the label style name.
     *
     * @param labelStyleName the label style name.
     */
    public void setLabelStyleName(String labelStyleName) {
        this.labelStyleName = labelStyleName;
    }

    /**
     * Sets the column.
     *
     * @param column the column.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Gets the column.
     *
     * @return the column.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Adds the property to the form.
     *
     * @param label the label.
     * @param property the property.
     */
    public void addProperty(String label, ModelFormProperty property) {
        addProperty(label, property, -1, -1);
    }

    /**
     * Adds the property to the form.
     *
     * @param label the label.
     * @param property the property.
     * @param rowSpan the row span.
     * @param colSpan the col span.
     */
    public void addProperty(String label, ModelFormProperty property, int rowSpan, int colSpan) {

        properties.add(property);

        int row = getRowCount();
        row = row - 1;

        if (row < 0) {
            row = 0;
            insertRow(row);
        }

        int col = getCellCount(row);
        Set<Integer> check = spanRows.get(col);
        if (check != null) {
            if (check.contains(row)) {
                col = col + 2;
            }
        }

        if (col >= (column * 2)) {
            col = 0;
            row++;
        }

        int colLabel = col;
        Label title = new Label(label);
        if (labelStyleName != null) {
            title.setStyleName(labelStyleName);
        }
        setWidget(row, colLabel, title);

        col++;
        Widget item = property.getWidget(valueStyleName);
        setWidget(row, col, item);

        FlexTable.FlexCellFormatter cf = getFlexCellFormatter();
        if (rowSpan > -1 && rowSpan != 1) {
            cf.setRowSpan(row, col, rowSpan);
            cf.setRowSpan(row, colLabel, rowSpan);

            Set<Integer> tmp = spanRows.get(colLabel);
            if (tmp == null) {
                tmp = new HashSet<Integer>();
                spanRows.put(colLabel, tmp);
            }

            for (int i = 1; i < rowSpan; i++) {
                tmp.add(row + i);
            }
        }
        if (colSpan > -1 && colSpan != 1) {
            cf.setColSpan(row, col, colSpan);
            cf.setColSpan(row, colLabel, colSpan);
        }
    }
}
