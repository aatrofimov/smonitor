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
package org.lorislab.smonitor.admin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.watopi.chosen.client.ChosenOptions;
import com.watopi.chosen.client.gwt.ChosenListBox;
import com.watopi.chosen.client.resources.ChozenCss;
import com.watopi.chosen.client.resources.Resources;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.lorislab.smonitor.admin.client.handler.DialogEventHandler;
import org.lorislab.smonitor.admin.client.handler.TableRowHoverHandler;
import org.lorislab.smonitor.admin.client.model.AgentWrapper;
import org.lorislab.smonitor.admin.client.model.SessionWrapper;
import org.lorislab.smonitor.admin.client.panel.QuestionDialogBox;
import org.lorislab.smonitor.admin.client.panel.SessionGridPanel;
import org.lorislab.smonitor.admin.client.panel.SessionInfoDetailsPanel;
import org.lorislab.smonitor.admin.client.service.Client;
import org.lorislab.smonitor.admin.client.service.ClientFactory;
import org.lorislab.smonitor.admin.client.service.RestServiceExceptionCallback;
import org.lorislab.smonitor.admin.client.toolbar.SessionToolbarPanel;
import org.lorislab.smonitor.gwt.uc.page.ViewPage;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.rs.exception.RestServiceException;
import org.lorislab.smonitor.rs.model.ServerApplication;
import org.lorislab.smonitor.rs.model.SessionInfo;
import org.lorislab.smonitor.rs.model.SessionInfoDetails;
import org.lorislab.smonitor.rs.model.SessionSearchCriteria;
import org.lorislab.smonitor.rs.service.ApplicationService;

/**
 *
 * @author Andrej Petras
 */
public class SessionsView extends ViewPage {

    @UiField
    DockLayoutPanel searchCriteria;
    @UiField
    FlowPanel searchCriteriaItems;
    @UiField(provided = true)
    ChosenListBox agentsList;
    @UiField(provided = true)
    ChosenListBox appList;
    @UiField
    SessionGridPanel sessionPanel;
    @UiField
    Button btnSessionReset;
    @UiField
    Button btnSessionSearch;
    @UiField
    Label resultCount;
    private SessionToolbarPanel sessionToolbar = new SessionToolbarPanel();
    private Client<ApplicationService> appService = ClientFactory.create(ApplicationService.class);
    private AgentController agentController;
    private QuestionDialogBox<SessionWrapper> deleteQuestion = new QuestionDialogBox<SessionWrapper>();
    private SessionInfoDetailsPanel detailsPanel = new SessionInfoDetailsPanel();

