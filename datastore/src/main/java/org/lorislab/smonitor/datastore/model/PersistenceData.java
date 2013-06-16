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
package org.lorislab.smonitor.datastore.model;

import com.j256.ormlite.field.DatabaseField;
import java.util.UUID;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class PersistenceData {
    
    public static final String FIELD_GUID = "guid";
    
    @DatabaseField(canBeNull = false, id = true, unique = true)
    private String guid = UUID.randomUUID().toString();
    
    private String oldGuid = guid;
            
    public boolean isNew() {
        return guid.equals(oldGuid);
    }    

    public String getGuid() {
        return guid;
    }
 
}
