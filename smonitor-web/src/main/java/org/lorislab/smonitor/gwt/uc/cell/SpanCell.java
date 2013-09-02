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
package org.lorislab.smonitor.gwt.uc.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 *
 * @author Andrej Petras
 */
public final class SpanCell extends AbstractCell<String> {

    /**
     * The template.
     */
    private static SpanCell.Template template;

    public SpanCell() {
        if (template == null) {
            template = GWT.create(SpanCell.Template.class);
        }
    }

    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        sb.append(template.span(value));
    }
    
    /**
     * The template.
     */
    interface Template extends SafeHtmlTemplates {

        /**
         * The begin.
         */
        @SafeHtmlTemplates.Template("<span class=\"{0}\">")
        SafeHtml span(String styleName);
    }    
}
