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
package org.lorislab.smonitor.store.factory;

import java.util.List;
import org.lorislab.smonitor.config.factory.ObjectFactory;
import org.lorislab.smonitor.store.model.Application;
import org.lorislab.smonitor.store.model.Attribute;
import org.lorislab.smonitor.store.model.Configuration;

/**
 * The model factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class ModelFactory {

    /**
     * Creates the application model.
     *
     * @param configurations the list of configurations.
     * @return the new created application.
     */
    public abstract Application createApplication(List<Configuration> configurations);

    /**
     * Creates the configuration model.
     *
     * @param name the name.
     * @param attributes the list of attributes.
     * @return new create configuration.
     */
    public abstract Configuration createConfiguration(String name, List<Attribute> attributes);

    /**
     * Creates the attribute model.
     *
     * @param name the attribute name.
     * @param value the object value.
     * @param clazz the class of the value.
     * @return the new created attribute.
     */
    public Attribute createAtttribute(String name, Object value, Class clazz) {
        ObjectFactory.checkFieldType(clazz);
        return createAtttr(name, value, clazz);
    }

    /**
     * Creates the attribute model.
     *
     * @param name the attribute name.
     * @param value the object value.
     * @param clazz the class of the value.
     * @return the new created attribute.
     */
    protected abstract Attribute createAtttr(String name, Object value, Class clazz);
}
