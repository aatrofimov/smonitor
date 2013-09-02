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
package org.lorislab.smonitor.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;

/**
 * The administrator entry point.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class Admin implements EntryPoint {

    private MainLayout mainLayout;

    @Override
    public void onModuleLoad() {

        // Rest client configuration
        RestClient.setJacksonMarshallingActive(true);
        RestClient.setApplicationRoot("rs");

        // Disable right click
        RootLayoutPanel.get().addDomHandler(new ContextMenuHandler() {
            @Override
            public void onContextMenu(ContextMenuEvent event) {
                event.preventDefault();
                event.stopPropagation();
            }
        }, ContextMenuEvent.getType());

        // Create main content
        mainLayout = new MainLayout();
        RootLayoutPanel.get().add(mainLayout);
        
        // init
        mainLayout.init();
    }

}
