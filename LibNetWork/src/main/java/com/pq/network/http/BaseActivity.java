package com.pq.network.http;

import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;

/**
 * <pre>
 *     author : chenzengqiang
 *     e-mail : chenzengqiang@hongdoujiao.com
 *     time   : 2018/04/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BaseActivity extends AppCompatActivity {
    private CompositeDisposable disposables2Stop;// 管理Stop取消订阅者者
    private CompositeDisposable disposables2Destroy;// 管理Destroy取消订阅者者

}
