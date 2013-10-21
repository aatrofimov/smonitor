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

package org.lorislab.smonitor.base.resources.model;

import java.io.Serializable;

/**
 * The resource message.
 * 
 * @author Andrej Petras
 */
public final class ResourceMessage implements Serializable {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -3401739151456729405L;
    
    /**
     * The reference object.
     */
    private final Object reference;
    
   /**
     * The key of resource.
     */
    private final Enum<?> resourceKey;

    /**
     * The arguments for the message.
     */
    private final Object[] arguments;
    
    /**
     * Creates the message of resource from the resource key and the arguments.
     *
     * @param resourceKey the key of resource.
     * @param reference the object reference.
     * @param arguments the arguments of message.
     */
    public ResourceMessage(final Enum<?> resourceKey, final Object reference, final Object... arguments) {
        this.resourceKey = resourceKey;
        this.arguments = arguments;
        this.reference = reference;
    }

    /**
     * Gets the reference object.
     * @return the reference object.
     */
    public Object getReference() {
        return reference;
    }

    /**
     * Gets the key of resource.
     *
     * @return the key of resource.
     */
    public final Enum<?> getResourceKey() {
        return resourceKey;
    }

    /**
     * Gets the arguments of the message.
     *
     * @return the arguments of the message.
     */
    public final Object[] getArguments() {
        return arguments.clone();
    }
      
}
