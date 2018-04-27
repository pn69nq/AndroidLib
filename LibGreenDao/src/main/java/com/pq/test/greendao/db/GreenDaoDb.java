package com.pq.test.greendao.db;
import android.content.Context;


import com.pq.test.greendao.dao.DaoMaster;
import com.pq.test.greendao.dao.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;
/**
 * <pre>
 *     author : chenzengqiang
 *     e-mail : chenzengqiang@hongdoujiao.com
 *     time   : 2018/03/26
 *     desc   : greendao 获取DaoMaster DaoSession xyzDao
 *     version: 1.0
 * </pre>
 */

public class GreenDaoDb {
    private static final String DEFAULT_DB_NAME = "green-dao3.db";
    private static final String DEFAULT_DB_PASSWORD = "666";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    private static String DB_NAME;
    private static String DB_PASSWORD;

    public static void init(Context context) {
        init(context, DEFAULT_DB_NAME,DEFAULT_DB_PASSWORD);
    }

    public static void init(Context context, String dbName,String password) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        DB_NAME = dbName;
        DB_PASSWORD = password;
    }

    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            //不用 DaoMaster.DevOpenHelper, 需要自定义一个，方便升级
            DaoMaster.OpenHelper helper = new MyOpenHelper(context.getApplicationContext(), DB_NAME);
            if(DB_PASSWORD != null){
                daoMaster = new DaoMaster(helper.getEncryptedReadableDb(DB_PASSWORD));
            }
            else{
                daoMaster = new DaoMaster(helper.getReadableDb());
            }
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static void enableQueryBuilderLog(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    public static <T,K> BaseDbHelper<T,K> getDaoHelper(AbstractDao<T,K> dao){
        return new BaseDbHelper<>(dao);
    }
}
