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
package com.ajkaandrej.gwt.uc.form;

import com.ajkaandrej.gwt.uc.ConstantValues;
import com.ajkaandrej.gwt.uc.common.EntityComposite;
import com.ajkaandrej.gwt.uc.form.item.AbstractFormItem;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class EntityForm<T> extends EntityComposite<T> {

    private int column;
    private FlexTable layout;
    private List<AbstractFormItem> items;

    public EntityForm() {
        this(1);
    }

    public EntityForm(int column) {
        items = new ArrayList<AbstractFormItem>();
        this.column = column;
        layout = new FlexTable();
        ConstantValues.setWidth100(layout);
        initWidget(layout);
    }

    public void addCell(String label, AbstractFormItem<T, ?, ?> item) {
        items.add(item);

        int row = layout.getRowCount();
        if (row == 0) {
            layout.insertRow(row);
        } else {
            row = row - 1;
        }

        int col = layout.getCellCount(row);
        if (col >= (column * 2)) {
            col = 0;
            row++;
        }
        layout.setHTML(row, col, label);
        layout.getFlexCellFormatter().setHorizontalAlignment(row, col, HasHorizontalAlignment.ALIGN_RIGHT);
        col++;
        layout.setHTML(row, col, render(row,col,data));
    }

    public void open(T data) {
        this.data = data;
        updateView();
    }

    private void updateView() {
        for (int row = 0; row < layout.getRowCount(); row++) {
            for (int col = 1; col < layout.getCellCount(row); col = col + 2) {
                layout.setHTML(row, col, render(row, col, data));
            }
        }
    }

    private SafeHtml render(int row, int col, T object) {
        int index = (row * column) + col/2;
        SafeHtmlBuilder sb = new SafeHtmlBuilder();
        Cell.Context context = new Cell.Context(col, row, object);
        items.get(index).render(context, object, sb);
        return sb.toSafeHtml();
    }

    public void reset() {
        this.data = null;
        updateView();
    }
}
