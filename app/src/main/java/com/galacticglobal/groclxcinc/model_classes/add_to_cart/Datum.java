
package com.galacticglobal.groclxcinc.model_classes.add_to_cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

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
    private String productQty;
    @SerializedName("product_amt")
    @Expose
    private String productAmt;
    @SerializedName("product_gtotal")
    @Expose
    private Integer productGtotal;
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

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductAmt() {
        return productAmt;
    }

    public void setProductAmt(String productAmt) {
        this.productAmt = productAmt;
    }

    public Integer getProductGtotal() {
        return productGtotal;
    }

    public void setProductGtotal(Integer productGtotal) {
        this.productGtotal = productGtotal;
    }

    public String getOrderSession() {
        return orderSession;
    }

    public void setOrderSession(String orderSession) {
        this.orderSession = orderSession;
    }

}
