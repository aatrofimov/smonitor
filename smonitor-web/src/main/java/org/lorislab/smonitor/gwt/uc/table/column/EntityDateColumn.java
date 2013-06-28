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
package org.lorislab.smonitor.gwt.uc.table.column;

import org.lorislab.smonitor.gwt.uc.ConstantValues;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;

/**
 * The entity date column.
 *
 * @author Andrej Petras
 */
public abstract class EntityDateColumn<T> extends AbstractEntityColumn<T, Date, Date> {

    /**
     * The default constructor.
     */
    public EntityDateColumn() {
        this(ConstantValues.DATE_FORMAT);
    }

    /**
     * The default constructor.
     *
     * @param format the date format.
     */
    public EntityDateColumn(DateTimeFormat format) {
        super(new DateCell(format));
    }
}
