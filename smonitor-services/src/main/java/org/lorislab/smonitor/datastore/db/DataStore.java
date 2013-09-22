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

import java.io.File;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

/**
 *
 * @author Andrej Petras
 */
public class DataStore {

    private static final String DIR = "smonitor";
    private static final String FILE = "data";
    private static DB INSTANCE = null;

    public static void start() {
        if (INSTANCE == null) {
            File dir = new File(DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            INSTANCE = DBMaker.newFileDB(new File(dir, FILE))
                    .closeOnJvmShutdown()
                    .make();
        }
    }

    public static <K, V> BTreeMap<K, V> getSmallMap(Class clazz, Serializer serializer) {
        String name = clazz.getName();
        if (INSTANCE.exists(name)) {
            return INSTANCE.getTreeMap(name);
        }
        return INSTANCE.createTreeMap(name).valueSerializer(serializer).make();
    }

    public static void commit() {
        INSTANCE.commit();
    }

    public static void shutdown() {
        if (INSTANCE != null) {
            INSTANCE.close();
        }
    }
}
