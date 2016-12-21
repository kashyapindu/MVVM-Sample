package com.mvvmpattern.module.mvvmmodule.fragmentmodule;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvvmpattern.R;
import com.mvvmpattern.databinding.FragmentGetFavBinding;
import com.mvvmpattern.model.response.GetFavouriteResponse;
import com.mvvmpattern.module.mvvmmodule.favouritemodule.FavouriteAdapter;
import com.mvvmpattern.module.mvvmmodule.favouritemodule.FavouriteViewModel;

import java.util.List;

public class GetFavFragment extends Fragment implements FavouriteViewModel.DataListener {
    FragmentGetFavBinding mGetFavBinding;
    FavouriteViewModel mViewModel;
    FavouriteAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_fav, container, false);
        //Set view
        mGetFavBinding = DataBindingUtil.bind(view);
        //initialise view model
        mViewModel = new FavouriteViewModel(getActivity());
        //bind view with view model
        mGetFavBinding.setFavouriteViewModel(mViewModel);
        //get data
        mViewModel.getFavourite();
        //set recycer view
        setupRecyclerView(mGetFavBinding.rvFavouriteList);

        return view;
    }

    /**
     * set recycler view
     * @param rvFavouriteList
     */
    private void setupRecyclerView(RecyclerView rvFavouriteList) {

        FavouriteAdapter adapter = new FavouriteAdapter();
        rvFavouriteList.setAdapter(adapter);
        rvFavouriteList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    /**
     * set dapter
     * @param responseBean
     */
    @Override
    public void onGetFavoutiteList(List<GetFavouriteResponse.StatusBean.ResponseBean> responseBean) {
        adapter = (FavouriteAdapter) mGetFavBinding.rvFavouriteList.getAdapter();
        adapter.setRepositories(responseBean);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.destroy();
    }
}
