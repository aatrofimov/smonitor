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

import com.google.gwt.user.client.ui.CheckBox;
import java.util.Date;
import java.util.List;
import org.lorislab.smonitor.gwt.uc.ConstantValues;
import org.lorislab.smonitor.gwt.uc.form.BooleanCheckBoxProperty;
import org.lorislab.smonitor.gwt.uc.form.DateTextBoxProperty;
import org.lorislab.smonitor.gwt.uc.form.ModelForm;
import org.lorislab.smonitor.gwt.uc.form.StringListBoxProperty;
import org.lorislab.smonitor.gwt.uc.form.StringTextBoxProperty;
import org.lorislab.smonitor.rs.model.SessionInfoDetails;

/**
 *
 * @author Andrej Petras
 */
public class SessionInfoDetailsModelForm extends ModelForm<SessionInfoDetails> {

    @Override
    public void createProperties() {
        
        addProperty("Agent", new StringTextBoxProperty<SessionInfoDetails>(true) {
            @Override
            public String getValue(SessionInfoDetails object) {
                return object.getSession().getAgent();
            }
        });
        
        addProperty("Application", new StringTextBoxProperty<SessionInfoDetails>(true) {
            @Override
            public String getValue(SessionInfoDetails object) {
                return object.getSession().getApplication();
            }
        });
        
        addProperty("Host", new StringTextBoxProperty<SessionInfoDetails>(true) {
            @Override
            public String getValue(SessionInfoDetails object) {
                return object.getSession().getHost();
            }
        });
        
        addProperty("Id", new StringTextBoxProperty<SessionInfoDetails>(true) {
            @Override
            public String getValue(SessionInfoDetails object) {
                return object.getSession().getId();
            }
        });   
        
        addProperty("User", new StringTextBoxProperty<SessionInfoDetails>(true) {
            @Override
            public String getValue(SessionInfoDetails object) {
                return object.getSession().getUser();
            }
        });
        
        addProperty("Roles", new StringListBoxProperty<SessionInfoDetails>(true, true) {
            @Override
            public List<String> getValue(SessionInfoDetails object) {
                return object.getRoles();
            }
        }, 4, 1); 
                
        addProperty("Info", new StringTextBoxProperty<SessionInfoDetails>(true) {
            @Override
            public String getValue(SessionInfoDetails object) {
                return object.getInfo();
            }
        });
        
        addProperty("New", new BooleanCheckBoxProperty<SessionInfoDetails>(true) {
            @Override
            public Boolean getValue(SessionInfoDetails object) {
                return object.isNewSession();
            }

            @Override
            public CheckBox getWidget(String styleName) {
                return super.getWidget("checkbox-basic");
            }

        });
        
        addProperty("Created", new DateTextBoxProperty<SessionInfoDetails>(ConstantValues.DATE_FORMAT, true) {

            @Override
            public Date getValue(SessionInfoDetails object) {
                return object.getSession().getCreationTime();
            }            
        });
        
        addProperty("Accessed", new DateTextBoxProperty<SessionInfoDetails>(ConstantValues.DATE_FORMAT, true) {
            @Override
            public Date getValue(SessionInfoDetails object) {
                return object.getSession().getLastAccessedTime();
            }
        }); 
        
        addProperty("Accessed interval", new StringTextBoxProperty<SessionInfoDetails>(true) {
            @Override
            public String getValue(SessionInfoDetails object) {                
                return "" + object.getSession().getLastAccessedTimeInternal();
            }
        });
        
        addProperty("Max. inactive", new StringTextBoxProperty<SessionInfoDetails>(true) {
            @Override
            public String getValue(SessionInfoDetails object) {                
                return "" + object.getSession().getMaxInactiveInterval();
            }
        });
              
        addProperty("Size", new StringTextBoxProperty<SessionInfoDetails>(true) {
            @Override
            public String getValue(SessionInfoDetails object) {                
                return "" + object.getSize();
            }
        });
        
        addProperty("Ser. Size", new StringTextBoxProperty<SessionInfoDetails>(true) {
            @Override
            public String getValue(SessionInfoDetails object) {                
                return "" + object.getSizeSerializable();
            }
        });
        
    }
    
}
