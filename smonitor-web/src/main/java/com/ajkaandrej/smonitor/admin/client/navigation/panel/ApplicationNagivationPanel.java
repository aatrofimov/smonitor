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
package com.ajkaandrej.smonitor.admin.client.navigation.panel;

import com.ajkaandrej.smonitor.admin.client.navigation.factory.ObjectFactory;
import com.ajkaandrej.smonitor.admin.client.navigation.model.ApplicationTreeModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.ApplicationTreeViewModel;
import com.ajkaandrej.smonitor.admin.client.navigation.model.SingleSelectionApplicationTreeModel;
import com.ajkaandrej.smonitor.agent.rs.model.Application;
import com.ajkaandrej.smonitor.agent.rs.model.Host;
import com.ajkaandrej.smonitor.agent.rs.model.Server;
import com.ajkaandrej.smonitor.agent.rs.model.ServerContext;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Composite;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationNagivationPanel extends Composite {

    private CellTree tree;
    private ApplicationTreeViewModel model;

    public ApplicationNagivationPanel(SingleSelectionApplicationTreeModel selectionModel) {
        model = new ApplicationTreeViewModel(selectionModel);
        tree = new CellTree(model, null);
        initWidget(tree);
    }

    public void clear() {
        model.clear();
    }

    public void addServer(Server server) {
        if (server != null) {
            ServerContext context = server.getServerContext();
            Map<String, ApplicationTreeModel> data = model.getData();

            List<Host> hosts = server.getHosts();
            for (Host host : hosts) {
                List<Application> applications = host.getApplications();
                for (Application app : applications) {
                    ApplicationTreeModel tmp = data.get(app.getId());
                    if (tmp == null) {
                        tmp = ObjectFactory.create(app);
                        model.add(tmp);
                    }
                    tmp.instances.add(ObjectFactory.create(app, context));
                }
            }
        }
    }
}
