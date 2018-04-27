# 初始化greendao
```
     DbCore.init(context,"dbname","password");
     DbCore.enableQueryBuilderLog();
```
       
# db 包
```
    BaseDbHelper：提过dao的增删改查
    GreenDaoDb：DaoMaster DaoSession 通过daosession 可以获取对应的xyzDao
    MyOpenHelper：提供数据库升级更新用
    
```

# 