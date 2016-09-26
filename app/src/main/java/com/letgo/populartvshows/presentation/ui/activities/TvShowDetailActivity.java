package com.letgo.populartvshows.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
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
 *
 * Activity in charge of handling similar tv shows pager
 *
 */
public class TvShowDetailActivity extends SecondLevelActivity implements SimilarTvShowsPresenter.SimilarTvShowsView, ViewPager.OnPageChangeListener {

    private static final String TV_SHOW_OBJECT = "tv_show_object";
    private TvShow mTvShow;
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

        // Get TvShow from extra bundle
        String jsonMyObject = StringUtils.EMPTY_STRING;
        if (extras != null) {
            jsonMyObject = extras.getString(TV_SHOW_OBJECT);
        }

        // Convert json object to TvShow object
        mTvShow = new Gson().fromJson(jsonMyObject, TvShow.class);

        mSimilarTvShowList.add(mTvShow);

        // Initialize dependency injection
        initializeDependencyInjector();

        // Inject activity to ButterKnife
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            mSimilarTvShowsPresenter.attachView(this);
        }

        // Toolbar initialization
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));

        //Toolbar back button click
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setTitle(mTvShow.getName());
        mAdapter = new SimilarTvShowsAdapter(getSupportFragmentManager(), mTvShow);

        // Set adapter with selected tv show
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(this);
    }

    /**
     * Right to left transition animation when back pressed
     */
    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
        // Right to left transition animation when activity finished
        overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
    }

    @Override
    public void onStart() {
        super.onStart();
        mSimilarTvShowsPresenter.start();
    }

    /**
     * Initialize dependency injection
     */
    private void initializeDependencyInjector() {

        PopularTvShowsApplication app = (PopularTvShowsApplication) getApplication();

        DaggerSimilarTvShowsComponent.builder()
                                     .appComponent(app.getAppComponent())
                                     .similarTvShowsModule(new SimilarTvShowsModule(mTvShow.getId()))
                                     .build().inject(this);
    }

    /**
     * Set similar tv shows to adapter
     * @param similarTvShowList
     */
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

    /**
     * Set toolbar title depending pager position
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        setTitle(mSimilarTvShowList.get(position).getName());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
