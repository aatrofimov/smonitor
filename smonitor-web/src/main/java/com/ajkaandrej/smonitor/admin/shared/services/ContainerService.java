package com.ajkaandrej.smonitor.admin.shared.services;

import com.ajkaandrej.smonitor.admin.shared.exception.ServiceException;
import com.ajkaandrej.smonitor.admin.shared.model.EngineInfo;
import com.ajkaandrej.smonitor.admin.shared.model.SessionDetails;
import com.ajkaandrej.smonitor.admin.shared.model.SessionInfo;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationDetails;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationInfo;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@RemoteServiceRelativePath("ContainerService")
public interface ContainerService extends RemoteService {
    
    EngineInfo getEngine() throws ServiceException;
    
    WebApplicationDetails getWebApplication(WebApplicationInfo info) throws ServiceException;
    
    SessionDetails getSessionDetails(WebApplicationInfo webApp, SessionInfo info) throws ServiceException;
}
