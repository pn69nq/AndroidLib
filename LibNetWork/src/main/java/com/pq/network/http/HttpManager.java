package com.pq.network.http;

import android.util.Log;

import com.pq.network.http.api.BaseApi;
import com.pq.network.http.listener.HttpOnNextListener;
import com.pq.network.http.rx.RxSchedulers;
import com.pq.network.http.subscribers.BaseSubscriber;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http交互处理类
 *
 *
 */
public class HttpManager {
    private volatile static HttpManager INSTANCE;


    //构造方法私有
    private HttpManager() {

    }

    //获取单例
    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 处理http请求
     *
     * @param baseApi 封装的请求数据
     */
    public void doHttpDeal(HttpConfig config,BaseApi baseApi,boolean isDebug) {
        //手动创建一个OkHttpClient并设置超时时间缓存等设置
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(config.getConnectionTime(), TimeUnit.SECONDS);
        if(isDebug){
            builder.addInterceptor(getHttpLoggingInterceptor());
        }

        /*创建retrofit对象*/
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(config.getBaseUrl())
                .build();

        Observable observable = baseApi.getObservable(retrofit)
                 /*失败后的retry配置*/
//                .retryWhen(new RetryWhenNetworkException(basePar.getRetryCount(),
//                        basePar.getRetryDelay(), basePar.getRetryIncreaseDelay()))
//                /*生命周期管理*/
                .compose(RxSchedulers.io_main());


        /*链接式对象返回*/
        SoftReference<HttpOnNextListener> httpOnNextListener = config.getListener();
        if (httpOnNextListener != null && httpOnNextListener.get() != null) {
            httpOnNextListener.get().onNext(observable);
        }
        BaseSubscriber subscriber = new BaseSubscriber<>(config);

        /*数据回调*/
        observable.subscribe(subscriber);
    }


    /**
     * 日志输出
     * 自行判定是否添加
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor(){
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("RxRetrofit","Retrofit====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
