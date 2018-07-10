package com.allen.library.http;

import okhttp3.OkHttpClient;

/**
 * Created by allen on 2017/5/31.
 * <p>
 *
 * @author Allen
 *         okHttp client
 */

public class HttpClient {

    private OkHttpClient.Builder builder;

    public HttpClient() {
        builder = new OkHttpClient.Builder();
    }


    public OkHttpClient.Builder getBuilder() {
        return builder;
    }

}
