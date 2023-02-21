
package com.galacticglobal.groclxcinc.model_classes.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data{
        @SerializedName("user_id")
@Expose
private Integer userId;
        @SerializedName("user_fullname")
        @Expose
        private String userFullname;
        @SerializedName("user_pass")
        @Expose
        private String userPass;
        @SerializedName("user_fname")
        @Expose
        private String userFname;
        @SerializedName("user_lname")
        @Expose
        private String userLname;
        @SerializedName("user_email")
        @Expose
        private String userEmail;
        @SerializedName("user_address")
        @Expose
        private String userAddress;
        @SerializedName("user_phone_primary")
        @Expose
        private String userPhonePrimary;
        @SerializedName("user_phone_secondary")
        @Expose
        private Object userPhoneSecondary;
        @SerializedName("user_reg_date")
        @Expose
        private String userRegDate;
        @SerializedName("user_hno")
        @Expose
        private String userHno;
        @SerializedName("user_status")
        @Expose
        private Integer userStatus;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserFullname() {
            return userFullname;
        }

        public void setUserFullname(String userFullname) {
            this.userFullname = userFullname;
        }

        public String getUserPass() {
            return userPass;
        }

        public void setUserPass(String userPass) {
            this.userPass = userPass;
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

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
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

        public String getUserRegDate() {
            return userRegDate;
        }

        public void setUserRegDate(String userRegDate) {
            this.userRegDate = userRegDate;
        }

        public String getUserHno() {
            return userHno;
        }

        public void setUserHno(String userHno) {
            this.userHno = userHno;
        }

        public Integer getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(Integer userStatus) {
            this.userStatus = userStatus;
        }

}
