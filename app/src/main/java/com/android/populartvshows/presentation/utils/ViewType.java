package com.android.populartvshows.presentation.utils;

import com.android.populartvshows.presentation.ui.adapters.PopularTvShowsAdapter;

/**
 * @author diego.galico
 *
 * Enum for {@link PopularTvShowsAdapter} view type
 *
 */
public enum ViewType {

    VIEW_TYPE_ITEM(0),
    VIEW_TYPE_LOADING(1);

    private int value;

    ViewType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}