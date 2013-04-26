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
package org.lorislab.smonitor.config.xml.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lorislab.smonitor.config.exception.ConfigurationException;
import org.lorislab.smonitor.config.xml.util.XmlUtil;
import org.lorislab.smonitor.store.model.Application;
import org.lorislab.smonitor.store.service.ConfigurationStoreService;

/**
 * The XML configuration store service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class XmlConfigurationStoreService implements ConfigurationStoreService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(XmlConfigurationStoreService.class.getName());
    /**
     * The system property.
     */
    private static final String PROPERTY = XmlConfigurationStoreService.class.getName();
    /**
     * The default name.
     */
    private static final String DEFAULT_FILE = "configuration.xml";

    /**
     * {@inheritDoc}
     */
    @Override
    public Application load() throws ConfigurationException {
        Application result = null;
        String file = getFile();
        try {
            result = XmlUtil.loadFromFile(file);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error load the configuration from the file " + file, ex);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Application save(Application application) throws ConfigurationException {
        Application result = null;
        String file = getFile();
        try {
            XmlUtil.saveToFile(file, application);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error save the configuration from the file " + file, ex);
        }
        return result;
    }

    /**
     * Gets the configuration file name.
     *
     * @return the configuration file name.
     */
    private String getFile() {
        return System.getProperty(PROPERTY, DEFAULT_FILE);
    }
}
