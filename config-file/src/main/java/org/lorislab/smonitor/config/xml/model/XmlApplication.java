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
package org.lorislab.smonitor.config.xml.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.lorislab.smonitor.store.model.Application;
import org.lorislab.smonitor.store.model.Configuration;

/**
 * The application.
 * 
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@XmlRootElement(name = "application")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlApplication implements Application {

    /**
     * The list of configuration.
     */
    @XmlElementWrapper(name = "configurations")
    @XmlElement(name = "configuration", type=XmlConfiguration.class)
    private List<Configuration> configurations;

    /**
     * Sets the list of configurations.
     * @param configurations the list of configurations.
     */
    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Configuration> getConfigurations() {
        return configurations;
    }

}
