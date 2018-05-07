package com.tuwan.android.common.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.tuwan.android.R;

import android.content.res.Resources;
import android.content.pm.ActivityInfo;

import com.jaeger.library.StatusBarUtil;

import android.content.res.Configuration;

import com.tuwan.android.common.utils.UIUtils;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by gumenghao on 2018/5/7.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initStatusBar();

        setContentView(getLayoutResId());

        ButterKnife.bind(this);

        initExtraBundle();

        initData(savedInstanceState);

        initListener();

    }

    //设置顶部状态栏
    private void initStatusBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setColor(this, UIUtils.getColor(R.color.status_bar_color), 0);

        } else {

            StatusBarUtil.setColor(this, UIUtils.getColor(R.color.status_bar_color), 0);

        }

    }

    /**
     * 获取布局的Id
     */
    protected abstract int getLayoutResId();

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
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
