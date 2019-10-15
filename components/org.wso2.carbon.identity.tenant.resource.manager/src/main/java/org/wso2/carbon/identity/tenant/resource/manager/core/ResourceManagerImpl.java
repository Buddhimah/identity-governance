package org.wso2.carbon.identity.tenant.resource.manager.core;

import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.event.publisher.core.config.EventPublisherConfiguration;
import org.wso2.carbon.event.publisher.core.config.EventPublisherConfigurationFile;
import org.wso2.carbon.event.publisher.core.exception.EventPublisherConfigurationException;
import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManager;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceAdd;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceFile;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceTypeAdd;
import org.wso2.carbon.identity.tenant.resource.manager.constants.TenantResourceConstants;
import org.wso2.carbon.identity.tenant.resource.manager.internal.TenantResourceManagerDataHolder;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Tenant Resource manager service implementation.
 */
public class ResourceManagerImpl implements ResourceManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public void replaceEventPublisherConfiguration(String eventPublisherName, String publisherConfigXML)
            throws ConfigurationManagementException {

        EventPublisherConfiguration activeEventPublisherConfiguration = null;
        try {
            activeEventPublisherConfiguration = TenantResourceManagerDataHolder
                    .getInstance().getCarbonEventPublisherService()
                    .getActiveEventPublisherConfiguration(eventPublisherName);

        } catch (EventPublisherConfigurationException e) {
            //ResourceUtils.handleServerException("","");
        }
            if (activeEventPublisherConfiguration != null) {

                destroyEventPublisherConfiguration(activeEventPublisherConfiguration);
            }

        try {
            TenantResourceManagerDataHolder.getInstance().getCarbonEventPublisherService()
                    .deployEventPublisherConfiguration(publisherConfigXML);
        } catch (EventPublisherConfigurationException e) {
            e.printStackTrace();
        }
        TenantResourceManagerDataHolder.getInstance().getConfigurationManager()
                    .deleteFiles(TenantResourceConstants.PUBLISHER, eventPublisherName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ResourceFile> getEventPublishers() throws ConfigurationManagementException {


        createResourceType(TenantResourceManagerDataHolder.getInstance().getConfigurationManager());
        return TenantResourceManagerDataHolder.getInstance().getConfigurationManager()
                .getFiles(TenantResourceConstants.PUBLISHER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEventPublisherConfiguration(String eventPublisherName, String publisherConfigXML)
            throws ConfigurationManagementException {

        ConfigurationManager configurationManager =
                TenantResourceManagerDataHolder.getInstance().getConfigurationManager();
        List<ResourceFile> eventPublishers = getEventPublishers();
        for (ResourceFile resourceFile : eventPublishers) {
            if (eventPublisherName.equals(resourceFile.getName())) {
                //throw error
            } else {
                replaceEventPublisherConfiguration(eventPublisherName, publisherConfigXML);
                createResource(configurationManager,eventPublisherName);
                TenantResourceManagerDataHolder.getInstance().getConfigurationManager()
                        .addFile(TenantResourceConstants.PUBLISHER, eventPublisherName, eventPublisherName,
                                new ByteArrayInputStream(publisherConfigXML.getBytes()));
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEventPublisherConfiguration(String eventPublisherName, String publisherConfigXML)
            throws ConfigurationManagementException {

        replaceEventPublisherConfiguration(eventPublisherName, publisherConfigXML);
        TenantResourceManagerDataHolder.getInstance().getConfigurationManager()
                .addFile(TenantResourceConstants.PUBLISHER, eventPublisherName, eventPublisherName,
                        new ByteArrayInputStream(publisherConfigXML.getBytes()));
    }

    /**
     * This is used to destroy  an existing EventPublisher.
     *
     * @param eventPublisherConfiguration Event Publisher Configuration.
     * @throws ConfigurationManagementException Configuration Management Exception.
     */
    private void destroyEventPublisherConfiguration(EventPublisherConfiguration eventPublisherConfiguration) {

        int tenantId = PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId();
        EventPublisherConfigurationFile eventPublisherConfigurationFile = new EventPublisherConfigurationFile();
        eventPublisherConfigurationFile.setTenantId(tenantId);
        eventPublisherConfigurationFile.setEventPublisherName(eventPublisherConfiguration.getEventPublisherName());
        eventPublisherConfigurationFile.setFileName(eventPublisherConfiguration.getEventPublisherName());
        eventPublisherConfigurationFile.setStatus(EventPublisherConfigurationFile.Status.DEPLOYED);
        try {
            TenantResourceManagerDataHolder.getInstance().getCarbonEventPublisherService()
                    .addEventPublisherConfigurationFile(eventPublisherConfigurationFile, tenantId);
        } catch (EventPublisherConfigurationException e) {
            e.printStackTrace();
        }
        TenantResourceManagerDataHolder.getInstance().getCarbonEventPublisherService()
                .removeEventPublisherConfigurationFile(eventPublisherConfiguration.getEventPublisherName(), tenantId);

    }

    private void createResourceType(ConfigurationManager configurationManager) throws ConfigurationManagementException{

        if (configurationManager.getResourceType(TenantResourceConstants.PUBLISHER) == null) {
            ResourceTypeAdd resourceTypeAdd = new ResourceTypeAdd();
            resourceTypeAdd.setName(TenantResourceConstants.PUBLISHER);
            configurationManager.addResourceType(resourceTypeAdd);
        }
    }

    private void createResource(ConfigurationManager configurationManager, String eventPublisherName)
            throws ConfigurationManagementException {

        ResourceAdd resourceAdd = new ResourceAdd();
        resourceAdd.setName(eventPublisherName);
        configurationManager.addResource(TenantResourceConstants.PUBLISHER, resourceAdd);

    }

}
