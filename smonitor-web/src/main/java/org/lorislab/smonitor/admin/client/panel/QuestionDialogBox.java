package org.lorislab.smonitor.admin.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import org.lorislab.smonitor.admin.client.handler.DialogEventHandler;

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

/**
 *
 * @author Andrej Petras
 */
public class QuestionDialogBox<T> extends PopupPanel {
    
    @UiField
    Label title;
    
    @UiField
    Label question;
    
    @UiField
    Button btnOk;
    
    @UiField
    Button btnCancel;
    
    private T data;
    
    private DialogEventHandler<T> okHandler;
    
    private DialogEventHandler<T> cancelHandler;
    
    public QuestionDialogBox() {
        setWidget(binder.createAndBindUi(this));
        setStyleName("question-box-main");
        setGlassEnabled(true);
        
        btnCancel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (cancelHandler != null) {
                    cancelHandler.event(data);
                } else {
                    hide();
                }
            }
        }); 
        
        btnOk.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (okHandler != null) {
                    okHandler.event(data);
                } else {
                    hide();
                }
            }
        });        
    }

    public void setOkHandler(DialogEventHandler<T> okHandler) {
        this.okHandler = okHandler;
    }

    public void setCancelHandler(DialogEventHandler<T> cancelHandler) {
        this.cancelHandler = cancelHandler;
    }
        
    public void open(T data, String title, String question) {
        this.data = data;
        this.question.setText(question);
        this.title.setText(title);        
        this.center();
        this.show();
    }
        
    private static final QuestionDialogBox.Binder binder = GWT.create(QuestionDialogBox.Binder.class);

    interface Binder extends UiBinder<Widget, QuestionDialogBox> {
    }    
}
