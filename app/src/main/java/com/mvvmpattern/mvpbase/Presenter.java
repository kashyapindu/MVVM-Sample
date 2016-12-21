package com.mvvmpattern.mvpbase;

/**
 * Created by saveen_dhiman on 05-October-16.
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the BaseView type that wants to be attached with.
 */
public interface Presenter<V extends BaseView> {

    void attachView(V baseView);

    void detachView();
}