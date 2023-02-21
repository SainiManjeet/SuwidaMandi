
package com.galacticglobal.groclxcinc.model_classes.cart_items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("oid")
    @Expose
    private Integer oid;
    @SerializedName("pid")
    @Expose
    private Integer pid;
    @SerializedName("product_weight")
    @Expose
    private String productWeight;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("product_amt")


    /*@Expose
    private String productAmt;
    @SerializedName("sub_total")*/
    @Expose
    private float productAmt;
    @SerializedName("sub_total")

    @Expose
    private String subTotal;
    @SerializedName("product_attr")

//    @Expose
//    private Integer subTotal;
//    @SerializedName("product_attr")

    @Expose
    private Integer productAttr;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("p_size")
    @Expose
    private String pSize;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public float getProductAmt() {
        return productAmt;
    }

    public void setProductAmt(float productAmt) {
        this.productAmt = productAmt;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }
//  public Integer getSubTotal() {
//      return subTotal;
//  }

//    public void setSubTotal(Integer subTotal) {
//        this.subTotal = subTotal;
//    }
    public Integer getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(Integer productAttr) {
        this.productAttr = productAttr;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPSize() {
        return pSize;
    }

    public void setPSize(String pSize) {
        this.pSize = pSize;
    }

}
