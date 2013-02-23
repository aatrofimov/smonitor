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
package com.ajkaandrej.smonitor.mapper;

import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ObjectMapper {

    private static ObjectMapper INSTANCE;
    
    private ModelMapper mapper;

    private ObjectMapper() {
        mapper = new ModelMapper();
    }

    public static ObjectMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ObjectMapper();
        }
        return INSTANCE;
    }
    
    public <D extends Object> D map(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }

    public void map(Object source, Object destination) {
        mapper.map(source, destination);
    }

    public <D extends Object> D map(Object source, Type destinationType) {
        return mapper.map(source, destinationType);
    }
}
