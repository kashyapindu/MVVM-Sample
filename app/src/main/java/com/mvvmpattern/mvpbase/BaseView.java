package com.mvvmpattern.mvpbase;

/**
 * Created by saveen_dhiman on 05-October-16.
 * <p/>
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface BaseView {

    void showProgress();

    void hideProgress();


    void onException(String message);


}