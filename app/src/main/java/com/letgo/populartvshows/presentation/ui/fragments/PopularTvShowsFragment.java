package com.letgo.populartvshows.presentation.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.Constants;
import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerTvShowsComponent;
import com.letgo.populartvshows.app.dependencyinjection.modules.TvShowsModule;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.presentation.presenters.PopularTvShowsPresenter;
import com.letgo.populartvshows.presentation.presenters.base.PresenterCache;
import com.letgo.populartvshows.presentation.presenters.base.PresenterFactory;
import com.letgo.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;
import com.letgo.populartvshows.presentation.ui.activities.PopularTvShowsActivity;
import com.letgo.populartvshows.presentation.ui.activities.TvShowDetailActivity;
import com.letgo.populartvshows.presentation.ui.adapters.PopularTvShowsAdapter;
import com.letgo.populartvshows.utils.NetworkUtils;
import com.letgo.populartvshows.utils.RecyclerItemClickListener;
import com.letgo.populartvshows.utils.ViewType;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author diego.galico
 *
 * Fragment in charge of showing popular tv shows
 *
 */
public class PopularTvShowsFragment extends BaseFragment implements PopularTvShowsPresenter.PopularTvShowsView {

    private static final String TV_SHOW_OBJECT = "tv_show_object";

    private boolean loading = true;
    int mPastVisibleItems, mVisibleItemCount, mTotalItemCount;

    private PopularTvShowsAdapter mTvShowsAdapter;
    private GridLayoutManager mLinearLayout;

    private static final String TAG = PopularTvShowsActivity.class.getName();

    private PresenterCache presenterCache =
            PresenterCache.getInstance();

    @Inject
    PopularTvShowsPresenterImpl mTvShowsPresenter;

    @Optional
    @InjectView(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    @Optional
    @InjectView(R.id.progress_bar)
    View mProgressBar;

    @Optional
    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Optional
    @InjectView(R.id.recycler_view_popular_tv_shows)
    RecyclerView mRecyclerView;

    @Optional
    @InjectView(R.id.layout_error)
    LinearLayout mLinearLayoutError;

    @Optional
    @InjectView(R.id.error_subtitle)
    TextView mErrorSubtitle;

    @Optional
    @InjectView(R.id.retry)
    Button mRetry;

    // Retaining presenter during configuration changes
    private PresenterFactory<PopularTvShowsPresenterImpl> presenterFactory =
            new PresenterFactory<PopularTvShowsPresenterImpl>() {
                @NonNull
                @Override
                public PopularTvShowsPresenterImpl createPresenter() {
                    return mTvShowsPresenter;
                }
            };

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

        // Initialize dependency injection
        initializeDependencyInjector();

        mTvShowsPresenter = presenterCache.getPresenter(TAG,
                presenterFactory);

        mTvShowsPresenter.attachView(this);
    }

    /**
     * Initialize dependency injection
     */
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

    @Override
    public void onResume() {
        super.onResume();
        // Show snack bar if no connection
        if (!NetworkUtils.hasNetwork(getContext())) {
            showSnackBar();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_tv_shows, container, false);

        // Inject fragment and view to ButterKnife
        ButterKnife.inject(this, view);

        // Toolbar initialization
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        // Retry clicked
        mRetry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Retrieve tv shows information
                if (NetworkUtils.hasNetwork(getContext())) {
                    mLinearLayoutError.setVisibility(View.GONE);
                    if (isTheListEmpty()) {
                        mTvShowsPresenter.start();
                    }
                }
            }
        });

        mLinearLayout = new GridLayoutManager(getActivity(), Constants.GRID_LAYOUT_COLUMNS);

        // Return span size depending view type item in order to show centered pagination loading
        mLinearLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                int itemViewType = mTvShowsAdapter.getItemViewType(position);

                if (itemViewType == ViewType.VIEW_TYPE_ITEM.getValue()) {
                    return 1;
                } else if (itemViewType == ViewType.VIEW_TYPE_LOADING.getValue()) {
                    return Constants.GRID_LAYOUT_COLUMNS; //number of columns of the grid
                } else {
                    return -1;
                }
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayout);

        // Method called when user scrolls the recycler view
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    mVisibleItemCount = mLinearLayout.getChildCount();
                    mTotalItemCount = mLinearLayout.getItemCount();
                    mPastVisibleItems = mLinearLayout.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((mVisibleItemCount + mPastVisibleItems) >= mTotalItemCount) {
                            loading = false;
                            // Do pagination
                            mTvShowsAdapter.paginationLoading();
                            mTvShowsPresenter.showMoreTvShows();
                        }
                    }
                }
            }
        });

        // Method called when user clicks recycler view item
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        // if Picasso retrieved item image
                        if (mTvShowsAdapter.isTvShowReady(position)) {
                            Intent tvShowDetailActivityIntent = new Intent(
                                    getActivity(), TvShowDetailActivity.class);

                            // Send tvShowObject via intent
                            TvShow tvShowObject = mTvShowsAdapter.getTvShowsList().get(position);
                            tvShowDetailActivityIntent.putExtra(TV_SHOW_OBJECT, new Gson().toJson(tvShowObject));

                            getActivity().startActivity(tvShowDetailActivityIntent);

                            /// Left to right transition animation when activity started
                            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );

        // Method called when user pull to refresh
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // Update tv show list
                        updateTvShowsList();
                    }
                }
        );

        return view;
    }

    /**
     * Update tv show list
     */
    private void updateTvShowsList() {
        mTvShowsPresenter.updateTvShowsList();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showPopularTvShows(List<TvShow> tvShowList) {
        mSwipeRefreshLayout.setRefreshing(false);
        mTvShowsAdapter = new PopularTvShowsAdapter(tvShowList);
        mRecyclerView.setAdapter(mTvShowsAdapter);
        mLinearLayoutError.setVisibility(View.GONE);
    }

    @Override
    public boolean isTheListEmpty() {
        return (mTvShowsAdapter == null) || mTvShowsAdapter.getTvShowsList().isEmpty();
    }

    @Override
    public void appendPopularTvShows(List<TvShow> tvShowList) {
        loading = true;
        mTvShowsAdapter.appendTvShows(tvShowList);
        mLinearLayoutError.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        if (!loading) {
            mTvShowsAdapter.removePaginationLoading();
            loading = true;
        } else {
            mErrorSubtitle.setText(message);
            mLinearLayoutError.setVisibility(View.VISIBLE);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * Show snack bar
     */
    private void showSnackBar() {
        Snackbar snack = Snackbar.make(mRecyclerView, getResources().getString(R.string.snack_bar_message), Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        snack.show();
    }

}
