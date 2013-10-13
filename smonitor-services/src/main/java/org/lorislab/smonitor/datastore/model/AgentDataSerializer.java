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
package org.lorislab.smonitor.datastore.model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import org.mapdb.Serializer;

/**
 * The agent data serialise class.
 * 
 * @author Andrej Petras
 */
public class AgentDataSerializer implements Serializer<AgentData>, Serializable {
    
    private static final long serialVersionUID = 7337000275562637426L;

    @Override
    public void serialize(DataOutput out, AgentData value) throws IOException {
        out.writeUTF(value.getGuid());
        out.writeUTF(value.getName());
        out.writeUTF(value.getServer());
        
        if (value.getKey() != null) {
            out.writeBoolean(true);
            out.writeUTF(value.getKey());
        } else {
            out.writeBoolean(false);
        }
        out.writeBoolean(value.isEnabled());
    }

    @Override
    public AgentData deserialize(DataInput in, int available) throws IOException {
        if(available==0) return null;
        AgentData result = new AgentData();
        result.setGuid(in.readUTF());
        result.setName(in.readUTF());
        result.setServer(in.readUTF());
        boolean tmp = in.readBoolean();
        if (tmp) {
            result.setKey(in.readUTF());
        }
        result.setEnabled(in.readBoolean());        
        return result;
    }
    
}
