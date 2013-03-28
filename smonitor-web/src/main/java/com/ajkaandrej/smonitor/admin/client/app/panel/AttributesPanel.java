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
package com.ajkaandrej.smonitor.admin.client.app.panel;

import com.ajkaandrej.gwt.uc.ConstantValues;
import com.ajkaandrej.gwt.uc.panel.EntityComposite;
import com.ajkaandrej.smonitor.admin.client.app.model.AttributeTableModel;
import com.ajkaandrej.smonitor.admin.client.app.model.SessionDetailsModel;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class AttributesPanel extends EntityComposite<SessionDetailsModel> {
    
    private CellTable<AttributeTableModel> table;
    private ListDataProvider<AttributeTableModel> dataProvider = new ListDataProvider<AttributeTableModel>();
    
    public AttributesPanel() {
        table = new CellTable<AttributeTableModel>();
        table.setWidth(ConstantValues.PCT_100, true);
        table.setAutoHeaderRefreshDisabled(true);
        table.setAutoFooterRefreshDisabled(true);
        table.setPageSize(10);
        
        dataProvider.addDataDisplay(table);
        
        SingleSelectionModel<AttributeTableModel> ssm = new SingleSelectionModel<AttributeTableModel>();
        table.setSelectionModel(ssm);
        
        TextColumn<AttributeTableModel> nameColumn = new TextColumn<AttributeTableModel>() {
            @Override
            public String getValue(AttributeTableModel object) {
                return object.name;
            }
        };      
        table.addColumn(nameColumn, "Name");
        
        TextColumn<AttributeTableModel> typeColumn = new TextColumn<AttributeTableModel>() {
            @Override
            public String getValue(AttributeTableModel object) {
                return object.type;
            }
        };      
        table.addColumn(typeColumn, "Type");
        
        TextColumn<AttributeTableModel> serializableColumn = new TextColumn<AttributeTableModel>() {
            @Override
            public String getValue(AttributeTableModel object) {
                return "" + object.serializable;
            }
        };      
        table.addColumn(serializableColumn, "Serializable");
        
        TextColumn<AttributeTableModel> serializableSizeColumn = new TextColumn<AttributeTableModel>() {
            @Override
            public String getValue(AttributeTableModel object) {
                return "" + object.serializableSize;
            }
        };      
        table.addColumn(serializableSizeColumn, "Serializable size");
        
        TextColumn<AttributeTableModel> sizeColumn = new TextColumn<AttributeTableModel>() {
            @Override
            public String getValue(AttributeTableModel object) {
                return "" + object.size;
            }
        };      
        table.addColumn(sizeColumn, "Size");
        
        SimplePager pager = new SimplePager();
        pager.setDisplay(table);
        
        VerticalPanel vp = new VerticalPanel();
        vp.add(table);
        vp.add(pager);
        vp.setCellHorizontalAlignment(pager, HasHorizontalAlignment.ALIGN_CENTER);

        initWidget(vp);        
    }
    
    public void load(SessionDetailsModel session, List<AttributeTableModel> attributes) {
        reset();
        this.data = session;
        dataProvider.getList().addAll(attributes);
    }
    
    public void reset() {
        dataProvider.getList().clear();
    }

}
