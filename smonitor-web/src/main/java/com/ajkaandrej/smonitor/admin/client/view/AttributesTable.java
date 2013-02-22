/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.admin.client.view;

import com.ajkaandrej.smonitor.admin.client.model.AttributeInfoListGridRecord;
import com.ajkaandrej.smonitor.admin.shared.model.AttributeInfo;
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

    public void loadAttributes(AttributeInfo[] attributes) {
        AttributeInfoListGridRecord[] result = new AttributeInfoListGridRecord[0];
        if (attributes != null) {
            result = new AttributeInfoListGridRecord[attributes.length];
            for (int i = 0; i < attributes.length; i++) {
                result[i] = new AttributeInfoListGridRecord(attributes[i]);
            }
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
