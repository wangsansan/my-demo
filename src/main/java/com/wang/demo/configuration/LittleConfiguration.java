package com.wang.demo.configuration;

import com.wang.demo.service.Little.LittleService;
import com.wang.demo.service.Little.PrimaryLittleServiceImpl;
import com.wang.demo.service.Little.SecondLittleServiceImpl;
import com.wang.demo.service.request.MyRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/23 7:24 下午
 */
@Configuration
public class LittleConfiguration {

    @Value("${little.use}")
    private Integer littleUse;

    public static final int second = 2;

    @Bean
    @Resource
    public LittleService littleService(MyRequest myRequest) {
        if (littleUse == second) {
            SecondLittleServiceImpl secondLittleService  = new SecondLittleServiceImpl();
            secondLittleService.setMyRequest(myRequest);
            return secondLittleService;
        }

        PrimaryLittleServiceImpl primaryLittleService = new PrimaryLittleServiceImpl();
        primaryLittleService.setMyRequest(myRequest);
        return primaryLittleService;
    }

}
