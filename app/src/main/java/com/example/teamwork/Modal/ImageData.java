package com.example.teamwork.Modal;

import com.google.gson.annotations.SerializedName;

public class ImageData {

    @SerializedName("id")
    private int imgId;

    @SerializedName("thumbnailUrl")
    private String imageUrl;

    @SerializedName("title")
    private String imageTitle;

    public ImageData(int imgId, String imageUrl, String imageTitle) {
        this.imgId = imgId;
        this.imageUrl = imageUrl;
        this.imageTitle = imageTitle;
    }

    public int getImgId() {
        return imgId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageTitle() {
        return imageTitle;
    }
}
