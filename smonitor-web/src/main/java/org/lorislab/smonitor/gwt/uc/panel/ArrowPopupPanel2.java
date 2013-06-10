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
package org.lorislab.smonitor.gwt.uc.panel;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 * @author Andrej Petras
 */
public class ArrowPopupPanel2 extends PopupPanel {
    
    protected final AbsolutePanel arrow = new AbsolutePanel();
    
    public ArrowPopupPanel2() {
        super(true);
        this.setSize("200px", "200px");
        this.removeStyleName("gwt-PopupPanel");
        arrow.setStyleName("popup-panel-arrow-left");
        
        DockLayoutPanel layout = new DockLayoutPanel(Style.Unit.PX);
        layout.setSize("100%", "100%");
        setWidget(layout);
        
        
        VerticalPanel vp = new VerticalPanel();
        vp.setSize("100%", "100%");
        vp.add(arrow);
        vp.setCellVerticalAlignment(arrow, HasVerticalAlignment.ALIGN_MIDDLE);
        layout.addWest(vp, 11);
        
        
        Panel content = new HTMLPanel("TEXT");
        content.setStyleName("gwt-PopupPanel");
        content.setSize("178px", "94%");
        layout.add(content);
    }
}
