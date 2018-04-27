package com.pq.network.http.rx;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulers {


    /**
     * @param <T>
     * @return
     * 从io 装换到 main 线程
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}