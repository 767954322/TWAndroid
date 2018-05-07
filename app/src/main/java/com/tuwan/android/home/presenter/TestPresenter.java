package com.tuwan.android.home.presenter;

import android.app.Activity;

import com.tuwan.android.common.net.ApiService;
import com.tuwan.android.common.net.CommonObserver;
import com.tuwan.android.common.net.ServiceFactory;
import com.tuwan.android.home.contract.TestContract;
import com.tuwan.android.home.data.InBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gumenghao on 2018/5/7.
 */

public class TestPresenter implements TestContract.Presenter {


    private Activity mContext;
    private TestContract.View mView;

    public TestPresenter(TestContract.View mView, Activity context) {

        this.mView = mView;
        this.mContext = context;

    }

    @Override
    public void getTestData() {

        ServiceFactory.getNoCacheInstance()
                .createService(ApiService.class)
                .test()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<InBean>() {
                    @Override
                    public void onNext(@NonNull InBean result) {
                        super.onNext(result);

                        mView.onRefreshTestData(true, result);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);

                        mView.onRefreshTestData(false, null);

                    }
                });

    }

}
