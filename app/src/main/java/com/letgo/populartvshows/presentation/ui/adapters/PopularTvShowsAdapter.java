package com.letgo.populartvshows.presentation.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.letgo.populartvshows.R;
import com.letgo.populartvshows.domain.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliva on 9/18/16.
 */
public class PopularTvShowsAdapter extends RecyclerView.Adapter<PopularTvShowsAdapter.ProductViewHolder> {

    List<ProductModel> mProducts;

    public PopularTvShowsAdapter() {
        super();
        mProducts = new ArrayList<ProductModel>();
    }

    public void addData(ProductModel product) {
        mProducts.add(product);
        notifyDataSetChanged();
    }

    public void clear() {
        mProducts.clear();
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_product, viewGroup, false);
        ProductViewHolder viewHolder = new ProductViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder viewHolder, int i) {
        ProductModel product = mProducts.get(i);
        viewHolder.cardView.setBackgroundResource(product.getImage());
        viewHolder.title.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView title;

        public ProductViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
