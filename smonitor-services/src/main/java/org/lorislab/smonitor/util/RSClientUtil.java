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
package org.lorislab.smonitor.util;

import javax.ws.rs.core.Response;
import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ClientResponseFailure;
import org.lorislab.smonitor.rs.exception.ServiceException;

/**
 *
 * @author Andrej Petras
 */
public final class RSClientUtil {

    private RSClientUtil() {
    }

    public static void handleException(String guid, Exception ex) throws ServiceException {
        if (ex instanceof ClientResponseFailure) {
            ClientResponseFailure e = (ClientResponseFailure) ex;
            ClientResponse response = e.getResponse();
            if (response.getResponseStatus().equals(Response.Status.FORBIDDEN)) {
                throw new ServiceException(guid, "Authentification failed", e);
            }
            throw new ServiceException(guid, "Error in the communication to the server " + response.getResponseStatus().getStatusCode(), e);
        } else {
            String msg = ex.getMessage();
            if (msg.startsWith(ClientProtocolException.class.getName())) {
                throw new ServiceException(guid, "The server is not valid address.", ex);
            }
            throw new ServiceException(guid, "Could not connect to the server", ex);
        }
    }
}
