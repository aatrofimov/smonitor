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
 * The entity dialog box.
 *
 * @author Andrej Petras
 * @param <T> the entity.
 */
public abstract class EntityDialogBox<T> extends PopupPanel {

    /**
     * The mode.
     */
    public enum Mode {

        /**
         * The create mode.
         */
        CREATE,
        /**
         * The update mode.
         */
        UPDATE,
        /**
         * The view mode.
         */
        VIEW;
    }
    /**
     * The data.
     */
    private T data;
    /**
     * The update dialog handler.
     */
    private DialogEventHandler<T> updateHandler;
    /**
     * The create dialog handler.
     */
    private DialogEventHandler<T> createHandler;
    /**
     * The cancel dialog handler.
     */
    private DialogEventHandler<T> cancelHandler;

    /**
     * The default constructor.
     */
    public EntityDialogBox() {
        super();
        setGlassEnabled(true);
    }

    /**
     * Opens the data.
     *
     * @param data the data.
     * @param mode the mode.
     */
    public void open(T data, Mode mode) {
        this.data = data;
        openView(mode);
        getEntityData(data);
    }

    /**
     * Sets the cancel handler.
     *
     * @param cancelHandler the cancel handler.
     */
    public void setCancelHandler(DialogEventHandler<T> cancelHandler) {
        this.cancelHandler = cancelHandler;
    }

    /**
     * Sets the create handler.
     *
     * @param createHandler the create handler.
     */
    public void setCreateHandler(DialogEventHandler<T> createHandler) {
        this.createHandler = createHandler;
    }

    /**
     * Sets the update handler.
     *
     * @param updateHandler the update handler.
     */
    public void setUpdateHandler(DialogEventHandler<T> updateHandler) {
        this.updateHandler = updateHandler;
    }

    /**
     * Opens the mode.
     *
     * @param mode the mode.
     */
    protected abstract void openView(Mode mode);

    /**
     * Gets the entity data.
     *
     * @param item the entity.
     */
    protected abstract void getEntityData(T item);

    /**
     * Sets the entity data.
     *
     * @param item the entity.
     */
    protected abstract void setEntityData(T item);

    /**
     * Create entity.
     */
    public void create() {
        setEntityData(data);
        if (createHandler != null) {
            createHandler.event(data);
        } else {
            close();
        }
    }

    /**
     * Update entity.
     */
    public void update() {
        setEntityData(data);
        if (updateHandler != null) {
            updateHandler.event(data);
        } else {
            close();
        }
    }

    /**
     * Cancel entity.
     */
    public void cancel() {
        if (cancelHandler != null) {
            cancelHandler.event(data);
        } else {
            close();
        }
    }

    /**
     * Close the dialog box.
     */
    public void close() {
        this.hide();
    }
}
