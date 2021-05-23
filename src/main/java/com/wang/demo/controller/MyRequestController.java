package com.wang.demo.controller;

import com.wang.demo.service.MyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/23 6:37 下午
 */
@RestController
@RequestMapping("/request/")
public class MyRequestController {

    @Resource
    private MyRequest myRequest;

    @GetMapping("time")
    public Long getMyRequestTime() {
        return myRequest.getMyTime();
    }

}
