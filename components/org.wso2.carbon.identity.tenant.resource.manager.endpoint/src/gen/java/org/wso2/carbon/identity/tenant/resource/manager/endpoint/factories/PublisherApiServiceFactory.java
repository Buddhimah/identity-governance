package org.wso2.carbon.identity.tenant.resource.manager.endpoint.factories;

import org.wso2.carbon.identity.tenant.resource.manager.endpoint.PublisherApiService;
import org.wso2.carbon.identity.tenant.resource.manager.endpoint.impl.PublisherApiServiceImpl;

public class PublisherApiServiceFactory {

   private final static PublisherApiService service = new PublisherApiServiceImpl();

   public static PublisherApiService getPublisherApi()
   {
      return service;
   }
}
