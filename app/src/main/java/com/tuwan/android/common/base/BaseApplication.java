package com.tuwan.android.common.base;

import android.app.Application;
import android.os.Environment;
import java.io.File;

import okhttp3.Request;

/**
 * @author gumenghao .
 * @version v1.0 .
 * @date 2018/5/7.
 * @file BaseApplication.java .
 * @brief BaseApplication .
 */
public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        mApplacation = this;

    }


    //缓存路径
    @Override
    public File getCacheDir() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File cacheDir = getExternalCacheDir();

            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {

                return cacheDir;

            }
        }
        return super.getCacheDir();

    }

    public abstract Request.Builder addOkHttpAddHeader(Request.Builder builder);


    public static BaseApplication getInstance() {

        return mApplacation;

    }


    private static BaseApplication mApplacation;

}
