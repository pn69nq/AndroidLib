package com.pq.test;

import android.app.Application;

import com.pq.test.greendao.db.GreenDaoDb;

/**
 * <pre>
 *     author : chenzengqiang
 *     e-mail : chenzengqiang@hongdoujiao.com
 *     time   : 2018/03/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GreenDaoDb.init(this,"test.db","google");
        GreenDaoDb.enableQueryBuilderLog();
    }







}
