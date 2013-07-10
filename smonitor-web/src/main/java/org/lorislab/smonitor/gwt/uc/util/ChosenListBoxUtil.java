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
package org.lorislab.smonitor.gwt.uc.util;

import com.watopi.chosen.client.gwt.ChosenListBox;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The chosen list box utility class.
 *
 * @author Andrej Petras
 */
public final class ChosenListBoxUtil {

    /**
     * The default constructor.
     */
    private ChosenListBoxUtil() {
        // empty constructor
    }

    /**
     * Clear chosen list box.
     *
     * @param listBox the chosen list box.
     */
    public static void clearSelected(ChosenListBox listBox) {
        listBox.setSelectedIndex(-1);
        listBox.update();

    }

    /**
     * Gets the list of selected values.
     *
     * @param list the chosen list box.
     * @return the set of selected values.
     */
    public static Set<String> getSelectedValues(ChosenListBox list) {
        Set<String> result = new HashSet<String>();
        if (list != null) {
            String[] values = list.getValues();
            if (values != null && values.length > 0) {
                result.addAll(Arrays.asList(values));
            } else {
                int size = list.getItemCount();
                String item;
                for (int i = 0; i < size; i++) {
                    item = list.getValue(i);
                    if (item != null) {
                        result.add(item);
                    }
                }
            }
        }
        
        return result;
    }
    
    /**
     * Removes the selected items from the list.
     * @param list the chosen list box.
     * @param items the items to be removed from selection.
     */
    public static boolean removeSelectionItems(ChosenListBox list, Set<String> items) {
        boolean result = false;
        if (list != null) {
            String[] values = list.getValues();
            if (values != null && values.length > 0) {
                Set<String> tmp = new HashSet<String>(Arrays.asList(values));
                int size = tmp.size();
                tmp.removeAll(items);
                if (size != tmp.size()) {
                    list.setSelectedIndex(-1);
                    list.setSelectedValue(tmp.toArray(new String[tmp.size()]));       
                    list.update();
                    result = true;
                }
            }
        }
        return result;
    }
}
