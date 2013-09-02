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

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Andrej Petras
 */
@Provider
public final class RestServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Context
    private HttpHeaders headers;

    @Override
    public Response toResponse(ServiceException exception) {
        RestServiceException entity = new RestServiceException();
        entity.setMessage(exception.getMessage());
        entity.setRef(exception.getRef());
        entity.setParams(exception.getParams());        
        if (exception.getCause() != null) {
            entity.setDetails(exception.getCause().getMessage());
        }
        return Response.status(Status.BAD_REQUEST).entity(entity).type(headers.getMediaType()).build();

    }
    
}
