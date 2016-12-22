package com.dashlane.populartvshows.domain;

import java.util.List;

/**
 * @author diego.galico
 *
 * Class that represents ConfigurationEntity information in the domain layer
 *
 */
public class ConfigurationData {

    private String base_url;
    private List<String> backdrop_sizes;
    private List<String>  poster_sizes;
    private String image_url_poster;
    private String image_url_backDrop;

    public String getBaseUrl() {
        return base_url;
    }

    public void setBaseUrl(String baseUrl) {
        this.base_url = baseUrl;
    }

    public List<String> getBackdropSizes() {
        return backdrop_sizes;
    }

    public void setBackdropSizes(List<String> backdropSizes) {
        this.backdrop_sizes = backdropSizes;
    }

    public List<String> getPosterSizes() {
        return poster_sizes;
    }

    public void setPosterSizes(List<String> posterSizes) {
        this.poster_sizes = posterSizes;
    }

    public String getImageUrlBackDrop() {
        return image_url_backDrop;
    }

    public void setImageUrlBackDrop(String imageUrlBackDrop) {
        this.image_url_backDrop = imageUrlBackDrop;
    }

    public String getImageUrlPoster() {
        return image_url_poster;
    }

    public void setImageUrlPoster(String imageUrlPoster) {
        this.image_url_poster = imageUrlPoster;
    }

}
