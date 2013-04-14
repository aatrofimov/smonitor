/*
 * Copyright 2013 Andrej Petras <andrej@ajka-andrej.com>.
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
package org.lorislab.smonitor.admin.client.app.panel;

import org.lorislab.smonitor.gwt.uc.panel.EntityTablePanel;
import org.lorislab.smonitor.gwt.uc.table.EntityTable;
import org.lorislab.smonitor.gwt.uc.table.column.EntityBooleanColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityLongColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityTextColumn;
import org.lorislab.smonitor.admin.client.app.model.AttributeTableModel;
import org.lorislab.smonitor.admin.client.app.model.SessionDetailsModel;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.Column;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class AttributesPanel extends EntityTablePanel<SessionDetailsModel,AttributeTableModel> {
    
    public AttributesPanel() {
        super();
        
        EntityTable<AttributeTableModel> table = this.getTable();
        table.addColumn("Name", true, new EntityTextColumn<AttributeTableModel>(){
            @Override
            public String getObject(AttributeTableModel object) {
                return object.name;
            }
        });
        
        table.addColumn("Type", true, new EntityTextColumn<AttributeTableModel>(){
            @Override
            public String getObject(AttributeTableModel object) {
                return object.type;
            }
        });
        Column serColumn = table.addColumn("Serializable", true, new EntityBooleanColumn<AttributeTableModel>(){
            @Override
            public Boolean getObject(AttributeTableModel object) {
                return object.serializable;
            }
        });
        table.setColumnWidth(serColumn, 150, Style.Unit.PX);
        
        Column serSizeColumn =  table.addColumn("Ser. size", true, new EntityLongColumn<AttributeTableModel>(){
            @Override
            public Long getObject(AttributeTableModel object) {
                return object.serializableSize;
            }
        });
        table.setColumnWidth(serSizeColumn, 150, Style.Unit.PX);
        
        Column sizeColumn = table.addColumn("Size", true, new EntityLongColumn<AttributeTableModel>(){
            @Override
            public Long getObject(AttributeTableModel object) {
                return object.size;
            }
        });
        table.setColumnWidth(sizeColumn, 100, Style.Unit.PX);      
    }
    
}
