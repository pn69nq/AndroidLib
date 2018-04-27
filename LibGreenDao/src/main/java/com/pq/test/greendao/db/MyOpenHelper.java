package com.pq.test.greendao.db;
import android.content.Context;
import android.util.Log;

import com.pq.test.greendao.dao.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * <pre>
 *     author : chenzengqiang
 *     e-mail : chenzengqiang@hongdoujiao.com
 *     time   : 2018/03/26
 *     desc   : 升级管理类
 *     version: 1.0
 * </pre>
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.w("test","db version update from " + oldVersion + " to " + newVersion);

        switch (oldVersion) {
            case 1:

                break;
        }

    }
}
