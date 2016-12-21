package com.dashlane.populartvshows.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dashlane.populartvshows.R;
import com.dashlane.populartvshows.app.Constants;
import com.dashlane.populartvshows.data.entities.TvShow;
import com.dashlane.populartvshows.utils.StringUtils;
import com.dashlane.populartvshows.utils.ViewType;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author diego.galico
 *
 * PopularTvShowsAdapter is an adapter class in charge of showing popular tv shows items
 *
 */
public class PopularTvShowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<TvShow> mTvShows;
    private static final String TAG = "PopularTvShowsAdapter";

    public PopularTvShowsAdapter(List<TvShow> tvShows) {
        mTvShows = tvShows;
    }

    public List<TvShow> getTvShowsList() {
        return mTvShows;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ViewType.VIEW_TYPE_ITEM.getValue()) {
            this.mContext = viewGroup.getContext();
            View view = LayoutInflater.from(viewGroup.getContext())
                                      .inflate(R.layout.item_popular_tv_show, viewGroup, false);
            return new TvShowViewHolder(view);
        } else if (viewType == ViewType.VIEW_TYPE_LOADING.getValue()) {
            View view = LayoutInflater.from(viewGroup.getContext())
                                      .inflate(R.layout.item_loading, viewGroup, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return mTvShows.get(position) == null ? ViewType.VIEW_TYPE_LOADING.getValue() : ViewType.VIEW_TYPE_ITEM.getValue();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TvShowViewHolder) {
            TvShow tvShow = mTvShows.get(position);
            TvShowViewHolder tvShowViewHolder = (TvShowViewHolder) holder;
            tvShowViewHolder.title.setText(tvShow.getName());
            tvShowViewHolder.average.setText(StringUtils.EMPTY_STRING + tvShow.getVoteAverage());

            // Concat image url poster with tv show image url
            String imageUrl = Constants.IMAGE_URL_POSTER + tvShow.getPosterPath();

            // Load image using Picasso
            Picasso.with(mContext)
                   .load(imageUrl)
                   .fit().centerCrop()
                   .tag(TAG)
                   .into(tvShowViewHolder.itemTvShowCover, new Callback() {
                       @Override
                       public void onSuccess() {
                           // Tv show is ready to be clicked
                           mTvShows.get(position).setTvShowReady(true);
                       }

                       @Override
                       public void onError() {

                       }
                   });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    /**
     * Is tv show ready to be clicked
     *
     * @param position
     * @return
     */
    public boolean isTvShowReady(int position) {
        return mTvShows.get(position).isTvShowReady();
    }

    /**
     * Add pagination loading for tv shows
     */
    public void paginationLoading() {
        mTvShows.add(null);
        notifyItemInserted(mTvShows.size() - 1);
    }

    /**
     * Append tv shows to actual list
     *
     * @param tvShowList
     */
    public void appendTvShows(List<TvShow> tvShowList) {
        //Remove pagination loading item
        removePaginationLoading();
        mTvShows.addAll(tvShowList);
        notifyDataSetChanged();
    }

    /**
     * Remove pagination loading for tv shows
     */
    public void removePaginationLoading() {
        mTvShows.remove(mTvShows.size() - 1);
        notifyItemRemoved(mTvShows.size());
    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
    }

    /**
     * Cancel Picasso request when view recycled
     * @param holder
     */
    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof TvShowViewHolder) {
            ((TvShowViewHolder) holder).cleanup();
        }

    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemTvShowCover;
        public TextView title;
        public TextView average;

        public TvShowViewHolder(View itemView) {
            super(itemView);
            itemTvShowCover = (ImageView) itemView.findViewById(R.id.image_view_item_tv_show);
            title = (TextView) itemView.findViewById(R.id.text_view_title);
            average = (TextView) itemView.findViewById(R.id.text_view_average);
        }

        // Clean Picasso request
        public void cleanup() {
            Picasso.with(mContext)
                   .cancelRequest(itemTvShowCover);
            itemTvShowCover.setImageDrawable(null);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }
    }
}
