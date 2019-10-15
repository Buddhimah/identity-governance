package org.wso2.carbon.identity.tenant.resource.manager.endpoint.util;

import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.tenant.resource.manager.core.ResourceManager;

public class ResourceManagerEndpointUtil {

    public static ResourceManager getResourceManager() {

        return (ResourceManager) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                .getOSGiService(ResourceManager.class, null);
    }
}
