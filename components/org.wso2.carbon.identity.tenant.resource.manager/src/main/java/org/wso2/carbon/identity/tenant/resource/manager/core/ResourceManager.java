package org.wso2.carbon.identity.tenant.resource.manager.core;

import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.model.ResourceFile;

import java.util.List;

/**
 * Tenant Resource manager service interface.
 */
public interface ResourceManager {

    /**
     * This API is used to replace an existing EventPublisher with the given one.
     *
     * @param eventPublisherName Event Publisher Name.
     * @param publisherConfigXML Event Publisher Configuration in XML.
     * @throws ConfigurationManagementException Configuration Management Exception.
     */
    void replaceEventPublisherConfiguration(String eventPublisherName, String publisherConfigXML)
            throws ConfigurationManagementException;

    /**
     * This API is used to get all EventPublishers for a specific tenant.
     *
     * @throws ConfigurationManagementException Configuration Management Exception.
     */
    List<ResourceFile> getEventPublishers() throws ConfigurationManagementException;

    /**
     * This API is used to add an EventPublisher for a particular tenant.
     *
     * @param eventPublisherName Event Publisher Name.
     * @param publisherConfigXML Event Publisher Configuration in XML.
     * @throws ConfigurationManagementException Configuration Management Exception.
     */
    void addEventPublisherConfiguration(String eventPublisherName, String publisherConfigXML)
            throws ConfigurationManagementException;

    /**
     * This API is used to update an EventPublisher for a particular tenant.
     *
     * @param eventPublisherName Event Publisher Name.
     * @param publisherConfigXML Event Publisher Configuration in XML.
     * @throws ConfigurationManagementException Configuration Management Exception.
     */
    void updateEventPublisherConfiguration(String eventPublisherName, String publisherConfigXML)
            throws ConfigurationManagementException;


}
