/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.jboss.as.console.client.standalone;

import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.as.console.client.Console;
import org.jboss.as.console.client.core.NameTokens;
import org.jboss.as.console.client.shared.model.SubsystemRecord;
import org.jboss.as.console.client.shared.subsys.SubsystemTreeBuilder;
import org.jboss.ballroom.client.layout.LHSNavTree;
import org.jboss.ballroom.client.layout.LHSNavTreeItem;
import org.jboss.ballroom.client.widgets.stack.DisclosureStackPanel;

import java.util.List;

/**
 * LHS navigation for standalone server management.
 *
 * @author Heiko Braun
 * @date 2/10/11
 */
public class LHSStandaloneNavigation {

    private ScrollPanel scroll ;

    private VerticalPanel stack;

    private VerticalPanel layout;
    private LHSNavTree subsysTree;

    public LHSStandaloneNavigation() {
        super();

        layout = new VerticalPanel();
        layout.setStyleName("fill-layout-width");

        stack = new VerticalPanel();
        stack.setStyleName("fill-layout-width");

        // ----------------------------------------------------


        subsysTree = new LHSNavTree("profiles");

        DisclosurePanel subsysPanel  = new DisclosureStackPanel(Console.CONSTANTS.common_label_profile(), true).asWidget();
        subsysPanel.setContent(subsysTree);
        stack.add(subsysPanel);

        // ----------------------------------------------------

        Tree commonTree = new LHSNavTree("profiles");
        DisclosurePanel commonPanel  = new DisclosureStackPanel(Console.CONSTANTS.common_label_generalConfig()).asWidget();
        commonPanel.setContent(commonTree);

        LHSNavTreeItem[] commonItems = new LHSNavTreeItem[] {
                /*new LHSNavTreeItem("Server", NameTokens.StandaloneServerPresenter),*/
                new LHSNavTreeItem(Console.CONSTANTS.common_label_interfaces(), NameTokens.InterfacePresenter),
                new LHSNavTreeItem(Console.CONSTANTS.common_label_socketBinding(), NameTokens.SocketBindingPresenter),
                new LHSNavTreeItem(Console.CONSTANTS.common_label_systemProperties(), NameTokens.PropertiesPresenter)
        };

        for(LHSNavTreeItem item : commonItems)
        {
            commonTree.addItem(item);
        }

        stack.add(commonPanel);

        layout.add(stack);

        scroll = new ScrollPanel(layout);

    }

    public Widget asWidget()
    {
        return scroll;
    }

    public void updateFrom(List<SubsystemRecord> subsystems) {

        subsysTree.removeItems();

        SubsystemTreeBuilder.build(subsysTree, subsystems);

    }
}