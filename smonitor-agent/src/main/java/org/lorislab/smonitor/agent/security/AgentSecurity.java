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
package org.lorislab.smonitor.agent.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class AgentSecurity {
    
    public static final String HEADER_KEY = "SMonitor-Agent-Key";
    
    private static String PASSWORD = "CHANGE-ME";
    
    private static String DIGEST = "MD5";
    
    public static String getDefaultKey() {
        return generatedKey(PASSWORD);
    }
    
    private static String generatedKey(String password) {  
        String result = password;
        try {
            MessageDigest m = MessageDigest.getInstance(DIGEST);
            m.update(password.getBytes(), 0, password.length());
            BigInteger in = new BigInteger(1, m.digest());
            result = in.toString(16);            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AgentSecurity.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return result;
    }
       
}
