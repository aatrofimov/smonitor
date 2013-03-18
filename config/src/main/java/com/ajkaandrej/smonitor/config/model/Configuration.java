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
package com.ajkaandrej.smonitor.config.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The configuration model.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class Configuration implements Serializable {
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4738136507254007102L;
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());
    /**
     * The list splitter.
     */
    private static final String LIST_SPLIT = ",";    
    /**
     * The data.
     */
    private Map<String, String> data;
    /**
     * The module name.
     */
    private String module;

    /**
     * The default constructor.
     *
     * @param module the module name.
     */
    public Configuration(String module) {
        this.module = module;
        reset();
    }

    /**
     * Gets the module name.
     *
     * @return the module name.
     */
    public String getModule() {
        return module;
    }

    /**
     * Reset the configuration model and call the method to set the default
     * values.
     *
     * @see #setDefaultValues()
     */
    public final void reset() {
        data = new HashMap<String, String>();
        setDefaultValues();
    }

    /**
     * Gets the configuration data.
     *
     * @return the configuration data
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * Gets the configuration keys for the model.
     *
     * @return the configuration keys for the model.
     */
    public Set<String> getKeys() {
        return data.keySet();
    }

    /**
     * Adds the value to the key.
     *
     * @param key the key.
     * @param value the value.
     */
    protected void addValue(String key, String value) {
        if (key != null) {
            data.put(key, value);
        }
    }

    /**
     * Gets the string value.
     *
     * @param key the key.
     * @return the string value.
     */
    protected String getStringValue(String key) {
        return data.get(key);
    }

    /**
     * Gets the string list values for the key.
     *
     * @param key the key.
     * @return the string list values for the key.
     */
    protected List<String> getStringList(String key) {
        List<String> result = new ArrayList<String>();
        String value = data.get(key);
        if (value != null) {
            String[] items = value.split(LIST_SPLIT);
            result.addAll(Arrays.asList(items));
        }
        return result;
    }

    /**
     * Gets the boolean value for the key.
     *
     * @param key the key.
     * @return the boolean value.
     */
    protected boolean getBooleanValue(String key) {
        String value = data.get(key);
        return Boolean.parseBoolean(value);
    }

    /**
     * Gets the integer value for the key.
     *
     * @param key the key.
     * @return the integer value.
     */
    protected int getIntegerValue(String key) {
        int result = 0;
        try {
            String value = data.get(key);
            result = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            LOGGER.log(Level.FINEST, "Error convert string to integer valu. Key:" + key, ex);
        }
        return result;
    }

    /**
     * The abstract method to set-up the default values for the keys.
     */
    protected abstract void setDefaultValues();
}
