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
package com.ajkaandrej.smonitor.admin.client.view;

import com.ajkaandrej.smonitor.admin.client.model.AttributeInfoListGridRecord;
import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionAttribute;
import com.smartgwt.client.types.AutoFitWidthApproach;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class AttributesTable extends ListGrid {

    public AttributesTable() {
        setWidth100();
        setHeight100();
        setShowAllRecords(true);
        setFields(createFields());
    }

    public void loadData(List<ClientHttpSessionAttribute> attributes) {
        AttributeInfoListGridRecord[] result = null;
        if (attributes != null) {
            result = new AttributeInfoListGridRecord[attributes.size()];
            for (int i = 0; i < attributes.size(); i++) {
                result[i] = new AttributeInfoListGridRecord(attributes.get(i));
            }
        } else {
            result = new AttributeInfoListGridRecord[0];
        }
        setData(result);
    }

    private static ListGridField[] createFields() {
        List<ListGridField> tmp = new ArrayList<ListGridField>();
        tmp.add(new ListGridField(AttributeInfoListGridRecord.ATTR_NAME, "Name"));
        tmp.add(new ListGridField(AttributeInfoListGridRecord.ATTR_CLASS, "Class"));
        tmp.add(new ListGridField(AttributeInfoListGridRecord.ATTR_SIZE, "Size"));
        tmp.add(new ListGridField(AttributeInfoListGridRecord.ATTR_SERIALIZABLE_SIZE, "Serializable size"));
        tmp.add(new ListGridField(AttributeInfoListGridRecord.ATTR_SERIALIZABLE, "Serializable"));

        for (ListGridField field : tmp) {
            field.setShowDefaultContextMenu(false);
            field.setAutoFitWidthApproach(AutoFitWidthApproach.VALUE);
        }
        return tmp.toArray(new ListGridField[tmp.size()]);
    }
}
