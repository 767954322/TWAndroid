package com.tuwan.android.home.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.tuwan.android.R;
import butterknife.BindView;
import com.tuwan.android.home.data.InBean;
import com.tuwan.android.common.base.BaseActivity;
import com.tuwan.android.common.utils.GlideManager;
import com.tuwan.android.home.contract.TestContract;
import com.tuwan.android.home.presenter.TestPresenter;

public class MainActivity extends BaseActivity

        implements TestContract.View {

    @Override
    protected int getLayoutResId() {

        return R.layout.activity_main;

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mTestPresenter = new TestPresenter(this, this);

        mTestPresenter.getTestData();

    }

    @Override
    public void onRefreshTestData(boolean requestStatus, InBean result) {

        if (requestStatus) {

            if (null != result && result.getLineg().size() > 0) {

                tvTest.setText(result.getLineg().get(0).getTitle());
                String imageUrl = "http://www.zanyiba.com/uploads/141229/1-141229231413K7.jpg";
                GlideManager.glideLoader(MainActivity.this, imageUrl, ivTest, GlideManager.TAG_ROUND);

            }

        } else {

            //TODO 请求失败
            tvTest.setText("数据获取失败");

        }

    }

    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.iv_test)
    ImageView ivTest;

    private TestPresenter mTestPresenter;

}