    public SessionsView(AgentController agentController) {
        this.agentController = agentController;
        ChosenOptions options = new ChosenOptions();
        options.setResources(GWT.<MyResources>create(MyResources.class));
        agentsList = new ChosenListBox(true, options);

        options = new ChosenOptions();
        options.setResources(GWT.<MyResources>create(MyResources.class));
        appList = new ChosenListBox(true, options);

        initWidget(uiBinder.createAndBindUi(this));


        searchCriteria.getElement().getParentElement().getStyle().setOverflow(Overflow.VISIBLE);
        searchCriteriaItems.getElement().getParentElement().getStyle().setOverflow(Overflow.VISIBLE);

        btnSessionReset.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                agentsList.setSelectedIndex(-1);
                agentsList.update();
                appList.setSelectedIndex(-1);
                appList.update();
            }
        });

        btnSessionSearch.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SessionSearchCriteria criteria = new SessionSearchCriteria();
                criteria.setAgents(getValues(agentsList));
                criteria.setApplications(getValues(appList));

                sessionPanel.reset();
                appService.call(sessionSearch, sessionSearchError).findSessions(criteria);
            }
        });

        sessionPanel.setTableRowHoverHandler(new TableRowHoverHandler() {
            @Override
            public void onRowOver(TableRowElement row) {
                int index = row.getRowIndex();
                SessionWrapper w = sessionPanel.get(index);
                TableCellElement cell = row.getCells().getItem(0);
                sessionToolbar.open(cell.getAbsoluteLeft(), cell.getAbsoluteTop(), w);
            }

            @Override
            public void onRowOut() {
                sessionToolbar.close();
            }
        });

        sessionPanel.setChangeSizeHandler(new EntityDataGrid.ChangeSizeHandler() {
            @Override
            public void changeSize(int size) {
                resultCount.setText("# " + sessionPanel.size());
            }
        });

        sessionToolbar.setHandler(new SessionToolbarPanel.ClickButtonHandler() {
            @Override
            public void info(SessionWrapper data) {
                appService.call(sessionDetails).getSesssionDetails(data.data.getGuid(), data.data.getHost(), data.data.getApplication(), data.data.getId());
            }

            @Override
            public void delete(SessionWrapper data) {
                deleteQuestion.open(data, "Delete Session", "Do you really want to delete selected session " + data.data.getId() + " ?");
            }

            @Override
            public void refresh(SessionWrapper data) {
                sessionPanel.request(data);
                appService.call(sessionRefresh).getSesssion(data.data.getGuid(), data.data.getHost(), data.data.getApplication(), data.data.getId());
            }
        });

        deleteQuestion.setOkHandler(new DialogEventHandler<SessionWrapper>() {
            @Override
            public void event(SessionWrapper data) {
                appService.call(sessionDelete).deleteSesssion(data.data.getGuid(), data.data.getHost(), data.data.getApplication(), data.data.getId());
            }
        });

    }

    private static Set<String> getValues(ChosenListBox list) {
        Set<String> result = new HashSet<String>();
        if (list != null) {
            String[] values = list.getValues();
            if (values != null && values.length > 0) {
                result.addAll(Arrays.asList(values));
            } else {
                int size = list.getItemCount();
                String item;
                for (int i = 0; i < size; i++) {
                    item = list.getValue(i);
                    if (item != null) {
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void openPage() {
        agentsList.clear();
        appList.clear();
        sessionToolbar.close();
        deleteQuestion.close();

        List<AgentWrapper> data = agentController.getAgents();
        if (data != null) {
            Set<String> tmp = new HashSet<String>();
            for (AgentWrapper w : data) {
                if (w.server != null) {
                    agentsList.addItem(w.data.getName(), w.data.getGuid());
                    List<ServerApplication> apps = w.server.getApplications();
                    if (apps != null) {
                        for (ServerApplication a : apps) {
                            if (!tmp.contains(a.getId())) {
                                appList.addItem(a.getName(), a.getId());
                                tmp.add(a.getId());
                            }
                        }
                    }
                }
            }
        }


    }

    @Override
    public void closePage() {
        sessionToolbar.close();
        deleteQuestion.close();
    }

    @Override
    public String getPageTitle() {
        return "Sessions";
    }
    final RemoteCallback<List<SessionInfo>> sessionSearch = new RemoteCallback<List<SessionInfo>>() {
        @Override
        public void callback(List<SessionInfo> value) {
            sessionPanel.reset();
            sessionPanel.addAllItems(value);
        }
    };
    final RemoteCallback<SessionInfo> sessionRefresh = new RemoteCallback<SessionInfo>() {
        @Override
        public void callback(SessionInfo value) {
            if (value != null) {
                sessionPanel.replaceById(value.getId(), value);
            }
        }
    };
    final RemoteCallback<SessionInfoDetails> sessionDetails = new RemoteCallback<SessionInfoDetails>() {
        @Override
        public void callback(SessionInfoDetails value) {
            if (value != null) {
                detailsPanel.open(value);
            }
        }
    };
    final RestServiceExceptionCallback sessionSearchError = new RestServiceExceptionCallback() {
        @Override
        public void exception(RestServiceException exception) {
        }
    };
    final RemoteCallback<String> sessionDelete = new RemoteCallback<String>() {
        @Override
        public void callback(String value) {
            sessionPanel.removeById(value);
            deleteQuestion.close();
        }
    };

    interface MyUiBinder extends UiBinder<Widget, SessionsView> {
    }
    private static SessionsView.MyUiBinder uiBinder = GWT.create(SessionsView.MyUiBinder.class);

    public interface MyResources extends Resources {

        @ClientBundle.Source("chozen.css")
        @Override
        ChozenCss css();
    }
}
