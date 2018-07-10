package com.allen.library.http;


import com.allen.library.interceptor.AddCookiesInterceptor;
import com.allen.library.interceptor.CacheInterceptor;
import com.allen.library.interceptor.HeaderInterceptor;
import com.allen.library.interceptor.ReceivedCookiesInterceptor;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;


public class RxHttpBuilder {

    private HttpClient mHttpClient;
    private RetrofitClient mRetrofitClient;

    public RxHttpBuilder(HttpClient httpClient,RetrofitClient retrofitClient){
        mHttpClient = httpClient;
        mRetrofitClient = retrofitClient;
    }


    /**
     * 设置baseUrl
     *
     * @param baseUrl
     * @return
     */
    public RxHttpBuilder setBaseUrl(String baseUrl) {
        getGlobalRetrofitBuilder().baseUrl(baseUrl);
        return this;
    }

    /**
     * 设置自己的client
     *
     * @param okClient
     * @return
     */
    public RxHttpBuilder setOkClient(OkHttpClient okClient) {
        getGlobalRetrofitBuilder().client(okClient);
        return this;
    }


    /**
     * 添加统一的请求头
     *
     * @param headerMaps
     * @return
     */
    public RxHttpBuilder setHeaders(Map<String, Object> headerMaps) {
        getGlobalOkHttpBuilder().addInterceptor(new HeaderInterceptor(headerMaps));
        return this;
    }

    /**
     * 是否开启请求日志
     *
     * @param isShowLog
     * @return
     */
    public RxHttpBuilder setLog(boolean isShowLog,HttpLoggingInterceptor.Logger logger) {
        if (isShowLog && logger != null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger);
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            getGlobalOkHttpBuilder().addInterceptor(loggingInterceptor);
        }
        return this;
    }

    /**
     * 开启缓存，缓存到默认路径
     *
     * @return
     */
    public RxHttpBuilder setCache(String cacheDir, CacheInterceptor cacheInterceptor) {
        Cache cache = new Cache(new File(cacheDir)
                , 1024 * 1024 * 100);
        getGlobalOkHttpBuilder().addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache);
        return this;
    }

    /**
     * 设置缓存路径及缓存文件大小
     *
     * @param cachePath
     * @param maxSize
     * @return
     */
    public RxHttpBuilder setCache(String cachePath, long maxSize, CacheInterceptor cacheInterceptor) {
        if (cachePath != null && cachePath.length() > 0 && maxSize > 0) {
            Cache cache = new Cache(new File(cachePath), maxSize);
            getGlobalOkHttpBuilder()
                    .addInterceptor(cacheInterceptor)
                    .addNetworkInterceptor(cacheInterceptor)
                    .cache(cache);
        }

        return this;
    }

    /**
     * 持久化保存cookie保存
     *
     * @param saveCookie
     * @return
     */
    public RxHttpBuilder setCookie(boolean saveCookie, AddCookiesInterceptor addCookiesInterceptor) {
        if (saveCookie) {
            getGlobalOkHttpBuilder()
                    .addInterceptor(addCookiesInterceptor)
                    .addInterceptor(new ReceivedCookiesInterceptor());
        }
        return this;
    }


    /**
     * 设置读取超时时间
     *
     * @param second
     * @return
     */
    public RxHttpBuilder setReadTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置写入超时时间
     *
     * @param second
     * @return
     */
    public RxHttpBuilder setWriteTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 设置连接超时时间
     *
     * @param second
     * @return
     */
    public RxHttpBuilder setConnectTimeout(long second) {
        getGlobalOkHttpBuilder().readTimeout(second, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 信任所有证书,不安全有风险
     *
     * @return
     */
    public RxHttpBuilder setSslSocketFactory() {
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory();
        getGlobalOkHttpBuilder().sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * 使用预埋证书，校验服务端证书（自签名证书）
     *
     * @param certificates
     * @return
     */
    public RxHttpBuilder setSslSocketFactory(InputStream... certificates) {
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory(certificates);
        getGlobalOkHttpBuilder().sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * 使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
     *
     * @param bksFile
     * @param password
     * @param certificates
     * @return
     */
    public RxHttpBuilder setSslSocketFactory(InputStream bksFile, String password, InputStream... certificates) {
        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory(bksFile, password, certificates);
        getGlobalOkHttpBuilder().sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }


    /**
     * 全局的 retrofit
     *
     * @return
     */
    public Retrofit getGlobalRetrofit() {
        return mRetrofitClient.getRetrofit();
    }

    /**
     *
     * @return
     */
    public Retrofit.Builder getGlobalRetrofitBuilder() {
        return mRetrofitClient.getRetrofitBuilder();
    }

    public OkHttpClient.Builder getGlobalOkHttpBuilder() {
        return mHttpClient.getBuilder();
    }

    /**
     * 使用全局变量的请求
     *
     * @param cls
     * @param <K>
     * @return
     */
    public <K> K createGApi(final Class<K> cls) {
        return getGlobalRetrofit().create(cls);
    }


}
