package org.wso2.carbon.identity.tenant.resource.manager.endpoint.impl;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.tenant.resource.manager.endpoint.ApiResponseMessage;
import org.wso2.carbon.identity.tenant.resource.manager.endpoint.PublisherApiService;
import org.wso2.carbon.identity.tenant.resource.manager.endpoint.util.ResourceManagerEndpointUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.core.Response;

public class PublisherApiServiceImpl extends PublisherApiService {

    @Override
    public Response publisherPost(InputStream resourceFileInputStream, Attachment resourceFileDetail, String fileName) {
        // do some magic!

        try {
            ResourceManagerEndpointUtil.getResourceManager()
                    .addEventPublisherConfiguration(fileName, convert(resourceFileInputStream));
        } catch (ConfigurationManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    private String convert(InputStream inputStream) throws IOException {

        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }
}
