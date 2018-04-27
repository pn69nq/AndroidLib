package com.pq.network.http.listener;


import io.reactivex.Observable;

/**
 * 上游回调方法
 */
public abstract class HttpOnNextListener<T> {
    /**
     * 成功后回调方法
     * @param t
     */
    public abstract void onNext(T t);


    /**
     * 成功后的ober返回，扩展链接式调用
     * @param observable
     */
    public void onNext(Observable observable){

    }

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     * @param e
     */
    public  void onError(Throwable e){

    }

    /**
     * 取消回調
     */
    public void onCancel(){

    }

}
