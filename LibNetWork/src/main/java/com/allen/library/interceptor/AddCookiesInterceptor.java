package com.allen.library.interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Allen on 2017/5/11.
 * <p>
 *
 *         请求头里边添加cookie
 */

public class AddCookiesInterceptor implements Interceptor {

    private HashSet<String> mCookies;
    public AddCookiesInterceptor(HashSet<String> cookies){
        mCookies = cookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (mCookies != null) {
            for (String cookie : mCookies) {
                builder.addHeader("Cookie", cookie);
                // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
                Log.v("RxHttpUtils", "Adding Header Cookie--->: " + cookie);
            }
        }
        return chain.proceed(builder.build());
    }

}
