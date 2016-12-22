package com.dashlane.populartvshows.domain.interactors.impl;

import com.dashlane.populartvshows.data.entities.TvShowsWrapperEntity;
import com.dashlane.populartvshows.data.rest.RestData;
import com.dashlane.populartvshows.domain.executors.PostExecutionThread;
import com.dashlane.populartvshows.domain.executors.ThreadExecutor;
import com.dashlane.populartvshows.domain.TvShowData;
import com.dashlane.populartvshows.domain.interactors.TvShowsInteractor;
import com.dashlane.populartvshows.domain.mapper.TvShowEntityDataMapper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author diego.galico
 *
 * TvShowsInteractorImpl class is in charge of calling {@link RestData} to obtain popular tv shows response
 */
public class TvShowsInteractorImpl extends TvShowsInteractor {

    private int mCurrentPage = 1;
    private final RestData mRestData;
    private final TvShowEntityDataMapper mTvShowEntityDataMapper;

    @Inject
    public TvShowsInteractorImpl(RestData mRestData, ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread,
                                 TvShowEntityDataMapper tvShowEntityDataMapper) {

        super(threadExecutor, postExecutionThread);
        this.mRestData = mRestData;
        mTvShowEntityDataMapper = tvShowEntityDataMapper;
    }

    @Override
    public void setPage(int page) {
        mCurrentPage = page;
    }

    @Override
    public void addPageNumber() {
        mCurrentPage++;
    }

    @Override
    protected Observable<List<TvShowData>> buildInteractorObservable() {
        return mRestData.getTvShowsByPage(mCurrentPage).map(new Func1<TvShowsWrapperEntity, List<TvShowData>>() {
            @Override public List<TvShowData> call(TvShowsWrapperEntity tvShowsWrapperEntity) {
                return mTvShowEntityDataMapper.transform(tvShowsWrapperEntity);
            }
        });
    }
}
