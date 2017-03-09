package com.android.populartvshows.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.android.populartvshows.R;
import com.android.populartvshows.presentation.app.PopularTvShowsApplication;
import com.android.populartvshows.presentation.app.dependencyinjection.components.DaggerTvShowsComponent;
import com.android.populartvshows.presentation.app.dependencyinjection.modules.TvShowsModule;
import com.android.populartvshows.presentation.models.TvShowModel;
import com.android.populartvshows.presentation.presenters.PopularTvShowsPresenter;
import com.android.populartvshows.presentation.presenters.base.PresenterCache;
import com.android.populartvshows.presentation.presenters.base.PresenterFactory;
import com.android.populartvshows.presentation.presenters.impl.PopularTvShowsPresenterImpl;
import com.android.populartvshows.presentation.ui.activities.PopularTvShowsActivity;
import com.android.populartvshows.presentation.ui.adapters.PopularTvShowsAdapter;
import com.android.populartvshows.presentation.utils.NetworkUtils;
import com.android.populartvshows.presentation.utils.RecyclerItemClickListener;
import com.android.populartvshows.presentation.utils.StringUtils;
import com.android.populartvshows.presentation.utils.ViewType;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author diego.galico
 *
 * Fragment in charge of showing popular tv shows
 */
public class PopularTvShowsFragment extends BaseFragment implements PopularTvShowsPresenter.PopularTvShowsView {

    public interface OnTvShowListener {
        void onTvShowClicked(TvShowModel tvShow);
    }

    private OnTvShowListener onTvShowListener;

    private boolean mLoading = true;
    int mPastVisibleItems, mVisibleItemCount, mTotalItemCount;

    private PopularTvShowsAdapter mTvShowsAdapter;
    private GridLayoutManager mLinearLayout;

    private static final String TAG = PopularTvShowsActivity.class.getName();

    private PresenterCache presenterCache =
            PresenterCache.getInstance();

    @Inject
    PopularTvShowsPresenterImpl mTvShowsPresenter;

    @InjectView(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    @InjectView(R.id.progress_bar)
    View mProgressBar;

    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @InjectView(R.id.recycler_view_popular_tv_shows)
    RecyclerView mRecyclerView;

    @InjectView(R.id.layout_error)
    LinearLayout mLinearLayoutError;

    @InjectView(R.id.text_view_error_subtitle)
    TextView mErrorSubtitle;

    @InjectView(R.id.button_retry)
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

        if (savedInstanceState == null) {
            mTvShowsPresenter.attachView(this);
        }
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            onTvShowListener = (OnTvShowListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement OnTvShowListener");
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        mTvShowsPresenter.attachView(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterCache.removePresenter(TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_tv_shows, container, false);

        // Inject fragment and view to ButterKnife
        ButterKnife.inject(this, view);

        // Toolbar initialization
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(StringUtils.EMPTY_STRING);

        // Retry clicked
        mRetry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Retrieve tv shows information
                mLinearLayoutError.setVisibility(View.GONE);
                if (isTheListEmpty()) {
                    mTvShowsPresenter.start();
                }

            }
        });

        mLinearLayout = new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.tv_shows_grid_view_num_colums));

        // Return span size depending view type item in order to show centered pagination loading
        mLinearLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                int itemViewType = mTvShowsAdapter.getItemViewType(position);

                if (itemViewType == ViewType.VIEW_TYPE_ITEM.getValue()) {
                    return 1;
                } else if (itemViewType == ViewType.VIEW_TYPE_LOADING.getValue()) {
                    return getResources().getInteger(R.integer.tv_shows_grid_view_num_colums); //number of columns of the grid
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

                    if (mLoading) {
                        if ((mVisibleItemCount + mPastVisibleItems) >= mTotalItemCount) {
                            mLoading = false;
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
                            TvShowModel tvShowObject = mTvShowsAdapter.getTvShowsList().get(position);
                            onTvShowListener.onTvShowClicked(tvShowObject);
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
    public void showPopularTvShows(List<TvShowModel> tvShowList) {
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
    public void appendPopularTvShows(List<TvShowModel> tvShowList) {
        mLoading = true;
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
        if (!mLoading) {
            mTvShowsAdapter.removePaginationLoading();
            mLoading = true;
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

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

}
