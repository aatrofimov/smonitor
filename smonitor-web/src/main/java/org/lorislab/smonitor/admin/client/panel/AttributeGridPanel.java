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
import com.google.gwt.dom.client.Style.Unit;
import org.lorislab.smonitor.admin.client.model.AttributeWrapper;
import org.lorislab.smonitor.gwt.uc.table.column.EntitySpanColumn;
import org.lorislab.smonitor.rs.model.AttributeInfo;

/**
 *
 * @author Andrej Petras
 */
public final class AttributeGridPanel extends EntityDataGrid<AttributeInfo, AttributeWrapper> {

    @Override
    protected AttributeWrapper createWrapper() {
       return new AttributeWrapper();
    }

    @Override
    protected void createColumns() {
               
        Column colAction = addColumn(" ", true, new EntitySpanColumn<AttributeWrapper, Boolean>() {
            @Override
            public Boolean getObject(AttributeWrapper object) {
                return object.data.isSerializable();
            }

            @Override
            public String getValue(AttributeWrapper object) {
                if (object.data.isSerializable()) {
                    return "icon-download status-icon-green";
                }
                return "icon-attention status-icon-red";
            }
        });
        setColumnWidth(colAction, 25, Unit.PX);        
        
        
        Column colName = this.addColumn("Name", true, new EntityTextColumn<AttributeWrapper>() {
            @Override
            public String getObject(AttributeWrapper object) {
                return object.data.getName();
            }
        });
        this.setColumnWidth(colName, 250, Unit.PX);
        
        Column colType = this.addColumn("Type", true, new EntityTextColumn<AttributeWrapper>() {
            @Override
            public String getObject(AttributeWrapper object) {
                return object.data.getType();
            }
        });
        this.setColumnWidth(colType, 280, Unit.PX);  
                       
        Column colSerSize = this.addColumn("Ser. size", true, new EntityLongColumn<AttributeWrapper>() {
            @Override
            public Long getObject(AttributeWrapper object) {
                if (object.data.isSerializable()) {
                return object.data.getSerializableSize();
                }
                return null;
            }
        });
        this.setColumnWidth(colSerSize, 75, Unit.PX); 
        
        Column colSize = this.addColumn("Size", true, new EntityLongColumn<AttributeWrapper>() {
            @Override
            public Long getObject(AttributeWrapper object) {
                return object.data.getSize();
            }
        });
        this.setColumnWidth(colSize, 50, Unit.PX);        
    }
    
}
