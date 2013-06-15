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
package org.lorislab.smonitor.gwt.uc.form;

import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.gwt.uc.common.EntityComposite;
import org.lorislab.smonitor.gwt.uc.form.item.AbstractFormItem;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity form.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 * @param <T> the entity.
 */
public class EntityForm<T> extends EntityComposite<T> {

    /**
     * The number of column.
     */
    private int column;
    /**
     * The layout.
     */
    private FlexTable layout;
    /**
     * The list of form items.
     */
    private List<AbstractFormItem> items;
    /**
     * The first row.
     */
    private int firstRow;
    /**
     * The header.
     */
    private HeaderForm header;

    /**
     * The default constructor.
     */
    public EntityForm() {
        this(1);
    }

    /**
     * The default constructor.
     *
     * @param column the number of column.
     */
    public EntityForm(int column) {
        items = new ArrayList<AbstractFormItem>();
        firstRow = 0;
        this.column = column;
        layout = new FlexTable();
        initWidget(layout);
    }

    /**
     * Gets the header.
     *
     * @return the header.
     */
    public HeaderForm getHeader() {
        return header;
    }

    /**
     * Gets the header widget.
     *
     * @return the header widget.
     */
    public Widget getHeaderWidget() {
        if (header != null) {
            return layout.getWidget(0, 0);
        }
        return null;
    }

    /**
     * Sets the header style.
     *
     * @param style the header style.
     */
    public void setHeaderStyleName(String style) {
        FlexTable.FlexCellFormatter cf = layout.getFlexCellFormatter();
        cf.setStyleName(0, 0, style);
    }

    /**
     * Adds the header.
     *
     * @param header the header.
     * @param aligment the alignment.
     */
    public void addHeader(HeaderForm header, HasHorizontalAlignment.HorizontalAlignmentConstant alignment) {
        Widget w = null;
        if (this.header != null) {
            w = layout.getWidget(0, 0);
            if (header != null) {
                setHeader(w, header, alignment);
            } else {
                layout.removeRow(0);
                firstRow = 0;
            }
        } else if (header != null) {
            layout.insertRow(0);
            setHeader(w, header, alignment);
            firstRow = 1;
        }
    }

    /**
     * Sets the header.
     *
     * @param widget the widget.
     * @param header the header form.
     * @param alignment the alignment.
     */
    private void setHeader(Widget widget, HeaderForm header, HasHorizontalAlignment.HorizontalAlignmentConstant alignment) {
        this.header = header;
        layout.setWidget(0, 0, header.getWidget(data, widget));
        FlexTable.FlexCellFormatter cf = layout.getFlexCellFormatter();
        cf.setColSpan(0, 0, column * 2);
        cf.setHorizontalAlignment(0, 0, alignment);
    }

    /**
     * Adds the cell.
     *
     * @param label the label.
     * @param item the form item.
     */
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

    /**
     * Opens the data.
     *
     * @param data the data.
     */
    public void open(T data) {
        this.data = data;
        updateView();
    }

    /**
     * Updates the view.
     */
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

    /**
     * Renders the object at the row and col position.
     *
     * @param row the row.
     * @param col the col.
     * @param object the object.
     * @return the safe HTML corresponding to the object at row and col
     * position.
     */
    private SafeHtml render(int row, int col, T object) {
        int index = ((row - firstRow) * column) + col / 2;
        SafeHtmlBuilder sb = new SafeHtmlBuilder();
        Cell.Context context = new Cell.Context(col, row, object);
        items.get(index).render(context, object, sb);
        return sb.toSafeHtml();
    }

    /**
     * Resets the form.
     */
    public void reset() {
        this.data = null;
        updateView();
    }
}
