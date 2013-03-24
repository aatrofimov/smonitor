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

import com.ajkaandrej.smonitor.config.factory.ConfigurationServiceFactory;
import com.ajkaandrej.smonitor.config.model.MonitorConfig;
import com.ajkaandrej.smonitor.config.service.ConfigurationService;
import com.ajkaandrej.smonitor.rs.exception.MonitorServiceException;
import com.ajkaandrej.smonitor.rs.model.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class MonitorServiceImpl implements MonitorService {

    @Override
    public List<Connection> getServerConnections() throws MonitorServiceException {
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
    public void realoadConfiguration() throws MonitorServiceException {
        try {
            ConfigurationService service = ConfigurationServiceFactory.getService();
            service.reloadConfigurations();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
}
