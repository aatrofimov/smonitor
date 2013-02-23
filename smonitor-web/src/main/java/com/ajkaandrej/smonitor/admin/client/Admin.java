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
package com.ajkaandrej.smonitor.admin.client;

import com.ajkaandrej.smonitor.admin.client.model.EngineTreeNode;
import com.ajkaandrej.smonitor.admin.client.model.SessionInfoListGridRecord;
import com.ajkaandrej.smonitor.admin.client.view.AttributesTable;
import com.ajkaandrej.smonitor.admin.client.view.EngineTree;
import com.ajkaandrej.smonitor.admin.client.view.SessionDetailsForm;
import com.ajkaandrej.smonitor.admin.client.view.SessionsTable;
import com.ajkaandrej.smonitor.admin.client.view.WebAppDetailForm;
import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionHeader;
import com.ajkaandrej.smonitor.admin.shared.model.ClientHttpSessionWrapper;
import com.ajkaandrej.smonitor.admin.shared.model.ClientServerEngine;
import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplication;
import com.ajkaandrej.smonitor.admin.shared.model.ClientWebApplicationWrapper;
import com.ajkaandrej.smonitor.admin.shared.services.ContainerService;
import com.ajkaandrej.smonitor.admin.shared.services.ContainerServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class Admin implements EntryPoint {

    private final IButton refreshButton = new IButton("Refresh");
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
                if (object instanceof ClientWebApplication) {
                    loadWebApplicationInfoDetail((ClientWebApplication) object);
                } else {
                    table.loadData(null);
                    wepAppDetailForm.loadData(null);
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
        
        loadEngine();
    }

    private void loadWebApplicationInfoDetail(ClientWebApplication webApp) {
        if (containerService == null) {
            containerService = GWT.create(ContainerService.class);
        }

        AsyncCallback<ClientWebApplicationWrapper> callback = new AsyncCallback<ClientWebApplicationWrapper>() {
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
            }

            public void onSuccess(ClientWebApplicationWrapper result) {
                wepAppDetailForm.loadData(result);
                table.loadData(result.getSessions());
            }
        };

        containerService.getWebApplicationWrapper(webApp, callback);
    }

    private void loadEngine() {
        if (containerService == null) {
            containerService = GWT.create(ContainerService.class);
        }

        AsyncCallback<ClientServerEngine> callback = new AsyncCallback<ClientServerEngine>() {
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
            }

            public void onSuccess(ClientServerEngine result) {
                tree.loadData(result);
            }
        };

        containerService.getServerEngine(callback);
    }
    
    private void loadSessionInfoDetail(ClientWebApplication webApp, ClientHttpSessionHeader sessionHeader) {
         if (containerService == null) {
            containerService = GWT.create(ContainerService.class);
        }

        AsyncCallback<ClientHttpSessionWrapper> callback = new AsyncCallback<ClientHttpSessionWrapper>() {
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
            }

            public void onSuccess(ClientHttpSessionWrapper result) {
                sessionDetailForm.loadData(result);
                attrTable.loadData(result.getAttributes());
            }
        };

        containerService.getHttpSessionWrapper(webApp, sessionHeader, callback);
    }
}
