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
package com.ajkaandrej.smonitor.config.file.service;

import com.ajkaandrej.smonitor.config.exception.ConfigurationException;
import com.ajkaandrej.smonitor.config.file.util.ConfigFileUtil;
import com.ajkaandrej.smonitor.config.model.Configuration;
import com.ajkaandrej.smonitor.config.service.AbstractConfigurationService;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The properties file configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class PropertiesFileConfigurationService extends AbstractConfigurationService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(PropertiesFileConfigurationService.class.getName());

    /**
     * {@inheritDoc}
     *
     * Load configuration from the properties files.
     */
    @Override
    protected void loadConfigurations() throws ConfigurationException {
        List<File> files = ConfigFileUtil.getModuleFiles();
        if (files != null && !files.isEmpty()) {
            for (File file : files) {
                String name = ConfigFileUtil.getModuleName(file);
                Properties props = ConfigFileUtil.loadProperties(file); 
                LOGGER.log(Level.INFO, "Load configuration for module {0} from properties file {1}", new Object[]{name, file.getName()});                
                updateConfiguration(name, (Map) props);                
            }
        } else {
            LOGGER.log(Level.INFO, "No configuration for the smonitor found.");
        }
    }

    /**
     * {@inheritDoc}
     *
     * Save the configuration model to the corresponding properties file.
     */
    @Override
    protected Map<String,String> saveConfiguration(String name, Map<String,String> data) throws ConfigurationException {        
        Properties props = ConfigFileUtil.saveConfiguration(name, data);
        return (Map) props;
    }
}
