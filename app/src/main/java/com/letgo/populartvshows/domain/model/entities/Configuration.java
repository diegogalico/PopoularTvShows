package com.letgo.populartvshows.domain.model.entities;

import java.util.List;

/**
 * Created by diego.galico on 21/09/2016.
 */

public class Configuration {
    private ImageInfo images;
    private List<String> change_keys;

    public List<String> getChange_keys() {
        return change_keys;
    }

    public void setChange_keys(List<String> change_keys) {
        this.change_keys = change_keys;
    }

    public ImageInfo getImages() {
        return images;
    }

    public void setImages(ImageInfo images) {
        this.images = images;
    }
}
