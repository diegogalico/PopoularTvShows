/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.populartvshows.domain.mapper;

import com.android.populartvshows.data.entities.ConfigurationEntity;
import com.android.populartvshows.domain.ConfigurationData;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ConfigurationEntity} (in the data layer) to {@link com.android.populartvshows.domain.ConfigurationData} in the
 * domain layer.
 */
@Singleton
public class ConfigurationEntityDataMapper {

  @Inject
  public ConfigurationEntityDataMapper() {}

  /**
   * Transform a {@link ConfigurationEntity} into an {@link ConfigurationData}.
   *
   * @param userEntity Object to be transformed.
   * @return {@link ConfigurationData} if valid {@link ConfigurationEntity} otherwise null.
   */
  public ConfigurationData transform(ConfigurationEntity userEntity) {
    ConfigurationData configurationData = null;
    if (userEntity != null) {
      configurationData = new ConfigurationData();
      configurationData.setBackdropSizes(userEntity.getImages().getBackdropSizes());
      configurationData.setPosterSizes(userEntity.getImages().getPosterSizes());
      configurationData.setBaseUrl(userEntity.getImages().getBaseUrl());
    }

    return configurationData;
  }
}
