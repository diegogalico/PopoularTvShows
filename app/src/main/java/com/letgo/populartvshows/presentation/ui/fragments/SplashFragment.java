package com.letgo.populartvshows.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.letgo.populartvshows.R;

/**
 * @author diego.galico
 */
public class SplashFragment extends BaseFragment {

    public static SplashFragment newInstance() {
        SplashFragment splashFragment = new SplashFragment();
        return splashFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        return view;
    }

}
