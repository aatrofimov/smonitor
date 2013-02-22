package com.ajkaandrej.smonitor.admin.client;

import com.ajkaandrej.smonitor.admin.client.model.EngineTreeNode;
import com.ajkaandrej.smonitor.admin.client.model.SessionInfoListGridRecord;
import com.ajkaandrej.smonitor.admin.client.view.AttributesTable;
import com.ajkaandrej.smonitor.admin.client.view.EngineTree;
import com.ajkaandrej.smonitor.admin.client.view.SessionDetailsForm;
import com.ajkaandrej.smonitor.admin.client.view.SessionsTable;
import com.ajkaandrej.smonitor.admin.client.view.WebAppDetailForm;
import com.ajkaandrej.smonitor.admin.shared.model.EngineInfo;
import com.ajkaandrej.smonitor.admin.shared.model.SessionDetails;
import com.ajkaandrej.smonitor.admin.shared.model.SessionInfo;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationDetails;
import com.ajkaandrej.smonitor.admin.shared.model.WebApplicationInfo;
import com.ajkaandrej.smonitor.admin.shared.services.ContainerService;
import com.ajkaandrej.smonitor.admin.shared.services.ContainerServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class Admin implements EntryPoint {

    private final IButton refreshButton = new IButton("Refresh12");
    private final EngineTree tree = new EngineTree();
    private ContainerServiceAsync containerService;
    private final SessionsTable table = new SessionsTable();
    private final AttributesTable attrTable = new AttributesTable();
       
    private WebAppDetailForm wepAppDetailForm = new WebAppDetailForm();
    private SessionDetailsForm sessionDetailForm = new SessionDetailsForm();
    
    @Override
    public void onModuleLoad() {


        refreshButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                loadEngine();
            }
        });

        tree.addSelectionChangedHandler(new SelectionChangedHandler() {
            @Override
            public void onSelectionChanged(com.smartgwt.client.widgets.grid.events.SelectionEvent event) {
                EngineTreeNode node = (EngineTreeNode) event.getRecord();
                Object object = node.getUserObject();
                if (object instanceof WebApplicationInfo) {
                    loadWebApplicationInfoDetail((WebApplicationInfo) object);
                }
            }
        });

        table.addSelectionChangedHandler(new SelectionChangedHandler() {

            @Override
            public void onSelectionChanged(SelectionEvent event) {
                SessionInfoListGridRecord item = (SessionInfoListGridRecord) event.getSelectedRecord();
                loadSessionInfoDetail(wepAppDetailForm.getObject().getInfo(), item.getObject());
            }
        });
     
        final TabSet topTabSet = new TabSet();  
        topTabSet.setTabBarPosition(Side.TOP);
        topTabSet.setWidth100();
        topTabSet.setHeight100();
        
        Tab tTab1 = new Tab("Details");
        tTab1.setPane(sessionDetailForm);
        
        Tab tTab2 = new Tab("Attributes");
        tTab2.setPane(attrTable);
        
        topTabSet.addTab(tTab1);  
        topTabSet.addTab(tTab2);
        
        SectionStack sectionStack = new SectionStack();
        sectionStack.setWidth("70%");
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        sectionStack.setAnimateSections(true);
        sectionStack.setOverflow(Overflow.HIDDEN);

        SectionStackSection summarySection = new SectionStackSection();
        summarySection.setTitle("Details");
        summarySection.setExpanded(false);        
        summarySection.setItems(wepAppDetailForm);

        SectionStackSection detailsSection = new SectionStackSection();
        detailsSection.setTitle("Sessions");
        detailsSection.setExpanded(true);
        detailsSection.setCanCollapse(false);
        detailsSection.setItems(table, topTabSet);
        
        sectionStack.setSections(summarySection, detailsSection);

        VLayout leftView = new VLayout();
        leftView.setWidth("30%");
        leftView.addMember(refreshButton);
        leftView.addMember(tree);
        leftView.setShowResizeBar(true);

        HLayout mainLayout = new HLayout();
        mainLayout.setWidth("97%");
        mainLayout.setHeight("97%");
        mainLayout.addMember(leftView);
        mainLayout.addMember(sectionStack);

        RootPanel.get().add(mainLayout);
    }

    private void loadWebApplicationInfoDetail(WebApplicationInfo webApp) {
        if (containerService == null) {
            containerService = GWT.create(ContainerService.class);
        }

        AsyncCallback<WebApplicationDetails> callback = new AsyncCallback<WebApplicationDetails>() {
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
            }

            public void onSuccess(WebApplicationDetails result) {
                wepAppDetailForm.editWebApplicationDetails(result);
                table.loadData(result.getSessions());
            }
        };

        containerService.getWebApplication(webApp, callback);
    }

    private void loadEngine() {
        if (containerService == null) {
            containerService = GWT.create(ContainerService.class);
        }

        AsyncCallback<EngineInfo> callback = new AsyncCallback<EngineInfo>() {
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
            }

            public void onSuccess(EngineInfo result) {
                tree.loadData(result);
            }
        };

        containerService.getEngine(callback);
    }
    
    private void loadSessionInfoDetail(WebApplicationInfo webApp, SessionInfo sessionInfo) {
         if (containerService == null) {
            containerService = GWT.create(ContainerService.class);
        }

        AsyncCallback<SessionDetails> callback = new AsyncCallback<SessionDetails>() {
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
            }

            public void onSuccess(SessionDetails result) {
                sessionDetailForm.loadData(result);
                attrTable.loadAttributes(result.getAttributes());
            }
        };

        containerService.getSessionDetails(webApp, sessionInfo, callback);
    }
}
