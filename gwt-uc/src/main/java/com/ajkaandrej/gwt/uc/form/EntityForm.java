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
import com.google.gwt.user.client.ui.Widget;
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
    private int firstRow;
    private HeaderForm header;

    public EntityForm() {
        this(1);
    }

    public EntityForm(int column) {
        items = new ArrayList<AbstractFormItem>();
        firstRow = 0;
        this.column = column;
        layout = new FlexTable();
        ConstantValues.setWidth100(layout);
        initWidget(layout);
    }

    public HeaderForm getHeader() {
        return header;
    }

    public Widget getHeaderWidget() {
        if (header != null) {
            return layout.getWidget(0, 0);
        }
        return null;
    }

    public void setHeaderStyleName(String style) {
        FlexTable.FlexCellFormatter cf = layout.getFlexCellFormatter();
        cf.setStyleName(0, 0, style);
    }
    
    public void addHeader(HeaderForm header, HasHorizontalAlignment.HorizontalAlignmentConstant aligment) {
        Widget w = null;
        if (this.header != null) {
            w = layout.getWidget(0, 0);
            if (header != null) {
                setHeader(w, header, aligment);
            } else {
                layout.removeRow(0);
                firstRow = 0;
            }
        } else if (header != null) {
            layout.insertRow(0);
            setHeader(w, header, aligment);
            firstRow = 1;
        }
    }

    private void setHeader(Widget widget, HeaderForm header, HasHorizontalAlignment.HorizontalAlignmentConstant aligment) {
        this.header = header;
        layout.setWidget(0, 0, header.getWidget(data, widget));
        FlexTable.FlexCellFormatter cf = layout.getFlexCellFormatter();
        cf.setColSpan(0, 0, column * 2);
        cf.setHorizontalAlignment(0, 0, aligment);
    }

    public void addCell(String label, AbstractFormItem<T, ?, ?> item) {
        items.add(item);

        int row = layout.getRowCount();
        if (row == firstRow) {
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
        layout.setHTML(row, col, render(row, col, data));
    }

    public void open(T data) {
        this.data = data;
        updateView();
    }

    private void updateView() {
        if (header != null) {
            Widget w = layout.getWidget(0, 0);
            layout.setWidget(0, 0, header.getWidget(data, w));
        }
        for (int row = firstRow; row < layout.getRowCount(); row++) {
            for (int col = 1; col < layout.getCellCount(row); col = col + 2) {
                layout.setHTML(row, col, render(row, col, data));
            }
        }
    }

    private SafeHtml render(int row, int col, T object) {
        int index = ((row - firstRow) * column) + col / 2;
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
