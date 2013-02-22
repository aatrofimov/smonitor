package com.ajkaandrej.smonitor.mapper;

import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ObjectMapper {

    private static ObjectMapper instance;
    private ModelMapper mapper;

    private ObjectMapper() {
        mapper = new ModelMapper();
    }

    public static ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapper();
        }
        return instance;
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
