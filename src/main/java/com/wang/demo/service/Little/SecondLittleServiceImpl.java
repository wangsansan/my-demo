package com.wang.demo.service.Little;

import com.wang.demo.service.request.MyRequest;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/23 6:53 下午
 */
public class SecondLittleServiceImpl implements LittleService {

    private MyRequest myRequest;

    public MyRequest getMyRequest() {
        return myRequest;
    }

    public void setMyRequest(MyRequest myRequest) {
        this.myRequest = myRequest;
    }

    @Override
    public String showMyName() {
        return "send little service" + myRequest.getMyTime();
    }
}
