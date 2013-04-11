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
package com.ajkaandrej.gwt.uc.form.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import java.util.List;

/**
 * The list cell.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ListCell extends AbstractCell<List<String>> {

    /**
     * The template.
     */
    interface Template extends SafeHtmlTemplates {

        /**
         * The begin.
         */
        @SafeHtmlTemplates.Template("<select multiple tabindex=\"-1\" size=\"{0}\">")
        SafeHtml begin(int size);

        /**
         * The deselected.
         */
        @SafeHtmlTemplates.Template("<option multiple value=\"{0}\">{0}</option>")
        SafeHtml deselected(String option);

        /**
         * The selected.
         */
        @SafeHtmlTemplates.Template("<option multiple value=\"{0}\" selected=\"selected\">{0}</option>")
        SafeHtml selected(String option);
    }
    /**
     * The template.
     */
    private static ListCell.Template template;
    /**
     * The size.
     */
    private int size;

    /**
     * The default constructor.
     */
    public ListCell() {
        this(3);
    }

    /**
     * The default constructor.
     *
     * @param size the size.
     */
    public ListCell(int size) {
        this.size = size;
        if (template == null) {
            template = GWT.create(ListCell.Template.class);
        }
    }

    /**
     * Gets the size.
     *
     * @return the size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size.
     *
     * @param size the size.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Context context, List<String> value, SafeHtmlBuilder sb) {
        sb.append(template.begin(size));
        if (value != null) {
            for (String option : value) {
                sb.append(template.deselected(option));
            }
        }
        sb.appendHtmlConstant("</select>");
    }
}
