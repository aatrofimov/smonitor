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
package org.lorislab.smonitor.gwt.uc.dialogbox;

import org.lorislab.smonitor.admin.client.handler.DialogEventHandler;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 *
 * @author Andrej Petras
 */
public abstract class EntityDialogBox<T> extends PopupPanel {
    
    public enum Mode {
        
        CREATE,
        
        UPDATE,
        
        VIEW;
    }
          
    private T data;
    
    private DialogEventHandler<T> updateHandler;
    
    private DialogEventHandler<T> createHandler;
    
    private DialogEventHandler<T> cancelHandler; 

    public EntityDialogBox() {
        super();
        setGlassEnabled(true);
    }
    
    public void open(T data, Mode mode) {
        this.data = data;
        openView(mode);
        getEntityData(data);
    }
        
    public void setCancelHandler(DialogEventHandler<T> cancelHandler) {
        this.cancelHandler = cancelHandler;
    }

    public void setCreateHandler(DialogEventHandler<T> createHandler) {
        this.createHandler = createHandler;
    }

    public void setUpdateHandler(DialogEventHandler<T> updateHandler) {
        this.updateHandler = updateHandler;
    }
    
    protected abstract void openView(Mode mode);
    
    protected abstract void getEntityData(T item);
    
    protected abstract void setEntityData(T item);
    
    public void create() {
        setEntityData(data);        
        if (createHandler != null) {            
            createHandler.event(data);
        } else {
            close();
        }
    }
    
    public void update() {
        setEntityData(data);
        if (updateHandler != null) {            
            updateHandler.event(data);
        } else {
            close();
        }        
    }
    
    public void cancel() {
        if (cancelHandler != null) {
            cancelHandler.event(data);
        } else {
            close();
        }          
    }
    
    public void close() {
        this.hide();
    }
}
