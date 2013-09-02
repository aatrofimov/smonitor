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
package org.lorislab.smonitor.profiler.utils;

/**
 * The object profile.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ObjectProfile {
    
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
