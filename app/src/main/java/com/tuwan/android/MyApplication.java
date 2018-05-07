package com.tuwan.android;

import com.tuwan.android.common.data.KeyValue;
import com.tuwan.android.common.base.BaseApplication;
import com.tuwan.android.common.utils.SharedPreferencesUtils;

import okhttp3.Request;

/**
 * Created by gumenghao on 2018/5/7.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //模拟登陆，获取到token
        SharedPreferencesUtils.writeString(KeyValue.PublicKey.COOKIE,"Tuwan_Passport=848FF61B78FBC6564863F57D" +
                "070ED4E0101A22E56602D584759488275ABE500C4610B5107BB0CB26FDD479763063A5E9187A599CCAFD6786D38B858D4197A249");
        cookie = SharedPreferencesUtils.readString(KeyValue.PublicKey.COOKIE);

    }

    @Override
    public Request.Builder addOkHttpAddHeader(Request.Builder builder) {

        if (cookie != null) {

            return builder.addHeader(KeyValue.PublicKey.COOKIE, cookie);

        }

        return null;

    }

    private static String cookie;


}
