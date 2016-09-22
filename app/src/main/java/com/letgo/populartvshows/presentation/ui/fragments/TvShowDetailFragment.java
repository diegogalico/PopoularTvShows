package com.letgo.populartvshows.presentation.ui.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerSimilarTvShowsComponent;
import com.letgo.populartvshows.app.dependencyinjection.modules.SimilarTvShowsModule;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.SimilarTvShowsPresenter;
import com.letgo.populartvshows.presentation.presenters.impl.SimilarTvShowsPresenterImpl;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author diego.galico
 */
public class TvShowDetailFragment extends BaseFragment implements SimilarTvShowsPresenter.SimilarTvShowsView {


    private int mTvShowId;
    private static final String TV_SHOW_ID = "tv_show_id";

    @Inject
    SimilarTvShowsPresenterImpl mSimilarTvShowsPresenter;

    @Optional
    @InjectView(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    public static TvShowDetailFragment newInstance() {
        TvShowDetailFragment tvShowDetailFragment = new TvShowDetailFragment();
        return tvShowDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        mTvShowId = bundle.getInt(TV_SHOW_ID, 0);

        initializeDependencyInjector();
        if (savedInstanceState == null) {
            mSimilarTvShowsPresenter.attachView(this);
        } else {
            //initializeFromParams(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //mSimilarTvShowsPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show_detail, container, false);
        ButterKnife.inject(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                getActivity().onBackPressed();
            }
        });
        return view;

    }

    private void initializeDependencyInjector() {

        PopularTvShowsApplication app = (PopularTvShowsApplication) getActivity().getApplication();

        DaggerSimilarTvShowsComponent.builder()
                                     .appComponent(app.getAppComponent())
                                     .similarTvShowsModule(new SimilarTvShowsModule(mTvShowId))
                                     .build().inject(this);
    }

    @Override
    public void showSimilarTvShows(List<TvShow> similarTvShowList) {

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
}
