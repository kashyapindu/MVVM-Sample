package com.mvvmpattern.api;


import com.google.gson.Gson;
import com.mvvmpattern.model.request.GetFavouriteRequest;
import com.mvvmpattern.model.request.LoginRequest;
import com.mvvmpattern.model.response.GetFavouriteResponse;
import com.mvvmpattern.model.response.UserDetailResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by indu Ksahyap on 06-december-16.
 * Contains API methods to be consumed
 */

    public class ApiClient{

    public static RestService getRestService(String base_url) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();

        return retrofit.create(RestService.class);
    }




    public static OkHttpClient getOkHttpClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(httpLoggingInterceptor);
        builder.readTimeout(180, TimeUnit.SECONDS);
        builder.connectTimeout(180, TimeUnit.SECONDS);
        return builder.build();
    }

    public interface RestService {


        @POST(ApiConstants.LOGIN)
        Observable<UserDetailResponse> login(@Body LoginRequest loginRequest);


        //Get Favourite API
        @POST(ApiConstants.GET_FAVOURITE)
        Observable<GetFavouriteResponse> getFaourite(@Body GetFavouriteRequest getFavouriteRequest);



    }
}
