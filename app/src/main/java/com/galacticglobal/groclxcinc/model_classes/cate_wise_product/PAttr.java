
package com.galacticglobal.groclxcinc.model_classes.cate_wise_product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PAttr {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("p_size")
    @Expose
    private String pSize;
    @SerializedName("p_amt")
    @Expose
    private String pAmt;
    @SerializedName("p_image")
    @Expose
    private String pImage;
    @SerializedName("p_desc")
    @Expose
    private String pDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPSize() {
        return pSize;
    }

    public void setPSize(String pSize) {
        this.pSize = pSize;
    }

    public String getPAmt() {
        return pAmt;
    }

    public void setPAmt(String pAmt) {
        this.pAmt = pAmt;
    }

    public String getPImage() {
        return pImage;
    }

    public void setPImage(String pImage) {
        this.pImage = pImage;
    }

    public String getPDesc() {
        return pDesc;
    }

    public void setPDesc(String pDesc) {
        this.pDesc = pDesc;
    }

}
