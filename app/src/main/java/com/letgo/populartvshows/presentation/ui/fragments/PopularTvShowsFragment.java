package com.letgo.populartvshows.presentation.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerTvShowsComponent;
import com.letgo.populartvshows.app.dependencyinjection.modules.TvShowsModule;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.domain.model.entities.TvShowsWrapper;
import com.letgo.populartvshows.presentation.presenters.PopularTvShowsPresenter;
import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;
import com.letgo.populartvshows.presentation.ui.adapters.PopularTvShowsAdapter;
import com.letgo.populartvshows.utils.RecyclerViewClickListener;

import java.util.List;

import javax.inject.Inject;

/**
 * @author diego.galico
 */
public class PopularTvShowsFragment extends BaseFragment implements
        PopularTvShowsPresenter.PopularTvShowsView, RecyclerViewClickListener {

    private final static String BUNDLE_TV_SHOWS_WRAPPER = "tv_shows_wrapper";
    private PopularTvShowsAdapter mTvShowsAdapter;
    private GridLayoutManager mLinearLayout;
    private RecyclerView mRecycleView;

    @Inject
    PopularTvShowsPresenterImpl mTvShowsPresenter;



    public static PopularTvShowsFragment newInstance() {
        PopularTvShowsFragment popularTvShowsFragment = new PopularTvShowsFragment();
        return popularTvShowsFragment;
    }

    public PopularTvShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeDependencyInjector();

        if (savedInstanceState == null) {
            mTvShowsPresenter.attachView(this);
        } else {
            initializeFromParams(savedInstanceState);
        }
    }

    private void initializeDependencyInjector() {

        PopularTvShowsApplication app = (PopularTvShowsApplication) getActivity().getApplication();

        DaggerTvShowsComponent.builder()
                              .appComponent(app.getAppComponent())
                              .tvShowsModule(new TvShowsModule())
                              .build().inject(this);
    }

    @Override
    public void onStart() {

        super.onStart();
        mTvShowsPresenter.start();
    }

    private void initializeFromParams(Bundle savedInstanceState) {
        TvShowsWrapper tvShowsWrapper = (TvShowsWrapper) savedInstanceState
                .getSerializable(BUNDLE_TV_SHOWS_WRAPPER);
        mTvShowsPresenter.onNext(tvShowsWrapper);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_tv_shows, container, false);

        mLinearLayout = new GridLayoutManager(getActivity(), 2);
        mRecycleView = (RecyclerView) view.findViewById(R.id.recycler_view_popular_tv_shows);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(mLinearLayout);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mTvShowsAdapter != null) {
            outState.putSerializable(BUNDLE_TV_SHOWS_WRAPPER, new TvShowsWrapper(
                    mTvShowsAdapter.getTvShowsList()));
        }
    }

    @Override
    public void showPopularTvShows(List<TvShow> tvShowList) {
        mTvShowsAdapter = new PopularTvShowsAdapter(tvShowList);
        mTvShowsAdapter.setRecyclerListListener(this);

        mRecycleView.setAdapter(mTvShowsAdapter);
    }

    @Override
    public boolean isTheListEmpty() {
        return (mTvShowsAdapter == null) || mTvShowsAdapter.getTvShowsList().isEmpty();
    }

    @Override
    public void appendPopularTvShows(List<TvShow> tvShowList) {

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
    public void onClick(View v, int position, float x, float y) {

    }
}
