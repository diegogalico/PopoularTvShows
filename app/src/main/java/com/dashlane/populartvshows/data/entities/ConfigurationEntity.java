package com.dashlane.populartvshows.data.entities;

import java.util.List;

/**
 * @author diego.galico
 *
 * Class that model ConfigurationEntity information
 *
 */
public class ConfigurationEntity {
    private ImageInfoEntity images;
    private List<String> change_keys;

    public List<String> getChangeKeys() {
        return change_keys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.change_keys = changeKeys;
    }

    public ImageInfoEntity getImages() {
        return images;
    }

    public void setImages(ImageInfoEntity images) {
        this.images = images;
    }
}
