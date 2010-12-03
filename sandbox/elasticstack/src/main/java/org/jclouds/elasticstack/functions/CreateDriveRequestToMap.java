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

package org.jclouds.elasticstack.functions;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jclouds.elasticstack.domain.CreateDriveRequest;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

/**
 * 
 * @author Adrian Cole
 */
@Singleton
public class CreateDriveRequestToMap implements Function<CreateDriveRequest, Map<String, String>> {
   private final BaseDriveToMap baseDriveToMap;

   @Inject
   public CreateDriveRequestToMap(BaseDriveToMap baseDriveToMap) {
      this.baseDriveToMap = baseDriveToMap;
   }

   @Override
   public Map<String, String> apply(CreateDriveRequest from) {
      ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
      builder.putAll(baseDriveToMap.apply(from));
      if (from.getAvoid().size() != 0)
         builder.put("avoid", Joiner.on(' ').join(from.getAvoid()));
      if (from.getEncryptionCipher() != null)
         builder.put("encryption:cipher", from.getEncryptionCipher());
      return builder.build();
   }
}