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
package org.lorislab.smonitor.connector.tomcat.listener;

import org.lorislab.smonitor.connector.tomcat.listener.TrackingContainerListener;
import org.lorislab.smonitor.connector.common.listener.TrackingSessionAttributeListener;
import org.lorislab.smonitor.connector.common.listener.TrackingSessionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.apache.catalina.Container;
import org.apache.catalina.ContainerEvent;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.Context;

/**
 * The tracking container listener.
 *
 * @author Andrej Petras
 */
public class TrackingContainerListener implements ContainerListener {

    private static final Logger LOGGER = Logger.getLogger(TrackingContainerListener.class.getName());

    public static TrackingContainerListener INSTANCE = new TrackingContainerListener();
    
    protected void add(Context context) {
        Object[] tmp = add(context.getApplicationLifecycleListeners(), TrackingSessionListener.INSTANCE);
        context.setApplicationLifecycleListeners(tmp);
    }

    protected void remove(Context context) {
        Object[] tmp = remove(context.getApplicationLifecycleListeners(), TrackingSessionListener.INSTANCE);
        context.setApplicationLifecycleListeners(tmp);
    }
    
    public void register(Context context) {
        add(context);
        
        Object[] tmp = add(context.getApplicationEventListeners(), TrackingSessionAttributeListener.INSTANCE);
        context.setApplicationEventListeners(tmp);     
        
        LOGGER.info("Registration listener for the application " + context.getName());
    }

    public void unregister(Context context) {
        remove(context);
        
        Object[] tmp = remove(context.getApplicationEventListeners(), TrackingSessionAttributeListener.INSTANCE);
        context.setApplicationEventListeners(tmp);
        
        LOGGER.info("Unregistration listener for the application " + context.getName());
    }

    @Override
    public void containerEvent(ContainerEvent event) {
        if (Container.ADD_CHILD_EVENT.equals(event.getType())) {
            Context context = (Context) event.getData();
            register(context);
        } else if (Container.REMOVE_CHILD_EVENT.equals(event.getType())) {
            Context context = (Context) event.getData();
            unregister(context);            
        }
    }
    
    protected static Object[] add(Object[] tmp, Object item) {
        List<Object> data;
        if (tmp != null) {
            data = new ArrayList<Object>(Arrays.asList(tmp));
        } else {
            data = new ArrayList<Object>();
        }
        data.add(item);        
        return data.toArray(new Object[data.size()]);
    }

    protected static Object[] remove(Object[] tmp, Object item) {
        if (tmp != null) {
            List<Object> data = new ArrayList<Object>(Arrays.asList(tmp));
            data.remove(item);
            return data.toArray(new Object[data.size()]);
        }
        return tmp;
    }    
}
