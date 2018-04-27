package com.pq.network.http.api;


import com.pq.network.http.listener.HttpOnObserverListener;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Retrofit;

/**
 * <pre>
 *     author : chenzengqiang
 *     e-mail : chenzengqiang@hongdoujiao.com
 *     time   : 2018/03/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class BaseApi<T> implements Observer<BaseResultEntity<T>>,HttpOnObserverListener<T> {


    //定义上游代码，实现调用http调用具体代码，返回对应的observable
    public abstract Observable getObservable(Retrofit retrofit);

    //定义下游
    @Override
    public void onNext(BaseResultEntity<T> value) {
        if(isSuc(value)){
            onHandleSuccess(value.getData());
        }
        else{
            onHandleFail(value);
        }
    }

    @Override
    public void onError(Throwable e) {
        onHandleError(e);
    }
}
