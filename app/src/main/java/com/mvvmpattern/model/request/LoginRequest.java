package com.mvvmpattern.model.request;

/**
 * Created by indu_kashyap on 12/6/2016.
 */

public class LoginRequest {


    /**
     * email : chinagoyal@gmail.com
     * password : soft@123
     * device_type : a
     * device_id : fQ1omHQEARc:APA91bE28HpMlcsIHs_EdBVp07gvm1FuQIXidIwuE_bhlDOY1EB2JX7wRaaEIk9zMgtpbk84wEnEyTozr6Lzdgu8PzLErqDsN8PooSm41n8EW6n8wsZLTYDjAjh0sOGSYAmgGCpAL6Aj
     */

    private String email;
    private String password;
    private String device_type;
    private String device_id;

    public LoginRequest(String password, String email, String device_type, String device_id) {
        this.password = password;
        this.email = email;
        this.device_type = device_type;
        this.device_id = device_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }


}
