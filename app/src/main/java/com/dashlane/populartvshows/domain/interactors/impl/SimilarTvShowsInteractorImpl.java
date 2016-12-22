package com.dashlane.populartvshows.domain.interactors.impl;

import com.dashlane.populartvshows.data.entities.TvShowsWrapperEntity;
import com.dashlane.populartvshows.data.rest.RestData;
import com.dashlane.populartvshows.domain.TvShowData;
import com.dashlane.populartvshows.domain.executors.PostExecutionThread;
import com.dashlane.populartvshows.domain.executors.ThreadExecutor;
import com.dashlane.populartvshows.domain.interactors.SimilarTvShowsInteractor;
import com.dashlane.populartvshows.domain.mapper.TvShowEntityDataMapper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author diego.galico
 *
 * SimilarTvShowsInteractorImpl class is in charge of calling {@link RestData} to obtain similar tv shows response
 *
 */
public class SimilarTvShowsInteractorImpl extends SimilarTvShowsInteractor {

    private final RestData mRestData;
    private final int mTvShowId;
    private final TvShowEntityDataMapper mTvShowEntityDataMapper;

    @Inject
    public SimilarTvShowsInteractorImpl(RestData mRestData, int tvShowId, ThreadExecutor threadExecutor,
                                        PostExecutionThread postExecutionThread,
                                        TvShowEntityDataMapper tvShowEntityDataMapper) {
        super(threadExecutor, postExecutionThread);
        this.mRestData = mRestData;
        this.mTvShowId = tvShowId;
        mTvShowEntityDataMapper = tvShowEntityDataMapper;
    }

    @Override
    protected Observable<List<TvShowData>> buildInteractorObservable() {
        return mRestData.getSimilarTvShows(mTvShowId).map(new Func1<TvShowsWrapperEntity, List<TvShowData>>() {
            @Override public List<TvShowData> call(TvShowsWrapperEntity tvShowsWrapperEntity) {
                return mTvShowEntityDataMapper.transform(tvShowsWrapperEntity);
            }
        });
    }
}
