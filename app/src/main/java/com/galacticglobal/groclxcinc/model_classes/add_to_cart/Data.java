
package com.galacticglobal.groclxcinc.model_classes.add_to_cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("oid")
    @Expose
    private Integer oid;
    @SerializedName("pid")
    @Expose
    private Integer pid;
    @SerializedName("product_attr")
    @Expose
    private Integer productAttr;
    @SerializedName("product_qty")
    @Expose
    private Integer productQty;
    @SerializedName("product_amt")
    @Expose
    private String productAmt;
    @SerializedName("product_gtotal")
    @Expose
    private String productGtotal;
    @SerializedName("order_session")
    @Expose
    private String orderSession;

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

    public Integer getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(Integer productAttr) {
        this.productAttr = productAttr;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    public String getProductAmt() {
        return productAmt;
    }

    public void setProductAmt(String productAmt) {
        this.productAmt = productAmt;
    }

    public String getProductGtotal() {
        return productGtotal;
    }

    public void setProductGtotal(String productGtotal) {
        this.productGtotal = productGtotal;
    }

    public String getOrderSession() {
        return orderSession;
    }

    public void setOrderSession(String orderSession) {
        this.orderSession = orderSession;
    }

}
