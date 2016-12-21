package com.mvvmpattern.module.mvvmmodule.favouritemodule;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvvmpattern.R;
import com.mvvmpattern.databinding.AdapterSearchviewBinding;
import com.mvvmpattern.model.response.GetFavouriteResponse;
import com.mvvmpattern.utils.ImageUtility;

import java.util.Collections;
import java.util.List;

/**
 * Created by indu_kashyap on 11/8/2016.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ItemViewHolder> {

    private List<GetFavouriteResponse.StatusBean.ResponseBean> mResponseBeanList;
    ImageUtility mImageUtility;
    PreferenceManager mPreferenceManager;
    private Context mContext;
    ItemFavouriteViewModel mItemFavouriteViewModel;


    public FavouriteAdapter() {
        this.mResponseBeanList = Collections.emptyList();
    }

    public FavouriteAdapter(List<GetFavouriteResponse.StatusBean.ResponseBean> favouriteArrayLists) {
        this.mResponseBeanList = favouriteArrayLists;
    }

    public void setRepositories(List<GetFavouriteResponse.StatusBean.ResponseBean> favouriteArrayList) {
        this.mResponseBeanList = favouriteArrayList;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //bind view of layout with view model class
        AdapterSearchviewBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_searchview,
                parent,
                false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.bindRepository(mResponseBeanList.get(position));
        holder.binding.llsearchresultFav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(mResponseBeanList.get(position));
                notifyItemRemoved(position);

            }

        });


    }

    /**
     * delete items
     *
     * @param data
     */
    private void remove(GetFavouriteResponse.StatusBean.ResponseBean data) {
        try {

            int position = mResponseBeanList.indexOf(data);
            mResponseBeanList.remove(position);
            notifyItemRemoved(position);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {

        return mResponseBeanList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        final AdapterSearchviewBinding binding;

        public ItemViewHolder(AdapterSearchviewBinding binding) {
            super(binding.rladapterMainlayout);
            this.binding = binding;


        }

        /**
         * get data
         *
         * @param responseBean
         */
        void bindRepository(GetFavouriteResponse.StatusBean.ResponseBean responseBean) {
            if (binding.getItemFavouriteViewModel() == null) {
                binding.setItemFavouriteViewModel(new ItemFavouriteViewModel(responseBean, itemView.getContext()));
            } else {
                binding.getItemFavouriteViewModel().setRepository(responseBean);

            }
        }


    }


}


