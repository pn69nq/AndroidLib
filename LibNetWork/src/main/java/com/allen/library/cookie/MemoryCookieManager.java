package com.allen.library.cookie;

import java.util.HashMap;
import java.util.HashSet;

/**
 * <pre>
 *     author : chenzengqiang
 *     e-mail : chenzengqiang@hongdoujiao.com
 *     time   : 2018/7/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MemoryCookieManager implements CookieManager{


    private HashMap<String,Object> mCookiesMap;

    public MemoryCookieManager(){
        mCookiesMap = new HashMap<>();
    }


    @Override
    public HashSet<String> getCookie(String key) {
        Object cookie = mCookiesMap.get(CookieManager.KEY_COOKIE);
        if(cookie != null){
            return (HashSet<String>) cookie;
        }
        return null;
    }

    @Override
    public void setCookie(String key, HashSet<String> cookie) {
       mCookiesMap.put(key,cookie);
    }

    @Override
    public void setDate(String key,long timestamp) {
        mCookiesMap.put(key,timestamp);
    }
}
