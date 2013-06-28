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
package org.lorislab.smonitor.gwt.uc.form.property;

import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;

/**
 * The date text property.
 *
 * @author Andrej Petras
 * @param <T> the object.
 */
public abstract class DateTextBoxProperty<T> extends AbstractTextBoxProperty<T, Date> {

    /**
     * The date time format.
     */
    private DateTimeFormat format;

    /**
     * The default constructor.
     *
     * @param format the date time format.
     */
    public DateTextBoxProperty(DateTimeFormat format) {
        super();
        this.format = format;
    }

    /**
     * The default constructor.
     *
     * @param format the date time format.
     * @param readOnly the read only flag.
     */
    public DateTextBoxProperty(DateTimeFormat format, boolean readonly) {
        super(readonly);
        this.format = format;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidgetValue(Date value) {
        widget.setText(format.format(value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getWidgetValue() {
        return format.parse(widget.getText());
    }
}
