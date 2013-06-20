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
package org.lorislab.smonitor.admin.client.panel;

import com.google.gwt.user.cellview.client.Column;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.column.EntityLongColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityTextColumn;
import org.lorislab.smonitor.rs.model.AttributeInfo;
import com.google.gwt.dom.client.Style.Unit;
import org.lorislab.smonitor.gwt.uc.table.column.EntityImageColumn;

/**
 *
 * @author Andrej Petras
 */
public class AttributeGridPanel extends AbstractGridPanel2<AttributeInfo> {

    @Override
    protected void createColumns() {
        
        Column colAction = addColumn(" ", true, new EntityImageColumn<AttributeInfo, Boolean>() {
            @Override
            public Boolean getObject(AttributeInfo object) {
                return object.isSerializable();
            }

            @Override
            public String getValue(AttributeInfo object) {
                if (object.isSerializable()) {
                    return "images/status_ok.png";
                }
                return "images/status_error.png";
            }
        });
        setColumnWidth(colAction, 25, com.google.gwt.dom.client.Style.Unit.PX);
        
        Column colName = this.addColumn("Name", true, new EntityTextColumn<AttributeInfo>() {
            @Override
            public String getObject(AttributeInfo object) {
                return object.getName();
            }
        });
        this.setColumnWidth(colName, 250, Unit.PX);
        
        Column colType = this.addColumn("Type", true, new EntityTextColumn<AttributeInfo>() {
            @Override
            public String getObject(AttributeInfo object) {
                return object.getType();
            }
        });
        this.setColumnWidth(colType, 280, Unit.PX);  
                       
        Column colSerSize = this.addColumn("Ser. size", true, new EntityLongColumn<AttributeInfo>() {
            @Override
            public Long getObject(AttributeInfo object) {
                if (object.isSerializable()) {
                return object.getSerializableSize();
                }
                return null;
            }
        });
        this.setColumnWidth(colSerSize, 75, Unit.PX); 
        
        Column colSize = this.addColumn("Size", true, new EntityLongColumn<AttributeInfo>() {
            @Override
            public Long getObject(AttributeInfo object) {
                return object.getSize();
            }
        });
        this.setColumnWidth(colSize, 50, Unit.PX);        
    }

    @Override
    protected AttributeInfo findById(final Object id) {
        AttributeInfo item = find(new EntityDataGrid.FilterItem<AttributeInfo>() {
            @Override
            public AttributeInfo isItem(AttributeInfo item) {
                if (item.getName().equals((String) id)) {
                    return item;
                }
                return null;
            }
        });
        return item;
    }
    
}
