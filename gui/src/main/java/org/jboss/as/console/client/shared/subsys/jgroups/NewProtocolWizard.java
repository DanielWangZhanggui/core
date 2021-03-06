package org.jboss.as.console.client.shared.subsys.jgroups;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.as.console.client.shared.help.FormHelpPanel;
import org.jboss.as.console.client.shared.subsys.Baseadress;
import org.jboss.ballroom.client.widgets.forms.ComboBoxItem;
import org.jboss.ballroom.client.widgets.forms.Form;
import org.jboss.ballroom.client.widgets.forms.FormValidation;
import org.jboss.ballroom.client.widgets.forms.TextBoxItem;
import org.jboss.ballroom.client.widgets.window.DialogueOptions;
import org.jboss.ballroom.client.widgets.window.WindowContentBuilder;
import org.jboss.dmr.client.ModelNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heiko Braun
 * @date 2/16/12
 */
public class NewProtocolWizard {


    private JGroupsPresenter presenter;

    public NewProtocolWizard(JGroupsPresenter presenter) {
        this.presenter = presenter;
    }

    Widget asWidget() {
        VerticalPanel layout = new VerticalPanel();
        layout.setStyleName("window-content");

        final Form<JGroupsProtocol> form = new Form<JGroupsProtocol>(JGroupsProtocol.class);

        ComboBoxItem typeField = new ComboBoxItem("type", "Type");

        List<String> names = new ArrayList<String>();
        for (Protocol element : Protocol.values()) {
            final String name = element.getLocalName();
            if (name!=null && !"TCP".equals(name) && !"UDP".equals(name))
                names.add(name);
        }

        typeField.setValueMap(names);

        TextBoxItem socket = new TextBoxItem("socketBinding", "Socket Binding", false);

        form.setFields(typeField, socket);


        DialogueOptions options = new DialogueOptions(

                // save
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        // merge base

                        FormValidation validation = form.validate();
                        if(validation.hasErrors())
                            return;

                        presenter.onCreateProtocol(form.getUpdatedEntity());

                    }
                },

                // cancel
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        presenter.closeDialoge();
                    }
                }

        );

        // ----------------------------------------

        Widget formWidget = form.asWidget();

        FormHelpPanel helpPanel = new FormHelpPanel(new FormHelpPanel.AddressCallback() {
            @Override
            public ModelNode getAddress() {
                ModelNode address = Baseadress.get();
                address.add("subsystem", "jgroups");
                address.add("stack", "*");
                address.add("protocol", "*");
                return address;
            }
        }, form);

        layout.add(helpPanel.asWidget());

        layout.add(formWidget);

        return new WindowContentBuilder(layout, options).build();
    }
}
