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

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface ConfigurationService {
    
    /**
     * Loads the configuration model.
     *
     * @param <T> the configuration model.
     * @param clazz the class of the configuration model.
     * @return the configuration model.
     * @throws ConfigurationException if the method fails.
     */
    public <T extends Configuration> T loadConfiguration(Class<T> clazz) throws ConfigurationException;

    /**
     * Reloads the server configuration.
     *
     * @throws ConfigurationException if the method fails.
     */
    public void reloadConfigurations() throws ConfigurationException;
         
    /**
     * Loads the configuration model.
     *
     * @param <T> the configuration model.
     * @return the configuration model.
     * @throws ConfigurationException if the method fails.
     */
    public <T extends Configuration> T loadConfiguration(T model) throws ConfigurationException;
    
    /**
     * Saves the configuration model.
     * 
     * @param <T> the configuration model type.
     * @param model the configuration model.
     * @return the saved configuration model.
     * @throws ConfigurationException if the method fails.
     */
    public <T extends Configuration> T saveConfiguration(T model) throws ConfigurationException;
 
}
