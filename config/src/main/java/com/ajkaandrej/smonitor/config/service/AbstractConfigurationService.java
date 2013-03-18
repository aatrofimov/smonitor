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
package com.ajkaandrej.smonitor.config.service;

import com.ajkaandrej.smonitor.config.exception.ConfigurationException;
import com.ajkaandrej.smonitor.config.model.Configuration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class AbstractConfigurationService implements ConfigurationService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractConfigurationService.class.getName());
    /**
     * The cache parameter.
     */
    private Map<String, Map<String, String>> cache;

    /**
     * The default constructor
     */
    public AbstractConfigurationService() {
        cache = null;
    }

    /**
     * Loads the configuration model.
     *
     * @param <T> the configuration model.
     * @param clazz the class of the configuration model.
     * @return the configuration model.
     * @throws ConfigurationException if the method fails.
     */
    @Override
    public <T extends Configuration> T loadConfiguration(Class<T> clazz) throws ConfigurationException {
        T result = null;
        try {
            result = clazz.getConstructor().newInstance();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error by creating the configuration model instance!", ex);
        }
        result = loadConfiguration(result);
        return result;
    }

    /**
     * Reloads the server configuration.
     *
     * @throws ConfigurationException if the method fails.
     */
    @Override
    public void reloadConfigurations() throws ConfigurationException {
        try {
            if (cache != null) {
                cache.clear();
            }
            cache = new HashMap<String, Map<String, String>>();
            loadConfigurations();
        } finally {
            cache = null;
        }
    }

    /**
     * Gets the module configuration parameter.
     *
     * @param application the application (module).
     * @param keys the set of keys.
     * @return the map of the configuration parameter.
     */
    private Map<String, String> getModuleProperties(String application, Set<String> keys) {
        Map<String, String> config = cache.get(application);
        Map<String, String> result = new HashMap<String, String>();
        if (config != null && keys != null) {
            for (String key : keys) {
                String param = config.get(key);
                if (param != null) {
                    result.put(key, param);
                }
            }
        }
        return result;
    }

    /**
     * Loads the configuration model.
     *
     * @param <T> the configuration model.
     * @return the configuration model.
     * @throws ConfigurationException if the method fails.
     */
    @Override
    public <T extends Configuration> T loadConfiguration(T model) throws ConfigurationException {
        if (cache == null) {
            reloadConfigurations();
        }
        if (model != null) {
            Map<String, String> data = getModuleProperties(model.getModule(), model.getKeys());
            model.getData().putAll(data);
        }
        return model;
    }

    /**
     * Adds the configuration for the module.
     *
     * @param module the module name.
     * @param data the configuration data.
     */
    protected void updateConfiguration(String module, Map<String, String> data) {
        if (module != null && data != null) {
            Map<String, String> tmp = cache.get(module);
            if (tmp != null) {
                tmp.putAll(data);
            } else {
                cache.put(module, data);
            }
        }
    }

    @Override
    public <T extends Configuration> T saveConfiguration(T model) throws ConfigurationException {
        T result = null;
        if (model != null) {
            Map<String,String> data = saveConfiguration(model.getModule(), model.getData());
            if (data != null) {
                updateConfiguration(model.getModule(), data);
                result = loadConfiguration(model);
            }
        }
        return result;
    }

    /**
     * Loads the configuration.
     *
     * @throws ConfigurationException if the method fails.
     */
    protected abstract void loadConfigurations() throws ConfigurationException;

    /**
     * Saves the configuration model.
     *
     * @param name the configuration module name.
     * @param data the configuration module map.
     * @return the configuration map.
     * @throws ConfigurationException if the method fails.
     */
    protected abstract Map<String,String> saveConfiguration(String name, Map<String,String> data) throws ConfigurationException;
}
