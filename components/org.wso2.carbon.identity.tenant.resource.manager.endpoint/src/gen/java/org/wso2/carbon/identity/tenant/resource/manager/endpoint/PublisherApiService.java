package org.wso2.carbon.identity.tenant.resource.manager.endpoint;

import org.wso2.carbon.identity.tenant.resource.manager.endpoint.*;
import org.wso2.carbon.identity.tenant.resource.manager.endpoint.dto.*;

import org.wso2.carbon.identity.tenant.resource.manager.endpoint.dto.ErrorDTO;
import java.io.File;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class PublisherApiService {
    public abstract Response publisherPost(InputStream resourceFileInputStream,Attachment resourceFileDetail,String fileName);
}

