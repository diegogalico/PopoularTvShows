package com.dashlane.populartvshows.domain.model.entities;

import java.util.List;

/**
 * @author diego.galico
 *
 * Class that model Configuration information
 *
 */
public class Configuration {
    private ImageInfo images;
    private List<String> change_keys;

    public List<String> getChangeKeys() {
        return change_keys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.change_keys = changeKeys;
    }

    public ImageInfo getImages() {
        return images;
    }

    public void setImages(ImageInfo images) {
        this.images = images;
    }
}
