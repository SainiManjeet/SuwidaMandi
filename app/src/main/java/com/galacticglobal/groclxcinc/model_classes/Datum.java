
package com.galacticglobal.groclxcinc.model_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("cid")
    @Expose
    private Integer cid;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("cat_image")
    @Expose
    private String catImage;
    @SerializedName("category_slug")
    @Expose
    private String categorySlug;
    @SerializedName("sub_cat")
    @Expose
    private String subCat;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public String getSubCat() {
        return subCat;
    }

    public void setSubCat(String subCat) {
        this.subCat = subCat;
    }

}
