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
package org.lorislab.smonitor.admin.client.service;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import org.jboss.errai.enterprise.client.jaxrs.MarshallingWrapper;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseException;
import org.jboss.errai.enterprise.client.jaxrs.api.RestErrorCallback;
import org.lorislab.smonitor.rs.exception.RestServiceException;

/**
 *
 * @author Andrej Petras
 */
public abstract class RestServiceExceptionCallback implements RestErrorCallback {

    @Override
    public boolean error(Request message, Throwable throwable) {
        try {
            throw throwable;
        } catch (ResponseException e) {
            Response response = e.getResponse();
            RestServiceException exception = MarshallingWrapper.fromJSON(response.getText(), RestServiceException.class);
            exception(exception);
        } catch (Throwable t) {
            Window.alert(t.getMessage());
        }
        return false;
    }
    
    public abstract void exception(final RestServiceException exception);
}
