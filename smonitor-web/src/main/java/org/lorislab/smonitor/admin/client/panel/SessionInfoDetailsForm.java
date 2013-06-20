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
package org.lorislab.smonitor.admin.client.panel;

import java.util.List;
import org.lorislab.smonitor.gwt.uc.form.EntityForm;
import org.lorislab.smonitor.gwt.uc.form.item.BooleanFormItem;
import org.lorislab.smonitor.gwt.uc.form.item.DoubleFormItem;
import org.lorislab.smonitor.gwt.uc.form.item.ListFormItem;
import org.lorislab.smonitor.gwt.uc.form.item.TextFormItem;
import org.lorislab.smonitor.rs.model.SessionInfoDetails;

/**
 *
 * @author Andrej Petras
 */
public class SessionInfoDetailsForm extends EntityForm<SessionInfoDetails>{
    
    public SessionInfoDetailsForm() {
        super(4);
        
        addCell("Id:", new TextFormItem<SessionInfoDetails>() {
            @Override
            public String getObject(SessionInfoDetails object) {
                return object.getSession().getId();
            }
        });

        addCell("Info:", new TextFormItem<SessionInfoDetails>() {
            @Override
            public String getObject(SessionInfoDetails object) {
                return object.getInfo();
            }
        });
        
        addCell("New:", new BooleanFormItem<SessionInfoDetails>() {
            @Override
            public Boolean getObject(SessionInfoDetails object) {
                return object.isNewSession();
            }
        });
        
        addCell("Ser. size:", new DoubleFormItem<SessionInfoDetails>() {
            @Override
            public Double getObject(SessionInfoDetails object) {
                return object.getSizeSerializable();
            }
        }); 
        
        addCell("Size:", new DoubleFormItem<SessionInfoDetails>() {
            @Override
            public Double getObject(SessionInfoDetails object) {
                return object.getSize();
            }
        }); 
        
        addCell("Roles:", new ListFormItem<SessionInfoDetails>() {
            @Override
            public List<String> getObject(SessionInfoDetails object) {
                return object.getRoles();
            }
        });
        
    }
}
