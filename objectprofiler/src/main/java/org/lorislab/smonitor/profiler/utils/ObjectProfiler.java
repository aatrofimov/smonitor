/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lorislab.smonitor.profiler.utils;

import java.io.ByteArrayOutputStream;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;

/**
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
