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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * @author diego.galico
 */
public class TvShowDetailFragment extends BaseFragment {

    private TvShow mTvShow;

    private static final String TV_SHOW_OBJECT = "tv_show_object";

    @Optional
    @InjectView(R.id.toolbar)
    android.support.v7.widget.Toolbar mToolbar;

    @Optional
    @InjectView(R.id.overview)
    TextView mOverview;

    @Optional
    @InjectView(R.id.vote_average)
    TextView mVoteAverage;

    @Optional
    @InjectView(R.id.first_air_date)
    TextView mFirstAirDate;

    @Optional
    @InjectView(R.id.tv_show_image)
    ImageView mImage;

    public static TvShowDetailFragment newInstance(TvShow tvShow) {
        TvShowDetailFragment tvShowDetailFragment = new TvShowDetailFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString(TV_SHOW_OBJECT, new Gson().toJson(tvShow));
        tvShowDetailFragment.setArguments(args);

        return tvShowDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jsonMyObject = getArguments() != null ? getArguments().getString(TV_SHOW_OBJECT) : StringUtils.EMPTY_STRING;
        mTvShow = new Gson().fromJson(jsonMyObject, TvShow.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show_detail, container, false);
        ButterKnife.inject(this, view);

        mOverview.setText(mTvShow.getOverview());
        mVoteAverage.setText(StringUtils.EMPTY_STRING + mTvShow.getVoteAverage());
        mFirstAirDate.setText(StringUtils.convertStringDate(mTvShow.getFirstAirDate()));

        String imageUrl = Constants.IMAGE_URL_BACKDROP + mTvShow.getBackdropPath();
        Picasso.with(getContext())
               .load(imageUrl)
               .placeholder(R.drawable.ic_placeholder)
               .fit().centerCrop()
               .into(mImage, new Callback() {
                   @Override
                   public void onSuccess() {

                   }

                   @Override
                   public void onError() {

                   }
               });
        return view;

    }

}
