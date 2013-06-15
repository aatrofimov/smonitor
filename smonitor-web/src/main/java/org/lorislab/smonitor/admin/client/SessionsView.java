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
package org.lorislab.smonitor.admin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.watopi.chosen.client.ChosenOptions;
import com.watopi.chosen.client.gwt.ChosenListBox;
import com.watopi.chosen.client.resources.ChozenCss;
import com.watopi.chosen.client.resources.Resources;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.lorislab.smonitor.admin.client.model.AgentWrapper;
import org.lorislab.smonitor.admin.client.panel.SessionGridPanel;
import org.lorislab.smonitor.gwt.uc.page.ViewPage;
import org.lorislab.smonitor.rs.model.ServerApplication;

/**
 *
 * @author Andrej Petras
 */
public class SessionsView extends ViewPage {

    @UiField(provided = true)
    ChosenListBox agentsList;    

    @UiField(provided = true)
    ChosenListBox appList; 
    
    @UiField
    SessionGridPanel sessionPanel;
    
    private AgentController agentController;
    
    public SessionsView(AgentController agentController) {
        this.agentController = agentController;
        ChosenOptions options = new ChosenOptions();
        options.setResources(GWT.<MyResources>create(MyResources.class));        
        agentsList = new ChosenListBox(true, options);
        appList = new ChosenListBox(true, options);
        
        initWidget(uiBinder.createAndBindUi(this));
        
        appList.setPlaceholderText("Choose ...");
        agentsList.setPlaceholderText("Choose ...");
        
    }
    
    @Override
    public void openPage() {
        agentsList.clear();
        appList.clear();
        
        List<AgentWrapper> data = agentController.getAgents();
        if (data != null) {
            Set<String> tmp = new HashSet<String>();
            for (AgentWrapper w : data) {
                if (w.server != null) {
                    agentsList.addItem(w.agent.getName(), w.agent.getGuid());
                    List<ServerApplication> apps = w.server.getApplications();
                    if (apps != null) {
                        for (ServerApplication a : apps) {
                             if (!tmp.contains(a.getId())) {
                                 appList.addItem(a.getName(), a.getId());
                                 tmp.add(a.getId());
                             }
                        }
                    }
                }
            }
        }
        

    }

    @Override
    public void closePage() {
        
    }

    @Override
    public String getPageTitle() {
        return "Sessions";
    }
    
    
    interface MyUiBinder extends UiBinder<Widget, SessionsView> { }
    private static SessionsView.MyUiBinder uiBinder = GWT.create(SessionsView.MyUiBinder.class);
 
    public interface MyResources extends Resources {
         
        @ClientBundle.Source("chozen.css")
        @Override
        ChozenCss css();
    }    
}
