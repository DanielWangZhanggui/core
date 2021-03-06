package org.jboss.as.console.client.domain.runtime;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import org.jboss.as.console.client.Console;
import org.jboss.as.console.client.core.message.Message;
import org.jboss.as.console.client.domain.hosts.HostSelector;
import org.jboss.as.console.client.shared.model.SubsystemRecord;
import org.jboss.as.console.client.shared.state.HostList;
import org.jboss.as.console.client.widgets.DefaultSplitLayoutPanel;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Heiko Braun
 * @date 11/2/11
 */
public class DomainRuntimeView extends ViewImpl implements DomainRuntimePresenter.MyView {

    private DomainRuntimePresenter presenter;

    private SplitLayoutPanel layout;
    private LayoutPanel contentCanvas;
    private DomainRuntimeNavigation lhsNavigation;

    private HostSelector hostSelector;

    @Inject
    public DomainRuntimeView() {
        super();

        layout = new DefaultSplitLayoutPanel(2);

        contentCanvas = new LayoutPanel();
        lhsNavigation = new DomainRuntimeNavigation();

        Widget nav = lhsNavigation.asWidget();
        nav.getElement().setAttribute("role", "navigation");

        contentCanvas.getElement().setAttribute("role", "main");

        layout.addWest(nav, 217);
        layout.add(contentCanvas);

    }

    @Override
    public Widget asWidget() {
        return layout;
    }


    @Override
    public void setInSlot(Object slot, IsWidget  content) {

        if (slot == DomainRuntimePresenter.TYPE_MainContent) {
            if(content!=null)
                setContent(content);

        } else {
            Console.getMessageCenter().notify(
                    new Message("Unknown slot requested:" + slot)
            );
        }
    }

    private void setContent(IsWidget  newContent) {
        contentCanvas.clear();
        contentCanvas.add(newContent);
    }

    @Override
    public void setPresenter(DomainRuntimePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setHosts(HostList hosts) {
        lhsNavigation.setHosts(hosts);
    }

    @Override
    public void setSubsystems(List<SubsystemRecord> result) {
        lhsNavigation.setSubsystems(result);
    }

    @Override
    public void resetHostSelection() {
        lhsNavigation.resetHostSelection();
    }
}
