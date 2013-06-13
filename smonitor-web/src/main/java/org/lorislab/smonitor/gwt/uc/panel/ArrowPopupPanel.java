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

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 *
 * @author Andrej Petras
 */
public class ArrowPopupPanel extends PopupPanel {

    public ArrowPopupPanel() {
        setStyleName("arrow-popup-right");

        HTML h = new HTML("TEXT<br/>TEXT<br/>TEXT<br/>TEXT<br/>");
        add(h);
    }

    public void open(int left, int top) {
        this.setPopupPosition(left - 40, top - 27);
        this.show();
    }

    public void close() {
        this.hide();
    }

}
