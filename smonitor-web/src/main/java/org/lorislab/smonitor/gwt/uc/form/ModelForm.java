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
 *
 * @author Andrej Petras
 */
public abstract class ModelForm<T> extends FlexTable {
    
    private int column;

    private String labelStyleName;

    private String valueStyleName;
  
    private T data;
    
    private List<ModelFormProperty> properties;
    
    private Map<Integer, Set<Integer>> spanRows;
    
    public ModelForm() {
        properties = new ArrayList<ModelFormProperty>();
    }

    public void init() {
        spanRows = new HashMap<Integer,Set<Integer>>();
        createProperties();
        spanRows.clear();
        spanRows = null;
    }
    
    protected abstract void createProperties();
    
    public T getData() {
        return data;
    }
        
    public void open(T data) {
        this.data = data;
        for (ModelFormProperty property : properties) {
            property.load(data);
        }
    }
    
    public String getValueStyleName() {
        return valueStyleName;
    }

    public void setValueStyleName(String valueStyleName) {
        this.valueStyleName = valueStyleName;
    }
        
    public String getLabelStyleName() {
        return labelStyleName;
    }

    public void setLabelStyleName(String labelStyleName) {
        this.labelStyleName = labelStyleName;
    }
        
    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }
        
    public void addProperty(String label, ModelFormProperty property) {
        addProperty(label, property, -1, -1);
    }
    
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
                        
            for (int i=1; i<rowSpan; i++) {
                tmp.add(row+i);
            }
        }
        if (colSpan > -1 && colSpan != 1) {
           cf.setColSpan(row, col, colSpan);
           cf.setColSpan(row, colLabel, colSpan);
        }                        
    }
}
