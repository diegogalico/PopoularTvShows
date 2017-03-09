/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.populartvshows.domain.mapper;

import com.android.populartvshows.data.entities.TvShowEntity;
import com.android.populartvshows.data.entities.TvShowsWrapperEntity;
import com.android.populartvshows.domain.TvShowData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link TvShowsWrapperEntity} (in the data layer) to {@link List<TvShowData>} in the
 * domain layer.
 */
@Singleton
public class TvShowEntityDataMapper {

    @Inject
    public TvShowEntityDataMapper() {
    }

    public TvShowData transform(TvShowEntity tvShowEntity) {
        TvShowData tvShowData = null;
        if (tvShowEntity != null) {
            tvShowData = new TvShowData();
            tvShowData.setPosterPath(tvShowEntity.getPosterPath());
            tvShowData.setBackdropPath(tvShowEntity.getBackdropPath());
            tvShowData.setFirstAirDate(tvShowEntity.getFirstAirDate());
            tvShowData.setId(tvShowEntity.getId());
            tvShowData.setOverview(tvShowEntity.getOverview());
            tvShowData.setVoteAverage(tvShowEntity.getVoteAverage());
            tvShowData.setName(tvShowEntity.getName());
        }

        return tvShowData;
    }

    /**
     * Transform a {@link TvShowsWrapperEntity} into an {@link List<TvShowData>}.
     *
     * @param tvShowsWrapperEntity Object to be transformed.
     * @return {@link List<TvShowData>} if valid {@link TvShowsWrapperEntity} otherwise null.
     */
    public List<TvShowData> transform(TvShowsWrapperEntity tvShowsWrapperEntity) {
        List<TvShowData> tvShowList = new ArrayList<>();
        TvShowData tvShow;
        for (TvShowEntity tvShowEntity : tvShowsWrapperEntity.getTvShowInfo()) {
            tvShow = transform(tvShowEntity);
            if (tvShow != null) {
                tvShowList.add(tvShow);
            }
        }
        return tvShowList;
    }
}
