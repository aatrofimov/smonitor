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

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.lorislab.smonitor.config.xml.adapter.DateAdapter;
import org.lorislab.smonitor.config.xml.adapter.LocaleAdapter;
import org.lorislab.smonitor.store.model.Attribute;

/**
 * The configuration attribute.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@XmlRootElement(name = "attribute")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlAttribute implements Attribute {

    /**
     * The name.
     */
    @XmlElement(name = "name", nillable = true)
    private String name;
    /**
     * The string value.
     */
    @XmlElement(name = "string", nillable = false)
    private String stringV;
    /**
     * The integer value.
     */
    @XmlElement(name = "integer", nillable = false)
    private Integer integerV;
    /**
     * The double value.
     */
    @XmlElement(name = "double", nillable = false)
    private Double doubleV;
    /**
     * The long value.
     */
    @XmlElement(name = "long", nillable = false)
    private Long longV;
    /**
     * The date value.
     */
    @XmlElement(name = "date", nillable = false)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dateV;
    /**
     * The boolean value.
     */
    @XmlElement(name = "boolean", nillable = false)
    private Boolean booleanV;
    /**
     * The list value.
     */
    @XmlElementWrapper(name = "list", nillable = false)
    @XmlElement(name="item", nillable = false)
    private List<String> listV;
    /**
     * The map value.
     */
    @XmlElementWrapper(name = "map", nillable = false)
    private Map<String, String> mapV;
    /**
     * The locale value.
     */
    @XmlElement(name = "locale", nillable = false)
    @XmlJavaTypeAdapter(LocaleAdapter.class)
    private Locale localeV;

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
    public Object getValue() {
        return getValue(getStringV(), getBooleanV(), getIntegerV(), getDoubleV(), getLongV(), getDateV(), getListV(), getMapV(), getLocaleV());
    }

    /**
     * Gets the string value.
     *
     * @return the string value.
     */
    public String getStringV() {
        return stringV;
    }

    /**
     * Sets the string value.
     *
     * @param stringV the string value.
     */
    public void setStringV(String stringV) {
        this.stringV = stringV;
    }

    /**
     * Gets the integer value.
     *
     * @return the integer value.
     */
    public Integer getIntegerV() {
        return integerV;
    }

    /**
     * Sets the integer value.
     *
     * @param integerV the integer value.
     */
    public void setIntegerV(Integer integerV) {
        this.integerV = integerV;
    }

    /**
     * Gets the double value.
     *
     * @return the double value.
     */
    public Double getDoubleV() {
        return doubleV;
    }

    /**
     * Sets the double value.
     *
     * @param doubleV the double value.
     */
    public void setDoubleV(Double doubleV) {
        this.doubleV = doubleV;
    }

    /**
     * Gets the long value.
     *
     * @return the long value.
     */
    public Long getLongV() {
        return longV;
    }

    /**
     * Sets the long value.
     *
     * @param longV the long value.
     */
    public void setLongV(Long longV) {
        this.longV = longV;
    }

    /**
     * Gets the date value.
     *
     * @return the date value.
     */
    public Date getDateV() {
        return dateV;
    }

    /**
     * Sets the date value.
     *
     * @param dateV the date value.
     */
    public void setDateV(Date dateV) {
        this.dateV = dateV;
    }

    /**
     * Gets the boolean value.
     *
     * @return the boolean value.
     */
    public Boolean getBooleanV() {
        return booleanV;
    }

    /**
     * Sets the boolean value.
     *
     * @param booleanV the boolean value.
     */
    public void setBooleanV(Boolean booleanV) {
        this.booleanV = booleanV;
    }

    /**
     * Gets the list value.
     *
     * @return the list value.
     */
    public List<String> getListV() {
        return listV;
    }

    /**
     * Sets the list value.
     *
     * @param listV the list value.
     */
    public void setListV(List<String> listV) {
        this.listV = listV;
    }

    /**
     * Gets the map value.
     *
     * @return the map value.
     */
    public Map<String, String> getMapV() {
        return mapV;
    }

    /**
     * Sets the map value.
     *
     * @param mapV the map value.
     */
    public void setMapV(Map<String, String> mapV) {
        this.mapV = mapV;
    }

    /**
     * Gets the locale value.
     *
     * @return the locale value.
     */
    public Locale getLocaleV() {
        return localeV;
    }

    /**
     * Sets the locale value.
     *
     * @param localeV the locale value.
     */
    public void setLocaleV(Locale localeV) {
        this.localeV = localeV;
    }

    /**
     * Gets the first not null value.
     *
     * @param values the list of values.
     * @return the first not null value or null.
     */
    private static Object getValue(Object... values) {
        for (Object value : values) {
            if (value != null) {
                return value;
            }
        }
        return null;
    }
}
