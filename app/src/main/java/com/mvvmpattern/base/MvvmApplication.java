package com.mvvmpattern.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by saveen_dhiman on 05-October-16.
 * Initialization of required libraries
 */

public class MvvmApplication extends Application {


    private static Context mContext;
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();



    }
}


