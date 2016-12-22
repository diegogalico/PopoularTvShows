package com.dashlane.populartvshows.presentation.models;

import com.dashlane.populartvshows.data.entities.ImageInfoEntity;

import java.util.List;

/**
 * @author diego.galico
 *
 * Class that represents ConfigurationEntity information in the presentation layer
 *
 */
public class ConfigurationModel {
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
