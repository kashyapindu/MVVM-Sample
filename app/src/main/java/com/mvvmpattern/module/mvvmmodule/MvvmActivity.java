package com.mvvmpattern.module.mvvmmodule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

import com.mvvmpattern.R;
import com.mvvmpattern.databinding.ActivityMvvmBinding;

public class MvvmActivity extends Activity implements MvvmViewModel.DataListener {
    ActivityMvvmBinding mvvmBinding;
    MvvmViewModel mvvmViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view
        mvvmBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
       // initialise view model
        mvvmViewModel = new MvvmViewModel(this, this);
        //bind view with view model
        mvvmBinding.setMvvmViewModel(mvvmViewModel);

    }


    /**
     * setr error message
     * @param message
     */
    @Override
    public void setErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * set input type
     * @param shown
     */
    @Override
    public void setInputType(String shown) {
        if (shown.equalsIgnoreCase("Show")) {
            mvvmBinding.etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            mvvmBinding.etPassword.setInputType(129);
        }


    }

    /**
     * cancel view model
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvvmViewModel.destroy();
    }

    /**
     * create intent
     * @param context
     * @return
     */
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MvvmActivity.class);
        return intent;

    }


}
