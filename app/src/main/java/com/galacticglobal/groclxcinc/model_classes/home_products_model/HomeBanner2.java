
package com.galacticglobal.groclxcinc.model_classes.home_products_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeBanner2 {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("cid")
    @Expose
    private Integer cid;
    @SerializedName("name")
    @Expose
    private String name;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
