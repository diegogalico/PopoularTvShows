package com.letgo.populartvshows.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.domain.model.entities.TvShow;
import com.letgo.populartvshows.utils.RecyclerViewClickListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author diego.galico
 */
public class PopularTvShowsAdapter extends RecyclerView.Adapter<PopularTvShowsAdapter.TvShowViewHolder> {

    private Context mContext;
    private List<TvShow> mTvShows;
    private RecyclerViewClickListener mRecyclerClickListener;

    public PopularTvShowsAdapter(List<TvShow> tvShows) {
        mTvShows = tvShows;
    }

    public List<TvShow> getTvShowsList() {
        return mTvShows;
    }

    @Override
    public TvShowViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                               .inflate(R.layout.item_popular_tv_show, viewGroup, false);
        this.mContext = viewGroup.getContext();
        TvShowViewHolder viewHolder = new TvShowViewHolder(v, mRecyclerClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TvShowViewHolder viewHolder, final int position) {
        TvShow tvShow = mTvShows.get(position);
        viewHolder.title.setText(tvShow.getName());
        String posterURL = tvShow.getPosterPath();
        Picasso.with(mContext)
               .load(posterURL)
               .fit().centerCrop()
               .into(viewHolder.itemTvShowCover, new Callback() {
                   @Override
                   public void onSuccess() {
                       mTvShows.get(position).setTvShowReady(true);
                   }

                   @Override
                   public void onError() {

                   }
               });
    }

    public boolean isTvShowReady(int position) {

        return mTvShows.get(position).isTvShowReady();
    }

    public void clear() {
        mTvShows.clear();
        notifyDataSetChanged();
    }

    public void appendTvShows(List<TvShow> tvShowList) {
        mTvShows.addAll(tvShowList);
        notifyDataSetChanged();
    }

    public void setRecyclerListListener(RecyclerViewClickListener mRecyclerClickListener) {
        this.mRecyclerClickListener = mRecyclerClickListener;
    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        private final RecyclerViewClickListener onClickListener;
        public ImageView itemTvShowCover;
        public TextView title;

        public TvShowViewHolder(View itemView, RecyclerViewClickListener onClickListener) {
            super(itemView);
            itemTvShowCover = (ImageView) itemView.findViewById(R.id.item_tv_show_cover);
            title = (TextView) itemView.findViewById(R.id.title);
            this.onClickListener = onClickListener;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP && event.getAction() != MotionEvent.ACTION_MOVE) {

                onClickListener.onClick(v, getPosition(), event.getX(), event.getY());
            }
            return true;
        }
    }
}
