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
package com.ajkaandrej.smonitor.admin.client.panel;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class FooterPanel extends Composite {
    
    private Label versionLabel;
    
    public FooterPanel() {
        
        HTML label = new HTML("jBoss session monitor created by <a href='https://github.com/andrejpetras'>Andrej Petras</a>. Version: ");
        versionLabel = new Label();        
        HorizontalPanel hp = new HorizontalPanel();
        hp.add(label);
        hp.add(versionLabel);
        initWidget(hp);
    }
    
    public void setVersion(String version) {
        versionLabel.setText(version);
    }
}
