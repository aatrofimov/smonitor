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
package org.lorislab.smonitor.admin.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The please wait panel.
 * 
 * @author Andrej Petras
 */
public final class PleaseWaitPanel extends PopupPanel {
    
    public PleaseWaitPanel() {
        super(false);
        setWidget(uiBinder.createAndBindUi(this));
        setStyleName("pleaseWaitPanel");
		setGlassEnabled(true);		
    }
   
    interface MyUiBinder extends UiBinder<Widget, PleaseWaitPanel> {
    }
    private static PleaseWaitPanel.MyUiBinder uiBinder = GWT.create(PleaseWaitPanel.MyUiBinder.class);    
}
