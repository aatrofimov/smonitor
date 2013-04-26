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
package org.lorislab.smonitor.config.factory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lorislab.smonitor.store.model.Application;
import org.lorislab.smonitor.store.model.Attribute;
import org.lorislab.smonitor.store.model.Configuration;

/**
 * The object factory.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ObjectFactory {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ObjectFactory.class.getName());
    /**
     * The set of supported field types.
     */
    private static final Set<Class> SUPPORTED_TYPES = new HashSet<Class>();

    /**
     * The static block.
     */
    static {
        SUPPORTED_TYPES.add(String.class);
        SUPPORTED_TYPES.add(Integer.class);
        SUPPORTED_TYPES.add(int.class);
        SUPPORTED_TYPES.add(Boolean.class);
        SUPPORTED_TYPES.add(boolean.class);
        SUPPORTED_TYPES.add(Double.class);
        SUPPORTED_TYPES.add(double.class);
        SUPPORTED_TYPES.add(Long.class);
        SUPPORTED_TYPES.add(long.class);
        SUPPORTED_TYPES.add(Date.class);
        SUPPORTED_TYPES.add(Locale.class);
        SUPPORTED_TYPES.add(List.class);
        SUPPORTED_TYPES.add(Map.class);
    }

    /**
     * The default private constructor.
     */
    private ObjectFactory() {
        // empty constructor
    }

    /**
     * Creates the list of objects for the application.
     *
     * @param application the application.
     * @return the list of objects.
     */
    public static List<Object> createObjects(Application application) {
        List<Object> result = new ArrayList<Object>();
        if (application != null) {
            result = createObjects(application.getConfigurations());
        }
        return result;
    }

    /**
     * Creates the list of objects.
     *
     * @param configurations the list of configurations.
     * @return the list of objects.
     */
    public static List<Object> createObjects(List<Configuration> configurations) {
        List<Object> result = new ArrayList<Object>();
        if (configurations != null) {
            for (Configuration configuration : configurations) {
                Object value = createConfiguration(configuration);
                if (value != null) {
                    result.add(value);
                }
            }
        }
        return result;
    }

    /**
     * Creates the configuration object.
     *
     * @param configuration the configuration.
     * @return the configuration object or null if error.
     */
    private static Object createConfiguration(Configuration configuration) {
        Object result = null;
        try {
            Object item = createInstance(configuration.getName());
            if (item != null) {
                // load all attributes
                List<Attribute> attributes = configuration.getAttributes();
                if (attributes != null) {

                    // sets the attribute to the object.
                    for (Attribute attribute : attributes) {
                        setAttribute(item, attribute.getName(), attribute.getValue());
                    }
                }
            } else {
                LOGGER.log(Level.WARNING, "The configration instance {0} is null!", configuration.getName());
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error creating the configuration " + configuration.getName(), ex);
        }
        return result;
    }

    /**
     * Sets the attribute to the corresponding field in the object.
     *
     * @param item the object.
     * @param attribute the attribute.
     */
    private static void setAttribute(Object item, String name, Object value) throws Exception {
        if (value != null) {
            try {
                Class clazz = item.getClass();
                Field field = clazz.getDeclaredField(name);

                boolean accessible = field.isAccessible();
                try {
                    field.setAccessible(true);
                    field.set(item, value);
                } finally {
                    field.setAccessible(accessible);
                }

            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Could not set the attribute " + name + " to the object " + item.getClass(), ex);
                throw ex;
            }
        } else {
            LOGGER.log(Level.FINE, "The attribute {0} has a null value. Could not be set!", name);
        }
    }

    /**
     * Creates the instance of the class.
     *
     * @param className the class name.
     * @return the object instance.
     */
    private static Object createInstance(String className) {
        Object result = null;
        try {
            Class clazz = Class.forName(className);
            result = createInstance(clazz);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error create new instance of the class name {0}", className);
            throw new RuntimeException("Error create new instance for the class name " + className, ex);
        }
        return result;
    }

    /**
     * Creates the instance of the class.
     *
     * @param className the class.
     * @return the object instance.
     */
    public static <T> T createInstance(Class<T> clazz) {
        T result = null;
        try {
            result = clazz.getConstructor().newInstance();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error create new instance of the class " + clazz.getName(), ex);
            throw new RuntimeException("Error create new instance for the class " + clazz.getName(), ex);
        }
        return result;
    }

    /**
     * Checks the field type.
     *
     * @param clazz the field class.
     */
    public static void checkFieldType(Class clazz) {
        if (!SUPPORTED_TYPES.contains(clazz)) {
            throw new RuntimeException("The field type is not supported. Type: " + clazz.getName());
        }
    }
}
