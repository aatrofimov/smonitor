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
package org.lorislab.smonitor.datastore.service;

import com.j256.ormlite.dao.CloseableWrappedIterable;
import com.j256.ormlite.dao.Dao;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.lorislab.smonitor.datastore.db.DBConnection;
import org.lorislab.smonitor.datastore.model.PersistenceData;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class AbstractDataService<T extends PersistenceData> {

    protected Dao<T, String> dao;

    public AbstractDataService() {
        // get the class
        Class<T> clazz = discoverEntityClass(this.getClass());
        // create Table and DAO
        dao = DBConnection.createDao(clazz);
    }

    /**
     * Determines the entity class from the class.
     *
     * @param clazz the class.
     */
    private Class<T> discoverEntityClass(Class clazz) {
        Class<T> result = null;
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        Type type = parameterizedType.getActualTypeArguments()[0];
        if (type instanceof Class) {
            result = (Class<T>) type;
        } else if (type instanceof ParameterizedType) {
            result = (Class<T>) ((ParameterizedType) type).getRawType();
        }
        return result;
    }

    public List<T> findAll() {
        List<T> result = new ArrayList<T>();
        CloseableWrappedIterable<T> list = dao.getWrappedIterable();
        for (T item : list) {
            result.add(item);
        }
        return result;
    }

    public T findByBuid(String guid) {
        T result = null;
        try {
            result = dao.queryForId(guid);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public T save(T model) {
        T result = null;
        try {
            if (model != null) {
                if (model.isNew()) {
                    dao.create(model);
                } else {
                    dao.update(model);
                }
                result = findByBuid(model.getGuid());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean delete(T model) {
        int count = 0;
        try {
            count = dao.delete(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count == 1;
    }
    
    public boolean deleteById(String guid) {
        int count = 0;
        try {
            count = dao.deleteById(guid);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count == 1;
    }    
}
