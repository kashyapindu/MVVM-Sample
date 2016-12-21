package com.mvvmpattern.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by indu_kashyap on 12/6/2016.
 */

public class UserDetailResponse implements Parcelable {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("response")
    @Expose
    private Response response;

    /**
     *
     * @return
     * The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public class Auth {

        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("deviceid")
        @Expose
        private String deviceid;
        @SerializedName("zipcode")
        @Expose
        private String zipcode;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("email_verified")
        @Expose
        private Integer emailVerified;
        @SerializedName("active")
        @Expose
        private Integer active;
        @SerializedName("user_referral_code")
        @Expose
        private String userReferralCode;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("modified")
        @Expose
        private String modified;
        @SerializedName("id")
        @Expose
        private Integer id;

        /**
         *
         * @return
         * The firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         *
         * @param firstName
         * The first_name
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         *
         * @return
         * The lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         *
         * @param lastName
         * The last_name
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         *
         * @return
         * The email
         */
        public String getEmail() {
            return email;
        }

        /**
         *
         * @param email
         * The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         *
         * @return
         * The deviceid
         */
        public String getDeviceid() {
            return deviceid;
        }

        /**
         *
         * @param deviceid
         * The deviceid
         */
        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        /**
         *
         * @return
         * The zipcode
         */
        public String getZipcode() {
            return zipcode;
        }

        /**
         *
         * @param zipcode
         * The zipcode
         */
        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        /**
         *
         * @return
         * The country
         */
        public String getCountry() {
            return country;
        }

        /**
         *
         * @param country
         * The country
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         *
         * @return
         * The city
         */
        public String getCity() {
            return city;
        }

        /**
         *
         * @param city
         * The city
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         *
         * @return
         * The dob
         */
        public String getDob() {
            return dob;
        }

        /**
         *
         * @param dob
         * The dob
         */
        public void setDob(String dob) {
            this.dob = dob;
        }

        /**
         *
         * @return
         * The address
         */
        public String getAddress() {
            return address;
        }

        /**
         *
         * @param address
         * The address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         *
         * @return
         * The emailVerified
         */
        public Integer getEmailVerified() {
            return emailVerified;
        }

        /**
         *
         * @param emailVerified
         * The email_verified
         */
        public void setEmailVerified(Integer emailVerified) {
            this.emailVerified = emailVerified;
        }

        /**
         *
         * @return
         * The active
         */
        public Integer getActive() {
            return active;
        }

        /**
         *
         * @param active
         * The active
         */
        public void setActive(Integer active) {
            this.active = active;
        }

        /**
         *
         * @return
         * The userReferralCode
         */
        public String getUserReferralCode() {
            return userReferralCode;
        }

        /**
         *
         * @param userReferralCode
         * The user_referral_code
         */
        public void setUserReferralCode(String userReferralCode) {
            this.userReferralCode = userReferralCode;
        }

        /**
         *
         * @return
         * The created
         */
        public String getCreated() {
            return created;
        }

        /**
         *
         * @param created
         * The created
         */
        public void setCreated(String created) {
            this.created = created;
        }

        /**
         *
         * @return
         * The modified
         */
        public String getModified() {
            return modified;
        }

        /**
         *
         * @param modified
         * The modified
         */
        public void setModified(String modified) {
            this.modified = modified;
        }

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

    }


    public class Response {

        @SerializedName("auth")
        @Expose
        private Auth auth;
        @SerializedName("dp_path")
        @Expose
        private String dpPath;

        /**
         *
         * @return
         * The auth
         */
        public Auth getAuth() {
            return auth;
        }

        /**
         *
         * @param auth
         * The auth
         */
        public void setAuth(Auth auth) {
            this.auth = auth;
        }

        /**
         *
         * @return
         * The dpPath
         */
        public String getDpPath() {
            return dpPath;
        }

        /**
         *
         * @param dpPath
         * The dp_path
         */
        public void setDpPath(String dpPath) {
            this.dpPath = dpPath;
        }

    }

    public class Status {

        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("message")
        @Expose
        private String message;

        /**
         *
         * @return
         * The code
         */
        public String getCode() {
            return code;
        }

        /**
         *
         * @param code
         * The code
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         *
         * @return
         * The message
         */
        public String getMessage() {
            return message;
        }

        /**
         *
         * @param message
         * The message
         */
        public void setMessage(String message) {
            this.message = message;
        }

    }




}
