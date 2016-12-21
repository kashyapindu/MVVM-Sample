package com.mvvmpattern.module.mvvmmodule.favouritemodule;

import android.content.Context;

/**
 * Created by indu_kashyap on 12/12/2016.
 */

public interface FavouriteViewModelContract {

    interface MainView {

        Context getContext();

    }

    interface ViewModel {

        void destroy();
    }
}
