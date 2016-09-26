package com.letgo.populartvshows.presentation.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerConfigurationComponent;
import com.letgo.populartvshows.app.dependencyinjection.modules.ConfigurationModule;
import com.letgo.populartvshows.presentation.presenters.ConfigurationPresenter;
import com.letgo.populartvshows.presentation.presenters.impl.ConfigurationPresenterImpl;
import com.letgo.populartvshows.presentation.ui.activities.PopularTvShowsActivity;
import com.letgo.populartvshows.utils.NetworkUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author diego.galico
 *
 * Fragment in charge of showing splash screen
 *
 */
public class SplashFragment extends BaseFragment implements ConfigurationPresenter.ConfigurationView {

    @Inject
    ConfigurationPresenterImpl mConfigurationPresenter;

    @Optional
    @InjectView(R.id.progress_bar_splash)
    View mProgressBar;

    @Optional
    @InjectView(R.id.layout_error_splash)
    LinearLayout mLinearLayoutError;

    @Optional
    @InjectView(R.id.text_view_splash_title)
    TextView mSplashTitle;

    @Optional
    @InjectView(R.id.text_view_error_subtitle)
    TextView mErrorSubtitle;

    @Optional
    @InjectView(R.id.button_retry)
    Button mRetry;

    public static SplashFragment newInstance() {
        SplashFragment splashFragment = new SplashFragment();
        return splashFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dependency injection
        initializeDependencyInjector();

        mConfigurationPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.inject(this, view);

        // Retry clicked
        mRetry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Retrieve configuration information
                if (NetworkUtils.hasNetwork(getContext())) {
                    mConfigurationPresenter.start();
                    mLinearLayoutError.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mConfigurationPresenter.start();
    }

    /**
     * Initialize dependency injection
     */
    private void initializeDependencyInjector() {

        PopularTvShowsApplication app = (PopularTvShowsApplication) getActivity().getApplication();

        DaggerConfigurationComponent.builder()
                                    .appComponent(app.getAppComponent())
                                    .configurationModule(new ConfigurationModule())
                                    .build().inject(this);
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
        mErrorSubtitle.setText(message);
        mSplashTitle.setVisibility(View.GONE);
        mLinearLayoutError.setVisibility(View.VISIBLE);
    }

    @Override
    public void startPopularTvShowsActivity() {
        Intent myIntent = new Intent(getActivity(), PopularTvShowsActivity.class);
        startActivity(myIntent);
        getActivity().finish();
    }
}
