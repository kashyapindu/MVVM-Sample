package com.mvvmpattern.module.mvpmodule;

import com.mvvmpattern.mvpbase.BaseView;

/**
 * Created by indu_kashyap on 12/6/2016.
 */

public interface MvpView extends BaseView {
    void onLoginSuccess();
    void OnSuccess();

    void onError(Object throwable);
}


