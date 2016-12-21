package com.mvvmpattern.module.mvvmmodule.favouritemodule;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mvvmpattern.api.ApiClient;
import com.mvvmpattern.api.ApiConstants;
import com.mvvmpattern.model.request.GetFavouriteRequest;
import com.mvvmpattern.model.response.GetFavouriteResponse;
import com.mvvmpattern.module.mvvmmodule.BaseViewModel;
import com.mvvmpattern.utils.DialogsUtil;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by indu_kashyap on 12/8/2016.
 */

public class FavouriteViewModel implements BaseViewModel {
    private Context mContext;
    private DialogsUtil mDialogsUtil = new DialogsUtil(mContext);
    private String userId = "267";
    private String isTraveller = "1";
    private GetFavouriteResponse getFavouriteResponse;
    private DataListener mDataListener;
    public ObservableInt recyclerViewVisibility;
    private ObservableInt defaultTextVisibilty;
    private ObservableInt defaultGetFavTextVisibilty;
    private boolean isFragment;

    /**
     * create constructor
     *
     * @param context
     */
    public FavouriteViewModel( Context context) {
        mContext = context;
        recyclerViewVisibility = new ObservableInt(View.INVISIBLE);

        if (!isFragment) {
            defaultGetFavTextVisibilty = new ObservableInt(View.GONE);
            defaultTextVisibilty = new ObservableInt(View.VISIBLE);
        } else {
            defaultTextVisibilty = new ObservableInt(View.GONE);
            defaultGetFavTextVisibilty = new ObservableInt(View.VISIBLE);
        }


    }

    /**
     * get default text visibility
     * @return
     */
    public ObservableInt getDefaultGetFavTextVisibilty() {
        isFragment = false;
        return defaultGetFavTextVisibilty;
    }

    /**
     * set default text visibility
     * @param defaultGetFavTextVisibilty
     */
    public void setDefaultGetFavTextVisibilty(ObservableInt defaultGetFavTextVisibilty) {
        isFragment = false;
        this.defaultGetFavTextVisibilty = defaultGetFavTextVisibilty;
    }
    /**
     * get default text visibility
     * @return
     */
    public ObservableInt getDefaultTextVisibilty() {
        isFragment = true;
        return defaultTextVisibilty;
    }
    /**
     * set default text visibility
     * @param defaultTextVisibilty
     */
    public void setDefaultTextVisibilty(ObservableInt defaultTextVisibilty) {
        isFragment = true;
        this.defaultTextVisibilty = defaultTextVisibilty;
    }

    /**
     * Pull to refresh
     * @return
     */
    public RecyclerView.OnScrollListener hasMoreData() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                getFavourite();

            }
        };

    }

    /**
     * Back button listener
     * @return
     */
    public View.OnClickListener onBackClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();
            }
        };
    }

    /**
     * set Finish on back
     */
    private void backButton() {
        ((Activity) (mContext)).finish();
    }


    //get post api method
    public void getFavourite() {
        mDialogsUtil.showLoading(mContext);
        GetFavouriteRequest request = new GetFavouriteRequest();
        request.setUser_id(userId);
        request.setIsTraveller(isTraveller);

        ApiClient.getRestService(ApiConstants.BASE_URL).getFaourite(request).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<GetFavouriteResponse>() {
                    @Override
                    public void onCompleted() {
                        mDialogsUtil.hideLoading(mContext);
                        if (getFavouriteResponse.getStatus().getMessage().equalsIgnoreCase("success")) {
                            if (getFavouriteResponse.getStatus().getResponse().size() > 0) {
                                recyclerViewVisibility.set(View.VISIBLE);
                                getDefaultTextVisibilty().set(View.GONE);


                            } else {
                                recyclerViewVisibility.set(View.GONE);
                                getDefaultTextVisibilty().set(View.VISIBLE);

                            }


                        } else {

                            mDialogsUtil.hideLoading(mContext);
                            recyclerViewVisibility.set(View.GONE);

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        mDialogsUtil.hideLoading(mContext);
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                    }


                    @Override
                    public void onNext(GetFavouriteResponse getFavouriteResponse) {
                        mDialogsUtil.hideLoading(mContext);
                        FavouriteViewModel.this.getFavouriteResponse = getFavouriteResponse;
                        if (mDataListener != null) {
                            mDataListener.onGetFavoutiteList(getFavouriteResponse.getStatus().getResponse());
                        }

                    }
                });
    }

    @Override
    public void destroy() {

    }

    /**
     * ser interface to send list
     */
    public interface DataListener {
        void onGetFavoutiteList(List<GetFavouriteResponse.StatusBean.ResponseBean> responseBean);


    }

}
