package org.jboss.as.console.client.tools;

/**
 * @author Heiko Braun
 * @date 7/23/12
 */
public interface StoragePresenter {
    void launchNewTemplateWizard();

    void onRemoveTemplate(String id);
}