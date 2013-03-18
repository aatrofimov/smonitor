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
package com.ajkaandrej.smonitor.config.file.util;

import com.ajkaandrej.smonitor.config.file.filter.ConfigFileFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public final class ConfigFileUtil {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ConfigFileUtil.class.getName());
    /**
     * The configuration directory.
     */
    private static final String CONFIG_DIR = "smonitor";
    private static final char FILENAME_EXT_SEPARATOR = '.';

    public static File getDirectory() {
        File result = new File(CONFIG_DIR);
        if (!result.exists()) {
            result.mkdir();
            LOGGER.log(Level.INFO, "Create smonitor configuration directory {0}", result.getPath());
        }
        return result;
    }

    public static List<File> getModuleFiles() {
        List<File> result = new ArrayList<File>();
        File dir = getDirectory();
        if (dir != null) {
            File[] tmp = dir.listFiles(new ConfigFileFilter());
            if (tmp != null) {
                result.addAll(Arrays.asList(tmp));
            }
        } else {
            LOGGER.log(Level.WARNING, "The configuration directory is null!");
        }
        return result;
    }

    public static File getModuleFile(String name) {
        File result = null;
        File dir = getDirectory();
        if (dir != null) {
            result = new File(dir, name);            
        }
        return result;
    }

    public static String getModuleName(File file) {
        String result = null;
        if (file != null) {
            String tmp = file.getName();
            int pos = tmp.lastIndexOf(FILENAME_EXT_SEPARATOR);
            if (pos == -1) {
                result = tmp;
            } else {
                result = tmp.substring(0, pos);
            }
        }
        return result;
    }

    public static Properties saveConfiguration(String module, Map<String, String> data) {
        Properties result = null;
        File file = getModuleFile(module);
        if (file != null) {
            result = loadProperties(file);
            if (result != null) {                
                result.putAll(data);
                saveProperties(module, file, result);
            }
        } else {
            LOGGER.log(Level.WARNING, "The module properties file is null!");            
        }
        return result;
    }

    public static void saveProperties(String module, File file, Properties properties) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            properties.store(out, "The configuration for the module " + module);            
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error saving the properties file " + file.getPath(), ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Error closing the file stream for the file " + file.getPath(), ex);
                }
            }            
        }        
    }
    
    /**
     * Loads a properties from the corresponding file.
     *
     * @param file the properties file.
     * @return the properties the properties.
     */
    public static Properties loadProperties(File file) {
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            props.load(fis);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error reading the properties file " + file.getPath(), ex);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Error closing the file stream for the file " + file.getPath(), ex);
                }
            }
        }
        return props;
    }
}
