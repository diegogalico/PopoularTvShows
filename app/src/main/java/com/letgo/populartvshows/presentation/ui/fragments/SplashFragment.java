package com.letgo.populartvshows.presentation.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.PopularTvShowsApplication;
import com.letgo.populartvshows.app.dependencyinjection.components.DaggerConfigurationComponent;
import com.letgo.populartvshows.app.dependencyinjection.modules.ConfigurationModule;
import com.letgo.populartvshows.presentation.presenters.ConfigurationPresenter;
import com.letgo.populartvshows.presentation.presenters.impl.ConfigurationPresenterImpl;
import com.letgo.populartvshows.presentation.ui.BaseView;
import com.letgo.populartvshows.presentation.ui.activities.PopularTvShowsActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author diego.galico
 */
public class SplashFragment extends BaseFragment implements ConfigurationPresenter.ConfigurationView {

    @Inject
    ConfigurationPresenterImpl mConfigurationPresenter;

    @Optional
    @InjectView(R.id.progress_bar)
    View mProgressBar;

    public static SplashFragment newInstance() {
        SplashFragment splashFragment = new SplashFragment();
        return splashFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDependencyInjector();
        mConfigurationPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onStart() {

        super.onStart();
        mConfigurationPresenter.start();
    }

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

    }

    @Override
    public void startPopularTvShowsFragment() {
        Intent myIntent = new Intent(getActivity(), PopularTvShowsActivity.class);
        startActivity(myIntent);
    }
}
