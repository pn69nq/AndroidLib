package com.allen.library.cookie;

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
public interface CookieManager {
     static final String KEY_COOKIE = "key_cookie";
     static final String KEY_TIMESTAMP = "key_timestamp";
     HashSet<String> getCookie(String key);
     void setCookie(String key,HashSet<String> cookie);
     void setDate(String key,long timestamp);
}
