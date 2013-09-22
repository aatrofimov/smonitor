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
package org.lorislab.smonitor.connector.jboss7.listener;

import org.lorislab.smonitor.connector.tomcat.listener.TrackingContainerListener;
import org.lorislab.smonitor.connector.common.listener.TrackingSessionListener;
import org.apache.catalina.Context;

/**
 * The tracking container listener.
 *
 * @author Andrej Petras
 */
public class TrackingJBoss7ContainerListener extends TrackingContainerListener {

    public static TrackingJBoss7ContainerListener LISTENER_INSTANCE = new TrackingJBoss7ContainerListener();

    @Override
    protected void add(Context context) {
        Object[] tmp = add(context.getApplicationSessionLifecycleListeners(), TrackingSessionListener.INSTANCE);
        context.setApplicationSessionLifecycleListeners(tmp);
    }

    @Override
    protected void remove(Context context) {
        Object[] tmp = remove(context.getApplicationSessionLifecycleListeners(), TrackingSessionListener.INSTANCE);
        context.setApplicationSessionLifecycleListeners(tmp);
    }

}
