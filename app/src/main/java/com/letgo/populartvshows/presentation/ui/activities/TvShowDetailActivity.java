package com.letgo.populartvshows.presentation.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerSimilarTvShowsComponent;
import com.letgo.populartvshows.app.dependencyinjection.modules.SimilarTvShowsModule;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.SimilarTvShowsPresenter;
import com.letgo.populartvshows.presentation.presenters.impl.SimilarTvShowsPresenterImpl;
import com.letgo.populartvshows.presentation.ui.adapters.SimilarTvShowsAdapter;
import com.letgo.populartvshows.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author diego.galico
 */
public class TvShowDetailActivity extends SecondLevelActivity implements SimilarTvShowsPresenter.SimilarTvShowsView, ViewPager.OnPageChangeListener {

    private static final String TV_SHOW_OBJECT = "tv_show_object";
    private static final String TV_SHOW_ID = "tv_show_id";
    private TvShow mTvShow;
    private int mTvShowId;
    private List<TvShow> mSimilarTvShowList = new ArrayList<>();

    private SimilarTvShowsAdapter mAdapter;

    @Inject
    SimilarTvShowsPresenterImpl mSimilarTvShowsPresenter;

    @Optional
    @InjectView(R.id.pager)
    ViewPager mPager;

    @Optional
    @InjectView(R.id.toolbar_detail)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String jsonMyObject = StringUtils.EMPTY_STRING;
        if (extras != null) {
            mTvShowId = extras.getInt(TV_SHOW_ID, 0);
            jsonMyObject = extras.getString(TV_SHOW_OBJECT);
        }
        mTvShow = new Gson().fromJson(jsonMyObject, TvShow.class);
        mSimilarTvShowList.add(mTvShow);
        initializeDependencyInjector();
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            mSimilarTvShowsPresenter.attachView(this);
        } else {
            //initializeFromParams(savedInstanceState);
        }

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
        setTitle(mTvShow.getName());
        mAdapter = new SimilarTvShowsAdapter(getSupportFragmentManager(), mTvShow);
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(this);

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
        mSimilarTvShowList.addAll(similarTvShowList);
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTitle(mSimilarTvShowList.get(position).getName());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
