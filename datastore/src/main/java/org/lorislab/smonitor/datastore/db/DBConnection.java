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
package org.lorislab.smonitor.datastore.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class DBConnection {

    private static String DEFAULT_URL = "jdbc:h2:smonitor";
    private static ConnectionSource CONNECTION = null;

    public static ConnectionSource getConnectionSource() {
        if (CONNECTION == null) {
            CONNECTION = createFileConnectionSource();
        }
        return CONNECTION;
    }

    public static <T> Dao<T, String> createDao(Class<T> clazz) {
        Dao<T, String> result = null;
        try {
            // create DAO
            result = DaoManager.createDao(getConnectionSource(), clazz);            
            // create table
            TableUtils.createTableIfNotExists(getConnectionSource(), clazz);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static ConnectionSource createFileConnectionSource() {
        ConnectionSource result = null;
        try {
            result = new JdbcConnectionSource(getFileUrl());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static String getFileUrl() {
        String value = System.getProperty(DBConnection.class.getName());
        if (value == null || value.isEmpty()) {
            value = DEFAULT_URL;
        }

        return value;
    }
}
