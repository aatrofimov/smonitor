package com.ajkaandrej.smonitor.admin.server.services;

import com.ajkaandrej.smonitor.admin.server.factory.ContainerFactory;
import com.ajkaandrej.smonitor.admin.shared.exception.ServiceException;
import com.ajkaandrej.smonitor.admin.shared.model.EngineInfo;
import com.ajkaandrej.smonitor.admin.shared.model.SessionDetails;
import com.ajkaandrej.smonitor.admin.shared.model.SessionInfo;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationDetails;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationInfo;
import com.ajkaandrej.smonitor.admin.shared.services.ContainerService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.logging.Logger;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ContainerServiceImpl extends RemoteServiceServlet implements ContainerService {

    private static final long serialVersionUID = 5084673839095891171L;

    private static final Logger LOGGER = Logger.getLogger(ContainerServiceImpl.class.getName());
    
    @Override
    public EngineInfo getEngine() throws ServiceException {
        EngineInfo result = ContainerFactory.createEngine();
        return result;
    }

    @Override
    public WebApplicationDetails getWebApplication(WebApplicationInfo info) throws ServiceException {
        WebApplicationDetails result = null;
        if (info != null) {
            result = ContainerFactory.create(info);
        }
        return result;
    }

    @Override
    public SessionDetails getSessionDetails(WebApplicationInfo webApp, SessionInfo info) throws ServiceException {
        SessionDetails result = null;
        if (webApp != null && info != null) {
            result = ContainerFactory.create(webApp, info);
        } else {
            LOGGER.warning("Is null: webApp " + webApp + " info " + info);            
        }
        return result;
    }
}
