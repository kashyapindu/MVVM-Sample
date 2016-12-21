package com.mvvmpattern.module.mvvmmodule;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.mvvmpattern.api.ApiClient;
import com.mvvmpattern.api.ApiConstants;
import com.mvvmpattern.model.request.LoginRequest;
import com.mvvmpattern.model.response.UserDetailResponse;
import com.mvvmpattern.module.mvvmmodule.favouritemodule.FavouriteActivity;
import com.mvvmpattern.module.mvvmmodule.fragmentmodule.GeFavFragmentActivity;
import com.mvvmpattern.utils.DialogsUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by indu_kashyap on 12/6/2016.
 */

public class MvvmViewModel implements BaseViewModel {
    Context mContext;
    DialogsUtil mDialogsUtil = new DialogsUtil(mContext);
    private Toolbar toolbar;

    private String emailValue = "";
    private String deviceType = "a";
    private String deviceId = "fQ1omHQEARc:APA91bE28HpMlcsIHs_EdBVp07gvm1FuQIXidIwuE_bhlDOY1EB2JX7wRaaEIk9zMgtpbk84wEnEyTozr6Lzdgu8PzLErqDsN8PooSm41n8EW6n8wsZLTYDjAjh0sOGSYAmgGCpAL6Aj";
    private UserDetailResponse mUserDetailResponse;
    private DataListener mDataListener;
    private ObservableInt showPasswordVisibility;
    public ObservableField<String> showPassword = new ObservableField<>();
    public ObservableField<String> passwordValue = new ObservableField<>();

    /**
     * constructor
     *
     * @param context
     * @param dataListener
     */
    public MvvmViewModel(Context context, DataListener dataListener) {
        mContext = context;
        mDataListener = dataListener;
        showPassword.set("Show");


    }

    /**
     * get password
     *
     * @return
     */
    public ObservableField<String> getPasswordValue() {
        return passwordValue;
    }

    /**
     * set password
     *
     * @param passwordValue
     */
    public void setPasswordValue(ObservableField<String> passwordValue) {
        this.passwordValue = passwordValue;
    }

    /**
     * get show password
     *
     * @return
     */
    public ObservableField<String> getShowPassword() {
        return showPassword;
    }

    /**
     * set show password
     *
     * @param showPassword
     */
    public void setShowPassword(ObservableField<String> showPassword) {
        this.showPassword = showPassword;
    }

    /**
     * get toolbar
     *
     * @return
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * set toolbar navigation click listener
     *
     * @param toolbar
     */
    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        toolbar.setNavigationOnClickListener((OnClickListener) onBackButtonClick());
    }


    /**
     * Login click Listener
     *
     * @return
     */
    public OnClickListener onLoginClick() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                login(getPasswordValue().get(), emailValue, deviceType, deviceId);


            }
        };
    }

    /**
     * back button click listener
     *
     * @return
     */
    public OnClickListener onBackButtonClick() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();
            }
        };
    }

    /**
     * set back button
     */
    private void backButton() {
        ((Activity) (mContext)).finish();
    }

    /**
     * get password visibility
     *
     * @return
     */
    public ObservableInt getShowPasswordVisibility() {
        return showPasswordVisibility;
    }

    /**
     * set Password visibility
     *
     * @param showPasswordVisibility
     */
    public void setShowPasswordVisibility(ObservableInt showPasswordVisibility) {
        this.showPasswordVisibility = showPasswordVisibility;

    }

    /**
     * set email
     *
     * @param emailValue
     */
    public void setEmailValue(String emailValue) {
        this.emailValue = emailValue;
    }

    /**
     * show button click listener
     *
     * @return
     */
    public View.OnClickListener onShowClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShow();

            }
        };
    }

    /**
     * set Show click listener
     */
    private void onClickShow() {
        if (mDataListener != null) {
            mDataListener.setInputType(getShowPassword().get());
            if (getShowPassword().get().equalsIgnoreCase("Show")) {
                getShowPassword().set("Hide");
            } else {
                getShowPassword().set("Show");
            }


        }
    }

    /**
     * get email
     *
     * @return
     */
    public String getEmailValue() {
        return emailValue;
    }

    /**
     * Forgot password click listener
     *
     * @return
     */
    public OnClickListener onForgotPasswordClick() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                onForgotClick();

            }
        };
    }

    /**
     * Forgot click
     */
    private void onForgotClick() {
        mContext.startActivity(GeFavFragmentActivity.createIntent((Activity) (mContext)));


    }

    /**
     * set Login
     *
     * @param password
     * @param email
     * @param deviceType
     * @param deviceId
     */
    public void login(String password, String email, String deviceType, String deviceId) {

        mDialogsUtil.showLoading(mContext);
        LoginRequest request = new LoginRequest(password, email, deviceType, deviceId);
        ApiClient.getRestService(ApiConstants.BASE_URL).login(request).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<UserDetailResponse>() {
                    @Override
                    public void onCompleted() {
                        mDialogsUtil.hideLoading(mContext);

                        if (mUserDetailResponse.getStatus().getMessage().equalsIgnoreCase("success")) {
                            if (mUserDetailResponse.getResponse() != null) {
                                mContext.startActivity(FavouriteActivity.createIntent((Activity) (mContext)));

                            }
                        } else {
                            Toast.makeText(mContext, mUserDetailResponse.getStatus().getMessage(), Toast.LENGTH_LONG).show();

                        }
                        ;
                    }


                    @Override
                    public void onError(Throwable e) {
                        mDialogsUtil.hideLoading(mContext);
                        if (mDataListener != null) {
                            mDataListener.setErrorMessage(String.valueOf(e.getMessage()));
                        }


                    }

                    @Override
                    public void onNext(UserDetailResponse userDetailResponse) {
                        mDialogsUtil.hideLoading(mContext);
                        MvvmViewModel.this.mUserDetailResponse = userDetailResponse;
                    }
                });


    }

    @Override
    public void destroy() {
        setShowPassword(null);


    }

    /**
     * set interface
     */
    public interface DataListener {
        void setErrorMessage(String message);

        void setInputType(String shown);

    }
}
