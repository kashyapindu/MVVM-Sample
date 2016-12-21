package com.mvvmpattern.mvpbase;

/**
 * Created by saveen_dhiman on 05-October-16.
 * <p/>
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {

    private T mBaseView;

    @Override
    public void attachView(T baseView) {
        mBaseView = baseView;
    }

    @Override
    public void detachView() {
        mBaseView = null;
    }

    public boolean isViewAttached() {
        return mBaseView != null;
    }

    public T getBaseView() {
        return mBaseView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseView) before" +
                    " requesting data to the Presenter");
        }
    }
}