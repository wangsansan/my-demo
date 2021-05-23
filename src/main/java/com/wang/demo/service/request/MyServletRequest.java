package com.wang.demo.service.request;

import java.util.Random;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/23 6:31 下午
 */
public class MyServletRequest implements MyRequest {

    private Long time;

    public MyServletRequest() {
        long random = new Random().nextLong();
        this.time = Long.MAX_VALUE - Math.abs(random);
    }

    public Long getTime() {
        return time;
    }

    @Override
    public Long getMyTime() {
        return time;
    }
}
