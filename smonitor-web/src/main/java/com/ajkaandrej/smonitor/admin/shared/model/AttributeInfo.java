package com.ajkaandrej.smonitor.admin.shared.model;

import java.io.Serializable;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class AttributeInfo implements Serializable {
    
    private static final long serialVersionUID = 345191636202612543L;
    
    private String name;
    
    private String clazz;
    
    private long size;

    private boolean serializable;
    
    private long serializableSize;

    public boolean isSerializable() {
        return serializable;
    }

    public void setSerializable(boolean serializable) {
        this.serializable = serializable;
    }

    public void setSerializableSize(long serializableSize) {
        this.serializableSize = serializableSize;
    }

    public long getSerializableSize() {
        return serializableSize;
    }
    
    
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}
