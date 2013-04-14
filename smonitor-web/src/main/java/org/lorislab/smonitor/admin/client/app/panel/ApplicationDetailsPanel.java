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
package org.lorislab.smonitor.admin.client.app.panel;

import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.admin.client.app.model.ApplicationDetailsModel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationDetailsPanel extends Composite {

    private VerticalPanel vPanel;
    
    public ApplicationDetailsPanel() {        
        vPanel = new VerticalPanel();
        vPanel.setSpacing(5);
        ConstantValues.setWidth100(vPanel);
        initWidget(vPanel);
    }

    public void reset() {
        vPanel.clear();
    }

    public void add(ApplicationDetailsModel model) {
        ApplicationDetailsPanelItem item = new ApplicationDetailsPanelItem();
        item.open(model);
        vPanel.add(item);
    }

}
