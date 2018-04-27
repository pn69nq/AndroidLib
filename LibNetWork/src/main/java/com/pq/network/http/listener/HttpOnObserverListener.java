package com.pq.network.http.listener;

import com.pq.network.http.api.BaseResultEntity;

public interface HttpOnObserverListener<T> {
    boolean isSuc(BaseResultEntity<T> value);
    void onHandleSuccess(T value);
    void onHandleFail(BaseResultEntity<T> value);
    void onHandleError(Throwable e);
}
