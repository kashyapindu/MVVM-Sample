package com.mvvmpattern.module.mvpmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mvvmpattern.R;
import com.mvvmpattern.base.BaseActivity;
import com.mvvmpattern.model.response.UserDetailResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MvpActivity extends BaseActivity implements MvpView {

    @BindView(R.id.back_btn)
    ImageView mBackBtn;
    @BindView(R.id.tv_forgot_pass)
    TextView mTvForgotPass;
    @BindView(R.id.heading)
    TextView mHeading;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.nav_view)
    View mNavView;
    @BindView(R.id.tv_password)
    TextView mTvPassword;
    @BindView(R.id.tv_pass_show)
    TextView mTvPassShow;
    @BindView(R.id.rlcontainer)
    RelativeLayout mRlcontainer;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.nav_view1)
    View mNavView1;
    @BindView(R.id.et_email_address)
    EditText mEtEmailAddress;
    @BindView(R.id.ll_user_details)
    RelativeLayout mLlUserDetails;
    @BindView(R.id.sc_login)
    ScrollView mScLogin;
    @BindView(R.id.iv_signIn)
    ImageView mIvSignIn;
    @BindView(R.id.activity_mvp)
    RelativeLayout mActivityMvp;
    String deviceId = "fQ1omHQEARc:APA91bE28HpMlcsIHs_EdBVp07gvm1FuQIXidIwuE_bhlDOY1EB2JX7wRaaEIk9zMgtpbk84wEnEyTozr6Lzdgu8PzLErqDsN8PooSm41n8EW6n8wsZLTYDjAjh0sOGSYAmgGCpAL6Aj";
    MvpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new MvpPresenter(this);
        mPresenter.attachView(this);
        mEtEmailAddress.setText("saveendhiman@gmail.com");
        mEtPassword.setText("welcome@123");

    }

    @Override
    public int getLayout() {
        return R.layout.activity_mvp;
    }

    @Override
    protected String getToolbarTitle() {
        return "Login";
    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void OnSuccess() {

    }

    @Override
    public void onError(Object throwable) {
        Toast.makeText(this, (CharSequence) throwable, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onException(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @OnClick(R.id.iv_signIn)
    public void onClick() {
        Log.e("tag", "onClick: "+"Clicked"+(mEtPassword.getText().toString()+" "+ mEtEmailAddress.getText().toString().trim()+ " "+"a"+" "+ deviceId ));
        mPresenter.login(mEtPassword.getText().toString(), mEtEmailAddress.getText().toString().trim(), "a", deviceId);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public static Intent createIntent(Context context, UserDetailResponse repositories) {
        Intent intent=new Intent(context,MvpActivity.class);
        intent.putExtra("Repo.medel",  repositories);
        return intent;
    }

}
