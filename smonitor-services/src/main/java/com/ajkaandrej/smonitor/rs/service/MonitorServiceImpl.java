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
package com.ajkaandrej.smonitor.rs.service;

import com.ajkaandrej.smonitor.agent.rs.exception.ServiceException;
import com.ajkaandrej.smonitor.config.factory.ConfigurationServiceFactory;
import com.ajkaandrej.smonitor.config.model.MonitorConfig;
import com.ajkaandrej.smonitor.config.service.ConfigurationService;
import com.ajkaandrej.smonitor.rs.model.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class MonitorServiceImpl implements MonitorService {

    /** The MAVEN property file. */
    private static final String PROPERTY_FILE = "META-INF/maven/com.ajkaandrej.smonitor/smonitor-services/pom.properties";
    
    /** The version key. */
    private static final String KEY_VERSION = "version";
    
    @Override
    public String getVersion() throws ServiceException {
        Properties properties = new Properties();
        InputStream input = MonitorServiceImpl.class.getClassLoader().getResourceAsStream(PROPERTY_FILE);
        try {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        return properties.getProperty(KEY_VERSION);
    }

    
    @Override
    public List<Connection> getServerConnections() throws ServiceException {
        List<Connection> result = new ArrayList<Connection>();
        try {
            Connection con = new Connection();
            con.setName("localhost");
            con.setUrl(null);
            result.add(con);
            
            ConfigurationService service = ConfigurationServiceFactory.getService();
            MonitorConfig config = service.loadConfiguration(MonitorConfig.class);
            List<String> tmp = config.getConnections();
            if (tmp != null) {
                for (String item : tmp) {
                    con = new Connection();
                    con.setName(item);
                    con.setUrl(item);
                    result.add(con);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void realoadConfiguration() throws ServiceException {
        try {
            ConfigurationService service = ConfigurationServiceFactory.getService();
            service.reloadConfigurations();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
}
