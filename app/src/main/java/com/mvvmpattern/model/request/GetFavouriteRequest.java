package com.mvvmpattern.model.request;

import com.google.gson.Gson;

/**
 * Created by indu_kashyap on 11/8/2016.
 */

public class GetFavouriteRequest {


    /**
     * user_id : 158
     * isTraveller : 1
     */

    private String user_id;
    private String isTraveller;

    public static GetFavouriteRequest objectFromData(String str) {

        return new Gson().fromJson(str, GetFavouriteRequest.class);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIsTraveller() {
        return isTraveller;
    }

    public void setIsTraveller(String isTraveller) {
        this.isTraveller = isTraveller;
    }
}
