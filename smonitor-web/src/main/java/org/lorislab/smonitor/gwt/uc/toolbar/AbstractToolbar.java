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
package org.lorislab.smonitor.gwt.uc.toolbar;

import com.google.gwt.user.client.ui.PopupPanel;

/**
 * The abstract tool bar.
 *
 * @author Andrej Petras
 * @param <T> the model.
 * @param <H> the handler.
 */
public class AbstractToolbar<T, H> extends PopupPanel {

    /**
     * The data.
     */
    private T data;
    /**
     * The handler.
     */
    private H handler;

    /**
     * Gets the data.
     *
     * @return the data.
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the handler.
     *
     * @return the handler.
     */
    public H getHandler() {
        return handler;
    }

    /**
     * Sets the handler.
     *
     * @param handler the handler.
     */
    public void setHandler(H handler) {
        this.handler = handler;
    }

    /**
     * Returns
     * <code>true</code> for the valid event.
     *
     * @return <code>true</code> for the valid event.
     */
    public boolean isEvent() {
        return handler != null && data != null;
    }

    /**
     * Opens the tool-bar.
     *
     * @param left the left position.
     * @param top the top position.
     * @param height the tool bar height.
     * @param data the data.
     */
    public void open(int left, int top, int height, T data) {
        this.data = data;
        this.setHeight(height + "px");
        this.setPopupPosition(left, top);
        this.show();
    }

    /**
     * Close the tool-bar.
     */
    public void close() {
        data = null;
        this.hide();
    }
}
