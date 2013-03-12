/*
 * Copyright 2013 Andrej_Petras.
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
package com.ajkaandrej.smonitor.agent.rs.service;

import com.ajkaandrej.smonitor.agent.mapper.ObjectMapper;
import com.ajkaandrej.smonitor.connector.factory.ConnectorServiceFactory;
import com.ajkaandrej.smonitor.agent.rs.exception.ServiceException;
import com.ajkaandrej.smonitor.agent.rs.model.Application;
import com.ajkaandrej.smonitor.agent.rs.model.ApplicationDetails;
import com.ajkaandrej.smonitor.connector.service.ConnectorService;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.TypeToken;

/**
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationServiceImpl implements ApplicationService {

    @Override
    public List<Application> getApplications() throws ServiceException {
        ConnectorService service = ConnectorServiceFactory.getService();
        Type listType = new TypeToken<List<Application>>() {}.getType();
        List<Application> result = ObjectMapper.getInstance().map(service.getApplications(), listType);
        return result;
    }

    @Override
    public ApplicationDetails getApplication(String name) throws ServiceException {
        ConnectorService service = ConnectorServiceFactory.getService();
        ApplicationDetails result = ObjectMapper.getInstance().map(service.getApplicationDetails(name), ApplicationDetails.class);
        return result;
    }

}
