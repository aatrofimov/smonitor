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
package com.ajkaandrej.smonitor.agent.mapper;

import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;

/**
 * The object mapper.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ObjectMapper {

    /**
     * The object mapper instance.
     */
    private static ObjectMapper INSTANCE;
    /**
     * The model mapper.
     */
    private ModelMapper mapper;

    /**
     * The default constructor.
     */
    private ObjectMapper() {
        mapper = new ModelMapper();
    }

    /**
     * Gets the object mapper instance.
     *
     * @return the object mapper instance.
     */
    public static ObjectMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ObjectMapper();
        }
        return INSTANCE;
    }

    /**
     * Maps a instance to an new instance of destinationType.
     *
     * @param <D> destination type
     * @param source object to map from
     * @param destinationType type to map to
     */
    public <D extends Object> D map(Object source, Class<D> destinationType) {
        D result = null;
        if (source != null) {
            result = mapper.map(source, destinationType);
        }
        return result;
    }

    /**
     * Maps {@code source} to {@code destination}.
     *
     * @param source object to map from
     * @param destination object to map to
     */
    public void map(Object source, Object destination) {
        if (source != null && destination != null) {
            mapper.map(source, destination);
        }
    }

    /**
     * Maps {@code source} to an instance of {@code destinationType}.
     *
     * @param <D> destination type
     * @param source object to map from
     * @param destinationType type to map to
     * @return fully mapped instance of {@code destinationType}
     */
    public <D extends Object> D map(Object source, Type destinationType) {
        D result = null;
        if (source != null) {
            result = mapper.map(source, destinationType);
        }
        return result;
    }
}
