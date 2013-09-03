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

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.gwt.uc.table.column.EntityLongColumn;
import org.lorislab.smonitor.gwt.uc.table.column.EntityTextColumn;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineHTML;
import org.lorislab.smonitor.admin.client.model.AttributeWrapper;
import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.gwt.uc.table.column.EntitySpanColumn;
import org.lorislab.smonitor.rs.model.AttributeInfo;
import org.lorislab.smonitor.rs.model.SessionInfoDetails;

/**
 *
 * @author Andrej Petras
 */
public final class AttributeGridPanel extends EntityDataGrid<AttributeInfo, AttributeWrapper> {

    private static final String[] UNITS = {"B","K","M","G","T","P","E","",""};
    
    private SessionInfoDetails sessionInfoDetails;

    public void setSessionInfoDetails(SessionInfoDetails sessionInfoDetails) {
        this.sessionInfoDetails = sessionInfoDetails;
    }

    @Override
    protected AttributeWrapper createWrapper() {
        return new AttributeWrapper();
    }

    @Override
    protected void createColumns() {
        setAutoFooterRefreshDisabled(false);
                
        Column colAction = addColumn(ConstantValues.space(), true, new EntitySpanColumn<AttributeWrapper, Boolean>() {
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
        this.setColumnWidth(colName, 220, Unit.PX);

        Column colType = this.addColumn("Type", true, new EntityTextColumn<AttributeWrapper>() {
            @Override
            public String getObject(AttributeWrapper object) {
                return object.data.getType();
            }
        });
        this.setColumnWidth(colType, 290, Unit.PX);

        Header<String> footerSerSize = new Header<String>(new TextCell()) {
            @Override
            public String getValue() {
                if (sessionInfoDetails == null) {
                    return "0B";
                } else {
                    return convertBytes(sessionInfoDetails.getSizeSerializable());
                }
            }
        };

        Column colSerSize = this.addColumn("Ser. size", true, new EntityLongColumn<AttributeWrapper>() {
            @Override
            public Long getObject(AttributeWrapper object) {
                if (object.data.isSerializable()) {
                    return object.data.getSerializableSize();
                }
                return null;
            }
        }, footerSerSize);
        this.setColumnWidth(colSerSize, 75, Unit.PX);

        Header<String> footerSize = new Header<String>(new TextCell()) {
            @Override
            public String getValue() {
                if (sessionInfoDetails == null) {
                    return "0B";
                } else {
                    return convertBytes(sessionInfoDetails.getSize());
                }
            }
        };

        Column colSize = this.addColumn("Size", true, new EntityLongColumn<AttributeWrapper>() {
            @Override
            public Long getObject(AttributeWrapper object) {
                return object.data.getSize();
            }
        }, footerSize);
        this.setColumnWidth(colSize, 70, Unit.PX);
    }

    public static String convertBytes(double bytes) {
        int unit = 1024;
        int exp = 0;
        double tmp = bytes;
        String pre = UNITS[exp];
        
        if (tmp >= unit) {
            exp = (int) (Math.log(tmp) / Math.log(unit));
            tmp = tmp / Math.pow(unit, exp);
            pre = UNITS[exp] + pre;
        }
        
        return NumberFormat.getFormat("#.00").format(tmp) + pre;
    }
}
