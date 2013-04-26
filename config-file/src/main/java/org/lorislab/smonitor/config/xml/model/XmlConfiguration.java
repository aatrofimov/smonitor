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
package org.lorislab.smonitor.config.xml.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.lorislab.smonitor.store.model.Attribute;
import org.lorislab.smonitor.store.model.Configuration;

/**
 * The configuration model.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlConfiguration implements Configuration {

    /**
     * The name.
     */
    @XmlElement(name = "name")
    private String name;
    /**
     * The list of attributes.
     */ 
    @XmlElementWrapper(name = "attributes")
    @XmlElement(name = "attribute", type=XmlAttribute.class)
    private List<Attribute> attributes;

    /**
     * Sets the list of attributes.
     *
     * @param attributes the list of attributes.
     */
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
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
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Attribute> getAttributes() {
        return attributes;
    }
}
