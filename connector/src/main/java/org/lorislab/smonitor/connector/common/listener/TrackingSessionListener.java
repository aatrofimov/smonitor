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
package org.lorislab.smonitor.connector.common.listener;

import java.util.logging.Logger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * The tracking session listener.
 * 
 * @author Andrej Petras
 */
public class TrackingSessionListener implements HttpSessionListener {

    private static final Logger LOGGER = Logger.getLogger(TrackingSessionListener.class.getName());

    public static TrackingSessionListener INSTANCE = new TrackingSessionListener() ;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.info("Create session " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.info("Destroy session " + se.getSession().getId());
    }
   
}
