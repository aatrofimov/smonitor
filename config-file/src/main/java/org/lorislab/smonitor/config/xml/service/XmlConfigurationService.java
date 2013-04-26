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
package org.lorislab.smonitor.config.xml.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lorislab.smonitor.config.exception.ConfigurationException;
import org.lorislab.smonitor.config.factory.ObjectFactory;
import org.lorislab.smonitor.config.service.ConfigurationService;
import org.lorislab.smonitor.store.factory.ConfigurationStoreServiceFactory;
import org.lorislab.smonitor.store.model.Application;
import org.lorislab.smonitor.store.service.ConfigurationStoreService;

/**
 * The properties file configuration.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class XmlConfigurationService implements ConfigurationService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(XmlConfigurationService.class.getName());

    /**
     * The data cache.
     */
    private Map<Class,Object> data = new HashMap<Class, Object>();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T loadConfiguration(Class<T> clazz) throws ConfigurationException {
        T result = null;
        if (clazz != null) {
            result = (T) data.get(clazz);
            if (result == null) {             
                LOGGER.log(Level.WARNING, "The configuration model does not exist. Class {0}", clazz);                
                result = ObjectFactory.createInstance(clazz);
                data.put(clazz, data);
            }            
        } else {
            throw new ConfigurationException("The model class is null!");
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reloadConfigurations() throws ConfigurationException {
        // clean the cache.
        data.clear();
        
        // load the store service
        ConfigurationStoreService storeService = ConfigurationStoreServiceFactory.getService();
        Application application = storeService.load();
        
        // create the list of objects.
        List<Object> objects = ObjectFactory.createObjects(application);        
        
        // save the list of objects to the cache.
        if (objects != null) {
            for (Object object : objects) {
                data.put(object.getClass(), object);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T saveConfiguration(T model) throws ConfigurationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
