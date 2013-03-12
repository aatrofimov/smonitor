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

import com.ajkaandrej.smonitor.agent.rs.exception.ServiceException;
import com.ajkaandrej.smonitor.agent.rs.model.AttributeDetails;

/**
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionAttributeServiceImpl implements SessionAttributeService {

    @Override
    public AttributeDetails getAttribute(String session, String name) throws ServiceException {
        return null;
    }

    @Override
    public AttributeDetails updateAttribute(String session, AttributeDetails attribute) throws ServiceException {
        return null;
    }

    @Override
    public void deleteAttribute(String session, String name) throws ServiceException {
        
    }

}
