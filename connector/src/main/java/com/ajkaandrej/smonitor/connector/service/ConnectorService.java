package com.ajkaandrej.smonitor.connector.service;

import com.ajkaandrej.smonitor.connector.model.HttpSessionHeader;
import com.ajkaandrej.smonitor.connector.model.HttpSessionWrapper;
import com.ajkaandrej.smonitor.connector.model.ServerEngine;
import com.ajkaandrej.smonitor.connector.model.ServerHost;
import com.ajkaandrej.smonitor.connector.model.WebApplication;
import com.ajkaandrej.smonitor.connector.model.WebApplicationWrapper;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface ConnectorService {

    HttpSessionWrapper load(WebApplication webApplication, HttpSessionHeader header);
    
    WebApplicationWrapper load(WebApplication webApplication);
  
    ServerEngine load();
    
    ServerHost[] load(ServerEngine engine);
    
    WebApplication[] load(ServerEngine engine, ServerHost host);

}
