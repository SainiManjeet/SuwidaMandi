
package com.galacticglobal.groclxcinc.model_classes.edit_profile_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_role")
    @Expose
    private Integer userRole;
    @SerializedName("user_refer_id")
    @Expose
    private Object userReferId;
    @SerializedName("refer_name")
    @Expose
    private Object referName;
    @SerializedName("teamleader")
    @Expose
    private Object teamleader;
    @SerializedName("consultant")
    @Expose
    private Integer consultant;
    @SerializedName("is_hostess")
    @Expose
    private Integer isHostess;
    @SerializedName("user_fullname")
    @Expose
    private String userFullname;
    @SerializedName("user_fname")
    @Expose
    private String userFname;
    @SerializedName("user_lname")
    @Expose
    private String userLname;
    @SerializedName("user_name")
    @Expose
    private Object userName;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_pass")
    @Expose
    private String userPass;
    @SerializedName("user_address")
    @Expose
    private String userAddress;
    @SerializedName("user_suburb")
    @Expose
    private Object userSuburb;
    @SerializedName("user_postcode")
    @Expose
    private Object userPostcode;
    @SerializedName("user_country")
    @Expose
    private Object userCountry;
    @SerializedName("user_state")
    @Expose
    private Object userState;
    @SerializedName("user_phone_primary")
    @Expose
    private String userPhonePrimary;
    @SerializedName("user_phone_secondary")
    @Expose
    private Object userPhoneSecondary;
    @SerializedName("user_notes")
    @Expose
    private Object userNotes;
    @SerializedName("billing_address")
    @Expose
    private String billingAddress;
    @SerializedName("user_bill_address")
    @Expose
    private String userBillAddress;
    @SerializedName("user_bill_suburb")
    @Expose
    private Object userBillSuburb;
    @SerializedName("user_bill_postcode")
    @Expose
    private Object userBillPostcode;
    @SerializedName("user_bill_state")
    @Expose
    private Object userBillState;
    @SerializedName("user_bill_country")
    @Expose
    private Object userBillCountry;
    @SerializedName("user_bill_phone")
    @Expose
    private Object userBillPhone;
    @SerializedName("user_reg_date")
    @Expose
    private String userRegDate;
    @SerializedName("user_ban")
    @Expose
    private Integer userBan;
    @SerializedName("user_online")
    @Expose
    private Integer userOnline;
    @SerializedName("forgot_pass")
    @Expose
    private Object forgotPass;
    @SerializedName("user_status")
    @Expose
    private Integer userStatus;
    @SerializedName("is_deleted")
    @Expose
    private Integer isDeleted;
    @SerializedName("user_apply_reps")
    @Expose
    private Integer userApplyReps;
    @SerializedName("partyEnquiry")
    @Expose
    private Integer partyEnquiry;
    @SerializedName("consultantEnquiry")
    @Expose
    private Integer consultantEnquiry;
    @SerializedName("joinVip")
    @Expose
    private Integer joinVip;
    @SerializedName("onlineRegister")
    @Expose
    private Integer onlineRegister;
    @SerializedName("is_visible")
    @Expose
    private Integer isVisible;
    @SerializedName("user_unit")
    @Expose
    private String userUnit;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Object getUserReferId() {
        return userReferId;
    }

    public void setUserReferId(Object userReferId) {
        this.userReferId = userReferId;
    }

    public Object getReferName() {
        return referName;
    }

    public void setReferName(Object referName) {
        this.referName = referName;
    }

    public Object getTeamleader() {
        return teamleader;
    }

    public void setTeamleader(Object teamleader) {
        this.teamleader = teamleader;
    }

    public Integer getConsultant() {
        return consultant;
    }

    public void setConsultant(Integer consultant) {
        this.consultant = consultant;
    }

    public Integer getIsHostess() {
        return isHostess;
    }

    public void setIsHostess(Integer isHostess) {
        this.isHostess = isHostess;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Object getUserSuburb() {
        return userSuburb;
    }

    public void setUserSuburb(Object userSuburb) {
        this.userSuburb = userSuburb;
    }

    public Object getUserPostcode() {
        return userPostcode;
    }

    public void setUserPostcode(Object userPostcode) {
        this.userPostcode = userPostcode;
    }

    public Object getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(Object userCountry) {
        this.userCountry = userCountry;
    }

    public Object getUserState() {
        return userState;
    }

    public void setUserState(Object userState) {
        this.userState = userState;
    }

    public String getUserPhonePrimary() {
        return userPhonePrimary;
    }

    public void setUserPhonePrimary(String userPhonePrimary) {
        this.userPhonePrimary = userPhonePrimary;
    }

    public Object getUserPhoneSecondary() {
        return userPhoneSecondary;
    }

    public void setUserPhoneSecondary(Object userPhoneSecondary) {
        this.userPhoneSecondary = userPhoneSecondary;
    }

    public Object getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(Object userNotes) {
        this.userNotes = userNotes;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getUserBillAddress() {
        return userBillAddress;
    }

    public void setUserBillAddress(String userBillAddress) {
        this.userBillAddress = userBillAddress;
    }

    public Object getUserBillSuburb() {
        return userBillSuburb;
    }

    public void setUserBillSuburb(Object userBillSuburb) {
        this.userBillSuburb = userBillSuburb;
    }

    public Object getUserBillPostcode() {
        return userBillPostcode;
    }

    public void setUserBillPostcode(Object userBillPostcode) {
        this.userBillPostcode = userBillPostcode;
    }

    public Object getUserBillState() {
        return userBillState;
    }

    public void setUserBillState(Object userBillState) {
        this.userBillState = userBillState;
    }

    public Object getUserBillCountry() {
        return userBillCountry;
    }

    public void setUserBillCountry(Object userBillCountry) {
        this.userBillCountry = userBillCountry;
    }

    public Object getUserBillPhone() {
        return userBillPhone;
    }

    public void setUserBillPhone(Object userBillPhone) {
        this.userBillPhone = userBillPhone;
    }

    public String getUserRegDate() {
        return userRegDate;
    }

    public void setUserRegDate(String userRegDate) {
        this.userRegDate = userRegDate;
    }

    public Integer getUserBan() {
        return userBan;
    }

    public void setUserBan(Integer userBan) {
        this.userBan = userBan;
    }

    public Integer getUserOnline() {
        return userOnline;
    }

    public void setUserOnline(Integer userOnline) {
        this.userOnline = userOnline;
    }

    public Object getForgotPass() {
        return forgotPass;
    }

    public void setForgotPass(Object forgotPass) {
        this.forgotPass = forgotPass;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getUserApplyReps() {
        return userApplyReps;
    }

    public void setUserApplyReps(Integer userApplyReps) {
        this.userApplyReps = userApplyReps;
    }

    public Integer getPartyEnquiry() {
        return partyEnquiry;
    }

    public void setPartyEnquiry(Integer partyEnquiry) {
        this.partyEnquiry = partyEnquiry;
    }

    public Integer getConsultantEnquiry() {
        return consultantEnquiry;
    }

    public void setConsultantEnquiry(Integer consultantEnquiry) {
        this.consultantEnquiry = consultantEnquiry;
    }

    public Integer getJoinVip() {
        return joinVip;
    }

    public void setJoinVip(Integer joinVip) {
        this.joinVip = joinVip;
    }

    public Integer getOnlineRegister() {
        return onlineRegister;
    }

    public void setOnlineRegister(Integer onlineRegister) {
        this.onlineRegister = onlineRegister;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public String getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(String userUnit) {
        this.userUnit = userUnit;
    }

}
