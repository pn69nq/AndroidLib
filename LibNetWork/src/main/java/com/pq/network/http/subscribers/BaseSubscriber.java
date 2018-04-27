package com.pq.network.http.subscribers;

import com.pq.network.http.HttpConfig;
import com.pq.network.http.listener.HttpOnNextListener;

import java.lang.ref.SoftReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseSubscriber<T> implements Observer<T> {

    /* 软引用回调接口*/
    private SoftReference<HttpOnNextListener> mSubscriberOnNextListener;

    /**
     * 构造
     *
     * @param config
     */
    public BaseSubscriber(HttpConfig config ) {
        this.mSubscriberOnNextListener = config.getListener();
    }


    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onNext(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onError(e);
        }
    }
}