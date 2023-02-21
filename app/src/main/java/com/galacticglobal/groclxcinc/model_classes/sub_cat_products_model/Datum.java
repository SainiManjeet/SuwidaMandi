
package com.galacticglobal.groclxcinc.model_classes.sub_cat_products_model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("pid")
    @Expose
    private Integer pid;
    @SerializedName("product_special")
    @Expose
    private Integer productSpecial;
    @SerializedName("product_featured")
    @Expose
    private Integer productFeatured;
    @SerializedName("product_code")
    @Expose
    private String productCode;
    @SerializedName("product_cat")
    @Expose
    private String productCat;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_cost")
    @Expose
    private String productCost;
    @SerializedName("product_weight")
    @Expose
    private String productWeight;
    @SerializedName("product_stock_status")
    @Expose
    private Integer productStockStatus;
    @SerializedName("product_desc")
    @Expose
    private String productDesc;
    @SerializedName("product_special_cost")
    @Expose
    private String productSpecialCost;
    @SerializedName("product_qty")
    @Expose
    private Integer productQty;
    @SerializedName("p_attr")
    @Expose
    private List<PAttr> pAttr = null;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("total_sold")
    @Expose
    private String totalSold;
    @SerializedName("remaining_stock")
    @Expose
    private Integer remainingStock;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getProductSpecial() {
        return productSpecial;
    }

    public void setProductSpecial(Integer productSpecial) {
        this.productSpecial = productSpecial;
    }

    public Integer getProductFeatured() {
        return productFeatured;
    }

    public void setProductFeatured(Integer productFeatured) {
        this.productFeatured = productFeatured;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCost() {
        return productCost;
    }

    public void setProductCost(String productCost) {
        this.productCost = productCost;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public Integer getProductStockStatus() {
        return productStockStatus;
    }

    public void setProductStockStatus(Integer productStockStatus) {
        this.productStockStatus = productStockStatus;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductSpecialCost() {
        return productSpecialCost;
    }

    public void setProductSpecialCost(String productSpecialCost) {
        this.productSpecialCost = productSpecialCost;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    public List<PAttr> getPAttr() {
        return pAttr;
    }

    public void setPAttr(List<PAttr> pAttr) {
        this.pAttr = pAttr;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(String totalSold) {
        this.totalSold = totalSold;
    }

    public Integer getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(Integer remainingStock) {
        this.remainingStock = remainingStock;
    }

}
