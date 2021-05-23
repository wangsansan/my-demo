package com.wang.demo.controller;

import com.wang.demo.service.Little.LittleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/23 7:00 下午
 */
@RestController
@RequestMapping("/little/")
public class MyLittleController {

    @Resource
    private LittleService littleService;

    @GetMapping("name")
    public String getMyLittleName() {
        return littleService.showMyName();
    }

}
