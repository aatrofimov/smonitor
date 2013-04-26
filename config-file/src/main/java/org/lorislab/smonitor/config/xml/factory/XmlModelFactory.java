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
package org.lorislab.smonitor.config.xml.factory;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.lorislab.smonitor.config.xml.model.XmlApplication;
import org.lorislab.smonitor.config.xml.model.XmlAttribute;
import org.lorislab.smonitor.config.xml.model.XmlConfiguration;
import org.lorislab.smonitor.store.factory.ModelFactory;
import org.lorislab.smonitor.store.model.Application;
import org.lorislab.smonitor.store.model.Attribute;
import org.lorislab.smonitor.store.model.Configuration;

/**
 * The XML model factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class XmlModelFactory extends ModelFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Application createApplication(List<Configuration> configurations) {
        XmlApplication result = new XmlApplication();
        result.setConfigurations(configurations);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Configuration createConfiguration(String name, List<Attribute> attributes) {
        XmlConfiguration result = new XmlConfiguration();
        result.setName(name);
        result.setAttributes(attributes);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Attribute createAtttr(String name, Object value, Class clazz) {
        XmlAttribute result = new XmlAttribute();
        result.setName(name);

        if (clazz.equals(String.class)) {
            result.setStringV((String) value);
        } else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            result.setIntegerV((Integer) value);
        } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
            result.setDoubleV((Double) value);
        } else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            result.setLongV((Long) value);
        } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            result.setBooleanV((Boolean) value);
        } else if (clazz.equals(Locale.class)) {
            result.setLocaleV((Locale) value);
        } else if (clazz.equals(Date.class)) {
            result.setDateV((Date) value);
        } else if (List.class.isAssignableFrom(clazz) ) {
            result.setListV((List) value);
        } else if (Map.class.isAssignableFrom(clazz) ) {
            result.setMapV((Map) value);
        } else {
            throw new RuntimeException("No supported type " + clazz);
        }
        
        return result;
    }
}
