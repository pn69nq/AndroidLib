package com.allen.library;

import com.allen.library.cookie.CookieManager;
import com.allen.library.http.RxHttpBuilder;
import com.allen.library.http.SingleRxHttp;
import com.allen.library.lifecycle.LifeCycleManager;


public class RxHttpUtils {
    private static RxHttpUtils instance;
    private LifeCycleManager mLifeCycleManager;
    private CookieManager mCookieManager;
    private RxHttpBuilder mRxHttpBuilder;



    public RxHttpUtils(){
        mLifeCycleManager = new LifeCycleManager();
        mRxHttpBuilder = new RxHttpBuilder();
    }

    public static RxHttpUtils getInstance() {
        if (instance == null) {
            synchronized (RxHttpUtils.class) {
                if (instance == null) {
                    instance = new RxHttpUtils();
                }
            }
        }
        return instance;
    }

    public RxHttpBuilder config() {
        return mRxHttpBuilder;
    }

    public LifeCycleManager getLifeCycleManager() {
        return mLifeCycleManager;
    }

    /**
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public <K> K createApi(Class<K> cls) {
        return mRxHttpBuilder.createGApi(cls);
    }

    /**
     * 获取单个请求配置实例
     *
     * @return SingleRxHttp
     */
    public SingleRxHttp getSInstance() {
        return SingleRxHttp.getInstance();
    }



}
