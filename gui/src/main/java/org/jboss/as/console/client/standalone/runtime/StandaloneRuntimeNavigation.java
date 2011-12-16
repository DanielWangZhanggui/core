package org.jboss.as.console.client.standalone.runtime;

import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import org.jboss.as.console.client.core.NameTokens;
import org.jboss.as.console.client.widgets.nav.DefaultTreeItem;
import org.jboss.ballroom.client.layout.LHSNavTree;
import org.jboss.ballroom.client.layout.LHSNavTreeItem;
import org.jboss.ballroom.client.widgets.stack.DisclosureStackPanel;

/**
 * @author Heiko Braun
 * @date 11/2/11
 */
public class StandaloneRuntimeNavigation {

    private VerticalPanel stack;
    private VerticalPanel layout;
    private LHSNavTree subsysTree;

    public Widget asWidget()
    {
        layout = new VerticalPanel();
        layout.setStyleName("fill-layout-width");

        stack = new VerticalPanel();
        stack.setStyleName("fill-layout-width");

        // ----------------------------------------------------


        subsysTree = new LHSNavTree("standalone-runtime");


        // ----------------------------------------------------

        Tree statusTree = new LHSNavTree("standalone-runtime");
        TreeItem serverContents = new DefaultTreeItem("Server Status");
        TreeItem subsysContents = new DefaultTreeItem("Subsystem Metrics");
        statusTree.addItem(serverContents);
        statusTree.addItem(subsysContents);


        LHSNavTreeItem jvmItem = new LHSNavTreeItem("JVM Status", NameTokens.VirtualMachine);
        serverContents.addItem(jvmItem);


        //LHSNavTreeItem metrics = new LHSNavTreeItem("Subsystem Metrics", "metrics");

        LHSNavTreeItem datasources = new LHSNavTreeItem("Datasources", "ds-metrics");
        LHSNavTreeItem jmsQueues = new LHSNavTreeItem("JMS Destinations", "jms-metrics");
        LHSNavTreeItem web = new LHSNavTreeItem("Web", "web-metrics");
        LHSNavTreeItem tx = new LHSNavTreeItem("Transactions", NameTokens.TXMetrics);
        LHSNavTreeItem osgi = new LHSNavTreeItem("OSGi", NameTokens.OSGiRuntimePresenter);

        subsysContents.addItem(datasources);
        subsysContents.addItem(jmsQueues);
        subsysContents.addItem(web);
        subsysContents.addItem(tx);
        subsysContents.addItem(osgi);


        DisclosurePanel serverPanel  = new DisclosureStackPanel("Status", true).asWidget();
        serverPanel.setContent(statusTree);

        // open by default
        serverContents.setState(true);
        subsysContents.setState(true);

        stack.add(serverPanel);

        // ----------------------------------------------------

        Tree deploymentTree = new LHSNavTree("standalone-runtime");
        DisclosurePanel deploymentPanel  = new DisclosureStackPanel("Deployments").asWidget();
        deploymentPanel.setContent(deploymentTree);

        deploymentTree.addItem(new LHSNavTreeItem("Manage Deployments", NameTokens.DeploymentListPresenter));

        stack.add(deploymentPanel);

        layout.add(stack);

        return layout;
    }
}
