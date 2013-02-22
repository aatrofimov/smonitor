/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajkaandrej.smonitor.profiler.utils;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ObjectProfile {
    
    private long size;
    
    private long serializableSize;
    
    private boolean serializable;

    private String clazz;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
        
    public long getSerializableSize() {
        return serializableSize;
    }

    public long getSize() {
        return size;
    }

    public boolean isSerializable() {
        return serializable;
    }

    public void setSerializable(boolean serializable) {
        this.serializable = serializable;
    }

    public void setSerializableSize(long serializableSize) {
        this.serializableSize = serializableSize;
    }

    public void setSize(long size) {
        this.size = size;
    }
        
}
