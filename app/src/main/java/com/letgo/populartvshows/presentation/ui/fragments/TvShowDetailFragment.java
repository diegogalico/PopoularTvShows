package com.letgo.populartvshows.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.Constants;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.utils.StringUtils;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author diego.galico
 *
 * Fragment in charge of showing tv show detail
 *
 */
public class TvShowDetailFragment extends BaseFragment {

    private TvShow mTvShow;
    private static final String TV_SHOW_OBJECT = "tv_show_object";
    private static final String TAG = "TvShowDetailFragment";

    @Optional
    @InjectView(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    @Optional
    @InjectView(R.id.text_view_overview)
    TextView mOverview;

    @Optional
    @InjectView(R.id.text_view_vote_average)
    TextView mVoteAverage;

    @Optional
    @InjectView(R.id.text_view_first_air_date)
    TextView mFirstAirDate;

    @Optional
    @InjectView(R.id.image_view_tv_show)
    ImageView mImage;

    public static TvShowDetailFragment newInstance(TvShow tvShow) {
        TvShowDetailFragment tvShowDetailFragment = new TvShowDetailFragment();

        Bundle args = new Bundle();
        args.putString(TV_SHOW_OBJECT, new Gson().toJson(tvShow));
        tvShowDetailFragment.setArguments(args);

        return tvShowDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Convert json object to TvShow object
        String jsonMyObject = getArguments() != null ? getArguments().getString(TV_SHOW_OBJECT) : StringUtils.EMPTY_STRING;
        mTvShow = new Gson().fromJson(jsonMyObject, TvShow.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show_detail, container, false);

        // Inject fragment and view to ButterKnife
        ButterKnife.inject(this, view);

        mOverview.setText(mTvShow.getOverview());
        mVoteAverage.setText(StringUtils.EMPTY_STRING + mTvShow.getVoteAverage());
        mFirstAirDate.setText(StringUtils.convertStringDate(mTvShow.getFirstAirDate()));

        // Concat image url backdrop with tv show image url
        String imageUrl = Constants.IMAGE_URL_BACKDROP + mTvShow.getBackdropPath();

        // Load image using Picasso
        Picasso.with(getContext())
               .load(imageUrl)
               .placeholder(R.drawable.ic_placeholder)
               .tag(TAG)
               .fit().centerCrop()
               .into(mImage);

        return view;
    }

    /**
     * Cancel Picasso request when fragment destroyed
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Picasso.with(getContext()).cancelRequest(mImage);
    }
}
