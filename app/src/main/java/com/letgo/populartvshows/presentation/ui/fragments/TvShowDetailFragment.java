package com.letgo.populartvshows.presentation.ui.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.letgo.populartvshows.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author diego.galico
 */
public class TvShowDetailFragment extends BaseFragment {

    private int mTvShowId;
    private static final String TV_SHOW_ID = "tv_show_id";

    @Optional
    @InjectView(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    @Optional
    @InjectView(R.id.title_tv_show)
    TextView mTitle;

    public static TvShowDetailFragment newInstance(int num) {
        TvShowDetailFragment tvShowDetailFragment = new TvShowDetailFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt(TV_SHOW_ID, num);
        tvShowDetailFragment.setArguments(args);

        return tvShowDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTvShowId = getArguments() != null ? getArguments().getInt(TV_SHOW_ID) : 1;
        Bundle bundle = this.getArguments();
        mTvShowId = bundle.getInt(TV_SHOW_ID, 0);

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
        mTitle.setText("" + mTvShowId);
        return view;

    }

}
