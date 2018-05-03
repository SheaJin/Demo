package com.xy.doll.banner;

/**
 * Created by jxy on 2018/4/26.
 */

public class BannerInfo {
    public String imageUrl;
    public String clickUrl;

    public BannerInfo(String imageUrl, String clickUrl) {
        this.imageUrl = imageUrl;
        this.clickUrl = clickUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }
}
