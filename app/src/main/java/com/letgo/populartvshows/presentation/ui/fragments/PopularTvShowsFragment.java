package com.letgo.populartvshows.presentation.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerTvShowsComponent;
import com.letgo.populartvshows.app.dependencyinjection.modules.TvShowsModule;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.domain.model.entities.TvShowsWrapper;
import com.letgo.populartvshows.presentation.presenters.PopularTvShowsPresenter;
import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;
import com.letgo.populartvshows.presentation.ui.activities.TvShowDetailActivity;
import com.letgo.populartvshows.presentation.ui.adapters.PopularTvShowsAdapter;
import com.letgo.populartvshows.utils.RecyclerItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author diego.galico
 */
public class PopularTvShowsFragment extends BaseFragment implements
        PopularTvShowsPresenter.PopularTvShowsView {

    private final static String BUNDLE_TV_SHOWS_WRAPPER = "tv_shows_wrapper";
    private PopularTvShowsAdapter mTvShowsAdapter;
    private GridLayoutManager mLinearLayout;
    boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private static final String TV_SHOW_OBJECT = "tv_show_object";
    private static final String TV_SHOW_ID = "tv_show_id";

    @Inject
    PopularTvShowsPresenterImpl mTvShowsPresenter;

    @Optional
    @InjectView(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    @Optional
    @InjectView(R.id.recycler_view_popular_tv_shows)
    RecyclerView mRecyclerView;

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
        ButterKnife.inject(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        mLinearLayout = new GridLayoutManager(getActivity(), 2);

        mLinearLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mTvShowsAdapter.getItemViewType(position)) {
                    case 0:
                        return 1;
                    case 1:
                        return 2; //number of columns of the grid
                    default:
                        return -1;
                }
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayout);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = mLinearLayout.getChildCount();
                    totalItemCount = mLinearLayout.getItemCount();
                    pastVisiblesItems = mLinearLayout.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            //Do pagination
                            mTvShowsAdapter.loadMore();
                            mTvShowsPresenter.showMoreTvShows();
                        }
                    }
                }
            }
        });

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Intent tvShowDetailActivityIntent = new Intent(
                                getActivity(), TvShowDetailActivity.class);

                        TvShow tvShowObject = mTvShowsAdapter.getTvShowsList().get(position);
                        int tvShowID = mTvShowsAdapter.getTvShowsList().get(position).getId();
                        tvShowDetailActivityIntent.putExtra(TV_SHOW_OBJECT, new Gson().toJson(tvShowObject));
                        tvShowDetailActivityIntent.putExtra(TV_SHOW_ID, tvShowID);
                        getActivity().startActivity(tvShowDetailActivityIntent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mTvShowsAdapter != null) {
            /*outState.putSerializable(BUNDLE_TV_SHOWS_WRAPPER, new TvShowsWrapper(
                    mTvShowsAdapter.getTvShowsList()));*/
        }
    }

    @Override
    public void showPopularTvShows(List<TvShow> tvShowList) {
        mTvShowsAdapter = new PopularTvShowsAdapter(tvShowList);
        mRecyclerView.setAdapter(mTvShowsAdapter);
    }

    @Override
    public boolean isTheListEmpty() {
        return (mTvShowsAdapter == null) || mTvShowsAdapter.getTvShowsList().isEmpty();
    }

    @Override
    public void appendPopularTvShows(List<TvShow> tvShowList) {
        loading = true;
        mTvShowsAdapter.appendTvShows(tvShowList);
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
