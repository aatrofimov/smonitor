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

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import java.sql.SQLException;
import java.util.List;
import org.lorislab.smonitor.datastore.criteria.AgentDataSearchCriteria;
import org.lorislab.smonitor.datastore.model.AgentData;
import org.lorislab.smonitor.datastore.model.PersistenceData;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class AgentDataService extends AbstractDataService<AgentData> {

    public List<AgentData> findByCriteria(AgentDataSearchCriteria criteria) {
        List<AgentData> result = null;

        try {
            QueryBuilder<AgentData, String> queryBuilder = dao.queryBuilder();
            
            Where<AgentData, String> where = queryBuilder.where();
            
            boolean first = false;
            if (criteria.getGuids() != null) {
                where.in(PersistenceData.FIELD_GUID, criteria.getGuids());
                first = true;
            }
            
            if (criteria.isEnabled() != null) {
                if (first) {
                    where.and();
                }
                where.eq(AgentData.FIELD_ENABLED, criteria.isEnabled());
            }

            PreparedQuery<AgentData> preparedQuery = queryBuilder.prepare();
            result = dao.query(preparedQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
}
