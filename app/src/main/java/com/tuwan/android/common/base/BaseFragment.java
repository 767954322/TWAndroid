package com.tuwan.android.common.base;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by gumenghao on 2018/5/7.
 */
public abstract class BaseFragment extends Fragment {

    protected String TAG = getClass().getSimpleName();
    protected Activity activity;
    protected Unbinder mUnbinder;
    protected View rootView;

    public BaseFragment() {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(getLayoutResId(), container, false);
            initButterKnife();
            initExtraBundle();
            initData(savedInstanceState);
            initListener();
        }
        return rootView;
    }

    /**
     * 获取布局的Id
     */
    protected abstract int getLayoutResId();

    private void initButterKnife() {

        //绑定并且返回一个Unbinder值用来解绑
        mUnbinder = ButterKnife.bind(this, rootView);

    }

    /**
     * 获取bundle数据
     */
    protected void initExtraBundle() {

    }

    /**
     * 初始化数据操作
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 设置监听
     */
    protected void initListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //解绑
        mUnbinder.unbind();

    }

}
