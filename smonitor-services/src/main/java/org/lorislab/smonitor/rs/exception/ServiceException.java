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
package org.lorislab.smonitor.rs.exception;

import java.util.Map;

/**
 *
 * @author Andrej Petras
 */
public class ServiceException  extends RuntimeException {
    
    private static final long serialVersionUID = -4382293726363854140L;
    
    private String ref;
    
    private Map<String, Object> params;
    
    public ServiceException(String ref, String msg) {        
        this(ref, msg, null, null);
    }
    
    public ServiceException(String ref, String msg, Map<String, Object> params) {        
        this(ref, msg, null, params);
    }
    
    public ServiceException(String ref, Throwable ex, Map<String, Object> params) {
        this(ref, null, ex, params);
    }

    public ServiceException(String ref, String msg, Throwable ex, Map<String, Object> params) {
        super(msg, ex);
        this.ref = ref;
        this.params = params;
    }    

    public ServiceException(String ref, String msg, Throwable ex) {
        this(ref, msg, ex, null);
    } 
    
    public String getRef() {
        return ref;
    }

    public Map<String, Object> getParams() {
        return params;
    }      
        
}
