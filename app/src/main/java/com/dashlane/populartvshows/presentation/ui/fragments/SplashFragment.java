package com.dashlane.populartvshows.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dashlane.populartvshows.R;
import com.dashlane.populartvshows.presentation.app.PopularTvShowsApplication;
import com.dashlane.populartvshows.presentation.app.dependencyinjection.components.DaggerConfigurationComponent;
import com.dashlane.populartvshows.presentation.app.dependencyinjection.modules.ConfigurationModule;
import com.dashlane.populartvshows.presentation.presenters.ConfigurationPresenter;
import com.dashlane.populartvshows.presentation.presenters.impl.ConfigurationPresenterImpl;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author diego.galico
 *
 * Fragment in charge of showing splash screen
 */
public class SplashFragment extends BaseFragment implements ConfigurationPresenter.ConfigurationView {

    public interface NavigationHandler {
        void navigateToTvShowActivity();
    }

    private NavigationHandler navigationHandler;

    @Inject
    ConfigurationPresenterImpl mConfigurationPresenter;

    @InjectView(R.id.progress_bar_splash)
    View mProgressBar;

    @InjectView(R.id.layout_error_splash)
    LinearLayout mLinearLayoutError;

    @InjectView(R.id.text_view_splash_title)
    TextView mSplashTitle;

    @InjectView(R.id.text_view_error_subtitle)
    TextView mErrorSubtitle;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.inject(this, view);

        // Retry clicked
        mRetry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Retrieve configuration information
                mConfigurationPresenter.start();
                mLinearLayoutError.setVisibility(View.GONE);

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            navigationHandler = (NavigationHandler) getActivity();
        } catch (ClassCastException ex) {
            throw new ClassCastException(getActivity().toString() + " must implement navigateToTvShowActivity");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mConfigurationPresenter.attachView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mConfigurationPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mConfigurationPresenter.attachView(null);
    }

    @Override public Context getContext() {
        return getActivity().getApplicationContext();
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
        navigationHandler.navigateToTvShowActivity();
    }
}
