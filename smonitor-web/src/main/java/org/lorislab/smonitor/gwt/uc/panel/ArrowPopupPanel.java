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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.Iterator;

/**
 *
 * @author Andrej Petras
 */
public class ArrowPopupPanel extends Composite implements HasWidgets{
    
    protected final AbsolutePanel arrow = new AbsolutePanel();
    
    @UiField
    PopupPanel popupPanel;
    
    public ArrowPopupPanel() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    public void open(int left, int top) {
        popupPanel.setPopupPosition(left-60, top);
        if (!popupPanel.isShowing()) {
            popupPanel.show();
        }
    }
    
    public void close() {
        popupPanel.hide();
    }

    @Override
    public void add(Widget w) {
        popupPanel.add(w);
    }

    @Override
    public void clear() {
        popupPanel.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return popupPanel.iterator();
    }

    @Override
    public boolean remove(Widget w) {
        return popupPanel.remove(w);
    }
    
    interface MyUiBinder extends UiBinder<Widget, ArrowPopupPanel> {
    }
    private static ArrowPopupPanel.MyUiBinder uiBinder = GWT.create(ArrowPopupPanel.MyUiBinder.class);    
}
