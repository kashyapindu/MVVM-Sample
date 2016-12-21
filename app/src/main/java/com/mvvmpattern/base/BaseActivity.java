package com.mvvmpattern.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.mvvmpattern.R;
import com.mvvmpattern.api.RetrofitException;

import butterknife.ButterKnife;
import retrofit2.Response;

/**
 * Created by Indu Kashyap on 05-October-16.
 * To be extended by all Activities
 */
public abstract class BaseActivity extends AppCompatActivity{

    private AlertDialog mAlertDialog;
    private Dialog connectionDialog;
/*
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        //bind view here for all activities extending this Activity

       /* setUpToolbar();
        if (mToolbar != null)
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });*/

    }


    /**
     * get layout to inflate
     */
    public abstract
    @LayoutRes
    int getLayout();

    public Throwable handleError(RetrofitException retrofitError) {
        retrofitError.printStackTrace();
        String message = MvvmApplication.getContext().getString(R.string.something_went);
        String title = "Oops!";
        if (retrofitError.getKind().equals(RetrofitException.Kind.NETWORK)) {
            title = "No Internet Connection!";
            message = MvvmApplication.getContext().getString(R.string.network_failed);
        } else {
            try {
                Response r = retrofitError.getResponse();
                if (r != null && r.code()== 408) {
                    title = "Alert!";
                    message = MvvmApplication.getContext().getString(R.string.timed_out);
                } else if (r.code() == 500) {
                    message = MvvmApplication.getContext().getString(R.string.internal_error);
                    title = "Sorry!";
                } else if (r.code() == 404) {
                    title = MvvmApplication.getContext().getString(R.string.alert);
                    message = MvvmApplication.getContext().getString(R.string.something_went);
                }
            } catch (Exception exception) {
                // something went wrong
                message = MvvmApplication.getContext().getString(R.string.something_went);
                title = MvvmApplication.getContext().getString(R.string.alert);
            }
        }

        return new Exception(message + "-" + title);
    }
   /* public void setUpToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            ActionBar bar = getSupportActionBar();
            if (bar != null) {
                bar.setDisplayShowHomeEnabled(true);
                bar.setDisplayHomeAsUpEnabled(true);
                bar.setDisplayShowTitleEnabled(false);
                bar.setHomeButtonEnabled(true);
            }
            mToolbar.setTitle(getToolbarTitle());
        }
    }*/

    /**
     * @return return toolbar title
     */
    protected abstract String getToolbarTitle();




}