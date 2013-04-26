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
package org.lorislab.smonitor.config.xml.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.lorislab.smonitor.store.model.Application;

/**
 * The XML utility class.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class XmlUtil {
    
    /**
     * The default private constructor.
     */
    private XmlUtil() {
        // empty constructor
    }
    
    public static Application loadFromFile(String file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Application.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Application) unmarshaller.unmarshal(new FileInputStream(file));
    }
    
    public static void saveToFile(String file, Application application) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(application.getClass());
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);        
        marshaller.marshal(application, new FileOutputStream(file));        
    }
}
