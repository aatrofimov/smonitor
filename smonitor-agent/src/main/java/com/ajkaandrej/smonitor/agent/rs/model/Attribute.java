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
package com.ajkaandrej.smonitor.agent.rs.model;

/**
 * The session attribute.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class Attribute {

    /**
     * The name.
     */
    private String name;
    /**
     * The type.
     */
    private String type;
    /**
     * The attribute memory size.
     */
    private long size;
    /**
     * The serialisable flag.
     */
    private boolean serializable;
    /**
     * The serialisable size.
     */
    private long serializableSize;

    /**
     * Gets the serialisable flag.
     *
     * @return <code>true</code> if the attribute is serialisable.
     */
    public boolean isSerializable() {
        return serializable;
    }

    /**
     * Sets serialisable flag.
     *
     * @param serializable serialisable flag.
     */
    public void setSerializable(boolean serializable) {
        this.serializable = serializable;
    }

    /**
     * Sets serialisable size.
     *
     * @param serializableSize serialisable size.
     */
    public void setSerializableSize(long serializableSize) {
        this.serializableSize = serializableSize;
    }

    /**
     * Gets serialisable size.
     *
     * @return serialisable size.
     */
    public long getSerializableSize() {
        return serializableSize;
    }

    /**
     * Gets the type.
     *
     * @return the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the size.
     *
     * @return the size.
     */
    public long getSize() {
        return size;
    }

    /**
     * Sets the size.
     *
     * @param size the size.
     */
    public void setSize(long size) {
        this.size = size;
    }
}
