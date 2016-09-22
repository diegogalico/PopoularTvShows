package com.letgo.populartvshows.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.app.Constants;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.utils.RecyclerItemClickListener;
import com.letgo.populartvshows.utils.StringUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author diego.galico
 */
public class PopularTvShowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<TvShow> mTvShows;

    public final int VIEW_TYPE_ITEM = 0;
    public final int VIEW_TYPE_LOADING = 1;

    public PopularTvShowsAdapter(List<TvShow> tvShows) {
        mTvShows = tvShows;
    }

    public List<TvShow> getTvShowsList() {
        return mTvShows;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            this.mContext = viewGroup.getContext();
            View view = LayoutInflater.from(viewGroup.getContext())
                                      .inflate(R.layout.item_popular_tv_show, viewGroup, false);
            return new TvShowViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(viewGroup.getContext())
                                      .inflate(R.layout.item_loading, viewGroup, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return mTvShows.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TvShowViewHolder) {
            TvShow tvShow = mTvShows.get(position);
            TvShowViewHolder tvShowViewHolder = (TvShowViewHolder) holder;
            tvShowViewHolder.title.setText(tvShow.getName());
            tvShowViewHolder.average.setText(StringUtils.EMPTY_STRING + tvShow.getVoteAverage());
            String imageUrl = Constants.IMAGE_URL_POSTER + tvShow.getPosterPath();
            Picasso.with(mContext)
                   .load(imageUrl)
                   .fit().centerCrop()
                   .into(tvShowViewHolder.itemTvShowCover, new Callback() {
                       @Override
                       public void onSuccess() {
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

    public boolean isTvShowReady(int position) {

        return mTvShows.get(position).isTvShowReady();
    }

    public void loadMore() {
        mTvShows.add(null);
        notifyItemInserted(mTvShows.size() - 1);
    }

    public void appendTvShows(List<TvShow> tvShowList) {
        //Remove loading item
        mTvShows.remove(mTvShows.size() - 1);
        notifyItemRemoved(mTvShows.size());
        mTvShows.addAll(tvShowList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder{

        public ImageView itemTvShowCover;
        public TextView title;
        public TextView average;

        public TvShowViewHolder(View itemView) {
            super(itemView);
            itemTvShowCover = (ImageView) itemView.findViewById(R.id.item_tv_show_cover);
            title = (TextView) itemView.findViewById(R.id.title);
            average = (TextView) itemView.findViewById(R.id.average);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarLoadMore);
        }
    }
}
