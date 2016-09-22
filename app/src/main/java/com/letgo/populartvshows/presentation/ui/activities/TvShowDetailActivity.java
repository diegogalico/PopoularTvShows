package com.letgo.populartvshows.presentation.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerSimilarTvShowsComponent;
import com.letgo.populartvshows.app.dependencyinjection.modules.SimilarTvShowsModule;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.SimilarTvShowsPresenter;
import com.letgo.populartvshows.presentation.presenters.impl.SimilarTvShowsPresenterImpl;
import com.letgo.populartvshows.presentation.ui.adapters.SimilarTvShowsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author diego.galico
 */
public class TvShowDetailActivity extends SecondLevelActivity implements SimilarTvShowsPresenter.SimilarTvShowsView {

    private static final String TV_SHOW_ID = "tv_show_id";
    private int mTvShowId;

    private SimilarTvShowsAdapter mAdapter;

    @Inject
    SimilarTvShowsPresenterImpl mSimilarTvShowsPresenter;

    @Optional
    @InjectView(R.id.pager)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTvShowId = getIntent().getExtras().getInt(TV_SHOW_ID, 0);
        initializeDependencyInjector();
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            mSimilarTvShowsPresenter.attachView(this);
        } else {
            //initializeFromParams(savedInstanceState);
        }

        mAdapter = new SimilarTvShowsAdapter(getSupportFragmentManager());

    }

    @Override
    public void onStart() {
        super.onStart();
        mSimilarTvShowsPresenter.start();
    }

    private void initializeDependencyInjector() {

        PopularTvShowsApplication app = (PopularTvShowsApplication) getApplication();

        DaggerSimilarTvShowsComponent.builder()
                                     .appComponent(app.getAppComponent())
                                     .similarTvShowsModule(new SimilarTvShowsModule(mTvShowId))
                                     .build().inject(this);
    }

    @Override
    public void showSimilarTvShows(List<TvShow> similarTvShowList) {
        mAdapter.setSimilarTvShowsCount(similarTvShowList.size());
        mAdapter.setSimilarTvShows(similarTvShowList);
        mPager.setAdapter(mAdapter);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_similar_tv_shows_pager;
    }

}
