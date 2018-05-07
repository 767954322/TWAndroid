package com.tuwan.android.common.net;


import com.tuwan.android.common.net.converter.MyGsonConverterFactory;
import com.tuwan.android.common.net.utils.OkHttpProvider;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 获取ServiceFactory对象的方法请慎重对待，否则将会导致错误
 */
public class ServiceFactory {

    public ServiceFactory() {
        mOkHttpClient = OkHttpProvider.getDefaultOkHttpClient();
    }

    /**
     * 没有网络的时候将读取缓存
     */
    public static ServiceFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceFactory();
        }
        INSTANCE.mOkHttpClient = OkHttpProvider.getDefaultOkHttpClient();
        return INSTANCE;
    }

    public static ServiceFactory getNoCacheInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceFactory();
        }
        INSTANCE.mOkHttpClient = OkHttpProvider.getNoCacheOkHttpClient();
        return INSTANCE;
    }

    /**
     * 没有网络时读取缓存，wifi状态下
     */
    public static ServiceFactory getShortCacheInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceFactory();
        }
        INSTANCE.mOkHttpClient = OkHttpProvider.getShortCahceOkHttpClient();
        return INSTANCE;
    }

    public static ServiceFactory getLongCacheInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceFactory();
        }
        INSTANCE.mOkHttpClient = OkHttpProvider.getLongCacheOkHttpClient();
        return INSTANCE;
    }

    public <S> S createService(Class<S> serviceClass) {
        String baseUrl = "";
        try {
            Field field1 = serviceClass.getField("BASE_URL");
            baseUrl = (String) field1.get(serviceClass);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.getMessage();
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    private OkHttpClient mOkHttpClient;
    private static ServiceFactory INSTANCE;

}
