package com.mvvmpattern.api;

/**
 * Created by saveen_dhiman on 05-October-16.
 * contains all the constants related to API's
 */
public interface ApiConstants {
    String STATUS_SUCCESS = "200";
    int STATUS_SUCCESS1 = 200;
    int RESULT_CODE = 1;
    String STATUS_SUCCESS_String = "Success";
    String GOOGLE_SUCCESS = "OK";
    String STATUS_SESSION_EXPIRE = "203";
    String BASE_URL = "http://softprodigyphp.in/";/* "http://staging.softprodigy.in/";*/
    String GOOGLE_BASE_URL="https://maps.googleapis.com/";

    //    String CONTROLLER = "/TravelerApp/";
    String CONTROLLER = "/TravelerApp2/";

    String LOGIN = CONTROLLER + "/users/user_login";
    String GET_FAVOURITE=CONTROLLER + "users/get_favourite";
    String PAGINATION_LIMIT = "10";



}
