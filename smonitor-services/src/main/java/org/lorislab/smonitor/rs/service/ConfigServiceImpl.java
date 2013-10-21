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
package org.lorislab.smonitor.rs.service;

import java.io.InputStream;
import java.util.Properties;
import org.lorislab.smonitor.base.exception.ServiceException;

/**
 *
 * @author Andrej Petras
 */
public final class ConfigServiceImpl implements ConfigService {

    /**
     * The MAVEN property file.
     */
    private static final String PROPERTY_FILE = "META-INF/maven/org.lorislab.smonitor/smonitor-services/pom.properties";
    /**
     * The version key.
     */
    private static final String KEY_VERSION = "version";

    //TODO: load from manifest
    @Override
    public String getVersion() throws Exception {
        Properties properties = new Properties();
        try {            
            InputStream input = ConfigServiceImpl.class.getClassLoader().getResourceAsStream(PROPERTY_FILE);
            try {
                properties.load(input);
            } finally {
                if (input != null) {
                    input.close();
                }
            }
        } catch (Exception ex) {
            throw new ServiceException(null, "Error load version properties", ex);
        }
        return properties.getProperty(KEY_VERSION);
    }
}
