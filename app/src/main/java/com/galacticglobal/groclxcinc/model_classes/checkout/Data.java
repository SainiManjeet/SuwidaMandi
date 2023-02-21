
package com.galacticglobal.groclxcinc.model_classes.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("sid")
    @Expose
    private Integer sid;
    @SerializedName("discountcoupon_id")
    @Expose
    private Integer discountcouponId;
    @SerializedName("discount_amount")
    @Expose
    private String discountAmount;
    @SerializedName("discount_percentage")
    @Expose
    private String discountPercentage;
    @SerializedName("gift_id")
    @Expose
    private Integer giftId;
    @SerializedName("gift_amt")
    @Expose
    private String giftAmt;
    @SerializedName("sorder_amt")
    @Expose
    private String sorderAmt;
    @SerializedName("sorder_shipping")
    @Expose
    private String sorderShipping;
    @SerializedName("sorder_pickup")
    @Expose
    private Integer sorderPickup;
    @SerializedName("sorder_gst")
    @Expose
    private String sorderGst;
    @SerializedName("sorder_gtotal")
    @Expose
    private String sorderGtotal;
    @SerializedName("sorder_shipping_method")
    @Expose
    private Object sorderShippingMethod;
    @SerializedName("sorder_shipping_address")
    @Expose
    private String sorderShippingAddress;
    @SerializedName("sorder_billing_address")
    @Expose
    private String sorderBillingAddress;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("sorder_payment_type")
    @Expose
    private String sorderPaymentType;
    @SerializedName("sorder_number")
    @Expose
    private String sorderNumber;
    @SerializedName("sorder_session")
    @Expose
    private String sorderSession;
    @SerializedName("sorder_created")
    @Expose
    private String sorderCreated;
    @SerializedName("sorder_status")
    @Expose
    private String sorderStatus;
    @SerializedName("sorder_txn_id")
    @Expose
    private Object sorderTxnId;
    @SerializedName("sorder_token")
    @Expose
    private Object sorderToken;
    @SerializedName("sorder_payment_status")
    @Expose
    private String sorderPaymentStatus;
    @SerializedName("shipped_by")
    @Expose
    private Object shippedBy;
    @SerializedName("shipped_docket_no")
    @Expose
    private Object shippedDocketNo;
    @SerializedName("sorder_updated")
    @Expose
    private Object sorderUpdated;
    @SerializedName("ip_address")
    @Expose
    private Object ipAddress;
    @SerializedName("user_info")
    @Expose
    private Object userInfo;
    @SerializedName("user_details")
    @Expose
    private UserDetails userDetails;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getDiscountcouponId() {
        return discountcouponId;
    }

    public void setDiscountcouponId(Integer discountcouponId) {
        this.discountcouponId = discountcouponId;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public String getGiftAmt() {
        return giftAmt;
    }

    public void setGiftAmt(String giftAmt) {
        this.giftAmt = giftAmt;
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

    public Integer getSorderPickup() {
        return sorderPickup;
    }

    public void setSorderPickup(Integer sorderPickup) {
        this.sorderPickup = sorderPickup;
    }

    public String getSorderGst() {
        return sorderGst;
    }

    public void setSorderGst(String sorderGst) {
        this.sorderGst = sorderGst;
    }

    public String getSorderGtotal() {
        return sorderGtotal;
    }

    public void setSorderGtotal(String sorderGtotal) {
        this.sorderGtotal = sorderGtotal;
    }

    public Object getSorderShippingMethod() {
        return sorderShippingMethod;
    }

    public void setSorderShippingMethod(Object sorderShippingMethod) {
        this.sorderShippingMethod = sorderShippingMethod;
    }

    public String getSorderShippingAddress() {
        return sorderShippingAddress;
    }

    public void setSorderShippingAddress(String sorderShippingAddress) {
        this.sorderShippingAddress = sorderShippingAddress;
    }

    public String getSorderBillingAddress() {
        return sorderBillingAddress;
    }

    public void setSorderBillingAddress(String sorderBillingAddress) {
        this.sorderBillingAddress = sorderBillingAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getSorderCreated() {
        return sorderCreated;
    }

    public void setSorderCreated(String sorderCreated) {
        this.sorderCreated = sorderCreated;
    }

    public String getSorderStatus() {
        return sorderStatus;
    }

    public void setSorderStatus(String sorderStatus) {
        this.sorderStatus = sorderStatus;
    }

    public Object getSorderTxnId() {
        return sorderTxnId;
    }

    public void setSorderTxnId(Object sorderTxnId) {
        this.sorderTxnId = sorderTxnId;
    }

    public Object getSorderToken() {
        return sorderToken;
    }

    public void setSorderToken(Object sorderToken) {
        this.sorderToken = sorderToken;
    }

    public String getSorderPaymentStatus() {
        return sorderPaymentStatus;
    }

    public void setSorderPaymentStatus(String sorderPaymentStatus) {
        this.sorderPaymentStatus = sorderPaymentStatus;
    }

    public Object getShippedBy() {
        return shippedBy;
    }

    public void setShippedBy(Object shippedBy) {
        this.shippedBy = shippedBy;
    }

    public Object getShippedDocketNo() {
        return shippedDocketNo;
    }

    public void setShippedDocketNo(Object shippedDocketNo) {
        this.shippedDocketNo = shippedDocketNo;
    }

    public Object getSorderUpdated() {
        return sorderUpdated;
    }

    public void setSorderUpdated(Object sorderUpdated) {
        this.sorderUpdated = sorderUpdated;
    }

    public Object getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(Object ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

}
