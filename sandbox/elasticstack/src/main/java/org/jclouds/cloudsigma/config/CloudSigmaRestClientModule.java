/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.cloudsigma.config;

import java.util.Map;

import org.jclouds.cloudsigma.CloudSigmaAsyncClient;
import org.jclouds.cloudsigma.CloudSigmaClient;
import org.jclouds.cloudsigma.functions.CreateDriveRequestToMap;
import org.jclouds.cloudsigma.functions.DriveDataToMap;
import org.jclouds.elasticstack.domain.CreateDriveRequest;
import org.jclouds.elasticstack.domain.DriveData;
import org.jclouds.elasticstack.handlers.ElasticStackErrorHandler;
import org.jclouds.http.HttpErrorHandler;
import org.jclouds.http.RequiresHttp;
import org.jclouds.http.annotation.ClientError;
import org.jclouds.http.annotation.Redirection;
import org.jclouds.http.annotation.ServerError;
import org.jclouds.rest.ConfiguresRestClient;
import org.jclouds.rest.config.RestClientModule;

import com.google.common.base.Function;
import com.google.inject.TypeLiteral;

/**
 * Configures the CloudSigma connection.
 * 
 * @author Adrian Cole
 */
@RequiresHttp
@ConfiguresRestClient
public class CloudSigmaRestClientModule extends RestClientModule<CloudSigmaClient, CloudSigmaAsyncClient> {

   public CloudSigmaRestClientModule() {
      super(CloudSigmaClient.class, CloudSigmaAsyncClient.class);
   }

   @Override
   protected void bindErrorHandlers() {
      bind(HttpErrorHandler.class).annotatedWith(Redirection.class).to(ElasticStackErrorHandler.class);
      bind(HttpErrorHandler.class).annotatedWith(ClientError.class).to(ElasticStackErrorHandler.class);
      bind(HttpErrorHandler.class).annotatedWith(ServerError.class).to(ElasticStackErrorHandler.class);
   }

   @Override
   protected void configure() {
      super.configure();
      bind(new TypeLiteral<Function<CreateDriveRequest, Map<String, String>>>() {
      }).to(CreateDriveRequestToMap.class);
      bind(new TypeLiteral<Function<DriveData, Map<String, String>>>() {
      }).to(DriveDataToMap.class);
   }

   @Override
   protected void bindRetryHandlers() {
      // TODO
   }

}
