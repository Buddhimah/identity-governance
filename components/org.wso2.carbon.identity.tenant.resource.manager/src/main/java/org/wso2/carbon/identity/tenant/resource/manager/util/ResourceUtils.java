package org.wso2.carbon.identity.tenant.resource.manager.util;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.tenant.resource.manager.constants.TenantResourceConstants;
import org.wso2.carbon.identity.tenant.resource.manager.exception.TenantResourceManagementClientException;
import org.wso2.carbon.identity.tenant.resource.manager.exception.TenantResourceManagementServerException;

/**
 * Utility methods for tenant resource management.
 */
public class ResourceUtils {

    /**
     * This method can be used to generate a TenantResourceManagementClientException from
     * ConfigurationConstants.ErrorMessages object when no exception is thrown.
     *
     * @param error TenantResourceConstants.ErrorMessages.
     * @param data  data to replace if message needs to be replaced.
     * @return TenantResourceManagementClientException.
     */
    public static TenantResourceManagementClientException handleClientException(TenantResourceConstants.ErrorMessages error,
            String data) {

        String message = populateMessageWithData(error, data);
        return new TenantResourceManagementClientException(message, error.getCode());
    }

    public static TenantResourceManagementClientException handleClientException(TenantResourceConstants.ErrorMessages error,
            String data, Throwable e) {

        String message = populateMessageWithData(error, data);
        return new TenantResourceManagementClientException(message, error.getCode(), e);
    }

    /**
     * This method can be used to generate a TenantResourceManagementServerException from
     * TenantResourceConstants.ErrorMessages object when no exception is thrown.
     *
     * @param error TenantResourceConstants.ErrorMessages.
     * @param data  data to replace if message needs to be replaced.
     * @return TenantResourceManagementServerException.
     */
    public static TenantResourceManagementServerException handleServerException(TenantResourceConstants.ErrorMessages error,
            String data) {

        String message = populateMessageWithData(error, data);
        return new TenantResourceManagementServerException(message, error.getCode());
    }

    public static TenantResourceManagementServerException handleServerException(TenantResourceConstants.ErrorMessages error,
            String data, Throwable e) {

        String message = populateMessageWithData(error, data);
        return new TenantResourceManagementServerException(message, error.getCode(), e);
    }

    private static String populateMessageWithData(TenantResourceConstants.ErrorMessages error, String data) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getMessage(), data);
        } else {
            message = error.getMessage();
        }
        return message;
    }
}
