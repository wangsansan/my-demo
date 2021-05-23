package com.wang.demo.service.Little;

import com.wang.demo.service.request.MyRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/23 6:52 下午
 */
public class PrimaryLittleServiceImpl implements LittleService {

    private MyRequest myRequest;

    public MyRequest getMyRequest() {
        return myRequest;
    }

    public void setMyRequest(MyRequest myRequest) {
        this.myRequest = myRequest;
    }

    @Override
    public String showMyName() {
        return "primary little service" + myRequest.getMyTime();
    }
}
