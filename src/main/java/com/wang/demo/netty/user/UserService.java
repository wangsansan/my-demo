package com.wang.demo.netty.user;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/11 20:16
 */
public class UserService {

    public static final Map<String, String> userInfo = Maps.newHashMap();

    {
        userInfo.put("wangsan", "123456");
        userInfo.put("lisi", "123456");
        userInfo.put("zhaowu", "123456");
        userInfo.put("sunliu", "123456");
    }

    public boolean login(String userName, String password) {
        if (!userInfo.containsKey(userName)) {
            return false;
        }
        return userInfo.get(userName).equals(password);
    }

}
