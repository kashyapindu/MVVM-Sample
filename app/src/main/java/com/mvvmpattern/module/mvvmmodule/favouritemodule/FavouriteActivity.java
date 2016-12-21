package com.mvvmpattern.module.mvvmmodule.favouritemodule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mvvmpattern.R;
import com.mvvmpattern.databinding.ActivityFavouriteBinding;
import com.mvvmpattern.model.response.GetFavouriteResponse;

import java.util.List;

public class FavouriteActivity extends AppCompatActivity implements FavouriteViewModel.DataListener, FavouriteViewModelContract.MainView {
    private ActivityFavouriteBinding mBinding;
    private FavouriteViewModel mFavouriteViewModel;
    private FavouriteViewModelContract.MainView mMainView = this;
    FavouriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get Reference of the layout of the activity
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_favourite);
        //initialise /create obect of view model class
        mFavouriteViewModel = new FavouriteViewModel(this);
        //set bind view with view model class
        mBinding.setFavouriteViewModel(mFavouriteViewModel);
        //set recycler view
        setupRecyclerView(mBinding.rvFavouriteList);
        //get favourite list from web
        mFavouriteViewModel.getFavourite();


    }

    /**
     * set recycler view
     * @param rvFavouriteList
     */
    private void setupRecyclerView(RecyclerView rvFavouriteList) {

        FavouriteAdapter adapter = new FavouriteAdapter();
        rvFavouriteList.setAdapter(adapter);
        rvFavouriteList.setLayoutManager(new LinearLayoutManager(this));
    }


    /**
     * Create intent
     * @param context
     * @return
     */
    public static Intent createIntent(Activity context) {
        Intent intent = new Intent(context, FavouriteActivity.class);
        return intent;
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
     * get activity context
     * @return
     */
    @Override
    public Context getContext() {
        return FavouriteActivity.this;
    }

    /**
     * set adapter on get data from listener
     * @param responseBean
     */
    @Override
    public void onGetFavoutiteList(List<GetFavouriteResponse.StatusBean.ResponseBean> responseBean) {
        adapter = (FavouriteAdapter) mBinding.rvFavouriteList.getAdapter();
        adapter.setRepositories(responseBean);
        adapter.notifyDataSetChanged();


    }


}
