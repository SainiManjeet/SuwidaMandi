
package com.galacticglobal.groclxcinc.model_classes.order_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("sid")
    @Expose
    private Integer sid;
    @SerializedName("sorder_amt")
    @Expose
    private String sorderAmt;
    @SerializedName("sorder_shipping")
    @Expose
    private String sorderShipping;
    @SerializedName("sorder_gtotal")
    @Expose
    private String sorderGtotal;
    @SerializedName("sorder_billing_address")
    @Expose
    private Object sorderBillingAddress;
    @SerializedName("sorder_shipping_address")
    @Expose
    private Object sorderShippingAddress;
    @SerializedName("sorder_payment_type")
    @Expose
    private String sorderPaymentType;
    @SerializedName("sorder_number")
    @Expose
    private String sorderNumber;
    @SerializedName("sorder_session")
    @Expose
    private String sorderSession;
    @SerializedName("sorder_status")
    @Expose
    private String sorderStatus;
    @SerializedName("sorder_created")
    @Expose
    private String sorderCreated;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSorderAmt() {
        return sorderAmt;
    }

    public void setSorderAmt(String sorderAmt) {
        this.sorderAmt = sorderAmt;
    }

    public String getSorderShipping() {
        return sorderShipping;
    }

    public void setSorderShipping(String sorderShipping) {
        this.sorderShipping = sorderShipping;
    }

    public String getSorderGtotal() {
        return sorderGtotal;
    }

    public void setSorderGtotal(String sorderGtotal) {
        this.sorderGtotal = sorderGtotal;
    }

    public Object getSorderBillingAddress() {
        return sorderBillingAddress;
    }

    public void setSorderBillingAddress(Object sorderBillingAddress) {
        this.sorderBillingAddress = sorderBillingAddress;
    }

    public Object getSorderShippingAddress() {
        return sorderShippingAddress;
    }

    public void setSorderShippingAddress(Object sorderShippingAddress) {
        this.sorderShippingAddress = sorderShippingAddress;
    }

    public String getSorderPaymentType() {
        return sorderPaymentType;
    }

    public void setSorderPaymentType(String sorderPaymentType) {
        this.sorderPaymentType = sorderPaymentType;
    }

    public String getSorderNumber() {
        return sorderNumber;
    }

    public void setSorderNumber(String sorderNumber) {
        this.sorderNumber = sorderNumber;
    }

    public String getSorderSession() {
        return sorderSession;
    }

    public void setSorderSession(String sorderSession) {
        this.sorderSession = sorderSession;
    }

    public String getSorderStatus() {
        return sorderStatus;
    }

    public void setSorderStatus(String sorderStatus) {
        this.sorderStatus = sorderStatus;
    }

    public String getSorderCreated() {
        return sorderCreated;
    }

    public void setSorderCreated(String sorderCreated) {
        this.sorderCreated = sorderCreated;
    }

}
