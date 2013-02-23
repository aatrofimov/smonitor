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
package com.ajkaandrej.smonitor.admin.client.model;

import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionAttribute;
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
    
    public AttributeInfoListGridRecord(ClientHttpSessionAttribute attr) {
        setAttribute(ATTR_NAME, attr.getName());
        setAttribute(ATTR_CLASS, attr.getClazz());
        setAttribute(ATTR_SIZE, attr.getSize());
        setAttribute(ATTR_SERIALIZABLE_SIZE, attr.getSerializableSize());
        setAttribute(ATTR_SERIALIZABLE, attr.isSerializable());
        setAttribute(ATTR_OBJECT, attr);
    }
    
    public ClientHttpSessionAttribute getObject() {
        return (ClientHttpSessionAttribute) getAttributeAsObject(ATTR_OBJECT);
    }
}
