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
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 *
 * @author Andrej Petras
 */
public class TrackingSessionAttributeListener implements HttpSessionAttributeListener {

    private static final Logger LOGGER = Logger.getLogger(TrackingSessionAttributeListener.class.getName());
    public static TrackingSessionAttributeListener INSTANCE = new TrackingSessionAttributeListener();

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        LOGGER.info("ADD " + event.getName() + " : " + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        LOGGER.info("REMOVE " + event.getName() + " : " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        LOGGER.info("REPLACE " + event.getName() + " : " + event.getValue() + " => " + event.getSession().getAttribute(event.getName()));
    }
}
