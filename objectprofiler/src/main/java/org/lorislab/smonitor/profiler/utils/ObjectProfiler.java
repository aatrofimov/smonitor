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
package org.lorislab.smonitor.profiler.utils;

import java.io.ByteArrayOutputStream;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;

/**
 * The object profiles.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ObjectProfiler {

    public static ObjectProfile createObjectInfo(Object object) {
        ObjectProfile result = new ObjectProfile();
        if (object != null) {
            result.setSize(com.vladium.utils.ObjectProfiler.sizeof(object));
            result.setClazz(object.getClass().getName());
            result.setSerializable(true);

            try {
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(bs);
                os.writeObject(object);
                result.setSerializableSize(bs.size());
            } catch (NotSerializableException e) {
                result.setSerializable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
