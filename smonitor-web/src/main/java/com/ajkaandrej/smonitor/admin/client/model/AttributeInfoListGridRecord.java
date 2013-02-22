/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.admin.client.model;

import com.ajkaandrej.smonitor.admin.shared.model.AttributeInfo;
import com.ajkaandrej.smonitor.admin.shared.model.SessionInfo;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class AttributeInfoListGridRecord extends ListGridRecord {

    public static final String ATTR_NAME = "name";
    
    public static final String ATTR_CLASS = "class";
    
    public static final String ATTR_SIZE = "size";
    
    public static final String ATTR_SERIALIZABLE_SIZE = "ser_size";

    public static final String ATTR_SERIALIZABLE = "serializable";
    
    public static final String ATTR_OBJECT = "object";
    
    public AttributeInfoListGridRecord(AttributeInfo attr) {
        setAttribute(ATTR_NAME, attr.getName());
        setAttribute(ATTR_CLASS, attr.getClazz());
        setAttribute(ATTR_SIZE, attr.getSize());
        setAttribute(ATTR_SERIALIZABLE_SIZE, attr.getSerializableSize());
        setAttribute(ATTR_SERIALIZABLE, attr.isSerializable());
        setAttribute(ATTR_OBJECT, attr);
    }
    
    public AttributeInfo getObject() {
        return (AttributeInfo) getAttributeAsObject(ATTR_OBJECT);
    }
}
