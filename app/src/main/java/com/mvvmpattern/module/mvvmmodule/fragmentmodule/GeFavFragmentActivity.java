package com.mvvmpattern.module.mvvmmodule.fragmentmodule;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mvvmpattern.R;
import com.mvvmpattern.databinding.ActivityGeFavFragmentBinding;
import com.mvvmpattern.module.mvvmmodule.favouritemodule.FavouriteAdapter;
import com.mvvmpattern.module.mvvmmodule.favouritemodule.FavouriteViewModel;

public class GeFavFragmentActivity extends AppCompatActivity {
    private ActivityGeFavFragmentBinding mBinding;
    private FavouriteViewModel mFavouriteViewModel;
    FavouriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ge_fav_fragment);
        //initialise view model
        mFavouriteViewModel = new FavouriteViewModel(this);
        //Bind view with view model
        mBinding.setFavouriteViewModel(mFavouriteViewModel);
        //open fragment
        openFragment();

    }

    /**
     * set fragment
     */
    private void openFragment() {
        Fragment newFragment = new GetFavFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(mBinding.container.getId(), newFragment);
        transaction.commit();
        mBinding.tvTitle.setText("Get Favourite Fragment");

    }
    /**
     * cancel view model
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFavouriteViewModel.destroy();
    }
    /**
     * create intent
     * @param context
     * @return
     */
    public static Intent createIntent(Activity context) {
        Intent intent = new Intent(context, GeFavFragmentActivity.class);
        return intent;
    }


}
