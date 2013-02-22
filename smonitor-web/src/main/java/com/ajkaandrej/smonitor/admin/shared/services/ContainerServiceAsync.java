package com.ajkaandrej.smonitor.admin.shared.services;

import com.ajkaandrej.smonitor.admin.shared.exception.ServiceException;
import com.ajkaandrej.smonitor.admin.shared.model.EngineInfo;
import com.ajkaandrej.smonitor.admin.shared.model.SessionDetails;
import com.ajkaandrej.smonitor.admin.shared.model.SessionInfo;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationDetails;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public interface  ContainerServiceAsync {
    
    void getEngine(AsyncCallback<EngineInfo> callback);
    
    void getWebApplication(WebApplicationInfo info, AsyncCallback<WebApplicationDetails> callback);
    
    void getSessionDetails(WebApplicationInfo webApp, SessionInfo info, AsyncCallback<SessionDetails> callback);

}
