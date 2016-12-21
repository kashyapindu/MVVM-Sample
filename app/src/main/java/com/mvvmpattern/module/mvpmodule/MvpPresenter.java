package com.mvvmpattern.module.mvpmodule;

import android.content.Context;

import com.mvvmpattern.api.ApiClient;
import com.mvvmpattern.api.ApiConstants;
import com.mvvmpattern.model.request.LoginRequest;
import com.mvvmpattern.model.response.UserDetailResponse;
import com.mvvmpattern.mvpbase.BasePresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by indu_kashyap on 12/6/2016.
 */

public class MvpPresenter extends BasePresenter<MvpView> {


    public MvpPresenter(Context context) {
    }

    public void login(String password, String email, String deviceType, String deviceId) {
        getBaseView().showProgress();
        LoginRequest request = new LoginRequest(password,email,deviceType,deviceId);


        ApiClient.getRestService(ApiConstants.BASE_URL).login(request).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<UserDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getBaseView().hideProgress();
                            getBaseView().onError(e);
                        }
                    }

                    @Override
                    public void onNext(UserDetailResponse userDetailResponse) {
                        if (isViewAttached()) {
                            getBaseView().hideProgress();

                            if (userDetailResponse.getStatus().getCode().toString().equalsIgnoreCase(ApiConstants.STATUS_SUCCESS)) {
                                getBaseView().onLoginSuccess();

                            } else {

                                getBaseView().onException(userDetailResponse.getStatus().getMessage().toString());
                            }
                        }
                    }
                });


    }
}
