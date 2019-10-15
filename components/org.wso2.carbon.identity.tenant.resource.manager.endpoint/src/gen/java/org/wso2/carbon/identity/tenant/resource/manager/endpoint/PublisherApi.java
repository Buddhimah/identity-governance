package org.wso2.carbon.identity.tenant.resource.manager.endpoint;

import org.wso2.carbon.identity.tenant.resource.manager.endpoint.dto.*;
import org.wso2.carbon.identity.tenant.resource.manager.endpoint.PublisherApiService;
import org.wso2.carbon.identity.tenant.resource.manager.endpoint.factories.PublisherApiServiceFactory;

import io.swagger.annotations.ApiParam;

import org.wso2.carbon.identity.tenant.resource.manager.endpoint.dto.ErrorDTO;
import java.io.File;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/publisher")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(value = "/publisher", description = "the publisher API")
public class PublisherApi  {

   private final PublisherApiService delegate = PublisherApiServiceFactory.getPublisherApi();

    @POST
    
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create a publisher\n", notes = "This API is used to deploy a publisher given by the user.\n", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response publisherPost(@ApiParam(value = "This represents the corresponding publisher file that needs to be added.") @Multipart(value = "resourceFile", required = false) InputStream resourceFileInputStream,
    @ApiParam(value = "This represents the corresponding publisher file that needs to be added. : details") @Multipart(value = "resourceFile" , required = false) Attachment resourceFileDetail,
    @ApiParam(value = "This represents the corresponding publisher file name that needs to be added." )@Multipart(value = "fileName", required = false)  String fileName)
    {
    return delegate.publisherPost(resourceFileInputStream,resourceFileDetail,fileName);
    }
}

