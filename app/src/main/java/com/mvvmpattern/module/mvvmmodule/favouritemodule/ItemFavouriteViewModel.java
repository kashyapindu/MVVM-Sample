package com.mvvmpattern.module.mvvmmodule.favouritemodule;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.mvvmpattern.model.response.GetFavouriteResponse;
import com.mvvmpattern.module.mvvmmodule.BaseViewModel;
import com.mvvmpattern.module.mvvmmodule.MvvmActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by indu_kashyap on 12/8/2016.
 */

public class ItemFavouriteViewModel implements BaseViewModel {
    GetFavouriteResponse.StatusBean.ResponseBean mResponseBean;
    Context mContext;

    /**
     * Constructor
     * @param responseBean
     * @param context
     */
    public ItemFavouriteViewModel(GetFavouriteResponse.StatusBean.ResponseBean responseBean, Context context) {
        mResponseBean = responseBean;
        mContext = context;


    }

    /**
     * on item click
     * @return
     */
    public View.OnClickListener onFavClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v);
            }
        };

    }

    /**
     * get name
     * @return
     */
    public String getName() {

        return mResponseBean.getCategory() + " " + mResponseBean.getSummary();
    }

    /**
     * get Weight
     * @return
     */
    public String getWeight() {
        return String.valueOf(mResponseBean.getApprox_weight() + " " + "Kg");
    }

    /**
     * Get trip
     * @return
     */
    public String getTrip() {
        return mResponseBean.getSource_location() + " " + " to" + " " + mResponseBean.getDestination_location();
    }

    /**
     * get Image
     * @return
     */
    public String getPictureProfile() {
        return mResponseBean.getDestination_image();
    }

    /**
     * get source image
     * @return
     */
    public String getDestinationPicture() {
        return mResponseBean.getSource_image();
    }

    /**
     * get fav
     * @return
     */
    public int getIsFavourite() {
        return mResponseBean.getFlag();
    }

    /**
     * set image
     * @param imageView
     * @param url
     */
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    /**
     * on item click
     * @param view
     */
    public void onItemClick(View view) {
        mContext.startActivity(MvvmActivity.newIntent(mContext));
    }


    @Override
    public void destroy() {

    }

    /**
     * set data
     * @param responseBean
     */
    public void setRepository(GetFavouriteResponse.StatusBean.ResponseBean responseBean) {
        this.mResponseBean = responseBean;
        notifyAll();
    }


}
