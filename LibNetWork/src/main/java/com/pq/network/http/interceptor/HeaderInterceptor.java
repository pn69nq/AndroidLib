package com.pq.network.http.interceptor;

import android.util.ArrayMap;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class HeaderInterceptor implements Interceptor {

    private ArrayMap<String,String> mHeaders;
    public HeaderInterceptor(ArrayMap<String, String> headers){
        mHeaders = headers;
    }

    @Override public okhttp3.Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        //如果公共请求头不为空,则构建新的请求
        if (mHeaders != null) {
            Request.Builder requestBuilder = originalRequest.newBuilder();
            for (String key : mHeaders.keySet()) {
                requestBuilder.addHeader(key, mHeaders.get(key));
            }
            requestBuilder.method(originalRequest.method(), originalRequest.body());
            return chain.proceed(requestBuilder.build());
        }
        return chain.proceed(originalRequest);
    }
}