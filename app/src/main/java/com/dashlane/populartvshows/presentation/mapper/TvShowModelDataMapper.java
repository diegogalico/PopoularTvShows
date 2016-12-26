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
package com.dashlane.populartvshows.presentation.mapper;

import com.dashlane.populartvshows.data.entities.TvShowsWrapperEntity;
import com.dashlane.populartvshows.domain.TvShowData;
import com.dashlane.populartvshows.presentation.models.TvShowModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link TvShowsWrapperEntity} (in the data layer) to {@link List<TvShowData>} in the
 * domain layer.
 */
@Singleton
public class TvShowModelDataMapper {

    @Inject
    public TvShowModelDataMapper() {
    }

    public TvShowModel transform(TvShowData tvShowData) {
        TvShowModel tvShowModel = null;
        if (tvShowData != null) {
            tvShowModel = new TvShowModel();
            tvShowModel.setPosterPath(tvShowData.getPosterPath());
            tvShowModel.setBackdropPath(tvShowData.getBackdropPath());
            tvShowModel.setFirstAirDate(tvShowData.getFirstAirDate());
            tvShowModel.setId(tvShowData.getId());
            tvShowModel.setOverview(tvShowData.getOverview());
            tvShowModel.setVoteAverage(tvShowData.getVoteAverage());
            tvShowModel.setName(tvShowData.getName());
        }

        return tvShowModel;
    }

    /**
     * Transform a {@link TvShowsWrapperEntity} into an {@link List<TvShowData>}.
     *
     * @param listTvShowData Object to be transformed.
     * @return {@link List<TvShowModel>} if valid {@link List<TvShowData>} otherwise null.
     */
    public List<TvShowModel> transform(List<TvShowData> listTvShowData) {
        List<TvShowModel> tvShowList = new ArrayList<>();
        TvShowModel tvShow;
        for (TvShowData tvShowData : listTvShowData) {
            tvShow = transform(tvShowData);
            if (tvShow != null) {
                tvShowList.add(tvShow);
            }
        }
        return tvShowList;
    }
}
